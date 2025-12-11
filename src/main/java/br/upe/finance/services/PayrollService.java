package br.upe.finance.services;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.upe.finance.dtos.ReadPayrollDto;
import br.upe.finance.dtos.mappers.ReadPayrollDtoMapper;
import br.upe.finance.repositories.PayrollRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final ReadPayrollDtoMapper readPayrollDtoMapper;

    public List<ReadPayrollDto> getEmployeePayrolls(Integer employeeId) {
        return payrollRepository.findAllByEmployeeId(employeeId)
            .stream()
            .map(readPayrollDtoMapper::fromModel)
            .toList();
    }

    public Optional<ReadPayrollDto> getEmployeePayrollByMonth(
        Integer employeeId,
        LocalDate dateInMonth) {
        LocalDate startDate = dateInMonth
            .with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = dateInMonth
            .with(TemporalAdjusters.lastDayOfMonth());

        return payrollRepository
            .findByEmployeeIdAndPaymentDateBetween(
                employeeId,
                startDate,
                endDate
            )
            .map(readPayrollDtoMapper::fromModel);
    }

    public List<ReadPayrollDto> getAllEmployeesPayroll(LocalDate dateInMonth) {
        LocalDate startDate = dateInMonth
            .with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endDate = dateInMonth
            .with(TemporalAdjusters.lastDayOfMonth());

        return payrollRepository.findAllByPaymentDateBetween(startDate, endDate)
            .stream()
            .map(readPayrollDtoMapper::fromModel)
            .toList();
    }
}