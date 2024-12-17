package se.bella.projektarbete.Metoder;

import se.bella.projektarbete.JDBC.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkRolesImpl implements WorkRolesDAO {

    @Override
    public void insertWorkRole(WorkRoles workRole) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = """
                    INSERT INTO work_role(title, description, salary, creation_date)
                    VALUES (?, ?, ?, ?)
                    """;
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, workRole.getTitle());
            pstmt.setString(2, workRole.getDescription());
            pstmt.setDouble(3, workRole.getSalary());
            pstmt.setDate(4, workRole.getCreationDate());


            pstmt.executeUpdate();
            System.out.println("Adding...");
            JDBCUtil.commit(conn);

        } catch (SQLException e) {
            e.printStackTrace();
            JDBCUtil.rollback(conn);
            throw e;

        } finally {
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);

        }
    }

    @Override
    public WorkRoles getWorkRole(int roleId) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        WorkRoles workrole = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = """
                    SELECT * FROM work_role WHERE role_id = ?
                    """;
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, roleId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                roleId = rs.getInt("role_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                double salary = rs.getDouble("salary");
                Date creationDate = rs.getDate("creation_date");

                workrole = new WorkRoles(roleId, title, description, salary, creationDate);
            } else {
                System.out.println("No role with id " + roleId + " found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching person", e);
        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
        return workrole;

    }

    @Override
    public List<WorkRoles> getAllWorkRoles() throws SQLException {
        List<WorkRoles> roleList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM work_role");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Double salary = rs.getDouble("salary");
                Date creationDate = rs.getDate("creation_date");

                WorkRoles workroles = new WorkRoles(roleId, title, description, salary, creationDate);
                roleList.add(workroles);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching all persons", e);

        } finally {
            JDBCUtil.closeResultSet(rs);
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
        return roleList;
    }

    @Override
    public void updateWorkRole(WorkRoles workRole) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = """
                    UPDATE work_role SET title = ?, description = ?, salary = ?, creation_date = ? 
                    WHERE role_id = ?
                    """;
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, workRole.getTitle());
            pstmt.setString(2, workRole.getDescription());
            pstmt.setDouble(3, workRole.getSalary());
            pstmt.setDate(4, workRole.getCreationDate());
            pstmt.setInt(5, workRole.getRoleId());

            int roles = pstmt.executeUpdate();
            JDBCUtil.commit(conn);

            if (roles == 0) {
                System.out.println("No role with id " + workRole.getRoleId() + " found");
            } else {
                System.out.println("Work role exits");
                System.out.println("Uppdating...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JDBCUtil.rollback(conn);
            throw e;

        } finally {
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Override
    public void deleteWorkRole(WorkRoles workRole) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = """
                    DELETE FROM work_role WHERE role_id = ?
                    """;
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, workRole.getRoleId());

            int roles = pstmt.executeUpdate();
            JDBCUtil.commit(conn);

            if (roles == 0) {
                System.out.println("No role with id " + workRole.getRoleId() + " found");
            } else {
                System.out.println("Work role exits");
                System.out.println("Deleting...");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JDBCUtil.rollback(conn);
            throw e;

        } finally {
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }

    }
}
