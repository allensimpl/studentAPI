package com.studentPro.studentManager.Service;

import Constants.MessageConstants;
import mapper.StudentMapper;
import com.studentPro.studentManager.DTO.MarkDTO;
import com.studentPro.studentManager.DTO.MarkRequestDTO;
import com.studentPro.studentManager.DTO.MarkResponseDTO;
import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.Entity.Mark;
import com.studentPro.studentManager.Repository.MarkRepository;
import com.studentPro.studentManager.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarkServiceImpl implements IMarkService{
    @Autowired
    private MarkRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<MarkDTO> getMarks(int pageNo, int pageSize, int subjectID, String sort, boolean descending){
        int offset = (pageNo - 1) * pageSize;
        Pageable pagingParams = PageRequest.of(offset, pageSize, JpaSort.unsafe(descending ? Sort.Direction.DESC : Sort.Direction.ASC, "(" + sort + ")"));
        Page<Mark> allMarksSearched = repository.getAllMarks(subjectID,pagingParams);
        return StudentMapper.dtoConverter(allMarksSearched.getContent());
    }

    @Override
    public List<Mark> getAllMarksById(int id){
        return repository.findAllOfId(id);
    }

    @Override
    public List<Mark> getMark(int id) {
        return repository.findAllOfId(id);
    }

    @Override
    public Mark postMark(MarkRequestDTO mark) throws Exception {
        if (studentRepository.containsID(mark.getStudentID())<1) {
            throw new Exception(MessageConstants.FAILED_NO_ID);
        }
        if(repository.isNEW(mark.getStudentID(),mark.getSubjectID())>0){
            throw new Exception(MessageConstants.ALREADY_EXISTS);
        }
        Mark newData = new Mark(mark.getStudentID(),mark.getSubjectID(),mark.getMark());
        //move to mapper class
        return repository.save(newData);
    }

    @Override
    public List<MarkResponseDTO> postMarks(List<MarkRequestDTO> marks) throws Exception {
        List<Integer> studentIDList = new ArrayList<>();
        for(MarkRequestDTO eachMark:marks){
            studentIDList.add(eachMark.getStudentID());
        }
        if(studentRepository.containsIDList(studentIDList)<1){
            throw new Exception(MessageConstants.FAILED_NO_ID);
        }
        List<String> uniqueIds = (List<String>) marks.stream().map(m -> (Integer.toString( m.getStudentID())+Integer.toString(m.getSubjectID()))).toList();
        List<String> existingids = repository.getStudentMarkList();
        for (String id : uniqueIds){
            if(existingids.contains(id)){
                throw new Exception(MessageConstants.AlreadyExists(id));
            }
        }
        List<Mark> marksData = new ArrayList<>();
        for(MarkRequestDTO eachMark:marks){
            marksData.add(new Mark(eachMark.getStudentID(),eachMark.getSubjectID(), eachMark.getMark()));
            // move to mapper class
        }
        List<Mark> markResponse = repository.saveAll(marksData);
        List<MarkResponseDTO> markResponseDTOList = new ArrayList<>();
        for(Mark m:markResponse){
            // move to mapper class
            markResponseDTOList.add(new MarkResponseDTO(m.getMarkId(),m.getStudentID(),m.getSubject(),m.getMark()));
        }
        return markResponseDTOList;
    }

    @Override
    public ResponseDTO updateMarkItem(MarkRequestDTO mark, int id) {
        Mark updatingMarkItem = repository.findById(id).orElse(null);
        updatingMarkItem.setSubject(mark.getSubjectID());
        updatingMarkItem.setStudentID(mark.getStudentID());
        updatingMarkItem.setMark(mark.getMark());
        //move to mapper
        MarkResponseDTO data = new MarkResponseDTO(updatingMarkItem.getMarkId(),updatingMarkItem.getSubject(),updatingMarkItem.getStudentID(), updatingMarkItem.getMark());
        return new ResponseDTO(MessageConstants.SUCCESS,200,data);
        //move to constants
    }

    @Override
    public String deleteById(int id) throws Exception {
        if(repository.getIDCount(id)<1){
            throw new Exception(MessageConstants.FAILED_NO_ID);
        }
        repository.deleteById(id);
        return MessageConstants.successfulDeletion(id);
    }

    public List<Mark> listAll(){
        return repository.findAll();
    }
}
