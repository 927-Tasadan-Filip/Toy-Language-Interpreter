package Model.Statements;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.DataStructures.MyIList;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class PrintStmt implements IStmt{
    private Exp exp;

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public PrintStmt() {}

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    public String toString(){
        return "print(" +exp.toString()+")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out = state.getOut();
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        MyIHeap<Value> hp = state.getHeap();
        Value evaluated_exp = exp.eval(symTbl, hp);
        out.append(evaluated_exp);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(this.exp);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typeCheck(typeEnv);
        return typeEnv;
    }
}
