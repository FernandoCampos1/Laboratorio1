# Laboratorio 9
Laboratorio-Iron

# Diseño e implementación de una arquitectura de microservicios

# Tareas:

# Ejercicio de diseño:

Resumen de la aplicación: Los participantes reciben una descripción de una aplicación monolítica de comercio electrónico que se encarga de la gestión de usuarios, el catálogo de productos, el procesamiento de pedidos y la atención al cliente.
Tarea: Identificar y delinear microservicios potenciales basados ​​en principios de diseño basados ​​en dominios. Los participantes determinarán los límites de los servicios, definirán cómo se comunicarán los servicios y planificarán cómo manejar los datos compartidos.


# Descripción de la aplicación de comercio electrónico monolítica:
La aplicación es una plataforma de comercio electrónico tradicional que abarca todas las funcionalidades dentro de una arquitectura de software única y unificada. La aplicación maneja las siguientes operaciones clave:

Gestión de usuarios: gestiona perfiles de usuario, autenticación y autorización. Almacena información personal, gestiona las sesiones de inicio de sesión y gestiona las preferencias del usuario.

Catálogo de productos: mantiene una lista completa de productos, incluidas descripciones, precios, imágenes y niveles de inventario. Admite funcionalidades de búsqueda y categorización de productos.

Procesamiento de pedidos: gestiona todos los aspectos del proceso de pedido, desde la gestión del carrito hasta la realización del pedido, el procesamiento de pagos y el seguimiento del historial de pedidos.

Atención al cliente: maneja las consultas, devoluciones, quejas y comentarios de los clientes a través de un sistema basado en tickets integrado con las bases de datos de usuarios y pedidos.

La aplicación se basa en una única base de datos relacional que contiene todos los datos del usuario, información del producto, pedidos e interacciones de atención al cliente. Actualmente opera en una base de código única con una interfaz basada en web que se comunica directamente con el servidor backend.

La plataforma ha experimentado desafíos de escalamiento durante períodos de alto tráfico, frecuentes tiempos de inactividad durante las actualizaciones y una dificultad cada vez mayor para implementar nuevas funciones sin afectar las funcionalidades existentes. El objetivo es descomponer esta arquitectura monolítica en una arquitectura basada en microservicios para abordar estos problemas y mejorar la agilidad y escalabilidad generales.



# Simulación de implementación:

Hoja de ruta de migración: desarrolle un plan detallado para migrar los componentes monolíticos identificados a microservicios. Este plan debe incluir la priorización de los servicios a migrar, la identificación de dependencias y una estrategia para la migración de datos.

Documentación de arquitectura: documente la arquitectura de microservicios propuesta, ilustrando la interacción entre los servicios y los pasos de migración. Incluya una breve narrativa que explique el fundamento de las decisiones clave.

# Solucion:
Para cambiar de monolitico a microservicios a contiuacion se presenta un posible diseño de microservicios para esta aplicación:
Se dividira en los siguientes microservicios: 

1. Microservicio de gestión de usuarios: Este microservicio se encargará de la gestión de perfiles de usuario, autenticación y autorización. Tendrá su propia base de datos para almacenar información del usuario.
2. Microservicio de catálogo de productos: Este microservicio manejará la lista de productos, incluyendo descripciones, precios, imágenes y niveles de inventario. Tendrá su propia base de datos para almacenar información del producto.  
3. Microservicio de procesamiento de pedidos: Este microservicio manejará todo el proceso de pedido, desde la gestión del carrito hasta la realización del pedido, el procesamiento de pagos y el seguimiento del historial de pedidos. Tendrá su propia base de datos para almacenar información del pedido.  
4. Microservicio de atención al cliente: Este microservicio manejará las consultas, devoluciones, quejas y comentarios de los clientes a través de un sistema basado en tickets. Tendrá su propia base de datos para almacenar información de los tickets.


# Aquí está un plan detallado para migrar los componentes monolíticos identificados a microservicios:  

1. Análisis de la aplicación monolítica existente: Antes de comenzar la migración, es importante entender completamente la aplicación monolítica existente. Esto incluye entender cómo se estructuran los datos, cómo se manejan las operaciones y cómo se manejan las dependencias entre diferentes partes de la aplicación.  
2. Identificación de los microservicios: Basándose en el análisis, identificar los microservicios potenciales. En este caso, ya se han identificado cuatro microservicios: gestión de usuarios, catálogo de productos, procesamiento de pedidos y atención al cliente.  
3. Priorización de los microservicios a migrar: Decidir el orden en el que se migrarán los microservicios. Una buena estrategia podría ser comenzar con el microservicio que tiene menos dependencias con otros microservicios. En este caso, podríamos comenzar con el microservicio de gestión de usuarios, ya que es probable que tenga menos dependencias que los otros.  
4. Diseño de la arquitectura de cada microservicio: Para cada microservicio, diseñar la arquitectura, incluyendo la base de datos, las operaciones que soportará y cómo se comunicará con otros microservicios.
5. Migración de la base de datos: Para cada microservicio, migrar los datos relevantes de la base de datos monolítica a la base de datos del microservicio. Esto puede implicar la creación de scripts de migración y la realización de pruebas exhaustivas para asegurarse de que los datos se migran correctamente.  
6. Implementación del microservicio: Implementar el microservicio, incluyendo todas las operaciones que soportará. Asegurarse de que el microservicio es funcionalmente equivalente a la parte correspondiente de la aplicación monolítica.  
7. Pruebas: Realizar pruebas exhaustivas para asegurarse de que el microservicio funciona correctamente y se integra correctamente con el resto de la aplicación.  
8. Despliegue del microservicio: Una vez que el microservicio ha sido probado y se ha confirmado que funciona correctamente, puede ser desplegado.  
9. Monitoreo y ajuste: Después del despliegue, es importante monitorear el microservicio para identificar y solucionar cualquier problema que pueda surgir. También puede ser necesario hacer ajustes basados en el rendimiento y la retroalimentación de los usuarios.  
10. Repetir para los siguientes microservicios: Una vez que se ha completado la migración de un microservicio, el proceso se repite para el siguiente microservicio en la lista de prioridades. 


# Documentación de arquitectura

Al diseñar la arquitectura de microservicios para la aplicación de comercio electrónico, se tomaron varias decisiones clave basadas en los principios de diseño de microservicios y las necesidades específicas de la aplicación.

1. Descomposición basada en dominios: Se decidió descomponer la aplicación monolítica en microservicios basados en dominios de negocio. Esto significa que cada microservicio se encarga de una parte específica del negocio (gestión de usuarios, catálogo de productos, procesamiento de pedidos, atención al cliente). Esta decisión se tomó para asegurar que cada microservicio sea lo más independiente posible y pueda evolucionar a su propio ritmo.  
2. Base de datos por servicio: Cada microservicio tiene su propia base de datos. Esto asegura que cada microservicio sea lo más desacoplado posible de los demás y pueda manejar sus propios datos de la manera que mejor se adapte a sus necesidades.  
3. Comunicación entre microservicios a través de APIs RESTful: Los microservicios se comunican entre sí a través de APIs RESTful. Esto proporciona un medio de comunicación simple y estandarizado que permite a los microservicios interactuar entre sí de manera eficiente.
4. Escalabilidad y tolerancia a fallos: Al descomponer la aplicación en microservicios, cada microservicio puede escalarse de forma independiente según sea necesario. Esto permite manejar eficientemente las cargas de trabajo altas y garantizar que la aplicación pueda seguir funcionando incluso si uno de los microservicios falla.  
5. Migración gradual: Se decidió migrar la aplicación monolítica a una arquitectura de microservicios de manera gradual. Esto permite minimizar el riesgo y asegurar que la aplicación siga funcionando correctamente durante el proceso de migración.

![arquitectura notificaciones.png](arquitectura%20notificaciones.png)