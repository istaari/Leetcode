package LLD.airlineManagementSystem.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PaymentProcessor {
    Payment processPayment(BigDecimal amount, PaymentMethod paymentMethod);
    Payment refund(Payment payment);
}
