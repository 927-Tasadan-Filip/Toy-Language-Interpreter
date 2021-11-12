package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.DataStructures.MyIStack;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class WhileStmt implements IStmt{
    private Exp expression;
    private IStmt stmt;

    public WhileStmt(Exp expression, IStmt stmt) {
        this.expression = expression;
        this.stmt = stmt;
    }

    public Exp getExpression() {
        return expression;
    }

    public void setExpression(Exp expression) {
        this.expression = expression;
    }

    public IStmt getStmt() {
        return stmt;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> hp = state.getHeap();

        Value val_exp = expression.eval(symTbl, hp);
        if(val_exp.getType().equals(new BoolType())) {
            BoolValue bool_val_exp = (BoolValue)val_exp;
            if(bool_val_exp.getVal()) {
                exeStack.push(this);
                exeStack.push(stmt);
            }
        } else {
            throw  new MyException("While conditional expression is not a boolean");
        }

        return state;
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") " + stmt.toString();
    }
}
