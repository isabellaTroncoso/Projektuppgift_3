package se.bella.projektarbete.applikation;

import se.bella.projektarbete.Metoder.WorkRoles;

import java.util.Objects;

public class Employee {
    private int employeeId;
    private String name;
    private String email;
    private String password;
    private WorkRoles workRole;

    public Employee(int employeeId, String name, String email, String password, WorkRoles workRole) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }
    public Employee(String name, String email, String password, WorkRoles workRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.workRole = workRole;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {

        return name;
    }

    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
    }

    public WorkRoles getWorkRole() {
        return workRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return employeeId == employee.employeeId && Objects.equals(name, employee.name) && Objects.equals(email, employee.email) && Objects.equals(password, employee.password) && Objects.equals(workRole, employee.workRole);
    }

    @Override
    public int hashCode() {

        return Objects.hash(employeeId, name, email, password, workRole);
    }
}
