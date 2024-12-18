package LLD.airlineManagementSystem.payment;

import java.math.BigDecimal;

public class CreditCardPaymentProcessor implements PaymentProcessor {

    public Payment processPayment(BigDecimal amount, PaymentMethod paymentMethod) {
        // Specific logic related payment Method
        return new Payment(amount, paymentMethod);
    }

    public Payment refund(Payment payment) {
        // Specific logic related refund
        payment.setStatus(PaymentStatus.REFUNDED);
        return payment;
    }


}
