package Model.ProgramState;

import Model.DataStructures.*;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PrgState{
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIHeap<Value> heap;
    private  int id;
    private static int prgStateStaticId = 0;

    private IStmt originalProgram;

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIHeap<Value> getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap<Value> heap) {
        this.heap = heap;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public int getId() {
        return id;
    }

    public void setNewId() {
        manageId();
        id = prgStateStaticId;
    }

    public static synchronized void manageId() {
        prgStateStaticId = prgStateStaticId + 1;
    }

    public PrgState() {}
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> filetbl, MyIHeap<Value> hp, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        fileTable = filetbl;
        heap = hp;
        originalProgram = prg.deepCopy();
        setNewId();
        stk.push(prg);
    }

    public boolean isNotCompleted() {
        if(!exeStack.isEmpty()) {
            return true;
        }
        return false;
    }

    public PrgState oneStep() throws MyException{
        if(exeStack.isEmpty()) throw new MyException("prgState stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    public MyIDictionary<String,Value> cloneSymTable() {
        MyDictionary<String, Value> copy_SymTable = new MyDictionary<>();
        Map<String, Value> copy_symbol_map = symTable.getContent().entrySet().stream().
                    collect(Collectors.toMap(e->e.getKey(),
                                             e->e.getValue().deepCopy()));
        HashMap<String, Value> copy_symbol_hash_map = new HashMap<>(copy_symbol_map);
        copy_SymTable.setContent(copy_symbol_hash_map);

        return copy_SymTable;
    }

    @Override
    public String toString() {
        String current_state_string = "";
        current_state_string += "Id=" + Integer.toString(id) + "\n";
        current_state_string += "Heap:\n" + heap.toString();
        current_state_string += "Execution stack:\n" + exeStack.toString();
        current_state_string += "Symbol table:\n" + symTable.toString();
        current_state_string += "Out:\n" + out.toString();
        current_state_string += "FileTable:\n" + fileTable.keysToString();
        return current_state_string;
    }


}