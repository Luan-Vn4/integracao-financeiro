package br.upe.finance.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.upe.finance.models.enums.ResourceCategory;
import br.upe.finance.models.enums.ApprovalStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resource_management")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResourceManagement {

    /// Fields ///

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    @NotNull
    private UUID id;

    @OneToOne
    @Nullable
    @JoinColumn(name = "budget_item_id")
    private BudgetItem budgetItem;

    @Column(name = "money_amount", precision = 19, scale = 4)
    @NotNull
    private BigDecimal moneyAmount;

    @Column(name = "item", length = 255)
    @NotNull
    private String item;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ResourceCategory category;

    @Column(name = "justification", columnDefinition = "TEXT")
    @NotNull
    private String justification;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "approval_status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ApprovalStatus approvalStatus;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @NotNull
    private LocalDateTime updatedAt;

    /// Public Methods ///

    @Override
    public String toString() {
        return String.format("ResourceManagement{id=%s}", this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
