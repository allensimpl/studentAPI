package com.studentPro.studentManager.Controller;

import com.studentPro.studentManager.DTO.StudentDTO;
import com.studentPro.studentManager.Entity.Student;
import com.studentPro.studentManager.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentServiceImpl service;
    @GetMapping("/all")
    public List<StudentDTO> getAllStudents(){
        List<StudentDTO> studentsDTOList = new ArrayList<>();
        List<Student> studentsList = service.getStudents();
        for(Student s:studentsList){
            StudentDTO tempStudentDTO = new StudentDTO(s.getRollNo(),s.getName(),s.getAge());
            studentsDTOList.add(tempStudentDTO);
        }
        return studentsDTOList;
    }
    @GetMapping
    public List<StudentDTO> getAllStudentsPage(@RequestParam(name = "offset",required = false) Integer offset,@RequestParam(name = "size", required = false) Integer size ){
        Page<Student> studentsExtra = service.findWithPagination(offset,size);
        List<StudentDTO> students = new ArrayList<>();
        for(Student s: studentsExtra.toList()){
            StudentDTO studentDTO = new StudentDTO(s.getRollNo(),s.getName(),s.getAge());
            students.add(studentDTO);
        }
        return students;
    }
    @GetMapping("/:{id}")
    public StudentDTO getStudentById(@PathVariable("id") int id){
        Student tempStudent = service.getStudent(id);
        return new StudentDTO(tempStudent.getRollNo(), tempStudent.getName(),tempStudent.getAge());
    }
    @GetMapping("/name:{name}")
    public List<StudentDTO> getStudentByName(@PathVariable String name){
        List<Student> tempStudentList = service.getStudentByName(name);
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for(Student s:tempStudentList){
            studentDTOList.add(new StudentDTO(s.getRollNo(),s.getName(),s.getAge()));
        }
        return studentDTOList;
    }
    @GetMapping("/searchStart:{starting}")
    public List<StudentDTO> getStudentByNameStarting(@PathVariable String start){
        List<StudentDTO> studentsDTOList = new ArrayList<>();
        List<Student> studentsList= service.findByCharInName(start);
        for(Student s:studentsList){
            studentsDTOList.add(new StudentDTO(s.getRollNo(),s.getName(),s.getAge()));
        }
        return studentsDTOList;
    }
//    @GetMapping
//    public List<Student> getStudentByCharFind(@RequestParam(value = "nameStarting", required = false) String starter){
//        return service.findByCharInName(starter);
//    }
    @PostMapping
    public Student saveStudent(@RequestBody Student student){
        return service.postStudent(student);
    }
    @PostMapping("/bulk")
    public List<Student> saveStudents(@RequestBody List<Student> students){
        return service.postStudents(students);
    }
    @PutMapping
    public Student updateStudent(@RequestBody Student student){
        return service.updateStudent(student);
    }
    @DeleteMapping("/:{id}")
    public String deleteStudent(@PathVariable("id") int id){
        return service.deleteStudent(id);
    }
    @DeleteMapping("all")
    public String deleteAllStudents(){
        return service.deleteAll();
    }
}
