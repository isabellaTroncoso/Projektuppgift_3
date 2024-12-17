package se.bella.projektarbete.applikation;

import se.bella.projektarbete.JDBC.JDBCUtil;
import se.bella.projektarbete.Metoder.WorkRoles;
import se.bella.projektarbete.Metoder.WorkRolesDAO;
import se.bella.projektarbete.Metoder.WorkRolesImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static WorkRolesDAO dao = new WorkRolesImpl();
    private static EmployeeDAO empDAO = new EmployeeDAO();

    public static void main(String[] args)  {

        try (Connection conn = JDBCUtil.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("--Start Menu--");
                System.out.println("1. Insert Work Role");
                System.out.println("2. Show Work Role");
                System.out.println("3. Show All Work Roles");
                System.out.println("4. Update Work Role");
                System.out.println("5. Delete Work Role");
                System.out.println("6. Login");
                System.out.println("7. Exit");
                System.out.print("Enter your choice number: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> insertWorkRole(scanner);
                    case 2 -> showWorkRole(scanner);
                    case 3 -> showAllWorkRoles();
                    case 4 -> updateWorkRole(scanner);
                    case 5 -> deleteWorkRole(scanner);
                    case 6 -> login(scanner, conn);
                    case 7 -> running = false;
                    default -> System.out.println("Invalid choice! Try again.");

                }
            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertWorkRole(Scanner scanner) throws SQLException {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Creation date (YYYY-MM-DD): ");
        String creationDate = scanner.nextLine();
        System.out.println();

        WorkRoles workRole = new WorkRoles(title, description, salary, Date.valueOf(creationDate));
        dao.insertWorkRole(workRole);
        System.out.println("Work role created successfully.");
        System.out.println();
    }

    private static void showWorkRole(Scanner scanner) throws SQLException {
        System.out.println("--Show Work Role--");
        System.out.print("Enter role ID: ");
        int roleId = scanner.nextInt();
        System.out.println();

        WorkRoles workRole = dao.getWorkRole(roleId);
        if (workRole != null) {
            System.out.println("Title: " + workRole.getTitle());
            System.out.println("Description: " + workRole.getDescription());
            System.out.println("Salary: " + workRole.getSalary());
            System.out.println("Creation date (YYYY-MM-DD): " + workRole.getCreationDate());
            System.out.println();

        } else {
            System.out.println("No work role found with ID " + roleId);
            System.out.println();
        }
    }

    private static void showAllWorkRoles() throws SQLException {
        System.out.println("\nAll Work Roles: ");
        List<WorkRoles> workRoles = dao.getAllWorkRoles();
        if (workRoles.isEmpty()) {
            System.out.println("No work roles found.");
        } else {
            for (WorkRoles role : workRoles) {
                System.out.println("Work role " + role);
                System.out.println();
            }
        }
    }

    private static void updateWorkRole(Scanner scanner) throws SQLException {
        System.out.println("--Update Work Role--");
        System.out.print("Enter work role ID to update: ");
        int roleId = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        System.out.print("New title: ");
        String title = scanner.nextLine();
        System.out.print("New description: ");
        String description = scanner.nextLine();
        System.out.print("New salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("New creation date (YYYY-MM-DD): ");
        String creationDate = scanner.nextLine();
        System.out.println();

        WorkRoles workRole = new WorkRoles(roleId, title, description, salary, Date.valueOf(creationDate));
        dao.updateWorkRole(workRole);
        System.out.println("Work role updated successfully.");
        System.out.println();
    }

    private static void deleteWorkRole(Scanner scanner) throws SQLException {
        System.out.println("--Delete Work Role--");
        System.out.print("Enter work role ID to delete: ");
        int roleId = scanner.nextInt();
        System.out.println();

        WorkRoles workRole = new WorkRoles(roleId, null, null, 0, null);
        dao.deleteWorkRole(workRole);
        System.out.println("Work role deleted successfully.");
        System.out.println();
    }

    private static void login(Scanner scanner, Connection conn) throws SQLException {
        System.out.println("--Login--");
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println();

        Employee employee = empDAO.validateLogin(conn, email, password);
        if (employee != null) {
            empDAO.printEmployeeDetails(employee);
        } else {
            System.out.println("Login failed. Incorrect email or password.");
            System.out.println();
        }

    }

}
