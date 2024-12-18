package LLD.airlineManagementSystem.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@SuppressWarnings("all")
public class Payment {

    private final String transactionId;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final PaymentMethod paymentMethod;
    private PaymentStatus status;

    public Payment(BigDecimal amount, PaymentMethod paymentMethod) {
        this.transactionId = UUID.randomUUID().toString();
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.paymentMethod = paymentMethod;
        this.status = PaymentStatus.SUCCESS;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
