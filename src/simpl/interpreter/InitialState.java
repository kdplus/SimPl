package simpl.interpreter;

import static simpl.parser.Symbol.symbol;

import java_cup.symbol;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(Env.empty), new Mem(), new Int(0));
    }

    private static Env initialEnv(Env E) {
        // TODO
        // don't understand here...
        Env newE = new Env(E, Symbol.symbol("fst"), new fst());
        newE = new Env(newE, Symbol.symbol("snd"), new snd());
        newE = new Env(newE, Symbol.symbol("hd"), new hd());
        newE = new Env(newE, Symbol.symbol("tl"), new tl());
        newE = new Env(newE, Symbol.symbol("iszero"), new iszero());
        newE = new Env(newE, Symbol.symbol("pred"), new pred());
        newE = new Env(newE, Symbol.symbol("succ"), new succ());
        return newE;
    }
}
