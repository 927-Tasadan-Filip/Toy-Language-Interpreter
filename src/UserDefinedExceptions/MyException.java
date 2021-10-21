package UserDefinedExceptions;

public class MyException extends Exception{
    public MyException() {}
    public MyException(String msg)  {
        super(msg);
    }
    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

}
