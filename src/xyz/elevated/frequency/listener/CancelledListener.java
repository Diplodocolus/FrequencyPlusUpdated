package xyz.elevated.frequency.listener;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class CancelledListener implements Listener{
    @Getter
    private boolean lagback;

    public void setLagback(boolean configAllowed) {
        if (configAllowed) {
            lagback = true;
            return;
        }
        lagback = false;
    }
    @Getter @Setter
    private boolean cancelled;

    public CancelledListener() {
        this.cListener = this;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (this.lagback) {
            e.setTo(e.getFrom());
            lagback = false;
        }
    }

    @EventHandler
    public void onItemChange(PlayerItemHeldEvent e) {
        if (this.cancelled) {
            e.setCancelled(true);
            cancelled = false;
        }
    }

    public static CancelledListener cListener;

    public static final CancelledListener getListener() {
        return cListener;
    }

    public void setCancelled(boolean configAllowed) {
        if (configAllowed) {
            lagback = true;
            return;
        }
        lagback = false;
    }
}
