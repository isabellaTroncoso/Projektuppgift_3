package se.bella.projektarbete.Metoder;

import java.sql.SQLException;
import java.util.List;

public interface WorkRolesDAO {

    void insertWorkRole(WorkRoles workRole) throws SQLException;
    WorkRoles getWorkRole(int roleId) throws SQLException;
    List<WorkRoles> getAllWorkRoles() throws SQLException;
    void updateWorkRole(WorkRoles workRole) throws SQLException;
    void deleteWorkRole(WorkRoles workRole) throws SQLException;
}
