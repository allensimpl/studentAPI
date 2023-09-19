package com.studentPro.studentManager.Controller;

import Constants.MessageConstants;
import com.studentPro.studentManager.DTO.ResponseDTO;
import com.studentPro.studentManager.DTO.StudentRequestDTO;
import com.studentPro.studentManager.DTO.StudentResponseDTO;
import com.studentPro.studentManager.Service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private IStudentService service;

    @GetMapping
    public List<StudentResponseDTO> getStudents(@RequestParam(name = "pageNo",required = true) Integer pageNo,
                                                @RequestParam(name = "pageSize", required = true) Integer pageSize,
                                                @RequestParam(name = "sort",required = false, defaultValue = "name") String sort,
                                                @RequestParam(name = "descending", required = false, defaultValue = "false") boolean descending,
                                                @RequestParam(name = "search",required = false) String search){
        return service.getStudents(pageNo, pageSize, sort, descending, search);
    }

    @PostMapping
    public ResponseDTO saveStudent(@RequestBody StudentRequestDTO student){
        try{
            return service.postStudent(student);
        }catch (Exception e){
            return new ResponseDTO(MessageConstants.FAILED,200,e.getMessage());
        }
    }

    @PostMapping("/bulk")
    public ResponseDTO saveStudents(@RequestBody List<StudentRequestDTO> students){
        try{
            return service.postStudents(students);
        }catch (Exception e){
            return new ResponseDTO(MessageConstants.FAILED,200,e.getMessage());
        }
    }

    @PutMapping("/:{id}")
    //Validation check if it is there
    public ResponseDTO updateStudent(@RequestBody StudentRequestDTO student, @PathVariable int id){
        try{
            return service.updateStudent(student,id);
        }catch (Exception e){
            return new ResponseDTO(MessageConstants.FAILED,200,e.getMessage());
        }
    }

    @DeleteMapping("/:{id}")
    public String deleteStudent(@PathVariable("id") int id){
        try{
            String message = service.deleteStudent(id);
            return message;
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
