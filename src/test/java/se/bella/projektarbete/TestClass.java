package se.bella.projektarbete;

import org.junit.Test;
import se.bella.projektarbete.JDBC.JDBCUtil;
import se.bella.projektarbete.Metoder.WorkRoles;
import se.bella.projektarbete.Metoder.WorkRolesDAO;
import se.bella.projektarbete.Metoder.WorkRolesImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestClass {

    @Test
    public void testConnectionIsOk() {
        try {
            JDBCUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInsertAndFetchAllWorkRoles() {
        try {
            WorkRolesDAO roles = new WorkRolesImpl();
            WorkRoles newRole = new WorkRoles("Waiter", "Serving food", 100.0, Date.valueOf("2024-12-05"));
            WorkRoles newRole2 = new WorkRoles("Taxi","Driving around",322.2,Date.valueOf("2024-12-09"));

            roles.insertWorkRole(newRole);
            roles.insertWorkRole(newRole2);
            List<WorkRoles> workRoles = roles.getAllWorkRoles();

            assertEquals(2, workRoles.size());
            System.out.println(workRoles);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
