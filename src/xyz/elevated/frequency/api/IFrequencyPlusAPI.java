package xyz.elevated.frequency.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xyz.elevated.frequency.check.Check;
import xyz.elevated.frequency.data.PlayerData;

import java.util.Collection;
import java.util.UUID;

public interface IFrequencyPlusAPI {
    public Player getPlayer(UUID uuid);

    public PlayerData getPlayerData(Player player);

    public void register(Listener listener);

    @Override
    public String toString();

    public Collection<Check> getCheckList();
}
