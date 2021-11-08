package application.validator;

import application.exception.UserAbortedAction;
import application.exception.ZeroValue;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.inject.Inject;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Validator {

    @Inject
    private Scanner sc;

    @Inject
    private Logger logger;


    public String validatePrice(String price) throws UserAbortedAction, ZeroValue, InputMismatchException {

        if ((Double.parseDouble(price) > 0) && Pattern.matches("\\d*[.]\\d{2}", price) || (Pattern.matches("\\d*", price))) {
            return price;

        } else {
            if (Double.parseDouble(price) <= 0) throw new ZeroValue();
            System.out.println("Foutieve invoer ontvangen, opnieuw proberen?" +
                    "\n Y: Doorgaan" +
                    "\n N: Stoppen");
            logger.info(Level.DEBUG + "Faulty input detected, either input = 0 or negative or no digit was given");
            switch (sc.nextLine().toLowerCase()) {
                case "y":
                    priceRetry();
                    break;
                case "n":
                    cancelInput();
                default:
                    logger.debug(Level.DEBUG + " No Y or N are given");
            }
        }
        return validatePrice(sc.nextLine().replace(',', '.'));
    }

    public String validateText(String textInput, int max) throws UserAbortedAction {
        if (textInput.length() < max) {
            return textInput;
        } else {
            System.out.println("Te veel karakters, opnieuw proberen? Y/N");
            switch (sc.nextLine().toLowerCase()) {
                case "y":
                    validateText(sc.nextLine(), max);
                    break;
                case "n":
                    return cancelInput();
                default:
                    logger.debug(Level.DEBUG + " No Y or N are given");
                    System.out.println("Ongeldige input, graag Y of N");
            }
        }
        return validateText(sc.nextLine(), max);
    }

    private String cancelInput() throws UserAbortedAction {
        logger.debug(Level.INFO + " User opted to discontinue action");
        throw new UserAbortedAction();
    }

    private void priceRetry() {
        logger.info(Level.DEBUG + " User selected Y to retry");
        System.out.println("Voer een getal in, bijvoorbeeld '19,99'");

    }
}