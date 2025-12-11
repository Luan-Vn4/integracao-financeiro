package br.upe.finance.dtos.mappers;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.ReadSalaryDto;
import br.upe.finance.models.Salary;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReadSalaryDtoMapper {

    public ReadSalaryDto fromModel(Salary model) {
        return ReadSalaryDto
            .builder()
            .employeeId(model.getEmployeeId())
            .moneyAmount(model.getMoneyAmount())
            .type(model.getType())
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
            .build();
    }

}