package xyz.elevated.frequency.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import xyz.elevated.frequency.api.EventType;

public class VerboseEvent extends Event {
    private static HandlerList handlerList = new HandlerList();
    private boolean cancelled;
    private EventType type;

    public void setType(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public boolean isPre() {
        if (type == EventType.PRE) {
            return true;
        }
        return false;
    }

    public boolean isPost() {
        if (type == EventType.POST) {
            return true;
        }
        return false;
    }

    public boolean isCancelled() {
        return true;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static final HandlerList getHandlerList() {
        return handlerList;
    }
}
