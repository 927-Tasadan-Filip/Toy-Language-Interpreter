package Model.Statements;
import Model.DataStructures.MyIDictionary;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import UserDefinedExceptions.MyException;

public interface IStmt{
    PrgState execute(PrgState state) throws MyException;
    IStmt deepCopy();
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}