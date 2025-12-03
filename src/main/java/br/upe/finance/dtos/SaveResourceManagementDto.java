package br.upe.finance.dtos;

import java.math.BigDecimal;

import br.upe.finance.models.enums.ResourceCategory;
import br.upe.finance.models.enums.ApprovalStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// @formatter:off
public record SaveResourceManagementDto(
    @NotNull(message = "Money amount is required")
    @Positive(message = "Money amount must be positive")
    BigDecimal moneyAmount,

    @NotNull(message = "Item is required")
    @Size(max = 255, message = "Item must not exceed 255 characters")
    String item,

    @NotNull(message = "Category is required")
    ResourceCategory category,

    @NotNull(message = "Justification is required")
    String justification,

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    Integer quantity,

    String description,

    @NotNull(message = "Approval status is required")
    ApprovalStatus approvalStatus
) {
}
// @formatter:on
