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

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

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
    private BudgetItem budgetItem;

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
        return String.format("Payroll{id=%s}", this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
