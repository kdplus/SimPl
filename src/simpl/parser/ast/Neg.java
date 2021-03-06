package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr = e.typecheck(E);// no need apply
        Substitution s = tr.t.unify(Type.INT).compose(tr.s);
        return TypeResult.of(s, Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        int v = -((IntValue)e.eval(s)).n;
        return new IntValue(v);
    }
}
