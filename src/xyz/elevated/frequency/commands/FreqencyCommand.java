package xyz.elevated.frequency.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.elevated.frequency.Frequency;
import xyz.elevated.frequency.util.ColorUtil;

import static org.bukkit.ChatColor.*;
import static org.bukkit.ChatColor.RED;

public class FreqencyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                Frequency.INSTANCE.getPlugin().reloadConfig();
                sender.sendMessage(ColorUtil.format("&8[&dFrequency&6+&8] &aReload Success!"));
                return true;
            } else {
                sender.sendMessage(RED + "Are you mean, " + GRAY + "/frequency reload" + RED +"? Frequency dont know " + GRAY + "/frequencyplus " +
                        args[0] + RED + " command");
            }
        }
        return true;
    }
}
