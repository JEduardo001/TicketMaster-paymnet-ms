package com.swSoftware.asientos.payment_ms.infrastructure.adapter.input.rest;

import com.swSoftware.asientos.payment_ms.application.dto.responseApi.DtoResponseApi;
import com.swSoftware.asientos.payment_ms.application.usecase.GetAllPaymentsUseCase;
import com.swSoftware.asientos.payment_ms.application.usecase.GetPaymentUseCase;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.AllArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

import static com.swSoftware.asientos.payment_ms.domain.common.HeaderConstants.CORRELATION_KEY;


@RestController
@RequestMapping("/api/v1/payment")
@AllArgsConstructor
public class PaymentController {

    private final GetPaymentUseCase getPaymentUseCase;
    private final GetAllPaymentsUseCase getAllPaymentsUseCase;


    @GetMapping("/{idPayment}")
    public ResponseEntity<DtoResponseApi> get(@Parameter(description = "UUID of the seat") @PathVariable UUID idPayment){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Payment obtained")
                .data(getPaymentUseCase.execute(idPayment))
                .build()
        );
    }

    @GetMapping()
    public ResponseEntity<DtoResponseApi> getAll(
            @Parameter(description = "UUID cursor for pagination") @RequestParam(required = false) UUID lastId,
            @Parameter(description = "Page size limit") @RequestParam(defaultValue = "60") int limit
    ) {
        return ResponseEntity.ok(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .idCorrelation(MDC.get(CORRELATION_KEY.toString()))
                .message("Payments obtained")
                .data(getAllPaymentsUseCase.execute(lastId, limit))
                .build());
    }

}
