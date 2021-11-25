package Repository;

import Model.ProgramState.PrgState;
import UserDefinedExceptions.MyException;

import java.util.List;

public interface IRepo {
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> new_list);
    void logPrgStateExec(PrgState prg) throws MyException;
}
