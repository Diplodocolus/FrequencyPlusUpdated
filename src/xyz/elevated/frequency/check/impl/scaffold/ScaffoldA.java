package xyz.elevated.frequency.check.impl.scaffold;

import org.bukkit.Location;
import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.BlockCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.update.BlockUpdate;

@CheckData(name = "Scaffold (A)")
public class ScaffoldA extends BlockCheck {
    private static final double MAX_ANGLE = Math.toRadians(90);
    public ScaffoldA(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(BlockUpdate update) {
        final float yaw = update.getDeltaYaw();
        final float pitch = update.getDeltaYaw();

        final boolean onGround = update.isOnGround();

        final Location userLoc = update.getUserLocation();
        final Location blockLoc = update.getBlockLocation();

        // If greater than 90 in radians.
        if (yaw >= MAX_ANGLE) {
            fail();
        }
    }
}
