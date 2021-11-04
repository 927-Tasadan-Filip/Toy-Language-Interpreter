package Repository;

import Model.DataStructures.MyIStack;
import Model.ProgramState.PrgState;
import UserDefinedExceptions.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repo implements IRepo {
    private PrgState prg_state;
    private String logFilePath;

    public Repo() {}

    public Repo(PrgState state) {
        this.prg_state = state;
    }

    public Repo(PrgState state, String logFilePath) {
        this.prg_state = state;
        this.logFilePath = logFilePath;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg() {
        return prg_state;
    }

    @Override
    public void logPrgStateExec() throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(this.getCrtPrg().toString());
            logFile.write("\n");
            logFile.close();
        } catch (IOException exception) {
            throw new MyException("The requested logFile couldn't be opened");
        }

    }
}
