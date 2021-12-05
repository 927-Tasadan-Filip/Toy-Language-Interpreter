package Model.Statements;

import Model.DataStructures.*;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.io.BufferedReader;

public class ForkStmt implements IStmt{
    private IStmt stmt;

    public IStmt getStmt() {
        return stmt;
    }

    public void setStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    public ForkStmt(IStmt stmt) {
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
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typeCheck(typeEnv);
        return typeEnv;
    }
}
