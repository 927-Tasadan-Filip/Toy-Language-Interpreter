package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.util.HashMap;

public class LogicExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;
    private HashMap<String, Integer> op_map = new HashMap<String, Integer>();

    public Exp getE1() {
        return e1;
    }

    public void setE1(Exp e1) {
        this.e1 = e1;
    }

    public Exp getE2() {
        return e2;
    }

    public void setE2(Exp e2) {
        this.e2 = e2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    private void initialiseOpMap() {
        op_map.put("and",1);
        op_map.put("or",2);
    }

    public LogicExp() {
        initialiseOpMap();
    }

    public LogicExp(Exp e1, Exp e2, String op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
        initialiseOpMap();
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value val1 = e1.eval(tbl, hp);
        if(val1.getType().equals(new BoolType())) {
            Value val2 = e2.eval(tbl, hp);
            if(val2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) val1;
                BoolValue bool2 = (BoolValue) val2;
                boolean n1 = bool1.getVal();
                boolean n2 = bool2.getVal();
                switch (op_map.get(op)) {
                    case 1:
                        return new BoolValue((n1 && n2));
                    case 2:
                        return new BoolValue((n1 || n2));
                    default:
                        throw new MyException("invalid boolean operator");
                }
            }
            else throw new MyException("Operand2 is not a boolean");
        }
        else throw new MyException("Operand1 is not a boolean");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
