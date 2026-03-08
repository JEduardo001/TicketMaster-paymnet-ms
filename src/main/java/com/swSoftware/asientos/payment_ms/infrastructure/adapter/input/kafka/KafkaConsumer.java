package com.swSoftware.asientos.payment_ms.infrastructure.adapter.input.kafka;

import com.swSoftware.asientos.payment_ms.domain.port.IPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final IPaymentService iPaymentService;

    @KafkaListener(
            topics = "dev.ticket-ms.created-ticket.v1",
            groupId = "ticket-ms.created-ticket.v1"    )
    public void responseEventTicketCreated(com.app.events.CreateTicketEvent request, @Header(value = "CORRELATION_HEADER",required = false) String correlationId)
    {

        iPaymentService.createPaymentSession(request);
    }

}

