package xyz.elevated.frequency.check.impl.badpackets;

import xyz.elevated.frequency.FrequencyPlugin;
import xyz.elevated.frequency.check.CheckData;
import xyz.elevated.frequency.check.type.PacketCheck;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.wrapper.impl.client.WrappedPlayInHeldItemSlot;

@CheckData(name = "BadPackets (S)")
public class BadPacketsS extends PacketCheck {
    private int lastSlot = -1;
    public BadPacketsS(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (!FrequencyPlugin.getFrequencyConfig().getBoolean("checks.badpackets.protocol.check_protocol1") &&
                !FrequencyPlugin.getFrequencyConfig().getBoolean("checks.badpackets.enable") &&
                !FrequencyPlugin.getFrequencyConfig().getBoolean("checks.badpackets.modules.switch")) {
            return;
        }
        if (object instanceof WrappedPlayInHeldItemSlot) {
            WrappedPlayInHeldItemSlot packet = (WrappedPlayInHeldItemSlot) object;

            int slot = packet.getSlot();

            if (slot == lastSlot) {
                fail("player switched fastest!");
                cancel(FrequencyPlugin.getFrequencyConfig().getBoolean("checks.badpackets.cancel"));
            }

            lastSlot = packet.getSlot();
        }
    }
}
