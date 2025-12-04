package br.upe.finance.dtos.mappers;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.ResourceRequestDto;
import br.upe.finance.models.ResourceManagement;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ResourceRequestDtoMapper {

    public ResourceManagement toModel(ResourceRequestDto dto) {
        return ResourceManagement
            .builder()
            .moneyAmount(dto.moneyAmount())
            .item(dto.item())
            .category(dto.category())
            .justification(dto.justification())
            .quantity(dto.quantity())
            .description(dto.description())
            .build();
    }

}
