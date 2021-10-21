package Controller;

import Model.DataStructures.MyIStack;
import Model.ProgramState.PrgState;
import Model.Statements.IStmt;
import Repository.IRepo;
import UserDefinedExceptions.MyException;

public class Controller {
    private IRepo program_repo;
    boolean display_flag = false;

    public boolean isDisplayFlag() {
        return display_flag;
    }

    public void setDisplayFlagTrue() {
        this.display_flag = true;
    }

    public void setDisplayFlagFalse() {
        this.display_flag = false;
    }

    public Controller() {}
    public Controller(IRepo program_repo) {
        this.program_repo = program_repo;
    }

    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty()) {
            throw new MyException("prgstate stack is empty");
        }
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void printPrgState(PrgState prg) {
        if(isDisplayFlag()) {
            System.out.println(prg.toString());
        }
    }

    public void allStep() throws MyException{
        PrgState prg = program_repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface
        printPrgState(prg);
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            printPrgState(prg);
        }
    }
}
