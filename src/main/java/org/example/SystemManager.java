package org.example.Lab2;

public class SystemManager {
    InventoryManager inventoryManager;
    PaymentProcessor paymentProcessor;
    OrderStatusUpdater orderStatusUpdater;
    CustomerNotifier customerNotifier;


    public SystemManager(InventoryManager inventoryManager, PaymentProcessor paymentProcessor,
                         OrderStatusUpdater orderStatusUpdater, CustomerNotifier customerNotifier) {
        this.inventoryManager = inventoryManager;
        this.paymentProcessor = paymentProcessor;
        this.orderStatusUpdater = orderStatusUpdater;
        this.customerNotifier = customerNotifier;
    }
    void processOrder(Order order) {
        inventoryManager.verifyInventory(order);
        paymentProcessor.processPayment(order);
        orderStatusUpdater.updateOrderStatus(order, "processed");
        customerNotifier.notifyCustomer(order);
    }
}
