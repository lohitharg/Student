package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import com.example.student.vo.StudentDepartmentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
@Autowired
  private   StudentService studentService;

    @PostMapping("/save")
    public Student save(@RequestBody Student student){
return studentService.save(student);
    }


    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable("id") Long studentId){
        log.info("Inside findStudentbyId");
        return studentService.findStudentById(studentId);
    }
    @GetMapping("/departments")
    public List<Student> allDepartments(){
        log.info("Inside findalldepartments");
        return studentService.findAllStudents();
    }

  @GetMapping("/getstudentwithdepartment/{studentId}")
    public StudentDepartmentVO getstudentwithdepartment(Long studentId){
       return studentService.getstudentwithdepartment(studentId);
    }
}
