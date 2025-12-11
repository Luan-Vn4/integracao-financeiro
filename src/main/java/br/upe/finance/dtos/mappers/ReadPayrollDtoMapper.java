package br.upe.finance.dtos.mappers;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.ReadPayrollDto;
import br.upe.finance.models.Payroll;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReadPayrollDtoMapper {

    public ReadPayrollDto fromModel(Payroll model) {
        return ReadPayrollDto.builder()
            .id(model.getId())
            .employeeId(model.getEmployeeId())
            .moneyAmount(model.getMoneyAmount())
            .paymentDate(model.getPaymentDate())
            .budgetItemId(model.getBudgetItem() != null ? model.getBudgetItem().getId() : null)
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
            .build();
    }
}