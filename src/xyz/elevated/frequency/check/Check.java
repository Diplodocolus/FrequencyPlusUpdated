package xyz.elevated.frequency.check;

import com.google.common.collect.Lists;
import xyz.elevated.frequency.alert.AlertManager;
import xyz.elevated.frequency.data.PlayerData;
import xyz.elevated.frequency.exempt.type.ExemptType;
import xyz.elevated.frequency.listener.CancelledListener;
import xyz.elevated.frequency.util.LogUtil;

import java.util.List;

public abstract class Check<T> {
    protected final PlayerData playerData;

    public PlayerData getPlayerData() {
        return playerData;
    }
    private String checkName;
    private int threshold;

    public String getCheckName() {
        return checkName;
    }

    public int getThreshold() {
        return threshold;
    }

    private final AlertManager alertManager = new AlertManager(this);
    private final List<Long> violations = Lists.newArrayList();

    public Check(final PlayerData playerData) {
        this.playerData = playerData;

        final Class<?> checkClass = this.getClass();

        if (checkClass.isAnnotationPresent(CheckData.class)) {
            final CheckData checkData = checkClass.getAnnotation(CheckData.class);

            this.checkName = checkData.name();
            this.threshold = checkData.threshold();
        } else {
            LogUtil.log("Check annotation not found in class: " + checkClass.getSimpleName());
        }
    }

    protected void fail() {
        fail("");
    }

    protected void fail(String fail) {
        alertManager.fail(fail);
    }

    protected boolean isExempt(final ExemptType exemptType) {
        return playerData.getExemptManager().isExempt(exemptType);
    }

    protected boolean isExempt(final ExemptType... exemptTypes) {
        return playerData.getExemptManager().isExempt(exemptTypes);
    }

    public abstract void process(final T object);

    public void lagback(boolean configAllowed) {
        CancelledListener.getListener().setLagback(configAllowed);
    }

    public void cancel(boolean configAllowed) {
        CancelledListener.getListener().setCancelled(configAllowed);
    }
}
