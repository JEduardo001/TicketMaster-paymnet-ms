package com.swSoftware.asientos.payment_ms.domain.service.payment;

import com.swSoftware.asientos.payment_ms.application.dto.page.DtoPage;
import com.swSoftware.asientos.payment_ms.application.dto.payment.DtoPayment;
import com.swSoftware.asientos.payment_ms.application.usecase.GetAllPaymentsUseCase;
import com.swSoftware.asientos.payment_ms.domain.model.PaymentModel;
import com.swSoftware.asientos.payment_ms.infrastructure.adapter.mapper.PaymentMapper;
import com.swSoftware.asientos.payment_ms.infrastructure.adapter.output.persistence.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllPaymentsService implements GetAllPaymentsUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public DtoPage execute(UUID lastId, int limit) {
        Pageable limitProvider = PageRequest.of(0, limit);

        List<PaymentModel> payments = paymentRepository.findNextPage(lastId, limitProvider);
        List<DtoPayment> paymentsDto = payments.stream().map(paymentMapper::toDto).collect(Collectors.toList());
        String nextCursor = payments.isEmpty() ? null : payments.get(payments.size() - 1).getId().toString();

        return new DtoPage(nextCursor,payments.size() == limit,paymentsDto);
    }
}
