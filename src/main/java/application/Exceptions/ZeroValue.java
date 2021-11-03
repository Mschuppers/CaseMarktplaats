package application.Exceptions;

import org.slf4j.Logger;

import javax.inject.Inject;

public class ZeroValue extends Exception {

@Inject
private Logger logger;

    public String ValueEqualsZero() {
        logger.info("Value of 0 was submitted by user");
        return "Waarde mag niet 0 zijn, aanmaken product wordt afgebroken";
    }
}
