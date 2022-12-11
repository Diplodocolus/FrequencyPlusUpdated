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
    private final String broadcast = ColorUtil.format("&8[&7Frequency+&8] &a%s &7was found using an unfair advantage and was removed from the network.");

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

        final String alert = data == "" ? DARK_GRAY + "[" + GRAY + "Frequency+" + DARK_GRAY + "] " + GREEN + player.getName() + GRAY + " failed " + DARK_GREEN +
                check.getCheckName() + GOLD + " [" + GRAY + "VL" + GREEN + violations + GOLD + "]" : DARK_GRAY + "[" + GRAY + "Frequency+" + DARK_GRAY + "] " +
                GREEN + player.getName() + GRAY + " failed " + DARK_GREEN + check.getCheckName() + GOLD + " [" + GRAY + "VL " + GREEN + violations + GOLD + "]" +
                GRAY + ", " + data;
        final String message = String.format(broadcast, player.getName());
        if (violations > threshold) {
            //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + player.getName() + " [Frequency] Unfair Advantage");;
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
            base = DARK_GRAY + "[" + GRAY + "Frequency+" + DARK_GRAY + "] " + GREEN + name + GRAY + " failed " + DARK_GREEN + checkName + GOLD + "[" +
                GRAY + "VL" + GREEN + violations + GOLD + "]";
        } else {
            base = DARK_GRAY + "[" + GRAY + "Frequency+" + DARK_GRAY + "] " + GREEN + name + GRAY + " failed " + DARK_GREEN + checkName + GOLD + "[" +
                    GRAY + "VL" + GREEN + violations + GOLD + "]" + GRAY + ", " + data;
        }

        return base;
    }
}
