package br.upe.finance.dtos.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.AppointmentInfoDto;
import br.upe.finance.models.Appointment;

@Component
public class AppointmentInfoDtoMapper {

    public Appointment toModel(AppointmentInfoDto dto) {
        return Appointment.builder()
            .id(UUID.randomUUID())
            .doctorId(dto.doctorId())
            .date(dto.date().atStartOfDay())
            .moneyAmount(dto.moneyAmount())
            .build();
    }
}