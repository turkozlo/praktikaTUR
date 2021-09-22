package tur.service.impl;

import tur.dao.TeacherDao;
import tur.domain.Teacher;
import tur.service.TeacherService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAll();
    }

    @Override
    public void printAllTeachersToConsole() {
        System.out.println(teacherDao.getAll());
    }

    @Override
    public void writeTeacherDataToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(teacherDao.getAll().toString());
        writer.close();
    }

    @Override
    public boolean deleteTeacher(Teacher teacher) {
        return teacherDao.delete(teacher);
    }
}
