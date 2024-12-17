package se.bella.projektarbete.applikation;

import se.bella.projektarbete.Metoder.WorkRoles;

import java.sql.*;


public class EmployeeDAO {

    public void printEmployeeDetails(Employee employee) {
        if (employee != null) {
            System.out.println("Found Employee!");
            System.out.println("Name: " + employee.getName());
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Password: " + employee.getPassword());
            System.out.println("Work Role: ............ " + "\n" + employee.getWorkRole());
        } else {
            System.out.println("No employee found.");
        }

    }

    public Employee validateLogin(Connection conn, String login, String password) {
        try {
            String sql = """
                    SELECT e.employee_id, e.name, e.email, e.password, w.role_id, w.title, w.description,
                           w.salary, w.creation_date FROM employee e
                    LEFT JOIN work_role w ON e.role_id = w.role_id
                    WHERE e.email = ? AND e.password = ?;
                    """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    WorkRoles workrole = new WorkRoles(
                            rs.getInt("role_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getDouble("salary"),
                            rs.getDate("creation_date"));

                    return new Employee(
                            rs.getInt("employee_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            workrole);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}


