package org.example.Lab2;

class CustomerNotifier {
    EmailService emailService;

    void notifyCustomer(Order order) {
        // Sends an email notification to the customer
        emailService.sendEmail(order.customerEmail, "Your order has been processed.");
    }
}