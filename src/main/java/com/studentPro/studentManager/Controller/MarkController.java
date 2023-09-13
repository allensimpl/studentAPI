package com.studentPro.studentManager.Controller;


import com.studentPro.studentManager.DTO.ResponseDto;
import com.studentPro.studentManager.Entity.Mark;
import com.studentPro.studentManager.Entity.Student;
import com.studentPro.studentManager.Service.MarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;

@RestController
@RequestMapping("marks")
public class MarkController {
    @Autowired
    private MarkServiceImpl service;
    @GetMapping
    public List<Mark> getMarksAll(){
        return service.getAllMarks();
    }
    @GetMapping("/:{id}")
    public List<Mark> getMark(@PathVariable("id") int id){
        return service.getAllMarksById(id);
    }
    @GetMapping("/BySubject:{subject}")
    public List<Mark> getMarksSubject(@PathVariable("subject") String subject){
        return service.getMarkSubjects(subject);
    }
    @PostMapping
    public ResponseDto addMark(@RequestBody Mark mark){
        try{
            Mark data = service.postMark(mark);
            return new ResponseDto("Success", 200, data);
        }catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error in Adding");
            return new ResponseDto(e.getMessage(), 500, null);
        }
    }
    @PostMapping("/bulk")
    public List<Mark> addMarks(@RequestBody List<Mark> marks){
        return service.postMarks(marks);
    }
    @PutMapping
    public Mark updateMark(Mark mark){
        return service.updateMarkItem(mark);
    }
    @DeleteMapping("/all")
    public String deleteAll(){
        return service.deleteAll();
    }
    @DeleteMapping("/")
    public String deleteMark(@RequestParam int id, String subject){
        return service.deleteMark(id,subject);
    }
    @DeleteMapping("/:{id}")
    public String deleteById(@PathVariable("id") int id){
        return service.deleteById(id);
    }
}
