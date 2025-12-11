package br.upe.finance.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

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

@Service
@RequiredArgsConstructor
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

        Payroll firstPayroll = new Payroll();
        
        firstPayroll.setEmployeeId(savedSalary.getEmployeeId());
        
        firstPayroll.setPaymentDate(LocalDate.now());

        firstPayroll.setMoneyAmount(savedSalary.getMoneyAmount());

        payrollRepository.save(firstPayroll);

        return readSalaryDtoMapper.fromModel(savedSalary);
    }
}