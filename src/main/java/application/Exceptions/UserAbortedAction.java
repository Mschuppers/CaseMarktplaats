package application.Exceptions;

public class UserAbortedAction extends Exception{



    public String ActionAbortedByUser(){
        return "Handeling afgebroken, product niet toegevoegd";
    }
}
