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

import java.time.LocalDate;
import java.util.UUID;

import jakarta.annotation.Nullable;
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

    @Column(name = "worked_hours")
    @NotNull
    private Short workedHours;

    @Column(name = "payment_date")
    @NotNull
    private LocalDate paymentDate;

    @Column(name = "commission_amount")
    @Nullable
    private Short commissionAmount;

    @OneToOne
    @NotNull
    @JoinColumn(name = "budget_item_id")
    private BudgetItem budgetItem;

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
