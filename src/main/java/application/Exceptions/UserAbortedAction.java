package application.Exceptions;

import org.slf4j.Logger;

import javax.inject.Inject;

public class UserAbortedAction extends Exception{

@Inject
private Logger logger;

    public String ActionAbortedByUser(){
        logger.info("User aborted action");
        return "Handeling afgebroken, product niet toegevoegd";
    }
}
