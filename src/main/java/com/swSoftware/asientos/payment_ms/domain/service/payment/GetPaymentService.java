package com.swSoftware.asientos.payment_ms.domain.service.payment;

import com.swSoftware.asientos.payment_ms.application.dto.payment.DtoPayment;
import com.swSoftware.asientos.payment_ms.application.usecase.GetPaymentUseCase;
import com.swSoftware.asientos.payment_ms.infrastructure.adapter.exception.payment.ExceptionPaymentNotFound;
import com.swSoftware.asientos.payment_ms.infrastructure.adapter.mapper.PaymentMapper;
import com.swSoftware.asientos.payment_ms.infrastructure.adapter.output.persistence.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetPaymentService implements GetPaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public DtoPayment execute(UUID id){
        return paymentMapper.toDto(paymentRepository.findById(id).orElseThrow(ExceptionPaymentNotFound::new));
    }
}
