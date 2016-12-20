package simpl.parser.ast;

import simpl.interpreter.*;
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
        TypeResult lt = l.typecheck(E);
        TypeResult rt = r.typecheck(lt.s.compose(E));

        TypeVar t1 = new TypeVar(true);
        Type t2 = rt.t;
        ArrowType at = new ArrowType(t2, t1);
        Substitution s = rt.s.compose(lt.s);
        s = s.apply(lt.t).unify(at).compose(s);
        return TypeResult.of(s, s.apply(t1));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value f = l.eval(s);
        if (f instanceof DefineValue) {
            if (((DefineValue) f).getName == 0 && r instanceof Name){
                ((DefineValue) f).x = Symbol.symbol(r.toString());
                ((DefineValue) f).getName = 1;
                return f;
            }
            if (((DefineValue) f).getName == 1 && ((DefineValue) f).getValue == 0) {
                Value v = r.eval(s);
                ((DefineValue) f).v = (FunValue) v;
                ((DefineValue) f).getValue = 1;
                return f;
            }
            Env E = new Env(((DefineValue) f).E, ((DefineValue) f).v.x, r);
            if (((DefineValue) f).E.get_expr(Symbol.symbol("a")) == null) {
                E = E.compose(s.E);
            }
            s = State.of(E, s.M, s.p);
            if (((DefineValue) f).v.e instanceof Cond) {
                Expr cond = new Cond(((DefineValue) f).E.get_expr(Symbol.symbol("a")), s.E.get_expr(Symbol.symbol("b")), s.E.get_expr(Symbol.symbol("c")));
                Value v = cond.eval(s);
                return v;
            }
            FunValue newf =  new FunValue(E, ((Fn)((DefineValue) f).v.e).x, ((Fn)((DefineValue) f).v.e).e);
            return new DefineValue(E, ((Fn)((DefineValue) f).v.e).x, newf);
        }
        Value v = r.eval(s);
        Env E = new Env(((FunValue) f).E, ((FunValue) f).x, v);
        State s1 = State.of(E, s.M, s.p);
        return ((FunValue)f).e.eval(s1);
    }
}
