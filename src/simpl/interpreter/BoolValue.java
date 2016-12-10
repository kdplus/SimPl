package simpl.interpreter;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other == null) return false;
        if (other instanceof BoolValue){
            //BoolValue a = (BoolValue)other;
            if (((BoolValue)other).b == b) return true;
        }
        return false;
    }
}
