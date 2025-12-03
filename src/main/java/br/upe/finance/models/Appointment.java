package br.upe.finance.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotNull
    private UUID id;

    @Column(name = "doctor_id")
    @NotNull
    private UUID doctorId;

    @Column(name = "date")
    @NotNull
    private LocalDateTime date;

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

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "budget_item_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BudgetItem budgetItem;

    /// Public Methods ///

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

}
