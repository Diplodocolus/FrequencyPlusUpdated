package xyz.elevated.frequency.update;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter @Setter
public final class BlockUpdate {
    private Location userLocation, blockLocation;
    private boolean onGround;
    private float deltaYaw, deltaPitch;

    public BlockUpdate(Location userLocation, Location blockLocation, boolean onGround, float deltaYaw, float deltaPitch) {
        this.userLocation = userLocation;
        this.blockLocation = blockLocation;
        this.onGround = onGround;
        this.deltaYaw = deltaYaw;
        this.deltaPitch = deltaPitch;
    }

    public float getDeltaYaw() {
        return deltaYaw;
    }

    public float getDeltaPitch() {
        return deltaPitch;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public Location getBlockLocation() {
        return blockLocation;
    }

    public boolean isOnGround() {
        return onGround;
    }
}
