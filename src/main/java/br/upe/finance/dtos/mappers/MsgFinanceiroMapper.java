package br.upe.finance.dtos.mappers;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.AppointmentInfoDto;
import br.upe.finance.dtos.MsgFinanceiroDTO;

@Component
public class MsgFinanceiroMapper {

    public AppointmentInfoDto toInternalDto(MsgFinanceiroDTO msg) {
        return new AppointmentInfoDto(
            msg.id(),
            msg.idMedico(),
            msg.dataConsulta(),
            BigDecimal.valueOf(msg.valorConsulta())
        );
    }
}