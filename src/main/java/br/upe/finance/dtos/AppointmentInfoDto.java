package br.upe.finance.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AppointmentInfoDto(
    @NotNull(message = "Appointment ID is required")
    Integer id,

    @NotNull(message = "Doctor ID is required")
    Integer doctorId,

    @NotNull(message = "Date is required")
    LocalDate date,

    @NotNull(message = "Money amount is required")
    @Positive(message = "Money amount must be positive")
    BigDecimal moneyAmount) {}