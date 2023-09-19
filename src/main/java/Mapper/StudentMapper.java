package Mapper;

import com.studentPro.studentManager.DTO.MarkDTO;
import com.studentPro.studentManager.Entity.Mark;

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
}
