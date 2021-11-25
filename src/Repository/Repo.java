package Repository;

import Model.DataStructures.MyIStack;
import Model.ProgramState.PrgState;
import UserDefinedExceptions.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Repo implements IRepo {
    private List<PrgState> prg_list;
    private String logFilePath;

    public Repo() {
        prg_list = new LinkedList<PrgState>();
    }

    public Repo(PrgState state) {
        this();
        prg_list.add(state);
    }

    public Repo(PrgState state, String logFilePath) {
        this();
        prg_list.add(state);
        this.logFilePath = logFilePath;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return prg_list;
    }

    @Override
    public void setPrgList(List<PrgState> new_list) {
        prg_list = new_list;
    }

    @Override
    public void logPrgStateExec(PrgState prg) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(prg.toString());
            logFile.write("\n");
            logFile.close();
        } catch (IOException exception) {
            throw new MyException("The requested logFile couldn't be opened");
        }

    }
}
