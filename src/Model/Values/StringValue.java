package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value{
    private String val;

    public StringValue() {}

    public StringValue(String val) {
        this.val = val;
    }
    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public boolean equals(Object another)  {
        if(another instanceof StringValue) {
            if(((StringValue) another).getVal().equals(this.getVal())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "\"" +this.val + "\"";
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
