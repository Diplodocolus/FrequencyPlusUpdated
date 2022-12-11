package xyz.elevated.frequency.update;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.elevated.frequency.data.PlayerData;

@Getter @Setter
public final class PositionUpdate {
    private final Location from, to;
    private final boolean onGround;

    public PositionUpdate(Location from, Location to, boolean onGround) {
        this.from = from;
        this.to = to;
        this.onGround = onGround;
    }

    public Location getTo() {
        return to;
    }

    public Location getFrom() {
        return from;
    }

    public boolean isOnGround() {
        return onGround;
    }
}
