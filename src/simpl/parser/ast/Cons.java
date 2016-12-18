package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l.toString() + " :: " + r.toString() + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lt = l.typecheck(E);
        TypeResult rt = r.typecheck(lt.s.compose(E));

        Substitution s = rt.s.compose(lt.s);
        ListType listt = new ListType(s.apply(lt.t));
        s = rt.t.unify(listt).compose(s);
        // maybe prob
        return TypeResult.of(s, s.apply(listt));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new ConsValue(v1, v2);
    }
}
