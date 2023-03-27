package com.example.student.service;

import com.example.student.Repository.StudentRepository;
import com.example.student.entity.Student;
import com.example.student.vo.Department;
import com.example.student.vo.StudentDepartmentVO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class StudentService {
 @Autowired
    private StudentRepository studentRepository;

 @Autowired
 private RestTemplate restTemplate;

    public Student save(Student student) {
        log.info("I am in saverepo");
        return studentRepository.save(student);
    }

    public Student findStudentById(Long studentId) {
        log.info("I am in findbyidrepo");
        return studentRepository.findByStudentId(studentId);
    }
    public List<Student> findAllStudents() {
        log.info("I am in findALL");
        return studentRepository.findAll();
    }
private final static String Student_service="studentservice";
    @CircuitBreaker(name = Student_service,fallbackMethod = "fallBackMethod")
    public StudentDepartmentVO getstudentwithdepartment(Long studentId) {
        StudentDepartmentVO studentDepartmentVO=new StudentDepartmentVO();
        Student student= studentRepository.findByStudentId(studentId);
        String url = "http://demo_microservice/departments/" + student.getDepartmentId();
        Department department=restTemplate.getForObject(url,Department.class);
        studentDepartmentVO.setStudent(student);
    studentDepartmentVO.setDepartment(department);
    return studentDepartmentVO;
    }

    public String fallBackMethod(Exception e){
        return "fallback method";
    }
}
