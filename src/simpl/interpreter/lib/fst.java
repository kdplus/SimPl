package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.ArrowType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class fst extends FunValue {

    public fst() {
        // TODO
        super(Env.empty, Symbol.symbol("x"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return TypeResult.of(((ArrowType) (E.get(Symbol.symbol("fst")))).t2);
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                return ((PairValue) s.E.get(Symbol.symbol("x"))).v1;
            }
        });
    }
}
