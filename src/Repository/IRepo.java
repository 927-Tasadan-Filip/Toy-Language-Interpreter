package Repository;

import Model.ProgramState.PrgState;
import UserDefinedExceptions.MyException;

public interface IRepo {
    PrgState getCrtPrg();
    void logPrgStateExec() throws MyException;
}
