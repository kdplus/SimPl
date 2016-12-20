package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class Env {

    private Env E;
    private final Symbol x;
    public Value v;
    public final Expr expr;

    private Env() {
        E = null;
        x = null;
        v = null;
        expr = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
        this.expr = null;
    }

    public Env(Env E, Symbol x, Expr expr) {
        this.E = E;
        this.x = x;
        this.v = null;
        this.expr = expr;
    }

    public Value get(Symbol y) {
        // TODO
        if (x == y) return v;
        if (E != null) return E.get(y);
        return null;
    }

    public Env getE(){
        return E;
    }
    public Expr get_expr(Symbol y) {
        // TODO
        if (x == y) return expr;
        if (E != null) return E.get_expr(y);
        return null;
    }

    public void get_expr_pos(Symbol y, Value v) {
        if (x == y) {
            this.v = v;
            return;
        }
        if (E != null) this.E.get_expr_pos(y, v);
    }

    public Env compose(Env E2) {
        // TODO
        if (E2 == null) return this.E;
        Env tE = new Env(this, E2.x, E2.v);
        return tE.compose(E2.E);
    }
}
