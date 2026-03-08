package com.swSoftware.asientos.payment_ms.domain.port;

import com.app.events.CreateTicketEvent;

public interface IPaymentService {
    void createPaymentSession(CreateTicketEvent request);
}
