package br.upe.finance.services;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.upe.finance.dtos.AppointmentInfoDto;
import br.upe.finance.dtos.mappers.AppointmentInfoDtoMapper;
import br.upe.finance.models.Appointment;
import br.upe.finance.models.Payroll;
import br.upe.finance.models.Salary;
import br.upe.finance.repositories.AppointmentRepository;
import br.upe.finance.repositories.PayrollRepository;
import br.upe.finance.repositories.SalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorsAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PayrollRepository payrollRepository;
    private final SalaryRepository salaryRepository;
    private final AppointmentInfoDtoMapper appointmentMapper;

    @Transactional
    public void receiveAppointmentInfo(AppointmentInfoDto dto) {
        System.out.println("Receiving appointment info: " + dto);

        Appointment appointment = appointmentMapper.toModel(dto);
        appointmentRepository.save(appointment);

        System.out.println("Appointment saved: " + appointment);

        LocalDate date = dto.date();
        LocalDate startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        Optional<Payroll> payrollOpt = payrollRepository
            .findByEmployeeIdAndPaymentDateBetween(
                dto.doctorId(),
                startOfMonth,
                endOfMonth
            );

        Payroll payroll;

        if (payrollOpt.isPresent()) {
            payroll = payrollOpt.get();
            log.info("Updating existing payroll for Doctor {}", dto.doctorId());
        } else {
            log.info(
                "Payroll not found for Doctor {} in {}. Creating new one.",
                dto.doctorId(),
                date.getMonth()
            );

            Salary doctorSalary = salaryRepository
                .findByEmployeeId(dto.doctorId())
                .orElseThrow(
                    () -> new RuntimeException(
                        "Doctor Salary not found for ID: " + dto.doctorId()
                    )
                );

            payroll = new Payroll();
            payroll.setEmployeeId(dto.doctorId());
            payroll.setPaymentDate(date);
            payroll.setMoneyAmount(doctorSalary.getMoneyAmount());
        }

        payroll.setMoneyAmount(payroll.getMoneyAmount().add(dto.moneyAmount()));

        payrollRepository.save(payroll);
    }
}