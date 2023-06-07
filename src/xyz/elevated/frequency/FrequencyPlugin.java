package xyz.elevated.frequency;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.elevated.frequency.commands.FreqencyCommand;

import java.io.IOException;

import static org.bukkit.ChatColor.*;

public final class FrequencyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("frequency").setExecutor(new FreqencyCommand());

        Frequency.INSTANCE.start(this);
        fileConfiguration = getConfig();
    }

    @Override
    public void onDisable() {
        Frequency.INSTANCE.stop(this);
    }

    private static FileConfiguration fileConfiguration;

    public static FileConfiguration getFrequencyConfig() {
        return fileConfiguration;
    }
}
