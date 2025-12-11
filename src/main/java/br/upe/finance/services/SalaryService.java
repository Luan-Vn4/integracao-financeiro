package br.upe.finance.services;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.upe.finance.dtos.ReadSalaryDto;
import br.upe.finance.dtos.SalaryRequestDto;
import br.upe.finance.dtos.mappers.ReadSalaryDtoMapper;
import br.upe.finance.dtos.mappers.SalaryRequestDtoMapper;
import br.upe.finance.models.Payroll;
import br.upe.finance.models.Salary;
import br.upe.finance.repositories.PayrollRepository;
import br.upe.finance.repositories.SalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalaryService {

    /// Fields ///

    private final SalaryRepository salaryRepository;
    private final PayrollRepository payrollRepository;
    private final ReadSalaryDtoMapper readSalaryDtoMapper;
    private final SalaryRequestDtoMapper salaryMapper;

    /// Public Methods ///

    public Optional<ReadSalaryDto> findById(UUID employeeId) {
        return salaryRepository
            .findById(employeeId)
            .map(readSalaryDtoMapper::fromModel);
    }

    public Page<ReadSalaryDto> findAll(Pageable pageable) {
        return salaryRepository
            .findAll(pageable)
            .map(readSalaryDtoMapper::fromModel);
    }

    @Transactional
    public ReadSalaryDto registerSalary(SalaryRequestDto dto) {
        Salary newSalary = salaryMapper.toModel(dto);
        Salary savedSalary = salaryRepository.save(newSalary);

        createPayrollIfNotExists(savedSalary, LocalDate.now());

        return readSalaryDtoMapper.fromModel(savedSalary);
    }

    /// Private Methods ///
    
    private void createPayrollIfNotExists(Salary salary, LocalDate date) {
        LocalDate startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        boolean exists = payrollRepository.findByEmployeeIdAndPaymentDateBetween(
            salary.getEmployeeId(), startOfMonth, endOfMonth
        ).isPresent();

        if (!exists) {
            log.info("Creating initial payroll for employee {}", salary.getEmployeeId());
            
            Payroll firstPayroll = new Payroll();
            firstPayroll.setEmployeeId(salary.getEmployeeId());
            firstPayroll.setPaymentDate(date);
            firstPayroll.setMoneyAmount(salary.getMoneyAmount());
            
            payrollRepository.save(firstPayroll);
        } else {
            log.info("Payroll already exists for employee {} in month {}. Skipping creation.", 
                salary.getEmployeeId(), date.getMonth());
        }
    }
}