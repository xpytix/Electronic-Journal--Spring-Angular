package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;

import com.example.CarRentalApi.school.dto.course.CourseDtoPut;
import com.example.CarRentalApi.school.dto.student.StudentDtoPut;
import com.example.CarRentalApi.school.mapper.CourseMapper;
import com.example.CarRentalApi.school.mapper.StudentMapper;
import com.example.CarRentalApi.school.model.Course;
import com.example.CarRentalApi.school.model.Credit;
import com.example.CarRentalApi.school.repository.CourseRepository;
import com.example.CarRentalApi.school.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.student.StudentDto;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.repository.StudentRepository;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;
    private final CreditRepository creditRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, StudentMapper studentMapper, CourseMapper courseMapper, CreditRepository creditRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
        this.creditRepository = creditRepository;
    }

    public List<StudentDto> getStudents() {
        return studentMapper.studentsToStudentsDto(studentRepository.findAll());
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public void addNewCourse(Long courseId, StudentDtoPut student) {
        Optional<Course> existCourse = courseRepository.findById(courseId);
        Course courseToAdd = existCourse
                .orElseThrow(() -> new IllegalStateException("course with id " +courseId + "does not exist"));

        Optional<Student> existStudent = studentRepository.findById(student.getId());

        Student studentToUpdate = existStudent
                .orElseThrow(() -> new IllegalStateException("student with id " + student.getId() + "does not exist"));

        Credit newCredit = new Credit();
        //credit
        newCredit.setStudent(studentToUpdate);
        newCredit.setGrade(2);
        newCredit.setAttempt(false);
        newCredit.setCourse(courseToAdd);
        creditRepository.save(newCredit);
       //student
        studentToUpdate.getCredits().add(newCredit);



    }
    public StudentDto getStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("student with id " + studentId + "does not exist");
        }
        return studentMapper.studentToStudentsDto(studentRepository.getById(studentId));
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("student with id " + studentId + "does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    public void updateStudent(Student student) {
        Optional<Student> exist = studentRepository.findById(student.getId());

        Student studentToUpdate = exist
                .orElseThrow(() -> new IllegalStateException("student with id " + student.getId() + "does not exist"));

        studentToUpdate.setEmail(student.getEmail());
        studentToUpdate.setDateOfBirth(student.getDateOfBirth());
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());

        studentRepository.save(studentToUpdate);

    }

}
