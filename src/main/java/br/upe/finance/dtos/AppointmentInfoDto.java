package br.upe.finance.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AppointmentInfoDto(
    @NotNull(message = "O ID do(a) doutor(a) é obrigatório")
    UUID doctorId,

    @NotNull(message = "A data da consulta é obrigatória")
    LocalDate date,

    @NotNull(message = "O valor da consulta é obrigatório")
    @Positive(message = "O valor da consulta deve ser positivo")
    BigDecimal moneyAmount
) {}