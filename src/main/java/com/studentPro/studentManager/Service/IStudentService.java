package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.DTO.StudentRequestDTO;
import com.studentPro.studentManager.DTO.StudentResponseDTO;

import java.util.List;

public interface IStudentService {

    ResponseDTO postStudents(List<StudentRequestDTO> studentsDTO) throws Exception;

    ResponseDTO updateStudent(StudentRequestDTO studentDTO, int id) throws Exception;

    String deleteStudent(int id) throws Exception;
    ResponseDTO postStudent(StudentRequestDTO studentDTO) throws Exception;

    List<StudentResponseDTO> getStudents(int pageNo, int pageSize, String sort, boolean descending, String search);
}