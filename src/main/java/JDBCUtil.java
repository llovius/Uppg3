import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private static Properties properties = new Properties();
    static {
        try (InputStream input = JDBCUtil.class.getClassLoader().
                getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load database properties");
        }
    }
    public static Connection getConnection() throws SQLException {
        //skapa upp en instans av hsql:s jdbcDriver-klass
        Driver hsqlDriver = new org.hsqldb.jdbcDriver();
        //registrera drivern hos klassen DriverManager
        DriverManager.registerDriver(hsqlDriver);
        //Skapa en URL till databasen
        String dbURL = properties.getProperty("db.url");
        //Default användarnamn
        String userId = properties.getProperty("db.user");
        //Default password
        String password = properties.getProperty("db.password");
        System.out.println("dbURL="+dbURL);
        //Använd metoden getConnection i DriverManager för att få en anslutning till databasen
        Connection conn = DriverManager.getConnection(dbURL, userId, password);
        //Sätt autoCommit till false
        conn.setAutoCommit(false);
        //returnera anslutningen
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closePreparedStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    public string getDatabasNamn(Connection conn) throws SQLException {
        DatabaseMetaData metadata = null;
        try {
            metadata = conn.getMetaData();
            return (String) metadata.getDatabaseProductName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */
}

