package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.Entity.Mark;
import com.studentPro.studentManager.Entity.Student;
import org.springframework.cglib.reflect.ConstructorDelegate;

import java.util.List;

public interface IMarkService {

    List<Mark> getMark(int id);

    List<Mark> getAllMarks();


    List<Mark> getAllMarksById(int id);

    List<Mark> getMarkSubjects(String subject);

    Mark postMark(Mark mark) throws Exception;
    List<Mark> postMarks(List<Mark> marks);
    Mark updateMarkItem(Mark mark);
    String deleteMark(int id,String subject);

    String deleteAll();

    String deleteById(int id);
}