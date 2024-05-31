# Laboratorio 10
Laboratorio-Iron

# Análisis de código:
Tarea 1: Analizar cada fragmento de código para identificar posibles problemas de rendimiento. Busque problemas comunes como:
- Bucles ineficientes que pueden optimizarse o reescribirse.
- Cálculos redundantes que pueden simplificarse o eliminarse.
- Uso excesivo de memoria debido a malas elecciones de estructura de datos.
- Consultas de bases de datos no optimizadas que ralentizan la recuperación de datos.

Tarea 2: Documente sus hallazgos iniciales para cada fragmento, señalando cuáles cree que son las áreas clave de mejora.

# Implementación de optimizaciones:

Tarea 3: refactorice el código según su análisis. Aplicar técnicas de optimización como:
- Optimización de bucles para reducir la sobrecarga computacional.
- Implementar mecanismos de almacenamiento en caché efectivos cuando corresponda.
- Reducir la complejidad mejorando la lógica condicional o las llamadas a funciones.
- Optimización de estructuras de datos para una mejor gestión de la memoria.
- Mejora de las consultas a la base de datos para reducir los tiempos de carga.

Tarea 4: Asegúrese de que cada optimización esté documentada. Para cada cambio que realice, escriba una breve explicación de:
- Cuál era el problema específico.
- Cómo lo abordaste con tu optimización.
- Por qué elegiste ese enfoque en particular.

# Evaluación:
Tarea 5: compilar un informe que resuma las optimizaciones que implementó. El informe debe incluir:
- Una descripción de los problemas del código original.
- Explicaciones detalladas de los cambios realizados.
- Discusión sobre los resultados esperados u observados de estos cambios en términos de mejora del desempeño.

# Entregables:
- Un conjunto completo de fragmentos de código optimizados.
- Un informe escrito que detalla los cambios realizados, el razonamiento detrás de cada decisión y cualquier observación o resultado esperado relacionado con las mejoras en el desempeño.

Solucion:

# Fragmento de JavaScript:


```javascript   

// Inefficient loop handling and excessive DOM manipulation
function updateList(items) {
let list = document.getElementById("itemList");
list.innerHTML = "";
for (let i = 0; i < items.length; i++) {
let listItem = document.createElement("li");
listItem.innerHTML = items[i];
list.appendChild(listItem);
}
}
```


El código proporcionado es una función JavaScript que actualiza una lista de elementos en el DOM. Aquí hay algunos posibles problemas de rendimiento:

1. **Manipulación excesiva del DOM**: Cada vez que se llama a `appendChild`, se realiza una operación costosa de manipulación del DOM. Si la lista de elementos es grande, esto puede llevar a un rendimiento deficiente.

2. **Borrado y recreación de todos los elementos de la lista**: Independientemente de si los elementos han cambiado o no, la función borra todos los elementos de la lista y los recrea. Esto es ineficiente si solo algunos elementos han cambiado.

Para mejorar el rendimiento, podrías considerar las siguientes optimizaciones:

1. **Usar DocumentFragment para minimizar la manipulación del DOM**: En lugar de añadir cada elemento de la lista directamente al DOM, puedes añadirlos a un DocumentFragment y luego añadir el DocumentFragment al DOM. Esto realiza una sola operación de manipulación del DOM, independientemente del número de elementos de la lista.

2. **Actualizar solo los elementos que han cambiado**: En lugar de borrar y recrear todos los elementos de la lista, podrías intentar identificar cuáles elementos han cambiado y actualizar solo esos elementos.


Aquí está el código optimizado:

```javascript
function updateList(items) {
  let list = document.getElementById("itemList");
  let fragment = document.createDocumentFragment();
  for (let i = 0; i < items.length; i++) {
    let listItem = document.createElement("li");
    listItem.innerHTML = items[i];
    fragment.appendChild(listItem);
  }
  list.innerHTML = "";
  list.appendChild(fragment);
}
```

En este código, se crea un DocumentFragment y se añaden todos los elementos de la lista a este fragmento. Luego, se borran todos los elementos de la lista en el DOM y se añade el DocumentFragment a la lista. Esto realiza una sola operación de manipulación del DOM, lo que puede mejorar significativamente el rendimiento si la lista de elementos es grande.


# Fragmento de Java:

```java
// Redundant database queries
public class ProductLoader {
public List<Product> loadProducts() {
List<Product> products = new ArrayList<>();
for (int id = 1; id <= 100; id++) {
products.add(database.getProductById(id));
}
return products;
}
}
```

El código proporcionado realiza una consulta a la base de datos para cada producto, lo cual puede ser ineficiente si hay muchos productos. En lugar de hacer 100 consultas a la base de datos, podríamos hacer una sola consulta que obtenga todos los productos a la vez. Aquí está el código optimizado:

```java
import java.util.List;

public class ProductLoader {
    public List<Product> loadProducts() {
        return database.getProductsByIdRange(1, 100);
    }
}
```

En este código, se llama a un método `getProductsByIdRange` en el objeto `database`, que obtiene todos los productos con ID entre 1 y 100 en una sola consulta. Esto puede mejorar significativamente el rendimiento si la base de datos está optimizada para consultas de rango.

Por supuesto, este código asume que tienes un método `getProductsByIdRange` en tu objeto `database`. Si no lo tienes, tendrías que implementarlo. La implementación exacta de este método dependerá de cómo esté implementada tu base de datos.

# Fragmento de C#:
    
```csharp
// Unnecessary computations in data processing
public List<int> ProcessData(List<int> data) {
List<int> result = new List<int>();
foreach (var d in data) {
if (d % 2 == 0) {
result.Add(d * 2);
} else {
result.Add(d * 3);
}
}
return result;
}
```

El código proporcionado procesa una lista de datos, duplicando los números pares y triplicando los números impares. No parece haber cálculos redundantes en este código, ya que cada cálculo se realiza una vez por cada elemento de la lista de datos.

Sin embargo, si la lista de datos es muy grande, este código podría ser ineficiente debido a la creación de una nueva lista y la adición de elementos a esa lista uno por uno. En .NET, cuando se agrega un elemento a una lista y la capacidad actual de la lista no es suficiente para contener el nuevo elemento, la lista duplica su capacidad, lo que implica la creación de un nuevo arreglo y la copia de todos los elementos al nuevo arreglo. Esto puede ser costoso en términos de tiempo y memoria.

Para optimizar este código, podrías considerar las siguientes mejoras:

1. **Inicializar la lista con la capacidad necesaria**: Si conoces el número de elementos que se agregarán a la lista, puedes inicializar la lista con esa capacidad para evitar las costosas operaciones de redimensionamiento.

2. **Usar LINQ para procesar los datos**: LINQ (Language Integrated Query) es una característica de C# que permite procesar datos de manera eficiente y declarativa. Puedes usar LINQ para procesar los datos en lugar de usar un bucle `foreach`.

Aquí está el código optimizado:

```csharp
public List<int> ProcessData(List<int> data) {
    return data.Select(d => d % 2 == 0 ? d * 2 : d * 3).ToList();
}
```

En este código, se utiliza el método `Select` de LINQ para procesar cada elemento de la lista de datos. El resultado es una nueva lista que contiene los datos procesados. Este código es más eficiente y más fácil de leer que el código original.
