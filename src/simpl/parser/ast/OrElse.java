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

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lt = l.typecheck(E);
        TypeResult rt = r.typecheck(lt.s.compose(E));
        Substitution s = rt.s.compose(lt.s);
        s = s.apply(lt.t).unify(Type.BOOL).compose(s);
        s = s.apply(rt.t).unify(Type.BOOL).compose(s);
        return TypeResult.of(s, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue t = new BoolValue(true);
        BoolValue f = new BoolValue(false);
        if (l.eval(s).equals(f)) return r.eval(s);
        return t;
    }
}
