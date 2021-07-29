package com.example.CarRentalApi.school.mapper;

import com.example.CarRentalApi.school.dto.credit.CreditDto;
import com.example.CarRentalApi.school.dto.credit.CreditDtoGet;
import com.example.CarRentalApi.school.model.Credit;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CreditMapper {
    public CreditDto creditToCreditDto(Credit credit);

    public CreditDtoGet creditToCreditSlimDto(Credit credit);

    public List<CreditDto> creditsToCreditsDto(List<Credit> credits);
}
