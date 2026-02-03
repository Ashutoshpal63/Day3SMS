package com.example.day3sms.service;

import com.example.day3sms.dto.StudentRequestDto;
import com.example.day3sms.dto.StudentResponseDto;
import com.example.day3sms.exception.StudentNotFoundException;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final
    StudentRepository repository;

    public StudentService (StudentRepository repository){
        this.repository=repository;
    }

    //create
//    public StudentModel addStudent(StudentModel student)
//    {
//        return repository.save(student);
//    }
    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student=new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved=repository.save(student);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }

    //display students
//    public List<StudentModel> getStudents(){
//        return repository.findAll();
//    }
    public List<StudentResponseDto> getAllStudents(){
        return repository.findAll().stream().map(s->new StudentResponseDto(
                s.getId(),
                s.getName(),
                s.getAge(),
                s.getEmail()
        )).toList();
    }

    //Update
    public StudentResponseDto updateStudent(String id,StudentResponseDto student){
        StudentModel existingStudent=repository.findById(id)
                .orElseThrow(()->new RuntimeException("NO Student found"));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());
        StudentModel updated=repository.save(existingStudent);
        return new StudentResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getAge(),
                updated.getEmail()
        );
    }
    public void delete(String id){
        if(!repository.existsById(id)){
            throw new StudentNotFoundException("No student found with this id");
        }
        repository.deleteById(id);
    }
}
