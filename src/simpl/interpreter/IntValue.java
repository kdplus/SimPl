package simpl.interpreter;

public class IntValue extends Value {

    public final int n;

    public IntValue(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if (other == null) return false;
        if (other instanceof IntValue){
            //BoolValue a = (BoolValue)other;
            if (((IntValue)other).n == n) return true;
        }
        return false;
    }
}
