package View;

import Controller.Controller;
import Model.DataStructures.*;
import Model.Expressions.*;
import Model.ProgramState.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.IRepo;
import Repository.Repo;

import java.io.BufferedReader;

class Interpreter {
    public static void main(String[] args) {
        IStmt ex1 = new CompStatement(new VarDeclStmt("v", new IntType()),
                new CompStatement(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        MyIStack<IStmt> stk1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl1 = new MyDictionary<String, Value>();
        MyIList<Value> out1 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<>();
        MyIHeap<Value> heap1 = new MyHeap<>();
        PrgState prg1 = new PrgState(stk1, symtbl1, out1, fileTable1, heap1, ex1);
        IRepo repo1 = new Repo(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStatement(new VarDeclStmt("a", new IntType()),
                new CompStatement(new VarDeclStmt("b", new IntType()),
                        new CompStatement(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStatement(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        MyIStack<IStmt> stk2 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl2 = new MyDictionary<String, Value>();
        MyIList<Value> out2 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<>();
        MyIHeap<Value> heap2 = new MyHeap<>();
        PrgState prg2 = new PrgState(stk2, symtbl2, out2, fileTable2, heap2, ex2);
        IRepo repo2 = new Repo(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);

        IStmt ex3 = new CompStatement(new VarDeclStmt("a", new BoolType()),
                new CompStatement(new VarDeclStmt("v", new IntType()),
                        new CompStatement(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStatement(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        MyIStack<IStmt> stk3 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl3 = new MyDictionary<String, Value>();
        MyIList<Value> out3 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<>();
        MyIHeap<Value> heap3 = new MyHeap<>();
        PrgState prg3 = new PrgState(stk3, symtbl3, out3, fileTable3, heap3, ex3);
        IRepo repo3 = new Repo(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

        IStmt ex4 = new CompStatement(new VarDeclStmt("varf", new StringType()), new CompStatement(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                                    new CompStatement(new OpenRFile(new VarExp("varf")), new CompStatement(new VarDeclStmt("varc", new IntType()),
                                            new CompStatement(new ReadFile(new VarExp("varf"),"varc"), new CompStatement(new PrintStmt(new VarExp("varc")),
                                                    new CompStatement(new ReadFile(new VarExp("varf"),"varc"), new CompStatement(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))))));
        MyIStack<IStmt> stk4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl4 = new MyDictionary<String, Value>();
        MyIList<Value> out4 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<>();
        MyIHeap<Value> heap4 = new MyHeap<>();
        PrgState prg4 = new PrgState(stk4, symtbl4, out4, fileTable4, heap4, ex4);
        IRepo repo4 = new Repo(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);

        IStmt ex5 = new CompStatement(new VarDeclStmt("fis", new StringType()), new CompStatement(new AssignStmt("fis", new ValueExp(new StringValue("test5.txt"))),
                new CompStatement(new OpenRFile(new VarExp("fis")), new CompStatement(new VarDeclStmt("nr1", new IntType()),
                        new CompStatement(new VarDeclStmt("nr2", new IntType()), new CompStatement(new ReadFile(new VarExp("fis"),"nr1"),
                                new CompStatement(new ReadFile(new VarExp("fis"),"nr2"),new CompStatement(new IfStmt(new RelationalExp(">",new VarExp("nr1"), new VarExp("nr2")),
                                        new PrintStmt(new VarExp("nr1")), new PrintStmt(new VarExp("nr2"))), new CloseRFile(new VarExp("fis"))))))))));
        MyIStack<IStmt> stk5 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl5 = new MyDictionary<String, Value>();
        MyIList<Value> out5 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<>();
        MyIHeap<Value> heap5 = new MyHeap<>();
        PrgState prg5 = new PrgState(stk5, symtbl5, out5, fileTable5, heap5, ex5);
        IRepo repo5 = new Repo(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

        // Heap allocation example
        IStmt ex6 = new CompStatement(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStatement(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                            new CompStatement(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(new HeapAllocation("a", new VarExp("v")),
                                    new CompStatement(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        MyIStack<IStmt> stk6 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl6 = new MyDictionary<String, Value>();
        MyIList<Value> out6 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<>();
        MyIHeap<Value> heap6 = new MyHeap<>();
        PrgState prg6 = new PrgState(stk6, symtbl6, out6, fileTable6, heap6, ex6);
        IRepo repo6 = new Repo(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        // Heap reading example
        IStmt ex7 = new CompStatement(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStatement(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                                new CompStatement(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStatement(new HeapAllocation("a", new VarExp("v")),
                                                new CompStatement(new PrintStmt(new HeapReading(new VarExp("v"))), new PrintStmt(new ArithExp('+',new HeapReading(new HeapReading(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        MyIStack<IStmt> stk7 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl7 = new MyDictionary<String, Value>();
        MyIList<Value> out7 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable7 = new MyDictionary<>();
        MyIHeap<Value> heap7 = new MyHeap<>();
        PrgState prg7 = new PrgState(stk7, symtbl7, out7, fileTable7, heap7, ex7);
        IRepo repo7 = new Repo(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);

        // Heap writing example
        IStmt ex8 = new CompStatement(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStatement(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                            new CompStatement(new PrintStmt(new HeapReading(new VarExp("v"))),
                                    new CompStatement(new HeapWriting("v", new ValueExp(new IntValue(30))),
                                            new PrintStmt(new ArithExp('+',new HeapReading(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        MyIStack<IStmt> stk8 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl8 = new MyDictionary<String, Value>();
        MyIList<Value> out8 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable8 = new MyDictionary<>();
        MyIHeap<Value> heap8 = new MyHeap<>();
        PrgState prg8 = new PrgState(stk8, symtbl8, out8, fileTable8, heap8, ex8);
        IRepo repo8 = new Repo(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

        // While statement example
        IStmt ex9 = new CompStatement(new VarDeclStmt("v", new IntType()),
                        new CompStatement(new AssignStmt("v", new ValueExp(new IntValue(4))),
                                new CompStatement(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))), new CompStatement(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                        new PrintStmt(new VarExp("v")))));
        MyIStack<IStmt> stk9 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl9 = new MyDictionary<String, Value>();
        MyIList<Value> out9 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable9 = new MyDictionary<>();
        MyIHeap<Value> heap9 = new MyHeap<>();
        PrgState prg9 = new PrgState(stk9, symtbl9, out9, fileTable9, heap9, ex9);
        IRepo repo9= new Repo(prg9, "log9.txt");
        Controller ctr9 = new Controller(repo9);

        // Garbage collector example
        IStmt ex10 = new CompStatement(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStatement(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                            new CompStatement(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                    new CompStatement(new HeapAllocation("a", new VarExp("v")),
                                            new CompStatement(new HeapAllocation("v", new ValueExp(new IntValue(30))), new PrintStmt(new HeapReading(new HeapReading(new VarExp("a")))))))));
        MyIStack<IStmt> stk10 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl10 = new MyDictionary<String, Value>();
        MyIList<Value> out10 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable10 = new MyDictionary<>();
        MyIHeap<Value> heap10 = new MyHeap<>();
        PrgState prg10 = new PrgState(stk10, symtbl10, out10, fileTable10, heap10, ex10);
        IRepo repo10 = new Repo(prg10, "log10.txt");
        Controller ctr10 = new Controller(repo10);

        // Garbage collector test
        IStmt ex11 = new CompStatement(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStatement(new HeapAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStatement(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStatement(new HeapAllocation("a", new VarExp("v")),
                                        new CompStatement(new HeapAllocation("v", new ValueExp(new IntValue(30))),
                                                new CompStatement(new PrintStmt(new HeapReading(new HeapReading(new VarExp("a")))),
                                                        new CompStatement(new HeapAllocation("a", new VarExp("v")), new HeapWriting("v", new ValueExp(new IntValue(50))))))))));
        MyIStack<IStmt> stk11 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl11 = new MyDictionary<String, Value>();
        MyIList<Value> out11 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable11 = new MyDictionary<>();
        MyIHeap<Value> heap11 = new MyHeap<>();
        PrgState prg11 = new PrgState(stk11, symtbl11, out11, fileTable11, heap11, ex11);
        IRepo repo11 = new Repo(prg11, "log11.txt");
        Controller ctr11 = new Controller(repo11);

        IStmt ex12 = new CompStatement(new VarDeclStmt("v", new IntType()),
                        new CompStatement(new VarDeclStmt("a", new RefType(new IntType())),
                            new CompStatement(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStatement(new HeapAllocation("a", new ValueExp(new IntValue(22))),
                                    new CompStatement(new ForkStmt(
                                                            new CompStatement(new HeapWriting("a", new ValueExp(new IntValue(30))),
                                                                    new CompStatement(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                            new CompStatement(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReading(new VarExp("a"))))))),
                                    new CompStatement(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReading(new VarExp("a")))))))));
        MyIStack<IStmt> stk12 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl12 = new MyDictionary<String, Value>();
        MyIList<Value> out12 = new MyList<Value>();
        MyIDictionary<StringValue, BufferedReader> fileTable12 = new MyDictionary<>();
        MyIHeap<Value> heap12 = new MyHeap<>();
        PrgState prg12 = new PrgState(stk12, symtbl12, out12, fileTable12, heap12, ex12);
        IRepo repo12 = new Repo(prg12, "log12.txt");
        Controller ctr12 = new Controller(repo12);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
        menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
        menu.addCommand(new RunExample("11", ex11.toString(), ctr11));
        menu.addCommand(new RunExample("12", ex12.toString(), ctr12));
        menu.show();
    }
}
