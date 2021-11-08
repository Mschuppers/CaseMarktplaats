package application.exception;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.inject.Inject;

public class UserAbortedAction extends Exception {

    @Inject
    private Logger logger;

    public String ActionAbortedByUser() {
        logger.debug(Level.DEBUG + "Handeling afgebroken door user");
        return "Handeling afgebroken, product niet toegevoegd";
    }
}
