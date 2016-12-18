package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e1.typecheck(E);
        TypeResult t2 = e2.typecheck(t1.s.compose(E));
        Substitution s = t2.s.compose(t1.s);
        s = s.apply(t1.t).unify(Type.BOOL).compose(s);
        return TypeResult.of(s, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = e1.eval(s);
        if (v1.equals(new BoolValue(true))) {
            Seq seq = new Seq(e2, this);
            return seq.eval(s);
        }
        return Value.UNIT;
    }
}
