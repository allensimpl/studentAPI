package com.studentPro.studentManager.mapper;

import com.studentPro.studentManager.DTO.MarkDTO;
import com.studentPro.studentManager.DTO.StudentExcelDto;
import com.studentPro.studentManager.DTO.SubjectResponseDTO;
import com.studentPro.studentManager.Entity.Mark;
import com.studentPro.studentManager.Entity.Subject;
import com.studentPro.studentManager.view.IStudentMarkView;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
    public static List<MarkDTO> dtoConverter(List<Mark> markList){
        List<MarkDTO> markDTOList = new ArrayList<>();
        for(Mark m:markList){
            markDTOList.add(new MarkDTO(m.getMarkId(),m.getStudentID(),m.getStudentID(),m.getMark()));
        }
        return markDTOList;
    }

    public static SubjectResponseDTO subjectResponseDTOMaker(Subject subject){
        return new SubjectResponseDTO(subject.getId(),subject.getSubject());
    }

    public static List<SubjectResponseDTO> subjectResponseDTOListMaker(List<Subject> subjects){
        List<SubjectResponseDTO> subjectDTOs = new ArrayList<>();
        for(Subject eachSubject:subjects){
            subjectDTOs.add(new SubjectResponseDTO(eachSubject.getId(),eachSubject.getSubject()));
        }
        return subjectDTOs;
    }

    public static List<StudentExcelDto> studentViewToDtoConvertor(List<IStudentMarkView> studentMarkViewList){
        List<StudentExcelDto> studentExcelDtos = new ArrayList<>();
        for(IStudentMarkView view : studentMarkViewList){
            StudentExcelDto dto = new StudentExcelDto();
            dto.setName(view.getStudentName());
            dto.setMark(view.getMark());
            dto.setSubject(view.getSubject());
            studentExcelDtos.add(dto);
        }
        return studentExcelDtos;
    }
}
