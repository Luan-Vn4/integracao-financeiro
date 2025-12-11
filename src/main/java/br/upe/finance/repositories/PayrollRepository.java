package br.upe.finance.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upe.finance.models.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, UUID> {
    
    // Pega todas as folhas de pagamento de um funcionário
    List<Payroll> findAllByEmployeeId(UUID employeeId);

    // Pega a folha de pagamento de um funcionário em um mês específico
    Optional<Payroll> findByEmployeeIdAndPaymentDateBetween(UUID employeeId, LocalDate startDate, LocalDate endDate);

    // Pega todas as folhas de pagamento de todos os funcionários em um mês específico
    List<Payroll> findAllByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
}