package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Let extends Expr {

    public Symbol x;
    public Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult t1 = e1.typecheck(E);
        TypeResult t2 = e2.typecheck(t1.s.compose(TypeEnv.of(E, x, t1.t)));
        Substitution s = t2.s.compose(t1.s);
        return TypeResult.of(s, s.apply(t2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        //System.out.println(s.E.toString());
        Value v1 = e1.eval(s);
        Env E = new Env(s.E, x, v1);
        Value v2 = e2.eval(s.of(E, s.M, s.p));
        return v2;
    }
}
