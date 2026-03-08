package com.swSoftware.asientos.payment_ms.infrastructure.adapter.output.persistence;

import com.swSoftware.asientos.payment_ms.domain.model.PaymentModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> {

    @Query("SELECT u FROM PaymentModel u WHERE (:lastId IS NULL OR u.id > :lastId) ORDER BY u.id ASC")
    List<PaymentModel> findNextPage(@Param("lastId") UUID lastId, Pageable pageable);
}
