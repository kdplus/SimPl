package simpl.interpreter;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "pair@" + v1 + "@" + v2;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other == null) return false;
        if (!(other instanceof PairValue)) return false;
        if (!((PairValue)other).v1.equals(v1)) return false;
        if (!((PairValue)other).v2.equals(v2)) return false;
        return true;
    }
}
