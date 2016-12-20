package simpl.parser.ast;

import jdk.nashorn.internal.ir.WhileNode;
import simpl.interpreter.*;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

import java.util.EventListener;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr = e.typecheck(E);
        // do not need apply ...?
        return TypeResult.of(tr.s, new RefType(tr.s.apply(tr.t)));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Int p = new Int(s.p.get());

        if (p.get() > 1) {
            System.out.println("GC start!!!!");
            Env E = s.E;
            while (E != Env.empty) {
                while (E.v.getClass().toString().intern() == "class simpl.interpreter.RefValue" && E.v.mark == 0){
                    E.v.mark = 1;
                    //if (E.v instanceof RefValue)
                      //  E.v = s.M.get(((RefValue) E.v).p);
                }
                E.v.mark = 1;
                E = E.getE();
            }
            for (int i = 0; i < p.get(); ++i) {
                if (s.M.get(i).mark == 0) {
                    System.out.println("Collect");
                    System.out.println(i);
                    s.M.put(i, null);
                }
            }

        }


        int prev_p = s.p.get();
        s.p.set(prev_p + 1);
        Value v = e.eval(s);
        s.M.put(prev_p, v);
        return new RefValue(prev_p);
    }
}
