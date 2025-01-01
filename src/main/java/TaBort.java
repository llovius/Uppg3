import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TaBort {
    public static void tabort(WorkRoleDAO workRoleDAO, Scanner scanner, JaEjNej jaEjNej) {
        Integer id = null;
        List<WorkRole> workRoles;


        try {
            System.out.print("Roll-Id:");
            String input = scanner.nextLine();
            while (true) {
                try {
                    id = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("FEL!!! Ge Roll-ID som ett heltal!");

                }
            }
            WorkRole workRole = workRoleDAO.fetchWorkRole(id);

            int numDel = workRoleDAO.deleteRole(id);
            if (numDel > 0) {
                System.out.println(numDel + " rad borttagen med role_id=" + id);
                System.out.println(workRole.toString());
                if (jaEjNej.jaEjNej("borttagning!", scanner)) workRoleDAO.commit();
                else workRoleDAO.rollback();

//                workRoles = workRoleDAO.fetchWorkRoles();
//                for (WorkRole workRole1 : workRoles) {
//                    System.out.println(workRole1.toString());
//                }

            } else {
                System.out.println("Hittar ingen rad att ta bort med role_id=" + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}