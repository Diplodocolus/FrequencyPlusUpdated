package xyz.elevated.frequency.check.impl.fly;

import lombok.Builder;
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
        if (object instanceof WrappedPlayInFlying && !playerData.getBukkitPlayer().isFlying()) {
            fail();
        }
    }
}
