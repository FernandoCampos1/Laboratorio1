package org.example.Lab2;

class PaymentProcessorModificar {
    PaymentService paymentService;
    ExpressPaymentService expressPaymentService;

    boolean processPayment(Order order) {
        if (order.type.equals("standard")) {
            return processStandardPayment(order);
        } else if (order.type.equals("express")) {
            return processExpressPayment(order, "highPriority");
        } else {
            throw new Error("Invalid order type");
        }
    }

    boolean processStandardPayment(Order order) {
        // Handles standard payment processing
        if (paymentService.process(order.amount)) {
            return true;
        } else {
            throw new Error("Payment failed");
        }
    }

    boolean processExpressPayment(Order order, String priority) {
        // Handles express payment processing
        if (expressPaymentService.process(order.amount, priority)) {
            return true;
        } else {
            throw new Error("Express payment failed");
        }
    }
}
