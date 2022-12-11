package xyz.elevated.frequency.data;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import xyz.elevated.frequency.check.Check;
import xyz.elevated.frequency.data.impl.*;
import xyz.elevated.frequency.exempt.ExemptManager;
import xyz.elevated.frequency.observable.Observable;
import xyz.elevated.frequency.update.PositionUpdate;
import xyz.elevated.frequency.update.RotationUpdate;
import xyz.elevated.frequency.util.EvictingList;
import xyz.elevated.frequency.util.EvictingMap;
import xyz.elevated.frequency.util.NmsUtil;
import xyz.elevated.frequency.util.Pair;

import lombok.Getter;
import lombok.Setter;
import xyz.elevated.frequency.wrapper.PacketWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter @Setter
public final class PlayerData {
    private Player bukkitPlayer;
    private PlayerConnection connection;

    private final EvictingList<BoundingBox> boundingBoxes = new EvictingList<>(10);
    private EvictingList<Location> locationsSent = new EvictingList<>(10);
    private EvictingList<Pair<AxisAlignedBB, Integer>> targetLocations = new EvictingList<>(30);

    private final EvictingMap<Short, Long> transactionUpdates = new EvictingMap<>(20);
    private final EvictingMap<Integer, Long> keepAliveUpdates = new EvictingMap<>(20);

    private final Observable<Boolean> sprinting = new Observable<>(false);
    private final Observable<Boolean> cinematic = new Observable<>(false);
    private final Observable<Integer> joined = new Observable<>(0);
    private final Observable<Entity> target = new Observable<>(null);
    private final Observable<Long> keepAlivePing = new Observable<>(0L);
    private final Observable<Long> transactionPing = new Observable<>(0L);
    private final Observable<Integer> ticks = new Observable<>(0);
    private final Observable<Double> cps = new Observable<>(0.0);
    private final Observable<Double> rate = new Observable<>(0.0);
    private Observable<BoundingBox> boundingBox = new Observable<>(new BoundingBox(0, 0, 0, null));

    private final Observable<RotationUpdate> rotationUpdate = new Observable<>(new RotationUpdate(0, 0));
    private final Observable<PositionUpdate> positionUpdate = new Observable<>(new PositionUpdate(null, null, false));

    private final RotationManager rotationManager = new RotationManager(this);
    private final CheckManager checkManager = new CheckManager(this);
    private final ExemptManager exemptManager = new ExemptManager(this);
    private final PositionManager positionManager = new PositionManager(this);
    private final ActionManager actionManager = new ActionManager(this);
    private final ConnectionManager connectionManager = new ConnectionManager(this);
    private final BlockManager blockManager = new BlockManager(this);
    private final VelocityManager velocityManager = new VelocityManager();

    public EvictingList<Pair<AxisAlignedBB, Integer>> getTargetLocations() {
        return targetLocations;
    }

    public PlayerData(final Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
        this.connection = NmsUtil.getPlayerConnection(bukkitPlayer);

        target.observe((from, to) -> {
            if(from == null || from.getEntityId() != to.getEntityId()) {
                getTargetLocations().clear();
            }
        });
    }

    public Player getPlayer() {
        return this.bukkitPlayer;
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    public ExemptManager getExemptManager() {
        return exemptManager;
    }

    public ActionManager getActionManager() { return actionManager; }

    public Observable<Double> getCps() {
        return cps;
    }

    public Observable<Integer> getTicks() {
        return ticks;
    }

    public Observable<Integer> getJoined() {
        return joined;
    }

    public Observable<PositionUpdate> getPositionUpdate() {
        return positionUpdate;
    }

    public VelocityManager getVelocityManager() {
        return velocityManager;
    }

    public PositionManager getPositionManager() {
        return positionManager;
    }

    public Observable<BoundingBox> getBoundingBox() {
        return boundingBox;
    }

    public Observable<Double> getRate() {
        return rate;
    }

    public Observable<Entity> getTarget() {
        return target;
    }

    public EvictingList<BoundingBox> getBoundingBoxes() {
        return boundingBoxes;
    }

    public CheckManager getCheckManager() {
        return checkManager;
    }

    public PlayerConnection getConnection() {
        return connection;
    }

    public EvictingMap<Short, Long> getTransactionUpdates() {
        return transactionUpdates;
    }

    public EvictingMap<Integer, Long> getKeepAliveUpdates() {
        return keepAliveUpdates;
    }

    public Observable<Long> getTransactionPing() {
        return transactionPing;
    }

    public Observable<Long> getKeepAlivePing() {
        return keepAlivePing;
    }

    public Observable<Boolean> getCinematic() {
        return cinematic;
    }

    public Observable<Boolean> getSprinting() {
        return sprinting;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public RotationManager getRotationManager() {
        return rotationManager;
    }
}
