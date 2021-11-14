package Model.Statements;

import Model.ProgramState.PrgState;
import UserDefinedExceptions.MyException;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
