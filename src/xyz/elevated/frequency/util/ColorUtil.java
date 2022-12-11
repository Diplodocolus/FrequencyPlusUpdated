package xyz.elevated.frequency.util;

import org.bukkit.ChatColor;

public class ColorUtil {

    // & to paragraph symbol
    public static String format(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
