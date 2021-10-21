package View;

import Controller.Controller;
import Model.DataStructures.*;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.ProgramState.PrgState;
import Model.Statements.*;
import Model.Types.IntType;
import Model.Values.IntValue;
import Model.Values.Value;
import Repository.IRepo;
import Repository.Repo;
import UserDefinedExceptions.MyException;


public class HardcodedExamples {
    private IRepo view_repo;
    private Controller view_controller;

    HardcodedExamples() {};
    public void Example1() {
        System.out.println("int v; v=2; Print(v)");
        IStmt ex1= new CompStatement(new VarDeclStmt("v",new IntType()),
                new CompStatement(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl =
                new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        PrgState crtPrgState= new PrgState(stk,symtbl,out, ex1);
        view_repo = new Repo(crtPrgState);
        view_controller = new Controller(view_repo);
        view_controller.setDisplayFlagTrue();

        try {
            view_controller.allStep();
        } catch (MyException exception) {
            System.out.println(exception);
        }

    }
}
