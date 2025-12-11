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
import br.upe.finance.repositories.AppointmentRepository;
import br.upe.finance.repositories.PayrollRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorsAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PayrollRepository payrollRepository;
    private final AppointmentInfoDtoMapper appointmentMapper;

    @Transactional
    public void receiveAppointmentInfo(AppointmentInfoDto dto) {
        Appointment appointment = appointmentMapper.toModel(dto);
        
        appointmentRepository.save(appointment);

        LocalDate appointmentDate = dto.date();
        LocalDate startOfMonth = appointmentDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = appointmentDate.with(TemporalAdjusters.lastDayOfMonth());

        Optional<Payroll> payrollOpt = payrollRepository
            .findByEmployeeIdAndPaymentDateBetween(dto.doctorId(), startOfMonth, endOfMonth);

        if (payrollOpt.isPresent()) {
            Payroll payroll = payrollOpt.get();
            
            payroll.setMoneyAmount(payroll.getMoneyAmount().add(dto.moneyAmount()));
            
            payrollRepository.save(payroll);
        } else {
            throw new RuntimeException(
                String.format("Folha de pagamento do funcionário %s no mês %s não foi encontrada.", 
                dto.doctorId(), appointmentDate.getMonth())
            );
        }
    }
}