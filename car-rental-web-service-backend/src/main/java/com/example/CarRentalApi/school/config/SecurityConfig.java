package com.example.CarRentalApi.school.config;

import com.example.CarRentalApi.school.repository.StudentRepository;
import com.example.CarRentalApi.school.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("api/v1/teacher")
                .hasAuthority("USER")
                .antMatchers("api/v1/teacher")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll();
    }

}



//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider
//                = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(encoder());
//        return  provider;
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }


//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//
//        List<Teacher> teachers = teacherRepository.findAll();
//        List<UserDetails> teachersDetails = teachers.stream().map(teacher -> User.builder()
//                .username(teacher.getEmail())
//                .password(teacher.getPassword())
//                .roles(teacher.getRole())
//                .build()).collect(Collectors.toList());
//
//        List<Student> students = studentRepository.findAll();
//        List<UserDetails> studentDetails = students.stream().map(student -> User.builder()
//                .username(student.getEmail())
//                .password(student.getPassword())
//                .roles(student.getRole())
//                .build()).collect(Collectors.toList());
//        return new InMemoryUserDetailsManager(
//                studentDetails
//        );


//        UserDetails userAdminDetails = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("password"))
//                .roles("ADMIN")
//                .build();
//        UserDetails userUserDetails = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(
//             userAdminDetails, userUserDetails
//
//        );
