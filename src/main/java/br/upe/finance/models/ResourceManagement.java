package br.upe.finance.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import br.upe.finance.models.enums.BudgetType;
import br.upe.finance.models.enums.ResourceCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

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

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "budget_item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Setter(AccessLevel.PRIVATE)
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

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @NotNull
    private LocalDateTime updatedAt;

    /// Public Methods ///

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
        if (this.budgetItem != null) {
            this.budgetItem.setMoneyAmount(moneyAmount.negate());
        }
    }

    @Override
    public String toString() {
        return String.format("ResourceManagement{id=%s}", this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    /// Private Methods ///

    @PrePersist
    private void createBudgetItemIfNeeded() {
        if (this.budgetItem == null) {
            BudgetItem budgetItem = BudgetItem.builder()
                .type(BudgetType.RESOURCES)
                .moneyAmount(this.moneyAmount.negate())
                .build();
            this.budgetItem = budgetItem;
        }
    }

    @PreUpdate
    private void updateBudgetItemMoneyAmount() {
        this.budgetItem.setMoneyAmount(this.moneyAmount.negate());
    }

}
