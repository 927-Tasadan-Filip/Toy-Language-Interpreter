package Model.Statements;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.DataStructures.MyIStack;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class IfStmt implements IStmt{
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt() {};

    public IfStmt(Exp e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}

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

    public String toString() {
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> hp = state.getHeap();
        Value cond = exp.eval(symTbl, hp);
        if(cond.getType().equals(new BoolType())) {
            BoolValue v1 = (BoolValue) cond;
            if(v1.getVal()) {
                stk.push(thenS);
            }
            else {
                stk.push(elseS);
            }
        }
        else {
            throw new MyException("If conditional expression is not a boolean");
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(this.exp, this.thenS.deepCopy(), this.elseS.deepCopy());
    }
}
