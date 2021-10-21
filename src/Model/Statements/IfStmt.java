package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Values.BoolValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class IfStmt implements IStmt{
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public IStmt getThenS() {
        return thenS;
    }

    public void setThenS(IStmt thenS) {
        this.thenS = thenS;
    }

    public IStmt getElseS() {
        return elseS;
    }

    public void setElseS(IStmt elseS) {
        this.elseS = elseS;
    }

    public IfStmt() {};

    public IfStmt(Exp e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}

    public String toString() {
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        Value cond = exp.eval(symTbl);
        if(cond.getType().equals("bool")) {
            BoolValue v1 = (BoolValue) cond;
            if(v1.getVal()) {
                stk.push(thenS);
            }
            else {
                stk.push(elseS);
            }
        }
        else {
            throw new MyException("Conditional expression is not a boolean");
        }

        return state;
    }

}
