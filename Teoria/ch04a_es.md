---

layout: answer

title: "Capítulo CUATRO"
subtitle: "Trabajar con datos"
exam_objectives:
  - "Utilice primitivas y clases contenedoras. Evalúe expresiones aritméticas y booleanas utilizando la API de matemáticas y aplicando reglas de precedencia, conversiones de tipos y conversión."
  - "Manipule texto, incluidos bloques de texto, utilizando las clases String y StringBuilder."
---
## Respuestas
**1. La respuesta correcta es C.**

**Explicación:**

- **A)** Un `doble` puede asignarse directamente a un `flotante` sin realizar casting. 
  - Esta opción es incorrecta. Un "doble" no se puede asignar directamente a un "flotador" sin lanzarlo porque "doble" tiene un alcance y precisión mayores que un "flotador".

- **B)** Un `booleano` se puede convertir en un `int`.
  - Esta opción es incorrecta. Los valores "booleanos" no se pueden convertir a "int" en Java. No son tipos compatibles.

- **C)** Se puede asignar una `Cadena` a una variable de referencia `Objeto`.
  - Esta opción es correcta. Una `Cadena` es una instancia de la clase `Objeto` y, por lo tanto, se puede asignar a una variable de referencia `Objeto`.

- **D)** Un `char` es un tipo de datos de referencia. 
  - Esta opción es incorrecta. `char` es un tipo de datos primitivo, no un tipo de datos de referencia.

- **E)** Un `int` puede almacenar un valor `long` sin ninguna conversión explícita.
  - Esta opción es incorrecta. un `int` no puede almacenar un valor `long` sin una conversión explícita porque `long` tiene un rango mayor que `int`.


**2. La respuesta correcta es A.**

**Explicación:**

Desglosemos la expresión `a + b * c / a - b` paso a paso según el orden de las operaciones:

1. **La multiplicación y la división** se realizan primero de izquierda a derecha:
   - `b * c` = `10 * 15` = `150`
   - `150/a` = `150/5` = `30`

2. **La suma y la resta** se realizan a continuación de izquierda a derecha:
   - `a + 30` = `5 + 30` = `35`
   - `35 - b` = `35 - 10` = `25`

Entonces, el valor de "resultado" es "25" y el programa imprime "25".

- **A)** `25`
  - Esta opción es correcta.

- **B)** `35`
  - Esta opción es incorrecta.

- **C)** `20` 
  - Esta opción es incorrecta.

- **D)** `15` 
  - Esta opción es incorrecta.


**3. La respuesta correcta es D.**

**Explicación:**

- **A)** Los objetos `StringBuilder` son inmutables.
  - Esta opción es incorrecta. Los objetos `StringBuilder` son mutables, lo que significa que se pueden cambiar después de su creación.

- **B)** Los objetos `String` se pueden modificar después de su creación. 
  - Esta opción es incorrecta. Los objetos `String` son inmutables, lo que significa que una vez que se crea un objeto `String`, no se puede modificar. Cualquier modificación da como resultado un nuevo objeto "String".

- **C)** `StringBuilder` está sincronizado y es seguro para subprocesos.
  - Esta opción es incorrecta. `StringBuilder` no está sincronizado y no es seguro para subprocesos. Si se requiere sincronización, se debe utilizar `StringBuffer` en su lugar.

- **D)** `StringBuilder` proporciona métodos para secuencias mutables de caracteres.
  - Esta opción es correcta. `StringBuilder` proporciona métodos para una secuencia mutable de caracteres, lo que permite la modificación del objeto sin crear nuevas instancias.

- **E)** `String` y `StringBuilder` tienen las mismas características de rendimiento para la manipulación de cadenas.
  - Esta opción es incorrecta. `String` y `StringBuilder` no tienen las mismas características de rendimiento para la manipulación de cadenas. `StringBuilder` es generalmente más eficiente para este tipo de operaciones porque es mutable y no crea nuevas instancias con cada modificación.


**4. Las respuestas correctas son A y B.**

**Explicación:**

- **A)** Los bloques de texto pueden abarcar varias líneas sin necesidad de secuencias de escape para líneas nuevas.
  - Esta opción es correcta. De hecho, los bloques de texto pueden abarcar varias líneas sin necesidad de secuencias de escape para nuevas líneas, lo que facilita el trabajo con cadenas de varias líneas.


- **B)** Los bloques de texto conservan el formato exacto, incluidos los espacios en blanco, del código tal como está escrito.
  - Esta opción es correcta. Los bloques de texto conservan el formato exacto, incluidos los espacios en blanco, del código tal como está escrito. Esto es útil para mantener el diseño original del texto.


- **C)** Los bloques de texto solo se pueden usar dentro de los métodos.
  - Esta opción es incorrecta. Los bloques de texto se pueden usar en cualquier lugar donde se pueda usar una "Cadena" normal, no solo dentro de los métodos. Pueden ser parte de campos de clase, parámetros de métodos, etc.


- **D)** Los bloques de texto recortan automáticamente los espacios en blanco iniciales y finales de cada línea. 
  - Esta opción es incorrecta. Los bloques de texto no se automatizanrecorte automáticamente los espacios en blanco iniciales y finales de cada línea. Conservan el espacio en blanco exacto tal como está escrito en el código.

- **E)** Los bloques de texto requieren un nivel de sangría mínimo de un espacio.
  - Esta opción es incorrecta. Los bloques de texto no requieren un nivel de sangría mínimo de un espacio. La sangría inicial común a todas las líneas se elimina automáticamente, pero las líneas dentro del bloque de texto pueden tener cero o más espacios iniciales.


**5. La respuesta correcta es D.**

**Explicación:**

- **A)** El método `Math.round()` devuelve un `doble`.
  - Esta opción es incorrecta. El método `Math.round()` devuelve un `long` cuando se le da un argumento `double` y un `int` cuando se le da un argumento `float`.

- **B)** El método `Math.random()` devuelve un número entero aleatorio.
  - Esta opción es incorrecta. El método `Math.random()` devuelve un valor `doble` entre 0,0 (inclusive) y 1,0 (exclusivo).

- **C)** El método `Math.max()` solo se puede utilizar con números enteros.
  - Esta opción es incorrecta. El método `Math.max()` se puede utilizar con varios tipos numéricos, incluidos `int`, `long`, `float` y `double`.

- **D)** El método `Math.pow()` devuelve el resultado de elevar el primer argumento a la potencia del segundo argumento.
  - Esta opción es correcta. El método `Math.pow()` devuelve el resultado de elevar el primer argumento a la potencia del segundo argumento. Ambos argumentos son de tipo "doble".

- **E)** El método `Math.abs()` solo se puede utilizar con números positivos.
  - Esta opción es incorrecta. El método `Math.abs()` se puede utilizar con números negativos para devolver su valor absoluto y funciona con varios tipos numéricos, incluidos `int`, `long`, `float` y `double`.