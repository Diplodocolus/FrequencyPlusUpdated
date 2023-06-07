package xyz.elevated.frequency.check.impl.scaffold;

import org.bukkit.event.inventory.InventoryType;
import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.PacketCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.wrapper.impl.client.WrappedPlayInBlockPlace;

@CheckData(name="Scaffold (D)")
public class ScaffoldD extends PacketCheck {
    public ScaffoldD(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInBlockPlace) {
            if (playerData.getBukkitPlayer().getOpenInventory().getType() == InventoryType.PLAYER) {
                fail("tried to placing blocks in inventory");
            }
        }
    }
}
