package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> hp) throws MyException;
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}