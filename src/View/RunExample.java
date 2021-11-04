package View;

import Controller.Controller;
import UserDefinedExceptions.MyException;

import java.util.Scanner;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allStep(); }
        catch (MyException exception) {
            System.out.println("Error: " + exception);
        }
    }
}
