package br.upe.finance.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import br.upe.finance.models.enums.BudgetType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Appointment {

    /// Fields ///

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "doctor_id")
    @NotNull
    private Integer doctorId;

    @Column(name = "date")
    @NotNull
    private LocalDateTime date;

    @Column(name = "money_amount", precision = 19, scale = 4)
    @NotNull
    private BigDecimal moneyAmount;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "budget_item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Setter(AccessLevel.PRIVATE)
    private BudgetItem budgetItem;

    /// Public Methods ///

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
        if (this.budgetItem != null) {
            this.budgetItem.setMoneyAmount(moneyAmount);
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Appointment{id=%s, doctorId=%s, date=%s, moneyAmount=%s}",
            this.id,
            this.doctorId,
            this.date,
            this.moneyAmount
        );
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
                .type(BudgetType.APPOINTMENTS)
                .moneyAmount(this.moneyAmount)
                .build();
            this.budgetItem = budgetItem;
        }
    }

    @PreUpdate
    private void updateBudgetItemMoneyAmount() {
        if (this.budgetItem == null) {
            BudgetItem budgetItem = BudgetItem.builder()
                .type(BudgetType.APPOINTMENTS)
                .moneyAmount(this.moneyAmount)
                .build();
            this.budgetItem = budgetItem;
        } else {
            this.budgetItem.setMoneyAmount(this.moneyAmount);
        }
    }

}
