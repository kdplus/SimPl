package simpl.parser.ast;

import com.sun.org.apache.regexp.internal.RE;
import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = s.E.get(x);
        if (v instanceof  RecValue) {
            RecValue nv = (RecValue)v;
            Rec rec = new Rec(nv.x, nv.e);
            return rec.eval(State.of(nv.E, s.M, s.p));
        }
        return s.E.get(x);
    }
}
