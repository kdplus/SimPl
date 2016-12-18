package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lt = l.typecheck(E);
        TypeResult rt = r.typecheck(lt.s.compose(E));
        Substitution s = rt.s.compose(lt.s);
        s = s.apply(lt.t).unify(Type.INT).compose(s);
        s = s.apply(rt.t).unify(Type.INT).compose(s);
        return TypeResult.of(s, Type.BOOL);
    }
}
