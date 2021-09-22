package tur.service.impl;

import tur.dao.StudentDao;
import tur.domain.Student;
import tur.service.StudentService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAll();
    }

    @Override
    public void printAllStudentsToConsole() {
        System.out.println(studentDao.getAll());
    }

    @Override
    public void writeStudentDataToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(studentDao.getAll().toString());
        writer.close();
    }

    @Override
    public boolean deleteStudent(Student student) {
        return studentDao.delete(student);
    }
}
