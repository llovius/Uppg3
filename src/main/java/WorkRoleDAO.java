import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface WorkRoleDAO {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //public void initDAO() throws Exception;

    public void insertWorkRole(WorkRole workRole) throws SQLException;

    public List<WorkRole> fetchWorkRoles() throws SQLException;

    public WorkRole fetchWorkRole(int id) throws SQLException;

    public Employee fetchEmployee(String empUser, String empPass) throws SQLException;

    public int updateWorkRole(WorkRole workRole) throws SQLException;

    public int deleteRoleIdGT(Integer roleId) throws SQLException;

    public int deleteRole(Integer roleId) throws SQLException;

    public void insertEmployee(Employee employee) throws SQLException;

    public void shutDownDAO() throws Exception;

    public void commit() throws SQLException;
    public void rollback() throws SQLException;
}

