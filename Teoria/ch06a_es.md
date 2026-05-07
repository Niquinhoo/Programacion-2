-"None"
layout: answer

title: "Capítulo SEIS"
subtitle: "Matrices, genéricos y colecciones"
exam_objectives:
  - "Cree matrices, colecciones List, Set, Map y Deque, y agregue, elimine, actualice, recupere y ordene sus elementos."
-"None"## Respuestas
**1. La respuesta correcta es D.**

**Explicación:**

- **A)**```
0 0 0 
0 0 0 
```- Esta opción es incorrecta porque los elementos de la matriz se inicializan y modifican dentro de los bucles. Los valores no son todos ceros.

- **B)**```
0 1 2 
0 1 2 
```- Esta opción es incorrecta porque cada fila se inicializa con valores incrementales basados ​​en la suma de índices, no idénticos para ambas filas.

- **C)**```
0 0 0 
1 1 1 
```- Esta opción es incorrecta porque los valores deben ser la suma del índice de la fila y el índice de la columna, no todos ceros o todos unos para la segunda fila.

- **D)**```
0 1 2 
1 2 3 
```- Esta es la respuesta correcta. Cada elemento de la matriz se establece en la suma de sus índices. Entonces, `arr[0][0] = 0 + 0 = 0`, `arr[0][1] = 0 + 1 = 1`, `arr[0][2] = 0 + 2 = 2`, `arr[1][0] = 1 + 0 = 1`, `arr[1][1] = 1 + 1 = 2`, `arr[1][2] = 1 + 2 = 3`.


**2. La respuesta correcta es B.**

**Explicación:**

- **A)**```java
public static T getFirstElement(T[] array) {
    return array[0];
}
```- Esta opción es incorrecta porque falta el tipo genérico `<T>` antes del tipo de retorno `T`.

- **B)**```java
public static <T> T getFirstElement(T[] array) {
    return array[0];
}
```- Esta es la respuesta correcta. El tipo genérico `<T>` se declara correctamente antes del tipo de retorno `T`.

- **C)**```java
public static <T> getFirstElement(T[] array) {
    return array[0];
}
```- Esta opción es incorrecta porque falta el tipo de retorno `T`.

- **D)**```java
public static <T> T[] getFirstElement(T[] array) {
    return array[0];
}
```- Esta opción es incorrecta porque el tipo de retorno es `T[]`, que no coincide con el tipo de retorno del método previsto.


**3. La respuesta correcta es D.**

**Explicación:**

**A)** El código compila e imprime:```
1 2 3
1.1 2.2 3.3
one two three
```- Esta opción es incorrecta. El código no se compila, por lo que no puede generar ningún resultado.

**B)** El código compila e imprime:```
1 2 3
1.1 2.2 3.3
```- Esta opción es incorrecta. Si bien este sería el resultado si se eliminara la línea `printList(strings)`, el código tal como está escrito no se compila.

**C)** El código no se compila debido a un error en el método `printList`.
  - Esta opción es incorrecta. El método `printList` está definido correctamente utilizando un comodín de límite superior `<? extiende Número>`.

**D)** El código no se compila debido a un error en el método `main`.
  - Esta opción es correcta. El código no se puede compilar debido a un error en el método "principal". `printList(strings)` provoca un error de compilación porque `String` no es una subclase de `Number`.

**E)** El código se compila pero genera una excepción de tiempo de ejecución cuando se ejecuta.
  - Esta opción es incorrecta. El código no se compila por lo que no se puede ejecutar.
  
  
**4. La respuesta correcta es A.** 

**Explicación:**

- **A)** `[A, B, E, C, D]`
  - Esta opción es correcta. El método `add` con un parámetro de índice inserta el elemento especificado en la posición especificada en la lista. Todos los elementos después de la posición especificada se desplazan hacia la derecha. Por lo tanto, `"E"` se inserta en el índice 2, empujando `"C"` y `"D"` hacia la derecha.

- **B)** `[A,E,B,C,D]`
  - Esta opción es incorrecta. Este sería el resultado si se agregara `"E"` en el índice 1, no en el índice 2.

- **C)** `[A, B, C, E, D]`
  - Esta opción es incorrecta. Este sería el resultado si se agregara `"E"` en el índice 3, no en el índice 2.

- **D)** `[A, B, C, D, E]`
  - Esta opción es incorrecta. Este sería el resultado si se agregara `"E"` al final de la lista, no en el índice 2.

- **E)** `[A, C, B, E, D]`
  - Esta opción es incorrecta. Esta secuencia no sigue el comportamiento adecuado del método `add` con índice 2. Parece una mezcla aleatoria y no corresponde a cómo se desplazan los elementos cuando se agrega un nuevo elemento.
  
  
**5. Las respuestas correctas son C y D.**.

**Explicación:**

- **A)** Un `Conjunto` permite elementos duplicados.
  - Esta opción es incorrecta. Una de las características principales de un "Conjunto" es que no permite elementos duplicados. Cada elemento debe ser único.

- **B)** Los elementos de un `Conjunto` se mantienen en el orden en que fueron insertados.
  - Esta opción es incorrecta. El orden de los elementos depende de la implementación específica de la interfaz "Set". Por ejemplo, "HashSet" no mantiene ningún orden, mientras que "LinkedHashSet" mantiene el orden de inserción y "TreeSet" mantiene un orden de clasificación.

- **C)** La interfaz `Set` incluye métodos para agregar, eliminar y verificar la presencia de elementos.
  - Esta opción es correcta. La interfaz `Set` proporciona métodos como `add()`, `remove()` y `contains()` para administrar sus elementos.

- **D)** La interfaz `Set` se implementa mediante clases como `HashSet`, `LinkedHashSet` y `TreeSet`.
  - Esta opción es correcta. `HashSet`, `LinkedHashSet` y `TreeSet` son implementaciones concretas de la interfaz `Set`, cada una con características diferentes en cuanto a orden y rendimiento.

- **E)** Un `Set` garantiza un rendimiento en tiempo constante para las operaciones básicas (agregar, eliminar, contener).
  - Esta opción es incorrecta. Esta afirmación es cierta específicamente para `HashSet`, que proporciona un rendimiento promedio en tiempo constante para estas operaciones. Sin embargo, no es cierto para todas las implementaciones de "Set". Por ejemplo, `TreeSet` proporciona rendimiento de tiempo logarítmico para estas operaciones porque se basa en un árbol Rojo-Negro.


**6. La respuesta correcta es C.**

**Explicación:**

- **A)** `[A, B, C, D]`
  - Esta opción es incorrecta. Esta opción ignora el orden en que se agregan los elementos al deque. Simplemente enumera los elementos en el orden en que aparecen agregados sin considerar los métodos "addFirst" y "addLast".

- **B)** `[C, B, A, D]`
  - Esta opción es incorrecta. Esta opción supone incorrectamente que `"A"` se agrega después de `"B"`; sin embargo, `addFirst("A")` coloca `"A"` en la segunda posición.

- **C)** `[C, A, B, D]`
  - Esta opción es correcta. De hecho, este es el resultado correcto. El método `addFirst("C")` coloca "C" al frente, `addFirst("A")` coloca `"A"` alla segunda posición, `addLast("B")` agrega `"B"` después de `"A"`, y `addLast("D")` agrega `"D"` al final. Por tanto, el orden final es "[C, A, B, D]".

- **D)** `[D, B, A, C]`
  - Esta opción es incorrecta. Esta opción muestra el orden inverso, que no coincide con cómo se agregan realmente los elementos al deque.

- **E)** `[A, C, B, D]`
  - Esta opción es incorrecta. Esta opción supone incorrectamente que `"A"` se agrega antes de `"C"` a pesar de que `"addFirst("C")` se llama después de `"addFirst("A")`.


**7. La respuesta correcta es D.**

**Explicación:**

- **A)** `{1=A, 2=B, 3=C, 2=D}`
  - Esta opción es incorrecta. Esta opción sugiere que el mapa mantendría claves duplicadas, lo que no es cierto para un "Mapa". Una clave solo puede tener un valor asociado a la vez.

- **B)** `{1=A, 2=B, 3=C}`
  - Esta opción es incorrecta. Esta opción ignora el hecho de que el valor asociado con la clave `2` se actualiza de `"B"` a `"D"`.

- **C)** `{1=A, 2=D, 3=C, 2=D}`
  - Esta opción es incorrecta. Esta opción nuevamente sugiere que el mapa puede tener claves duplicadas, lo cual no es así.

- **D)** `{1=A, 2=D, 3=C}`
  - Esta opción es correcta. El método `put` actualiza el valor asociado con una clave si la clave ya existe en el mapa. Por lo tanto, el valor asociado con la clave `2` se actualiza de `"B"` a `"D"`.

- **E)** `{1=A, 3=C, 2=B}`
  - Esta opción es incorrecta. Esta opción ignora la actualización del valor asociado con la clave `2` de `"B"` a `"D"`.


**8. La respuesta correcta es C.** 

**Explicación:**

- **A)**```
Alice 30  
Bob 25  
Charlie 35
```- Esta opción es incorrecta. Esta opción enumera los elementos en su orden original, no en el orden según la edad.

- **B)**```
Charlie 35  
Alice 30  
Bob 25
```- Esta opción es incorrecta. Esta opción enumera los elementos en orden descendente de edad, pero el método `compareTo` los ordena en orden ascendente de edad.

- **C)**```
Bob 25  
Alice 30  
Charlie 35
```- Esta opción es correcta. El método `compareTo` ordena los objetos `Persona` en orden ascendente según su edad. Por lo tanto, el orden de clasificación es "Bob (25)", "Alice (30)" y "Charlie (35)".

- **D)**```
Bob 25  
Charlie 35  
Alice 30
```- Esta opción es incorrecta. Esta opción no sigue correctamente el orden ascendente de edad.

- **E)**```
Alice 30  
Charlie 35  
Bob 25
```- Esta opción es incorrecta. Esta opción no sigue correctamente el orden ascendente de edad.


**9 .La respuesta correcta es A.**


**Explicación:**

- **A)**```
Bob 25  
Alice 30  
Charlie 35
```- Esta opción es incorrecta. El "AgeComparator" ordena los objetos "Persona" en orden ascendente según su edad. Por lo tanto, el orden de clasificación es "Bob (25)", "Alice (30)" y "Charlie (35)".

- **B)**```
Charlie 35  
Alice 30  
Bob 25
```- Esta opción es incorrecta. Esta opción enumera los elementos en orden descendente de edad, pero "AgeComparator" los ordena en orden ascendente de edad.

- **C)**```
Alice 30  
Bob 25  
Charlie 35
```- Esta opción es incorrecta. Esta opción no sigue correctamente el orden ascendente de edad.

- **D)**```
Bob 25  
Charlie 35  
Alice 30
```- Esta opción es incorrecta. Esta opción no sigue correctamente el orden ascendente de edad.

- **E)**```
Alice 30  
Charlie 35  
Bob 25
```- Esta opción es incorrecta. Esta opción no sigue correctamente el orden ascendente de edad.