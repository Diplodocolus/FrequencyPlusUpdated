package xyz.elevated.frequency.check.impl.speed;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MathHelper;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import xyz.elevated.frequency.Frequency;
import xyz.elevated.frequency.FrequencyPlugin;
import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.PositionCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.exempt.type.ExemptType;
import xyz.elevated.frequency.update.PositionUpdate;
import xyz.elevated.frequency.util.MathUtil;
import xyz.elevated.frequency.util.NmsUtil;


@CheckData(name = "Speed (A)")
public final class SpeedA extends PositionCheck {
    private int buffer = 0;
    private double blockSlipperiness = 0.91;
    private double lastHorizontalDistance = 0.0;

    public SpeedA(final PlayerData playerData) {
        super(playerData);
    }

    /*
     * Most values are found in the EntityLivingBase class on the client-side.
     * They can even be found inside the EntityLiving nms class.
     *
     * Don't modify unless you know what you're doing.
     */

    @Override
    public void process(final PositionUpdate positionUpdate) {
        if (!FrequencyPlugin.getFrequencyConfig().getBoolean("checks.speed.enable")) {
            return;
        }
        // Get the location update from the position update
        final Location from = positionUpdate.getFrom();
        final Location to = positionUpdate.getTo();

        // Get the entity player from the NMS util
        final Player player = playerData.getBukkitPlayer();
        final EntityPlayer entityPlayer = NmsUtil.INSTANCE.getEntityPlayer(playerData);

        // Get the pos deltas
        final double deltaX = to.getX() - from.getX();
        final double deltaY = to.getY() - from.getY();
        final double deltaZ = to.getZ() - from.getZ();

        // Get the player's attribute speed and last friction
        double blockSlipperiness = this.blockSlipperiness;
        double attributeSpeed = 1.d;

        // Run calculations to if the player is on ground and if they're exempt
        final boolean onGround = entityPlayer.onGround;
        final boolean exempt = this.isExempt(ExemptType.TPS, ExemptType.TELEPORTING);

        // Properly calculate max jump for jump threshold
        final int modifierJump = MathUtil.getPotionLevel(player, PotionEffectType.JUMP);

        /*
         * How minecraft calculates speed increase. We cast as a float because this is what the client does.
         * MCP just prints the casted float as a double. 0.2 is the effect modifier.
         */
        attributeSpeed += MathUtil.getPotionLevel(player, PotionEffectType.SPEED) * (float)0.2 * attributeSpeed;

        //How minecraft calculates slowness. 0.15 is the effect modifier.
        attributeSpeed += MathUtil.getPotionLevel(player, PotionEffectType.SLOW) * (float)-.15 * attributeSpeed;

        if (onGround) {
            blockSlipperiness *= 0.91f;

            if(playerData.getSprinting().get()) attributeSpeed *= 1.3; //This basically just replicates math done by the client with a single constant.
            attributeSpeed *= 0.16277136 / Math.pow(blockSlipperiness, 3);

            //Only do this when the player is sprinting. You dont move forward without sprinting my guy.
            if (deltaY > 0.4199 + modifierJump * 0.1 && playerData.getSprinting().get()) {
                /*
                 * It's not necessary to do any angle work since it'll always be a factor of 0.2.
                 * Angle work is only necessary if we are checking motionX motionZ on its own, not together.
                 */
                attributeSpeed += 0.2;
            }
        } else {
            /*
             * We use the Player object as this will effectively be the previous tick.
             * 0.026 is the value whe the player sprints, while 0.02 is when walking.
             */
            attributeSpeed = playerData.getSprinting().get() ? 0.026 : 0.02;

            //This is basically the air resistance of the player.
            blockSlipperiness = 0.91f;
        }

        // Add to the attribute speed according to velocity
        attributeSpeed += playerData.getVelocityManager().getMaxHorizontal();

        // Get the horizontal distance and convert to the movement speed
        final double horizontalDistance = MathUtil.magnitude(deltaX, deltaZ);
        final double movementSpeed = (horizontalDistance - lastHorizontalDistance) / attributeSpeed;

        // If thr movement speed is greater than the threshold and the player isn't exempt, fail
        if (movementSpeed > FrequencyPlugin.getFrequencyConfig().getDouble("checks.speed.max_speed") && !exempt) {
            buffer = Math.min(500, buffer + 10); //We do this to prevent integer overflow.

            if (buffer > 40) {
                fail("player speed is faster than normal, speed=(" + movementSpeed + "), buffer=(" + buffer + ")");
                lagback(FrequencyPlugin.getFrequencyConfig().getBoolean("checks.speed.lagback"));
                buffer /= 2;
            }
        } else {
            buffer = Math.max(buffer - 1, 0);
        }

        // Update previous values
        this.blockSlipperiness = entityPlayer.world.getType(new BlockPosition(MathHelper.floor(to.getX()),
                MathHelper.floor(to.getY()) - 1,
                MathHelper.floor(to.getZ())))
                .getBlock()
                .frictionFactor * 0.91F;

        this.lastHorizontalDistance = horizontalDistance * blockSlipperiness;
    }
}