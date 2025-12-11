package br.upe.finance.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.upe.finance.dtos.AppointmentInfoDto;
import br.upe.finance.dtos.MsgFinanceiroDTO;
import br.upe.finance.dtos.mappers.MsgFinanceiroMapper;
import br.upe.finance.messaging.constants.QueueName;
import br.upe.finance.services.DoctorsAppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppointmentListener {

    private final DoctorsAppointmentService appointmentService;
    private final MsgFinanceiroMapper msgMapper;

    @RabbitListener(queues = QueueName.APPOINTMENTS)
    public void receiveAppointment(@Payload MsgFinanceiroDTO message) {
        log.info("Received appointment message: {}", message);

        try {
            AppointmentInfoDto internalDto = msgMapper.toInternalDto(message);
            appointmentService.receiveAppointmentInfo(internalDto);

            log.info("Appointment processed successfully for Doctor ID: {}", internalDto.doctorId());
        } catch (Exception e) {
            log.error("Error processing appointment message: {}", message, e);
        }
    }
}