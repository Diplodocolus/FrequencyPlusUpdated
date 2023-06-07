package xyz.elevated.frequency.check.impl.speed;

import xyz.elevated.frequency.FrequencyPlugin;
import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.PositionCheck;
import xyz.elevated.frequency.check.type.RotationCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.update.PositionUpdate;

@CheckData(name = "Speed (B)")
public class SpeedB extends PositionCheck {
    private int buffer = 0;

    public SpeedB(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(final PositionUpdate positionUpdate) {
        if (!FrequencyPlugin.getFrequencyConfig().getBoolean("checks.speed.enable")) {
            return;
        }

    }
}
