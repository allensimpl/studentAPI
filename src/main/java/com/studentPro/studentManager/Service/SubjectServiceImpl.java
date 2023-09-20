package com.studentPro.studentManager.Service;

import Constants.MessageConstants;
import mapper.StudentMapper;
import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.DTO.SubjectRequestDTO;
import com.studentPro.studentManager.DTO.SubjectResponseDTO;
import com.studentPro.studentManager.Entity.Subject;
import com.studentPro.studentManager.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService{
    @Autowired
    private SubjectRepository repository;

    @Override
    public ResponseDTO getSubjects(){
        List<Subject> subjects = repository.findAll();
        return new ResponseDTO(MessageConstants.SUCCESS,200,subjects);
    }

    @Override
    public ResponseDTO getSubject(int id){
        Subject subject = repository.findById(id).orElse(null);
        if(subject == null){
            return new ResponseDTO(MessageConstants.FAILED_NO_ID,500,subject);
        }
        return new ResponseDTO(MessageConstants.SUCCESS,200,subject);
    }

    @Override
    public ResponseDTO addSubject(SubjectRequestDTO subject) throws Exception {
        if(repository.containsSubject(subject.getSubject())>0){
            throw new Exception(MessageConstants.FAILED_ID_EXISTS);
        }
        Subject newSubject = new Subject(subject.getSubject());
        repository.save(newSubject);
        return new ResponseDTO(MessageConstants.SUCCESS,200,newSubject);
    }

    @Override
    public Object addSubjects(List<SubjectRequestDTO> subjectsRequest) throws Exception {
        List<String> subjectsList = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        for(SubjectRequestDTO eachRequest:subjectsRequest){
            subjectsList.add(eachRequest.getSubject());
        }
        if(repository.containsSubjects(subjectsList)>0){
            throw new Exception(MessageConstants.SUB_ALREADY_EXIST);
        }
        for(SubjectRequestDTO eachSubjectRequest:subjectsRequest){
            subjects.add(new Subject(eachSubjectRequest.getSubject()));
        }
        repository.saveAll(subjects);
        List<SubjectResponseDTO> subjectResponseDTO = StudentMapper.subjectResponseDTOListMaker(subjects);
        return new ResponseDTO(MessageConstants.SUCCESS,200,subjectResponseDTO);
    }

    @Override
    public ResponseDTO updateStudent(SubjectRequestDTO subjectDTO, int id) throws Exception{
        Subject updatingSubject = (Subject) repository.findById(id).orElse(null);
        if(updatingSubject == null){
            throw new Exception(MessageConstants.FAILED_TO_GET_ID);
        }
        updatingSubject.setSubject(subjectDTO.getSubject());
        Subject data = (Subject) repository.save(updatingSubject);
        SubjectResponseDTO responseDTO = new SubjectResponseDTO(data.getId(),data.getSubject());
        return new ResponseDTO(MessageConstants.SUCCESS,200,responseDTO);
    }

    @Override
    public String deleteStudent(int id) throws Exception{
        if(repository.containsID(id)<1){
            throw new Exception(MessageConstants.FAILED_NO_ID);
        }
        repository.deleteById(id);
        return MessageConstants.successfulDeletion(id);
    }
}
