package tur.service;

import tur.domain.Teacher;

import java.io.IOException;
import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    void printAllTeachersToConsole();

    void writeTeacherDataToFile(String fileName) throws IOException;

    boolean deleteTeacher(Teacher teacher);
}
