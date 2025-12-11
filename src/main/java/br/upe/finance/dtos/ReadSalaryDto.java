package br.upe.finance.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.upe.finance.models.enums.SalaryType;
import lombok.Builder;

@Builder
public record ReadSalaryDto(
    UUID employeeId,
    BigDecimal moneyAmount,
    SalaryType type,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}