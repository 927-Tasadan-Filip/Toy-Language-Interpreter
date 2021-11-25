package Model.Values;
import Model.Types.RefType;
import Model.Types.Type;

import java.sql.Ref;

public class RefValue implements Value{
    private int addr;
    private Type locationType;

    public RefValue(int addr, Type locationType) {
        this.addr = addr;
        this.locationType = locationType;
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public Type getLocationType() {return this.locationType;}

    public Type getType() {
        return new RefType(locationType);
    }

    public void setType(Type locationType) {
        this.locationType = locationType;
    }

    public boolean equals(Object another)  {
        if(another instanceof RefValue) {
            if(((RefValue) another).getAddr() == addr) {
                if(((RefValue) another).getLocationType().equals(locationType)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.addr + ", " + locationType.toString() + ")";
    }

    @Override
    public Value deepCopy() {
        return new RefValue(addr, locationType);
    }
}
