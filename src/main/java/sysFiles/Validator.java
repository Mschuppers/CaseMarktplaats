package sysFiles;

import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Validator {

    @Inject
    private Logger log;

    public String validatePrice() {

        Scanner sc = new Scanner(System.in);

        final int maxattempts = 3;
        String ip = sc.nextLine().replace(",", ".");
        for (int i = 0; i < 3; ++i) {
            try {
                if (Pattern.matches("\\d*[.]\\d{2}?", ip) && (Double.parseDouble(ip)>0)) {
                    return ip;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Faulty input detected, 2 digits behindt the comma and numbers only please. \n" +
                        "This was attempt#: " + i + " out of " + maxattempts + " attempts permitted");
                ip = sc.nextLine().replace(",", ".");
                if (i == maxattempts) {
                    System.out.println("To many attempts!");
                    new RuntimeException();
                    log.error("Entered number of " + ip + " which does not match the xx.xx format");
                }
            }
        }
        return "";
    }
}

