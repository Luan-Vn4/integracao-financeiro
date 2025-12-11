package br.upe.finance.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;

@Builder
public record ReadPayrollDto(
    UUID id,
    Integer employeeId,
    BigDecimal moneyAmount,
    LocalDate paymentDate,
    UUID budgetItemId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}