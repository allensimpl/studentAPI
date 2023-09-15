package com.studentPro.studentManager.Service;
import com.studentPro.studentManager.DTO.ResponseDto;
import com.studentPro.studentManager.DTO.StudentRequestDTO;
import com.studentPro.studentManager.DTO.StudentResponseDTO;
import com.studentPro.studentManager.Entity.Student;
import com.studentPro.studentManager.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    private StudentRepository repository;

    @Override
    public ResponseDto postStudents(List<StudentRequestDTO> studentsDTO) throws Exception {

        List<Student> studentList = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        for(StudentRequestDTO student:studentsDTO){
            emails.add(student.getEmail());
        }
        if(repository.containsEmailIDs(emails)){
            throw new Exception("Email exists already");
        }
        for(StudentRequestDTO studentDTO:studentsDTO){
            Student student = new Student(studentDTO.getEmail(),studentDTO.getName(),studentDTO.getAge());
            studentList.add(student);
        }
        List<Student> dataStudent =  repository.saveAll(studentList);
        List<StudentResponseDTO> data = new ArrayList<>();
        for(Student sResponse:dataStudent){
            data.add(new StudentResponseDTO(sResponse.getId(),sResponse.getName(),sResponse.getAge(), sResponse.getEmail()));
        }
        return (new ResponseDto("Success",200,data));
    }


    @Override
    public ResponseDto postStudent(StudentRequestDTO studentDTO) throws Exception{
        if(repository.containsEmail(studentDTO.getEmail())){
            throw new Exception("The mail Id already exists");
        }
        Student student = new Student(studentDTO.getEmail(),studentDTO.getName(),studentDTO.getAge());
        Student data = repository.save(student);
        StudentResponseDTO studentDTOResponseData = new StudentResponseDTO(data.getId(),data.getName(),data.getAge(),data.getEmail());
        return new ResponseDto("Success",200,studentDTOResponseData);
    }

    @Override
    public ResponseDto updateStudent(StudentRequestDTO studentDTO,int id) throws Exception{
        if(!repository.containsID(id)){
            throw new Exception("The ID doesn't exist");
        }
        Student updatingStudent = repository.findById(id).orElse(null);
        updatingStudent.setAge(studentDTO.getAge());
        updatingStudent.setName(studentDTO.getName());
        updatingStudent.setEmail(studentDTO.getEmail());
        Student data = repository.save(updatingStudent);
        StudentResponseDTO responseData = new StudentResponseDTO(data.getId(),data.getName(),data.getAge(),data.getEmail());
        ResponseDto responseDTO = new ResponseDto("Updated",200,responseData);
        return responseDTO;
    }

    @Override
    public String deleteStudent(int id) throws Exception{
        if(!repository.containsID(id)){
            throw new Exception("The ID never existed");
        }
        repository.deleteById(id);
        return "Deleted"+id;
    }

    @Override
    public void print() {

    }


    public List<StudentResponseDTO> dtoConverter(List<Student> studentList){
        List<StudentResponseDTO> studentResponseDTOList = new ArrayList<>();
        for(Student s:studentList){
            studentResponseDTOList.add(new StudentResponseDTO(s.getId(),s.getName(),s.getAge(),s.getEmail()));
        }
        return studentResponseDTOList;
    }

    @Override
    public List<StudentResponseDTO> getStudents(int pageNo, int pageSize, String sort, boolean descending, String search){
        int offset = (pageNo - 1) * pageSize;
        Pageable pagingParams = PageRequest.of(offset, pageSize,
                JpaSort.unsafe(descending ? Sort.Direction.DESC : Sort.Direction.ASC, "(" + sort + ")"));
        Page<Student> allStudents = repository.getAllStudents( search, pagingParams);
//        Page<Student> allStudents = repository.findAll(pagingParams);
        return dtoConverter(allStudents.getContent());
    }
}
