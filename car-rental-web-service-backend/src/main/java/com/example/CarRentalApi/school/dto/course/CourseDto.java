package com.example.CarRentalApi.school.dto.course;

import com.example.CarRentalApi.school.dto.credit.CreditDtoGet;
import com.example.CarRentalApi.school.dto.teacher.TeacherDtoGet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class CourseDto {
    private Long id;
//    @Size(min = 2, message = "Name should be at least 2 characters")
//    @NotNull(message = "Name cannot be an empty value")
    private String name;
    private TeacherDtoGet teacher;
    private List<CreditDtoGet> credits;

}
