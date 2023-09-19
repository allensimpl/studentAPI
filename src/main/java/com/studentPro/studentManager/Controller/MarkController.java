package com.studentPro.studentManager.Controller;


import Constants.MessageConstants;
import com.studentPro.studentManager.DTO.MarkDTO;
import com.studentPro.studentManager.DTO.MarkRequestDTO;
import com.studentPro.studentManager.DTO.MarkResponseDTO;
import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.Entity.Mark;
import com.studentPro.studentManager.Service.IMarkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("marks")
public class MarkController {

    private IMarkService service;

    public MarkController(IMarkService service) {
        this.service = service;
    }

    @GetMapping("/:{id}")
    public List<Mark> getMark(@PathVariable("id") int id){
        return service.getAllMarksById(id);
    }

    @GetMapping
    public List<MarkDTO> getAllMarks(
                                    @RequestParam(name = "pageNo",required = true) int pageNo,
                                    @RequestParam(name = "pageSize",required = true) int pageSize,
                                     @RequestParam(name = "subjectID",required = true)int subjectID,
                                     @RequestParam(name = "sort",required = false,defaultValue = "mark") String sort,
                                     @RequestParam(name = "descending", required = false, defaultValue = "false") boolean descending) {
        return service.getMarks(pageNo,pageSize,subjectID,sort,descending);
    }


    @PostMapping
    public ResponseDTO addMark(@RequestBody MarkRequestDTO mark){
        try{
            Mark data = service.postMark(mark);
            return new ResponseDTO(MessageConstants.SUCCESS, 200, data);
        }catch (Exception e) {
            return new ResponseDTO(e.getMessage(), 500, null);
        }
    }

    @PostMapping("/bulk")
    public ResponseDTO addMarks(@RequestBody List<MarkRequestDTO> marks)  {
        try{
            List<MarkResponseDTO> data = service.postMarks(marks);
            return new ResponseDTO("Success Bulk",200,data);
        }catch(Exception e){
            return new ResponseDTO(e.getMessage(),500,null);
        }
    }

    @PutMapping("/:{id}")
    public ResponseDTO updateMark(@RequestBody MarkRequestDTO mark,@PathVariable("id") int id){
        return service.updateMarkItem(mark,id);
    }


    @DeleteMapping("/:{id}")
    public String deleteById(@PathVariable("id") int id){
        try{
            return service.deleteById(id);
        }catch(Exception e){
            return e.getMessage();
        }
    }

}
