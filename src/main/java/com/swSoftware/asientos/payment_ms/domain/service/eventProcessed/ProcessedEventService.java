package com.swSoftware.asientos.payment_ms.domain.service.eventProcessed;


import com.swSoftware.asientos.payment_ms.domain.model.EventProcessedModel;
import com.swSoftware.asientos.payment_ms.domain.port.IEventProcessedService;
import com.swSoftware.asientos.payment_ms.infrastructure.adapter.output.persistence.EventProcessedRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.swSoftware.asientos.payment_ms.infrastructure.shared.LogMessages.MESSAGE_EVENT_PROCESSED_SAVED;

@Service
@AllArgsConstructor
@Slf4j
public class ProcessedEventService implements IEventProcessedService {

    private final EventProcessedRepository eventProcessedRepository;

    @Override
    public void saveEventProcessedAndFlush(EventProcessedModel request){
        eventProcessedRepository.saveAndFlush(request);
        log.info(MESSAGE_EVENT_PROCESSED_SAVED.toString());

    }

    @Override
    public boolean eventAlreadyProcessed(UUID id){
        return eventProcessedRepository.existsById(id);
    }
}

