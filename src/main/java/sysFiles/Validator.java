package sysFiles;

import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Validator {

    @Inject
    private Logger log;

    public static String validatePrice() {

        Scanner sc = new Scanner(System.in);

        final int maxattempts = 3;
        for (int i = 0; i < 4; i++) {
            {
                String ip;
                try {
                     ip = sc.nextLine().replace(",", ".");
                    if (Pattern.matches("\\d*[.]\\d{2}?", ip)) {
                        return ip;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Faulty input detected, 2 digits behindt the comma and numbers only please. \n" +
                            "This was attempt#: " + i + " out of " + maxattempts + " attempts permitted");

                    if (i == maxattempts) {
                        System.out.println("To many attempts!");
                        break;
                    }
                }
            }
        } return "00.00";
    }
}
