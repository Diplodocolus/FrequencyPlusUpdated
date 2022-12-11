package xyz.elevated.frequency.util;

import java.util.Collection;

public final class Pair<X, Y> {
    private X x;
    private Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }
    public Pair() {
        this.x = null;
        this.y = null;
    }
    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }
}
