package xyz.elevated.frequency.check.impl.scaffold;

import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.BlockCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.update.BlockUpdate;

@CheckData(name="Scaffold (B)")
public class ScaffoldB extends BlockCheck {
    public ScaffoldB(final PlayerData data) {
        super(data);
    }

    @Override
    public void process(BlockUpdate update) {
        final boolean swinging = playerData.getActionManager().getSwinging().get();

        if (!swinging) {
            fail("tried to noswing");
        }
    }
}
