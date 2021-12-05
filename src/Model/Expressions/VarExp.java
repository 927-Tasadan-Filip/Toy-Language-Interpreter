package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class VarExp implements Exp{
    private String id;

    public VarExp() {
    }

    public VarExp(String id) {
        this.id = id;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> hp) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}