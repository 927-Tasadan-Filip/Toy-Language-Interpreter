package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class HeapAllocation implements IStmt{
    private String var_name;
    private Exp expression;

    public HeapAllocation(String var_name, Exp expression) {
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
    public String toString() {
        return "new(" + var_name + ", " + expression.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> hp = state.getHeap();
        if(symTbl.isDefined(var_name)) {
            if (symTbl.lookup(var_name).getType() instanceof RefType) {
                Value val_exp = expression.eval(symTbl, hp);
                Type type_var_name = ((RefValue)symTbl.lookup(var_name)).getLocationType();
                if (val_exp.getType().equals(type_var_name)) {
                    Integer assoc_key;
                    assoc_key = hp.update(val_exp);
                    RefValue new_ref_val = new RefValue(assoc_key, val_exp.getType());
                    symTbl.update(var_name, new_ref_val);
                } else {
                    throw new MyException("The var_name \"" + var_name + "\" type and the given expression type are not the same");
                }
            } else {
                throw new MyException("The var_name \"" + var_name + "\" type is not a RefType");
            }
        } else {
            throw new MyException("The var_name \"" + var_name + "\" is not defined in the symbol table");
        }

        return state;
    }
}
