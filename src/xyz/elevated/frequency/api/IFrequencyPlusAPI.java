package xyz.elevated.frequency.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xyz.elevated.frequency.Frequency;
import xyz.elevated.frequency.check.Check;
import xyz.elevated.frequency.data.PlayerData;

import java.util.Collection;
import java.util.UUID;

public interface IFrequencyPlusAPI {
    /**
     * find a player
     * @param uuid player uuid
     * @return Player
     */
    public Player getPlayer(UUID uuid);

    /**
     * manage a playerdata
     * @param player target
     * @return User's data
     */
    public PlayerData getPlayerData(Player player);

    /**
     * Register listener to Frequency+
     * @param listener Server Listener
     */
    public void register(Listener listener);

    /**
     * FrequencyPlusAPI class
     * @return class
     */
    @Override
    public String toString();

    /**
     * Return all checks
     * @return All Checks (Collection Check)
     */
    public Collection<Check> getCheckList();

    /**
     * Get a Frequency Class
     * @param clazz Class
     * @return Frequency
     */
    public abstract Frequency getFrequencyPlus(Class<?> clazz);

    /**
     * Get a Frequency Class
     */
    public abstract void registerAPI();

    /**
     * unregisterAPI (don't recommend)
     */
    public abstract void unregisterAPI();

    /**
     * disable frequency+ to force
     */
    public abstract void disablePlugin();
}
