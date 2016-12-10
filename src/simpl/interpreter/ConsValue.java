package simpl.interpreter;

import simpl.parser.ast.Cons;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        // TODO
        return v1.toString() + '*' + v2.toString();
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other == null) return false;
        if (!(other instanceof ConsValue)) return false;
        if (!((ConsValue)other).v1.equals(v1)) return false;
        if (!((ConsValue)other).v2.equals(v2)) return false;
        return true;
    }
}
