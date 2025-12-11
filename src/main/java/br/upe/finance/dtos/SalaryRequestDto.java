package br.upe.finance.dtos;

import java.math.BigDecimal;
import java.util.UUID;
import br.upe.finance.models.enums.SalaryType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SalaryRequestDto(
    @NotNull(message = "Employee ID is required")
    Integer employeeId,

    @NotNull(message = "Money amount is required")
    @Positive(message = "Money amount must be positive")
    BigDecimal moneyAmount,

    @NotNull(message = "Salary type is required")
    SalaryType type
) {}