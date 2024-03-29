package com.studentPro.studentManager.Controller;

import com.studentPro.studentManager.Constants.MessageConstants;
import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.DTO.SubjectRequestDTO;
import com.studentPro.studentManager.Service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    private ISubjectService service;
    @GetMapping
    public ResponseDTO getSubjects(){
        return service.getSubjects();
    }

    @GetMapping("/:{id}")
    public Object getSubject(@PathVariable("id") int id){
        return service.getSubject(id);
    }
    @PostMapping
    public Object addSubject(@RequestBody SubjectRequestDTO student){
        try{
            return service.addSubject(student);
        } catch (Exception e) {
            return new ResponseDTO(MessageConstants.FAILED,500,e.getMessage());
        }
    }
    @PostMapping("/bulk")
    public Object addSubjects(@RequestBody List<SubjectRequestDTO> students){
        try{
            return service.addSubjects(students);
        }catch (Exception e){
            return new ResponseDTO(MessageConstants.FAILED,500,e.getMessage());
        }
    }
    @PutMapping("/:{id}")
    public Object updateStudent(@RequestBody SubjectRequestDTO student,@PathVariable("id") int id){
        try{
            return service.updateStudent(student,id);
        } catch (Exception e) {
            return new ResponseDTO(MessageConstants.FAILED,500,e.getMessage());
        }
    }
    @DeleteMapping("/:{id}")
    public Object deleteStudent(@PathVariable("id") int id){
        try{
            return service.deleteStudent(id);
        }catch (Exception e){
            return new ResponseDTO(MessageConstants.FAILED_NO_ID,500,e.getMessage());
        }
    }
}
