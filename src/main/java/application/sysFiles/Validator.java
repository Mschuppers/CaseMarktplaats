package application.sysFiles;

import application.Exceptions.UserAbortedAction;
import application.Exceptions.ZeroValue;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Validator {

    @Inject
    private Scanner sc;

    @Inject
    private Logger log;


    public String validatePrice(String price) {

        try {
            if (Pattern.matches("\\d*[.]\\d{2}?", price) | (Pattern.matches("\\d*", price))) {
                return price;
            } else {
                if (Double.parseDouble(price)<=0) throw new ZeroValue();
                log.info("Foutieve invoer ontvangen, opnieuw proberen?" +
                        "\n Y: Doorgaan" +
                        "\n N: Stoppen");
                String retry = sc.nextLine().toLowerCase();
                switch (retry) {
                    case "y":
                        log.info("Voer een getal in, bijvoorbeeld '19,99'");
                        price = sc.nextLine().replace(',', '.');
                        validatePrice(price);
                        break;

                    case "n":
                        log.info("Productinvoer wordt stopgezet");
                        throw new UserAbortedAction();
                }
            }
        } catch (ZeroValue e) {
            log.info(e.ValueEqualsZero());
        } catch (UserAbortedAction e){
            log.info(e.ActionAbortedByUser());
        } throw new IllegalArgumentException();
    }
}
