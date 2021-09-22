package tur;


import tur.dao.StudentDao;
import tur.dao.TeacherDao;
import tur.service.StudentService;
import tur.service.TeacherService;
import tur.service.impl.StudentServiceImpl;
import tur.service.impl.TeacherServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataSource dataSource = new DataSource("src/main/resources/database.properties");
        TeacherService teacherService = new TeacherServiceImpl(new TeacherDao(dataSource));
        StudentService studentService = new StudentServiceImpl(new StudentDao(dataSource));
        teacherService.printAllTeachersToConsole();
        studentService.printAllStudentsToConsole();
        teacherService.writeTeacherDataToFile("F:\\dat\\1.txt");
        studentService.writeStudentDataToFile("F:\\dat\\2.txt");
        studentService.deleteStudent(studentService.getAllStudents().get(0));
    }
}
