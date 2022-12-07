package xyz.elevated.frequency.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import xyz.elevated.frequency.Frequency;
import xyz.elevated.frequency.FrequencyPlugin;
import xyz.elevated.frequency.alert.AlertManager;
import xyz.elevated.frequency.check.Check;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.data.impl.CheckManager;

import java.util.Collection;
import java.util.UUID;


/**
 * Frequency+ API
 *
 * You can get it with xyz.elevated.frequency.Frequency.frequencyPlusAPI
 */
public final class FrequencyPlusAPI implements IFrequencyPlusAPI {
    /**
     * Find Target API
     * @param uuid user uuid
     * @return target player
     */
    @Override
    public Player getPlayer(UUID uuid) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getUniqueId().equals(uuid)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Return target's playerdata
     * @param player Target Player
     * @return Target's PlayerData
     */
    @Override
    public PlayerData getPlayerData(Player player) {
        return new PlayerData(player);
    }

    /**
     * Register Event to Frequency+.
     * @param listener Custom Listener
     */
    @Override
    public void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, new FrequencyPlugin());
    }


    /**
     * Return this class location
     * @return Location
     */
    @Override
    public final String toString() {
        return "xyz.elevted.frequency.api:FrequencyPlusAPI";
    }

    /**
     * Return enabled checks
     * @return Check with Collection type
     */
    @Override
    public final Collection<Check> getCheckList() {
        return CheckManager.INSTANCE.getChecks();
    }
}
