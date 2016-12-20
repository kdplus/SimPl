package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lt = l.typecheck(E);
        TypeResult rt = r.typecheck(lt.s.compose(E));
        Substitution s = lt.s.compose(rt.s);
        return TypeResult.of(s, s.apply(rt.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = l.eval(s);
        if (v instanceof DefineValue) {
            Env E = new Env(s.E, ((DefineValue) v).x, v);
            Value v2 = r.eval(State.of(E, s.M, s.p));
            return v2;
        }
        Value v2 = r.eval(s);
        return v2;
    }
}
