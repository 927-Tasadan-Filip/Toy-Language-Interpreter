package Model.Values;


import Model.Types.BoolType;
import Model.Types.Type;
import UserDefinedExceptions.MyException;

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

    public boolean equals(Object another)  {
        if(another instanceof BoolValue) {
            if(((BoolValue) another).getVal() == this.getVal()) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return Boolean.toString(val);
    }

    public Type getType() {
        return new BoolType();
    }
}
