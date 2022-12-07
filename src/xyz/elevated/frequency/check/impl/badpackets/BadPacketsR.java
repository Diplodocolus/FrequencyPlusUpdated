package xyz.elevated.frequency.check.impl.badpackets;

import lombok.val;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.PacketCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.wrapper.impl.client.WrappedPlayInArmAnimation;
import xyz.elevated.frequency.wrapper.impl.client.WrappedPlayInUseEntity;
import xyz.elevated.frequency.wrapper.impl.server.WrappedPlayOutPosition;

@CheckData(name="BadPackets (R)")
public class BadPacketsR extends PacketCheck {
    private boolean animationExcept = false;
    public BadPacketsR(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInArmAnimation) {
            animationExcept = false;
        } else if (object instanceof WrappedPlayInUseEntity) {
            WrappedPlayInUseEntity packet = (WrappedPlayInUseEntity) object;
            if (animationExcept) {
                animationExcept = false;
                fail("tried to noswing");
            }

            if (packet.getAction() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
                animationExcept = true;
            }
        }
    }
}
