package Model.Values;


import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value {
    private boolean val;

    public void setVal(boolean val) {
        this.val = val;
    }

    public boolean getVal() {
        return val;
    }

    public BoolValue() {}

    public BoolValue(boolean v) {
        val = v;
    }

    public String toString() {
        return Boolean.toString(val);
    }

    public Type getType() {
        return new BoolType();
    }
}
