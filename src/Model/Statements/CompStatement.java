package Model.Statements;

import Model.DataStructures.MyIStack;
import Model.ProgramState.PrgState;
import UserDefinedExceptions.MyException;

public class CompStatement implements IStmt {
    private IStmt first;
    private IStmt snd;

    public IStmt getFirst() {
        return first;
    }

    public void setFirst(IStmt first) {
        this.first = first;
    }

    public IStmt getSnd() {
        return snd;
    }

    public void setSnd(IStmt snd) {
        this.snd = snd;
    }

    public CompStatement() {}

    public CompStatement(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    public String toString() {
//        return first.toString() + ";\n" + snd.toString();
        return "(" + first.toString() + ", " + snd.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStatement(this.first.deepCopy(), this.snd.deepCopy());
    }
}

