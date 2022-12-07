package xyz.elevated.frequency.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.elevated.frequency.data.PlayerData;

@AllArgsConstructor @Getter @Setter
public final class PositionUpdate {
    private final Location from, to;
    private final boolean onGround;
}
