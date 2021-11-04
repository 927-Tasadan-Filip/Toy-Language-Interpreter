package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
    private Exp exp;

    public CloseRFile() {}

    public CloseRFile(Exp exp) {
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
        return "CloseRFile(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();

        Value exp_val = exp.eval(symTbl);
        if(exp_val.getType().equals(new StringType())) {
            StringValue exp_string_val = (StringValue)exp_val;
            if(fileTbl.isDefined(exp_string_val)) {
                BufferedReader associated_reader = fileTbl.lookup(exp_string_val);
                try {
                    associated_reader.close();
                    fileTbl.remove(exp_string_val);
                } catch (IOException exception) {
                    throw new MyException("the requested file cannot be closed");
                }

            }
            else {
                throw new MyException("there is no file with this name in the program");
            }

        } else {
            throw new MyException("OpenRFile exp is not a StringType");
        }

        return state;
    }
}
