package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value {
    private int val;

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public IntValue() {}

    public IntValue(int v) {
        val = v;
    }

    public boolean equals(Object another)  {
        if(another instanceof IntValue) {
            if(((IntValue) another).getVal() == this.getVal()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return Integer.toString(val);
    }

    public Type getType() {
        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }
}
