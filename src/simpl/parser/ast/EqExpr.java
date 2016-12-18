package simpl.parser.ast;

import simpl.typing.*;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lt = l.typecheck(E);
        TypeResult rt = r.typecheck(lt.s.compose(E));
        if (lt.t.isEqualityType() && rt.t.isEqualityType()) {
            Substitution s = lt.s.compose(rt.s);
            s = s.apply(lt.t).unify(s.apply(rt.t)).compose(s);
            return TypeResult.of(s, Type.BOOL);
        }
        throw new TypeMismatchError();
    }
}
