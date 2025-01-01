import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WorkRoleDAOImpl implements WorkRoleDAO {
    static Connection con;
    PreparedStatement ps;
    ResultSet rs;
   // WorkRole wR = null;

    public WorkRoleDAOImpl() throws SQLException {
        try {
            con = JDBCUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public void insertWorkRole(WorkRole workRole) throws SQLException {

        try {
            String sql = """
                    
                            INSERT INTO work_role 
                    (title,description,salary,creation_date) 
                    VALUES (?,?,?,?) 
                    """;

            ps = con.prepareStatement(sql);
            ps.setString(1, workRole.getTitle());
            ps.setString(2, workRole.getDescription());
            ps.setDouble(3, workRole.getSalary());
            ps.setDate(4, workRole.getCreationDate());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<WorkRole> fetchWorkRoles() throws SQLException {
        WorkRole workRole = null;
        List<WorkRole> workRoles = new ArrayList<WorkRole>();
        try {

            String sql = "SELECT * FROM work_role";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                workRole = new WorkRole(rs.getInt("role_id"),
                        rs.getString("title"), rs.getString("description"),
                        rs.getDouble("salary"), rs.getDate("creation_date"));
                workRoles.add(workRole);
            }
            return workRoles;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

    }
//
    @Override
    public WorkRole fetchWorkRole(int roleId) throws SQLException {
        // skapa
        WorkRole workRole = null;
        String sql = "SELECT * FROM work_role WHERE role_id=?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, roleId);
        rs = ps.executeQuery();
        while (rs.next()) {
            workRole = new WorkRole(rs.getInt("role_Id"),
                    rs.getString("title"), rs.getString("Description"),
                    rs.getDouble("Salary"), rs.getDate("creation_date"));
        }
//        workRole = wR;
        return workRole;
    }

    @Override
    public int updateWorkRole (WorkRole workRole) throws SQLException {
        try {
            String sql = """
                    UPDATE work_role SET
                    title=?,
                    description=?,
                    salary=?,
                    creation_date=? where role_id=?
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, workRole.getTitle());
            ps.setString(2, workRole.getDescription());
            ps.setDouble(3, workRole.getSalary());
            ps.setDate(4, workRole.getCreationDate());
            ps.setInt(5, workRole.getRoleID());
            int numUpdate = ps.executeUpdate();
//            JDBCUtil.commit(con);
            return numUpdate;

        } catch (SQLException e) {
            //Skriv ut felet till konsollen
            e.printStackTrace();
            //Kasta vidare exceptionet
            throw e;
        }

    }

    @Override
    public int deleteRoleIdGT(Integer idLimit) throws SQLException {

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
        try {
//            conn = JDBCUtil.getConnection();  // Få anslutningen
            String sql = "DELETE FROM work_role WHERE role_id> ?";

            ps = con.prepareStatement(sql);
            ps.setInt(1, idLimit);
            int nRows = ps.executeUpdate();
            JDBCUtil.commit(con);
            System.out.println(nRows + " rader borttagna!");
            return nRows;


        } catch (SQLException e) {

            //Skriv ut felet till konsollen
            e.printStackTrace();
            //Kasta vidare exceptionet
            throw e;
        } finally {
            /*
            rs.close();//stäng resultset
            stmt.close();//stäng statemnet
            conn.close();//stäng connection
            //metoder för detta finns i JDBCUtil
             */
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closePreparedStatement(pstmt);
//            JDBCUtil.closeConnection(conn);
        }
    }

    @Override
    public int deleteRole(Integer roleId) throws SQLException {

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
        int nRows = 0;
        try {
//            conn = JDBCUtil.getConnection();  // Få anslutningen
            String sql = "DELETE FROM work_role WHERE role_id= ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, roleId);
            nRows = ps.executeUpdate();
            if (nRows < 1) {
                System.out.println("Ingen träff i deleten");
                throw new SQLException("No row found");
            }
//            JDBCUtil.commit(con);
            System.out.println(nRows + " rader borttagna!");


        } catch (SQLException e) {

            //Skriv ut felet till konsollen
            e.printStackTrace();
            //Kasta vidare exceptionet
            throw e;
//        } finally {
//            /*
//            rs.close();//stäng resultset
//            stmt.close();//stäng statemnet
//            conn.close();//stäng connection
//            //metoder för detta finns i JDBCUtil
//             */
////            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closePreparedStatement(pstmt);
//            JDBCUtil.closeConnection(conn);
        }
        return nRows;
    }

    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        try {
            String sql = """
                    INSERT INTO EMPLOYEE (
                    name,email, password, role_id) VALUES 
                    (?, ?, ?, ?)
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPassword());
            ps.setInt(4, employee.getWorkRole().getRoleID());
            ps.executeUpdate();
            JDBCUtil.commit(con);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Employee fetchEmployee(String empUser, String empPass) throws SQLException {
        Employee emp1 = null;
        WorkRole workRole=null;
        try {
            String sql = """

                    SELECT name, email, w.role_id, title, description, salary, creation_date
                    FROM employee e  JOIN work_role w
                    ON e.role_id=w.role_id
                    WHERE e.email=? AND e.password=?
                    """;
            /*
            String sql0 = """
                    SELECT name, email, role_id, title, description, salary, creation_date
                    FROM employee e,work_role w
                    WHERE e.role_id=w.role_id AND e.email=? AND e.password=?
                    """;

             */
            ps= con.prepareStatement(sql);
            ps.setString(1, empUser);
            ps.setString(2, empPass);
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                int roleId = rs.getInt("role_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                double salary = rs.getDouble("salary");
                Date creationDate = rs.getDate("creation_date");
                workRole = new WorkRole(roleId, title, description, salary, creationDate);
                emp1 = new Employee(name,workRole,empPass,empUser);

                System.out.println("*******************************************************");
                System.out.println("Välkommen "+name+"!");
                System.out.println("Du är registrerad för denna arbetsroll:");
                System.out.println(workRole.toString());

                return emp1;
            } else {
                System.out.println("gick åt fanders ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return emp1;
    }

    @Override
    public void commit() throws SQLException {
        try {
            if (con != null) {
                con.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void rollback() throws SQLException {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void shutDownDAO() {
        JDBCUtil.closeResultSet(rs);
        JDBCUtil.closePreparedStatement(ps);
        JDBCUtil.closeConnection(con);

    }
}
