package br.upe.finance.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.upe.finance.models.enums.BudgetType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "budget_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BudgetItem {

    /// Fields ///

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull
    private UUID id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private BudgetType type;

    @Column(name = "money_amount", precision = 19, scale = 4)
    @NotNull
    private BigDecimal moneyAmount;

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
        return String.format("BudgetItem{id=%s}", this.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

}
