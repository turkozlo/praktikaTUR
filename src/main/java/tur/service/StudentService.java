package tur.service;

import tur.domain.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void printAllStudentsToConsole();

    void writeStudentDataToFile(String fileName) throws IOException;

    boolean deleteStudent(Student student);
}
