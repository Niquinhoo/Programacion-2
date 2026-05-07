---

layout: chapter

title: "Capítulo CINCO"
subtitle: "Controlar el flujo del programa"
exam_objectives:
  - "Cree construcciones de control de flujo de programas que incluyan if/else, declaraciones y expresiones de cambio, bucles y declaraciones de interrupción y continuación."
  - "Implemente la herencia, incluidos tipos abstractos y sellados, así como clases de registros. Anular métodos, incluido el de la clase Objeto. Implementar polimorfismo y diferenciar entre tipo de objeto y tipo de referencia. Realice la conversión de tipos de referencia, identifique tipos de objetos utilizando el operador instancia de y la coincidencia de patrones con el operador instancia de y la construcción switch."

previous_link: "/ch04.html"
previous_title: "Trabajar con datos"
next_link: "/ch06.html"
next_title: "Matrices, genéricos y colecciones"
answers_link: "/ch05a.html"
---
## Contenido del capítulo

- [La declaración `if`](#la-declaración-if)
    - [Coincidencia de patrones en declaraciones `if`](#coincidencia-de-patrones-en-declaraciones-if)
    - [Alcance del flujo](#flujo-alcance)
- [La declaración `switch`] (#la-declaración-switch)
    - [Tipos en declaraciones `case`](#tipos-en-declaraciones-case)
    - [Valores en declaraciones `case`](#valores-en-declaraciones-case)
    - [La expresión `cambiar`](#la-expresión-cambiar)
    - [Coincidencia de patrones en declaraciones `switch`] (# coincidencia-de-patrones-en-declaraciones-switch)
- [El bucle `` while`] (#el-bucle- while )
    - [Bucles anidados](#bucles-nested)
    - [Las declaraciones `break` y `continue`](#las-declaraciones-break-and-continue)
    - [Agregar etiquetas](#adding-labels)
- [El bucle `for`](#el-bucle-for)
    - [El bucle tradicional `for`](#el-bucle-for-tradicional)
    - [El bucle `para-cada`](#el-bucle-para-cada-)
    - [Bucles `for` anidados](#bucles-for-anidados)
    - [Las declaraciones `break` y `continue`](#the-break-and-continue-statements-1)
    - [Agregar etiquetas](#adding-labels-1)
- [Puntos clave](#puntos clave)
- [Preguntas de práctica](#preguntas-de-practica)

---

## La declaración "si"
Una de las declaraciones de flujo de control más fundamentales en Java y muchos otros lenguajes de programación es la declaración "if". Permite que su programa tome decisiones y ejecute diferentes rutas de código en función de si se cumplen ciertas condiciones.  

En esencia, el propósito de una declaración "if" es ejecutar condicionalmente un bloque de código. Si la condición especificada se evalúa como "verdadera", el bloque de código se ejecutará. De lo contrario, ese bloque se omite y el programa continúa con la siguiente instrucción después del bloque if.

Aquí está el diagrama de flujo para la declaración "if":```
          ┌─────────┐
          │  Start  │
          └────┬────┘
               │
         ┌─────┴─────┐
         │ Condition │
         └─────┬─────┘
               │
        ┌──────┴──────┐
   ┌────┤  Is true?   ├────┐
   │    └─────────────┘    │
   │                       │
┌──┴──┐                 ┌──┴──┐
│ Yes │                 │ No  │
│     │                 │     │
│─────┴──────────┐   ┌──┴─────┴─────┐
│    Execute     │   │  Execute     │
│    if block    │   │  else block  │
└─────────┬──────┘   └──────┬───────┘
          │                 │
          └────────┬────────┘
                   │
             ┌─────┴─────┐
             │   End     │
             └───────────┘
```La sintaxis básica de una declaración "if" se ve así:```java
if (condition) {
    // Code to execute if condition is true
}
```La condición va entre paréntesis y debe evaluarse como un valor booleano, ya sea "verdadero" o "falso". El código para ejecutar condicionalmente va entre llaves. Si el bloque de código contiene solo una declaración, puede omitir las llaves:```java
if (x > 10) 
    System.out.println("x is greater than 10");
```Sin embargo, el uso de llaves se considera una buena práctica incluso para declaraciones individuales, ya que hace que su código sea más claro y menos propenso a errores si luego agrega más declaraciones al bloque.

Puedes encadenar múltiples condiciones usando la construcción "else if":```java
if (condition1) {
    // Code to execute if condition1 is true
} else if (condition2) {  
    // Code to execute if condition1 is false and condition2 is true
} else {
    // Code to execute if both condition1 and condition2 are false
}
```Aquí, cada condición "si no" solo se verificará si todas las condiciones "si"/"si no" anteriores se evaluaron como "falsas". Tan pronto como se determina que una condición es "verdadera", se ejecuta su bloque correspondiente y se omite el resto de la cadena "if"/"else if"/"else". El bloque final "else" se ejecuta si ninguna de las condiciones era "verdadera".

No hay un límite estricto para la cantidad de declaraciones "else if" que puede tener, pero si se encuentra con cadenas "if"/"else if" muy largas, es posible que desee considerar refactorizar a un enfoque más limpio, como una declaración "switch" o un polimorfismo.

Un punto común de confusión es intentar acceder a las variables declaradas dentro de un bloque `if` desde el bloque `else` correspondiente:```java
if (condition) {
    int x = 10;
} else {
    System.out.println(x); // Compile error - x is not in scope! 
}
```Esto falla porque las variables declaradas dentro de un bloque `if` o `else` solo están dentro del alcance dentro de ese bloque. Para usar una variable en las secciones `if` y `else`, debes declararla fuera (antes) de la declaración `if`.

### Coincidencia de patrones en declaraciones `if`
Java ha ido ampliando sus capacidades de coincidencia de patrones, facilitando el trabajo con estructuras de datos complejas. Exploremos cómo funciona la coincidencia de patrones con declaraciones "if".

#### Patrones de tipo
Los patrones de tipo le permiten probar si un objeto es una instancia de un tipo particular y, de ser así, crear una variable de ese tipo en un solo paso:```java
if (obj instanceof String s) {
    System.out.println(s.toUpperCase());
}
```Aquí, se prueba "obj" para ver si es una instancia de "String". Si es así, se convierte en `String` y se asigna a la variable de patrón `s`, que luego puede usarse en el bloque `if`.

Existen algunas reglas al usar patrones de tipo en declaraciones "if":
- El tipo de variable de patrón debe ser un subtipo de la variable en el lado izquierdo de `instanceof`.
- La variable de patrón sólo es utilizable cuando el compilador puede decir definitivamente su tipo, si hay ambigüedad, no se puede considerar inicializada.
- La coincidencia de patrones puede utilizar cualquier expresión válida, incluso llamadas a métodos, no solo comprobaciones de variables simples.

Por ejemplo:```java
if (getObject() instanceof String s) {
    System.out.println(s); // s in scope here
} else {
    System.out.println(s); //Compile error! s is definitely not a String
}

// ...

Object getObject() {
    return "hi";
}
```#### Patrones de registro
Java 21 introdujo patrones de registro, que le permiten desestructurar instancias de registro directamente en la declaración if. Esto proporciona una forma más declarativa y componible de trabajar con datos. He aquí un ejemplo:```java
record Book(String title, String author) {}

static void printDetails(Object obj) {
    if (obj instanceof Book(String title, String author)) {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
    }
}
```En este código, "Libro (título de cadena, autor de cadena)" es un patrón de registro. No solo verifica si `obj` es una instancia de `Book`, sino que también extrae los componentes `title` y `author` directamente en variables de patrón. Esto elimina la necesidad de llamadas a métodos de acceso separados.

Los patrones de registro también se pueden anidar, lo que le permite desestructurar gráficos de objetos complejos en un solo paso:```java
record Book(String title, String author) {}
record Library(String name, Book bestSeller) {}

Library myLibrary = new Library("City Library", new Book("Java Programming", "John Doe"));

if (myLibrary instanceof Library(var name, Book(var title, var author))) {
    System.out.println("Best seller at " + name + " is '" + title + "' by " + author);
}
```En este ejemplo, estamos usando un patrón de registro anidado para compararlo con el registro "Biblioteca" y su componente "Libro" simultáneamente. Si el patrón coincide, obtenemos acceso directo a los componentes "nombre", "título" y "autor" sin necesidad de utilizar métodos de acceso.

Los patrones de registro también funcionan con registros genéricos. El compilador inferirá los argumentos de tipo cuando sea posible:```java
record Box<T>(T t) {}

public class GenericRecord {
    static void unbox(Box<Box<Integer>> box) {
        if (box instanceof Box(Box(var u))) {
            System.out.println("Unboxed Integer: " + u);
        }
    }

    public static void main(String[] args) {
        unbox(new Box<>(new Box<>(8))); // Prints Unboxed Integer: 8
    }
}
```Aquí, el compilador infiere que "u" es un "entero", según el tipo de "caja".

Es importante tener en cuenta que los patrones de registro no coinciden con "nulo". Si necesita manejar posibles valores "nulos", debe hacerlo antes de la coincidencia del patrón:```java
if (obj != null && obj instanceof Book(String title, String author)) {
    System.out.println("Title: " + title);
    System.out.println("Author: " + author);
}
```En resumen, he aquí algunos puntos clave sobre los patrones de registro:

1. Consisten en un tipo de clase de registro seguido de una lista de patrones entre paréntesis para cada componente.
2. Se pueden anidar, lo que le permite desestructurar jerarquías de registros complejas en un solo patrón.
3. La palabra clave `var` se puede utilizar para inferir el tipo de un componente.
4. Los valores "nulo" no coinciden con ningún patrón de registro.
5. Para clases de registros genéricos, los argumentos de tipo se infieren si no se proporcionan explícitamente.

### Alcance del flujo
Un concepto importante que se debe comprender al utilizar la coincidencia de patrones en declaraciones "si" es el alcance del flujo. Esto se refiere a cómo el compilador razona sobre el alcance y la disponibilidad de las variables de patrón en función del flujo de control a través de su código.

Considere este ejemplo:```java
if (obj instanceof String s) {
    System.out.println(s); // s is definitely a String here
} else {
    System.out.println(s); // Compiler error: s might not be initialized
}
System.out.println(s); // Compiler error: s is not in scope here
```Dentro del bloque `if`, s es definitivamente una `Cadena`, el compilador lo sabe porque la verificación `instanceof` debe haber tenido éxito para que se ejecute ese bloque. Por lo tanto, es seguro utilizar `s` como `Cadena` dentro de este ámbito.

Sin embargo, en el bloque "else" correspondiente, "s" no se considera inicializado. El compilador no asume lo contrario de la condición "if", razona que si el bloque "else" se está ejecutando, la verificación "instanceof" debe haber fallado, por lo que a "s" nunca se le asignó un valor. Intentar usar `s` aquí resulta en un error de compilación.

Fuera de la declaración `if-else` por completo, `s` no está dentro del alcance en absoluto. Las variables de patrón solo son accesibles dentro del bloque `if` donde se declaran, y en los bloques posteriores `else if` o `else` si el compilador puede demostrar que fueron asignadas definitivamente.

El alcance del flujo se vuelve más complejo cuando hay múltiples variables de patrón en juego:```java
if (obj instanceof String s || obj instanceof Integer i) {
    // s or i is in scope, but not both 
} else {
    // neither s nor i are in scope
}
```En este caso, dentro del bloque `if`, solo uno de `s` o `i` estará dentro del alcance, dependiendo de qué instancia de verificación tuvo éxito. El compilador no le permite usar una variable de patrón a menos que pueda decir definitivamente que fue asignada.

Si necesita utilizar una variable de patrón en varios ámbitos, debe asignarla por separado:```java
String s = null;
if (obj instanceof String temp) {
    s = temp;
}
// s is now in scope, but may be null if the if block didn't execute
```Esto puede parecer una limitación, pero en realidad es una poderosa característica de seguridad. Al controlar estrictamente el alcance de las variables de patrón, Java ayuda a prevenir errores comunes y hace que su código sea más sólido.

Vale la pena señalar que el alcance del flujo solo se aplica a las variables de patrón declaradas, no a las variables originales. En el ejemplo anterior, "obj" permanece dentro del alcance en todo momento, porque se declaró antes de la declaración "if".


## La declaración de "cambio"
A veces, es necesario verificar el valor de una variable o expresión y ejecutar código diferente dependiendo de cuál sea ese valor. Si sólo hay un par de opciones, una declaración "if-else" funciona bien:```java
String animal = "cat";
if(animal.equals("dog")) {
    System.out.println("Woof!");
} else {
    System.out.println("Meow!");
}
```Pero ¿qué pasa si hay muchos valores posibles para verificar? Podrías encadenar un montón de declaraciones `if-else`:```java
String animal = "horse";
if(animal.equals("dog")) {
    System.out.println("Woof!"); 
} else if(animal.equals("cat")) {
    System.out.println("Meow!");
} else if(animal.equals("pig")) {
    System.out.println("Oink!");
} else if(animal.equals("horse")) {
    System.out.println("Neigh!");
} else {
    System.out.println("Unknown animal!");
}
```Sin embargo, esto puede volverse engorroso y complicado rápidamente. Ahí es donde entra en juego la declaración `switch`. Le permite definir bloques de código separados para diferentes valores de una variable o expresión. 

Aquí está el diagrama de la declaración "switch":```
┌─────────────────────────────────────┐
│          switch (variable)          │
│   ┌───────────────────────────────┐ │
│   │         case value1:          │ │
│   │           // code block       │ │
│   │           break;              │ │
│   ├───────────────────────────────┤ │
│   │         case value2:          │ │
│   │           // code block       │ │
│   │           break;              │ │
│   ├───────────────────────────────┤ │
│   │         case value3:          │ │
│   │           // code block       │ │
│   │           break;              │ │
│   ├───────────────────────────────┤ │
│   │         default:              │ │
│   │           // code block       │ │
│   └───────────────────────────────┘ │
└─────────────────────────────────────┘
```Y esta es su sintaxis básica:```java
switch(variable) {
    case value1:
        // code to run if variable == value1
        break;
    case value2: 
        // code to run if variable == value2
        break;
    default:
        // code to run if no case matches
}
```Entonces, el ejemplo de los animales podría reescribirse de manera más limpia como:```java
String animal = "horse";
switch(animal) {
    case "dog":
        System.out.println("Woof!");
        break;
    case "cat":
        System.out.println("Meow!");
        break; 
    case "pig":
        System.out.println("Oink!");
        break;
    case "horse":
        System.out.println("Neigh!");
        break;
    default:
        System.out.println("Unknown animal!");
}
```Cada "caso" define un valor con el que comparar la variable de cambio. Si hay una coincidencia, se ejecuta el código para ese caso. El "interrupción" hace que la ejecución salte al final del bloque "cambiar". Si ningún caso coincide, se ejecuta el bloque "predeterminado". 

Es importante incluir una declaración `break` (o `return`) para cada caso; de lo contrario, la ejecución *falla* en el siguiente caso, que rara vez es lo que desea. El caso predeterminado no necesita una "ruptura" explícita ya que es el último.

### Tipos en declaraciones `case`

No se puede utilizar cualquier tipo en un "interruptor". Históricamente, los conmutadores solo podían funcionar con estos tipos integrales y sus clases contenedoras:
-`int`/`Entero` 
-`byte`/`Byte`
- `corto`/`corto`
- `char`/`Carácter`

Luego, en versiones posteriores de Java, se agregaron como opción `String`, registros y las constantes de una `enum`.

Además, puede usar `var` en una declaración `switch` siempre que el tipo se resuelva en uno de los otros tipos permitidos:```java
var animal = "horse";
switch(animal) {
    case "dog":
        System.out.println("Woof!");
        break;
    case "cat":
        System.out.println("Meow!");
        break; 
    case "pig":
        System.out.println("Oink!");
        break;
    case "horse":
        System.out.println("Neigh!");
        break;
    default:
        System.out.println("Unknown animal!");
}
```En este caso, se infiere que "animal" es una "Cadena" según el valor que se le asigna. Dado que `String` es un tipo válido para un conmutador, usar `var` aquí está perfectamente bien.

Sin embargo, si intentas hacer algo como esto:```java
var data = 3.14;
switch(data) {
    // ...
}
```Obtendrá un error de compilación porque se infiere que "datos" es un "doble", que no es un tipo permitido para declaraciones de "cambio".

Acerca de las enumeraciones, consideremos una interfaz `Season` y una enumeración `Weather` que implementa esta interfaz:```java
sealed interface Season permits Weather {}
enum Weather implements Season { SPRING, SUMMER, FALL, WINTER }
```En versiones anteriores de Java, la instrucción switch requería que usaras solo los nombres simples de las constantes de enumeración:```java
void oldEnumSwitch(Weather w) {
    switch (w) {
        case SPRING -> {
            System.out.println("It's spring!");
        }
        case SUMMER -> {
            System.out.println("It's summer!");
        }
        case FALL -> {
            System.out.println("It's fall!");
        }
        case WINTER -> {
            System.out.println("It's winter!");
        }
    }
}
```Esta restricción funcionó bien para casos de uso básicos, pero se volvió engorrosa cuando se trataba de escenarios más complejos, como combinar tipos de enumeración o usar interfaces selladas.

En Java 21, ahora puede usar nombres completos de constantes de enumeración y mezclarlos con otras etiquetas de casos, lo que brinda mayor flexibilidad y permite expresiones de cambio más complejas:```java
void newEnumSwitch1(Season s) {
    switch (s) {
        case Weather.SPRING -> {  // Qualified name of enum constant
            System.out.println("It's spring!");
        }
        case Weather.SUMMER -> {
            System.out.println("It's summer!");
        }
        case Weather.FALL -> {
            System.out.println("It's fall!");
        }
        case Weather.WINTER -> {
            System.out.println("It's winter!");
        }
    }
}
```Además, el requisito de que la expresión del selector sea de tipo enum se relaja, lo que le permite usar nombres calificados de constantes de enum incluso si la expresión del selector no es del tipo enum, siempre que sea compatible con la asignación, como en el ejemplo anterior.

Sin embargo, un caso de uso no válido sería cuando la constante de enumeración no esté completamente calificada:```java
void invalidEnumSwitch(Season s) {
    switch (s) {
        case SPRING -> { // Error: SPRING must be qualified as Weather.SPRING
            System.out.println("It's spring!");
        }
        case Weather.SUMMER -> {
            System.out.println("It's summer!");
        }
        case Weather.FALL -> {
            System.out.println("It's fall!");
        }
        case Weather.WINTER -> {
            System.out.println("It's winter!");
        }
        default -> {
            System.out.println("Unknown season");
        }
    }
}
```### Valores en declaraciones `case`
Al definir los valores para cada "caso", hay algunas reglas importantes a tener en cuenta. El valor debe ser una constante en tiempo de compilación, lo que significa que debe conocerse en el momento en que se compila el código, no determinarse en tiempo de ejecución.

Por lo tanto, puede usar valores literales como `"perro"` o `3`, variables `final` (siempre que estén inicializadas con un valor constante) y constantes `enum`. Pero no puedes usar una variable normal o una llamada a un método, incluso si el método siempre devuelve el mismo valor. Por ejemplo:```java
final int NUMBER = 2;

int getSome() {
    return 1;
}

int x = 3;

switch(value) {
    case NUMBER: // OK, NUMBER is final and initialized with a constant
    case getSome(): // Error! Method calls aren't allowed
    case x: // Error! x is not final
    ...
}
```A veces, es posible que desees ejecutar el mismo código para múltiples valores de "caso". En lugar de duplicar el código, simplemente puede enumerar los valores juntos para un solo caso:```java
int dayNumber;
switch(dayName) {
    case "Monday":
        dayNumber = 1;
        break;
    case "Tuesday":
        dayNumber = 2;
        break;
    case "Saturday", "Sunday": // Runs the same code for "Saturday" and "Sunday"
        dayNumber = 0;
        break;
    default:
        throw new IllegalArgumentException("Invalid day: " + dayName);
}
```Mencioné esto antes, pero vale la pena reiterarlo, no olvide "salir" de cada bloque de caso (o usar "retorno"), a menos que desee específicamente que la ejecución pase al siguiente caso. Olvidar un "descanso" es una fuente común de errores en las declaraciones de cambio.

### La expresión `cambiar`
Java 14 introdujo oficialmente una nueva forma de "switch", conocida como expresión "switch". Tiene algunas diferencias clave con la tradicional declaración de "cambio". Primero, aquí está la sintaxis:```java
variable = switch(anotherVariable) {
    case value1 -> expression1;
    case value2 -> { statements; yield expression2; }
    default -> expression3;
};
```En lugar de `case:` y `break`, la expresión de cambio usa `->` para asignar cada caso a un valor o bloque de código. Si necesita varias declaraciones para un caso, utilice llaves y la palabra clave `yield` para especificar el valor a devolver.

Tenga en cuenta el punto y coma. Cada caso necesita uno al final, al igual que toda la expresión "switch".

La expresión "switch" siempre debe devolver un valor, y cada caso debe cubrir todas las posibilidades (ya sea explícitamente o con un valor "predeterminado"). Los tipos de datos de todos los resultados del "caso" también deben ser coherentes entre sí.

Aquí hay un ejemplo más concreto:```java
String animal = "horse";
String sound = switch(animal) {
    case "dog" -> "Woof!";
    case "cat" -> "Meow!";
    case "pig" -> "Oink!";
    case "horse" -> "Neigh!";
    case "human" -> {
        String greeting = "Hello!";
        yield greeting; // Use yield when there are multiple statements
    }
    default -> throw new IllegalArgumentException("Unknown animal: " + animal);
};
```En este caso, cada animal se asigna directamente al sonido que emite, excepto el "humano", que tiene un bloque de código. El caso "default" genera una excepción ya que la expresión "switch" debe cubrir todos los valores de entrada posibles.

### Coincidencia de patrones en declaraciones `switch`

Java 21 introdujo una característica nueva y poderosa: la coincidencia de patrones en declaraciones y expresiones de "cambio". Esto le permite probar la estructura de un objeto directamente en el "switch", haciendo que su código sea más expresivo y menos propenso a errores.

Comencemos con un ejemplo simple:```java
Object obj = "Hello, World!";
String result = switch (obj) {
    case Integer i -> "It's an integer: " + i;
    case String s -> "It's a string: " + s;
    case Double d -> "It's a double: " + d;
    default -> "It's something else";
};
System.out.println(result); // Outputs: It's a string: Hello, World!
```En este ejemplo, activamos un "Objeto" y en cada caso se verifica si el objeto es de un tipo específico. Si coincide, podemos usar la variable declarada en el patrón (como `s` para String) directamente en el cuerpo del caso.

Podemos agregar protectores a las etiquetas de nuestros estuches para una coincidencia aún más precisa:```java
Object obj = 42;
String category = switch (obj) {
    case Integer i when i < 0 -> "Negative integer";
    case Integer i when i > 0 -> "Positive integer";
    case Integer i -> "Zero";
    case String s when s.length() > 5 -> "Long string";
    case String s -> "Short string";
    default -> "Something else";
};
System.out.println(category); // Outputs: Positive integer
```La cláusula "cuándo" nos permite agregar condiciones adicionales a nuestra coincidencia de patrones.

Sin embargo, cuando se utiliza la coincidencia de patrones, el orden de los casos importa. Los patrones más específicos deberían preceder a los más generales:```java
Object obj = "Hello";
String result = switch (obj) {
    case String s when s.length() > 5 -> "Long string";
    case String s -> "Short string";
    case CharSequence cs -> "Some other CharSequence";
    default -> "Not a CharSequence";
};
System.out.println(result); // Outputs: Long string
```Si pusiéramos `case String s` antes de `case String s cuando s.length() > 5`, nunca se alcanzaría la protección y el compilador nos advertiría sobre un caso inalcanzable.

La coincidencia de patrones en Switch también introduce una forma más elegante de manejar valores "nulo":```java
String str = null;
String description = switch (str) {
    case null -> "It's null!";
    case String s -> "It's a string of length " + s.length();
};
System.out.println(description); // Outputs: It's null!
```En los conmutadores tradicionales, un valor "nulo" generaría una "NullPointerException". Con la coincidencia de patrones, podemos manejar explícitamente el caso "nulo".

Sin embargo, debe tener cuidado de no tener solo una etiqueta de caso que coincida con todos en un bloque "switch". Si, por ejemplo, agrega un caso "predeterminado" al ejemplo anterior:```java
String str = null;
String description = switch (str) {
    case null -> "It's null!";
    case String s -> "It's a string of length " + s.length();
    default -> "default";
};
System.out.println(description); // Outputs: It's null!
```Obtendrá un error de compilación: "el interruptor tiene un patrón incondicional y una etiqueta predeterminada".

Tener más de una etiqueta de caso que coincida con todos en una declaración o expresión "switch" genera un error en tiempo de compilación. Las etiquetas de los casos que coinciden con todos son:
- Una etiqueta de caso con un patrón que coincide incondicionalmente con la expresión del selector.
- La etiqueta de caso "predeterminada"

Sin embargo, lo siguiente compila:```java
Object obj = null; // Notice the Object type
String description = switch (obj) {
    case String s -> "It's a string of length " + s.length();
    case null, default  -> "It's null or not a string!";
};
System.out.println(description); // Outputs: It's null or not a string!
```Si una expresión de selector se evalúa como "nula" y el bloque de cambio no tiene la etiqueta de caso "nula", como en el siguiente caso:```java
Object obj = null;
String description = switch (obj) { // Throws NullPointerException
    case String s -> "It's a string of length " + s.length();
    default  -> "It's null or not a string";
};
System.out.println(description); 
```Luego se lanza una `NullPointerException`. 

Otro beneficio clave de la coincidencia de patrones en "switch" es la verificación exhaustiva. El compilador garantiza que se cubran todos los casos posibles:```java
sealed interface Shape permits Circle, Rectangle, Triangle {}
record Circle(double radius) implements Shape {}
record Rectangle(double width, double height) implements Shape {}
record Triangle(double base, double height) implements Shape {}

public class SwitchExhaustiveness {
    public static void main(String[] args) {
        Shape shape = new Circle(5);
        double area = switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
            case Triangle t -> 0.5 * t.base() * t.height();
        };
        System.out.println("Area: " + area);
    }
}
```En este ejemplo, debido a que `Shape` es una interfaz sellada y hemos cubierto todas sus subclases permitidas, el compilador sabe que hemos cubierto exhaustivamente todas las posibilidades. Si la expresión es de tipo "sellado", solo las clases declaradas en la cláusula "permisos" del tipo "sellado" deben ser manejadas por el "interruptor".

Sin embargo, si no cubres todas las posibilidades:```java
double area = switch (shape) {
    case Circle c -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
};
```Se genera un error en tiempo de compilación: "la expresión de cambio no cubre todos los valores de entrada posibles".

El problema se puede solucionar simplemente agregando un caso "predeterminado":```java
double area = switch (shape) {
    case Circle c -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
    default -> 0;
};
```Finalmente, en una declaración de "cambio", el compilador también puede inferir los argumentos de tipo para un patrón de registro genérico. Por ejemplo, teniendo en cuenta la siguiente declaración récord:```java
record Point<T, U>(T x, U y) { }
```El compilador puede inferir `Point(var x, var y)` como `Point<Long, Long>(Long x, Long x)`:```java
Point<Long, Long> p = new Point(1L, 2L);

switch (p) {
    case Point(var x, var y) -> 
        System.out.println(x + ", " + y);
}
```## El bucle "mientras"
Un bucle " while " le permite ejecutar repetidamente un bloque de código siempre que una condición "booleana" especificada siga siendo "verdadera".

Aquí está el diagrama de flujo de la declaración " while ":```
          ┌─────────┐
          │  Start  │
          └────┬────┘
               │
         ┌─────┴─────┐
    ┌────┤ Condition │
    │    └─────┬─────┘
    │          │
    │    ┌─────┴─────┐
    │    │  Is true? ├───────┐
    │    └─────┬─────┘       │
    │          │             │
    │    ┌─────┴─────┐ ┌─────┴─────┐
    │    │    Yes    │ │    No     │
    │    └─────┬─────┘ └─────┬─────┘
    │          │             │
    │    ┌─────┴─────┐       │
    │    │  Execute  │       │
    │    │   Loop    │       │
    │    │   Body    │       │
    │    └─────┬─────┘       │
    │          │             │
    └──────────┘             │
                             │
                        ┌────┴────┐
                        │  End    │
                        └─────────┘
```En realidad, existen dos variantes del bucle " while " en Java:
1. El bucle estándar " while " 
2. El ciclo "hacer-mientras"

El bucle estándar " while " tiene la siguiente estructura:```java
while(condition) {
    // code block to be executed
}
```La condición es una expresión "booleana" que se evalúa antes de cada iteración del ciclo. Si la condición es "verdadera", se ejecuta el bloque de código. Este proceso se repite hasta que la condición se vuelve "falsa". 

Es importante tener en cuenta que si la condición es falsa cuando se alcanza el bucle por primera vez, el bloque de código no se ejecutará en absoluto. El bucle se omitirá por completo.

Aquí hay un ejemplo que imprime los números del 0 al 9:```java 
int count = 0;
while(count < 10) {
    System.out.println(count);
    count++;
}
```El bucle continuará ejecutándose hasta que el recuento ya no sea inferior a 10.

El bucle `do- while` es similar al bucle estándar `` while` pero con una diferencia clave: la condición se evalúa después de que se haya ejecutado el bloque de código. Esto significa que el bloque de código siempre se ejecutará al menos una vez, incluso si la condición es inicialmente falsa.

Aquí está la sintaxis de un bucle `do- while`:```java
do {
    // code block to be executed
} while(condition);
```Como puede ver, el bloque de código viene antes de la palabra clave y condición " while ". La condición se verifica después de cada iteración, determinando si el ciclo debe continuar o terminar.

El siguiente ejemplo es funcionalmente equivalente al ejemplo anterior del bucle " while ":```java
int count = 0; 
do {
    System.out.println(count);
    count++;
} while(count < 10);
```Aunque la estructura es diferente, este bucle `do- while` logra el mismo resultado que el bucle `` while` estándar, imprimiendo los números del 0 al 9.

Entonces, ¿por qué elegirías un bucle `do- while` en lugar de un bucle `` while` estándar? Realmente depende del problema específico que estés intentando resolver. Si sabe que siempre desea que el bloque de código se ejecute al menos una vez, independientemente del estado de condición inicial, un "hacer mientras" puede ser una buena opción y puede aclarar su intención. Sin embargo, en muchos casos, un bucle " while " estándar es suficiente y se utiliza con mayor frecuencia.

### Bucles anidados
Es posible colocar un bucle dentro del cuerpo de otro bucle. Esto se conoce como anidamiento en bucle. Los bucles anidados le permiten iterar sobre múltiples dimensiones, como las filas y columnas de una matriz 2D.

A continuación se muestra un ejemplo que utiliza bucles `` while '' anidados para imprimir una tabla de multiplicar:```java
int i = 1;
while(i <= 10) {
    int j = 1;
    while(j <= 10) {
        System.out.print(i * j + "\t");
        j++;
    }
    System.out.println();
    i++;
}
```El bucle exterior itera del 1 al 10, representando las filas de la tabla de multiplicar. Para cada iteración del bucle exterior, el bucle interior también itera del 1 al 10, lo que representa las columnas. Se imprime el producto de los valores de fila y columna actuales, seguido de un carácter de tabulación (`\t`) para formatear. Una vez completada cada fila, se imprime una nueva línea para pasar a la siguiente fila.

Si bien este ejemplo utiliza bucles `` while`, también puedes anidar bucles `do- while` de manera similar. La elección del tipo de bucle depende de los requisitos específicos de su caso de uso.

### Las declaraciones `break` y `continue` 
La declaración "break" se utiliza para terminar inmediatamente un bucle o una declaración de cambio. Cuando se encuentra dentro de un bucle, "break" hace que el control del programa se transfiera a la siguiente declaración después del bucle.

A continuación se muestra un ejemplo del uso de "break" en un bucle " while ":```java
int count = 0;
while(true) {
    System.out.println(count);
    count++;
    if(count >= 5) {
        break;
    }
}
```Este ciclo continuará infinitamente porque la condición siempre es verdadera. Sin embargo, la instrucción "break" dentro del bucle hará que finalice una vez que el recuento llegue a 5.

Por otro lado, la instrucción "continuar" se utiliza para omitir el resto de la iteración del bucle actual y pasar inmediatamente a la siguiente iteración. 

Aquí hay un ejemplo que usa "continuar":```java
int i = 0;
while(i < 10) {
    if(i % 2 == 0) {
        i++;
        continue;
    }
    System.out.println(i);
    i++;
}
```Este bucle se repite de 0 a 9. Sin embargo, cuando `i` es par (divisible por 2), se ejecuta la instrucción `continue`, lo que provoca que se omita el resto de la iteración. Como resultado, sólo se imprimen los números impares.

Sin embargo, es importante tener en cuenta que el uso de "interrumpir" o "continuar" a veces puede generar código inalcanzable, lo que provocará un error de compilación.

Considere este ejemplo:```java
while(condition) {
    // code block
    break;
    // more code
}
```El código después de la declaración "break" nunca se ejecutará porque "break" siempre hace que el ciclo termine. El compilador de Java detectará esto y generará un error de compilación de "código inalcanzable".

Lo mismo se aplica a "continuar". Cualquier código colocado después de una declaración "continuar" en la misma iteración del bucle será inalcanzable.

Para evitar estos errores, asegúrese de que cualquier código colocado después de una "interrupción" o "continuar" tenga la posibilidad de ejecutarse bajo alguna condición.

### Agregar etiquetas
Finalmente, puedes asociar una etiqueta con un bucle. Las etiquetas proporcionan una forma de salir o continuar con un bucle externo específico desde dentro de un bucle anidado. Aquí está la sintaxis para agregar una etiqueta a un bucle:```java
label: 
while(condition) {
    // code block
}
```La etiqueta es un identificador seguido de dos puntos. Se coloca justo antes de la declaración del bucle.

A continuación se muestra un ejemplo que demuestra el uso de etiquetas:```java
int i = 0;
outerLoop:
while(i < 10) {
    int j = 0;
    while(j < 10) {
        if(j == 5) {
            break outerLoop;
        }
        System.out.println("i: " + i + ", j: " + j);
        j++;
    }
    i++;
}
```En este caso, el bucle exterior se denomina "outerLoop". Dentro del bucle anidado, hay una condición que comprueba si `j` es igual a 5. Cuando se cumple esta condición, la instrucción `break` se utiliza con la etiqueta `outerLoop`, lo que hace que la ejecución salte tanto del bucle interno como del externo. Sin la etiqueta, la ruptura solo saldría del bucle interior.

Al igual que "romper", "continuar" también se puede usar con una etiqueta para pasar a la siguiente iteración de un bucle externo.

Aquí hay un ejemplo que demuestra esto:```java
int i = 0;
outerLoop:
while(i < 3) {
    int j = 0;
    while(j < 3) {
        if(i == 1 && j == 1) {
            i++;
            continue outerLoop;
        }
        System.out.println("i: " + i + ", j: " + j);
        j++;
    }
    i++;
}
```En este ejemplo, el bucle exterior tiene la etiqueta "outerLoop". El bucle externo itera sobre los valores de "i" de 0 a 2, y el bucle interno itera sobre los valores de "j" de 0 a 2.

Dentro de los bucles anidados, hay una condición que verifica si tanto `i` como `j` son iguales a 1. Cuando se cumple esta condición, la instrucción `continue` se usa con la etiqueta `outerLoop`. Esto hace que el control del programa salte inmediatamente a la siguiente iteración del bucle exterior, omitiendo el resto del bucle interior.

Como resultado, la salida de este código será:```
i: 0, j: 0
i: 0, j: 1
i: 0, j: 2
i: 1, j: 0
i: 2, j: 0
i: 2, j: 1
i: 2, j: 2
```Observe que falta la salida `i: 1, j: 1` porque cuando `i` y `j` son ambos 1, se ejecuta la instrucción `continue outsideLoop`, lo que hace que el programa salte a la siguiente iteración del bucle externo, omitiendo la declaración de impresión.

Usar "continuar" con una etiqueta es menos común que usar "romper" con una etiqueta, pero puede ser útil en situaciones en las que desea omitir múltiples niveles de bucles anidados según una determinada condición.



## El bucle `for`
Al igual que los bucles " while ", los bucles "for" se utilizan para ejecutar repetidamente un bloque de código. Sin embargo, los bucles "for" proporcionan una sintaxis más concisa para iterar sobre un rango de valores o elementos de una colección.

En Java, hay dos tipos de bucles "for":
- El tradicional bucle "for"
- El bucle `for-each` (también conocido como bucle `for` mejorado)

Aquí hay un diagrama con los puntos clave de los bucles "for":```
┌─────────────────────────────────────────────────────────────┐
│                     Java for Loops                          │
│                                                             │
│  Traditional for Loop        │     for-each Loop            │
│                              │                              │
│  for (int i = 0; i < 5; i++) │  for (int num : numbers) {   │
│  {                           │      // code block           │
│      // code block           │  }                           │
│  }                           │                              │
│                              │                              │
│  Components:                 │  Components:                 │
│  1. Initialization           │  1. Element variable         │
│  2. Condition                │  2. Collection to iterate    │
│  3. Update statement         │                              │
│                              │                              │
│  Use when:                   │  Use when:                   │
│  - Need index                │  - Don't need index          │
│  - Custom increments         │  - Iterating full collection │
│  - Multiple counters         │  - Simpler syntax preferred  │
└─────────────────────────────────────────────────────────────┘
```Comencemos examinando con más detalle el bucle "for" tradicional.

### El bucle tradicional `for`
El bucle "for" tradicional tiene la siguiente estructura:```java
for(initialization; booleanExpression; updateStatement) {
    // code block to be executed
}
```El bucle consta de tres partes separadas por punto y coma:
1. **Inicialización:** Aquí es donde inicializas las variables del bucle. Se ejecuta sólo una vez al comienzo del ciclo.

2. **Expresión booleana:** Esta es la condición que se verifica antes de cada iteración. Si se evalúa como "verdadero", el ciclo continúa. Si es "falso", el ciclo termina.

3. **Declaración de actualización:** Aquí es donde se especifica cómo se deben actualizar las variables del bucle después de cada iteración. Se ejecuta al final de cada iteración.

Aquí hay un ejemplo simple que imprime los números del 0 al 4:```java
for(int i = 0; i < 5; i++) {
    System.out.println(i);
}
```El bucle inicializa `i` a 0, verifica si `i` es menor que 5 y, de ser así, ejecuta el bloque de código (imprimiendo el valor de `i`). Después de cada iteración, "i" se incrementa en 1. El ciclo continúa hasta que "i" ya no sea menor que 5.

También puedes usar la palabra clave `var` en la parte de inicialización:```java
for(var i = 0; i < 5; i++) {
    System.out.println(i);
}
```Si omites la expresión booleana, el valor predeterminado es "verdadero", creando un bucle infinito:```java
for(int i = 0; ; i++) {
    System.out.println(i);
}
```Este bucle continuará indefinidamente porque no existe ninguna condición que lo haga "falso". Para detener un bucle infinito, necesitaría utilizar una declaración "break" o algún otro medio para interrumpir el bucle.

Puede inicializar múltiples variables e incluir múltiples declaraciones de actualización en un bucle "for" separándolas con comas:```java
for(int i = 0, j = 10; i < j; i++, j--) {
    System.out.println("i: " + i + ", j: " + j);
}
```Este bucle inicializa `i` a 0 y `j` a 10, verifica si `i` es menor que `j` y, de ser así, ejecuta el bloque de código. Después de cada iteración, "i" se incrementa y "j" se reduce.

Es importante tener en cuenta que no puedes redeclarar una variable en el bloque de inicialización de un bucle `for`:```java
int i = 0;
for(int i = 0; i < 5; i++) { // Doesn't compile
    System.out.println(i);
}
```Este código no se compilará porque "i" se declara dos veces. Si necesita utilizar una variable que ya está declarada, simplemente omita el tipo de datos en el bloque de inicialización:```java
int i = 0;
for(i = 0; i < 5; i++) { // OK
    System.out.println(i);
}
```Además, todas las variables declaradas en el bloque de inicialización deben ser del mismo tipo de datos o tipos compatibles:```java
for(int i = 0, long j = 10; i < j; i++, j--) { // Doesn't compile
    System.out.println("i: " + i + ", j: " + j);
}
```Este código no se compilará porque `i` es de tipo `int` y `j` es de tipo `long`. Aquí está el ejemplo corregido:```java
for(int i = 0, j = 10; i < j; i++, j--) {
    System.out.println("i: " + i + ", j: " + j);
}
```Alternativamente, si necesita utilizar diferentes tipos de datos, debe declararlos antes del ciclo:```java
int i = 0;
long j = 10;
for(; i < j; i++, j--) {
    System.out.println("i: " + i + ", j: " + j);
}
```En cuanto al alcance de una variable declarada en el bloque de inicialización, se limita al bucle `for`. No puedes usarlo fuera del bucle:```java
for(int i = 0; i < 5; i++) {
    System.out.println(i);
}
System.out.println(i); // Doesn't compile
```Una vez más, si necesita utilizar el valor final de la variable del bucle después del bucle, debe declararlo antes del bucle:```java
int i;
for(i = 0; i < 5; i++) {
    System.out.println(i);
}
System.out.println(i); // OK, prints 5
```En muchos casos, es posible que necesite comparar la variable del bucle actual con otros elementos del bucle. El bucle tradicional `for` hace esto posible al permitirle leer elementos hacia adelante o hacia atrás:```java
int[] arr = {1,2,3,4,5};
for(int i = 0; i < arr.length; i++) {
    // Read forward
    if(i < arr.length - 1) {
        System.out.println("Current: " + arr[i] + ", Next: " + arr[i+1]);
    }
 
    // Read backward
    if(i > 0) {
        System.out.println("Current: " + arr[i] + ", Previous: " + arr[i-1]);
    }
}
```La condición de lectura directa if verifica si el elemento actual no es el último y, de ser así, imprime el elemento actual y el siguiente.

La condición de lectura inversa if verifica si el elemento actual no es el primero y, de ser así, imprime el elemento actual y el anterior.


### El bucle "para cada uno"
El bucle "for-each", también conocido como bucle "for" mejorado, proporciona una forma más sencilla de iterar sobre matrices y colecciones. Elimina la necesidad de declarar y actualizar explícitamente las variables del bucle.

La estructura de un bucle "para cada" es la siguiente:```java
for(dataType item : collection) {
    // code block to be executed
}
```El bucle consta de dos partes con tres elementos:
1. **dataType:** El tipo de datos de los elementos de la colección.

2. **elemento:** Una variable que contendrá el elemento actual durante cada iteración.

3. **colección:** La matriz o colección sobre la que se va a iterar.

Aquí hay un ejemplo que imprime los elementos de una matriz usando un bucle "for-each":```java
int[] numbers = {1, 2, 3, 4, 5};
for(int num : numbers) {
    System.out.println(num);
}
```En cada iteración, el bucle asigna el siguiente elemento de la matriz de números a la variable `num` y ejecuta el bloque de código.

El bucle `for-each` se puede usar con matrices y con cualquier objeto que implemente la interfaz `Iterable`, que incluye la mayoría de las clases de colección, como `ArrayList` y `HashSet`.

Si se pregunta si todo lo que se aplica a los bucles "for" se aplica a los bucles "for-each", la respuesta no es del todo. Si bien los bucles "for" y "for-each" comparten algunas similitudes, existen algunas diferencias clave en cómo se comportan y qué pueden hacer:

1. **Iteración**: un bucle `for-each` itera automáticamente sobre todos los elementos de una matriz o colección, desde el primero hasta el último. No tienes control sobre el índice ni el orden de iteración. Por otro lado, un bucle "for" tradicional le brinda control total sobre las declaraciones de inicialización, condición y actualización, lo que le permite iterar en cualquier orden u omitir elementos.

2. **Modificación**: Un bucle `for-each` no le impide modificar los elementos de la matriz o colección dentro del bucle, pero no proporciona acceso directo al índice. Puede modificar los elementos si la colección subyacente admite modificaciones. Por el contrario, un bucle "for" tradicional le permite modificar elementos accediendo a ellos a través de su índice.

3. **Iteración sobre matrices y colecciones**: se puede utilizar un bucle `for-each` para iterar sobre matrices y cualquier objeto que implemente la interfaz `Iterable`, que incluye la mayoría de las clases de colección. Se puede utilizar un bucle "for" tradicional para iterar sobre matrices y colecciones, pero es necesario utilizar un índice o iterador explícito.

4. **Acceso al índice**: en un bucle `for-each`, no tienes acceso directo al índice del elemento actual. Si necesita el índice, tendrá que usar un bucle "for" tradicional, que le da acceso al índice a través de la variable del bucle.

5. **Rendimiento**: Para las matrices, la diferencia de rendimiento entre un bucle "for-each" y un bucle for tradicional es generalmente insignificante. Para las colecciones, el rendimiento es similar al de un "bucle para cada" que es azúcar sintáctico para usar un iterador.

A continuación se muestra un ejemplo que demuestra una situación en la que no se puede utilizar un bucle "for-each":```java
int[] numbers = {1, 2, 3, 4, 5};
for(int i = 0; i < numbers.length; i++) {
    if(numbers[i] % 2 == 0) {
        numbers[i] *= 2; // Double even numbers
    }
}
```En este caso, necesitamos modificar los elementos de la matriz según una condición. También necesitamos acceso al índice para realizar la modificación. Esto no se puede hacer con un bucle "for-each".

Sin embargo, si sólo necesitáramos imprimir los números pares duplicados, un bucle "for-each" sería adecuado:```java
int[] numbers = {1, 2, 3, 4, 5};
for(int num : numbers) {
    if(num % 2 == 0) {
        System.out.println(num * 2); // Print doubled even numbers
    }
}
```En resumen, los bucles "for-each" proporcionan una sintaxis concisa para iterar sobre todos los elementos, mientras que los bucles for ofrecen más control y flexibilidad, permitiéndole acceder a índices e iterar de forma personalizada.

### Bucles for anidados
Al igual que los bucles " while ", los bucles "for" también se pueden anidar. Esto le permite iterar sobre matrices multidimensionales o realizar iteraciones complejas.```java
int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
for(int[] row : matrix) {
    for(int cell : row) {
        System.out.print(cell + " ");
    }
    System.out.println();
}
```Este código utiliza dos bucles "for-each" anidados para iterar a través de una matriz 2D. El bucle exterior itera sobre cada fila y el bucle interior itera sobre cada celda de la fila actual.

### Las declaraciones `break` y `continue`
La declaración "break" se puede utilizar en bucles "for" para finalizar el bucle prematuramente.```java
int[] numbers = {1, 2, 3, 4, 5};
for(int num : numbers) {
    if(num == 3) {
        break;
    }
    System.out.println(num);
}
```En este ejemplo, el bucle terminará cuando `num` sea igual a 3. El resultado será:```
1
2
```Por otro lado, la instrucción "continuar" se puede utilizar en bucles "for" para omitir el resto de la iteración actual y pasar a la siguiente.```java
int[] numbers = {1, 2, 3, 4, 5};
for(int num : numbers) {
    if(num % 2 == 0) {
        continue;
    }
    System.out.println(num);
}
```Este bucle imprimirá sólo los números impares de la matriz. Cuando `num` es par, se ejecuta la instrucción `continue` y se omite el resto de la iteración.

Además, el uso de "interrumpir" o "continuar" en bucles "for" a veces puede generar código inalcanzable, lo que genera errores de compilación.```java
for(int i = 0; i < 10; i++) {
    System.out.println(i);
    break;
    System.out.println("Unreachable"); // Unreachable code
}
```En este ejemplo, el código después de la declaración "break" es inalcanzable porque "break" siempre hace que el ciclo termine. El compilador de Java detectará esto y arrojará un error de compilación.

El mismo principio se aplica a "continuar". Cualquier código después de una declaración "continuar" en la misma iteración será inalcanzable.

Para evitar estos errores, asegúrese de que cualquier código colocado después de una declaración "break" o "continue" tenga la posibilidad de ejecutarse bajo alguna condición.

### Agregar etiquetas
Las etiquetas se pueden agregar a los bucles "for" de la misma manera que a los bucles " while ". Son útiles para romper o continuar con bucles externos desde dentro de bucles anidados:```java
int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
outerLoop:
for(int[] row : matrix) {
    for(int cell : row) {
        if(cell == 5) {
            break outerLoop;
        }
        System.out.print(cell + " ");
    }
    System.out.println();
}
```En este ejemplo, el bucle exterior está etiquetado como "outerLoop". Cuando el valor de `cell` es 5, la declaración `break` se usa con la etiqueta `outerLoop`, lo que hace que el programa termine tanto el bucle interno como el externo. Esto es lo que sucede:
1. El bucle exterior comienza con la primera fila `(1, 2, 3)` de `matriz`.
   - El bucle interior imprime "1", luego "2", luego "3".
   - Se completa el bucle interior y se imprime una nueva línea.

2. El bucle exterior pasa a la segunda fila `(4, 5, 6)`.
   - El bucle interior imprime "4".
   - El bucle interno encuentra 5 y se ejecuta `break externalLoop;`.
   - Se terminan tanto el bucle interior como el exterior.

El programa finaliza en este punto. La salida es:```
1 2 3
4
```La tercera fila `(7, 8, 9)` nunca se procesa porque los bucles terminaron antes de tiempo.


## Puntos clave
- La declaración `if` permite que su programa ejecute condicionalmente un bloque de código basado en una condición booleana.

- La sintaxis básica de una declaración `if` es: `if (condición) {código}`. El bloque de código se ejecuta si la condición es verdadera.

- Puedes encadenar múltiples condiciones usando "else if". Las condiciones se verifican en orden hasta que una sea verdadera o se alcance el bloque "else".

- Las variables declaradas dentro de un bloque `if` o `else` solo están dentro del alcance dentro de ese bloque.

- Las declaraciones `if` pueden usar la coincidencia de patrones con el operador `instanceof`, asignando el objeto coincidente a una variable de patrón para usar en el bloque `if`.

- Java 21 introdujo patrones de registro, lo que permite la desestructuración de instancias de registro directamente en declaraciones "if".

- Los patrones de registro se pueden anidar, lo que permite la desestructuración de gráficos de objetos complejos en un solo paso.

- La coincidencia de patrones no coincide con los valores "nulos".

- El compilador controla estrictamente el alcance de las variables de patrón en función de reglas de alcance de flujo para evitar errores.

- La sentencia `switch` permite ejecutar diferentes bloques de código basados ​​en el valor de una variable o expresión.

- Java 21 permite el uso de nombres completos de constantes de enumeración en declaraciones "switch".

- Las constantes de enumeración ahora se pueden mezclar con otras etiquetas de casos en el mismo "interruptor".

- Se flexibiliza el requisito de que la expresión del selector sea de tipo enum, lo que permite el uso de nombres calificados de constantes de enum incluso si el selector no es del tipo enum (pero es compatible con la asignación).

- Cada "caso" en un "interruptor" define un valor con el que comparar. Si hay una coincidencia, se ejecuta el bloque de código de ese caso. 

- Incluya una declaración de "interrupción" al final de cada bloque de caso para evitar fallas, a menos que se desee fallar.

- Las declaraciones `switch` pueden funcionar con `String`, constantes `enum` y tipos integrales como `int`, `char`, etc.

- Los valores de los casos deben ser constantes en tiempo de compilación.

- Java 14 introdujo oficialmente expresiones `switch`, que usan `->` para asignar casos a valores de resultados y deben cubrir todas las posibilidades de entrada.

- Java 21 introdujo la coincidencia de patrones en declaraciones y expresiones de "cambio". Esto permite probar la estructura de un objeto directamente en el "interruptor".

- Puede utilizar patrones de tipo, patrones de registro y agregar guardias con cláusulas "cuándo" para una coincidencia más precisa.

- El orden de los casos importa; Los patrones más específicos deberían preceder a los más generales.

- La coincidencia de patrones en `switch` introduce una forma de manejar valores `null` explícitamente.

- El compilador garantiza la exhaustividad en las declaraciones y expresiones de `switch`: el bloque `switch` debe tener cláusulas que traten con todos los valores posibles de la expresión del selector.

- El bucle `` while` ejecuta repetidamente un bloque de código siempre que su condición booleana siga siendo verdadera.

- Si la condición es inicialmente "falsa", el bloque de código no se ejecutará en absoluto.

- El bucle `do- while` es similar pero la condición se verifica después de cada iteración, por lo que el bloque de código siempre se ejecuta al menos una vez.

- Puedes anidar un bucle dentro de otro para iterar sobre múltiples dimensiones.

- La instrucción "break" termina inmediatamente un bucle, mientras que "continue" salta a la siguiente iteración.

- Puedes darle una etiqueta a un bucle y luego usar "interrumpir" o "continuar" con esa etiqueta para salir o continuar con un bucle externo etiquetado.

- El bucle `for` proporciona una sintaxis concisa para iterar sobre un rango de valores.

- El bucle tradicional `for` tiene una declaración de inicialización, condición y actualización. El bloque de código se ejecuta repetidamente hasta que la condición sea "falsa".

- Las variables declaradas en el bloque de inicialización tienen un alcance limitado al bucle `for`.

- El bucle `for-each` (bucle `for` mejorado) simplifica la iteración sobre matrices/colecciones, eliminando la necesidad de una indexación explícita.

- `for-each` no se puede usar si necesita el índice o desea iterar en un orden personalizado. Utilice un bucle "for" tradicional en esos casos.

- Puedes usar `break`/`continue` y etiquetas con bucles `for` al igual que con los bucles `` while`.

- Evite el código inalcanzable después de las declaraciones "break" o "continue".


## Preguntas de práctica

**1. ¿Cuál será el resultado del siguiente programa?**```java
public class IfStatementTest {
    public static void main(String[] args) {
        int x = 10;
        if (x > 5) {
            if (x < 20) {
                System.out.println("x is between 5 and 20");
            }
        } else {
            System.out.println("x is 5 or less");
        }
    }
}
```**A)** `x está entre 5 y 20`  
**B)** `x es 5 o menos`  
**C)** `x es mayor que 20`  
**D)** El programa no compila  
**E)** El programa se compila pero no produce ningún resultado.


**2. Dado el siguiente código:**```java
record Person(String name, int age) {}
record Employee(int id, Person person) {}

public class RecordPattern {
    public static void main(String[] args) {
        Employee emp = new Employee(1001, new Person("Alice", 30));
                                            
        // Insert code here
    }
}
```¿Cuál de las siguientes opciones utiliza correctamente la coincidencia de patrones de registro en una declaración "if" para extraer e imprimir el nombre y la edad de un registro "Persona" en Java 21?

**A)**```java
if (emp instanceof Employee) {
    var (id, Person(name, age)) = emp;
    System.out.println(name + " is " + age + " years old.");
}
```**B)**```java
if (emp instanceof Employee(_, Person(var name, var age))) {
    System.out.println(name + " is " + age + " years old.");
}
```**DO)**```java
if (emp instanceof Employee e) {
    System.out.println(e.person().name() + " is " + e.person().age() + " years old.");
}
```**D)**```java
if (emp instanceof Employee(var id, Person(var name, var age))) {
    System.out.println(name + " is " + age + " years old.");
}
```**MI)**```java
if (emp instanceof Employee(var id, var person)) {
    System.out.println(person.name() + " is " + person.age() + " years old.");
}
```**3. ¿Cuál de los siguientes fragmentos de código se compila sin errores?**```java
public class FlowScopingTest {
    public static void main(String[] args) {
        int x = 10;
        if (x > 5) {
            int y = x * 2;
        }
        // Code snippet 1
        System.out.println(y);

        if (x < 20) {
            int z = x + 5;
        }
        // Code snippet 2
        z += 5;

        int a = 5;
        if (a > 0) {
            a = 15;
        }
        // Code snippet 3
        System.out.println(a);

        if (x > 0) {
            int b = x + 3;
            if (b > 15) {
                b -= 2;
            }
        }
        // Code snippet 4
        System.out.println(b);
    }
}
```**A)** Fragmento de código 1  
**B)** Fragmento de código 2  
**C)** Fragmento de código 3  
**D)** Ninguno de los anteriores  


**4. ¿Cuál será el resultado del siguiente programa?**```java
public class SwitchTest {
    public static void main(String[] args) {
        int dayOfWeek = 3;
        String dayType;
        switch (dayOfWeek) {
            case 1:
            case 7:
                dayType = "Weekend";
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                dayType = "Weekday";
                break;
            default:
                dayType = "Invalid day";
        }
        System.out.println(dayType);
    }
}
```**A)** `Fin de semana`  
**B)** `Día no válido`  
**C)** `Día laborable`  
**D)** El programa no compila  
**E)** El programa se compila pero no produce ningún resultado.


**5. ¿Cuál será el resultado del siguiente programa?**```java
public class SwitchExpressionTest {
    public static void main(String[] args) {
        int score = 85;
        String grade = switch (score) {
            case 90, 100 -> "A";
            case 80, 89 -> "B";
            case 70, 79 -> "C";
            case 60, 69 -> "D";
            default -> "F";
        };
        System.out.println(grade);
    }
}
```**A)** `A`  
**B)** `F`  
**C)** El programa no compila  
**D)** `B`  
**E)** El programa se compila pero no produce ningún resultado.


**6. Dado el siguiente código:**```java
public class SwitchEnums {
    sealed interface Vehicle permits CarType {}
    enum CarType implements Vehicle { SEDAN, SUV, HATCHBACK, CONVERTIBLE }

    void processVehicle(Vehicle v) {
        switch(v) {
            // Insert case statements here
        }
    }
}
```¿Cuál de las siguientes declaraciones "case" son válidas en Java 21 cuando se insertan en la expresión "switch"?

**A)**```java
case CarType.SEDAN, CarType.HATCHBACK -> System.out.println("Compact vehicle");
case CarType.SUV -> System.out.println("Large vehicle");
case CarType.CONVERTIBLE -> System.out.println("Open-top vehicle");
```**B)**```java
case SEDAN, HATCHBACK -> System.out.println("Compact vehicle");
case SUV -> System.out.println("Large vehicle");
case CONVERTIBLE -> System.out.println("Open-top vehicle");
```**DO)**```java
case CarType.SEDAN || CarType.HATCHBACK -> System.out.println("Compact vehicle");
case CarType.SUV -> System.out.println("Large vehicle");
case CarType.CONVERTIBLE -> System.out.println("Open-top vehicle");
```**D)**```java
case Vehicle.SEDAN, Vehicle.HATCHBACK -> System.out.println("Compact vehicle");
case Vehicle.SUV -> System.out.println("Large vehicle");
case Vehicle.CONVERTIBLE -> System.out.println("Open-top vehicle");
```**7. Dado el siguiente código:**```java
sealed interface Shape permits Circle, Square, Triangle {}
record Circle(double radius) implements Shape {}
record Square(double side) implements Shape {}
record Triangle(double base, double height) implements Shape {}

Shape shape = new Circle(5);
double area = switch (shape) {
    // Insert case statements here
};
```¿Cuál de las siguientes declaraciones `case` implementa correctamente la coincidencia de patrones para la jerarquía `Shap`e cuando se inserta en la expresión `switch`?

**A)**```java
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case null -> 0;
```**B)**```java
default -> 0;
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case Triangle t -> 0.5 * t.base() * t.height();
```**DO)**```java
case Shape s when s instanceof Circle ->
        Math.PI * ((Circle)s).radius() * ((Circle)s).radius();
case Shape s when s instanceof Square ->
        ((Square)s).side() * ((Square)s).side();
case Shape s when s instanceof Triangle ->
        0.5 * ((Triangle)s).base() * ((Triangle)s).height();
```**D)**```java
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case Triangle t -> 0.5 * t.base() * t.height();
```**8. ¿Cuál será el resultado del siguiente programa?**```java
public class LabeledBreakTest {
    public static void main(String[] args) {
        int count = 0;
        outerLoop:
        while (count < 5) {
            while (true) {
                count++;
                if (count == 3) {
                    break outerLoop;
                }
            }
        }
        System.out.println(count);
    }
}
```**A)** `2`  
**B)** `3`  
**C)** `4`  
**D)** `5`  
**E)** El programa no compila


**9. ¿Cuál será el resultado del siguiente programa?**```java
public class ForLoopTest {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
```**A)** `5`  
**B)** `10`  
**C)** `15`  
**D)** `20`  
**E)** El programa no compila


**10. ¿Cuál será el resultado del siguiente programa?**```java
public class EnhancedForLoopTest {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                continue;
            }
            sum += num;
        }
        System.out.println(sum);
    }
}
```**A)** `9`  
**B)** `10`  
**C)** `12`  
**D)** `15`  
**E)** El programa no compila