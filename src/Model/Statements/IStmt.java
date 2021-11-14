package Model.Statements;
import Model.ProgramState.PrgState;
import UserDefinedExceptions.MyException;

public interface IStmt{
    PrgState execute(PrgState state) throws MyException;
    IStmt deepCopy();
//which is the execution method for a statement.
}