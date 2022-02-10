package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.dto.UserStudent;
import com.example.CarRentalApi.school.dto.UserTeacher;
import com.example.CarRentalApi.school.dto.student.StudentDtoRegister;
import com.example.CarRentalApi.school.dto.teacher.TeacherDtoRegister;
import com.example.CarRentalApi.school.mapper.CourseMapper;
import com.example.CarRentalApi.school.mapper.StudentMapper;
import com.example.CarRentalApi.school.mapper.TeacherMapper;
import com.example.CarRentalApi.school.model.*;
import com.example.CarRentalApi.school.payload.request.LoginRequest;
import com.example.CarRentalApi.school.payload.request.SignupRequest;
import com.example.CarRentalApi.school.payload.response.JwtResponse;
import com.example.CarRentalApi.school.payload.response.MessageResponse;
import com.example.CarRentalApi.school.repository.*;
import com.example.CarRentalApi.school.security.jwt.JwtUtils;
import com.example.CarRentalApi.school.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;


    @Autowired
    public AuthController(TeacherRepository teacherRepository,TeacherMapper teacherMapper, AuthenticationManager authenticationManager, PasswordEncoder encoder, JwtUtils jwtUtils, RoleRepository roleRepository, UserRepository userRepository, StudentRepository studentRepository, StudentMapper studentMapper) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.teacherMapper = teacherMapper;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signupStudent")
    public ResponseEntity<?> registerUserStudent(@Valid @RequestBody UserStudent userStudent) {
        // Create new student
        StudentDtoRegister studentDtoRegister = userStudent.getStudentDtoRegister();

        Student student = studentMapper.studentDtoRegisterToStudent(studentDtoRegister);
        // Create new user's account
        User user = new User(userStudent.getSignupRequest().getUsername(),
                encoder.encode(userStudent.getSignupRequest().getPassword()));

        student.setUser(user);

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        Set<String> strRoles = userStudent.getSignupRequest().getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        studentRepository.save(student);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    private Optional<Student> existEmail(StudentDtoRegister student)
    {
        return studentRepository.findByEmail(student.getEmail());
    }
    @PostMapping("/signupTeacher")
    public ResponseEntity<?> registerUserTeacher(@Valid @RequestBody UserTeacher userTeacher) {
        // Create new student
        TeacherDtoRegister teacherDtoRegister = userTeacher.getTeacherDtoRegister();

        Teacher teacher = teacherMapper.teacherDtoRegisterToStudent(teacherDtoRegister);
        // Create new user's account
        User user = new User(userTeacher.getSignupRequest().getUsername(),
                encoder.encode(userTeacher.getSignupRequest().getPassword()));

        teacher.setUser(user);

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        Set<String> strRoles = userTeacher.getSignupRequest().getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        teacherRepository.save(teacher);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}