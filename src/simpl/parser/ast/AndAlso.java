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

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeError e = new TypeError("AndAlso only can handle BoolType!");
        TypeResult lt = l.typecheck(E);
        TypeResult rt = r.typecheck(E);
        if (lt.t == Type.BOOL && rt.t == Type.BOOL) return TypeResult.of(lt.s.compose(rt.s), Type.BOOL);
        else throw e;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue t = new BoolValue(true);
        BoolValue f = new BoolValue(false);
        if (l.eval(s).equals(t)) return r.eval(s);
        return f;
    }
}
