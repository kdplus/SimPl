package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e.typecheck(E);
        RefType reft = new RefType(new TypeVar(true));
        Substitution s = t1.t.unify(reft).compose(t1.s);
        return TypeResult.of(s, s.apply(reft.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue ref = (RefValue) e.eval(s);
        Value v = s.M.get(ref.p);
        return v;
    }
}
