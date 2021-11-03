package application.sysFiles;

import application.Exceptions.UserAbortedAction;
import application.Exceptions.ZeroValue;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Validator {

    @Inject
    private Scanner sc;

    @Inject
    private Logger log;


    public String validatePrice(String price) throws UserAbortedAction, ZeroValue, InputMismatchException {

        if (!(Double.parseDouble(price) <= 0) && Pattern.matches("\\d*[.]\\d{2}?", price) || (Pattern.matches("\\d*", price))) {
            log.info("Value registered and approved by system: "+price);
            return price;
        } else {
            if (Double.parseDouble(price) <= 0) throw new ZeroValue();
            System.out.println("Foutieve invoer ontvangen, opnieuw proberen?"+
                    "\n Y: Doorgaan" +
                    "\n N: Stoppen");
            log.info( "Faulty input detected, either input = 0 or negative or no digit was given");
            String retry = sc.nextLine().toLowerCase();
            switch (retry) {
                case "y":
                    log.info("User selected Y to retry");
                    System.out.println("Voer een getal in, bijvoorbeeld '19,99'");
                    price = sc.nextLine().replace(',', '.');
                    validatePrice(price);
                    break;

                case "n":
                    log.info("User opted to discontinue action");
                    System.out.println("Productinvoer wordt stopgezet");
                    throw new UserAbortedAction();

                default:
                    log.info("No Y or N are given");
        }
        return price;
    }

}

//    public String validateDescription(String description) {
//
//    }
}
