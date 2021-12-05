package Model.Expressions;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class HeapReading implements Exp{
    private Exp expression;

    public HeapReading(Exp expression) {
        this.expression = expression;
    }

    public Exp getExpression() {
        return expression;
    }

    public void setExpression(Exp expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> hp) throws MyException {
        Value val_exp = expression.eval(tbl, hp);
        if(val_exp.getType() instanceof RefType) {
            RefValue ref_val_exp = (RefValue)val_exp;
            Integer ref_addr = ref_val_exp.getAddr();
            if(hp.isDefined(ref_addr)) {
                return hp.lookup(ref_addr);
            } else throw new MyException("The address " + ref_addr + " is not defined in symbolTable");
        } else throw new MyException("The expression type is not RefType");

    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typeCheck(typeEnv);
        if(type instanceof RefType) {
            RefType ref_type = (RefType) type;
            return ref_type.getInner();
        } else throw new MyException("The expression type is not RefType");
    }

    @Override
    public String toString() {
        return "rH(" + expression.toString() + ")";
    }
}
