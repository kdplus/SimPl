package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar tv = new TypeVar(true);
        TypeResult tr = e.typecheck(TypeEnv.of(E, x, tv));
        // tr.t need apply?
        Substitution s = tr.s.compose(tr.t.unify(tr.s.apply(tv)));
        return TypeResult.of(tr.s, s.apply(tr.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RecValue v = new RecValue(s.E, x, e);
        Env E = new Env(s.E, x, v);
        Value hey = e.eval(State.of(E, s.M, s.p));
        return  hey;
    }
}
