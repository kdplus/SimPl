package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Not extends UnaryExpr {

    public Not(Expr e) {
        super(e);
    }

    public String toString() {
        return "(not " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr = e.typecheck(E);
        Substitution s = tr.t.unify(Type.BOOL).compose(tr.s);
        return TypeResult.of(s, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = e.eval(s);
        if (v.equals(new IntValue(0))) return new BoolValue(true);
        return new BoolValue(false);
    }
}
