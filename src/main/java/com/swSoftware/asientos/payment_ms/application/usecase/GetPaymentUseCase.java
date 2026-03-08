package com.swSoftware.asientos.payment_ms.application.usecase;

import com.swSoftware.asientos.payment_ms.application.dto.payment.DtoPayment;

import java.util.UUID;

public interface GetPaymentUseCase {
    DtoPayment execute(UUID id);
}
