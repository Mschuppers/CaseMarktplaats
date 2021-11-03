package application.Exceptions;

public class ZeroValue extends Exception {


    public String ValueEqualsZero() {
        return "Waarde mag niet 0 zijn, aanmaken product wordt afgebroken";
    }
}
