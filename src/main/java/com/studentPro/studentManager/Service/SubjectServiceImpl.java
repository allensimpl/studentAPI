package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.DTO.SubjectRequestDTO;
import com.studentPro.studentManager.DTO.SubjectResponseDTO;
import com.studentPro.studentManager.Entity.Student;
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
    public SubjectResponseDTO subjectResponseDTOMaker(Subject subject){
        return new SubjectResponseDTO(subject.getId(),subject.getSubject());
    }
    public List<SubjectResponseDTO> subjectResponseDTOListMaker(List<Subject> subjects){
        List<SubjectResponseDTO> subjectDTOs = new ArrayList<>();
        for(Subject eachSubject:subjects){
            subjectDTOs.add(new SubjectResponseDTO(eachSubject.getId(),eachSubject.getSubject()));
        }
        return subjectDTOs;
    }
    @Override
    public ResponseDTO getSubjects(){
        List<Subject> subjects = repository.findAll();
        return new ResponseDTO("success",200,subjects);
    }
    @Override
    public ResponseDTO getSubject(int id){
        Subject subject = repository.findById(id).orElse(null);
        if(subject == null){
            return new ResponseDTO("Failed| No such ID exist",500,subject);
        }
        return new ResponseDTO("Success",200,subject);
    }
    @Override
    public ResponseDTO addSubject(SubjectRequestDTO subject) throws Exception {
        if(repository.containsSubject(subject.getSubject())>0){
            throw new Exception("The ID Already exists");
        }
        Subject newSubject = new Subject(subject.getSubject());
        repository.save(newSubject);
        return new ResponseDTO("success",200,newSubject);
    }
    @Override
    public Object addSubjects(List<SubjectRequestDTO> subjectsRequest) throws Exception {
        List<String> subjectsList = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        for(SubjectRequestDTO eachRequest:subjectsRequest){
            subjectsList.add(eachRequest.getSubject());
        }
        if(repository.containsSubjects(subjectsList)>0){
            throw new Exception("Subject Already Exists");
        }
        for(SubjectRequestDTO eachSubjectRequest:subjectsRequest){
            subjects.add(new Subject(eachSubjectRequest.getSubject()));
        }
        repository.saveAll(subjects);
        List<SubjectResponseDTO> subjectResponseDTO = subjectResponseDTOListMaker(subjects);
        return new ResponseDTO("success",200,subjectResponseDTO);
    }


    @Override
    public ResponseDTO updateStudent(SubjectRequestDTO subjectDTO, int id) throws Exception{
        Subject updatingSubject = (Subject) repository.findById(id).orElse(null);
        if(updatingSubject == null){
            throw new Exception("Failed to get ID");
        }
        updatingSubject.setSubject(subjectDTO.getSubject());
        Subject data = (Subject) repository.save(updatingSubject);
        SubjectResponseDTO responseDTO = new SubjectResponseDTO(data.getId(),data.getSubject());
        return new ResponseDTO("success",200,responseDTO);
    }

    @Override
    public String deleteStudent(int id) throws Exception{
        if(repository.containsID(id)<1){
            throw new Exception("This ID doesn't exist");
        }
        repository.deleteById(id);
        return "Successfully Deleted"+id;
    }
}
