package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.MarkDTO;
import com.studentPro.studentManager.Entity.Mark;

import java.util.List;

public interface IMarkService {

//    List<Mark> getMark(int id);
//
//    List<Mark> getAllMarks();


//    List<Mark> getMarks(int pageNo, int pageSize, int id, String subject);


    List<MarkDTO> getMarks(int pageNo, int pageSize, String subject, String sort, boolean descending);

    List<Mark> getAllMarksById(int id);

//    List<Mark> getMarkSubjects(String subject);

    List<Mark> getMark(int id);

    Mark postMark(Mark mark) throws Exception;
    List<Mark> postMarks(List<Mark> marks) throws Exception;
    Mark updateMarkItem(Mark mark);
    String deleteMark(int id,String subject);

    String deleteAll();

    String deleteById(int id);
}