package Test.Model.Statements;

import Model.DataStructures.*;
import Model.Expressions.ValueExp;
import Model.ProgramState.PrgState;
import Model.Statements.AssignStmt;
import Model.Statements.IStmt;
import Model.Statements.NopStmt;
import Model.Statements.VarDeclStmt;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.io.BufferedReader;

public class TestStatements {
    public static void main(String[] args) {
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl =
                new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<>();

        // VarDeclStmt test
        IStmt var_decl = new VarDeclStmt("a", new IntType());
        MyIHeap<Value> hp = new MyHeap<>();
        PrgState crtPrgState = new PrgState(stk,symtbl,out, fileTable, hp, var_decl);


        try {
            crtPrgState = var_decl.execute(crtPrgState);
            System.out.println(crtPrgState.toString());
        } catch (MyException exc) {
            System.out.println(exc);
        }

        // AssignStmt test
        IStmt assign_stmt = new AssignStmt("a", new ValueExp(new IntValue(5)));
        try {
            crtPrgState = assign_stmt.execute(crtPrgState);
            System.out.println(crtPrgState.toString());
        } catch (MyException exc) {
            System.out.println(exc);
        }

        // NopStmt
        IStmt nop_stmt = new NopStmt();
        try {
            crtPrgState = nop_stmt.execute(crtPrgState);
            System.out.println(crtPrgState.toString());
        } catch (MyException exc) {
            System.out.println(exc);
        }

        // PrintStmt



    }
}
