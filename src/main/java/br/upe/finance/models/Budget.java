package br.upe.finance.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UpdateTimestamp;

import br.upe.finance.models.enums.Sector;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "budgets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Budget {

    /// Fields ///

    @Id
    @Column(name = "year")
    @NotNull
    private Short year;

    @Column(name = "sector")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Sector sector;

    @Column(name = "money_amount", precision = 19, scale = 4)
    @NotNull
    private BigDecimal moneyAmount;

    @Column(name = "responsible_id")
    @NotNull
    private UUID responsibleId;

    @CreationTimestamp
    @NotNull
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
    @NotNull
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "budget")
    @OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Set<BudgetItem> items;

    /// Public Methods ///

    @Override
    public String toString() {
        return String.format("Budget{year=%d}", this.year);
    }

    @Override
    public int hashCode() {
        return this.year.hashCode();
    }

}
