---
layout: answer

title: "Capítulo UNO"
subtitle: "Utilizando el enfoque orientado a objetos de Java - Parte 1"
exam_objectives:
  - "Declare y cree instancias de objetos Java, incluidos objetos de clases anidados, y explique el ciclo de vida del objeto, incluida la creación, la reasignación de referencias y la recolección de basura."
  - "Cree clases y registros, y defina y utilice campos y métodos estáticos y de instancia, constructores e inicializadores estáticos y de instancia."
  - "Implemente la sobrecarga, incluidos los métodos var-arg."
---
## Respuestas

**1. La respuesta correcta es B.**

**Explicación:**

- **A)** Tanto `sb1` como `sb2` son elegibles para la recolección de basura.
  - Esta opción es incorrecta porque `sb2` todavía contiene una referencia al objeto `StringBuilder` que se asignó inicialmente. Por lo tanto, no es elegible para la recolección de basura.

- **B)** Sólo el objeto `StringBuilder` al que inicialmente hace referencia `sb1` es elegible para la recolección de basura.
  - Esta opción es correcta. Después de reasignar `sb1` para hacer referencia al mismo objeto que `sb2`, el objeto `StringBuilder` original creado con `new StringBuilder("Java")` y al que inicialmente hace referencia `sb1` ya no es accesible. Como no hay referencias que lo indiquen, se vuelve elegible para la recolección de basura.

- **C)** Sólo el objeto `StringBuilder` al que inicialmente hace referencia `sb2` es elegible para la recolección de basura.
  - Esta opción es incorrecta porque después de la asignación `sb1 = sb2;`, tanto `sb1` como `sb2` hacen referencia al mismo objeto ("new StringBuilder("Python")`). Todavía se puede acceder a este objeto a través de `sb2` (y ahora también a través de `sb1`), por lo que no es elegible para la recolección de basura.

- **D)** Ninguno de los objetos `StringBuilder` es elegible para la recolección de basura.
  - Esta opción es incorrecta porque, como se explicó, el objeto inicialmente referenciado por `sb1` pasa a ser elegible para la recolección de basura después de que `sb1` se reasigna a `sb2`.



**2. Las respuestas correctas son C y D.**

**Explicación:**

- **A)** `implementar` es incorrecto. La palabra clave correcta para implementar una interfaz en Java es "implementos".

- **B)** `matriz` es incorrecta. Java no tiene una palabra clave reservada llamada "matriz". Las matrices se declaran entre corchetes `[ ]`.

- **C)** `volatile` es correcto. `volatile` es una palabra clave reservada que se utiliza para indicar que el valor de una variable será modificado por diferentes subprocesos.

- **D)** `extends` es correcto. `extends` es una palabra clave reservada que se utiliza en declaraciones de clase para heredar de una superclase.



**3. Las respuestas correctas son A y E.**

**Explicación:**

- **A)** La línea 1 es un ejemplo de un comentario de una sola línea.
  - Esta opción es correcta. La línea 1 usa `//` para iniciar un comentario de una sola línea, que es una forma común de agregar notas o explicar una parte del código que no afecta la ejecución.

- **B)** Las líneas 3-7 demuestran el uso de un comentario javadoc.
  - Esta opción es incorrecta. Las líneas 3 a 7 utilizan un comentario de bloque. Los comentarios de Javadoc comienzan con `/**` y terminan con `*/`.

- **C)** La línea 9 utiliza un comentario javadoc para explicar el método `add`.
  - Esta opción es incorrecta. La línea 9 es un comentario de una sola línea, no un comentario javadoc. Los comentarios de Javadoc en Java se definen con `/**` al principio y `*/` al final, y se usan específicamente para describir clases, métodos y campos.

- **D)** La línea 12 utiliza un comentario especial `TODO`, diferente de un comentario de una sola línea.
  - Esta opción es incorrecta. La línea 12 utiliza un comentario "TODO", que es una convención que muchos desarrolladores siguen para marcar partes del código que requieren mayor desarrollo o atención, pero sigue siendo un comentario de una sola línea.

- **E)** Las líneas 3-7 son un comentario de bloque que se utiliza como si fuera un comentario javadoc.
  - Esta opción es correcta. Las líneas 3 a 7 utilizan un comentario de bloque, que no es procesado por las herramientas javadoc y, por lo tanto, no es adecuado para generar documentación oficial.



**4. Las respuestas correctas son B y D.** 

**Explicación:**

- **A)** La declaración `import` en `Application.java` es innecesaria porque ambas clases están en el mismo directorio.
  - Esta opción es incorrecta. En Java, la declaración `import` se utiliza para hacer visible una clase o un paquete completo, y su necesidad está determinada por la membresía del paquete de las clases, no por su ubicación en el directorio. Incluso si las clases están en el mismo directorio, si pertenecen a paquetes diferentes, se requiere la declaración `import` para usar una en el otro.

- **B)** La declaración `import` en `Application.java` es necesaria para usar la clase `Calculator` porque pertenecen a paquetes diferentes.
  - Esta es la respuesta correcta.r. La clase `Calculator` está en el paquete `math` y la clase `Application` está en el paquete `app`. A pesar de estar en el mismo directorio, los diferentes paquetes requieren una declaración de "importación" para usar la "Calculadora" en la "Aplicación".

- **C)** No se podrá acceder a la clase `Calculadora` en `Application.java` debido a que está en un directorio diferente.
  - Esta opción es incorrecta. El control de acceso de Java no se basa en la estructura del directorio sino en las declaraciones "paquete" e "importación". Siempre que las clases estén empaquetadas e importadas correctamente, se puede acceder a ellas desde diferentes directorios.

- **D)** Eliminar la declaración `paquete` de ambos archivos permitirá que `Application.java` use `Calculator` sin una declaración `import`, independientemente de la estructura del directorio.
  - Esta opción es correcta. Eliminar la declaración "paquete" de ambos archivos los colocará en el paquete predeterminado y podrán acceder entre sí sin una declaración "importación". Sin embargo, esto no se recomienda para nada más que código muy simple o temporal debido a problemas de legibilidad y administración del espacio de nombres.



**5. Las respuestas correctas son A, B y C.** 

**Explicación:**

- **A)** Cualquier otra clase en el mismo paquete o en cualquier otro paquete puede acceder a una clase o miembro `public`.
  - Esto es correcto. El modificador "público" otorga el nivel más alto de acceso. Se puede acceder a una clase o miembro "público" desde cualquier otra clase, independientemente de los paquetes a los que pertenezca.

- **B)** Cualquier clase en su propio paquete puede acceder a un miembro `protected`, pero desde fuera del paquete, solo las clases que extienden la clase que contiene el miembro protegido.
  - Esto es correcto. El nivel de acceso "protegido" permite acceder a un miembro dentro de su propio paquete y mediante subclases en cualquier paquete. Ofrece un nivel de acceso más restrictivo que el "público".

- **C)** Cualquier clase en el mismo paquete puede acceder a un miembro con acceso `predeterminado` (sin modificador), pero no desde una clase en un paquete diferente.
  - Esto es correcto. Si no se especifica ningún modificador de acceso (también conocido como nivel de acceso "predeterminado"), solo se puede acceder al miembro dentro de las clases del mismo paquete. Esto es más restrictivo que "protegido" y "público".

- **D)** Solo se puede acceder a un miembro "privado" mediante métodos que sean miembros de la misma clase o dentro del mismo archivo.
  - Esta opción es incorrecta porque solo se puede acceder a los miembros "privados" dentro de la misma clase. No se trata de estar dentro del mismo archivo, ya que Java sólo permite una clase pública de nivel superior por archivo.

- **E)** Cualquier clase del programa Java puede acceder a un miembro "protegido", independientemente del paquete.
  - Esto es incorrecto. El acceso "protegido" no otorga acceso universal a todas las clases de un programa. El acceso desde fuera del paquete está limitado únicamente a las subclases.



**6. La respuesta correcta es D:** 

**Explicación:**

- **A)** `clase vehículo público { }`
  - Esta opción es incorrecta porque la sintaxis es incorrecta. El orden correcto es el modificador de acceso seguido de la palabra clave "clase" y luego el nombre de la clase.

- **B)** `vehículo de clase pública { }`
  - Esta opción es incorrecta debido principalmente a la convención de nomenclatura de clases. En Java, los nombres de las clases deben comenzar con una letra mayúscula, por lo que "vehículo" debe ser "Vehículo".

- **C)** `Vehículo de clase pública { }`
  - Esta opción es incorrecta porque "Público" está incorrectamente escrito en mayúscula. Java distingue entre mayúsculas y minúsculas y la palabra clave correcta es "pública".

- **D)** `Vehículo de clase pública { }`
  - Esta es la respuesta correcta. La sintaxis sigue el orden correcto: el modificador de acceso ("público"), seguido de la palabra clave "clase", y luego el nombre de la clase ("Vehículo"), que comienza correctamente con una letra mayúscula según las convenciones de nomenclatura de Java.

- **E)** `claseVehículo público { }`
  - Esta opción es incorrecta por varias razones: el orden de sintaxis es incorrecto, no hay espacio entre `class` y el nombre de la clase y la posición del modificador de acceso es incorrecta.



**7. Las respuestas correctas son A, C y D.** 

**Explicación:**

- **A)** Se puede acceder a la variable `COUNT` directamente usando el nombre de la clase sin crear una instancia de `Counter`.
  - Esta opción es correcta. Las variables estáticas pertenecen a la clase y se puede acceder a ellas directamente con el nombre de la clase, como `Counter.COUNT`, sin necesidad de crear una instancia de la clase.

- **B)** El método `getCount()` es un ejemplo de método estático porque devuelve el valor de una variable estática.
  - Esta opción es incorrecta. Aunque `getCount()` devuelve el valor de una variable estática, no está definida como un método estático. Los métodos estáticos se declaran utilizando el modificador "estático". La instancia del método o su naturaleza no estática no cambia según las variables a las que accede o devuelve.

- **C)** Cada vez que se crea una nueva instancia de `Contador`, se incrementa la variable `COUNT`.
  - Esta opción es correcta. El constructor incrementa la variable `COUNT` en 1 cada vez que se crea una nueva instancia de `Counter`, lo que demuestra la naturaleza compartida de las variables estáticas en todas las instancias.

- **D)** El método `resetCount()` restablece la variable `COUNT` a 0 para todas las instancias de `Counter`.
  - Esta opción es correcta. El método estático `resetCount()` establece la variable `COUNT` en cero. Dado que `COUNT` es estático, este cambio afecta a todas las instancias de la clase, ya que solo hay una variable `COUNT` compartida entre ellas.



**8. Las respuestas correctas son A, C y D.**

**Explicación:**

- **A)** `int _age;` es correcto. Los identificadores en Java pueden comenzar con una letra, un guión bajo (_) o un signo de dólar ($). Por lo tanto, `_age` es un identificador válido.

- **B)** `double 2ndValue;` es incorrecto. Los identificadores no pueden comenzar con un dígito. El formato correcto sería comenzar con una letra o un carácter que no sea un dígito, como un guión bajo o un signo de dólar.

- **C)** `boolean is_valid;` es correcto. Similar a `_age`, `is_valid` es un identificador válido porque comienza con una letra y puede contener guiones bajos.

- **D)** `Cadena $nombre;` es correcta. Los identificadores también pueden comenzar con un signo de dólar ($), lo que hace que "$name" sea un identificador válido.

- **E)** `char #char;` es incorrecto. El carácter almohadilla (#) no está permitido como carácter inicial en los identificadores. Los identificadores solo pueden comenzar con letras, `$` o `_`.



**9. La respuesta correcta es B.** 

**Explicación:**

- **A)** `int public static final computeSum(int num1, int num2) { return num1 + num2 }` es incorrecto porque el tipo de retorno en las declaraciones de métodos va justo antes del nombre del método, no al principio.

- **B)** `private void updateRecord(int id) throws IOException {}` es correcto. Esta declaración de método es sintácticamente correcta en Java. Utiliza el modificador de acceso "privado", especifica un tipo de retorno ("void"), incluye una excepción ("IOException") que este método podría generar y define correctamente la lista de parámetros.

- **C)** `checkStatus booleano sincronizado [estado int] { estado de retorno == 1; }` La sintaxis correcta requiere paréntesis para la lista de parámetros, incluso cuando no hay parámetros, lo que genera la declaración correcta `synchronized boolean checkStatus(int status)`.

- **D)** `float calcularArea() {}` es incorrecto porque un método que devuelve `float` no puede tener un cuerpo de método vacío.



**10. Las respuestas correctas son (A y B) y (C y D).**

**Explicación:**

En Java, la firma de un método consta del nombre del método y la lista de parámetros. El tipo de retorno, el modificador de acceso y la lista de excepciones no se consideran parte de la firma del método.

- **A)** (`public void update (int id, String value)`) 
- **B)** (`private void update (int identifier, String data)`) 
  - Las opciones anteriores tienen la misma firma de método (`update(int, String)`) porque ambas tienen el mismo nombre de método y lista de parámetros (un `int` y un `String`, en ese orden). La diferencia en los nombres de los parámetros (`id` frente a `identificador` y `valor` frente a `datos`) no afecta la firma del método.

- **C)** `public boolean update (String value, int id)` 
- **D)** `void update (String value, int id)`
  - Esta opción tiene el mismo mfirma del método (`update(String, int)`) que C porque ambos tienen el mismo nombre de método y lista de parámetros (una `String` y un `int`, en ese orden). Los diferentes modificadores de acceso y tipos de retorno no afectan la firma del método.

- **E)** `la protected void update (int id, int value) arroja IOException`
  - Esta opción también tiene una lista de parámetros diferente (`update(int, int)`).



**11. Las respuestas correctas son C y D.** 

**Explicación:**

- **A)** Se puede acceder al método `resetAccountPassword` desde cualquier clase dentro del mismo paquete, pero no desde una clase en un paquete diferente.
  - Esta opción es incorrecta. El método `resetAccountPassword` tiene acceso `private`, lo que significa que solo se puede acceder a él dentro de la clase `AccountManager`, no desde ninguna clase, ni siquiera dentro del mismo paquete. La afirmación inicial era ligeramente incorrecta al sugerir acceso a nivel de paquete para un método "privado".

- **B)** Se puede acceder al método `auditTrail` desde cualquier clase dentro del mismo paquete y desde subclases en diferentes paquetes.
  - Esta opción es incorrecta porque el método `auditTrail` tiene acceso privado al paquete (sin modificador de acceso), lo que significa que es accesible desde cualquier clase dentro del mismo paquete pero no desde subclases en diferentes paquetes a menos que también estén dentro del mismo paquete.

- **C)** Se puede acceder al método `notifyAccountChanges` desde cualquier clase dentro del mismo paquete y desde subclases en diferentes paquetes.
  - Esta opción es correcta. El método `notifyAccountChanges` tiene acceso `protected`, lo que significa que se puede acceder a él dentro del mismo paquete y por subclases, incluso si las subclases están en paquetes diferentes.

- **D)** Se puede acceder al método `updateAccountInformation` desde cualquier clase, independientemente de su paquete.
  - Esta opción es correcta. El método `updateAccountInformation` es `public`, por lo que se puede acceder a él desde cualquier clase, independientemente del paquete al que pertenezca.



**12. La respuesta correcta es B.**

**Explicación:**

Java es estrictamente de paso por valor. Esto significa que al pasar una variable a un método, Java pasa una copia del valor de la variable, no de la variable en sí. Los cambios en el parámetro dentro del método no afectan la variable original.

- **A)**

```

Before calling changeValue: 10  
After calling changeValue: 20

```

- Esta opción es incorrecta porque, aunque el método `changeValue` cambia el parámetro `value` a 20, este cambio no afecta a la variable original `originalValue` fuera del método. El cambio a "valor" se realiza en su copia, no en el propio "valor original".

- **B)**

```

Before calling changeValue: 10  
After calling changeValue: 10

```

- Esta es la respuesta correcta. `originalValue` se pasa por valor al método `changeValue`. Por lo tanto, las modificaciones al "valor" dentro de "cambiarValor" no afectan al "valor original". El resultado confirma que "originalValue" permanece sin cambios después de la llamada al método.

- **C)**

```

Before calling changeValue: 20  
After calling changeValue: 20

```

- **D)**

```

Before calling changeValue: 20  
After calling changeValue: 10

```

- Estas opciones son incorrectas ya que sugieren que los cambios en los parámetros del método pueden afectar las variables originales, que no es como funciona la semántica de paso por valor de Java.



**13. La respuesta correcta es B.**

**Explicación:**

- **A)** `Object`
  - Esta opción es incorrecta porque Java utiliza el método más específico aplicable a los parámetros. En este caso, `String` es más específico que `Object`, por lo que se llama al método `print(String s)`.

- **B)** `String`
  - Esta opción es correcta. Aunque "nulo" se puede asignar a cualquier tipo de referencia, Java prefiere el método más específico aplicable a los parámetros del método. Dado que `String` es un tipo más específico que `Object`, el método `print(String s)` se elige en lugar del método `print(Object o)`.

- **C)** La compilación falla
  - La compilación no falla porque ambos métodos `print` están definidos correctamente y potencialmente pueden coincidir con la llamada `print(null)`. El mecanismo de sobrecarga de métodos de Java permite que esto se compile sin ningún problema.

- **D)** Se lanza una excepción de tiempo de ejecución
  - No se genera ninguna excepción en tiempo de ejecución porque la llamada al método `print` se resuelve correctamente en el método `print(String s)` en tiempo de compilación. Dado que el método se invoca correctamente y no hay ningún otro código que pueda provocar una excepción de tiempo de ejecución, este programa se ejecuta correctamente.



**14. Las respuestas correctas son B y D.**

**Explicación:**

- **A)** `public void print (cadena... mensajes, int count)`
  - Esta opción es incorrecta porque varargs (argumentos variables) debe ser el último parámetro en la lista de parámetros de un método. Tener "int count" después de "String... mensajes" viola esta regla.

- **B)** `public void print (int count, String... mensajes)`
  - Esta opción es correcta. Coloca correctamente el parámetro varargs `Cadena... mensajes` al final de la lista de parámetros del método, que es la sintaxis requerida para usar varargs.

- **C)** `public void print (String... messages...)`
  - Esta opción es incorrecta porque la sintaxis `String... messages...` no es válida. La sintaxis correcta para varargs es colocar puntos suspensivos (`...`) después del tipo y antes del nombre de la variable, como `String... message`.

- **D)** `public void print (Cadena[]... mensajes)`
  - Esta opción es correcta. Demuestra el uso de varargs con un tipo de matriz, lo cual está permitido. Aquí, cada argumento pasado a `mensajes` puede ser en sí mismo una matriz de `String`, y `mensajes` se tratará como una matriz de matrices (`Cadena[][]`).

- **E)** `public void print (cadena... mensajes, cadena lastMessage)`
  - Esta opción es incorrecta, similar a la opción A, porque varargs debe ser el último parámetro en la lista de parámetros del método. No se permite tener otro parámetro después del parámetro varargs.



**15. La respuesta correcta es A.** 

**Explicación:**

- **A)** La clase `Vehicle` demuestra la sobrecarga del constructor al tener múltiples constructores con diferentes listas de parámetros.
  - Esta opción es correcta. La sobrecarga de constructores en Java es una técnica que consiste en tener más de un constructor con diferentes listas de parámetros en la misma clase. Permite inicializar objetos de la clase de diferentes formas. La clase `Vehicle` tiene dos constructores, uno que toma un `String` (para el tipo de vehículo) y otro que toma un `int` (para la velocidad máxima), que es un ejemplo perfecto de sobrecarga de constructores.

- **B)** La clase `Vehicle` se compilará con un error porque no proporciona un constructor predeterminado.
  - Esta opción es incorrecta. Java no requiere un constructor predeterminado explícito si la clase proporciona otros constructores. La ausencia de un constructor predeterminado (uno que no acepte argumentos) no es un error de compilación; simplemente significa que el programador no puede crear una instancia de la clase utilizando un constructor sin argumentos a menos que esté definido explícitamente.

- **C)** Es posible crear una instancia de `Vehicle` con `type` y `maxSpeed` establecidos en valores específicos mediante una única llamada al constructor.
  - Esta opción es incorrecta porque ninguno de los constructores existentes tieneExcepto un parámetro `String` y `int`. Cada constructor sólo establece un campo con un valor especificado por la persona que llama; el otro campo conserva su valor predeterminado ("nulo" o "0").

- **D)** Llamar a cualquiera de los constructores inicializará los campos `type` y `maxSpeed` de la clase `Vehicle`.
  - Esta opción es incorrecta. Llamar a cualquiera de los constructores solo inicializa el parámetro que se le proporciona. El primer constructor inicializa el "tipo" y el segundo inicializa el "maxSpeed". Sin código adicional, como un constructor que acepte ambos parámetros o métodos de establecimiento, no hay forma de que ninguno de los constructores inicialice ambos campos por sí solo.



**16. Las respuestas correctas son A y D.** 

**Explicación:**

- **A)** El bloque inicializador de instancia se ejecuta antes que el constructor, inicializando la lista de `libros` y agregándole dos libros.
  - Esta opción es correcta. El bloque inicializador de instancia se ejecuta cada vez que se crea una instancia de la clase, antes de que se ejecute el código del constructor. Inicializa la lista de "libros" y le agrega dos libros.

- **B)** El bloque inicializador de instancia reemplaza la necesidad de un constructor en la clase `Biblioteca`.
  - Esta opción es incorrecta. El bloque inicializador de instancia no reemplaza la necesidad de un constructor. Se utiliza además de los constructores, a menudo para inicializar partes comunes de varios constructores de una clase.

- **C)** Los bloques inicializadores de instancia no pueden inicializar variables de instancia como "libros". 
  - Esta opción es incorrecta. Los bloques inicializadores de instancias pueden inicializar variables de instancia. En este caso, la lista de "libros" es una variable de instancia que se inicializa y completa dentro del bloque inicializador de instancia.

- **D)** Si se crean varias instancias de `Biblioteca`, el bloque inicializador de instancia se ejecutará cada vez antes que el constructor, asegurando que la lista de `libros` esté inicializada y completada para cada objeto.
  - Esta opción es correcta. Para cada nueva instancia de la clase `Biblioteca`, el bloque inicializador de instancia se ejecuta antes de invocar al constructor. Esto garantiza que la lista de "libros" se inicialice y se complete con el "Libro 1" y el "Libro 2" para cada objeto "Biblioteca" creado.



**17. La respuesta correcta es A.** 

**Explicación:**

- **A)** El bloque inicializador `static` se ejecuta solo una vez cuando la clase se carga por primera vez en la memoria, inicializando el mapa de `configuración` con valores predeterminados.
  - Esta opción es correcta. Los bloques inicializadores estáticos se ejecutan una sola vez, cuando la clase se carga por primera vez en la memoria JVM. En este caso, inicializa el mapa de "configuración" con los valores de configuración predeterminados.

- **B)** El bloque inicializador `static` permite que los métodos de instancia modifiquen el mapa de `configuración` sin crear una instancia de la clase `Configuración`.
  - Esta opción es engañosa. Si bien los métodos estáticos como `getSetting` pueden acceder y modificar campos estáticos como `settings` sin necesidad de una instancia de la clase, esta capacidad no se debe al bloque inicializador estático en sí, sino a la naturaleza de los campos y métodos estáticos.

- **C)** Los bloques inicializadores `estáticos` se ejecutan cada vez que se crea una nueva instancia de la clase `Configuración`.
  - Esta opción es incorrecta. Los bloques inicializadores estáticos no se ejecutan cada vez que se crea una nueva instancia de la clase. Se ejecutan sólo una vez: cuando la clase se carga por primera vez.

- **D)** El bloque inicializador `static` se ejecuta antes que cualquier bloque inicializador o constructor de instancia, cuando se crea una instancia de la clase.
  - Esta afirmación es parcialmente correcta en el sentido de que los bloques inicializadores estáticos se ejecutan antes que cualquier constructor o bloque inicializador de instancia, pero es engañosa ya que implica una secuencia con la creación de la instancia. El punto clave es que los bloques inicializadores estáticos se ejecutan una vez al cargar la clase, independientemente de la creación de instancias.



**18. La respuesta correcta es B.** 

**Explicación:**

En Java, el orden de inicialización cuando se carga una clase y se crea una instancia de esa clase es el siguientemínimos:

1. **Los campos estáticos y los inicializadores estáticos** se procesan en el orden en que aparecen en la definición de clase. Primero, el bloque inicializador estático imprime `"1. Inicializador estático"`. Luego, el campo estático `staticValue` se inicializa llamando a `initializeStaticValue()`, que imprime `"2. Inicializador de valor estático".`

2. **Los campos de instancia y los inicializadores de instancia** se procesan en el orden en que aparecen cuando se crea una instancia de la clase. Primero, el campo de instancia `instanceValue` se inicializa llamando a `initializeInstanceValue()`, que imprime `"3. Inicializador de valor de instancia"`. Luego, el bloque inicializador de instancia imprime `"3. Inicializador de instancia"`.

3. Los **constructores** se ejecutan después de que se hayan procesado todos los campos e inicializadores de instancia. El constructor en este caso imprime `"4. Constructor"`.

La numeración de la salida para `"3. Inicializador de instancia"` y `"3. Inicializador de valor de instancia"` en la pregunta podría parecer sugerir que se ejecutan simultáneamente o fuera de orden, pero es importante recordar que los campos de instancia y los inicializadores de instancia se ejecutan en el orden en que aparecen en la clase, antes de que se ejecute el constructor. La numeración duplicada significa que los inicializadores de campo de instancia se ejecutan primero, seguidos de los inicializadores de instancia y, finalmente, se ejecuta el constructor.

- **A)**

```

1. Static initializer
2. Static value initializer
3. Instance initializer
3. Instance value initializer
4. Constructor

```

- Esta opción es incorrecta.

- **B)**

```

1. Static initializer
2. Static value initializer
3. Instance value initializer
3. Instance initializer
4. Constructor

```

- Esta opción es correcta.

- **C)**

```

1. Static initializer
3. Instance initializer
2. Static value initializer
3. Instance value initializer
4. Constructor

```

- Esta opción es incorrecta. 

- **D)**

```

2. Static value initializer
1. Static initializer
3. Instance value initializer
3. Instance initializer
4. Constructor

```

- Esta opción es incorrecta.



**19. Las respuestas correctas son A y C.** 

**Explicación:**

- **A)** Al invocar `toString()` en una instancia de `CustomObject` se devolverá una `String` que incluye el nombre de la clase seguido del símbolo `@` y el código hash del objeto.
  - Esta opción es correcta. El método `toString()` en `java.lang.Object` devuelve una cadena que incluye el nombre de la clase, el símbolo `@` y el código hash del objeto en hexadecimal. Si `CustomObject` no anula `toString()`, se utiliza este formato predeterminado.

- **B)** Llamar a `equals(Object obj)` en dos instancias diferentes de `CustomObject` que tienen contenido idéntico devolverá `true` porque son instancias de la misma clase. 
  - Esta opción es incorrecta. La implementación predeterminada de `equals(Object obj)` en `java.lang.Object` verifica la igualdad de referencias, lo que significa que devuelve `true` solo si ambas referencias apuntan exactamente al mismo objeto. Sin anular "equals", dos instancias diferentes de "CustomObject", incluso con contenido idéntico, no se considerarían iguales.

- **C)** El uso de `hashCode()` en cualquier instancia de `CustomObject` generará un número entero único que permanece consistente en múltiples invocaciones dentro de la misma ejecución de un programa.  
  - Esta opción es correcta. El método `hashCode()` está diseñado para devolver una representación entera de la dirección de memoria del objeto o un valor derivado de ella. Si bien la implementación exacta no se especifica y puede variar, es consistente durante la ejecución de un programa para cualquier objeto determinado.

- **D)** El método `clone()` se puede utilizar para crear una copia superficial de una instancia de `CustomObject` sin la necesidad de que `CustomObject` implemente la interfaz `Cloneable`. 
  - Esta opción es incorrecta. El método `clone()` en `java.lang.Object` está protegido y genera una `CloneNotSupportedException` a menos que la clase implemente la interfaz `Cloneable`. Sin que `CustomObject` implemente explícitamente `Cloneable` y anule `clone()` para hacerlo `public`, no se puede usar para clonar instancias de `CustomObject`.



**20. La respuesta correcta es B.**

**Explicación:**

- **A)** Una clase anidada estática puede acceder directamente a miembros estáticos y no estáticos de su clase adjunta. 
  - Esta opción es incorrecta porque una clase anidada estática no puede acceder directamente a miembros no estáticos de su clase adjunta. Solo puede acceder directamente a miembros estáticos.

- **B)** Pueden existir instancias de una clase anidada estática sin una instancia de su clase adjunta.
  - Esta es la respuesta correcta. Una clase anidada estática está asociada con su clase externa y, a diferencia de las clases internas, no necesita una instancia de la clase externa para crear una instancia. Esto lo hace útil para agrupar clases que se utilizarán en un contexto estático.

- **C)** Una clase anidada estática solo se puede crear una instancia dentro del método estático de su clase adjunta.
  - Esta opción es incorrecta. Se puede crear una instancia de una clase anidada estática desde cualquier contexto (estático o no estático) siempre que sea accesible (es decir, la visibilidad lo permita).

- **D)** Las clases anidadas estáticas no se consideran miembros de su clase adjunta y no pueden acceder a ningún miembro de la clase adjunta.
  - Esta opción es incorrecta. De hecho, las clases anidadas estáticas se consideran miembros de su clase adjunta y pueden acceder a sus miembros estáticos y métodos estáticos. Sin embargo, no tienen acceso a miembros no estáticos de la clase adjunta a menos que creen una instancia de la clase adjunta.



**21. La respuesta correcta es A.** 

**Explicación:**

- **A)** Una clase anidada no estática puede acceder directamente a miembros estáticos y no estáticos de su clase adjunta.
  - Esta opción es correcta. Una clase anidada no estática, o clase interna, tiene acceso a todos los miembros (incluidos los estáticos y no estáticos) de su clase adjunta, como se demuestra en el fragmento de código donde "InnerClass" accede al campo "mensaje" no estático de "OuterClass".

- **B)** Las instancias de una clase anidada no estática pueden existir independientemente de una instancia de su c adjunta.muchacha. 
  - Esta opción es incorrecta. Las instancias de una clase anidada no estática (clase interna) están asociadas implícitamente con una instancia de la clase adjunta. Por lo tanto, no pueden existir independientemente de una instancia de la clase adjunta. En el fragmento de código proporcionado, la instancia "InnerClass" se crea a través de una instancia de "OuterClass".

- **C)** Una clase anidada no estática no puede acceder directamente a los miembros no estáticos de su clase adjunta.
  - Esta opción es incorrecta. Como se indicó anteriormente, una clase interna puede acceder directamente a miembros estáticos y no estáticos de su clase adjunta.

- **D)** Las clases anidadas no estáticas deben declararse estáticas para acceder a los miembros estáticos de su clase adjunta.
  - Esta opción es incorrecta. Las clases anidadas no estáticas (clases internas) están diseñadas para acceder a los miembros de su clase adjunta directamente sin necesidad de declararlas estáticas. Declarar una clase anidada como estática cambia su tipo a una clase anidada estática, que tiene propiedades de acceso diferentes a las de una clase interna.



**22. Las respuestas correctas son A y D.** 

**Explicación:**

- **A)** Las clases locales se pueden declarar dentro de cualquier bloque que preceda a una declaración.
  - Esta opción es correcta. De hecho, las clases locales en Java se pueden declarar dentro de cualquier bloque que preceda a una declaración, como el cuerpo de un método, un bucle "for" o una declaración "if".

- **B)** Las instancias de una clase local se pueden crear y utilizar fuera del bloque donde se define la clase local.
  - Esta opción es incorrecta. Las instancias de clases locales no se pueden crear ni utilizar fuera del bloque donde están definidas. Su alcance se limita al bloque en el que están declarados.

- **C)** Las clases locales son un tipo de clase anidada estática y pueden acceder directamente a miembros estáticos y no estáticos de la clase adjunta.
  - Esta opción es incorrecta. Las clases locales no son estáticas; están asociados con una instancia de la clase adjunta y tienen acceso a los miembros de su instancia. No tienen el contexto estático que tienen las clases anidadas estáticas y, por lo tanto, pueden acceder a miembros tanto estáticos como no estáticos de la clase adjunta.

- **D)** Las clases locales pueden acceder a variables locales y parámetros del bloque adjunto solo si se declaran "finales" o efectivamente finales.
  - Esto es correcto. Las clases locales pueden acceder a variables locales y parámetros del método (o cualquier bloque adjunto) en el que están definidas, pero esas variables deben declararse "finales" o efectivamente finales (lo que significa que sus valores no cambian después de que se inicializan).



**23. La respuesta correcta es A.** 

**Explicación:**

- **A)** Las clases anónimas pueden implementar interfaces y ampliar clases sin la necesidad de declarar una clase con nombre.
  - Esta opción es correcta. Las clases anónimas son una forma de ampliar clases existentes o implementar interfaces en el momento sin la necesidad de una declaración de clase formal. Esto los hace útiles para crear implementaciones rápidas y únicas.

- **B)** Una clase anónima debe anular todos los métodos de la superclase o interfaz que declara que está implementando o ampliando.
  - Esta opción es incorrecta. Una clase anónima sólo necesita anular los métodos abstractos de la superclase o interfaz que extiende o implementa. Si la superclase o interfaz no tiene métodos abstractos, entonces la clase anónima no necesita anular ningún método.

- **C)** Las clases anónimas pueden tener constructores como los tienen las clases con nombre.
  - Esta opción es incorrecta. Las clases anónimas no tienen constructores con nombre porque ellas mismas no tienen nombre. En cambio, cualquier inicialización se realiza a través de un bloque inicializador de instancia.

- **D)** Las instancias de clases anónimas no se pueden pasar como argumentos a los métodos.
  - Esta opción es incorrecta. De hecho, las instancias de clases anónimas se pueden pasar como argumentos a los métodos. Son útiles para crear implementaciones sobre la marcha para interfaces o subclases necesarias para una llamada a un método.



**24. La respuesta correcta es C.** 

**Explicación:**

- **A)** Un archivo fuente puede contener múltiples clases públicas.
  - Esta opción es incorrecta. Un archivo fuente Java no puede contener más de una clase "pública". Si una clase se declara "pública", debe ser la única clase "pública" en el archivo y el nombre del archivo debe coincidir con el nombre de la clase.

- **B)** Las clases privadas se pueden declarar en el nivel superior en un archivo fuente.
  - Esta opción es incorrecta. Java no permite que las clases se declaren como "privadas" en el nivel superior. Solo se pueden definir clases "públicas" o de paquete privado (sin modificador de acceso) en el nivel superior. Las clases internas pueden ser "privadas".

- **C)** Se debe declarar una clase `pública` en un archivo fuente que tenga el mismo nombre que la clase.
  - Esto es correcto. Según las reglas de Java, si una clase se declara "pública", el archivo fuente en el que se define debe tener el mismo nombre que la clase, seguido de la extensión ".java". Esta es una regla estricta que ayuda al compilador de Java a localizar fácilmente los archivos fuente.

- **D)** Si un archivo fuente contiene más de una clase, ninguna de las clases puede ser "pública".
  - Esto es incorrecto. Si bien es cierto que si un archivo fuente contiene una clase "pública", el archivo fuente debe llevar el nombre de esa clase "pública", no es cierto que ninguna de las clases puede ser "pública" si un archivo fuente contiene más de una clase. Un archivo fuente puede contener varias clases, pero solo una de ellas puede ser "pública", y el archivo fuente debe llevar el nombre de esa clase "pública". La afirmación podría implicar que múltiples clases de nivel superior no públicas son un escenario común sin el contexto de la regla de denominación de clases "públicas".