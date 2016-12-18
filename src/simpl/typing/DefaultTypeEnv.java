package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        E = TypeEnv.empty;

        TypeVar tv1 = new TypeVar(true);
        TypeVar tv2 = new TypeVar(true);
        PairType pair = new PairType(tv1, tv2);

        TypeVar tv3 = new TypeVar(true);
        ListType list = new ListType(tv3);

        E = TypeEnv.of(E, Symbol.symbol("fst"), new ArrowType(pair,tv1));
        E = TypeEnv.of(E, Symbol.symbol("snd"), new ArrowType(pair,tv2));
        E = TypeEnv.of(E, Symbol.symbol("hd"), new ArrowType(list,tv3));
        E = TypeEnv.of(E, Symbol.symbol("tl"), new ArrowType(list,list));
        E = TypeEnv.of(E, Symbol.symbol("iszero"), new ArrowType(Type.INT,Type.BOOL));
        E = TypeEnv.of(E, Symbol.symbol("pred"), new ArrowType(Type.INT,Type.INT));
        E = TypeEnv.of(E, Symbol.symbol("succ"), new ArrowType(Type.INT,Type.INT));
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
