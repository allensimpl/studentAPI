package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.ResponseDto;
import com.studentPro.studentManager.DTO.StudentRequestDTO;
import com.studentPro.studentManager.DTO.StudentResponseDTO;
import com.studentPro.studentManager.Entity.Student;

import java.util.List;

public interface IStudentService {



    ResponseDto postStudents(List<StudentRequestDTO> studentsDTO) throws Exception;

    ResponseDto updateStudent(StudentRequestDTO studentDTO,int id) throws Exception;

    String deleteStudent(int id) throws Exception;
    ResponseDto postStudent(StudentRequestDTO studentDTO) throws Exception;

    List<StudentResponseDTO> getStudents(int pageNo, int pageSize, String sort, boolean descending, String search);
}