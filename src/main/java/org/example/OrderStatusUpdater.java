package org.example.Lab2;
class OrderStatusUpdater {
    Database database;

    void updateOrderStatus(Order order, String status) {
        // Updates the order status in the database
        database.updateOrderStatus(order.id, status);
    }
}