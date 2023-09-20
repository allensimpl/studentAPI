package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.DTO.StudentExcelDto;
import com.studentPro.studentManager.Repository.StudentRepository;
import com.studentPro.studentManager.view.IStudentMarkView;
import com.studentPro.studentManager.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultServiceImpl implements IResultService{
    @Autowired
    private StudentRepository repository;
    @Override
    public List<StudentExcelDto> getResults() {
        List<IStudentMarkView> studentMarkViews = repository.getResults();
        return StudentMapper.studentViewToDtoConvertor(repository.getResults());
    }
}
