package xyz.elevated.frequency.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@AllArgsConstructor @Getter @Setter
public final class BlockUpdate {
    private Location userLocation, blockLocation;
    private boolean onGround;
    private float deltaYaw, deltaPitch;
}
