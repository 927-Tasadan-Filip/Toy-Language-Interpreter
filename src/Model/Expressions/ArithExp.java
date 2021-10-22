package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.Types.IntType;
import Model.Values.IntValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.util.HashMap;

public class ArithExp implements Exp{
    private Exp e1;
    private Exp e2;
    private char op; //1-plus, 2-minus, 3-star, 4-divide
    private HashMap<Character, Integer> op_map = new HashMap<Character, Integer>();

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

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }

    private void initialiseOpMap() {
        op_map.put('+',1);
        op_map.put('-',2);
        op_map.put('*',3);
        op_map.put('/',4);
    }

    public ArithExp() {
        initialiseOpMap();
    }

    public ArithExp(char op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
        initialiseOpMap();
    }

    public Value eval(MyIDictionary<String,Value> tbl) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();

                switch (op_map.get(op)) {
                    case 1:
                        return new IntValue(n1+n2);
                    case 2:
                        return new IntValue(n1-n2);
                    case 3:
                        return new IntValue(n1*n2);
                    case 4:
                        if(n2==0) throw new MyException("division by zero");
                        else return new IntValue(n1/n2);
                    default:
                        throw new MyException("invalid arithmetic operator");
                }
            }
            else
                throw new MyException("second operand is not an integer");
        }
        else
            throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
