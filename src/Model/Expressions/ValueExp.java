package Model.Expressions;

import Model.Value.Value;
import UserDefinedExceptions.MyException;

public class ValueExp implements Exp{
    private Value e;

    public Value getE() {
        return e;
    }

    public void setE(Value e) {
        this.e = e;
    }

    public ValueExp() {}

    public ValueExp(Value e) {
        this.e = e;
    }

    public Value eval(MyIDictionary<String,Value> tbl) throws MyException {
        return e;
    }

}