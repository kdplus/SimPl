package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class FunValue extends Value {

    public final Env E;
    public final Symbol x;
    public final Expr e;

    public FunValue(Env E, Symbol x, Expr e) {
        this.E = E;
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "fun";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        // Maybe it should not equals
        if (other == null) return false;
        if (!(other instanceof FunValue)) return false;
        if (((FunValue)other).e != e) return false;
        if (((FunValue)other).x != x) return false;
        if (((FunValue)other).E != E) return false;
        return true;
    }
}
