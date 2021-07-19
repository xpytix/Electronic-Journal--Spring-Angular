package com.example.CarRentalApi.school.config;

import com.example.CarRentalApi.school.model.Teacher;
import com.example.CarRentalApi.school.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginDetailsService implements UserDetailsService {

    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Teacher teacher = teacherRepository.findByEmail(username);
        if(teacher ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new LoginDetails(teacher);
    }
}
