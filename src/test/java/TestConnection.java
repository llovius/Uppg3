import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestConnection {
    @Test
    public void testConnection() {
        int nRolls=0;
        int noRolls=1;
        int nRollsDel = 0;
        int noRollsDel = 1;
        WorkRole workRole0 = null;
        try {
            WorkRoleDAO workRoleDAO = new WorkRoleDAOImpl();
            List<WorkRole> workRoles = workRoleDAO.fetchWorkRoles();
//            id = workRoles.getLast().getRoleID();
            for (WorkRole workRole1 : workRoles) {
                System.out.println(workRole1.toString());
            }
            noRolls = workRoles.size(); //Bör vara 0 utan data
            WorkRole workRole = new WorkRole("VD","Verkställande Direktör",100000.0, Date.valueOf("2024-12-17"));
            workRoleDAO.insertWorkRole(workRole);
            workRoles = workRoleDAO.fetchWorkRoles();
            for (WorkRole workRole1 : workRoles) {
                System.out.println(workRole1.toString());
                workRole0 = workRole1;
            }
            nRolls = workRoles.size(); //Bör vara 1 efter insert data
            nRollsDel = workRoleDAO.deleteRole(workRole0.getRoleID());
            workRoles = workRoleDAO.fetchWorkRoles();
            noRollsDel = workRoles.size();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(0,noRolls);
        Assertions.assertEquals(1,nRolls);
        Assertions.assertEquals(0,noRollsDel);
        Assertions.assertEquals(1,nRollsDel);

    }
}
