package xyz.elevated.frequency.check.impl.fly;

import xyz.elevated.frequency.FrequencyPlugin;
import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.PacketCheck;
import xyz.elevated.frequency.check.type.PositionCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.update.PositionUpdate;
import xyz.elevated.frequency.wrapper.impl.client.WrappedPlayInFlying;

@CheckData(name="Fly (F)")
public class FlyF extends PacketCheck {
    public FlyF(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (!FrequencyPlugin.getFrequencyConfig().getBoolean("checks.fly.enable") &&
                !FrequencyPlugin.getFrequencyConfig().getBoolean("checks.fly.modules.fakeground")) {
            return;
        }
        if (object instanceof WrappedPlayInFlying) {
            WrappedPlayInFlying packet = (WrappedPlayInFlying) object;

            if (!packet.onGround() && playerData.getActionManager().isFlying() && !playerData.getBukkitPlayer().isOnGround()) {
                fail("long time noground");
                lagback(FrequencyPlugin.getFrequencyConfig().getBoolean("checks.fly.lagback"));
            }
        }
    }
}
