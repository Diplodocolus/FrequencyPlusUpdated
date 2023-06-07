package xyz.elevated.frequency.alert;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.elevated.frequency.Frequency;
import xyz.elevated.frequency.api.EventType;
import xyz.elevated.frequency.api.event.VerboseEvent;
import xyz.elevated.frequency.check.Check;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.util.ColorUtil;

import java.util.List;

import static org.bukkit.ChatColor.*;

public final class AlertManager {
    private final Check<?> check;

    public AlertManager(Check<?> check) {
        this.check = check;
    }
    private final String broadcast = ColorUtil.format("&8[&dFrequency&6+&8] &a%s &7has been removed from the network");

    private final List<Long> alerts = Lists.newArrayList();

    public void fail(String data) {
        final long now = System.currentTimeMillis();

        final PlayerData playerData = check.getPlayerData();
        final Player player = playerData.getBukkitPlayer();

        if (alerts.contains(now)) {
            return;
        }

        alerts.add(now);

        final int violations = (int) (alerts.stream().filter(violation -> violation + 9000L > System.currentTimeMillis()).count());
        final int threshold = check.getThreshold();

        final String alert = data == "" ? DARK_GRAY + "[" + LIGHT_PURPLE + "Frequency" + GOLD + "+" + DARK_GRAY + "] " + DARK_PURPLE + player.getName() + GRAY + " failed " + DARK_PURPLE +
                check.getCheckName() + GOLD + " [" + GRAY + "VL" + DARK_PURPLE + violations + GOLD + "]" : DARK_GRAY + "[" + LIGHT_PURPLE + "Frequency" + GOLD + "+" + DARK_GRAY + "] " +
                GREEN + player.getName() + GRAY + " failed " + DARK_PURPLE + check.getCheckName() + GOLD + " [" + GRAY + "VL " + DARK_PURPLE + violations + GOLD + "]" +
                GRAY + ", " + data;
        final String message = String.format(broadcast, player.getName());
        if (violations > threshold) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban " + player.getName() + " 1h -s &8[&5Frequency&6+&8] &7Unfair Advantage.");
            Bukkit.broadcastMessage(message);

            alerts.clear();
        }

        // Execute the alert on a separate thread as we need to loop
        Frequency.INSTANCE.getExecutorAlert().execute(() -> Bukkit.getOnlinePlayers()
                        .stream()
                        .filter(send -> send.hasPermission("frequencyplus.alerts"))
                        .forEach(send -> {
                            send.sendMessage(alert);
                        }));

    }

    public String getAlert(String name, String checkName, int violations, String data) {
        String base = "";
        if (data == "") {
            base = DARK_GRAY + "[" + LIGHT_PURPLE + "Frequency" + GOLD + "+" + DARK_GRAY + "] " + DARK_PURPLE + name + GRAY + " failed " + DARK_PURPLE + checkName + GOLD + "[" +
                GRAY + "VL" + DARK_PURPLE + violations + GOLD + "]";
        } else {
            base = DARK_GRAY + "[" + LIGHT_PURPLE + "Frequency" + GOLD + "+" + DARK_GRAY + "] " + DARK_PURPLE + name + GRAY + " failed " + DARK_PURPLE + checkName + GOLD + "[" +
                    GRAY + "VL" + DARK_PURPLE + violations + GOLD + "]" + GRAY + ", " + data;
        }

        return base;
    }
}
