package br.upe.finance.models;

import java.math.BigDecimal;
import java.util.UUID;

import br.upe.finance.models.enums.SalaryType;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Column(name = "employee_id")
    @NotNull
    private UUID employeeId;

    @Column(name = "money_amount", precision = 19, scale = 4)
    @NotNull
    private BigDecimal moneyAmount;

    @Column(name = "type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private SalaryType type;

    @Column(name = "commission", precision = 19, scale = 4)
    @Nullable
    private BigDecimal commission;

    /// Public Methods ///

    @Override
    public String toString() {
        return String.format(
                "Salary{employeeId=%s, moneyAmount=%s, type=%s, commission=%s}",
                this.employeeId,
                this.moneyAmount, this.type, this.commission);
    }

    @Override
    public int hashCode() {
        return this.employeeId.hashCode();
    }

}
