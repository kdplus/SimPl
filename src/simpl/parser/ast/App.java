package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {

        return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value f = l.eval(s);
        Value v = r.eval(s);
        Env E = new Env(s.E, ((FunValue)f).x, v);
        State s1 = s.of(E, s.M, s.p);
        return ((FunValue)f).e.eval(s1);
    }
}