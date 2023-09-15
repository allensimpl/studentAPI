package com.studentPro.studentManager.Controller;


import com.studentPro.studentManager.DTO.MarkDTO;
import com.studentPro.studentManager.DTO.ResponseDto;
import com.studentPro.studentManager.Entity.Mark;
import com.studentPro.studentManager.Service.MarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("marks")
public class MarkController {
    @Autowired
    private MarkServiceImpl service;

//    @GetMapping
//    public List<Mark> getMarksAll(){
//        return service.getAllMarks();
//    }

    @GetMapping("/:{id}")
    public List<Mark> getMark(@PathVariable("id") int id){
        return service.getAllMarksById(id);
    }


//    @GetMapping("/BySubject:{subject}")
//    public List<Mark> getMarksSubject(@PathVariable("subject") String subject){
//        return service.getMarkSubjects(subject);
//    }

    @GetMapping
    public List<MarkDTO> getAllStudents(
                                    @RequestParam(name = "pageNo",required = true) int pageNo,
                                    @RequestParam(name = "pageSize",required = true) int pageSize,
//                                    @RequestParam(name = "id",required = false) int id,
                                     @RequestParam(name = "subject",required = true)String subject,
                                     @RequestParam(name = "sort",required = false,defaultValue = "mark") String sort,
                                     @RequestParam(name = "descending", required = false, defaultValue = "false") boolean descending) {
        return service.getMarks(pageNo,pageSize,subject,sort,descending);
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
    public ResponseDto addMarks(@RequestBody List<Mark> marks)  {
        try{
            List<Mark> data = service.postMarks(marks);
            return new ResponseDto("Success Bulk",200,data);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error In Bulk Adding");
            return new ResponseDto(e.getMessage(),500,null);
        }
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
