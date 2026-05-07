-"None"
layout: chapter

title: "Capítulo SIETE"
subtitle: "Manejo de errores y excepciones"
exam_objectives:
  - "Maneje excepciones usando try/catch/finally, try-with-resources y bloques multi-catch, incluidas excepciones personalizadas."

previous_link: "/ch06.html"
previous_title: "Matrices, genéricos y colecciones"
next_link: "/ch08.html"
next_title: "Interfaces funcionales y expresiones Lambda"
answers_link: "/ch07a.html"
-"None"## Contenido del capítulo

- [Comprensión de las excepciones](#comprensión-excepciones)
    - [Comprensión de los tipos de excepciones](#comprensión-de-los-tipos-de-excepciones)
    - [Lanzar una excepción](#lanzar-una-excepción)
    - [Excepciones personalizadas](#excepciones-personalizadas)
    - [Excepciones y métodos](#excepciones-y-métodos)
    - [Comprensión de los seguimientos de pila] (# comprensión-stack-traces)
    - [Reconociendo clases de excepción](#reconociendo-clases-de-excepción)
- [Manejo de excepciones](#handling-exceptions)
    - [El bloque `try-catch`](#el bloque-try-catch)
    - [El bloque `finalmente`](#el-bloque-finalmente)
- [Automatización de la gestión de recursos con el bloque `probar-con-recursos`](#automatización-de-la-gestión-de-recursos-con-el-bloque-probar-con-recursos)
- [Puntos clave](#puntos clave)
- [Preguntas de práctica](#preguntas-de-practica)

---

## Comprender las excepciones
Poder ejecutar un programa no es lo mismo que ejecutarlo correctamente. Cualquier programa no trivial es susceptible a errores. Errores en las entradas del usuario, operaciones matemáticas y acceso a recursos, etc. 

Ahí es donde entran en juego las excepciones. Una excepción indica algún tipo de condición anormal o excepcional que interrumpe el flujo normal del programa.

Cuando surge una condición excepcional, se dice que se *lanza* una excepción. Cuando esto sucede, el flujo normal del programa se interrumpe y la ejecución se transfiere a un bloque de código especial llamado controlador de excepciones, si existe uno para la excepción.

El propósito de las excepciones es manejar estos errores correctamente y evitar que el programa falle o se comporte inesperadamente. Al utilizar excepciones, puede separar el código de manejo de errores de la lógica normal del programa, haciendo que su código sea más limpio y manejable.

Las excepciones no necesariamente detienen el programa por completo cuando ocurren. Cuando se produce una excepción, se propaga por la pila de llamadas hasta que un controlador de excepciones la detecta y la maneja. Si no se encuentra ningún controlador adecuado, el programa finalizará. Pero si tiene un bloque `try-catch` para manejar la excepción, su programa puede solucionar el problema y continuar ejecutándose.

### Comprender los tipos de excepciones
Las clases de excepción más comunes en Java son subtipos de la clase `java.lang.Exception`. Pero esa no es toda la historia. Para comprender realmente las excepciones, debemos observar la jerarquía de excepciones:```
                         ┌───────────┐
                         │ Throwable │
                         └─────┬─────┘
                               │
                   ┌───────────┴───────────┐
                   │                       │
           ┌───────────────┐       ┌───────────────┐
           │   Exception   │       │     Error     │
           └───────┬───────┘       └───────────────┘
                   │
       ┌───────────┴───────────┐
       │                       │
┌──────────────────┐   ┌───────────────────┐
│ RuntimeException │   │ Checked Exceptions│
└──────────────────┘   └───────────────────┘
```En la cima de la jerarquía está la clase `java.lang.Throwable`. Debajo de "Throwable" hay dos ramas: "Exception" y "Error". Si bien pueden parecer similares, en realidad representan cosas bastante diferentes en Java.

Las excepciones son condiciones que una aplicación razonable podría querer detectar y manejar. Por lo general, representan condiciones que, si bien son inusuales, no son del todo inesperadas. Por ejemplo, intentar abrir un archivo que no existe generaría una `FileNotFoundException`.

Los errores, por otro lado, no deben ser detectados ni manejados por su programa. Indican problemas graves que una aplicación razonable no debería intentar detectar. La mayoría de estos errores son condiciones anormales. Por ejemplo, si su aplicación se queda sin memoria, se generará un "OutOfMemoryError".

Debajo de la clase `Exception`, hay dos categorías más: excepciones marcadas y excepciones no marcadas (también conocidas como excepciones de tiempo de ejecución porque se extienden desde `java.lang.RuntimeException`).

Las excepciones marcadas son condiciones excepcionales que una solicitud bien redactada debe anticipar y manejar. Por lo general, se trata de excepciones que están fuera del control del programa, como un archivo que no se encuentra, una conexión de red que falla o una entrada de usuario no válida. Las excepciones marcadas son subclases de "Exception" pero no de "RuntimeException".

Las excepciones no comprobadas son condiciones excepcionales que la aplicación normalmente no puede anticipar ni recuperarse. Suelen indicar errores de programación, como errores lógicos o uso inadecuado de una API. Las excepciones no marcadas son subclases de "RuntimeException".

Si bien puede parecer que las excepciones no verificadas son suficientes, las excepciones verificadas tienen un propósito importante. Obligan al programador a manejar la excepción, asegurando que se escriba el código de manejo de errores adecuado. Esto conduce a un código más robusto y confiable.

Por otro lado, no es necesario declarar excepciones no comprobadas en la cláusula `throws` de un método si pueden generarse mediante la ejecución del método. Por lo general, representan defectos en el programa (errores) y, como tales, no se puede esperar razonablemente que el código del cliente API se recupere de ellos ni los maneje de ninguna manera. Estas excepciones suelen indicar defectos de programación, y una excepción no comprobada es la forma en que el lenguaje de programación Java permite a un desarrollador indicar un defecto potencial donde el compilador no puede detectar fácilmente el problema.

### Lanzar una excepción
Hasta ahora hemos hablado sobre qué son las excepciones y los diferentes tipos de excepciones. Pero, ¿cómo se lanzan realmente las excepciones?

Se puede generar una excepción de dos maneras: automáticamente mediante el sistema de ejecución de Java o explícitamente mediante su código.

El sistema de ejecución de Java genera automáticamente muchas excepciones. Por ejemplo, si intenta acceder a un elemento de matriz con un índice que está fuera de límites, se generará una `ArrayIndexOutOfBoundsException`. Si intenta dividir un número entre cero, se generará una "ArithmeticException".

Pero también puedes lanzar excepciones explícitamente en tu código usando la declaración `throw`. La forma general de la declaración "lanzamiento" es:```java
throw new ExceptionType(messageString);
```Aquí, `ExceptionType` es el tipo de excepción que desea generar y `messageString` es una cadena opcional que proporciona más información sobre la excepción.

Por ejemplo, digamos que tiene un método que acepta un parámetro de edad entero. Si la edad pasada es negativa, es posible que desees lanzar una excepción:```java
public void checkAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
    // rest of the method
}
```En este caso, estamos lanzando una "IllegalArgumentException", que es un tipo de excepción no comprobada.

Hay muchos casos en los que es apropiado, e incluso necesario, lanzar excepciones en su propio código.

Al generar excepciones, puede indicar que se ha producido un error y proporcionar información sobre lo que salió mal. Esto es especialmente importante cuando escribes métodos o clases que serán utilizados por otros desarrolladores. Al lanzar excepciones, puede comunicar al usuario de su código que ha utilizado su método o clase incorrectamente, o que algo salió mal y debe solucionarlo.

Además, al generar excepciones, puede separar el código de manejo de errores del flujo normal de su programa. Esto hace que su código sea más legible y mantenible.

### Excepciones personalizadas
Si bien Java proporciona un amplio conjunto de excepciones integradas, hay situaciones en las que puede resultar beneficioso crear sus propias excepciones personalizadas.

Las excepciones personalizadas le permiten agregar más contexto y significado a las excepciones lanzadas por su aplicación. Pueden ayudar a encapsular mejor las condiciones de error específicas del dominio de su aplicación.

Por ejemplo, si está escribiendo una biblioteca para analizar archivos XML, puede definir una `XMLFileParseException` personalizada que genere cada vez que haya un error al analizar un archivo XML. Esto le comunica al usuario de su biblioteca exactamente qué salió mal, en lugar de simplemente lanzar una "Excepción" genérica.

Para crear una excepción marcada personalizada, simplemente necesita extender la clase `Exception` (o una de sus subclases):```java
public class XMLFileParseException extends Exception {
    public XMLFileParseException(String message) {
        super(message);
    }
}
```Para crear una excepción personalizada sin marcar, extiende la clase `RuntimeException` (o una de sus subclases):```java
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
```Luego puedes lanzar tus excepciones personalizadas como cualquier otra excepción:```java
throw new XMLFileParseException("Error parsing XML file: " + fileName);
throw new InvalidInputException("Input cannot be negative");
```Al decidir si desea marcar o desmarcar su excepción personalizada, considere las siguientes pautas:

- Utilice excepciones marcadas para condiciones excepcionales de las que la persona que llama debería recuperarse. A menudo representan condiciones que están fuera del control del programa, como que no se encuentre un archivo o que falle una conexión de red.

- Utilice excepciones no marcadas (extendiendo `RuntimeException`) para condiciones excepcionales de las que la persona que llama normalmente no puede recuperarse. Estos suelen indicar errores de programación, como intentar acceder a un elemento de matriz con un índice fuera de límites.

Otra cosa a considerar al crear excepciones personalizadas es la serialización. Si su clase de excepción se va a lanzar en diferentes JVM (por ejemplo, en un sistema distribuido), debe implementar la interfaz `java.io.Serializable`.```java
public class RemoteServiceException extends Exception implements Serializable {
    // ...
}
```Esto garantiza que el objeto de excepción se pueda serializar y deserializar correctamente cuando se transmite a través de la red.

Finalmente, al crear excepciones personalizadas, es una buena práctica proporcionar constructores que acepten una cadena de mensaje y una causa de excepción. La causa es la excepción que desencadenó su excepción. Esto le permite incluir excepciones de nivel inferior en sus excepciones personalizadas de nivel superior, lo que puede proporcionar más contexto sobre el error.```java
public class DataAccessException extends Exception {
    public DataAccessException(String message) {
        super(message);
    }
    
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
```Entonces puedes usarlo así:```java
try {
    // some database operation that throws a SQLException
} catch (SQLException ex) {
    throw new DataAccessException("Error accessing database", ex);
}
```De esta manera, la persona que llama a su código sabe que ocurrió una `DataAccessException`, pero aún puede acceder a la causa subyacente (la `SQLException`) si es necesario para un manejo o registro de errores más detallado.


### Excepciones y métodos
Cuando un método genera una excepción, debe declararla en la firma de su método. Esto se hace usando la palabra clave `throws` seguida de una lista de excepciones que el método podría generar.```java
public void readFile(String fileName) throws FileNotFoundException {
    // code that might throw a FileNotFoundException
}
```En este ejemplo, el método `readFile` declara que podría generar una `FileNotFoundException`.

Sin embargo, no es necesario declarar todas las excepciones en la firma del método. Sólo es necesario declarar las excepciones marcadas. No es necesario declarar las excepciones no marcadas (aquellas que extienden `RuntimeException`).

Esto nos lleva a una distinción importante: la diferencia entre "lanzar" y "lanzar".

- `throw` se usa para lanzar una excepción dentro de un método.
- `throws` se usa en la firma de un método para declarar que el método podría generar una excepción.

Una analogía útil para recordar la diferencia es un partido de béisbol:

- Un lanzador lanza la pelota.
- Pero antes del juego, el manager le dice al árbitro qué tipo de lanzamientos realiza su lanzador (bola rápida, curva, etc.).

De manera similar en Java:

- Un método lanza una excepción.
- Pero en la firma del método, el método declara los tipos de excepciones que arroja.

#### Anulación de métodos con excepciones
Cuando anulas un método en una subclase, puedes declarar que el método arroja menos excepciones marcadas que el método que estás anulando.```java
class Parent {
    public void doSomething() throws IOException, SQLException {
        // ...
    }
}

class Child extends Parent {
    @Override
    public void doSomething() throws IOException {
        // ...
    }
}
```En este ejemplo, el método `doSomething` en la clase `Parent` declara que puede generar una `IOException` o una `SQLException`. Pero cuando anulamos "doSomething" en la clase "Child", declaramos que solo arroja "IOException".

Esto está permitido porque hace que el método sea más utilizable. Una persona que llama al método `doSomething` de la clase `Child` solo necesita manejar `IOException`, no `SQLException`.

Sin embargo, no se permite lo contrario. Si el método de la clase principal no declara ninguna excepción, el método principal de la clase secundaria no puede declarar ninguna excepción marcada.```java
class Parent {
    public void doSomething() {
        // ...
    }
}

class Child extends Parent {
    @Override
    public void doSomething() throws IOException {  // Compile-time error
        // ...
    }
}
```Este código no se compilará porque el método principal (en `Child`) declara una excepción marcada (`IOException`) que el método original (en `Parent`) no declara.

La regla es que un método primordial puede declarar que arroja menos excepciones o excepciones más limitadas (subclases de las excepciones declaradas) que el método original, pero no más excepciones ni más amplias.

Esta regla existe para garantizar que una clase secundaria siempre pueda usarse en lugar de su clase principal sin causar excepciones comprobadas inesperadas. Este es un principio fundamental de polimorfismo y herencia en Java.

Sin embargo, tenga en cuenta que esto sólo se aplica a las excepciones marcadas. Las excepciones no marcadas se pueden agregar libremente al anular métodos.

### Comprensión de los seguimientos de pila
Cuando ocurre una excepción en un programa Java, imprime un seguimiento de la pila. Un seguimiento de la pila proporciona información sobre la excepción y el estado del programa cuando ocurrió la excepción.

Un seguimiento de la pila puede resultar increíblemente útil al depurar un programa. Le indica qué salió mal y en qué parte del código salió mal.

Veamos un ejemplo de seguimiento de pila:```
Exception in thread "main" java.lang.NullPointerException
    at com.example.myproject.Book.getTitle(Book.java:16)
    at com.example.myproject.Author.getBookTitles(Author.java:25)
    at com.example.myproject.App.main(Bootstrap.java:14)
```Este seguimiento de pila nos dice que ocurrió una `NullPointerException` en el método `getTitle` de la clase `Book`, que fue llamado desde la línea 25 del método `getBookTitles` de la clase `Author`, que a su vez fue llamado desde la línea 14 del método `main` de la clase `App`.

Cada línea en el seguimiento de la pila representa una llamada a un método, con la llamada más reciente en la parte superior. La primera línea muestra la excepción lanzada, seguida de las llamadas al método en la pila en ese momento.

Para cada llamada al método, el seguimiento de la pila muestra:
- El nombre completo de la clase que contiene el método.
- El nombre del método.
- El nombre del archivo de código fuente que contiene el método.
- El número de línea en el archivo de código fuente donde ocurrió la llamada al método.

Para leer un seguimiento de la pila, puede comenzar desde arriba y avanzar hacia abajo. La línea superior le indica qué tipo de excepción se lanzó. Las líneas siguientes representan las llamadas a métodos en la pila, con la llamada más reciente en la parte superior.

Cada línea proporciona una pista sobre el estado del programa cuando se lanzó la excepción. Puede utilizar estas pistas para identificar la ubicación en su código donde ocurrió el problema.


### Reconocimiento de clases de excepción
Cuando encuentra una excepción en su programa Java, uno de los primeros pasos para resolver el problema es identificar qué tipo de excepción es. Anteriormente, aprendió sobre la jerarquía de clases de excepción, cada una diseñada para representar un tipo específico de problema. Reconocer estas clases y comprender cuándo se producen puede ayudarle a diagnosticar problemas más rápidamente.

**Consejo 1: lea el nombre de la clase de excepción**
El nombre de la clase de excepción suele indicar qué salió mal. Por ejemplo, una `NullPointerException` sugiere que estás intentando usar una referencia `nula`, una `ArrayIndexOutOfBoundsException` indica que estás intentando acceder a una matriz con un índice no válido y una `IOException` indica que algo salió mal durante una operación de entrada/salida.

Familiarizarse con los nombres y significados de las clases de excepción más comunes puede ayudarle a identificar rápidamente problemas en su código.

**Consejo 2: comprenda la jerarquía de excepciones**
Comprender la jerarquía de excepciones de Java también puede ayudar a reconocer excepciones. Todas las excepciones en Java heredan de la clase "Throwable", que tiene dos subclases principales: "Excepción" y "Error".

Las excepciones que heredan directamente de la clase `Exception` son excepciones marcadas. Por lo general, estos representan problemas que deben manejarse en su código. Los ejemplos comunes incluyen `IOException` y `SQLException`

Las excepciones que heredan de la clase `RuntimeException` (que es una subclase de `Exception`) son excepciones no comprobadas. Estos a menudo indican errores de programación, como intentar acceder a un elemento de matriz con un índice fuera de límites (`ArrayIndexOutOfBoundsException`) o intentar usar una referencia `nula` (`NullPointerException`).

Los errores, por otro lado, representan problemas graves que una aplicación razonable no debería intentar detectar. Por lo general, estas son condiciones irrecuperables, como quedarse sin memoria (`OutOfMemoryError`) o un desbordamiento de pila (`StackOverflowError`).

**Consejo 3: lea el mensaje de excepción**
Cuando se lanza una excepción, normalmente incluye un mensaje con más detalles sobre lo que salió mal. Este mensaje puede ser increíblemente útil para diagnosticar el problema.

Por ejemplo, considere este mensaje de excepción:```
java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 5
```Este mensaje indica que el código está intentando acceder al elemento en el índice 10 en una matriz que solo tiene 5 elementos.

**Consejo 4: mira el seguimiento de la pila**
El seguimiento de la pila que acompaña a una excepción también puede proporcionar pistas valiosas sobre lo que salió mal. El seguimiento de la pila muestra la secuencia de invocaciones de métodos que llevaron a la excepción.

Cada línea en el seguimiento de la pila representa una llamada a un método, con la llamada más reciente en la parte superior. La línea le indica el nombre del método, la clase en la que se encuentra y el número de línea en el código fuente donde ocurrió la llamada.

Al rastrear el seguimiento de la pila, a menudo puede identificar la ubicación exacta en su código donde ocurrió el problema.

**Consejo 5: consulte la documentación de la API de Java**
Si encuentra una excepción con la que no está familiarizado, la documentación de la API de Java puede ser un gran recurso. La documentación de cada clase de excepción proporciona información sobre cuándo se produce la excepción y, a menudo, incluye ejemplos de cómo manejarla.

Por ejemplo, la [documentación para `ArrayIndexOutOfBoundsException`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ArrayIndexOutOfBoundsException.html) establece:```
Thrown to indicate that an array has been accessed with an illegal index. The index is either negative or greater than or equal to the size of the array.
```Esto proporciona una explicación clara de cuándo puede esperar encontrar esta excepción.

Finalmente, aquí hay una lista de excepciones comunes no verificadas y verificadas, así como clases de error:

**Excepciones comunes no comprobadas**
Las excepciones no verificadas son aquellas que no se verifican en tiempo de compilación. Suelen representar errores de programación, como errores de lógica o uso inadecuado de una API.

1. **Excepción aritmética**
   - Lanzado cuando se ha producido una condición aritmética excepcional.
2. **Excepción ArrayIndexOutOfBounds**
   - Se lanza para indicar que se ha accedido a una matriz con un índice ilegal.
3. **Excepción ClassCast**
   - Se lanza para indicar que el código ha intentado convertir un objeto a una subclase de la cual no es una instancia.
4. **Excepción de argumento ilegal**
   - Se lanza para indicar que a un método se le ha pasado un argumento ilegal o inapropiado.
5. **Excepción de Estado Ilegal**
   - Señala que un método ha sido invocado en un momento ilegal o inadecuado.
6. **Excepción de puntero nulo**
   - Se lanza cuando una aplicación intenta usar "nulo" en un caso en el que se requiere un objeto.
7. **Excepción de formato de número**
   - Se lanza para indicar que la aplicación ha intentado convertir una cadena a uno de los tipos numéricos, pero que la cadena no tiene el formato apropiado.

**Excepciones comunes marcadas**
Las excepciones marcadas son aquellas que se verifican en tiempo de compilación. Generalmente representan condiciones que una aplicación razonable podría querer detectar.

1. **Excepción de clase no encontrada**
   - Se produce cuando una aplicación intenta cargar una clase a través de su nombre de cadena pero no se puede encontrar ninguna definición para la clase con el nombre especificado.
2. **Excepción IO**
   - Señala que se ha producido una excepción de E/S de algún tipo.
3. **Excepción de archivo no encontrado**
   - Indica que ha fallado un intento de abrir el archivo indicado por un nombre de ruta específico.
4. **Excepción interrumpida**
   - Se lanza cuando un hilo está esperando, inactivo o ocupado de otro modo y el hilo se interrumpe.
5. **Excepción SQL**
   - Una excepción que proporciona información sobre un error de acceso a la base de datos u otros errores.
6. **Excepción de tiempo de espera**
   - Se lanza cuando se agota el tiempo de espera de una operación de bloqueo.

**Clases de errores comunes**
Los errores generalmente los genera la máquina virtual Java e indican problemas graves que las aplicaciones no deberían intentar detectar.

1. **Error de falta de memoria**
   - Se produce cuando la JVM no puede asignar un objeto porque no tiene memoria.
2. **Error de desbordamiento de pila**
   - Se produce cuando se produce un desbordamiento de pila porque una aplicación se repite demasiado.
3. **Error de máquina virtual**
   - Se lanza para indicar que la Máquina Virtual Java está averiada o se ha quedado sin recursos necesarios para que siga funcionando.
4. **Error desconocido**
   - Se lanza cuando se produce una excepción desconocida pero grave.



## Manejo de excepciones
Cuando ocurre una excepción en su programa Java, debe manejarla para evitar que su programa finalice abruptamente. Esto se hace usando bloques `try-catch`.

### El bloque `try-catch`
La sintaxis básica de un bloque try-catch es la siguiente:```java
try {
    // code that might throw an exception
} catch (ExceptionType e) {
    // code to handle the exception
}
```Coloca el código que podría generar una excepción en el bloque "try". Si ocurre una excepción dentro del bloque `try`, el bloque `catch` la detecta. El bloque `catch` especifica el tipo de excepción que puede manejar (`ExceptionType` en la sintaxis anterior) y proporciona el código para manejar esa excepción.

He aquí un ejemplo concreto:```java
try {
    File file = new File("example.txt");
    Scanner scanner = new Scanner(file);
    while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
    }
    scanner.close();
} catch (FileNotFoundException e) {
    System.out.println("File not found: " + e.getMessage());
}
```En este ejemplo, intentamos leer desde un archivo llamado `example.txt`. Si el archivo no existe, se generará una "FileNotFoundException". El bloque `catch` detecta esta excepción e imprime un mensaje indicando que no se encontró el archivo.

Puede utilizar varios bloques `catch` para manejar diferentes tipos de excepciones. Si ocurre una excepción en el bloque `try`, Java buscará el primer bloque `catch` que pueda manejar la excepción, comenzando desde arriba.```java
try {
    // code that might throw exceptions
} catch (IOException e) {
    // handle IOException
} catch (SQLException e) {
    // handle SQLException
}
```En este ejemplo, si se produce una "IOException" en el bloque "try", será manejada por el primer bloque "catch". Si se produce una "SQLException", será manejada por el segundo bloque "catch".

También puede detectar múltiples excepciones en un solo bloque "catch". Esto se conoce como bloque de "captura múltiple".```java
try {
    // code that might throw exceptions
} catch (IOException | SQLException e) {
    // handle either IOException or SQLException
}
```En este ejemplo, el bloque `catch` manejará una `IOException` o una `SQLException`.

Esto puede hacer que su código sea más conciso, pero solo debe usarse cuando desee manejar las excepciones de la misma manera. Si necesita manejar las excepciones de manera diferente, use bloques "catch" separados.

Además, el bloque multicaptura debe detectar dos o más excepciones no relacionadas. Las excepciones no relacionadas no comparten una relación padre-hijo en la jerarquía de excepciones, como `IOException` y `SQLException` en el ejemplo anterior.

Sin embargo, es importante ordenar los bloques "catch" del más específico al más general. Esto significa colocar bloques catch que detectan subclases de excepciones antes que aquellos que detectan sus excepciones de superclase. Si colocó el bloque catch `IOException` antes del bloque catch `FileNotFoundException`, nunca se alcanzará el bloque catch `FileNotFoundException` porque `FileNotFoundException` es una subclase de `IOException`. Como resultado, el bloque catch `IOException` capturaría todas las excepciones de tipo `IOException`, incluida `FileNotFoundException`, y se omitiría el código de manejo más específico para `FileNotFoundException`.

Por ejemplo:```java
try {
    // code that might throw exceptions
} catch (FileNotFoundException e) {
    // handle FileNotFoundException
} catch (IOException e) {
    // handle IOException
}
```En este ejemplo, si se produce una excepción `FileNotFoundException`, será detectada por el primer bloque `catch`. Si ocurre una "IOException" diferente, será capturada por el segundo bloque "catch". Esto garantiza que las excepciones específicas se manejen adecuadamente antes que las excepciones más generales.

### El bloque "finalmente"
El bloque `finally` se usa para ejecutar código que siempre debería ejecutarse, independientemente de si se lanzó una excepción o no:```java
try {
    // code that might throw an exception
} catch (ExceptionType e) {
    // handle the exception
} finally {
    // code that always runs
}
```El bloque "finalmente" se usa a menudo para tareas de limpieza, como cerrar archivos o conexiones de bases de datos.

Si se ejecuta una declaración `return` dentro del bloque `try`, el bloque `finally` aún se ejecutará antes de que el método regrese:```java
public static int returnTest() {
    try {
        return 1;
    } catch (Exception e) {
        return 2;
    } finally {
        System.out.println("Finally block");
    }
}
```En este ejemplo, aunque regresamos desde dentro del bloque `try`, el bloque `finally` aún se ejecutará e imprimirá `"Bloque final"` antes de que el método regrese.

Lo mismo ocurre si la declaración `return` está en un bloque `catch`, el bloque `finally` aún se ejecutará antes de que el método regrese.

Sin embargo, "finalmente" no se ejecutará si llama a "System.exit()" en el bloque "try" o "catch". `System.exit()` hace que la máquina virtual Java salga y el bloque `finally` no se ejecutará antes de que finalice el programa:```java
try {
    System.out.println("Try block");
    System.exit(0);
} catch (Exception e) {
    System.out.println("Catch block");
} finally {
    System.out.println("Finally block");
}
```En este ejemplo, llamamos `System.exit(0)` en el bloque `try`, por lo que solo imprimirá `"Bloque Try"` antes de que finalice el programa.

Puede utilizar un bloque `try` con un bloque `finally` y sin ningún bloque `catch`.```java
try {
    // code that might throw an exception
} finally {
    // code that always runs
}
```Esto puede resultar útil cuando desea asegurarse de que cierto código siempre se ejecute, incluso si se genera una excepción, pero en realidad no desea controlar la excepción en este método.

Por último, es posible que el bloque "finalmente" lance una excepción. Si esto sucede, y también hubo una excepción en el bloque `try`, la excepción del bloque `finally` será la que realmente se genere.```java
try {
    throw new Exception("Exception in try");
} finally {
    throw new Exception("Exception in finally");
}
```En este ejemplo, la excepción lanzada en el bloque "finalmente" será la que realmente arroja el método. Se suprimirá la excepción del bloque "try".


## Automatización de la gestión de recursos con el bloque "probar con recursos"
Introducida en Java 7, la declaración "try-with-resources" es una declaración "try" que declara uno o más recursos. Un recurso es un objeto que debe cerrarse una vez finalizado el programa. La declaración `try-with-resources` garantiza que cada recurso se cierre al final de la declaración.

La sintaxis básica de una declaración "probar con recursos" es:```java
try (Resource declaration) {
    // use the resource
} catch (ExceptionType e1) {
    // catch block
}
```Para que un recurso se utilice en una declaración `try-with-resources`, debe implementar la interfaz `java.lang.AutoCloseable`. Esta interfaz tiene un único método, `close()`, que se llama automáticamente al final del bloque `try`:```java
public interface AutoCloseable {
    void close() throws Exception;
}
```Alternativamente, los recursos pueden implementar la interfaz `java.io.Closeable`:```java
public interface Closeable  extends AutoCloseable  {
    void close() throws IOException;
}
```Ambos declaran un método `close()`, y la única diferencia práctica entre estas dos interfaces es que el método `close` de la interfaz `Closeable` solo arroja excepciones de tipo `IOException`, mientras que el método `close` de la interfaz `AutoCloseable` arroja excepciones de tipo `Exception` (en otras palabras, puede generar cualquier tipo de excepción):

Sin embargo, muchos de los recursos estándar de Java, como `Scanner`, `FileReader` y `DatabaseConnection`, ya implementan `AutoCloseable`.

Los recursos se pueden declarar dentro del paréntesis de la declaración "try", separados por punto y coma si hay varios recursos.```java
try (Scanner scanner = new Scanner(new File("example.txt"));
     PrintWriter writer = new PrintWriter(new File("output.txt"))) {
    // use the resources
}
```Los recursos se declaran para que puedan cerrarse sin hacerlo explícitamente en un bloque "finalmente". Además, los recursos declarados en la declaración "try-with-resources" solo están dentro del alcance dentro del bloque "try". Son efectivamente "finales", lo que significa que no se les puede asignar un nuevo valor después de que se hayan inicializado.

Entonces, si los recursos se declaran fuera de la declaración `try-with-resources`, deben ser definitivos:```java
final Scanner scanner = new Scanner(new File("example.txt"));
final PrintWriter writer = new PrintWriter(new File("output.txt"));

try (scanner; writer) {
    // use the resources
}

```O efectivamente final:```java
Scanner scanner = new Scanner(new File("example.txt"));
PrintWriter writer = new PrintWriter(new File("output.txt"));

// No reassignment after initialization makes them effectively final
try (scanner; writer) {
    // use the resources
}

```Si se declaran varios recursos, deben estar separados por un punto y coma y se cierran en el orden inverso a su declaración. Esto es importante si los recursos dependen unos de otros.```java
try (Scanner scanner = new Scanner(new File("example.txt"));
     DatabaseConnection connection = DriverManager.getConnection(DB_URL)) {
    // use the resources
}
```En este ejemplo, la conexión se cerrará antes que el escáner.

Como se mencionó anteriormente, los recursos declarados en una declaración de "prueba con recursos" son efectivamente "definitivos". Si bien no es necesario declararlos explícitamente como "finales", no puede asignarles un nuevo valor después de que se hayan inicializado:```java
try (Scanner scanner = new Scanner(new File("example.txt"))) {
    scanner = new Scanner(new File("other.txt"));  // This will not compile
}
```Sin embargo, una cosa a tener en cuenta al "probar con recursos" es la posibilidad de excepciones suprimidas.

Las excepciones suprimidas solo ocurren cuando tanto el bloque `try` como el método `close()` arrojan excepciones. 

Si se lanza una excepción desde el bloque `try` y otra excepción desde la llamada automática `close()`, se suprime la excepción de la llamada `close()`. Se agrega como una excepción suprimida a la excepción lanzada desde el bloque "try".```java
try (Scanner scanner = new Scanner(new File("example.txt"))) {
    throw new IllegalStateException("Thrown from try");
}
```Si la llamada a `scanner.close()` también genera una excepción, esa excepción se agregará como una excepción suprimida a `IllegalStateException`.

Puede recuperar estas excepciones suprimidas llamando al método `Throwable[] java.lang.Throwable.getSuppressed()` en la excepción lanzada por el bloque `try`:```java
try (Scanner scanner = new Scanner(new File("example.txt"))) {
    throw new IllegalStateException("Thrown from try");
} catch (Exception e) {
    System.err.println(e.getMessage());
    Stream.of(e.getSuppressed())
        .forEach(t -> System.err.println(t.getMessage()));
}
```Este es el resultado (asumiendo que el método `close()` arroja una excepción):```
Thrown from try
Close Exception
```## Puntos clave
- Una excepción es una condición anormal que interrumpe el flujo normal de un programa. Cuando ocurre una excepción, se dice que es *lanzada*.

- El propósito de las excepciones es manejar los errores correctamente y evitar que el programa falle o se comporte inesperadamente.

- Todas las clases de excepción en Java son subtipos de la clase `java.lang.Exception`. Las dos ramas principales de "Excepción" son excepciones marcadas y excepciones no marcadas (también conocidas como excepciones de tiempo de ejecución).

- Las excepciones marcadas son condiciones excepcionales que una aplicación bien redactada debe anticipar y manejar, mientras que las excepciones no marcadas generalmente no son recuperables.

- El tiempo de ejecución de Java puede generar una excepción automáticamente o explícitamente en el código mediante la instrucción `throw`.

- Se pueden crear excepciones personalizadas extendiendo la clase `Exception` (para excepciones marcadas) o la clase `RuntimeException` (para excepciones no marcadas).

- Cuando un método arroja una excepción, debe declararla en la firma de su método usando la palabra clave `throws`. Sólo es necesario declarar las excepciones marcadas.

- Un seguimiento de la pila proporciona información sobre una excepción y el estado del programa cuando ocurrió la excepción. Se puede utilizar para identificar la ubicación en el código donde ocurrió un problema.

- Las clases de excepción se pueden reconocer por su nombre, su lugar en la jerarquía de excepciones, el mensaje de excepción y consultando la documentación de la API de Java.

- Las excepciones se manejan mediante bloques `try-catch`. El bloque "finalmente" se usa para ejecutar código que debería ejecutarse independientemente de si se lanzó una excepción.

- El bloque `multi-catch` nos permite capturar dos o más excepciones no relacionadas con un solo bloque `catch`.

- El bloque `finally` siempre se ejecuta, incluso cuando se detecta una excepción o cuando el bloque `try` o `catch` contiene una declaración `return`. Sin embargo, el bloque `finally` no se ejecutará si la JVM sale durante el bloque `try` o `catch`, como por ejemplo llamando a `System.exit()`.

- La declaración `try-with-resources`, introducida en Java 7, garantiza que los recursos se cierren correctamente después de su uso. Los recursos utilizados en una declaración `try-with-resources` deben implementar la interfaz `AutoCloseable` o `Closeable`.


## Preguntas de práctica
**1. ¿Cuál de las siguientes afirmaciones describe correctamente una excepción marcada en Java?**

**A.** Una excepción marcada es un tipo de excepción que hereda de la clase `java.lang.RuntimeException`.  
**B.** Una excepción marcada debe detectarse o declararse en la firma del método utilizando la palabra clave `throws`.  
**C.** Una excepción marcada es un error que generalmente es causado por el entorno en el que se ejecuta la aplicación y que la aplicación no puede manejar.  
**D.** La máquina virtual Java puede generar una excepción marcada cuando se produce un error grave, como un error de falta de memoria.


**2. ¿Cuál de los siguientes fragmentos de código define y genera correctamente una excepción marcada personalizada?**```java
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}

public class TestCustomException {
    public static void main(String[] args) {
        try {
            methodThatThrowsException();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void methodThatThrowsException() throws CustomException {
        throw new CustomException("This is a custom checked exception");
    }
}
```**A.** Este código define una excepción marcada personalizada y la genera y maneja correctamente.  
**B.** Este código define una excepción personalizada no marcada.  
**C.** Este código no se compilará porque la excepción personalizada no está declarada correctamente en la firma del método.  
**D.** Este código se compilará pero no generará la excepción personalizada en tiempo de ejecución.


**3. Dada la siguiente clase, ¿cuál es el resultado?**```java
public class Main {
    protected static int myMethod() {
        try {
            throw new RuntimeException();
        } catch(RuntimeException e) {
             return 1;
        } finally {
             return 2;
        }
    }
    public static void main(String[] args) {
        System.out.println(myMethod());
    }
}
```**A.** `1`  
**B.** `2`  
**C.** La compilación falla  
**D.** Se produce una excepción en tiempo de ejecución


**4. Dada la siguiente clase, ¿cuál de las siguientes afirmaciones es verdadera?**```java
public class Main {
    public static void main(String[] args) {
        try {
            // Do nothing
        } finally {
            // Do nothing
        }
    }
}
```**A.** El código no se compila correctamente.  
**B.** El código se compilaría correctamente si agregamos un bloque `catch`.  
**C.** El código se compilaría correctamente si eliminamos el bloque "finalmente".  
**D.** El código se compila correctamente tal como está.

**5. ¿Cuáles de las siguientes afirmaciones son verdaderas? (Elija todas las que correspondan)**

**A.** En un `try-with-resources`, se requiere el bloque `catch`.  
**B.** La palabra clave `throws` se utiliza para generar una excepción.  
**C.** En un bloque `try-with-resources`, si declaras más de un recurso, deben estar separados por un punto y coma.  
**D.** Si se define un bloque `catch` para una excepción que el código del bloque `try` no pudo generar, se genera un error en tiempo de compilación.

**6. Dada la siguiente clase, ¿cuál es el resultado?:**```java
class Connection implements java.io.Closeable {
    public void close() throws IOException {
        throw new IOException("Close Exception");
    }
}

public class Main {
    public static void main(String[] args) {
        try (Connection c = new Connection()) {
            throw new RuntimeException("RuntimeException");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
```**A.** `Cerrar excepción`  
**B.** `Excepción de tiempo de ejecución`  
**C.** `RuntimeException` y luego `CloseException`  
**D.** La compilación falla  
**E.** Se imprime el seguimiento de la pila de una excepción no detectada


**7. ¿Cuáles de las siguientes excepciones son subclases directas de `RuntimeException`?**

**A.** `java.io.FileNotFoundException`  
**B.** `java.lang.ArithmeticException`  
**C.** `java.lang.ClassCastException`  
**D.** `java.lang.InterruptedException`


**8. Dado el siguiente código, ¿cuál es el resultado?**```java
class MyResource implements AutoCloseable {
    public void close() {
        throw new RuntimeException("Close Exception");
    }
}

public class Main {
    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            throw new RuntimeException("Try Block Exception");
        } catch (RuntimeException e) {
            Throwable[] suppressed = e.getSuppressed();
            if (suppressed.length > 0) {
                for (Throwable t : suppressed) {
                    System.out.println("Suppressed: " + t.getMessage());
                }
            } else {
                System.out.println(e.getMessage());
            }
        }
    }
}
```**A.** Sólo se imprime `"Probar excepción de bloqueo"`.  
**B.** Sólo se imprime `"Cerrar excepción"`.  
**C.** Se imprimen tanto `"Probar excepción de bloqueo"` como `"Cerrar excepción"`.  
**D.** Se imprime `"Suprimido: Cerrar excepción"`.