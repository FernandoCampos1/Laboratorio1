package org.example.Lab2;

class InventoryManager {
    int inventory = 100; // Example inventory value

    void verifyInventory(Order order) {
        // Checks inventory levels
        if (inventory < order.quantity) {
            throw new Error("Out of stock");
        }
    }
}
