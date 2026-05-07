---
layout: answer

title: "Capítulo DOS"
subtitle: "Utilizando el enfoque orientado a objetos de Java - Parte 2"
exam_objectives:
  - "Comprenda alcances variables, aplique encapsulación y cree objetos inmutables. Utilice la inferencia de tipos de variables locales."
  - "Implemente la herencia, incluidos tipos abstractos y sellados, así como clases de registros. Anular métodos, incluido el de la clase Objeto. Implementar polimorfismo y diferenciar entre tipo de objeto y tipo de referencia. Realice la conversión de tipos de referencia, identifique tipos de objetos utilizando el operador instancia de y la coincidencia de patrones con el operador instancia de y la construcción switch."
  - "Cree y utilice interfaces, identifique interfaces funcionales y utilice métodos de interfaz privados, estáticos y predeterminados."
-"None"## Respuestas

**1. La respuesta correcta es C.**

**Explicación:**

- **A)** El código compila y genera `3` seguido de `1`.
  - Esta opción es incorrecta porque, aunque el código imprime `3` seguido de `1` debido a que `x` está dentro del alcance, intentar acceder a `y` fuera de su bloque de declaración (el bloque `if`) provocará un error en tiempo de compilación.

- **B)** El código compila y genera `3` seguido de `1` y un valor indefinido para `y`.
  - Esta opción es incorrecta porque Java no permite el acceso a variables locales (`y` en este caso) fuera de su alcance. La noción de "valor indefinido para `y`" no es aplicable aquí; el compilador simplemente no compilará el código.

- **C)** El código no se compila porque se accede a "y" fuera de su alcance.
  - Esta es la opción correcta. La variable local `y` se declara dentro del bloque `if` y, por lo tanto, solo es accesible dentro de ese bloque. Intentar acceder a él fuera de su alcance, como se hizo en el último `System.out.println(y);`, provoca un error en tiempo de compilación, que dice específicamente que no se puede encontrar `y`.

- **D)** El código se compila pero genera una excepción de tiempo de ejecución al intentar imprimir `y`.
  - Esta opción es incorrecta porque el problema con el código ocurre en tiempo de compilación, no en tiempo de ejecución. El compilador no permitirá que el código se compile debido a la violación del alcance de la variable local `y`, por lo que una excepción de tiempo de ejecución con respecto a `y` está fuera de discusión.



**2. Las respuestas correctas son C y D.**

**Explicación:**

- **A)** `doble x, doble y;`
  - Esta opción es incorrecta porque al declarar múltiples variables del mismo tipo en una sola declaración, no se repite el tipo antes de cada variable. La sintaxis correcta sería "doble x, y;".

- **B)** `int i = 0, String s = "hola";`
  - Esta opción es incorrecta por el mismo motivo que A; no puede declarar variables de diferentes tipos (`int` y `String` en este caso) en una sola declaración. 

- **C)** `flotante f1 = 3,14, f2 = 6,28f;`
  - Esto es correcto. Puedes declarar múltiples variables del mismo tipo (`float` en este caso) en una sola declaración, y también está bien inicializarlas con valores en la misma declaración.

- **D)** `char a = 'A', b, c = 'C';`
  - Esto es correcto. Es válido declarar múltiples variables del mismo tipo (`char` en este caso) e inicializar algunas, todas o ninguna de ellas en la misma declaración.



**3. Las respuestas correctas son B y D.**

**Explicación:**

- **A)** `var` se puede utilizar para declarar tanto variables locales dentro de métodos como variables de instancia dentro de clases.
  - Esta opción es incorrecta porque `var` no se puede utilizar para declarar variables de instancia. Está específicamente restringido a variables locales dentro de métodos, constructores o bloques inicializadores, ya que usar `var` para campos reduciría la claridad de la API pública de una clase.

- **B)** El uso de `var` está restringido a variables locales dentro de métodos, constructores o bloques inicializadores.
  - Esta opción es correcta. `var` está destinado a la inferencia de tipos de variables locales, lo que reduce significativamente la verbosidad del código Java en escenarios donde el compilador puede determinar fácilmente el tipo de variable local a partir de su inicializador. Su uso está restringido para garantizar la claridad y evitar la ambigüedad en construcciones más complejas como campos de clase o parámetros de métodos.

- **C)** `var` se puede utilizar para declarar parámetros de método.
  - Esta opción es incorrecta. El ejemplo demuestra claramente que "var" no se puede utilizar para declarar parámetros de método. Esta limitación garantiza que las firmas de los métodos sigan siendo explícitas en sus requisitos de tipo, un aspecto crítico del contrato de una clase con sus llamadores.

- **D)** `var` mejora la legibilidad al inferir tipos que están claros a partir del contexto, pero no está permitido en las firmas de métodos para mantener la claridad.
  - Esta opción es correcta. Si bien `var` se usa principalmente para mejorar la legibilidad del código al reducir la necesidad de declaraciones de tipo explícitas donde el tipo se puede inferir del contexto, no está permitido en las firmas de métodos. Esta restricción garantiza que elLos tipos de parámetros en los métodos siempre se definen explícitamente, lo que ayuda a la legibilidad y el mantenimiento de las API públicas.

- **E)** `var` se puede utilizar para declarar variables de clase (estáticas).
  - Esta opción es incorrecta. De manera similar a las variables de instancia, `var` no está permitido para declarar variables de clase (`static`). La razón detrás de esta restricción se alinea con el objetivo de mantener declaraciones de tipo explícitas en la estructura de la clase, asegurando que el diseño de la clase permanezca claro e inequívoco tanto para el compilador como para los desarrolladores.


**4. Las respuestas correctas son A y C.**

**Explicación:**

- **A)** La palabra clave `extends` se usa en Java para crear una subclase que hereda de una superclase.
  - Esta opción es correcta. Las subclases pueden acceder directamente a los miembros "protegidos" y "públicos" de su superclase. Esta accesibilidad permite a las subclases aprovechar y ampliar la funcionalidad proporcionada por la superclase manteniendo al mismo tiempo la encapsulación de los miembros "privados".

- **B)** En Java, una clase puede extender varias clases para lograr herencia múltiple.
  - Esta opción es incorrecta. Java no admite herencia múltiple para clases. Una clase en Java sólo puede extender otra clase, evitando complicaciones como el problema del diamante y la complejidad asociada con la herencia múltiple.

- **C)** Las subclases solo pueden acceder directamente a los miembros "protegidos" y "públicos" de su superclase.
  - Esta opción es correcta. De hecho, la palabra clave `extends` se utiliza para definir una subclase que hereda propiedades y comportamientos de una única superclase, estableciendo una relación *es-a* entre la subclase y la superclase. Este es un concepto fundamental en la implementación de la herencia en Java.

- **D)** Una subclase en Java puede acceder directamente a miembros "privados" de su superclase.
  - Esta opción es incorrecta. Una subclase no puede acceder directamente a miembros "privados" de su superclase. En cambio, puede acceder a ellos a través de accesores "públicos" o "protegidos" proporcionados por la superclase. Este principio de encapsulación asegura una interacción controlada con el estado de la superclase.


**5. Las respuestas correctas son A y C.**

**Explicación:**

- **A)** El código se compilará e imprimirá `"El perro come"` cuando se ejecute.
  - Esta opción es correcta. La clase `Dog` ha proporcionado una implementación para el método `eat`, que es abstracto en la superclase `Animal`. Dado que `myAnimal` es de tipo `Animal` pero instanciado como `Dog`, llamará al método `eat` anulado en la clase `Dog`, imprimiendo `"Dog eats"`.

- **B)** Se puede crear una instancia de la clase `Animal`.
  - Esta opción es incorrecta. La clase `Animal` es abstracta y no se pueden crear instancias. Intentar crear una instancia de `Animal` directamente (`new Animal()`) resultaría en un error de compilación.

- **C)** Eliminar el método `eat` de la clase `Dog` provocará un error de compilación.
  - Esta opción es correcta. Dado que "Dog" extiende la clase abstracta "Animal" y "Animal" tiene un método abstracto "eat", "Dog" debe proporcionar una implementación para "eat". De lo contrario, el código no se compilará porque "Dog" también se consideraría abstracto.

- **D)** La clase `Cat` es necesaria para que el código se compile y ejecute.
  - Esta opción es incorrecta. No se hace referencia a la clase `Cat` en el método `main` ni en ningún otro lugar del fragmento de código proporcionado. Por lo tanto, es innecesario para la compilación y ejecución del segmento de código dado.


**6. Las respuestas correctas son B y C.**

**Explicación:**

- **A)** La clase `Persona` debe anular el método `getSpeed`.
  - Esta opción es incorrecta. No es necesario que la clase `Persona` anule el método `getSpeed` porque es un método predeterminado en la interfaz `Runnable`. Los métodos predeterminados proporcionan una implementación que las clases de implementación pueden usar o anular, pero la anulación no es obligatoria.

- **B)** La variable `distancia` en la interfaz `Walkable` es implícitamente `pública`, `estática` y `final`. 
  - Esta opción es correcta. En Java, todas las variables declaradas en una interfaz ason implícitamente "público", "estático" y "final". Esto significa que la variable "distancia" en la interfaz "Walkable" es una constante y debe inicializarse en el momento de la declaración. Se puede acceder a él con el nombre de la interfaz, como "Walkable.distance".

- **C)** Un objeto `Persona` puede llamar al método `getSpeed` sin ninguna implementación en la clase `Persona`.
  - Esta opción es correcta. Dado que la interfaz `Runnable` proporciona una implementación predeterminada para el método `getSpeed`, un objeto `Person` puede llamar al método `getSpeed` sin ninguna implementación adicional en la propia clase `Person`. Se utilizará la implementación predeterminada de la interfaz.

- **D)** La interfaz `Runnable` provoca un error de compilación debido a un conflicto de nombres con `java.lang.Runnable`.
  - Esta opción es incorrecta porque Java es totalmente compatible con la resolución de espacios de nombres. La interfaz `Runnable` declarada en el fragmento de código y `java.lang.Runnable` existen en paquetes diferentes. No hay ningún error de compilación a menos que se intente importar ambos en el mismo archivo sin utilizar un nombre completo. Además, esta situación no se relaciona directamente con la funcionalidad o declaración de interfaces según el enfoque del examen.



**7. La respuesta correcta es A.**

**Explicación:**

- **A)** La clase `Shape` está definida correctamente como una clase sellada, lo que permite que solo clases específicas la extiendan.
  - Esta opción es correcta. La clase `Shape` se declara como una clase sellada, lo que significa que solo puede ampliarse con las clases que permite explícitamente a través de la cláusula `permits`. En este caso, `Shape` permite que `Circle` y `Square` lo extiendan, y ambas clases están correctamente definidas como subclases permitidas.

- **B)** La clase `Cuadrado` no extiende correctamente la clase `Forma` porque no está marcada como `final`. 
  - Esta opción es incorrecta. No es necesario que las clases que amplían una clase sellada se marquen como "finales" si no son selladas. La palabra clave "no sellada" permite explícitamente que la clase "Cuadrado" extienda la clase sellada "Forma" sin ser final, lo que indica que se puede extender aún más.

- **C)** La clase `Circle` puede ampliarse aún más con otras clases. 
  - Esta opción es incorrecta. La clase "Círculo" se declara como "final", lo que significa que no se puede extender más y alinearse con las restricciones de extender una clase sellada donde la subclase permitida puede ser final, sellada o no sellada.

- **D)** El método `area` en la clase `Shape` debe proporcionar una implementación predeterminada.
  - Esta opción es incorrecta. Las clases abstractas como `Shape` no están obligadas a proporcionar implementaciones para sus métodos abstractos. El propósito de una clase abstracta es definir una plantilla que seguirán sus subclases, lo que incluye implementar cualquier método abstracto declarado en la clase abstracta.


**8. La respuesta correcta es D.**

**Explicación:**

- **A)** Una referencia al contexto "estático" de la clase, que permite el acceso a métodos y campos estáticos.
  - Esta opción es incorrecta. La palabra clave `this` no se refiere al contexto estático de la clase. Se refiere específicamente a la instancia actual de la clase. Los métodos y campos estáticos pertenecen a la clase misma y no forman parte de ninguna instancia, por lo que no se puede acceder a ellos a través de "this".

- **B)** Una variable especial que almacena el valor de retorno de un método.
  - Esta opción es incorrecta. La palabra clave `this` no almacena el valor de retorno de un método. Se utiliza dentro de un método de instancia o un constructor para hacer referencia al objeto actual sobre el que se invoca el método o el constructor.

- **C)** Una palabra clave opcional que siempre se puede omitir sin afectar la funcionalidad del código.
  - Esta opción es incorrecta. Si bien es cierto que en algunos casos `this` se puede omitir (por ejemplo, cuando se accede a campos o métodos de instancia sin ningún conflicto de nombres), su uso es necesario para situaciones como el encadenamiento de constructores (llamada `this()`) o cuando los nombres de los parámetros del método ocultan los nombres de los campos de instancia. En tales escenarios, "esto" aclara por quéA qué variable hace referencia el código.

- **D)** Una referencia al objeto actual, cuya variable de instancia se está llamando.
  - Esta opción es correcta. La palabra clave `this` en Java se utiliza para referirse al objeto actual: el objeto cuya variable de instancia, método o constructor se está llamando. Puede ver su uso en la línea 5 para llamar a otro constructor dentro de la misma clase, en la línea 14 para diferenciar entre el parámetro del método `size` y la variable de instancia `size`, y en el método `updateWidget` para acceder a la variable de instancia `size`. Este uso demuestra "esto" como una forma de referirse explícitamente a propiedades o métodos del objeto actual.


**9. Las respuestas correctas son A y B.**

**Explicación:**

- **A)** La palabra clave `super` se usa en el constructor `Dog` para llamar al constructor de superclase.
  - Esta opcional es correcta. En el constructor `Dog`, `super(name);` se usa para llamar al constructor de superclase (`Animal`) con el parámetro `name`. Esto es necesario para inicializar el campo "nombre" heredado de la clase "Animal" en la instancia "Perro".

- **B)** El método `eat` en la clase `Dog` usa `super` para invocar el método `eat` de la superclase.
  - Esta opcional es correcta. El método `eat` en la clase `Dog` llama a `super.eat();` para invocar el método `eat` definido en la superclase (`Animal`). Esto permite que la clase `Dog` extienda la funcionalidad del método `eat` más allá de lo definido en la superclase, demostrando la anulación de métodos y el uso de `super` para acceder al método anulado.

- **C)** Eliminar la llamada `super.eat();` en el método `eat` de la clase `Dog` evitará que la clase `Dog` se compile.
  - Esta opcional es incorrecta. Eliminar la llamada `super.eat();` del método `eat` de la clase `Dog` no impediría que la clase se compilara. Simplemente significaría que el método `eat` de la clase `Dog` ya no llama al método `eat` de la superclase, alterando el comportamiento del programa pero no su compilabilidad.

- **D)** La palabra clave `super` se puede utilizar para acceder a métodos `estáticos` desde la superclase.
  - Esta opcional es incorrecta. Si bien `super` de hecho se puede usar para acceder a métodos de superclase, no se usa específicamente ni es necesario para acceder a métodos estáticos. Los métodos estáticos pertenecen a la clase, no a las instancias, y deben invocarse utilizando el nombre de la clase. `super` se usa principalmente para métodos y constructores, por ejemplo.



**10. La respuesta correcta es D.**

**Explicación:**

- **A)** Compila e imprime `"Coche conduciendo a velocidad: 60"`.
  - Esta opción es incorrecta porque el método `drive` en la clase `Car` tiene un tipo de parámetro diferente (`long`) que el método en la clase `Vehicle` (`int`). Debido a la diferencia en los tipos de parámetros, el método `drive` de la clase `Car` no anula sino que sobrecarga el método `drive` de la clase `Vehicle`. Dado que el método se llama en una referencia `Vehicle`, se invoca el método `drive` de la clase `Vehicle`.

- **B)** No se compila porque el método `drive` no se puede llamar usando una referencia `Vehicle`. 
  - Esta opción es incorrecta porque "Vehículo" define correctamente el método "conducir".

- **C)** No se compila porque el método `drive` de la clase `Car` no anula correctamente el método `drive` de la clase `Vehicle`.
  - Esta opción es incorrecta porque el código sí se compila. La anotación `@Override` no causa un error en tiempo de compilación aquí porque no se aplica estrictamente en términos de sobrecarga de métodos (cambiar el tipo de parámetro crea una nueva firma de método, lo que la convierte en una sobrecarga válida).

- **D)** Compila e imprime `"Vehículo conduciendo a velocidad: 60"` porque el método `drive` en la clase `Car` es una sobrecarga, no una anulación.
  - Esta opción es correcta. El método `drive` en la clase `Car` tiene una firma diferente del método `drive` en la clase `Vehicle` debido al tipo de parámetro (`int` vs. `long`). Por lo tanto, el método `drive` de la clase `Car` sobrecarga el método de la superclase en lugar de anularlo. Cuando una referencia de "Vehículo" llamael método `drive` con un argumento `int`, invoca el método `drive` de la clase `Vehicle`, no el método de la clase `Car`.


**11. La respuesta correcta es E.**

**Explicación:**

- **A)** Compila e imprime `"Sabor Apple"` seguido de `"Rojo"`.
  - Esta opción es incorrecta porque, si bien el método `flavor` de hecho imprimirá `"Sabor Apple"` debido al polimorfismo (la clase `Apple` anula el método `flavor` de `Fruit`), el código no se compilará si se llama al método `color()` en una referencia `Fruit`. Esto se debe a que el método `color` no forma parte de la interfaz de la clase `Fruit`.

- **B)** Compila e imprime `"Sabor a fruta"`.
  - Esta opción es incorrecta por una razón similar a A. El método `sabor` imprimiría `"sabor a manzana"` debido al método anulado en la clase `Apple`, no "sabor a fruta"`. Sin embargo, la presencia de la llamada al método `color()` aún impediría la compilación.

- **C)** Se compila pero genera una excepción de tiempo de ejecución al intentar llamar a `color()`.
  - Esta opción es incorrecta porque el problema ocurre en tiempo de compilación, no en tiempo de ejecución. El compilador de Java no permitirá que se llame a un método en un tipo de referencia si ese método no está definido en la clase del tipo de referencia o en su jerarquía de superclases.

- **D)** No compila porque `Apple` no es un tipo válido de `Fruit`.
  - Esta opción es incorrecta. `Apple` es un tipo válido de `Fruit` debido a la herencia (`Apple extends Fruit`). Esta relación permite que una variable "Fruit" haga referencia a un objeto "Apple".

- **E)** No compila porque el método `color` no está definido en la clase `Fruit`.
  - Esta opción es correcta. El método `color` solo se define en la clase `Apple` y no en la clase `Fruit`. Dado que el tipo de referencia de `myFruit` es `Fruit`, que no tiene un método `color`, intentar llamar a `myFruit.color()` resultará en un error de compilación. Esto ilustra un principio clave del polimorfismo: el tipo de referencia (no el objeto) determina qué métodos se pueden llamar.


**12. Las respuestas correctas son B y D.**

**Explicación:**

- **A)** `((Perro)otroAnimal).ladrar();`
  - Esta opción es incorrecta porque intenta convertir "otroAnimal" a "Perro" sin verificar primero su tipo real. Dado que `anotherAnimal` es una instancia de `Animal` (no `Dog`), intentar esta conversión se compilará, pero provocará una `ClassCastException` en tiempo de ejecución.

- **B)** `si (otra instancia de Animal de Perro) ((Perro)otro Animal).ladrar();` 
  - Esta opción es correcta. Utiliza `instanceof` para comprobar si `anotherAnimal` es una instancia de `Dog` antes de intentar realizar el lanzamiento y llamar a `bark()`. En este caso, dado que "anotherAnimal" no es una instancia de "Dog", la verificación evita la conversión y la llamada al método, evitando una "ClassCastException".

- **C)** `((Gato)animal).miau();`
  - Esta opción es incorrecta porque convierte `animal` en `Cat` e intenta llamar a `meow()`. Dado que "animal" es en realidad una instancia de "Perro", esta conversión se compilará pero generará una "ClassCastException" en tiempo de ejecución.

- **D)** `if (otra instancia Animal de Gato) ((Gato)otro Animal).meow();`
  - Esta opción es correcta. Comprueba si `anotherAnimal` es una instancia de `Cat` antes de convertirlo en `Cat` y llamar a `meow()`.


**13. La respuesta correcta es A.**

**Explicación:**

- **A)** Compila e imprime `"Cadena con Java: ¡Hola Java!"` seguido de `"Entero mayor que 10: 15"`.
  - Esta opción es correcta. El fragmento de código demuestra efectivamente el uso de la coincidencia de patrones con el operador `instanceof` para los tipos `String` e `Integer`. La función de coincidencia de patrones verifica si `input` es una instancia de `String` o `Integer` y la vincula a una variable (`s` para `String` e `i` para `Integer`) dentro del alcance de los bloques `if` y `else if`. El operador lógico `&&` se usa correctamente para verificar más condicionalmente las propiedades de las variables (`s.contains("Java")` y `i > 10`). Por lo tanto, el método `proceso` imprime la salida para las entradas que son una `String` que contiene `"Java"` y un `Entero` mayor que `10`, respectivamente.

- **B)** Compila pero solo imprime `"Cadena con Java: ¡Hola Java!"` porque los números enteros no son compatibles con la coincidencia de patrones.
  - Esta opción es incorrecta porque la coincidencia de patrones funciona para cualquier tipo de referencia, incluido "Entero". El código admite números enteros y realiza comprobaciones adicionales utilizando correctamente la coincidencia de patrones.

- **C)** No se compila porque la coincidencia de patrones en `instanceof` no se puede combinar con operadores lógicos como `&&`.
  - Esta opción es incorrecta. El código se compilará y ejecutará como se esperaba. De hecho, la coincidencia de patrones en `instanceof` se puede combinar con operadores lógicos como `&&` para realizar comprobaciones adicionales en la misma declaración condicional, como se demuestra en el fragmento de código.

- **D)** Compila pero imprime las cuatro líneas debido al uso incorrecto de la coincidencia de patrones que siempre se evalúa como "verdadero".
  - Esta opción es incorrecta porque el uso de coincidencia de patrones en el código proporcionado es correcto y no siempre se evalúa como "verdadero". El código imprime correctamente mensajes específicos solo para las entradas que coinciden con las condiciones dadas.


**14. La respuesta correcta es E.**

**Explicación:**

- **A)** Hacer públicos los métodos `setName`, `setPrice` y `setStock` mejoraría la encapsulación de la clase.
  - Esta opción es incorrecta. Hacer que los configuradores sean públicos en realidad reduciría la encapsulación de la clase al permitir que clases externas modifiquen los campos sin restricciones, evitando potencialmente cualquier lógica de validación contenida en los configuradores.

- **B)** La clase no está encapsulada porque los campos de la clase `Product` son `privados`. 
  - Esta opción es incorrecta. El uso de campos "privados" es un aspecto fundamental de la encapsulación. Evita que las clases externas accedan directamente y modifiquen el estado del objeto, lo que exige la encapsulación.

- **C)** La encapsulación está debilitada porque el constructor permite la configuración directa de campos sin validación.
  - Esta opción es incorrecta. El constructor no debilita la encapsulación; en su lugar, utiliza configuradores "privados" que contienen lógica de validación. Esto garantiza que el estado del objeto se gestione y valide correctamente en el momento de su creación.

- **D)** La clase `Producto` debe tener captadores privados de paquete para mejorar la encapsulación.
  - Esta opción es incorrecta. Hacer que los captadores sean paquetes privados limitaría la usabilidad de la clase y no mejora inherentemente la encapsulación. Los captadores públicos son necesarios para que las clases externas vean (pero no modifiquen) el estado del objeto.

- **E)** La clase está encapsulada adecuadamente proporcionando captadores públicos para todos los campos y configuradores privados con validación, lo que garantiza el control sobre el estado de sus objetos.
  - Esta opción es correcta. La clase "Producto" demuestra prácticas de encapsulación adecuadas al hacer que sus campos sean "privados" y controlar el acceso a ellos a través de captadores "públicos" y definidores "privados". Los configuradores incluyen lógica de validación, lo que garantiza que solo se asignen estados válidos a los campos. Este patrón de diseño garantiza que el estado interno de las instancias de "Producto" esté protegido y gestionado correctamente.


**15. Las respuestas correctas son A y E.**

**Explicación:**

- **A)** La clase `CuentaDeAhorros` no puede acceder al campo `saldo` directamente debido a su modificador de acceso `private` en la clase `Cuenta`.
  - Esta opción es correcta. El diseño restringe intencionalmente el acceso directo al campo "saldo" para mantener la encapsulación.

- **B)** El método `getBalance` debe ser `public` para permitir que `SavingsAccount` acceda al saldo de la cuenta.
  - Esta opción es incorrecta. Hacer "getBalance" "público" aumentaría su visibilidad innecesariamente. "protected" es suficiente para el acceso a la subclase y este cambio no es necesario para que "SavingsAccount" funcione correctamente, lo que hace que esta declaración sea incorrecta.

- **C)** El método `depósito` en la clase `Cuenta` debe marcarse como `final` para evitar su anulación.
  - Esta opción es incorrecta. Marcar "depósito" como "final" evitaría que se anule ensubclases, lo cual no es un requisito o sugerencia indicado por el código dado. La decisión de hacer que un método sea "final" debe basarse en las necesidades de diseño específicas más que en un principio general de encapsulación.

- **D)** El campo `interestRate` en la clase `SavingsAccount` viola los principios de encapsulación al ser "privado".
  - Esta opción es incorrecta. Usar un modificador de acceso "privado" para "interestRate" en "SavingsAccount" es un ejemplo de encapsulación adecuada. Restringe el acceso al campo desde fuera de la clase, lo cual está alineado con los principios de encapsulación, lo que hace que esta opción sea incorrecta.

- **E)** La clase `Cuenta` encapsula correctamente el campo `saldo`, y `SavingsAccount` se adhiere a la encapsulación accediendo a `saldo` a través de `getBalance` y `deposit`.
  - Esta opción es correcta. La clase `Cuenta` usa acceso `private` para el campo `saldo` para encapsular su estado, proporcionando métodos `protegidos` y de paquete privado (`getBalance` y `depósito`) para acceso controlado y modificación. `SavingsAccount` respeta esta encapsulación al utilizar estos métodos para interactuar con el campo `saldo`, lo que demuestra una comprensión y aplicación adecuadas de los principios de encapsulación. Este diseño permite que "SavingsAccount" aproveche la funcionalidad proporcionada por "Account" sin romper la encapsulación, que es un objetivo clave en el diseño orientado a objetos.


**16. La respuesta correcta es C.**

**Explicación:**

- **A)** El objeto `Contacto` es mutable porque la clase `Dirección` no es `final`.
  - Esta opción es incorrecta porque la clase `Dirección` no afecta directamente la inmutabilidad del objeto `Contacto`. La clase `Contact` garantiza su inmutabilidad al no proporcionar establecedores y al realizar copias profundas de objetos mutables, como `Address`, tanto en el constructor como en el getter.

- **B)** El objeto `Contacto` es inmutable, pero sólo porque no proporciona configuradores.
  - Esta opción es incorrecta. Si bien es cierto que no proporciona establecedores, esta opción no captura completamente la esencia de la inmutabilidad. Por lo tanto, no resalta el hecho de que todos los campos en "Contacto" son "finales" y la estrategia de copia defensiva.

- **C)** El objeto "Contacto" es inmutable y previene adecuadamente la fuga de estado interno mutable mediante copia defensiva.
  - Esta es la opción correcta. La clase "Contacto" es inmutable porque cumple con todos los criterios de inmutabilidad: la clase se declara como "final" (evitando la subclasificación), todos sus campos son "privados" y "finales", y no proporciona ningún definidor. Además, implementa una copia defensiva para el campo mutable "Dirección" para garantizar que el estado interno no pueda ser alterado por cambios externos en los objetos "Dirección" pasados ​​o devueltos. Esto evita la fuga de su estado interno mutable.

- **D)** El objeto `Contact` es mutable porque el objeto `Address` se puede cambiar mediante el método `getAddress`. 
  - Esta opción es incorrecta porque la inmutabilidad del objeto "Contacto" se mantiene mediante copia defensiva. El método `getAddress` devuelve una nueva instancia de `Address` cada vez que se llama, lo que garantiza que el estado del objeto `Address` original no pueda modificarse desde fuera del objeto `Contact`.

- **E)** El objeto `Contacto` es inmutable pero no impide el acceso a su estado interno mutable.
  - Esta opción es incorrecta porque el objeto `Contacto` implementa una estrategia para impedir el acceso a su estado interno mutable: utiliza copia defensiva para el objeto `Dirección` tanto en el método constructor como en el método getter, lo que garantiza que el estado interno permanezca sin cambios debido a modificaciones externas.