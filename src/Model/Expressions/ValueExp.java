package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;
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

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> hp) throws MyException {
        return e;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString() {
        return e.toString();
    }
}