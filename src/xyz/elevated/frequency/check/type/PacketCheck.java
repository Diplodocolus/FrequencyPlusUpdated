package xyz.elevated.frequency.check.type;

import xyz.elevated.frequency.check.Check;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.data.impl.CheckManager;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PacketCheck extends Check<Object> {

    public PacketCheck(final PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(final Object object) {
        playerData.getCheckManager().getChecks()
                .stream()
                .filter(PostCheck.class::isInstance)
                .forEach(check -> check.process(object));
    }
}
