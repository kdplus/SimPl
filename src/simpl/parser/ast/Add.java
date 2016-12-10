package simpl.parser.ast;

import simpl.interpreter.*;

public class Add extends ArithExpr {

    public Add(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " + " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        int v = ((IntValue)l.eval(s)).n + ((IntValue)r.eval(s)).n;
        return new IntValue(v);
    }
}
