package org.example.Lab2;

public class StandardPaymentProcessor implements PaymentProcessor {
    PaymentService paymentService;

    @Override
    public boolean processPayment(Order order) {
        if (paymentService.process(order.amount)) {
            return true;
        } else {
            throw new Error("Payment failed");
        }
    }
}
