package Model.Statements;

import Model.DataStructures.*;
import Model.ProgramState.PrgState;
import Model.Values.StringValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.io.BufferedReader;

public class forkStmt implements IStmt{
    private IStmt stmt;

    public IStmt getStmt() {
        return stmt;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public forkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> newExeStack = new MyStack<>();
        MyIDictionary<String, Value> newSymTable = state.cloneSymTable();
        MyIList<Value> newOut = state.getOut();
        MyIDictionary<StringValue, BufferedReader> newFileTable = state.getFileTable();
        MyIHeap<Value> newHeap= state.getHeap();

        return new PrgState(newExeStack, newSymTable, newOut, newFileTable, newHeap, stmt);
    }

    @Override
    public IStmt deepCopy() {
        return new forkStmt(stmt.deepCopy());
    }
}
