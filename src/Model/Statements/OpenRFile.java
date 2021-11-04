package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class OpenRFile implements IStmt{
    private Exp exp;

    public OpenRFile() {}

    public OpenRFile(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "OpenRFile(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();
        Value val = exp.eval(symTbl);
        if(val.getType().equals(new StringType())) {
            StringValue string_val = (StringValue) val;
            if(fileTbl.isDefined(string_val)) {
                throw new MyException("there is another file with this name in the program");
            } else {
                try {
                    Reader reader = new FileReader(string_val.getVal());
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    fileTbl.update(string_val, bufferedReader);
                } catch (IOException exception) {
                    throw new MyException("the requested file cannot be opened");
                }
            }
        } else {
            throw new MyException("OpenRFile exp is not a StringType");
        }
        return state;
    }
}
