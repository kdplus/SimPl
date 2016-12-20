package simpl.interpreter.lib;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.*;

public class defmacro extends DefineValue {

    public defmacro() {
        // TODO
        super(Env.empty, Symbol.symbol("x"), Symbol.symbol("y"), new Expr() {
            @Override
            public TypeResult typecheck(TypeEnv E) throws TypeError {
                return TypeResult.of(Type.UNIT);
            }

            @Override
            public Value eval(State s) throws RuntimeError {
                return Value.UNIT;
            }
        });
    }
}

