package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl) throws MyException;
}