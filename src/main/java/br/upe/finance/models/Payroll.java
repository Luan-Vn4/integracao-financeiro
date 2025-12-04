package br.upe.finance.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import br.upe.finance.models.enums.BudgetType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;

@Entity
@Table(name = "payrolls")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payroll {

    /// Fields ///

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    @NotNull
    private UUID id;

    @Column(name = "payment_date")
    @NotNull
    private LocalDate paymentDate;

    @Column(name = "money_amount", precision = 19, scale = 4)
    @NotNull
    private BigDecimal moneyAmount;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "budget_item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Setter(AccessLevel.PRIVATE)
    private BudgetItem budgetItem;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
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
        return String.format("Payroll{id=%s}", this.id);
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
                .type(BudgetType.PAYROLL)
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
