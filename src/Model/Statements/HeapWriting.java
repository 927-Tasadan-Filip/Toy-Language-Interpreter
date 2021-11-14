package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class HeapWriting implements IStmt{
    private String var_name;
    private Exp expression;

    public HeapWriting(String var_name, Exp expression) {
        this.var_name = var_name;
        this.expression = expression;
    }

    public String getVarName() {
        return var_name;
    }

    public void setVarName(String var_name) {
        this.var_name = var_name;
    }

    public Exp getExpression() {
        return expression;
    }

    public void setExpression(Exp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> hp = state.getHeap();

        if(symTbl.isDefined(var_name)) {
            Value val_var_name = symTbl.lookup(var_name);
            if(val_var_name.getType() instanceof RefType) {
                RefValue ref_val_var_name = (RefValue)val_var_name;
                if(hp.isDefined(ref_val_var_name.getAddr())) {
                    Value val_exp = expression.eval(symTbl, hp);
                    if(val_exp.getType().equals(ref_val_var_name.getLocationType())) {
                        hp.update(ref_val_var_name.getAddr(), val_exp);
                    } else {
                        throw new MyException("The var_name \"" + var_name + "\" type and the given expression type are not the same");
                    }
                } else {
                    throw new MyException("The var_name  \"" + var_name + "\" address is not a key in the Heap");
                }
            } else {
                throw new MyException("The var_name \"" + var_name + "\" type is not a RefType");
            }
        } else {
            throw new MyException("The var_name \"" + var_name + "\" is not defined in the symbol table");
        }

        return state;
    }

    @Override
    public String toString() {
        return "wH(" + var_name + ", " + expression.toString() + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new HeapWriting(this.var_name, this.expression);
    }
}
