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


