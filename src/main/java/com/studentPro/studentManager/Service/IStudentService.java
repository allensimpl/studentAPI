package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.Entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IStudentService {
    List<Student> getStudents();

    Student getStudent(int id);

    List<Student> getStudentByName(String name);

    List<Student> postStudents(List<Student> students);

    Student postStudent(Student student);

    Student updateStudent(Student student);

    String deleteStudent(int id);

    String deleteAll();

    List<Student> findByCharInName(String chara);

    Page<Student> findWithPagination(int offset, int size);
}