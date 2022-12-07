package xyz.elevated.frequency.check.type;

import xyz.elevated.frequency.check.Check;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.update.BlockUpdate;

public class BlockCheck extends Check<BlockUpdate> {

    public BlockCheck(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(BlockUpdate object) {

    }
}
