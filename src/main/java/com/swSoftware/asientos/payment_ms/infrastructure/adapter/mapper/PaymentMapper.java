package com.swSoftware.asientos.payment_ms.infrastructure.adapter.mapper;

import com.swSoftware.asientos.payment_ms.application.dto.payment.DtoPayment;
import com.swSoftware.asientos.payment_ms.domain.model.PaymentModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    DtoPayment toDto(PaymentModel request);
}
