package Model.ProgramState;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.DataStructures.MyIList;
import Model.DataStructures.MyIStack;
import Model.Statements.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;

public class PrgState{
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private  MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIHeap<Value> heap;

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

    public PrgState() {}
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> filetbl, MyIHeap<Value> hp, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        fileTable = filetbl;
        heap = hp;
        // TODO
        // originalProgram=deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    @Override
    public String toString() {
        String current_state_string = "";
        current_state_string += "Execution stack:\n" + exeStack.toString();
        current_state_string += "Symbol table:\n" + symTable.toString();
        current_state_string += "Out:\n" + out.toString();
        current_state_string += "FileTable:\n" + fileTable.keysToString();
        current_state_string += "Heap:\n" + heap.toString();
        return current_state_string;
    }


}