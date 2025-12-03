package br.upe.finance.dtos.mappers;

import org.springframework.stereotype.Component;

import br.upe.finance.dtos.ReadResourceManagementDto;
import br.upe.finance.models.ResourceManagement;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReadResourceManagementDtoMapper {

    public ReadResourceManagementDto fromModel(ResourceManagement model) {
        return ReadResourceManagementDto
            .builder()
            .id(model.getId())
            .budgetItemId(
                model.getBudgetItem() != null
                    ? model.getBudgetItem().getId()
                    : null
            )
            .moneyAmount(model.getMoneyAmount())
            .item(model.getItem())
            .category(model.getCategory())
            .justification(model.getJustification())
            .quantity(model.getQuantity())
            .description(model.getDescription())
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
            .build();
    }

}
