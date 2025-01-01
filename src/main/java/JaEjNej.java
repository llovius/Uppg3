import java.util.Scanner;

public class JaEjNej {

    boolean jaEjNej(String questionLine, Scanner scanner) {
        String input;
        System.out.println("Spara? J/N");
        input = scanner.nextLine().stripLeading();
        String ettan = input.substring(0, 1).toUpperCase();
        if (ettan.equals("J")) {
            System.out.println("Sparar " + questionLine);
            return true;
        } else {
            System.out.println("Ångrar " + questionLine);
            return false;
        }

    }

}
