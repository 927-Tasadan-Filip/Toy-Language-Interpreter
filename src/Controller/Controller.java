package Controller;

import Model.DataStructures.MyDictionary;
import Model.DataStructures.MyIStack;
import Model.ProgramState.PrgState;
import Model.Statements.CompStatement;
import Model.Statements.IStmt;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepo;
import UserDefinedExceptions.MyException;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepo program_repo;
    private boolean display_flag = false;
    private ExecutorService executor;

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

    Map<Integer, Value> conservativeGarbageCollector(List<List<Integer>> symTableAddrList, Map<Integer,Value> heap){
        List<Integer> stillReferenced = heap.keySet().stream()
                .filter(id->{ for(List<Integer> currentTbl:symTableAddrList) {
                                if(currentTbl.contains(id)) {
                                    return true;}
                            }
                            return false;})
                .collect(Collectors.toList());
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

    public void printPrgState(PrgState prg) {
        if(isDisplayFlag()) {
            System.out.println(prg.toString());
        }
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> prgList) {

        prgList.forEach(prg-> {try {program_repo.logPrgStateExec(prg);}
                            catch (MyException e) {}});

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> p.oneStep()))
                .collect(Collectors.toList());

        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    . map(future -> { try { return future.get();}
                        catch(Exception e) {System.out.println(e.toString()); return null;} }).filter(p -> p!=null)
                    .collect(Collectors.toList());
            prgList.addAll(newPrgList);
            prgList.forEach(prg-> {try {program_repo.logPrgStateExec(prg);}
                    catch (MyException e) {}});
            program_repo.setPrgList(prgList);
        } catch (InterruptedException e) {}

    }

    public void allStep() throws MyException{
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(program_repo.getPrgList());
        while(prgList.size() > 0) {
            List<List<Integer>> symTableAddrList = new ArrayList<>();
            PrgState last_prg = null;
            for(PrgState prg: prgList) {
                if(prg != null) {
                    symTableAddrList.add(getAddrFromSymTable(prg.getSymTable().getContent().values()));
                    last_prg = prg;
                }
            }
            if(last_prg != null) {
                last_prg.getHeap().setContent(conservativeGarbageCollector(symTableAddrList, last_prg.getHeap().getContent()));
            }

            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(program_repo.getPrgList());
        }
        executor.shutdownNow();
        program_repo.setPrgList(prgList);
    }
}
