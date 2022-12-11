package xyz.elevated.frequency.check.impl.scaffold;

import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.PacketCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.wrapper.impl.client.WrappedPlayInBlockPlace;

@CheckData(name="Scaffold (C)")
public class ScaffoldC extends PacketCheck {
    public ScaffoldC(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInBlockPlace) {
            if (!playerData.getActionManager().getSwinging().get()) {
                fail("tried to noswing");
            }
        }
    }
}
