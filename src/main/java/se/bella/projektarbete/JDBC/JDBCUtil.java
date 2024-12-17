package se.bella.projektarbete.JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static Properties properties = new Properties();
    // skapas en Properties-instans för att läsa konfigurationen från application.properties.

    static {
        try (InputStream input = JDBCUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input); //Koden laddar filen application.properties. Om filen inte finns, kastas ett fel.
            System.out.println("Inläsning OK");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load database properties");
        }
    }


    public static Connection getConnection() throws SQLException {
        Driver hsqlDriver = new org.hsqldb.jdbcDriver();
        DriverManager.registerDriver(hsqlDriver); // Laddar HSQLDB-drivrutinen
        String dbURL = properties.getProperty("db.url");
//        System.out.println("Database URL: " + dbURL);
        String userId = properties.getProperty("db.user");
//        System.out.println("User ID: " + userId);
        String password = properties.getProperty("db.password");
//        System.out.println("Password: " + password);
        // Hämtar databasens URL, användarnamn och lösenord från application.properties.


        Connection conn = DriverManager.getConnection(dbURL,userId,password);
        //Skapar en anslutning med DriverManager.getConnection().
        conn.setAutoCommit(false);
        return conn;

    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(PreparedStatement stmt) {
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if (conn != null)
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(conn); // Om commit misslyckas, gör rollback
        }
    }

    public static void rollback(Connection conn) {
        try {
            if (conn != null)
                conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
