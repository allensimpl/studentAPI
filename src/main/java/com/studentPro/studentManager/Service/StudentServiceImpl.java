package com.studentPro.studentManager.Service;
import com.studentPro.studentManager.Entity.Student;
import com.studentPro.studentManager.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
    @Autowired
    private StudentRepository repository;


    @Override
    public List<Student> getStudents(){
        return repository.findAll();
    }
    @Override
    public Student getStudent(int id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getStudentByName(String name){
        return repository.findByName(name);
    }
    @Override
    public List<Student> postStudents(List<Student> students){
        return repository.saveAll(students);
    }
    @Override
    public Student postStudent(Student student){
        return repository.save(student);
    }

    @Override
    public Student updateStudent(Student student){
        Student updatingStudent = repository.findById(student.getRollNo()).orElse(null);
        updatingStudent.setAge(student.getAge());
        updatingStudent.setName(student.getName());
        updatingStudent.setRollNo(student.getRollNo());
        return repository.save(updatingStudent);
    }
    @Override
    public String deleteStudent(int id){
        repository.deleteById(id);
        return "Deleted"+id;
    }
    @Override
    public String deleteAll(){
        repository.deleteAll();
        return "Deleted All Items";
    }
    @Override
    public List<Student> findByCharInName(String chara) {
        return repository.findByStartingChar(chara);
    }
    @Override
    public Page<Student> findWithPagination(int offset,int size){
        return repository.findAll(PageRequest.of(offset, size));
    }
}
