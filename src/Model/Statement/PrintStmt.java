package Model.Statement;

import UserDefinedExceptions.MyException;

public class PrintStmt {
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
        Exp evaluated_exp = exp.eval(symTbl);
        out.append(evaluated_exp);
        return state;
    }
}
