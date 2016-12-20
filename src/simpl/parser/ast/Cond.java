package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e1.typecheck(E);
        TypeResult t2 = e2.typecheck(t1.s.compose(E));
        TypeResult t3 = e3.typecheck(t2.s.compose(t1.s.compose(E)));
        Substitution s = t3.s.compose(t2.s.compose(t1.s));

        s = s.apply(t1.t).unify(Type.BOOL).compose(s);
        s = s.apply(t2.t).unify(s.apply(t3.t)).compose(s);
        return TypeResult.of(s, s.apply(t2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO

        BoolValue v = (BoolValue) e1.eval(s);
        if (v.equals(new BoolValue(true))) return e2.eval(s);
        return e3.eval(s);
    }
}
