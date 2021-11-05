package application.exception;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.inject.Inject;

public class ZeroValue extends Exception {

@Inject
private Logger logger;

    public String ValueEqualsZero() {
        logger.info(Level.DEBUG + "Value of 0 was submitted by user");
        return "Waarde mag niet 0 zijn, aanmaken product wordt afgebroken";
    }
}
