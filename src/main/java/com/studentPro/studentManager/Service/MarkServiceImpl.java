package com.studentPro.studentManager.Service;

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

    public List<MarkDTO> dtoConverter(List<Mark> markList){
        List<MarkDTO> markDTOList = new ArrayList<>();
        for(Mark m:markList){
            markDTOList.add(new MarkDTO(m.getMarkId(),m.getStudentID(),m.getStudentID(),m.getMark()));
        }
        return markDTOList;
    }

    @Override
    public List<MarkDTO> getMarks(int pageNo, int pageSize, int subjectID, String sort, boolean descending){
        int offset = (pageNo - 1) * pageSize;
        Pageable pagingParams = PageRequest.of(offset, pageSize, JpaSort.unsafe(descending ? Sort.Direction.DESC : Sort.Direction.ASC, "(" + sort + ")"));
        Page<Mark> allMarksSearched = repository.getAllMarks(subjectID,pagingParams);
        return dtoConverter(allMarksSearched.getContent());
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
        if (!studentRepository.containsID(mark.getSubjectID())) {
            throw new Exception("User doesn't exist in Student Table!");
        }
        boolean test = repository.isNEW(mark.getStudentID(),mark.getSubjectID());
        if(repository.isNEW(mark.getStudentID(),mark.getSubjectID())){
            throw new Exception("User's current marks already Inserted!");
        }
        Mark newData = new Mark(mark.getStudentID(),mark.getSubjectID(),mark.getMark());
        return repository.save(newData);
    }

    @Override
    public List<MarkResponseDTO> postMarks(List<MarkRequestDTO> marks) throws Exception {
        List<Integer> studentIDList = new ArrayList<>();
        for(MarkRequestDTO eachMark:marks){
            studentIDList.add(eachMark.getStudentID());
        }
//        boolean test = studentRepository.containsIDList(studentIDList);
//        if(!studentRepository.containsIDList(studentIDList)){
//            throw Exception("User doesn't exist");
//        }
//        List<Integer> subjectIDList = new ArrayList<>();
//        for(MarkRequestDTO eachMark:marks){
//            subjectIDList.add(eachMark.getSubjectID());
//        }
//        if(repository.isNEW(studentIDList,subjectIDList)){
//            throw new Exception("User marks already inserted");
//        }


        for(MarkRequestDTO eachMark:marks){
            if(!studentRepository.containsID(eachMark.getStudentID())){
                throw new Exception("User"+eachMark.getStudentID()+" doesn't exist");
            }
            if(repository.isNEW(eachMark.getStudentID(),eachMark.getSubjectID())){
                throw new Exception("User's current marks already Inserted");
            }
        }
        List<Mark> marksData = new ArrayList<>();
        for(MarkRequestDTO eachMark:marks){
            marksData.add(new Mark(eachMark.getStudentID(),eachMark.getSubjectID(), eachMark.getMark()));
        }
        List<Mark> markResponse = repository.saveAll(marksData);
        List<MarkResponseDTO> markResponseDTOList = new ArrayList<>();
        for(Mark m:markResponse){
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
        MarkResponseDTO data = new MarkResponseDTO(updatingMarkItem.getMarkId(),updatingMarkItem.getSubject(),updatingMarkItem.getStudentID(), updatingMarkItem.getMark());
        return new ResponseDTO("success",200,data);
    }

//    @Override
//    public String deleteMark(int id, int subjectID) {
//        repository.deleteMark(id,subjectID);
//        return "Deleted"+id;
//    }
//
//    @Override
//    public String deleteAll(){
//        repository.deleteAll();
//        return "Deleted Everything";
//    }

    @Override
    public String deleteById(int id) throws Exception {
        if(repository.getIDCount(id)<1){
            throw new Exception("ID doesn't exist");
        }
        repository.deleteById(id);
        return id+" ID Deleted";
    }
}
