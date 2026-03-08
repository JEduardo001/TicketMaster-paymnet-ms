package com.swSoftware.asientos.payment_ms.infrastructure.adapter.output.kafka;


import com.app.events.CreateTicketEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.swSoftware.asientos.payment_ms.domain.common.HeaderConstants.CORRELATION_HEADER;


@Service
@AllArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(CreateTicketEvent request, String nameTopic, String correlationId) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(nameTopic, request);
        if (correlationId != null) {
            record.headers().add(CORRELATION_HEADER.toString(), correlationId.getBytes());
        }

        kafkaTemplate.send(record);
    }

   /* public void publisFailedSendEventDlq(ReserveEvent request) {
        kafkaTemplate.send("dev.payment-ms.failed.send.event.dlq.v1", request);
    }*/

}
