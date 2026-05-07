-"None"
layout: answer

title: "Capítulo OCHO"
subtitle: "Interfaces funcionales y expresiones Lambda"
exam_objectives:
  - "Utilice objetos Java y flujos primitivos, incluidas expresiones lambda que implementan interfaces funcionales, para crear, filtrar, transformar, procesar y ordenar datos."
-"None"## Respuestas
**1. Las respuestas correctas son B y D.**

**Explicación:**

- **A)** Una interfaz funcional puede tener múltiples métodos "abstractos".
  - Esta opción es incorrecta. Una interfaz funcional sólo puede tener un método abstracto. Tener múltiples métodos abstractos lo descalificaría como interfaz funcional.

- **B)** Una interfaz funcional puede tener métodos predeterminados y "estáticos".
  - Esta opción es correcta. Una interfaz funcional puede tener métodos estáticos y predeterminados, que no se cuentan como métodos abstractos.

- **C)** La anotación `@FunctionalInterface` es obligatoria para declarar una interfaz funcional. 
  - Esta opción es incorrecta. La anotación `@FunctionalInterface` no es obligatoria; es sólo un marcador para indicar que la interfaz está destinada a ser una interfaz funcional. Una interfaz puede ser una interfaz funcional sin esta anotación siempre que tenga exactamente un método abstracto.

- **D)** Las expresiones Lambda se pueden utilizar para crear instancias de interfaces funcionales.
  - Esta opción es correcta. Las expresiones Lambda se utilizan para proporcionar implementaciones para el método abstracto único de una interfaz funcional, lo que las convierte en una característica clave para la programación funcional en Java.


**2. La respuesta correcta es A.**

**Explicación:**

- **A)** `(s1, s2) -> s1.compareTo(s2)`
  - Esta opción es correcta. Esta expresión lambda implementa correctamente la interfaz `Comparator<String>`. Utiliza la sintaxis correcta para una expresión lambda, con parámetros entre paréntesis y una única expresión para el cuerpo.

- **B)** `(Cadena s1, s2) -> s1.compareTo(s2)`
  - Esta opción es incorrecta. La sintaxis no es válida porque si especifica el tipo de un parámetro, debe especificar el tipo para todos los parámetros. Debería ser `(Cadena s1, Cadena s2)`.

- **C)** `s1, s2 -> s1.compareTo(s2)`
  - Esta opción es incorrecta. Los parámetros deben estar entre paréntesis. La sintaxis correcta es `(s1, s2)`.

- **D)** `(s1, s2) -> devolver s1.compareTo(s2);`
  - Esta opción es incorrecta. Cuando utilice una declaración de devolución, también debe incluir llaves.

- **E)** `(s1, s2) -> { s1.compareTo(s2); }`
  - Esta opción es incorrecta. Cuando utilice llaves, debe incluir una declaración de devolución para las expresiones que devuelven un valor. La sintaxis correcta sería `(s1, s2) -> { return s1.compareTo(s2); }`.


**3. La respuesta correcta es B.**

**Explicación:**

- **A)** `java.util.función.Función`
  - Esta opción es incorrecta. `Función` representa una función que toma un argumento y produce un resultado.

- **B)** `java.util.function.BiFunction`
  - Esta opción es correcta. `BiFunction` representa una función que toma dos argumentos y produce un resultado.

- **C)** `java.util.function.Proveedor`
  - Esta opción es incorrecta. `Proveedor` representa una función que no toma argumentos y produce un resultado.

- **D)** `java.util.función.Consumidor`
  - Esta opción es incorrecta. `Consumidor` representa una función que toma un argumento y no produce un resultado.

- **E)** `java.util.function.Predicado`
  - Esta opción es incorrecta. `Predicado` representa una función que toma un argumento y devuelve un valor `booleano`.


**4. La respuesta correcta es A.**

**Explicación:**

- **A)** `13`
  - Esta opción es correcta. La `combinedFunction` primero multiplica 5 por 2 para obtener 10, luego suma 3, lo que da como resultado 13.

- **B)** `16`
  - Esta opción es incorrecta. Asume incorrectamente que se suma 5 después de duplicar y duplicar nuevamente.

- **C)** `10`
  - Esta opción es incorrecta. Representa solo el resultado de la primera función sin aplicar la segunda función.

- **D)** `11`
  - Esta opción es incorrecta. Parece representar erróneamente 5 más la primera función (doble).

- **E)** `8`
  - Esta opción es incorrecta. Parece representar incorrectamente el valor de entrada duplicado sin sumar 3.



**5. La respuesta correcta es C.**

**Explicación:**

- **A)** `Cadena::valorDe` 
  - Esta opción es incorrecta. `String::valueOf` convierte un número entero en una cadena, no una cadena en un número entero.

- **B)** `Entero::valorDe`
  - Esta opción es incorrecta. `Integer::valueOf` devuelve un objeto `Integer`, mientras que lambda devuelve un `int`.

- **C)** `Entero::parseInt`
  - Esta opción es correcta. `Integer::parseInt` es una referencia de método que coincide con la expresión lambda `str -> Integer.parseInt(str)` que convierte una cadena en un número entero.

- **D)** `Cadena::parseInt`
  - Esta opción es incorrecta. La clase `String` no tiene un método `parseInt`.

- **E)** `Entero::toString`
  - Esta opción es incorrecta. `Integer::toString` convierte un número entero en una cadena, no una cadena en un número entero.