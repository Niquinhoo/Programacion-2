---

layout: answer

title: "Capítulo CINCO"
subtitle: "Controlar el flujo del programa"
exam_objectives:
  - "Cree construcciones de control de flujo de programas que incluyan if/else, declaraciones y expresiones de cambio, bucles y declaraciones de interrupción y continuación."
  - "Implemente la herencia, incluidos tipos abstractos y sellados, así como clases de registros. Anular métodos, incluido el de la clase Objeto. Implementar polimorfismo y diferenciar entre tipo de objeto y tipo de referencia. Realice la conversión de tipos de referencia, identifique tipos de objetos utilizando el operador instancia de y la coincidencia de patrones con el operador instancia de y la construcción switch."
---
## Respuestas
**1. La respuesta correcta es A.**

**Explicación:**

- **A)** `x está entre 5 y 20`
  - Esta opción es correcta. El valor de `x` es 10, lo que satisface ambas condiciones en las declaraciones `if` anidadas (`x > 5` y `x < 20`). Por lo tanto, el programa imprime `"x está entre 5 y 20"`.

- **B)** `x es 5 o menos` 
  - Esta opción es incorrecta. El valor de `x` es 10, lo que no satisface la condición `x <= 5` en el bloque `else`. Por lo tanto, este mensaje no se imprimirá.

- **C)** `x es mayor que 20`
  - Esta opción es incorrecta. El valor de `x` es 10, lo que no satisface la condición `x > 20`. Por lo tanto, este mensaje no se imprimirá.

- **D)** El programa no compila 
  - Esta opción es incorrecta. El programa se compila correctamente sin errores.

- **E)** El programa se compila pero no produce ningún resultado.
  - Esta opción es incorrecta. El programa produce una salida porque el valor de `x` satisface las condiciones dentro de las declaraciones `if` anidadas, lo que lleva a la salida `"x está entre 5 y 20"`.
  

**2. La respuesta correcta es D.**

**Explicación:**

- **A)**

```java
if (emp instanceof Employee) {
    var (id, Person(name, age)) = emp;
    System.out.println(name + " is " + age + " years old.");
}

```

- Esta opción es incorrecta. Si bien intenta utilizar la desestructuración, esta sintaxis no es válida en Java. Java no admite la asignación de desestructuración de esta manera.

- **B)**

```java
if (emp instanceof Employee(_, Person(var name, var age))) {
    System.out.println(name + " is " + age + " years old.");
}

```

- Esta opción es incorrecta. Utiliza el guión bajo (`_`) para ignorar el campo `id`, que no es una técnica válida en Java 21.

- **C)**

```java
if (emp instanceof Employee e) {
    System.out.println(e.person().name() + " is " + e.person().age() + " years old.");
}

```

- Esta opción es incorrecta. Utiliza la tradicional "instancia de" sin coincidencia de patrones, confiando en métodos de acceso para extraer los datos.

- **D)**

```java
if (emp instanceof Employee(var id, Person(var name, var age))) {
    System.out.println(name + " is " + age + " years old.");
}

```

- Esta opción es correcta. Utiliza una coincidencia de patrones de registros anidados para extraer los datos de "Empleado" y "Persona" en un solo paso. Utiliza `var` para la inferencia de tipos y nombra correctamente las variables `nombre` y `edad` según sea necesario.

- **E)**

```java
if (emp instanceof Employee(var id, var person)) {
    System.out.println(person.name() + " is " + person.age() + " years old.");
}

```

- Esta opción es incorrecta. Si bien utiliza la coincidencia de patrones para el registro "Empleado", no anida la coincidencia de patrones para el registro "Persona", por lo que aún requiere llamar a métodos de acceso en "persona".



**3. La respuesta correcta es C.**



**Explicación:**

- **A)** Fragmento de código 1
  - Esta opción es incorrecta. La variable `y` se declara dentro de la primera declaración `if` y no es accesible fuera de su bloque. Por lo tanto, intentar imprimir `y` fuera de su alcance resulta en un error de compilación.

- **B)** Fragmento de código 2
  - Esta opción es incorrecta. La variable `z` se declara dentro de la segunda declaración `if` y no es accesible fuera de su bloque. Por lo tanto, intentar utilizar `z` fuera de su alcance resulta en un error de compilación.

- **C)** Fragmento de código 3
  - Esta opción es correcta. La variable `a` se declara fuera de la declaración `if`, por lo que es accesible tanto dentro como fuera del bloque `if`. Se permite reasignar un dentro del bloque "if".

- **D)** Ninguno de los anteriores
  - Esta opción es incorrecta. Si bien es cierto que los fragmentos de código 1, 2 y 4 no se compilarán, el fragmento de código 3 sí se compila sin errores. Por tanto, la respuesta no puede ser "ninguna de las anteriores".


**4. La respuesta correcta es C.**

**Explicación:**

- **A)** `Fin de semana` 
  - Esta opción es incorrecta. El valor de `dayOfWeek` es 3, lo que no coincide con los casos 1 o 7, por lo que no imprime `"Weekend"`.

- **B)** `Día no válido`
  - Esta opción es incorrecta. El caso predeterminado no se ejecuta porque el valor de `dayOfWeek` coincide con uno de los casos específicos (2, 3, 4, 5 o 6).

- **C)** `Día laborable`
  - Esta opción es correcta. El valor de `dayOfWeek` es 3, lo que coincide con el caso 3. Por lo tanto, la variable `dayType` se establece en `"Weekday"` y este valor se imprime.

- **D)** El programa no compila
  - Esta opción es incorrecta. El programa se compila sin errores.

- **E)** El programa se compila pero no produce ningún resultado.
  - Esta opción es incorrecta. El programa compila y produce una salida, que es "Día de la semana" según el valor de "día de la semana" dado.


**5. La respuesta correcta es B.**

**Explicación:**

- **A)** `A`
  - Esta opción es incorrecta. El valor de `score` es 85, lo que no coincide con los casos de 90 o 100. Por lo tanto, no imprime `"A"`.

- **B)** `F`
  - Esta opción es correcta. El caso predeterminado se ejecuta porque el valor de `score` no coincide con ninguna de las otras declaraciones de `case`.

- **C)** El programa no compila 
  - Esta opción es incorrecta. El programa utiliza una expresión `switch` correctamente, compilando sin errores.

- **D)** `B`
  - Esta opción es incorrecta. El valor de "puntuación" es 85, lo que no coincide con el caso de 80 u 89.

- **E)** El programa se compila pero no produce ningún resultado.
  - Esta opción es incorrecta. El programa compila y produce una salida, que es `"F"` según el valor de `puntuación` dado.


**6. La respuesta correcta es A.**

**Explicación:**

- **A)**

```java
case CarType.SEDAN, CarType.HATCHBACK -> System.out.println("Compact vehicle");
case CarType.SUV -> System.out.println("Large vehicle");
case CarType.CONVERTIBLE -> System.out.println("Open-top vehicle");

```

- Esta opción es correcta. En Java 21, puede usar nombres completos de constantes de enumeración en declaraciones de cambio, incluso cuando la expresión del selector es de un tipo que es compatible con la asignación con el tipo de enumeración (en este caso, `Vehicle` es compatible con la asignación con `CarType`).

- **B)**

```java
case SEDAN, HATCHBACK -> System.out.println("Compact vehicle");
case SUV -> System.out.println("Large vehicle");
case CONVERTIBLE -> System.out.println("Open-top vehicle");

```

- Esta opción es incorrecta. Cuando utilice un tipo de interfaz ("Vehículo") como expresión de selección, debe utilizar nombres completos para las constantes de enumeración. El uso de nombres no calificados (`SEDAN`, `HATCHBACK`, etc.) generará un error de compilación.

- **C)**

```java
case CarType.SEDAN || CarType.HATCHBACK -> System.out.println("Compact vehicle");
case CarType.SUV -> System.out.println("Large vehicle");
case CarType.CONVERTIBLE -> System.out.println("Open-top vehicle");

```

- Esta opción es incorrecta. Intenta utilizar el operador lógico OR (`||`) en la etiqueta del caso, que no es una sintaxis válida para declaraciones de cambio. Las etiquetas de casos múltiples deben estar separadas por comas, no por operadores lógicos.

- **D)**

```java
case Vehicle.SEDAN, Vehicle.HATCHBACK -> System.out.println("Compact vehicle");
case Vehicle.SUV -> System.out.println("Large vehicle");
case Vehicle.CONVERTIBLE -> System.out.println("Open-top vehicle");

```

- Esta opción es incorrecta. Aunque utiliza nombres completos, antepone incorrectamente las constantes de enumeración con "Vehicle" en lugar de "CarType". Las constantes de enumeración pertenecen a la enumeración `CarType`, no a la interfaz `Vehicle`, por lo que esto provocará un error de compilación.


**7. La respuesta correcta es D.**

**Explicación:**

- **A)**

```java
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case null -> 0;

```

- Esta opción es incorrecta. No se compila porque la expresión `switch` no es exhaustiva, no cubre todos los valores posibles de `Shape`.

- **B)**

```java
default -> 0;
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case Triangle t -> 0.5 * t.base() * t.height();

```

- Esta opción es incorrecta. No se compila porque el caso "predeterminado" (innecesario) viene antes que el resto de las declaraciones "caso".

- **C)**

```java
case Shape s when s instanceof Circle ->
        Math.PI * ((Circle)s).radius() * ((Circle)s).radius();
case Shape s when s instanceof Square ->
        ((Square)s).side() * ((Square)s).side();
case Shape s when s instanceof Triangle ->
        0.5 * ((Triangle)s).base() * ((Triangle)s).height();

```

- Esta opción es incorrecta. No compila porque no es exhaustivo. Dado que utiliza comprobaciones detalladas de `instanceof` en lugar de aprovechar la coincidencia de patrones, le falta una rama `default`.

- **D)**

```java
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case Triangle t -> 0.5 * t.base() * t.height();

```

- Esta opción es correcta. Cubre todos los subtipos posibles de la interfaz "Shape" sellada sin un caso "predeterminado" innecesario.


**8. La respuesta correcta es B.**

**Explicación:**

- **A)** `2`
  - Esta opción es incorrecta. El valor de "count" se incrementa hasta llegar a 3. La declaración etiquetada "break" sale del bucle externo cuando "count" es igual a 3.

- **B)** `3`
  - Esta opción es correcta. El valor de "count" se incrementa dentro del bucle interno " while ". Cuando `count` llega a 3, se ejecuta la instrucción etiquetada `break` (`break externalLoop`), lo que hace que el control salga del bucle externo. Por lo tanto, "count" es 3 cuando se imprime.

- **C)** `4`
  - Esta opción es incorrecta. El ciclo no continúa incrementando "count" a 4 porque la declaración etiquetada "break" sale del ciclo cuando "count" es 3.

- **D)** `5`
  - Esta opción es incorrecta. El ciclo no continúa incrementando "count" a 5 porque la declaración etiquetada "break" sale del ciclo cuando "count" es 3.

- **E)** El programa no compila
  - Esta opción es incorrecta. El programa se compila correctamente y se ejecuta sin errores.


**9. La respuesta correcta es C.**

**Explicación:**

- **A)** `5`
  - Esta opción es incorrecta. El valor 5 es sólo el límite superior del bucle y no la suma de los números enteros del 1 al 5.

- **B)** `10`
  - Esta opción es incorrecta. El valor 10 es menor que la suma de los números enteros del 1 al 5.

- **C)** `15`
  - Esta opción es correcta. El bucle se repite del 1 al 5, sumando cada valor de "i" a la "suma". Los cálculos son los siguientes: 1 + 2 + 3 + 4 + 5 = 15.

- **D)** `20`
  - Esta opción es incorrecta. El valor 20 es mayor que la suma de los números enteros del 1 al 5.

- **E)** El programa no compila
  - Esta opción es incorrecta. El programa se compila correctamente y se ejecuta sin errores.


**10. La respuesta correcta es A.**

**Explicación:**

- **A)** `9`
  - Esta opción es correcta. La declaración `continuar` omite la iteración actual cuando el número es par (`num % 2 == 0`). Los números impares en la matriz son 1, 3 y 5. Su suma es 1 + 3 + 5 = 9.

- **B)** `10`
  - Esta opción es incorrecta. La suma de los números impares (1, 3 y 5) es 9, no 10.

- **C)** `12`
  - Esta opción es incorrecta. La suma de los números impares (1, 3 y 5) es 9, no 12.

- **D)** `15`
  - Esta opción es incorrecta. La suma de todos los números en la matriz (1 + 2 + 3 + 4 + 5) es 15, pero la instrucción "continuar" hace que el bucle omita la suma de los números pares.

- **E)** El programa no compila
  - Esta opción es incorrecta. El programa se compila correctamente y se ejecuta sin errores.