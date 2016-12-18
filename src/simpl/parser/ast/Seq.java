package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
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
        l.eval(s);
        Value v2 = r.eval(s);
        return v2;
    }
}
