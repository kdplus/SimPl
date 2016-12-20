package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.parser.ast.Fn;

public class DefineValue extends UnitValue {

    public final Env E;
    public Symbol x;
    public final Symbol y;
    public final Expr e;
    public int getName;
    public int getValue;
    public FunValue v;

    public DefineValue(Env E, Symbol x, Symbol y, Expr e) {
        this.E = E;
        this.x = x;
        this.y = y;
        this.getName = 0;
        this.getValue = 0;
        this.e = e;
    }
    public DefineValue(Env E, Symbol x, FunValue v) {
        this.E = E;
        this.x = x;
        this.y = null;
        this.getName = 1;
        this.getValue = 1;
        this.e = null;
        this.v = v;
    }
    public String toString() {
        return "Def " + x.toString() + " ";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        // Maybe it should not equals
        if (other == null) return false;
        return false;
    }
}

