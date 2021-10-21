package Model.Statement;

import UserDefinedExceptions.MyException;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }
}
