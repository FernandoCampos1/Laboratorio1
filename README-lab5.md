# Laboratorio 5
Laboratorio-Iron

Tareas:
1. Optimización de consultas SQL
   A los participantes se les dan consultas SQL y se les pide que las mejoren basándose en conocimientos teóricos de optimización de bases de datos.

Consultas SQL proporcionadas para la optimización:

Consulta de pedidos: recupere pedidos con muchos artículos y calcule el precio total.
Copiar
SELECT Orders.OrderID, SUM(OrderDetails.Quantity * OrderDetails.UnitPrice) AS TotalPrice
FROM Orders
JOIN OrderDetails ON Orders.OrderID = OrderDetails.OrderID
WHERE OrderDetails.Quantity > 10
GROUP BY Orders.OrderID;
Explica este código

Consulta del cliente: busque todos los clientes de Londres y ordénelos por Nombre del cliente.
Copiar
SELECT CustomerName FROM Customers WHERE City = 'London' ORDER BY CustomerName;
Explica este código

Tareas de optimización:

Los participantes identifican ineficiencias y proponen modificaciones para optimizar estas consultas, como indexar columnas críticas, reestructurar uniones o ajustar filtros.
Se analiza la aplicación teórica de índices o la reescritura de subconsultas para reducir teóricamente la carga computacional.
2. Implementación de consultas NoSQL
   Los participantes optimizarán teóricamente las consultas NoSQL proporcionadas, centrándose en la recuperación eficiente de datos y la latencia minimizada.

Consultas NoSQL para revisión:

Consulta de publicaciones de usuario: recupere las publicaciones activas más populares y muestre su título y recuento de me gusta.
Copiar
db.posts
.find({ status: "active" }, { title: 1, likes: 1 })
.sort({ likes: -1 });
Explica este código

Agregación de datos de usuario: resuma la cantidad de usuarios activos por ubicación.
Copiar
db.users.aggregate([
{ $match: { status: "active" } },
{ $group: { _id: "$location", totalUsers: { $sum: 1 } } },
]);
Explica este código

Tarea de optimización:

Proponer cambios para mejorar el rendimiento de las consultas según la estructura del documento y las estrategias de indexación.
Las sugerencias pueden incluir modificar el esquema del documento, agregar índices u optimizar los canales de agregación.
