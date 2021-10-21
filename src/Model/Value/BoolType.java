package Model.Value;

import Model.Type.BoolType;

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

    public BoolType getType() {
        return new BoolType();
    }
}
