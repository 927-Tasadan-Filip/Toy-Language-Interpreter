package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

public class VarDeclStmt implements IStmt{
    private String name;
    private Type typ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getTyp() {
        return typ;
    }

    public void setTyp(Type typ) {
        this.typ = typ;
    }

    public VarDeclStmt() {}

    public VarDeclStmt(String name, Type typ) {
        this.name = name;
        this.typ = typ;
    }

    @Override
    public String toString() {
        return typ.toString() + " " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();

        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (symTbl.isDefined(name)) {
            throw new MyException("variable is already declared.");
        }
        else {
            symTbl.update(name, typ.defaultValue());
        }
        return state;
    }
}
