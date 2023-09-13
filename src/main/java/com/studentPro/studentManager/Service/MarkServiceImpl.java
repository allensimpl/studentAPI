package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.Entity.Mark;
import com.studentPro.studentManager.Repository.MarkRepository;
import com.studentPro.studentManager.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarkServiceImpl implements IMarkService{
    @Autowired
    private MarkRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Mark> getAllMarksById(int id){
        return repository.findAllOfId(id);
    }

    @Override
    public List<Mark> getAllMarks() {
        return repository.findAll();
    }

    @Override
    public List<Mark> getMark(int id) {
        return repository.findAllOfId(id);
    }

    @Override
    public List<Mark> getMarkSubjects(String subject) {
        return repository.findBySubject(subject);
    }
    @Override
    public Mark postMark(Mark mark) throws Exception {
        if (!studentRepository.containsID(mark.getRollNo())) {
            throw new Exception("User doesn't exist in Student Table!");
        }
        boolean test = repository.isNEW(mark.getRollNo(),mark.getSubject());
        if(repository.isNEW(mark.getRollNo(),mark.getSubject())){

            throw new Exception("User's current marks already Inserted!");
        }
//        if(!studentRepository.isNEW(mark.getRollNo(),mark.getSubject())){
//            throw new Exception("User's current marks already Inserted!");
//        }
        return repository.save(mark);
    }

    @Override
    public List<Mark> postMarks(List<Mark> marks) {
        return repository.saveAll(marks);
    }

    @Override
    public Mark updateMarkItem(Mark mark) {
        Mark updatingMarkItem = repository.findById(mark.getsId()).orElse(null);
        updatingMarkItem.setMark(mark.getMark());
        updatingMarkItem.setSubject(mark.getSubject());
        updatingMarkItem.setRollNo(mark.getRollNo());
        return updatingMarkItem;
    }

    @Override
    public String deleteMark(int id, String subject) {
        repository.deleteMark(id,subject);
        return "Deleted"+id;
    }
    @Override
    public String deleteAll(){
        repository.deleteAll();
        return "Deleted Everything";
    }
    @Override
    public String deleteById(int id){
        repository.deleteById(id);
        return id+" ID Deleted";
    }
}
