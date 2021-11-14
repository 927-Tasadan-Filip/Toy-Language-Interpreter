package Controller;

import Model.DataStructures.MyIStack;
import Model.ProgramState.PrgState;
import Model.Statements.CompStatement;
import Model.Statements.IStmt;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepo;
import UserDefinedExceptions.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        List<Integer> stillReferenced = heap.keySet().stream()
                .filter(symTableAddr::contains).collect(Collectors.toList());
        List<Integer> heapReferences = heap.entrySet().stream().
                filter(e->stillReferenced.contains(e.getKey()))
                .filter(e->(e.getValue().getType() instanceof RefType))
                .map(e->(((RefValue)e.getValue()).getAddr())).collect(Collectors.toList());
        List<Integer> allAddrList = Stream.concat(stillReferenced.stream(), heapReferences.stream())
                .collect(Collectors.toList());
        return heap.entrySet().stream()
                    .filter(e->allAddrList.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty()) {
            throw new MyException("Program state stack is empty");
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
        program_repo.logPrgStateExec();
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            printPrgState(prg);
            program_repo.logPrgStateExec();
            prg.getHeap().setContent(safeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getContent().values()),
                    prg.getHeap().getContent()));
            program_repo.logPrgStateExec();
        }
//        System.out.println(prg.getOriginalProgram().toString());
    }
}
