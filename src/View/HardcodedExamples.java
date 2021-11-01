package View;

import Controller.Controller;
import Model.DataStructures.*;
import Model.Expressions.ArithExp;
import Model.Expressions.ValueExp;
import Model.Expressions.VarExp;
import Model.ProgramState.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;
import Repository.IRepo;
import Repository.Repo;
import UserDefinedExceptions.MyException;

import java.util.Scanner;


public class HardcodedExamples {
    private IRepo view_repo;
    private Controller view_controller;
    Scanner examples_scanner;
    Integer user_command;

    HardcodedExamples() {
        examples_scanner = new Scanner(System.in);
    }

    private void askDisplayFlag() {
        System.out.println("Do you want to execute with display flag on? 1 - yes / 2 - no");
        System.out.print(">> ");
        user_command = examples_scanner.nextInt();
        switch (user_command) {
            case 1:
                view_controller.setDisplayFlagTrue();
                break;
            case 2:
                view_controller.setDisplayFlagFalse();
                break;
        }
    }

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

        try {
            askDisplayFlag();
            view_controller.allStep();
        } catch (MyException exception) {
            System.out.println(exception);
        }

    }

    public void Example2() {
        System.out.println("int a;int b; a=2+3*5;b=a+1;Print(b)");
        IStmt ex2 = new CompStatement( new VarDeclStmt("a",new IntType()),
                new CompStatement(new VarDeclStmt("b",new IntType()),
                        new CompStatement(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStatement(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
                MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl =
                new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        PrgState crtPrgState= new PrgState(stk,symtbl,out, ex2);
        view_repo = new Repo(crtPrgState);
        view_controller = new Controller(view_repo);

        try {
            askDisplayFlag();
            view_controller.allStep();
        } catch (MyException exception) {
            System.out.println(exception);
        }
    }

    public void Example3() {
        System.out.println("bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        IStmt ex3 = new CompStatement(new VarDeclStmt("a",new BoolType()),
                new CompStatement(new VarDeclStmt("v", new IntType()),
                        new CompStatement(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStatement(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl =
                new MyDictionary<String,Value>();
        MyIList<Value> out = new MyList<Value>();
        PrgState crtPrgState= new PrgState(stk,symtbl,out, ex3);
        view_repo = new Repo(crtPrgState);
        view_controller = new Controller(view_repo);

        try {
            askDisplayFlag();
            view_controller.allStep();
        } catch (MyException exception) {
            System.out.println(exception);
        }
    }
}
