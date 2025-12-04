package br.upe.finance.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.upe.finance.dtos.ResourceRequestDto;
import br.upe.finance.messaging.constants.QueueName;
import br.upe.finance.services.ResourceManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class ResourceRequestListener {

    private final ResourceManagementService rmService;

    @RabbitListener(queues = QueueName.RESOURCES_REQUEST)
    public void listen(ResourceRequestDto dto) {
        log.info("Received resource request: {}", dto);
        rmService.requestResource(dto);
        log.info("Resource request processed successfully");
    }

}
