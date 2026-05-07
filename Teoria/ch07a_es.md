-"None"
layout: answer

title: "Capítulo SIETE"
subtitle: "Manejo de errores y excepciones"
exam_objectives:
  - "Maneje excepciones usando try/catch/finally, try-with-resources y bloques multi-catch, incluidas excepciones personalizadas."
-"None"## Respuestas
**1. La respuesta correcta es B.**

**Explicación:**

- **A.** Una excepción marcada es un tipo de excepción que hereda de la clase `java.lang.RuntimeException`.
  - Esta opción es incorrecta. Una excepción marcada no hereda de `java.lang.RuntimeException`. Las excepciones marcadas son subclases de `java.lang.Exception` pero no de `java.lang.RuntimeException`.

- **B.** Una excepción marcada debe detectarse o declararse en la firma del método utilizando la palabra clave `throws`.
  - Esta opción es correcta. Las excepciones marcadas deben detectarse mediante un bloque `try-catch` o declararse en la firma del método con la palabra clave `throws`. Esto es para garantizar que la excepción se maneje correctamente en algún punto del código.

- **C.** Una excepción marcada es un error que generalmente es causado por el entorno en el que se ejecuta la aplicación y que la aplicación no puede manejar.
  - Esta opción es incorrecta. Describe los errores con mayor precisión que las excepciones comprobadas. Los errores suelen ser causados ​​por el entorno y no se espera que la aplicación los maneje.

- **D.** La máquina virtual Java puede generar una excepción marcada cuando se produce un error grave, como un error de falta de memoria.
  - Esta opción es incorrecta. Describe errores en lugar de excepciones comprobadas. Los errores como los de falta de memoria los genera la JVM y, en la mayoría de los casos, las aplicaciones no deben detectarlos ni manejarlos.


**2. La respuesta correcta es A.**

**Explicación:**

- **A.** Este código define una excepción marcada personalizada y la genera y maneja correctamente.
  - Esta opción es correcta. El código define una excepción marcada personalizada extendiendo "Exception". El método `methodThatThrowsException` genera esta excepción personalizada, que luego se detecta y maneja en el método `main`.

- **B.** Este código define una excepción personalizada no marcada. 
  - Esta opción es incorrecta. El código extiende `Exception`, no `RuntimeException`, lo que la convierte en una excepción marcada en lugar de una no marcada.

- **C.** Este código no se compilará porque la excepción personalizada no está declarada correctamente en la firma del método.
  - Esta opción es incorrecta. La excepción personalizada está declarada correctamente en la firma del método `methodThatThrowsException`, por lo que se compilará sin problemas.

- **D.** Este código se compilará pero no generará la excepción personalizada en tiempo de ejecución.
  - Esta opción es incorrecta. El código generará la excepción personalizada en tiempo de ejecución como se esperaba y será capturada y manejada en el bloque "catch".


**3. La respuesta correcta es B.**

**Explicación:**

- **A.** `1`
  - Esta opción es incorrecta. Aunque el bloque "catch" devuelve "1", el bloque "finalmente" anulará este valor de retorno con "2".

- **B.** `2`
  - Esta opción es correcta. El bloque `finally` siempre se ejecuta y su valor de retorno anula el valor de retorno del bloque `catch`, lo que da como resultado que se imprima `2`.

- **C.** La compilación falla
  - Esta opción es incorrecta. El código se compila sin errores.

- **D.** Se produce una excepción en tiempo de ejecución.
  - Esta opción es incorrecta. Mientras se lanza una `RuntimeException` en el bloque `try`, el bloque `catch` la detecta y no se propaga ninguna excepción que cause un error de tiempo de ejecución.


**4. La respuesta correcta es D.**

**Explicación:**

- **A.** El código no se compila correctamente.
  - Esta opción es incorrecta. El código se compila correctamente. Un bloque "try" puede ir seguido de un bloque "finally" sin un bloque "catch".

- **B.** El código se compilaría correctamente si agregamos un bloque `catch`.
  - Esta opción es incorrecta. Si bien agregar un bloque `catch` es válido, no es necesario que el código se compile. El bloque "try" se puede usar solo con un bloque "finally".

- **C.** El código se compilaría correctamente si eliminamos el bloque `finalmente`.
  - Esta opción es incorrecta. No es necesario eliminar el bloque "finalmente" para que el código se compile. El código es válido con el bloque "finalmente" presente.

- **D.** El código se compila correctamente tal como está.
  - Esta opción es correcta.El código se compila correctamente tal como está. Un bloque "try" debe ir seguido de un bloque "catch", un bloque "finally" o ambos. 


**5. Las respuestas correctas son C y D.**

**Explicación:**

- **A.** En un `try-with-resources`, se requiere el bloque `catch`.
  - Esta opción es incorrecta. En una declaración `try-with-resources`, el bloque catch es opcional. El objetivo principal de "probar con recursos" es garantizar que cada recurso se cierre al final de la declaración, ya sea que se produzca una excepción o no.

- **B.** La palabra clave `throws` se utiliza para generar una excepción. 
  - Esta opción es incorrecta. La palabra clave `throws` se usa en las declaraciones de métodos para especificar que el método puede generar una excepción, no para generar una excepción. La palabra clave `throw` se utiliza para generar una excepción.

- **C.** En un bloque `try-with-resources`, si declaras más de un recurso, deben estar separados por un punto y coma.
  - Esta opción es correcta. En un bloque `try-with-resources`, si declaras más de un recurso, deben estar separados por un punto y coma.

- **D.** Si se define un bloque `catch` para una excepción que el código en el bloque `try` no pudo generar, se genera un error en tiempo de compilación.
  - Esta opción es correcta. Si se define un bloque `catch` para una excepción que no puede ser lanzada por el código en el bloque `try`, el compilador generará un error porque el bloque `catch` es inalcanzable.


**6. La respuesta correcta es E.**

**Explicación:**

- **A.** `Cerrar excepción`
  - Esta opción es incorrecta. Si bien se producirá la `IOException` de `close()`, será suprimida por la `RuntimeException`.

- **B.** `Excepción de tiempo de ejecución`
  - Esta opción es incorrecta. La excepción principal es `RuntimeException`, pero no imprimirá su mensaje directamente porque el bloque catch no lo maneja.

- **C.** `RuntimeException` y luego `CloseException` 
  - Esta opción es incorrecta. Aunque se producen ambas excepciones, la `RuntimeException` es la principal y la `IOException` está suprimida. Ambos mensajes no se imprimen en secuencia.

- **D.** La compilación falla
  - Esta opción es incorrecta. El código se compila sin errores.

- **E.** Se imprime el seguimiento de la pila de una excepción no detectada.
  - Esta opción es correcta. La `RuntimeException` lanzada en el bloque try no es capturada por el bloque `catch (IOException e)`. Por lo tanto, se imprime el seguimiento de la pila de "RuntimeException".


**7. Las respuestas correctas son B y C.**

**Explicación:**

- **A.** `java.io.FileNotFoundException` es incorrecto. Es una subclase de `java.io.IOException`, que a su vez es una subclase de `java.lang.Exception`, lo que la convierte en una excepción marcada.

- **B.** `java.lang.ArithmeticException` es correcto. Es una subclase directa de `java.lang.RuntimeException` y representa errores aritméticos como la división por cero.

- **C.** `java.lang.ClassCastException` es correcto. Es una subclase directa de `java.lang.RuntimeException` e indica una operación de conversión no válida.

- **D.** `java.lang.InterruptedException` es incorrecto. Es una subclase directa de `java.lang.Exception`, lo que la convierte en una excepción marcada. Indica que un hilo ha sido interrumpido.


**8. La respuesta correcta es D.**

**Explicación:**

- **A.** Sólo se imprime `"Probar excepción de bloqueo"`.
  - Esta opción es incorrecta. La `Try Block Exception` es la excepción principal y no se imprime directamente porque el bloque `catch` comprueba primero las excepciones suprimidas.

- **B.** Sólo se imprime `"Cerrar excepción"`.
  - Esta opción es incorrecta. La "Excepción de cierre" no se imprime directamente; se suprime y se accede a él mediante el método `getSuppressed`.

- **C.** Se imprimen tanto `"Probar excepción de bloqueo"` como `"Cerrar excepción"`.
  - Esta opción es incorrecta. El código solo imprime excepciones suprimidas, no directamente el mensaje de excepción principal.

- **D.** Se imprime `"Suprimido: Cerrar excepción"`.
  - Esta opción es correcta. La `RuntimeException` lanzada en el bloque `try` es la excepción principal, y la `RuntimeException` del método `close` es complementaria.presionado. El bloque `catch` imprime el mensaje de excepción suprimida, `"Suprimido: Cerrar excepción"`.