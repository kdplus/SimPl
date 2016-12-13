package simpl.parser.ast;

import simpl.interpreter.*;

public class Neq extends EqExpr {

    public Neq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " <> " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue b = new BoolValue((((IntValue)l.eval(s)).n != ((IntValue)r.eval(s)).n));
        return b;
    }
}
