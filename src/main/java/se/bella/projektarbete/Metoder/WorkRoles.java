package se.bella.projektarbete.Metoder;

import java.sql.Date;
import java.util.Objects;

public class WorkRoles {
    private int roleId;
    private String title;
    private String description;
    private double salary;
    private java.sql.Date creationDate;

    public WorkRoles(int roleId, String title, String description, double salary, java.sql.Date creationDate) {
        this.roleId = roleId;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;

    }

    public WorkRoles(String title, String description, double salary, java.sql.Date creationDate) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;// Kontrollera om det är samma objekt
        if (o == null || getClass() != o.getClass()) return false;// Kontrollera att det är samma klass
        WorkRoles workRoles = (WorkRoles) o;// Typomvandling till WorkRoles

        // Jämför unika attribut för att avgöra om objekten är lika
        return roleId == workRoles.roleId && Double.compare(salary, workRoles.salary) == 0 && Objects.equals(title, workRoles.title) && Objects.equals(description, workRoles.description) && Objects.equals(creationDate, workRoles.creationDate);
    }

    @Override
    public int hashCode() {
     // Generera en hash-kod baserat på unika attribut
        return Objects.hash(roleId, title, description, salary, creationDate);
    }

    @Override
    public String toString() {
        return "RoleId = " + roleId + "\n" +
                "Title = " + title + "\n" +
                "Description = " + description + "\n" +
                "Salary = " + salary + "\n" +
                "CreationDate = " + creationDate + "\n";
    }
}
