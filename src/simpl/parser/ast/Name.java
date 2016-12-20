package simpl.parser.ast;

import com.sun.org.apache.regexp.internal.RE;
import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.*;

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
        if (E.get(x) == null) {
            throw new TypeError("Cannot find var name\n");
        }
        return TypeResult.of(E.get(x));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = s.E.get(x);
        if (v == null && s.E.get_expr(x) != null) {
            v = s.E.get_expr(x).eval(s);
            s.E.get_expr_pos(x, v);
            return v;
        }
        if (v instanceof  RecValue) {
            RecValue nv = (RecValue)v;
            Rec rec = new Rec(nv.x, nv.e);
            return rec.eval(State.of(nv.E, s.M, s.p));
        }
        return s.E.get(x);
    }
}
