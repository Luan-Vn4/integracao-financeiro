package br.upe.finance.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.upe.finance.models.enums.SalaryType;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salaries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Salary {

    /// Fields ///

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull
    private UUID id;

    @Column(name = "employee_id", unique = true)
    @NotNull
    private Integer employeeId;

    @Column(name = "money_amount", precision = 19, scale = 4)
    @NotNull
    private BigDecimal moneyAmount;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private SalaryType type;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /// Public Methods ///

    @Override
    public String toString() {
        return String.format(
            "Salary{employeeId=%s, moneyAmount=%s, type=%s}",
            this.employeeId,
            this.moneyAmount,
            this.type
        );
    }

    @Override
    public int hashCode() {
        return this.employeeId.hashCode();
    }

}
