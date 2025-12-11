package br.upe.finance.dtos.mappers;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.SalaryRequestDto;
import br.upe.finance.models.Salary;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SalaryRequestDtoMapper {

    public Salary toModel(SalaryRequestDto dto) {
        Salary salary = new Salary();
        salary.setEmployeeId(dto.employeeId());
        salary.setMoneyAmount(dto.moneyAmount());
        salary.setType(dto.type());
        
        return salary;
    }

}