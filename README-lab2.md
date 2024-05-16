# Laboratorio2
Laboratorio-Iron

0. Se cambia el seudocodigo a codigo java.


1. Primer principio de SOLID (Single Responsibility Principle)
   Como se menciona cada clase debe tener una sola responsabilidad, 
   en este caso se tiene una clase que se encarga de la lectura de un archivo y otra clase que se encarga de la escritura de un archivo, 
   cada una de estas clases tiene una sola responsabilidad.  

   En el codigo se puede observar que la clase SystemManager tiene multiples responsabilidades.

    Se propone dividir la clase SystemManager en clases mas pequeñas, cada una con una sola responsabilidad.
   
   tambien se aplica el principi Liskov Substitution Principle (LSP) ya que se puede reemplazar una clase por otra que herede de ella.
   Se crean interfaces para cada clase y se implementan en las clases que se crean. 

 Se divide en clases
[CustomerNotifier.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FCustomerNotifier.java)
package org.example;

class CustomerNotifier {
EmailService emailService;

    void notifyCustomer(Order order) {
        // Sends an email notification to the customer
        emailService.sendEmail(order.customerEmail, "Your order has been processed.");
    }
}



[Database.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FDatabase.java)

package org.example;

public interface Database {
void updateOrderStatus(String id, String status);
}


[EmailService.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FEmailService.java)

package org.example;

interface EmailService {
void sendEmail(String email, String message);
}


[ExpressPaymentService.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FExpressPaymentService.java)

package org.example;

interface ExpressPaymentService {
boolean process(double amount, String priority);
}

[InventoryManager.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FInventoryManager.java)

package org.example;

interface ExpressPaymentService {
boolean process(double amount, String priority);
}

[Order.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FOrder.java)

package org.example;

public class Order {
String type;
int quantity;
double amount;
String customerEmail;
String id;
}

[OrderStatusUpdater.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FOrderStatusUpdater.java)

package org.example;
class OrderStatusUpdater {
Database database;

    void updateOrderStatus(Order order, String status) {
        // Updates the order status in the database
        database.updateOrderStatus(order.id, status);
    }
}

[PaymentProcessor.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FPaymentProcessor.java)

package org.example;

class PaymentProcessor {
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


[PaymentService.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FPaymentService.java)

package org.example;

interface PaymentService {
boolean process(double amount);
}

[SystemManager.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FSystemManager.java)

package org.example;

public class SystemManager {
InventoryManager inventoryManager;
PaymentProcessor paymentProcessor;
OrderStatusUpdater orderStatusUpdater;
CustomerNotifier customerNotifier;

    void processOrder(Order order) {
        inventoryManager.verifyInventory(order);
        paymentProcessor.processPayment(order);
        orderStatusUpdater.updateOrderStatus(order, "processed");
        customerNotifier.notifyCustomer(order);
    }
}

2. Cambio en otro principi de solid: Open-Closed Principle (OCP)
Se encontro que se deberia de poder agregar otro tipo de pago, se modifica el codigo para que se pueda agregar otro tipo de pago.
Tambien aplicamos el principio de Sustitucion de Liskov (LSP) ya que se puede reemplazar una clase por otra que herede de ella.
por ejemplo la clase StandardPaymentProcessor y ExpressPaymentProcessor heredan de PaymentProcessor.

[SystemManager.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FSystemManager.java)

package org.example;

public class SystemManager {
InventoryManager inventoryManager;
PaymentProcessor paymentProcessor;
OrderStatusUpdater orderStatusUpdater;
CustomerNotifier customerNotifier;

    void processOrder(Order order) {
        inventoryManager.verifyInventory(order);
        paymentProcessor.processPayment(order);
        orderStatusUpdater.updateOrderStatus(order, "processed");
        customerNotifier.notifyCustomer(order);
    }
}


[PaymentProcessor.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FPaymentProcessor.java)

package org.example;

public interface PaymentProcessor {
boolean processPayment(Order order);
}


[StandardPaymentProcessor.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FStandardPaymentProcessor.java)

package org.example;

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

[ExpressPaymentProcessor.java](src%2Fmain%2Fjava%2Forg%2Fexample%2FExpressPaymentProcessor.java)

package org.example;

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




3. aplicando el principio de Dependency Inversion Principle (DIP).
   Se cambia las implementaciones de las clases:     
   InventoryManager inventoryManager;
   PaymentProcessor paymentProcessor;
   OrderStatusUpdater orderStatusUpdater;
   CustomerNotifier customerNotifier;
   
se crean interfaces para cada clase y se implementan en las clases que se crean. luego se pasan por medio de los constructores de las clases que las necesitan.

clase modificada por medio de los constructores.

package org.example;

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


