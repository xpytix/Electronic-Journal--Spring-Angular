package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.dto.student.StudentDtoRegister;
import com.example.CarRentalApi.school.mapper.StudentMapper;
import com.example.CarRentalApi.school.model.ERole;
import com.example.CarRentalApi.school.model.Role;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.model.User;
import com.example.CarRentalApi.school.payload.request.LoginRequest;
import com.example.CarRentalApi.school.payload.request.SignupRequest;
import com.example.CarRentalApi.school.payload.response.JwtResponse;
import com.example.CarRentalApi.school.payload.response.MessageResponse;
import com.example.CarRentalApi.school.repository.RoleRepository;
import com.example.CarRentalApi.school.repository.StudentRepository;
import com.example.CarRentalApi.school.repository.UserRepository;
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
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    StudentRepository studentRepository;
    StudentMapper studentMapper;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

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

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest ) {
//        Boolean existStudent = existEmail(studentDtoRegister).isEmpty();
//        if (!existStudent)
//        {
//            throw new IllegalStateException("student with email " +studentDtoRegister.getEmail() + "already exist");
//        }
//        else
//        {
//
//            studentRepository.save(studentMapper.studentDtoRegisterToStudent(studentDtoRegister));
//        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
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

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    private Optional<Student> existEmail(StudentDtoRegister student)
    {
        return studentRepository.findByEmail(student.getEmail());
    }
}