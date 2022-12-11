package xyz.elevated.frequency.update;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class RotationUpdate {
    private float deltaYaw, deltaPitch;

    public RotationUpdate(float deltaYaw, float deltaPitch) {
        this.deltaYaw = deltaYaw;
        this.deltaPitch = deltaPitch;
    }

    public float getDeltaYaw() {
        return deltaYaw;
    }
    public float getDeltaPitch() {
        return deltaPitch;
    }
}
