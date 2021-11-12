package Model.Statements;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.DataStructures.MyIList;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt{
    private Exp exp;
    private String var_name;

    public ReadFile() {}

    public ReadFile(Exp exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public String getVar_name() {
        return var_name;
    }

    public void setVar_name(String var_name) {
        this.var_name = var_name;
    }

    @Override
    public String toString() {
        return "ReadFile(" + exp.toString() + ", " + var_name + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();
        MyIHeap<Value> hp = state.getHeap();

        if(symTbl.isDefined(var_name)) {
            Value val = symTbl.lookup(var_name);
            if(val.getType().equals(new IntType())) {
                Value exp_val = exp.eval(symTbl, hp);
                if(exp_val.getType().equals(new StringType())) {
                    StringValue exp_string_val = (StringValue) exp_val;
                    if(fileTbl.isDefined(exp_string_val)) {
                        BufferedReader associated_reader = fileTbl.lookup(exp_string_val);
                        try {
                            String line = associated_reader.readLine();
                            IntValue int_val;
                            if(line == null) {
                                int_val = new IntValue(0);
                            } else {
                                int_val = new IntValue(Integer.parseInt(line));
                            }
                            symTbl.update(var_name, int_val);
                        } catch (IOException exception) {
                            throw new MyException("the program cannot read from the requested file");
                        }

                    } else {
                        throw new MyException("there is no file with this name");
                    }

                } else {
                    throw new MyException("the exp is not string type");
                }
            }
            else {
                throw new MyException("the defined variable with this var_name is not int type");
            }
        } else {
            throw new MyException("there is no defined variable with this var_name");
        }

        return state;
    }
}
