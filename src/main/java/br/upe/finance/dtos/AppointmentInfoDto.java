package br.upe.finance.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AppointmentInfoDto(
    @NotNull(message = "Doctor ID is required")
    UUID doctorId,

    @NotNull(message = "Date is required")
    LocalDate date,

    @NotNull(message = "Money amount is required")
    @Positive(message = "Money amount must be positive")
    BigDecimal moneyAmount
) {}