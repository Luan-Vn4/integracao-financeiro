package br.upe.finance.models;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "budget_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BudgetItem {

    /// Fields ///

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    @NotNull
    private UUID id;

    @Column(name = "money_amount", precision = 19, scale = 4)
    @NotNull
    private BigDecimal moneyAmount;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "budget_year", referencedColumnName = "year")
    private Budget budget;

    @OneToOne(mappedBy = "budgetItem", orphanRemoval = true)
    private Payroll payroll;

    /// Public Methods ///

    public Short getBudgetYear() {
        return this.budget.getYear();
    }

    @Override
    public String toString() {
        return String.format("BudgetItem{id=%s}", this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
