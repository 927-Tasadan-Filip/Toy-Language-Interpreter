package Test.Model.Expressions;

import Model.DataStructures.MyDictionary;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.DataStructures.MyStack;
import Model.Expressions.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class TestExpressions {
    public static void main(String[] args) {
        MyIDictionary<String, Value> dict = new MyDictionary<String, Value>();

        Value v1 = new IntValue(1);
        Exp ve1 = new ValueExp(v1);
        try {
            assert (((IntValue)(ve1.eval(dict))).getVal() == 1);
        }
        catch (MyException exc) {
            assert false;
        }
        Value v2 = new IntValue(2);
        Exp ve2 = new ValueExp(v2);
        Exp ar3 = new ArithExp(ve1, ve2, '+');
        try {
            assert ((((IntValue)(ar3.eval(dict))).getVal()) == 3);
        }
        catch (MyException exc) {
            assert false;
        }
        Value v3 = new BoolValue(true);
        Value v4 = new BoolValue(false);
        Exp vb3 = new ValueExp(v3);
        Exp vb4 = new ValueExp(v4);

        Exp eb5 = new LogicExp(vb3, vb4, "and");
        try {
            assert (!(((BoolValue) (eb5.eval(dict))).getVal()));
        }
        catch (MyException exc) {
            assert false;
        }

        Exp eb6 = new LogicExp(vb3, vb4, "or");
        try {
            assert (((BoolValue)(eb6.eval(dict))).getVal());
        }
        catch (MyException exc) {
            assert false;
        }

        dict.update("a", new IntValue(12));
        dict.update("b", new IntValue(25));
        Exp ev7 = new VarExp("a");
        try {
            assert (((IntValue)(ev7.eval(dict))).getVal() == 12);
        }
        catch (MyException exc) {
            assert false;
        }

        Exp ev8 = new VarExp("b");
        try {
            assert (((IntValue)(ev8.eval(dict))).getVal() == 25);
        }
        catch (MyException exc) {
            assert false;
        }

        Exp ev9 = new VarExp("c");
        try {
            ev9.eval(dict);
            assert false;
        }
        catch (MyException exc) {
            assert true;
        }



    }
}
