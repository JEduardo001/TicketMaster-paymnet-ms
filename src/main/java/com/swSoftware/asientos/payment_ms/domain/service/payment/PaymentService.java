package com.swSoftware.asientos.payment_ms.domain.service.payment;


import com.app.events.CreateTicketEvent;
import com.swSoftware.asientos.payment_ms.domain.model.EventProcessedModel;
import com.swSoftware.asientos.payment_ms.domain.model.PaymentModel;
import com.swSoftware.asientos.payment_ms.domain.port.IEventProcessedService;
import com.swSoftware.asientos.payment_ms.domain.port.IPaymentService;
import com.swSoftware.asientos.payment_ms.domain.status.StatusPayment;
import com.swSoftware.asientos.payment_ms.infrastructure.adapter.output.persistence.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.swSoftware.asientos.payment_ms.infrastructure.shared.LogMessages.MESSAGE_EVENT_ALREADY_PROCESSED;
import static com.swSoftware.asientos.payment_ms.infrastructure.shared.LogMessages.MESSAGE_SAVED_PAYMENT;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentService implements IPaymentService{

    private final PaymentRepository paymentRepository;
    private final IEventProcessedService iEventProcessedService;
    //private final String topic = "dev.payment-ms.created-payment-session.v1";


    private boolean saveEventProcessed(CreateTicketEvent request){
        try{
            iEventProcessedService.saveEventProcessedAndFlush(EventProcessedModel.builder()
                    .id(request.getIdCorrelation())
                    .data(request.toString())
                    .createdAt(Instant.now())
                    .build());
            return true;
        }catch(DataIntegrityViolationException ex){
            log.warn(MESSAGE_EVENT_ALREADY_PROCESSED.toString());
            return false;
        }
    }

    @Override
    public void createPaymentSession(CreateTicketEvent request){

        if (!saveEventProcessed(request)) {
            return;
        }

        PaymentModel paymentModel = PaymentModel.builder()
                .idUser(request.getIdUser())
                .idTicket(request.getIdTicket())
                .dateLimitToPage(Instant.now().plusSeconds(1800)) // limit 30 minutes to pay
                .status(StatusPayment.PAYMENT_PENDING_CONFIRMATION)
                .createAt(Instant.now())
                .build();

        paymentRepository.save(paymentModel);
        log.info(MESSAGE_SAVED_PAYMENT.toString());
        //sse para retornar al cliente el payment model para que pague.

    }
}