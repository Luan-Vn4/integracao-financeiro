package br.upe.finance.dtos.mappers;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.SaveResourceManagementDto;
import br.upe.finance.models.ResourceManagement;
import br.upe.finance.models.enums.ApprovalStatus;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SaveResourceManagementDtoMapper {

    public ResourceManagement toModel(SaveResourceManagementDto dto) {
        return ResourceManagement
            .builder()
            .moneyAmount(dto.moneyAmount())
            .item(dto.item())
            .category(dto.category())
            .justification(dto.justification())
            .quantity(dto.quantity())
            .description(dto.description())
            .approvalStatus(ApprovalStatus.PENDING)
            .build();
    }

}
