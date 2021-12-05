package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.util.HashMap;

public class RelationalExp implements Exp{
    private Exp e1;
    private Exp e2;
    private String op;
    private HashMap<String, Integer> op_map = new HashMap<>();

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
        op_map.put("<",1);
        op_map.put("<=",2);
        op_map.put("==",3);
        op_map.put("!=",4);
        op_map.put(">",5);
        op_map.put(">=",6);
    }

    public RelationalExp() {
        initialiseOpMap();
    }

    public RelationalExp(String op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
        initialiseOpMap();
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();

                switch (op_map.get(op)) {
                    case 1:
                        return new BoolValue(n1 < n2);
                    case 2:
                        return new BoolValue(n1 <= n2);
                    case 3:
                        return new BoolValue(n1 == n2);
                    case 4:
                        return new BoolValue(n1 != n2);
                    case 5:
                        return new BoolValue(n1 > n2);
                    case 6:
                        return new BoolValue(n1 >= n2);
                    default:
                        throw new MyException("invalid relational operator");
                }
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);

        if(type1.equals(new IntType())) {
            if(type2.equals(new IntType())) {
                return new BoolType();
            } else throw new MyException("second operand is not an integer");
        } else throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
