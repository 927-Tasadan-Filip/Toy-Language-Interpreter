package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class VarExp implements Exp{
    private String id;

    public VarExp() {
    }

    public VarExp(String id) {
        this.id = id;
    }

    public Value eval(MyIDictionary<String,Value> tbl) throws MyException {
        return tbl.lookup(id);
    }
}