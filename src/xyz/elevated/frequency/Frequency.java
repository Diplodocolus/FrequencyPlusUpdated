package xyz.elevated.frequency;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.JsonList;
import net.minecraft.server.v1_8_R3.JsonListEntry;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import static org.bukkit.ChatColor.*;

import org.bukkit.plugin.Plugin;
import xyz.elevated.frequency.api.FrequencyPlusAPI;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.data.type.PlayerDataManager;
import xyz.elevated.frequency.listener.CancelledListener;
import xyz.elevated.frequency.listener.PlayerListener;
import xyz.elevated.frequency.processor.ProcessorManager;
import xyz.elevated.frequency.tick.TickManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Getter
public enum Frequency {
    INSTANCE;

    private FrequencyPlugin plugin;

    private final ProcessorManager processorManager = new ProcessorManager();
    private final PlayerDataManager playerDataManager = new PlayerDataManager();
    private final TickManager tickManager = new TickManager();

    private final Executor executorAlert = Executors.newSingleThreadExecutor();
    private final Executor executorPacket = Executors.newSingleThreadExecutor();

    public static final FrequencyPlusAPI frequencyPlusAPI = new FrequencyPlusAPI();
    public void start(final FrequencyPlugin plugin) {
        plugin.saveDefaultConfig();
        this.plugin = plugin;
        assert plugin != null : "Something went wrong! The plugin was null. (Startup)";

        tickManager.start();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new CancelledListener(), plugin);
    }

    public void stop(final FrequencyPlugin plugin) {
        this.plugin = plugin;

        assert plugin != null : "Something went wrong! The plugin was null. (Shutdown)";

        tickManager.stop();
    }

    public Executor getExecutorAlert() {
        return executorAlert;
    }

    public TickManager getTickManager() {
        return tickManager;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public Executor getExecutorPacket() {
        return executorPacket;
    }

    public ProcessorManager getProcessorManager() {
        return processorManager;
    }
}
