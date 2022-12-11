package xyz.elevated.frequency.data.impl;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import xyz.elevated.frequency.check.type.BlockCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.update.BlockUpdate;

@Getter
public class BlockManager {
    private PlayerData playerData;

    private float yaw, pitch;
    private float lastYaw, lastPitch;
    private Location blockLoc, playerLoc;
    private ItemStack playerHandBlock;
    private boolean onGround;

    public BlockManager(PlayerData playerData, float yaw, float pitch, float lastYaw, float lastPitch, Location blockLoc, Location playerLoc,
                        ItemStack playerHandBlock, boolean onGround) {
        this.playerData = playerData;
        this.yaw = yaw;
        this.pitch = pitch;
        this.lastYaw = lastYaw;
        this.lastPitch = lastPitch;
        this.blockLoc = blockLoc;
        this.playerLoc = playerLoc;
        this.playerHandBlock = playerHandBlock;
        this.onGround = onGround;
    }

    public BlockManager(final PlayerData playerData) {
        this.playerData = playerData;
    }

    public void handle(final float yaw, final float pitch, PlayerData data, Location blockLoc, boolean onGround) {
        final float deltaYaw = Math.abs(yaw - lastYaw);
        final float deltaPitch = Math.abs(yaw - lastPitch);
        final Location playerLoc = data.getBukkitPlayer().getLocation();
        final BlockUpdate blockUpdate = new BlockUpdate(playerLoc, blockLoc, onGround, deltaYaw, deltaPitch);
        playerData.getCheckManager().getChecks().stream().filter(BlockCheck.class::isInstance).forEach(check -> {
            check.process(blockUpdate);
        });

        this.lastYaw = yaw;
        this.lastPitch = pitch;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.playerLoc = playerLoc;
        this.blockLoc = blockLoc;
        this.playerHandBlock = data.getBukkitPlayer().getItemInHand();
    }
}
