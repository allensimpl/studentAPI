package com.studentPro.studentManager.Service;

import com.studentPro.studentManager.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResultServiceImpl implements IResultService{
    @Autowired
    private ResultRepository repository;
    @Override
    public List<Result> getResults() {
        return repository.getResults();
    }
}
