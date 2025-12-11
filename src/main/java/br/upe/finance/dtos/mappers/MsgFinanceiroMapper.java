package br.upe.finance.dtos.mappers;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.AppointmentInfoDto;
import br.upe.finance.dtos.MsgFinanceiroDTO;

@Component
public class MsgFinanceiroMapper {

    public AppointmentInfoDto toInternalDto(MsgFinanceiroDTO msg) {
        return new AppointmentInfoDto(
            convertIdToUuid(msg.idMedico()),

            msg.dataConsulta(),

            BigDecimal.valueOf(msg.valorConsulta())
        );
    }

    private UUID convertIdToUuid(Integer id) {
        if (id == null) return null;
        return new UUID(0L, id.longValue()); 
    }
}