package xyz.elevated.frequency;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import static org.bukkit.ChatColor.*;

public final class FrequencyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("frequency").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
                if (args.length > 0) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        reloadConfig();
                        sender.sendMessage(GREEN + "Success frequency reload!");
                        return true;
                    } else {
                        sender.sendMessage(RED + "Are you mean, " + GRAY + "/frequencyplus reload" + RED +"? Frequency dont know " + GRAY + "/frequencyplus " +
                                args[0] + RED + " command");
                    }
                }
                return true;
            }
        });
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
