package xyz.elevated.frequency.check.impl.badpackets;

import net.minecraft.server.v1_8_R3.Packet;
import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.PacketCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.wrapper.impl.client.WrappedPlayInSteerVehicle;
import xyz.elevated.frequency.wrapper.impl.client.WrappedPlayInTransaction;

import java.util.Arrays;
import java.util.List;

@CheckData(name = "BadPackets (Q)")
public class BadPacketsQ extends PacketCheck {
    public BadPacketsQ(PlayerData playerData) {
        super(playerData);
    }

    private static final List<Float> validTurns = Arrays.asList(
            0.29400003f,
            0.98f,
            0f
    );

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInSteerVehicle) {
            WrappedPlayInSteerVehicle vehicle = new WrappedPlayInSteerVehicle((Packet<?>) object);

            float forward = Math.abs(vehicle.getForward()), side = Math.abs(vehicle.getSide());

            if (!validTurns.contains(forward) || !validTurns.contains(side)) {
                fail();
            }
        }
    }

}
