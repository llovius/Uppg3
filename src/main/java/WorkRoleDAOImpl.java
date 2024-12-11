import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WorkRoleDAOImpl implements WorkRoleDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    WorkRole wR = null;

    public WorkRoleDAOImpl() throws SQLException {
        try {
            con = JDBCUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

//    @Override
//    public void initDAO() throws SQLException {
//        try {
//            con = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
    @Override
    public void insertWorkRole(WorkRole workRole) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = JDBCUtil.getConnection();  // Få anslutningen
////            stmt = conn.createStatement(); // Skapa ett
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
            JDBCUtil.commit(con);

        } catch (SQLException e) {

            e.printStackTrace();
            throw e;

//        } finally {
//            /*
//            rs.close();//stäng resultset
//            stmt.close();//stäng statemnet
//            conn.close();//stäng connection
//            //metoder för detta finns i JDBCUtil
//             */
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closePreparedStatement(pstmt);
//            JDBCUtil.closeConnection(conn);
        }

    }

    @Override
    public List<WorkRole> fetchWorkRoles() throws SQLException {

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
        WorkRole workRole = null;
        List<WorkRole> workRoles = new ArrayList<WorkRole>();
        try {
//            conn = JDBCUtil.getConnection();  // Få anslutningen
//            stmt = conn.createStatement(); // Skapa ett
            String sql = "SELECT * FROM work_role";
            ps = con.prepareStatement(sql);
//            pstmt.setInt(1,  '*');
            rs = ps.executeQuery();
            while (rs.next()) {
                workRole = new WorkRole(rs.getInt("role_id"),
                        rs.getString("title"), rs.getString("description"),
                        rs.getDouble("salary"), rs.getDate("creation_date"));
                workRoles.add(workRole);
//                System.out.println(workRole.toString());
            }
            return workRoles;


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
//            JDBCUtil.closeResultSet(rs);
//            JDBCUtil.closePreparedStatement(pstmt);
//            JDBCUtil.closeConnection(conn);
//            workRoles = null;
       }

    }

    @Override
    public WorkRole fetchWorkRole(int roleId) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        WorkRole workRole = null;
//        con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM work_role WHERE role_id=?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, roleId);
        rs = ps.executeQuery();
        while (rs.next()) {
            wR = new WorkRole(rs.getInt("role_Id"),
                    rs.getString("title"), rs.getString("Description"),
                    rs.getDouble("Salary"), rs.getDate("creation_date"));
        }
//        workRole = wR;
        return wR;
    }

    @Override
    public int updateWorkRole(Integer workId) throws SQLException {
        try {
            String sql = """
                    UPDATE work_role SET
                    title=?,
                    description=?,
                    salary=?,
                    creation_date=? where role_id=?
                    """;
            ps = con.prepareStatement(sql);
            ps.setString(1, wR.getTitle());
            ps.setString(2, wR.getDescription());
            ps.setDouble(3, wR.getSalary());
            ps.setDate(4, wR.getCreationDate());
            ps.setInt(5, workId);
            int numUpdate = ps.executeUpdate();
            JDBCUtil.commit(con);
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
    public void deleteRole(Integer roleId) throws SQLException {

//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
        try {
//            conn = JDBCUtil.getConnection();  // Få anslutningen
            String sql = "DELETE FROM work_role WHERE role_id= ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, roleId);
            int nRows = ps.executeUpdate();
            if (nRows < 1) {
                System.out.println("Ingen träff i deleten");
                throw new SQLException("No row found");
            }
            JDBCUtil.commit(con);
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
    public void shutDownDAO(){
        JDBCUtil.closeResultSet(rs);
        JDBCUtil.closePreparedStatement(ps);
        JDBCUtil.closeConnection(con);
        wR=null;
    }
}
