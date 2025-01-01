import org.hsqldb.persist.Log;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.String.valueOf;

public class WorkRoleIO {
    String input = null;
    WorkRoleDAO workRoleDAO = null;
    Scanner scanner = null;
    Uppdatera u = new Uppdatera();
    TaBort t = new TaBort();
    LogInEmp loginEmp = new LogInEmp();

    public void editWorkRole() throws IOException, SQLException {

        WorkRole workRole = null;
        Integer id = null;
        List<WorkRole> workRoles = new ArrayList<WorkRole>();
        scanner = new Scanner(System.in);
        JaEjNej jaEjNej = new JaEjNej();
 try {
            workRoleDAO = new WorkRoleDAOImpl();
            workRoles = workRoleDAO.fetchWorkRoles();
            id = workRoles.getLast().getRoleID();
            for (WorkRole workRole1 : workRoles) {
                System.out.println(workRole1.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

       System.out.println("Du hanterar arbetsroller. Följande kommandon finns:");
        System.out.println("1. Skapa ny arbetsroll");
        System.out.println("2. Ta bort en arbetsroll");
        System.out.println("3. Visa alla arbetsroller");
        System.out.println("4. Visa en arbetsroll");
        System.out.println("5. Uppdatera en arbetsroll");
        System.out.println("6. Logga in som anställd");
        System.out.println("7 Avsluta");


        boolean forts = true;
        while (forts) {

            input = scanner.nextLine().stripLeading();
            String finput = input.substring(0, 1).toUpperCase();
            System.out.println();

            switch (finput) {
                case "1" -> u.nyPost(workRoleDAO, scanner);
                case "2" -> TaBort.tabort(workRoleDAO, scanner, jaEjNej);
                case "3" -> u.visaA(workRoleDAO, scanner);
                case "4" -> u.visa1(workRoleDAO, scanner);
                case "5" -> u.uppdatera(workRoleDAO, scanner);
                case "6" -> loginEmp.logInEmp(workRoleDAO, scanner);
                case "7" -> {
                    System.out.println("Avslutar redigering av arbetsroller - tack för visat intresse!");
                    forts = false;
                }
                default -> System.out.println("Felaktig val:" + finput + " Försök igen");
            }
            System.out.println("1-Skapa ny | 2-Ta bort | 3-Visa alla | 4-Visa en |" +
                    " 5-Uppdatera | 6-Logga in | 7-sluta");
        }

    }


}