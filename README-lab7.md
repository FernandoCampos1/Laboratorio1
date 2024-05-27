# Laboratorio 7
Laboratorio-Iron

# Gestión de configuración global: 

Problema: Conexion a base de datos utilizando el patron singleton

Patrón: Singleton

Se requeire utilizar el patron Singleton para la conexión a la base de datos, para evitar la creación de multiples conexiones a la base de datos.

Solucion:
Se aplicara el patron Singleton para la conexión a la base de datos. Para resolver el problema se creara una clase DatabaseSingleton que se encargara de la conexión a la base de datos. La clase DatabaseSingleton tendra un metodo getInstance que se encargara de crear una unica instancia de la clase DatabaseSingleton.
Primero, se declara una variable estática privada que contendrá la única instancia de la clase. Luego, se declara un constructor privado para evitar que se creen nuevas instancias de la clase desde fuera de la misma. Finalmente, se proporciona un método estático público que devolverá la única instancia de la clase, creándola si aún no existe.  Para garantizar que la instancia única se cree de manera segura en un entorno de subprocesos múltiples, se utiliza la palabra clave synchronized en el método getInstance(). Esto garantiza que solo un hilo a la vez pueda ejecutar este método, evitando así que se creen múltiples instancias de la clase.
En este código, la clase DatabaseSingleton se encarga de establecer la conexión con la base de datos en su constructor. El método getConnection() se utiliza para obtener la conexión a la base de dato.


```java 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private Connection connection;

    private DatabaseSingleton() {
        try {
            String url = "jdbc:myDriver:myDatabase";
            String username = "username";
            String password = "password";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static synchronized DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
```

# Creación dinámica de objetos basada en la entrada del usuario:

Patrón: Factory Method

Problema: Crear objetos dinámicamente basados en la entrada del usuario,  utilizando Java Swing, cuando el usuario presiona un botón, se crea un nuevo botón en una posición aleatoria dentro de la ventana.

Solucion:
crear una ventana con un botón. Cuando el usuario presiona el botón, se creará un nuevo botón en una posición aleatoria dentro de la ventana.
Primero, necesitamos crear la ventana y el botón inicial. Para esto, podemos usar una JFrame para la ventana y un JButton para el botón. Cuando el botón es presionado, generamos dos números aleatorios para la posición del nuevo botón y creamos un nuevo JButton en esa posición.


```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DynamicUI {
    public static void main(String[] args) {
        // Crear la ventana
        JFrame frame = new JFrame("Dynamic UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);

        // Crear el botón inicial
        JButton button = new JButton("Crear botón");
        button.setBounds(200, 200, 100, 50);
        frame.add(button);

        // Añadir un ActionListener al botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generar una posición aleatoria para el nuevo botón
                Random random = new Random();
                int x = random.nextInt(frame.getWidth() - 100);
                int y = random.nextInt(frame.getHeight() - 50);

                // Crear el nuevo botón
                JButton newButton = new JButton("Botón");
                newButton.setBounds(x, y, 100, 50);
                frame.add(newButton);

                // Actualizar la ventana
                frame.validate();
                frame.repaint();
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }
}
```

# Notificación de cambio de estado en todos los componentes del sistema:

Patrón: Observer

Problema: Notificar a todos los componentes del sistema cuando el estado del sistema cambia.

Solucion:
ejemplo de cómo podrías implementar la notificación de cambio de estado en todos los componentes del sistema utilizando el patrón de diseño Observer en Java. En este ejemplo, vamos a crear una clase SystemState que representa el estado del sistema y una interfaz SystemComponent que representa un componente del sistema. Cuando el estado del sistema cambia, todos los componentes del sistema son notificados.  Primero, definimos la interfaz SystemComponent que tiene un método update que se llama cuando el estado del sistema cambia.

```java
public interface SystemComponent {
void update(String state);
}
```
Luego, creamos la clase SystemState que mantiene una lista de componentes del sistema y tiene un método setState que cambia el estado del sistema y notifica a todos los componentes del sistema.

```java
import java.util.ArrayList;
import java.util.List;

public class SystemState {
private List<SystemComponent> components = new ArrayList<>();
private String state;

    public void addComponent(SystemComponent component) {
        components.add(component);
    }

    public void setState(String state) {
        this.state = state;
        notifyAllComponents();
    }

    private void notifyAllComponents() {
        for (SystemComponent component : components) {
            component.update(state);
        }
    }
}
```
Finalmente, creamos una clase Component que implementa la interfaz SystemComponent y imprime un mensaje cuando su método update es llamado.

```java
public class Component implements SystemComponent {
    private String name;

    public Component(String name) {
        this.name = name;
    }

    @Override
    public void update(String state) {
        System.out.println("Component " + name + " has been notified of system state change: " + state);
    }
}
```

Aquí hay un ejemplo de cómo podrías usar estas clases:

```java
public class Main {
    public static void main(String[] args) {
        SystemState systemState = new SystemState();

        Component component1 = new Component("Component 1");
        Component component2 = new Component("Component 2");

        systemState.addComponent(component1);
        systemState.addComponent(component2);

        systemState.setState("new state");
    }
}
```

En este código, creamos un SystemState y dos Component. Añadimos los componentes al estado del sistema y luego cambiamos el estado del sistema. Cuando el estado del sistema cambia, todos los componentes son notificados e imprimen un mensaje.

# Gestión eficiente de operaciones asincrónicas:

Patrón: Promise/Future

Problema: Gestionar eficientemente operaciones asincrónicas en Java.

Solucion:
operaciones asincrónicas utilizando la clase Future en Java. En este ejemplo, vamos a simular la realización de llamadas API asincrónicas utilizando un ExecutorService.  Primero, necesitamos crear un ExecutorService que se encargará de ejecutar nuestras tareas asincrónicas. Luego, podemos enviar nuestras tareas al ExecutorService y obtener un Future que representa el resultado de la tarea.  Aquí está el código:

```java
import java.util.concurrent.*;

public class AsyncOperations {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Crear un ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Enviar una tarea asincrónica al ExecutorService
        Future<String> future1 = executor.submit(() -> {
            // Simular una llamada API
            Thread.sleep(2000);
            return "Resultado de la llamada API 1";
        });

        Future<String> future2 = executor.submit(() -> {
            // Simular otra llamada API
            Thread.sleep(1000);
            return "Resultado de la llamada API 2";
        });

        // Obtener los resultados de las tareas asincrónicas
        // Esto bloqueará hasta que las tareas se completen
        String result1 = future1.get();
        String result2 = future2.get();

        System.out.println(result1);
        System.out.println(result2);

        // Cerrar el ExecutorService
        executor.shutdown();
    }
}
```

En este código, creamos un ExecutorService con dos hilos y enviamos dos tareas asincrónicas al ExecutorService. Cada tarea simula una llamada API durmiendo durante un cierto período de tiempo y luego devuelve un resultado. Luego, obtenemos los resultados de las tareas asincrónicas utilizando el método get de Future, que bloqueará hasta que las tareas se completen. Finalmente, cerramos el ExecutorService.  Por favor, ten en cuenta que este es un ejemplo simplificado. En una aplicación real, probablemente querrías manejar los errores de las llamadas API y proporcionar una forma de cancelar las tareas asincrónicas si es necesario.


# Simulación de ejecución de proyectos:


En este proyecto simulado, se aplicaron varios patrones de diseño para resolver desafíos comunes en el desarrollo de software:

1. **Singleton**: Se utilizó el patrón Singleton para gestionar la conexión a la base de datos. Este patrón asegura que solo haya una única instancia de la conexión a la base de datos en toda la aplicación, evitando la creación de múltiples conexiones. Esto se logró a través de la clase `DatabaseSingleton`, que proporciona un método `getInstance` para obtener la única instancia de la conexión a la base de datos.

2. **Factory Method**: Se implementó el patrón Factory Method para la creación dinámica de objetos basada en la entrada del usuario. En este caso, se creó una interfaz de usuario que permite al usuario crear botones dinámicamente en una ventana. Cuando el usuario presiona un botón, se crea un nuevo botón en una posición aleatoria dentro de la ventana.

3. **Observer**: Se utilizó el patrón Observer para la notificación de cambio de estado en todos los componentes del sistema. Se creó una clase `SystemState` que representa el estado del sistema y una interfaz `SystemComponent` que representa un componente del sistema. Cuando el estado del sistema cambia, todos los componentes del sistema son notificados.

4. **Promise/Future**: Se gestionaron eficientemente operaciones asincrónicas utilizando la clase `Future` en Java. Se simuló la realización de llamadas API asincrónicas utilizando un `ExecutorService`. Cada tarea asincrónica devuelve un `Future` que representa el resultado de la tarea.

Estos patrones de diseño y técnicas asincrónicas ayudan a estructurar el código de manera más eficiente, facilitan la gestión de recursos y operaciones, y mejoran la capacidad de respuesta y el rendimiento de la aplicación.


