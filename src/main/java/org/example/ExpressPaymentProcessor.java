package org.example.Lab2;

public class ExpressPaymentProcessor implements PaymentProcessor {
    ExpressPaymentService expressPaymentService;

    @Override
    public boolean processPayment(Order order) {
        if (expressPaymentService.process(order.amount, "highPriority")) {
            return true;
        } else {
            throw new Error("Express payment failed");
        }
    }
}
