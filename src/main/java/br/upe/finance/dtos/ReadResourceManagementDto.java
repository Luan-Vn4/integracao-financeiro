package br.upe.finance.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.upe.finance.models.enums.ResourceCategory;
import br.upe.finance.models.enums.ApprovalStatus;

// @formatter:off
public record ReadResourceManagementDto(
    UUID id,

    UUID budgetItemId,

    BigDecimal moneyAmount,

    String item,

    ResourceCategory category,

    String justification,

    Integer quantity,

    String description,

    ApprovalStatus approvalStatus,

    LocalDateTime createdAt,

    LocalDateTime updatedAt
) {
}
// @formatter:on
