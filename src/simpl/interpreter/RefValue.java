package simpl.interpreter;

import simpl.parser.ast.Ref;

public class RefValue extends Value {

    public final int p;

    public RefValue(int p) {
        this.p = p;
    }

    public String toString() {
        return "ref@" + p;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other == null) return false;
        if (!(other instanceof RefValue)) return false;
        if (((RefValue)other).p != p) return false;
        return  true;
    }
}
