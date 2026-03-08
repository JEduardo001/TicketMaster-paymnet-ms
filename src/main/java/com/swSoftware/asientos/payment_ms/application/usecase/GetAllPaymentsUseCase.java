package com.swSoftware.asientos.payment_ms.application.usecase;

import com.swSoftware.asientos.payment_ms.application.dto.page.DtoPage;

import java.util.UUID;


public interface GetAllPaymentsUseCase {
    DtoPage execute(UUID lastId, int limit);
}
