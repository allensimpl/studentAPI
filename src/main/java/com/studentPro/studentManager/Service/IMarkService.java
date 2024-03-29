package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.MarkDTO;
import com.studentPro.studentManager.DTO.MarkRequestDTO;
import com.studentPro.studentManager.DTO.MarkResponseDTO;
import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.Entity.Mark;

import java.util.List;

public interface IMarkService {

//    List<Mark> getMark(int id);
//
//    List<Mark> getAllMarks();


//    List<Mark> getMarks(int pageNo, int pageSize, int id, String subject);

    List<MarkDTO> getMarks(int pageNo, int pageSize, int subjectID, String sort, boolean descending);
//    List<MarkDTO> getMarks(int pageNo, int pageSize, String subject, String sort, boolean descending);

    List<Mark> getAllMarksById(int id);

//    List<Mark> getMarkSubjects(String subject);

    List<Mark> getMark(int id);

//    Mark postMark(Mark mark) throws Exception;

    Mark postMark(MarkRequestDTO mark) throws Exception;

    List<MarkResponseDTO> postMarks(List<MarkRequestDTO> marks) throws Exception;


    ResponseDTO updateMarkItem(MarkRequestDTO mark, int id);
//    String deleteMark(int id,String subject);

//    Mark updateMarkItem(MarkRequestDTO mark, int id);

//    String deleteMark(int id, int subjectID);

//    String deleteAll();

    String deleteById(int id) throws Exception;
}