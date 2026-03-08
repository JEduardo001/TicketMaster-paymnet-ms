package com.swSoftware.asientos.payment_ms.domain.port;

public interface IOutboxEventService<T> {
    void saveEvent(T request,String nameTopic);
}
