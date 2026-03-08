package com.swSoftware.asientos.payment_ms.application.dto.payment;

import com.swSoftware.asientos.payment_ms.domain.status.StatusPayment;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record DtoPayment(
      UUID id,
      UUID idUser,
      UUID idTicket,
      Instant dateLimitToPage,
      StatusPayment status,
      Instant createAt
) {
}
