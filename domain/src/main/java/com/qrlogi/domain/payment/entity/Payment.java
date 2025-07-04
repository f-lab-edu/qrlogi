package com.qrlogi.domain.payment.entity;


import com.qrlogi.domain.shipment.entity.Shipment;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {

    @Id
    @Column(length = 36)
    private String id; // UUID

    @Column(nullable = false, unique = true)
    private Long paymentNum; //내부식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payments_method", nullable = false)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private LocalDateTime paidAt;

    @PrePersist
    public void generateId() {
        if (id == null) id = UUID.randomUUID().toString();
    }
}