package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        int prev_p = s.p.get();
        s.p.set(prev_p + 1);
        Value v = e.eval(s);
        s.M.put(prev_p, v);
        return new RefValue(prev_p);
    }
}
