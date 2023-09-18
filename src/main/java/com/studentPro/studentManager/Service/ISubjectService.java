package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.DTO.SubjectRequestDTO;
import com.studentPro.studentManager.DTO.SubjectResponseDTO;

import java.util.List;

public interface ISubjectService {
    public abstract ResponseDTO getSubjects();

    public abstract ResponseDTO getSubject(int id);

    public abstract ResponseDTO addSubject(SubjectRequestDTO subject) throws Exception;

    public Object addSubjects(List<SubjectRequestDTO> subjectsRequest) throws Exception;


    public ResponseDTO updateStudent(SubjectRequestDTO subjectDTO, int id) throws Exception;
    public String deleteStudent(int id) throws Exception;
}
