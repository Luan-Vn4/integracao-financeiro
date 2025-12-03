package br.upe.finance.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.upe.finance.models.enums.ResourceCategory;
import lombok.Builder;

// @formatter:off
@Builder
public record ReadResourceManagementDto(
    UUID id,

    UUID budgetItemId,

    BigDecimal moneyAmount,

    String item,

    ResourceCategory category,

    String justification,

    Integer quantity,

    String description,

    LocalDateTime createdAt,

    LocalDateTime updatedAt
) {
}
// @formatter:on
