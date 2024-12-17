package se.bella.projektarbete.Metoder;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        WorkRolesDAO roles = new WorkRolesImpl();
        try {
            // INSERT
            WorkRoles workroles = new WorkRoles("Economy assistans", "Helping the economy manager", 110.0, Date.valueOf("2024-12-05"));
            WorkRoles workroles2 = new WorkRoles("Seller", "Selling products", 3455.0, Date.valueOf("2024-12-05"));
            roles.insertWorkRole(workroles2);

            // GET ROLE
//            WorkRoles workrole = roles.getWorkRole(14);
//            System.out.println(workrole);

            // GET ALL ROLES
//            List<WorkRoles> allWorkRoles = roles.getAllWorkRoles();
//            for (WorkRoles allWorkRole : allWorkRoles) {
//                System.out.println(allWorkRole);
//            }

            // UPDATE ROLE
//            WorkRoles workrole = roles.getWorkRole(14);
//            workrole.setTitle("Security");
//            workrole.setDescription("Keeping a eye on threats");
//            workrole.setSalary(1333.0);
//            workrole.setCreationDate(Date.valueOf("2024-12-06"));
//            roles.updateWorkRole(workrole);

            // DELETE ROLE
//            WorkRoles workrole = roles.getWorkRole(14);
//            roles.deleteWorkRole(workrole);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}




