package tur.dao;

import tur.DataSource;
import tur.domain.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao implements Dao<Teacher> {
    private final DataSource dataSource;

    public TeacherDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Teacher> getAll() {
        Teacher teacher;
        List<Teacher> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT firstName, lastName, subjectName FROM teachers")
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                teacher = new Teacher();
                while (resultSet.next()) {
                    teacher = new Teacher();
                    teacher.setFirstName(resultSet.getString("firstName"));
                    teacher.setLastName(resultSet.getString("lastName"));
                    teacher.setSubjectName(resultSet.getString("subjectName"));
                    result.add(teacher);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(Teacher teacher) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM teachers where firstName=? AND " +
                     "lastName=? AND subjectName=?")) {
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getSubjectName());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }
}
