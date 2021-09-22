package tur.dao;

import tur.DataSource;
import tur.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {
    private final DataSource dataSource;

    public StudentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Student> getAll() {
        Student student;
        List<Student> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT firstName, lastName, courseNumber, groupName FROM students")
        ) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                student = new Student();
                while (resultSet.next()) {
                    student = new Student();
                    student.setCourseNumber(resultSet.getInt("courseNumber"));
                    student.setFirstName(resultSet.getString("firstName"));
                    student.setLastName(resultSet.getString("lastName"));
                    student.setGroupName(resultSet.getString("groupName"));
                    result.add(student);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(Student student) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students where firstName=? AND " +
                     "lastName=? AND courseNumber=? AND groupName=?")) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getCourseNumber());
            preparedStatement.setString(4, student.getGroupName());
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

}
