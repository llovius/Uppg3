import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {


        WorkRole workRole = new WorkRole("VD","Verkställande Direktör",100000.0,
            java.sql.Date.valueOf("2024-08-08"));
//        WorkRole workRole = new WorkRole("EC","Ekonomi Chef",999999.9,
//                java.sql.Date.valueOf("2024-12-31"));
        try {
            WorkRoleIO workRoleIO = new WorkRoleIO();
            workRoleIO.editWorkRole();

//       workRoleDAO.insertWorkRole(workRole);//måste omgärdas av try och catch-block!
//            WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();
//            workRoleDAO.insertWorkRole(workRole);
            /*
            workRole = new WorkRole("VD","Verkställande Direktör",100001.0,
                    java.sql.Date.valueOf("2024-08-08"));
            workRoleDAO.insertWorkRole(workRole);

            List<WorkRole> workRoles = new ArrayList<WorkRole>();
            workRoles = workRoleDAO.fetchWorkRoles();
            for (WorkRole workRole1 : workRoles) {
//                System.out.println("I main");
                System.out.println(workRole1.toString());
            }

            workRole = workRoleDAO.fetchWorkRole(1);
            workRole.setSalary(100000.70);
            int nupdate = workRoleDAO.updateWorkRole(1);
            System.out.println(workRole.toString());
            System.out.println("Antal upd :" + nupdate);
            workRole = workRoleDAO.fetchWorkRole(1);
            Employee employee = new Employee("John Snålberg", workRole, "Spar0har",
                    "john0@firman.com");
            workRoleDAO.insertEmployee(employee);
            System.out.println(employee.toString());


            Integer idToDeleteLimit = 12;

            int nbort = workRoleDAO.deleteRoleIdGT(idToDeleteLimit);
            System.out.println("Ja du slapp "+nbort+" rader");

//            Integer id=0;
//            personDAO.deletePerson(id);
////            Person person = personDAO.getPerson(3);
//            if (person != null) {
//                System.out.println(person.toString());
//            }


            workRoleDAO.shutDownDAO();

             */
        } catch (Exception e) {

            //Skriv ut felet till konsollen
            e.printStackTrace();
            //Kasta vidare exceptionet
            throw e;
        }

    }
}