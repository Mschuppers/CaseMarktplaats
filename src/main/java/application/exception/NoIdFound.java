package application.exception;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.inject.Inject;

public class NoIdFound extends Exception {
    @Inject
    private Logger logger;

    public String UnkownId() {
        logger.info(Level.DEBUG + "Opgegeven ID niet gevonden");
        return "Opgegeven ID niet gevonden";
    }
}
