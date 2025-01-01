import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInEmp {
    public void logInEmp(WorkRoleDAO workRoleDAO, Scanner scanner ) throws SQLException {
        String input = null;
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        boolean felTecken = true;
        while (felTecken) {
            System.out.print("Enter your email: ");
            input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            felTecken = !(matcher.matches());
        }
        String email = input;
        System.out.println("Ge lösenord: ");
        input = scanner.nextLine();
        String losen = input;
        try {
            Employee employee = workRoleDAO.fetchEmployee(email, losen);
            if (employee == null) {
                System.out.println("Inlogning misslyckades,fel e-post eller lösenord!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }
}