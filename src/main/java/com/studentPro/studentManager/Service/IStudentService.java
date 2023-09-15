package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.ResponseDto;
import com.studentPro.studentManager.DTO.StudentRequestDTO;
import com.studentPro.studentManager.DTO.StudentResponseDTO;
import com.studentPro.studentManager.Entity.Student;

import java.util.List;

public interface IStudentService {
//    List<Student> getStudents();

//    Student getStudent(int id);

//    List<Student> getStudentByName(String name);

//    ResponseDto postStudents(List<StudentRequestDTO> students);


    ResponseDto postStudents(List<StudentRequestDTO> studentsDTO) throws Exception;

    ResponseDto updateStudent(StudentRequestDTO studentDTO,int id) throws Exception;

//    String deleteStudent(int id);
    String deleteStudent(int id) throws Exception;
    ResponseDto postStudent(StudentRequestDTO studentDTO) throws Exception;

    void print();

//    String deleteAll();

//    List<Student> findByCharInName(String chara);

//    Page<Student> findWithPagination(int offset, int size);

//    List<StudentDTO> getStudents(int pageNo, int pageSize, String sort, String search);

    List<StudentResponseDTO> getStudents(int pageNo, int pageSize, String sort, boolean descending, String search);
}