-"None"
layout: chapter

title: "Capítulo DOS"
subtitle: "Utilizando el enfoque orientado a objetos de Java - Parte 2"
exam_objectives:
  - "Comprenda alcances variables, aplique encapsulación y cree objetos inmutables. Utilice la inferencia de tipos de variables locales."
  - "Implemente la herencia, incluidos tipos abstractos y sellados, así como clases de registros. Anular métodos, incluido el de la clase Objeto. Implementar polimorfismo y diferenciar entre tipo de objeto y tipo de referencia. Realice la conversión de tipos de referencia, identifique tipos de objetos utilizando el operador instancia de y la coincidencia de patrones con el operador instancia de y la construcción switch."
  - "Cree y utilice interfaces, identifique interfaces funcionales y utilice métodos de interfaz privados, estáticos y predeterminados."

previous_link: "/ch01.html"
previous_title: "Utilizando el enfoque orientado a objetos de Java - Parte 1"
next_link: "/ch03.html"
next_title: "Trabajar con registros y enumeraciones"
answers_link: "/ch02a.html"
-"None"## Contenido del capítulo

- [Variables](#variables)
    - [Ámbitos variables](#ámbitos-variables)
    - [Declaraciones de variables](#declaraciones-variables)
    - [Inferencia de tipo variable](#inferencia-tipo-variable)
- [Herencia](#herencia)
    - [Presentación de herencia] (#introducción-herencia)
    - [Clases abstractas](#clases-abstractas)
    - [Interfaces](#interfaces)
    - [Clases selladas](#clases-selladas)
    - [La referencia `esta`](#la-esta-referencia)
    - [La referencia `super`](#la-super-referencia)
- [Polimorfismo](#polimorfismo)
    - [Introduciendo el polimorfismo](#introduciendo-polimorfismo)
    - [Reglas primordiales] (#reglas-overriding)
    - [Acceso a objetos Java](#acceso-objetos-java)
    - [Fundición de tipos](#fundición de tipos)
    - [El operador `instancia de`] (#la-instancia-de-operador)
- [Encapsulación](#encapsulación)
    - [¿Qué es la encapsulación?](#qué-es-la-encapsulación)
    - [Objetos inmutables](#objetos-inmutables)
- [Puntos clave](#puntos clave)
- [Preguntas de práctica](#preguntas-de-practica)

---

##Variables

### Ámbitos variables
Podemos pensar en el alcance de una variable como su visibilidad, donde se puede ver y acceder a ella en nuestro código. Administrar adecuadamente el alcance de las variables nos ayuda a escribir código más limpio y fácil de mantener y a evitar errores relacionados con el acceso a variables en el contexto incorrecto. 

En el nivel más alto, el alcance de una variable está determinado por el lugar donde se declara. En Java, hay cinco ámbitos principales que se deben tener en cuenta:
- Variables de bloque
- Variables locales
- Parámetros del método
- Campos (variables de instancia)
- Variables de clase (campos estáticos)

Aquí te dejamos un diagrama para visualizarlo:```
┌───────────────────────────────────────────────┐
│ Class                                         │
│ ┌───────────────────────────────────────────┐ │
│ │ Static/Class Variables                    │ │
│ │ ┌───────────────────────────────────────┐ │ │
│ │ │ Instance Variables                    │ │ │
│ │ │ ┌───────────────────────────────────┐ │ │ │
│ │ │ │ Method                            │ │ │ │
│ │ │ │ ┌───────────────────────────────┐ │ │ │ │
│ │ │ │ │ Method Parameters             │ │ │ │ │
│ │ │ │ │ Other Local Variables         │ │ │ │ │
│ │ │ │ │ ┌───────────────────────────┐ │ │ │ │ │
│ │ │ │ │ │ Block                     │ │ │ │ │ │
│ │ │ │ │ │ ┌───────────────────────┐ │ │ │ │ │ │
│ │ │ │ │ │ │ Block Variables       │ │ │ │ │ │ │
│ │ │ │ │ │ └───────────────────────┘ │ │ │ │ │ │
│ │ │ │ │ └───────────────────────────┘ │ │ │ │ │
│ │ │ │ └───────────────────────────────┘ │ │ │ │
│ │ │ └───────────────────────────────────┘ │ │ │
│ │ └───────────────────────────────────────┘ │ │
│ └───────────────────────────────────────────┘ │
└───────────────────────────────────────────────┘
```Las variables locales se declaran dentro del método donde están definidas, mientras que las variables de bloque solo son accesibles dentro del bloque donde están definidas. Entran en el alcance en su declaración y salen del alcance al final del método/bloque adjunto:```java
void myMethod() {
    int x = 1;
    if (x > 0) { 
        int y = 2;
        System.out.println(x + y); // x and y both in scope here
    }
    System.out.println(x); // Only x is in scope here
    System.out.println(y); // Compile error! y is out of scope
}
```Como puede ver, "y" solo es visible dentro del bloque "if" donde se declaró. Intentar acceder a él fuera de ese bloque genera un error de compilación.

Si declara una variable dentro de un bucle, no podrá acceder a ella fuera del bucle. Incluso si todo está en el mismo método, el alcance aún termina al cerrar el ciclo `}`. Por ejemplo:```java
void myLoopingMethod() {
    for (int i = 0; i < 10; i++) { 
        System.out.println(i);
    }
    System.out.println(i); // Compile error! i is out of scope
}
```De manera similar, las variables declaradas en un inicializador de bucle for, como `int i` arriba, tienen como alcance solo el cuerpo del bucle, no todo el método adjunto.

Este concepto también se aplica a otros bloques como "if/else". Una variable declarada dentro de un `if` no es visible en el `else` correspondiente:```java
void myIfElseMethod(int x) {
    if (x > 0) {
        int y = 1; 
    } else {
        System.out.println(y); // Compile error! y not in scope
    }
}
```Luego tenemos los parámetros del método. Estas también se consideran variables locales, pero con un alcance que cubre todo el cuerpo del método. Entran en el ámbito cuando se llama al método y salen del ámbito cuando se completa el método.

Los parámetros son locales del método, ningún otro método puede verlos, incluso si el método se está ejecutando actualmente:```java
void methodA(int x) {
    methodB();
    System.out.println(x); // x is in scope
}

void methodB() {
    System.out.println(x); // Compile error! x is not in scope
}
```Los campos, o variables de instancia, son variables declaradas a nivel de clase, fuera de cualquier método. Entran en el alcance cuando se crea una instancia del objeto y permanecen en el alcance mientras el objeto esté en la memoria:```java
class MyClass {
    private int x; // Instance variable (field)

    void myMethod() {
        System.out.println(x); // x is in scope here
    }
}
```Dado que las variables de instancia pertenecen a una instancia de objeto, no se puede acceder a ellas desde contextos estáticos, pero se puede acceder a ellas mediante cualquier método de instancia de la clase.

Un error común es pensar que las variables de instancia se recolectan como basura tan pronto como finaliza el método que las utiliza, lo cual no es el caso. Los campos de un objeto permanecen en la memoria hasta que el objeto en sí sea elegible para la recolección de basura, lo que puede pasar mucho tiempo después de que se complete una llamada a un método en particular.

Además, recuerde que si la variable o su clase se declara "privada", solo la clase declarante puede acceder a ella. Pero si tienen acceso "público", "protegido" o predeterminado (paquete), otras clases también pueden acceder a ellos.

Finalmente, las variables de clase, o campos estáticos, son variables "estáticas" declaradas a nivel de clase. Entran en el ámbito cuando se carga la clase y permanecen en el ámbito hasta que finaliza el programa. Sólo hay una copia de una variable de clase compartida entre todas las instancias de la clase.

Las variables de clase pertenecen a la clase misma, no a una instancia de objeto específica. Y a diferencia de las variables de instancia, se puede acceder a las variables de clase desde contextos estáticos y de instancia:```java
class MyClass {
    private static int x; // Class variable

    void myMethod() {
        System.out.println(x); // x is in scope 
    }

    static void myStaticMethod() {
        System.out.println(x); // x is also in scope
    }
}
```Se puede acceder a las variables de clase desde cualquier lugar de su programa, incluso sin crear una instancia de la clase. Pero todavía están sujetos a controles de acceso como "privado" y "público".

Un caso interesante es cuando tienes dos variables con el mismo nombre pero con diferentes alcances:```java
class MyClass {
    private int x; // Instance variable 
    
    void myMethod() {
        int x = 1; // Local variable
        System.out.println(x); // Prints 1 (local variable)
        System.out.println(this.x); // Prints 0 (instance variable) 
    }
}
```En esta situación, la variable local sigue a la variable de instancia dentro de su alcance. Para acceder a la variable de instancia, tenemos que usar la palabra clave `this`. Hablaremos de esto más adelante en el capítulo, pero, como puede ver, limitar adecuadamente el alcance no se trata de mejorar el rendimiento, sino de organizar nuestro código y controlar el acceso a las variables.


### Declaraciones de variables
Cuando empiezas a aprender Java, es fácil pensar que los campos y las variables locales son prácticamente lo mismo. Después de todo, ambas son sólo variables, ¿verdad? Los declaras, les das un tipo y un nombre, tal vez les asignas un valor y luego los usas en tu código. ¿Cuál es el problema?

Bueno, resulta que existen algunas diferencias bastante importantes entre los campos y las variables locales en Java. 

Los campos se declaran directamente dentro de una clase, pero fuera de cualquier método o constructor. Son parte del estado de la clase y cada instancia de la clase obtiene su propia copia de estos campos.

Las variables locales, por otro lado, se declaran dentro de un método o constructor. Solo existen mientras dura la llamada al método o constructor y no se puede acceder a ellos desde el exterior. Una vez que el método ha terminado de ejecutarse, las variables locales desaparecen.

He aquí un ejemplo:```java
public class MyClass {
    private int myField; // This is a field

    public void myMethod() {
        int myLocalVar = 25; // This is a local variable
        // Do something with myLocalVar...
    } // myLocalVar no longer exists after this point
}
```Ahora, podrías estar pensando: "Está bien, entonces los campos están en la clase y las variables locales están en los métodos. ¿Pero no puedo usarlos indistintamente de otra manera?" Bueno, no del todo. Hay algunas diferencias clave en cómo se comportan.

Por un lado, los campos obtienen automáticamente valores predeterminados si no los inicializas explícitamente. Para tipos numéricos (como `int`, `long`, `float`, `double`) el valor predeterminado es `0`. Para "booleano", es "falso". Para tipos de referencia (como `String` o cualquier objeto), es `null`.

Por otro lado, las variables locales no obtienen ningún valor predeterminado. Si intenta utilizar una variable local antes de inicializarla, obtendrá un error de compilación. En otras palabras, el compilador de Java quiere que usted sea explícito acerca de sus intenciones con las variables locales:```java
public void myMethod() {
    int uninitialized;
    System.out.println(uninitialized); // Compile error!
}
```Entonces Java requiere que inicialices una variable local antes de usarla. Pero, ¿cuándo exactamente necesitas realizar esta inicialización? La regla es simple: la inicialización debe ocurrir en todas las rutas de ejecución posibles antes del primer uso de la variable:```java
int myVar;
if (someCondition) {
    myVar = 1;
} else {
    myVar = 2;
}
System.out.println(myVar); // This is fine

int myOtherVar;
if (someCondition) {
    myOtherVar = 1;
}
System.out.println(myOtherVar); // Compile error! Not initialized on the else path.
```En el primer ejemplo, se garantiza que `myVar` se inicializará antes de usarse, independientemente de la ruta que tome `if/else`. Pero en el segundo ejemplo, si `someCondition` es `false`, `myOtherVar` no se inicializará antes de su primer uso, de ahí el error de compilación.

En cualquier caso, campos o variables locales, Java permite declarar varias variables del mismo tipo en una sola línea, separadas por comas:```java
int a, b, c;
```Pero esto no significa que estas variables compartan el mismo valor. Son variables completamente independientes que simplemente se declaran juntas. Puedes asignarles diferentes valores:```java
int a = 1, b = 2, c = 3;
```De hecho, no es necesario asignarles todos los valores de inmediato. Está totalmente bien hacer esto:```java
int a, b, c;
a = 1;
b = 2;
// c remains uninitialized for now
```Solo recuerda que no puedes usar `c` hasta que lo inicialices con un valor, o obtendrás un error de compilación.

Ahora bien, ¿qué pasa cuando deseas declarar múltiples variables de diferentes tipos? Bueno, no puedes hacer eso en una sola línea como puedes hacerlo con variables del mismo tipo. Tendrás que declarar cada uno por separado:```java
int a = 1;
String b = "hello";
// This won't compile: int a = 1, String b = "hello";
```Otra diferencia entre las variables locales y los campos está en cómo se usa "final". Marcar un campo como "final" significa que debe inicializarse cuando se construye el objeto y luego nunca podrá volver a cambiarse. Con una variable local, "final" solo significa que solo puedes asignarle un valor una vez. Pero esa asignación no tiene que ocurrir cuando se declara la variable:```java
public class MyClass {
    private final int myFinalField = 42; // Must initialize here

    public void myMethod(int arg) {
        final int myFinalVar; // Okay to initialize later
        if (arg > 0) {
            myFinalVar = arg;
        } else {
            myFinalVar = 0;
        }
        // Can't assign to myFinalVar again after this point
    }
}
```La asignación debe ocurrir antes del primer uso de la variable y solo puede ocurrir una vez. Esto suele resultar útil cuando desea asignar un valor de forma condicional, como en el ejemplo anterior. O cuando desea asignar un valor en un bucle pero asegurarse de que no cambie después del bucle:```java
final int myFinalVar;
for (int i = 0; i < 10; i++) {
    // Some calculation...
    myFinalVar = result;
    // Can't assign to myFinalVar again after this point
}
```Sin embargo, cuando se trabaja con referencias y objetos, si convierte una variable local en "final", puede cambiar las propiedades del objeto al que hace referencia. `final` solo le impide asignar un nuevo valor a la variable misma. Si la variable es una referencia a un objeto, aún puedes modificar ese objeto:```java
final StringBuilder sb = new StringBuilder();
sb.append("Hello"); // This is fine
sb = new StringBuilder(); // This won't compile
```En este ejemplo, podemos llamar a métodos en `sb` que modifican el objeto `StringBuilder`, pero no podemos asignar una nueva instancia de `StringBuilder` a `sb`.

### Inferencia de tipo de variable
Java 10 y versiones posteriores introdujeron una nueva característica, "var". Te permite declarar una variable local sin especificar su tipo:```java
var myVar = 42;
```Esto se llama inferencia de tipo de variable local. El compilador analiza el valor que está asignando a la variable y determina el tipo apropiado para usted. En este caso, infiere que "myVar" debería ser un "int".

Tradicionalmente, declarar variables locales a menudo podía generar código detallado y repetitivo. Por ejemplo:```java
HashMap<Integer, String> map = new HashMap<>();
List<String> list = new ArrayList<>();
AtomicInteger counter = new AtomicInteger(0);
```En cada caso, el tipo se menciona dos veces, una en el lado izquierdo y otra en el lado derecho. Aquí es donde entra en juego la palabra clave `var`.

Al usar `var`, el código anterior se puede reescribir como:```java
var map = new HashMap<Integer, String>();
var list = new ArrayList<String>();
var counter = new AtomicInteger(0);
```El compilador infiere los tipos de "mapa", "lista" y "contador" en función de las expresiones del inicializador. Esto hace que el código sea más conciso y legible, manteniendo al mismo tiempo la seguridad de tipos.

Es importante tener en cuenta que `var` se comporta como una palabra clave en su contexto de uso, aunque técnicamente es un nombre de tipo reservado para la inferencia de tipos de variables locales. Esto significa que el código que utiliza `var` como variable, método o nombre de paquete no se verá afectado.

`var` está restringido a variables locales dentro de métodos, constructores o bloques inicializadores. No se puede utilizar para declarar variables de instancia (campos) o variables de clase (estáticas). Esta restricción garantiza que el tipo de clase y las variables de instancia siempre estén claros en la API de la clase, no solo en su implementación:```java
public class MyClass {
   var myVar = "Hello"; // This will not compile
}
```Al igual que las variables de instancia y clase, `var` no se puede utilizar para declarar parámetros de método. Las firmas de métodos son parte de la API pública de la clase y deben indicar explícitamente sus tipos de parámetros para mayor claridad y garantizar la estabilidad del contrato:```java
public void myMethod(var param) { // This will not compile
   // ...
}
```Aparte de eso, `var` se puede utilizar en otras situaciones. Por ejemplo, en índices de bucle `for`:```java
var numbers = Arrays.asList(1, 2, 3, 4, 5);
for (var num : numbers) {
    System.out.println(num);
}

// Or

for (var i = 1; i <= 10; i++) {
    System.out.println(i);
}
```En declaraciones de "probar con recursos":```java
try (var stream = Files.lines(Path.of("file.txt"))) {
    stream.forEach(System.out::println);
}
```O para los parámetros de expresiones lambda escritas implícitamente:```java
Function<Integer, String> toString = (var i) -> String.valueOf(i);
```Tenga en cuenta que en una expresión lambda, todos los parámetros deben declararse con `var` o ninguno de ellos. No se permite mezclar `var` con tipos manifiestos o tipos inferidos.

Sin embargo, tenga cuidado con `var`, no siempre es la mejor opción. A veces, declarar explícitamente el tipo puede hacer que el código sea más legible y fácil de mantener. Solo puedes usar `var` cuando estás inicializando la variable allí mismo en la declaración:```java
var myVar; // This won't compile
var myOtherVar = someMethodThatReturnsAnObject(); // Fine, as long as the method return type is clear
```De manera similar, `var` no se puede usar al inicializar una variable con un valor `nulo` sin especificar su tipo porque el compilador no puede inferir el tipo de la variable:```java
// This will not compile because the type cannot be inferred
var myVar = null;
```Sin embargo, una vez que se ha usado `var` para declarar una variable con un tipo concreto, se le puede reasignar un valor `nulo`:```java
var myString = "Hello, World!"; // Inferred as String
myString = null; // This is allowed
```Finalmente, cuando se usa `var` con inicializadores de matriz, se requiere una creación de instancias explícita. No puede utilizar sintaxis abreviada porque no se puede inferir el tipo:```java
var numbers = new int[] {1, 2, 3}; // This works
// var numbers = {1, 2, 3}; // This will not compile
 ```## Herencia

### Presentamos la herencia
La herencia es uno de los conceptos centrales en la programación orientada a objetos. Le permite definir una nueva clase basada en una clase existente. La nueva clase hereda los atributos y métodos de la clase existente, lo que le permite reutilizar el código y construir relaciones jerárquicas entre sus clases.

¿Recuerdas la clase `Cookie` del principio del capítulo anterior?```java
public class Cookie {
    // Attributes
    String flavor; 
    int size;
                     
    // Behavior (Method)
    public void eat() {
        System.out.println("That was yummy!");
    }
}
```¿Cómo definirías una clase de galletas con chispas de chocolate? 

Bueno, las galletas con chispas de chocolate tienen sabor, cantidad de chispas y se pueden comer como galletas normales. Pero también tienen propiedades adicionales como la cantidad de chips por galleta. Entonces nuestra clase ingenua inicial `ChocolateChipCookie` podría verse así:```java
public class ChocolateChipCookie {

  String flavor;  
  int size;

  void eat() {
    System.out.println("That was yummy!"); 
  }

  int chips;

}
```¡Hemos duplicado los atributos y métodos de las cookies! No es un buen diseño.

Aquí es donde entra el concepto de herencia en la programación orientada a objetos.

Todas las variedades de galletas comparten propiedades comunes como tener sabor y ser comestibles. Podemos representar esto con una clase principal `Cookie` que contiene `sabor`, `size` y un método `eat()`. 

Las clases secundarias, como `ChocolateChipCookie`, pueden heredar estos elementos de cookie comunes de la clase principal `Cookie`. De esta manera, podemos crear muchas variedades específicas que heredan propiedades de cookies compartidas. Las clases secundarias aún pueden definir sus propios atributos especializados, como la cantidad de chispas de chocolate, pero reutilizan el código principal heredado.

En Java, se utiliza la palabra clave `extends` para crear una subclase que hereda de una superclase. Así es como se puede definir la clase `ChocolateChipCookie` usando herencia:```java
public class ChocolateChipCookie extends Cookie {

  int chips;

  public void addChips(int chipsPerCookie) {
    this.chips += chipsPerCookie;
  }

}  
```Aquí, "ChocolateChipCookie" es una subclase de "Cookie". Hereda los campos `sabor` y `size` y el método `eat()`. La subclase puede declarar sus propios métodos, como `ChocolateChipCookie` declara el método `addChips()`.

Sin embargo, una subclase no puede acceder directamente a miembros "privados" de su superclase. Las subclases sólo pueden acceder directamente a los miembros "protegidos" y "públicos" de la superclase. Para acceder a campos "privados", la superclase debe proporcionar accesores "públicos" o "protegidos".

Una cosa importante que hay que saber es que en Java, una clase sólo puede extenderse desde una clase debido a la elección del diseño para evitar la complejidad y ambigüedad asociadas con la herencia múltiple. En otras palabras, la herencia múltiple, donde una clase puede extenderse a más de una clase, puede conducir a:

1. **Problema del diamante:** Esta es una complicación bien conocida en la que una clase hereda de dos clases que tienen una clase base común. Este escenario crea ambigüedad en la jerarquía de herencia cuando dos clases principales tienen métodos con la misma firma, ya que es posible que el sistema no pueda determinar qué versión del método heredado usar.

2. **Mayor complejidad:** Permitir la herencia múltiple puede hacer que el diseño y el mantenimiento de un programa sean más complejos. Comprender el flujo de métodos y variables se vuelve más difícil, especialmente en bases de código grandes.

Algunos modificadores de clase importantes relacionados con la herencia son "final", "abstracto" y "sellado".

Las clases finales no se pueden subclasificar. Si intenta extender una clase "final", obtendrá un error de compilación. Usando el ejemplo de las cookies, si la clase `Cookie` fuera declarada como `final`:```java
public final class Cookie {
    // ...
}
```La declaración de la clase `ChocolateChip` generará un error de compilación.

Hacer que una clase sea "final" garantiza que su implementación no pueda cambiarse mediante subclases. Sin embargo, contrariamente a una idea errónea común, las clases "finales" no son más eficientes en tiempo de ejecución sólo porque sean "finales". El modificador "final" tiene que ver con la herencia, no con el rendimiento.

No se pueden crear instancias de clases abstractas, solo subclasificarlas. Están incompletos por sí solos y es necesario ampliarlos para poder utilizarlos. Las clases abstractas a menudo contienen métodos abstractos que no tienen implementación en la clase abstracta y deben ser implementados por subclases concretas. Intentar crear una instancia de una clase abstracta con "nuevo" resultará en un error de compilación.

Las clases selladas proporcionan un punto medio entre las clases finales y no finales. Las clases selladas se pueden ampliar, pero sólo mediante clases a las que se permita explícitamente hacerlo en la declaración de clase sellada. Esto le brinda un control detallado sobre la herencia. Las subclases de clases selladas deben declararse a su vez selladas, no selladas o definitivas. Las clases selladas restringen pero no prohíben por completo la herencia como lo hacen las clases finales.

Repasemos con más detalle las clases abstractas y selladas.

### Clases abstractas
Una clase abstracta es una clase de la que no se pueden crear instancias, lo que significa que no se pueden crear nuevas instancias de una clase abstracta. Sirve como base para subclases:```java
abstract class Cookie {
    abstract void flavor(); 
}
```Debes utilizar la palabra clave `abstracto` para declarar una clase o un método como abstracto. Una clase abstracta puede incluir o no métodos abstractos.

Los métodos abstractos se declaran sin implementación (sin llaves y seguidos de un punto y coma):```java
abstract void flavor();
```Los métodos abstractos son similares a los métodos regulares en el sentido de que los declaras con o sin parámetros, con un valor de retorno o "void" y cualquier modificador de acceso como "public", "protected" o default. La única diferencia es que los métodos abstractos no tienen ninguna implementación, no pueden tener cuerpo, por lo tanto, terminan con punto y coma (`;`) y no con corchetes (`{}`).

Para usar una clase abstracta, debes heredarla de otra clase usando la palabra clave `extends`. Veamos un ejemplo:```java
class OatmealRaisinCookie extends Cookie {
    void flavor() {
        System.out.println("Oatmeal and raisin flavor");
    }
}
```Cuando se hereda de una clase abstracta, la subclase generalmente proporciona implementaciones para todos los métodos abstractos de su clase principal. Si no es así, entonces la subclase también debe declararse abstracta:```java
abstract class Cookie {
    abstract void flavor();
    
    public void bake() {
        System.out.println("Cookie is baking");
    }
}

abstract class OatmealRaisinCookie extends Cookie {
    // Abstract method which makes the class abstract
    // (Otherwise it will not compile)
    abstract void flavor();
    
    // Even if it defines concrete method(s)
    public void addRaisins() {
        System.out.println("Adding raisins");
    }
}
```¿Por qué?

Porque una clase abstracta está (o pretende estar) incompleta. Crear un objeto a partir de una clase incompleta sería incorrecto.  Es necesario ampliar una clase abstracta para poder utilizarla, de forma muy parecida a una plantilla.

Las clases abstractas son útiles para compartir código entre clases estrechamente relacionadas. Lo más importante es que las clases abstractas pueden definir métodos que las subclases deben implementar, estableciendo un contrato o un protocolo que las subclases deben seguir.

Es bueno pensar en las clases concretas como especializaciones de clases abstractas. De la misma manera que un automóvil compacto es una especialización del concepto general de automóvil, las clases abstractas son el concepto general y las clases concretas son una implementación específica de ese concepto.

Las clases concretas tienen que implementar todos los métodos abstractos pero también pueden definir sus propios métodos nuevos. No todos los métodos tienen que ser abstractos en una clase concreta, sólo los declarados como abstractos en la clase abstracta principal. Aquí hay un ejemplo para ilustrar esto:```java
abstract class Cookie {
    abstract void flavor();
    
    public void bake() {
        System.out.println("Cookie is baking");
    }
}

class ChocolateCookie extends Cookie {
    // Implementing the abstract method
    void flavor() {
        System.out.println("Chocolate flavor");
    }
    
    // Defining its own new method
    public void addChocolateChips() {
        System.out.println("Adding chocolate chips");
    }
}

class OatmealRaisinCookie extends Cookie {
    // Implementing the abstract method
    void flavor() {
        System.out.println("Oatmeal and raisin flavor");
    }
    
    // Defining its own new method
    public void addRaisins() {
        System.out.println("Adding raisins");
    }
}
```En este ejemplo, `Cookie` es una clase abstracta con un método abstracto `flavor()` y un método concreto `bake()`.

Las clases `ChocolateCookie` y `OatmealRaisinCookie` son clases concretas que amplían la clase abstracta `Cookie`. Ambos implementan el método abstracto `flavor()` que heredaron de `Cookie`. Nuevamente, esto es obligatorio; de lo contrario, también tendrían que declararse abstractos.

Pero `ChocolateCookie` y `OatmealRaisinCookie` también definen sus propios métodos nuevos, `addChocolateChips()` y `addRaisins()` respectivamente. Estos métodos son específicos de cada tipo de cookie y no están relacionados con la clase abstracta.

Cuando creas instancias de `ChocolateCookie` y `OatmealRaisinCookie`, puedes llamar a todos sus métodos:```java
ChocolateCookie chocolateCookie = new ChocolateCookie();
chocolateCookie.flavor();          // Output: Chocolate flavor
chocolateCookie.addChocolateChips();  // Output: Adding chocolate chips
chocolateCookie.bake();            // Output: Cookie is baking

OatmealRaisinCookie oatmealRaisinCookie = new OatmealRaisinCookie();
oatmealRaisinCookie.flavor();      // Output: Oatmeal and raisin flavor
oatmealRaisinCookie.addRaisins();  // Output: Adding raisins
oatmealRaisinCookie.bake();        // Output: Cookie is baking
```Las clases abstractas pueden tener constructores. Los necesita para inicializar atributos y ejecutar cualquier lógica que deba ejecutarse cuando se crea una instancia de la (sub)clase concreta. Una clase abstracta es una clase y, como cualquier otra clase, puede tener atributos y es posible que sea necesario inicializar esos atributos cuando se crea una instancia (de la clase concreta). He aquí un ejemplo:```java
abstract class Cookie {
    protected String name;
    
    public Cookie(String name) {
        this.name = name;
        System.out.println("Cookie constructor is called");
    }
    
    abstract void flavor();
    
    public void bake() {
        System.out.println(name + " is baking");
    }
}
```En este ejemplo actualizado, la clase abstracta `Cookie` ahora tiene un constructor que toma un parámetro `name`. Inicializa el atributo "nombre" de la cookie. El atributo "nombre" se declara como "protegido", lo que significa que es accesible para las subclases.

De esta manera, las clases concretas `ChocolateCookie` y `OatmealRaisinCookie` pueden llamar al constructor de la clase abstracta `Cookie` usando `super()`, pasando el nombre específico para cada tipo de cookie. Veremos cómo usar `super()` más adelante en este capítulo.

Cuando piensa en las clases abstractas como un contrato o una plantilla que las subclases deben seguir y completar para garantizar un comportamiento común, las siguientes reglas tienen sentido:

- Una clase abstracta no puede ser definitiva. El modificador "final" impide que una clase sea subclasificada, y esto contradice la esencia de una clase abstracta, que debe heredarse para poder usarse. Entonces no, marcar una clase abstracta como "final" no la haría más segura, la haría inútil.

- Los métodos abstractos tampoco pueden ser finales, por la misma razón, deben ser anulados en una subclase. 

- Los métodos abstractos no pueden ser nativos ni sincronizados, por motivos ligeramente diferentes. Un método nativo se implementa en otro lenguaje como C++ en la JVM, por lo que ya tendría una implementación. Hablaremos de métodos sincronizados en un capítulo posterior, pero el modificador "sincronizado" se usa para coordinar el acceso multiproceso y, para eso, el método necesita un cuerpo, una implementación.

- Un método abstracto no puede ser "privado". No tiene sentido que un método que debe ser implementado por una subclase en otra clase no pueda ser visto por esa clase. Entonces no, los métodos abstractos "privados" no pueden existir.

- Por último, un método abstracto tampoco puede ser estático. Los métodos estáticos pertenecen a la clase misma, no a ninguna instancia. Un método abstracto sólo es útil cuando una subclase lo implementa, lo que significa que una instancia lo usa.

En resumen, estas son las reglas para declarar correctamente clases y métodos abstractos:

- Si una clase contiene uno o más métodos abstractos, la clase debe declararse como abstracta.

- Una clase abstracta puede tener métodos tanto abstractos como no abstractos (concretos).

- Una clase abstracta puede extender a otra clase abstracta o concreta y una clase abstracta puede extenderse a otra clase abstracta o concreta.

- Una subclase puede anular un método concreto en una superclase y declararlo como abstracto.

- Una subclase abstracta puede anular algunos o ninguno de los métodos abstractos de su superclase, pero una primera subclase concreta debe implementarlos todos.

Ahora, antes de hablar de clases selladas, repasemos el tema de las interfaces.

### Interfaces
Cuando se trata de programación orientada a objetos, además de clases, Java proporciona una herramienta poderosa: las interfaces. Una interfaz en Java es esencialmente un contrato que define un conjunto de métodos que una clase debe implementar. Es similar al menú de un restaurante. El menú enumera los platos disponibles pero no proporciona detalles sobre cómo se preparan. Cuando pides un plato del menú, la cocina (la clase) proporciona una implementación específica de ese plato (el método).

Entonces, ¿qué es exactamente una interfaz y en qué se diferencia de una clase normal o incluso de una clase abstracta?

Una interfaz en Java es un tipo de referencia, similar a una clase, que sólo puede contener constantes, firmas de métodos, métodos predeterminados, métodos estáticos y tipos anidados. No se pueden crear instancias de las interfaces, solo se pueden implementar mediante clases o ampliar mediante otras interfaces.

Para declarar una interfaz, utilice la palabra clave "interfaz" en lugar de la palabra clave "clase". He aquí un ejemplo:```java
public interface Drawable {
    void draw();
}
```Cualquier clase que implemente la interfaz `Drawable` debe proporcionar una implementación para el método `draw()`.

A primera vista, las interfaces pueden parecer muy similares a las clases abstractas. Después de todo, ambos pueden contener métodos abstractos, métodos sin cuerpo. Sin embargo, existen algunas diferencias clave:
- Una clase abstracta puede tener variables de instancia y constructores, mientras que una interfaz no.

- Una clase abstracta puede tener métodos no abstractos, mientras que todos los métodos en una interfaz son implícitamente abstractos (con la excepción de los métodos predeterminados y estáticos, que cubriremos más adelante).

- Una clase puede extender sólo una clase abstracta, pero puede implementar múltiples interfaces.

Entonces, si bien existe cierta superposición, las interfaces y las clases abstractas tienen propósitos diferentes y no son intercambiables.

Para utilizar una interfaz, una clase debe implementarla. La palabra clave `implements` se utiliza para implementar una interfaz:```java
public class Circle implements Drawable {
    public void draw() {
        System.out.println("Drawing a circle");
    }
}
```Si una clase implementa una interfaz pero no implementa todos los métodos, debe declararse como "abstracta".```java
public abstract class Shape implements Drawable {
    // Class content
}
```Todos los métodos en una interfaz son implícitamente "públicos" y "abstractos". No es necesario utilizar la palabra clave "pública" o "abstracta" al declarar métodos en una interfaz. 

Todas las variables declaradas en una interfaz son implícitamente "públicas", "estáticas" y "finales".

Entonces esto:```java
public interface MyInterface {
    int NUMBER = 10;
    void method();
}
```Es equivalente a esto:```java
public interface MyInterface {
    public static final int NUMBER = 10;
    public abstract void method();
}
```Es importante tener en cuenta que debido a que los métodos de interfaz son "abstractos", no pueden declararse como "privados", "protegidos", "finales" o "estáticos" (con la excepción de los métodos "estáticos", que cubriremos más adelante).

Una interfaz puede extender otra interfaz, de manera similar a cómo una clase puede extender otra clase. La palabra clave "extiende" se utiliza para esto:```java
public interface Moveable {
    void move();
}

public interface Drawable extends Moveable {
    void draw();
}
```En este caso, cualquier clase que implemente `Drawable` debe proporcionar implementaciones tanto para `draw()` como para `move()`.

Una clase sólo puede extenderse desde una clase. Sin embargo, una clase puede implementar múltiples interfaces. Esta es una forma de lograr una forma de herencia múltiple en Java:```java
public interface Moveable {
    void move();
}

public interface Drawable {
    void draw();
}

public class Circle implements Drawable, Moveable {
    public void draw() {
        System.out.println("Drawing a circle");
    }

    public void move() {
        System.out.println("Moving a circle");
    }
}
```Esto no viola la regla de herencia única de Java porque las interfaces no contienen ninguna implementación. Si una clase implementa dos interfaces que tienen el mismo método, no hay problema. La clase simplemente proporciona una implementación del método, resolviendo los problemas de ambigüedad y complejidad:```java
public interface A {
    void method();
}

public interface B {
    void method();
}

public class C implements A, B {
    public void method() {
        System.out.println("Method implementation");
    }
}
```Además, las interfaces pueden tener métodos predeterminados. Estos son métodos con un cuerpo que proporciona una implementación predeterminada si una clase no los anula:```java
public interface Drawable {
    void draw();
    default void print() {
        System.out.println("Printing...");
    }
}
```Las clases que implementan `Drawable` pueden, aunque no es necesario, anular el método `print()`.

Si una clase implementa dos interfaces y ambas tienen el mismo método predeterminado, la clase debe anular el método. Si quiere llamar al método predeterminado desde una de las interfaces, puede hacerlo usando la palabra clave `super`:```java
public interface A {
    default void method() {
        System.out.println("A's method");
    }
}

public interface B {
    default void method() {
        System.out.println("B's method");
    }
}

public class C implements A, B {
    public void method() {
        A.super.method();
    }
}
```Las interfaces también pueden tener métodos estáticos, similares a los métodos estáticos de las clases:```java
public interface Drawable {
    static void staticMethod() {
        System.out.println("Static method");
    }
}
```Los métodos estáticos en las interfaces no son heredados por clases o interfaces que extienden la interfaz.

Para el ejemplo anterior, usaría la interfaz `Drawable` para llamar a `staticMethod` de esta manera:```java
Drawable.staticMethod();
```Además de los métodos predeterminados y "estáticos", las interfaces también pueden tener métodos "privados". Estos son útiles para compartir código entre métodos predeterminados en la interfaz:```java
public interface Drawable {
    default void print() {
        printLine();
        System.out.println("Printing...");
    }

    private void printLine() {
        System.out.println("---");
    }
}
```Las clases que implementan la interfaz no pueden acceder a los métodos privados en las interfaces.

### Clases selladas
Imaginemos una familia real con una regla estricta: sólo ciertas personas pueden convertirse en futuros reyes o reinas, y esta regla es inmutable. En Java, las clases selladas son como esta familia real. Permiten que una clase controle estrictamente qué otras clases pueden extenderlo, al igual que la familia real controla quién puede estar en la fila para el trono.

Entonces, si una clase está sellada, ¿eso significa que está completamente bloqueada y nadie puede extenderla en absoluto? No exactamente. Una clase sellada simplemente restringe quién puede extenderla, pero no está completamente prohibida. Puedes especificar un conjunto de subclases permitidas.

Esta característica es útil por varias razones:
- Permite a los autores de bibliotecas evolucionar las API con el tiempo evitando extensiones no deseadas.
- Permite modelar jerarquías y máquinas de estados con un conjunto finito de subclases.
- Proporciona seguridad en tiempo de compilación al limitar las posibilidades de código externo.

Para crear una clase sellada, utiliza el modificador "sellado" en la declaración de clase, junto con la cláusula "permisos" para especificar las subclases permitidas:```java
public sealed class Vehicle permits Car, Truck, Motorcycle {
    public void startEngine() {
        System.out.println("Starting the vehicle's engine.");
    }
}

final class Car extends Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Starting the car's engine.");
    }
}

final class Truck extends Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Starting the truck's engine.");
    }
}

final class Motorcycle extends Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Starting the motorcycle's engine." );
    }
}
```El modificador "sellado" indica que la clase está sellada. La cláusula "permisos" enumera las clases a las que se les permite extender la clase sellada.

Las clases selladas y sus subclases deben declararse en el mismo paquete (o módulo con nombre) que sus subclases directas. Esto asegura una estrecha relación entre la clase sellada y sus subclases permitidas.

Cada clase que extiende directamente una clase sellada debe especificar exactamente uno de los siguientes tres modificadores: "final", "sellado" o "no sellado":

- `final`: la subclase no se puede ampliar más. Esta es la opción más restrictiva.
- `sellada`: la subclase también está sellada y debe especificar sus propias subclases permitidas.
- `no sellado`: la subclase está abierta a la extensión de subclases desconocidas. Esta es la opción más permisiva.

Si no especifica uno de estos modificadores en una subclase directa de una clase sellada, obtendrá un error de compilación. El compilador aplica esto para garantizar que la jerarquía esté bien definida.

Marcar una subclase como "no sellada" simplemente significa que está abierta a extensiones. No requiere que crees nuevas subclases. Usar accidentalmente "no sellado" cuando no agregas más subclases no romperá nada, pero sí indica a otros desarrolladores que tu intención es permitir que la clase se extienda.

La cláusula `permisos` es opcional si la clase sellada y sus subclases directas se declaran dentro del mismo archivo o las subclases están anidadas dentro de la clase sellada. El compilador puede inferir las subclases permitidas en estos casos, por lo que puede omitir el listado explícito.

A continuación se muestra un ejemplo en el que se omite la cláusula "permisos":```java
// Beverage.java
public sealed class Beverage {
    void pour();
}

final class Coffee implements Beverage {
    public void pour() {
        System.out.println("Pouring coffee");
    } 
}

final class Tea implements Beverage {
    public void pour() {
        System.out.println("Pouring tea");
    }
}
```Dado que `Coffee` y `Tea` se declaran en el mismo archivo que la clase `Beverage` sellada (`Beverage.java`), el compilador puede inferir la cláusula `permisos`.

Entonces, ¿las clases selladas solo se pueden usar dentro del mismo archivo? No, las clases selladas y sus subclases pueden estar en archivos diferentes, siempre y cuando estén en el mismo paquete o módulo. La restricción del mismo archivo solo es relevante para omitir la cláusula "permisos".

Y para responder a otra pregunta común: "Si sello una clase, no puedo usarla en otro paquete, ¿verdad?" Puedes usar una clase sellada de otro paquete, pero no puedes declarar sus subclases en un paquete diferente. El uso no está restringido, sólo la extensión.

En cualquier caso, una vez sellada una clase, el conjunto de subclases permitidas queda fijo. No puede agregar nuevas subclases fuera de lo especificado en la cláusula "permisos". Si necesita ampliar la jerarquía más adelante, deberá modificar la clase sellada para permitir subclases adicionales. Esto requiere recompilar la clase sellada y sus subclases existentes.

Si se pregunta si existe un límite en la cantidad de subclases que puede permitir una clase sellada, la respuesta es no, no hay un límite estricto en la cantidad de subclases que puede permitir. Sin embargo, la intención de las clases selladas es tener un conjunto finito y manejable de subclases. Permitir cientos de subclases iría en contra de ese espíritu y probablemente indicaría un problema de diseño. Cíñete a un número razonable que tenga sentido para tu caso de uso.

El sellamiento no se limita solo a las clases. También puedes sellar interfaces.

Las interfaces se pueden sellar para limitar las clases que las implementan o las interfaces que las amplían. He aquí un ejemplo:```java
public sealed interface Shape permits Circle, Rectangle, Triangle, Polygon {
    double getArea();
}

final class Circle implements Shape {
    public double getArea() {
        // Implementation of getArea() for circles
    }
}

final class Rectangle implements Shape {
    public double getArea() {
        // Implementation of getArea() for rectangles
    }
}

final class Triangle implements Shape {
    public double getArea() {
        // Implementation of getArea() for triangles
    }
}

sealed interface Polygon extends Shape permits RegularPolygon, IrregularPolygon {
    int getNumberOfSides();
}

final class RegularPolygon implements Polygon {
    public double getArea() {
        // Implementation of getArea() for regular polygons
    }
    
    public int getNumberOfSides() {
        // Implementation of getNumberOfSides() for regular polygons
    }
}

final class IrregularPolygon implements Polygon {
    public double getArea() {
        // Implementation of getArea() for irregular polygons
    }
    
    public int getNumberOfSides() {
        // Implementation of getNumberOfSides() for irregular polygons
    }
}
```En este ejemplo, la interfaz `Shape` está sellada y permite que cuatro clases la implementen: `Circle`, `Rectangle`, `Triangle` y `Polygon`. Esto significa que sólo estas cuatro clases pueden implementar directamente la interfaz `Shape`.

Pero la interfaz "Polygon" también está sellada y amplía la interfaz "Shape". Permite que dos clases lo implementen: `RegularPolygon` y `IrregularPolygon`. Esto demuestra cómo se puede utilizar el sellado para controlar qué interfaces pueden extender una interfaz sellada.

Al sellar la interfaz `Polygon`, restringimos las clases que pueden implementarla solo a `RegularPolygon` e `IrregularPolygon`. Ninguna otra clase puede implementar directamente "Polygon". Sin embargo, dado que `Polygon` extiende `Shape`, las clases `RegularPolygon` e `IrregularPolygon` también implementan indirectamente `Shape`.

Esto permite una estructura de herencia bien definida y restringida. 

Lo anterior también se aplica a las clases, puedes cambiar la interfaz `Shape` a una clase y hacer las modificaciones necesarias a las otras clases para lograr una estructura jerárquica similar.

Para resumir, estas son las reglas clave para las clases selladas:

1. Las clases selladas se declaran con los modificadores "sellado" y "permisos".

2. Las clases selladas deben declararse en el mismo paquete o módulo con nombre que sus subclases directas. 

3. Las subclases directas de clases selladas deben marcarse como "final", "sellada" o "no sellada".

4. La cláusula `permisos` es opcional si la clase sellada y sus subclases directas se declaran dentro del mismo archivo o las subclases están anidadas dentro de la clase sellada.

5. Las interfaces se pueden sellar para limitar las clases que las implementan o las interfaces que las extienden.


### La referencia "esta"
Cuando escribes código en Java, a menudo verás la palabra clave "esto" en tus métodos y constructores. Pero, ¿qué es exactamente "esto" y por qué lo usamos?

`this` es una referencia a la instancia actual de una clase. En otras palabras, cuando estás dentro de un método o constructor de una clase, "esto" se refiere al objeto específico al que pertenece el método o constructor. He aquí un ejemplo sencillo:```java
public class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;
    }
}
```En el constructor, usamos `this.name` para especificar que estamos hablando del campo `name` de este objeto `Persona` en particular, no de alguna otra variable `name`.

Pero espera, quizás estés pensando: "Entonces, `esta` es sólo otra variable que puedo cambiar, ¿verdad?" Bueno, no exactamente. `this` es una referencia final, lo que significa que no puedes asignarla a otra cosa. Siempre apunta a la instancia del objeto actual.

`this` no se puede usar en ninguna parte del código, como en los métodos estáticos. Solo es relevante dentro del contexto de un método de instancia o constructor. Los métodos estáticos pertenecen a la clase misma, no a una instancia específica, por lo que "esto" no tiene ningún significado allí.

Entonces, ¿tienes que usar "esto" cada vez que te refieres a un atributo o método, pase lo que pase? No necesariamente. Si no hay ambigüedad, a menudo puedes omitir "esto". Sin embargo, hay ocasiones en las que usar "esto" puede aclarar el código y evitar confusiones. Por ejemplo:```java
public class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;
    }
    
    public void introduce(Person other) {
        System.out.println("Hi " + other.name + ", I'm " + this.name);
    }
}
```Aquí, usar `this.name` deja claro que nos estamos refiriendo al `nombre` de la instancia actual de `Persona`, no a la `otra` `Persona`. 

A continuación se muestran algunas situaciones en las que "esto" es necesario:
- Para eliminar la ambigüedad entre variables locales y variables de instancia con el mismo nombre
- Pasar la instancia actual como argumento a un método.
- Para llamar a otro constructor desde dentro de un constructor

Hablando de constructores, no puedes usar "this" para llamar a un constructor desde cualquier lugar de mi clase. Sólo puedes usar `this` para llamar a otro constructor desde dentro de un constructor, y debe ser la primera declaración:```java
public class Person {
    private String name;
    private int age;
    
    public Person(String name) {
        this(name, 0);
    }
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```Esto es útil cuando tienes varios constructores y quieres evitar la duplicación de código.

Sin embargo, una regla es que si estás usando "this" para invocar otro constructor, debe ser la primera declaración en el constructor. Esta regla garantiza que se llame a otro constructor antes de ejecutar cualquier código en el constructor que contiene esta llamada, evitando el uso de campos no inicializados o la duplicación del código de inicialización. Por ejemplo, lo siguiente no se compilará:```java
public class Person {
    private String name;
    private int age;
    
    public Person(String name) {
        System.out.println("Person(String) Constructor Called");
        // The following line will cause a compilation error
        this(name, 0); // ERROR: Constructor call must be the first statement in a constructor
    }
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```Además, recuerde que "esto" no se refiere a la clase en sí. `this` se refiere a la instancia actual. Cada instancia obtiene su propia referencia "esta". No puede ser "nulo".

Esto también significa que "esto" se usa, por ejemplo, miembros. Los campos y métodos estáticos pertenecen a la clase misma, no a una instancia específica, por lo que "esto" no es aplicable.

Además, cuando usas "this" dentro de un método, te refieres a la instancia del objeto a la que pertenece el método, no al método en sí.

Finalmente, pasar `this` como argumento es útil cuando desea otorgar acceso a otro método a la instancia actual. Por ejemplo, podrías pasar `this` a un método de otra clase para que pueda volver a llamar al objeto de origen:```java
public class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;
    }
    
    public void introduceYourselfTo(IntroductionService service) {
        service.introduce(this);
    }
    
    public String getName() {
        return name;
    }
}

public class IntroductionService {
    public void introduce(Person person) {
        System.out.println("Hello, my name is " + person.getName());
    }
}
```En este ejemplo, tenemos dos clases: `Persona` y `IntroductionService`.

La clase `Persona` tiene un método llamado `introduceYourselfTo`, que toma un `IntroductionService` como parámetro. Dentro de este método, `this` (refiriéndose a la instancia actual de `Persona`) se pasa como argumento al método `introduce` de `IntroductionService`.

La clase `IntroductionService` tiene un método `introduce` que toma una `Persona` como parámetro. Este método puede luego acceder al método `getName()` de la `Persona` para imprimir la introducción.

Así es como puedes usar estas clases:```java
Person alice = new Person("Steve");
IntroductionService service = new IntroductionService();
alice.introduceYourselfTo(service);
```Y este es el resultado:```
Hello, my name is Steve
```### La referencia `super`

Entonces, la palabra clave `this` se usa para hacer referencia a la instancia actual de la clase. Pero, ¿qué sucede si desea hacer referencia a la superclase de la que hereda su clase actual? Ahí es donde entra en juego "súper".

La palabra clave `super` actúa como referencia a la clase principal (superclase) de la clase actual. Permite el acceso a los miembros de la superclase (campos, métodos y constructores).

El objetivo principal de "super" es diferenciar entre miembros de la superclase y miembros de la clase actual cuando tienen el mismo nombre. Al anteponer "super" al nombre de un miembro, especifica que desea utilizar la versión de la superclase de ese miembro, en lugar de la versión de la clase actual.

La sintaxis para usar "super" es sencilla:```java
super.memberName
```Aquí, `memberName` puede ser un campo, método o constructor de la superclase.

La anulación en Java es una característica que permite que una subclase o clase secundaria proporcione una implementación específica de un método que ya proporciona una de sus superclases o clases principales. 

Cuando anulas un método en una subclase, no estás borrando ni reemplazando el método original en la superclase. El método de la superclase todavía está ahí, pero cuando llamas al método en un objeto de la subclase, en su lugar se ejecuta la versión anulada en la subclase. Entonces, al anular un método en una subclase, es posible que desees llamar a la implementación original del método desde la superclase. 

En ese caso, puedes usar `super` para invocar la versión del método de la superclase:```java
@Override
public void someMethod() {
    super.someMethod(); // Calls the superclass's implementation
    // Additional code specific to the subclass
}
```Otro caso de uso común para "super" es cuando deseas invocar el constructor de la superclase desde el constructor de la clase actual. Al igual que con `this`, debes llamar a `super()` como primera declaración en el constructor:```java
public class SubClass extends SuperClass {
    public SubClass() {
        super(); // Invokes the superclass constructor
        // Other initialization code
    }
}
```De lo contrario, obtendrá un error de compilación. 

Si su superclase no tiene un constructor predeterminado (sin argumentos), deberá llamar explícitamente a un constructor parametrizado usando `super(argumentos)`. No puede utilizar "super" sin especificar los argumentos requeridos.

Considere este ejemplo:```java
// Superclass without a default constructor
public class Person {
    private String name;
    private int age;

    // Constructor that requires parameters
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter methods for name and age
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// Subclass that extends Person
public class Student extends Person {
    private String studentID;

    // Since Person does not have a default constructor, we must explicitly call a parameterized constructor
    public Student(String name, int age, String studentID) {
        super(name, age); // Calls the superclass constructor with arguments
        this.studentID = studentID;
    }

    // Getter method for studentID
    public String getStudentID() {
        return studentID;
    }
}
```En el constructor `Estudiante`, `super(nombre, edad);` se usa para llamar explícitamente al constructor parametrizado de la clase `Persona`. Esto es necesario porque "Persona" no tiene un constructor sin argumentos. Si se omitiera esta llamada `super`, el código no se compilaría, ya que Java intentaría llamar a un constructor predeterminado en la clase `Persona`, que no existe en este caso.

Ahora, quizás te preguntes: si uso "super", ¿eso significa que no puedo usar "this" en el mismo método? La respuesta es no. Puede utilizar tanto `this` como `super` en el mismo método, ya que tienen diferentes propósitos. `this` se refiere a la instancia actual, mientras que `super` se refiere a la superclase.

Sin embargo, es importante tener en cuenta que "super" no se puede utilizar para acceder directamente a miembros privados (campos o métodos) de la superclase. Solo se puede acceder a los miembros privados dentro de la misma clase. Si necesita acceder a ellos, tendrá que confiar en los métodos "públicos" o "protegidos" proporcionados por la superclase.

Finalmente, también vale la pena señalar que si bien "super" se usa principalmente para llamar a métodos o acceder a campos desde la clase principal inmediata, indirectamente permite la interacción con la jerarquía de herencia más amplia. En particular, si la clase principal inmediata hereda métodos de sus antepasados ​​(clases primarias y posteriores), "super" también puede acceder indirectamente a estos métodos. Esto se debe a que los métodos heredados de la clase principal, a los que `super` puede llamar, pueden llamar a métodos de sus antepasados ​​dentro de la cadena de herencia. Sin embargo, no es posible la invocación directa de métodos o el acceso a campos desde clases primarias o superiores, utilizando "super". Para acceder a dichos métodos directamente, normalmente confiaría en los métodos heredados que encapsulan esta funcionalidad dentro de su superclase inmediata.

Considere el siguiente ejemplo, que amplía el ejemplo anterior agregando una nueva clase, `GraduateStudent`, que hereda de `Student`, y una clase principal, `Human`, de la que `Person` hereda:```java
// Grandparent class
public class Human {
    private String nationality;

    public Human(String nationality) {
        this.nationality = nationality;
    }

    protected void sayHello() {
        System.out.println("Hello from Human!");
    }
}

// Parent class
public class Person extends Human {
    private String name;
    private int age;

    public Person(String name, int age, String nationality) {
        super(nationality); // Calls the Human constructor
        this.name = name;
        this.age = age;
    }

    // Overriding the sayHello method
    @Override
    protected void sayHello() {
        super.sayHello(); // Calls Human's sayHello
        System.out.println("Hello from Person!");
    }
}

// Current class
public class Student extends Person {
    private String studentID;

    public Student(String name, int age, String nationality, String studentID) {
        super(name, age, nationality); // Calls the Person constructor
        this.studentID = studentID;
    }

    // Overriding the sayHello method again
    @Override
    protected void sayHello() {
        super.sayHello(); // Calls Person's sayHello, which in turn calls Human's sayHello
        System.out.println("Hello from Student!");
    }
}

// New Subclass that extends Student
public class GraduateStudent extends Student {
    private String researchTopic;

    public GraduateStudent(String name, int age, String nationality, String studentID, String researchTopic) {
        super(name, age, nationality, studentID); // Calls the Student constructor
        this.researchTopic = researchTopic;
    }

    public void introduce() {
        super.sayHello(); // Calls Student's sayHello, which in turn calls Person's, and then Human's sayHello
        System.out.println("I am a graduate student working on " + researchTopic + ".");
    }
}

```En este ejemplo, la clase `GraduateStudent` usa `super.sayHello()` en su método `introduce`. Esto llama al método `sayHello` de la clase `Student`, que a su vez anula el método `sayHello` de `Person`. El método `sayHello` de la clase `Person` luego llama al método `sayHello` de `Human`. Esto demuestra cómo se puede usar "super" para acceder indirectamente a métodos en la cadena de herencia, desde la clase "Human" hasta la clase "GraduateStudent", aunque el acceso directo a los métodos de "Human" desde "GraduateStudent" usando "super" no es posible.

Ahora hablemos más sobre anulación y polimorfismo.



## Polimorfismo

### Introduciendo el polimorfismo
El polimorfismo es uno de los pilares de la programación orientada a objetos y es un concepto poderoso en Java. En términos simples, el polimorfismo le permite tratar objetos de diferentes subclases como si fueran objetos de la misma superclase. Es como tener un único control remoto que puede operar varios tipos de dispositivos, un televisor, un estéreo y un reproductor de DVD. Así como el control remoto envía señales a cada dispositivo que realiza diferentes funciones dependiendo del dispositivo con el que se comunica, en Java puede usar un único tipo de referencia para interactuar con objetos de diferentes clases, permitiéndoles realizar sus propios comportamientos únicos a través de una interfaz común.

Sin embargo, el polimorfismo no significa que los métodos puedan cambiar arbitrariamente su comportamiento. En cambio, permite a las subclases proporcionar sus propias implementaciones de métodos definidos en la superclase, un concepto conocido como anulación de métodos.

Como se mencionó anteriormente, cuando anulas un método en una subclase, no estás borrando ni reemplazando el método original en la superclase. El método de la superclase todavía está ahí, pero cuando llamas al método en un objeto de la subclase, en su lugar se ejecuta la versión anulada en la subclase. Es importante tener en cuenta que anular no es lo mismo que ocultar miembros, lo cual analizaremos más adelante.

Para anular correctamente un método, el método de la subclase debe tener lo mismo:
- Nombre
- Tipo de devolución
- Lista de parámetros

Como método en la superclase. He aquí un ejemplo:```java
class Animal {
    public void makeSound() {
        System.out.println("The animal makes a sound");
    }
}

class Pig extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Oink");
    }
}

class Duck extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Quack");
    }
}
```Y un diagrama para visualizar esta jerarquía:```
┌──────────────────────────────────────────┐
│            Animal makeSound()            │
└──────────────────────┬───────────────────┘
                       │
           ┌───────────┴─────────┐
           │                     │
┌──────────┴────────┐  ┌─────────┴─────────┐
│    Pig (Oink)     │  │   Duck (Quack)    │
└───────────────────┘  └───────────────────┘

```La clase `Animal` tiene un método llamado `makeSound()`. Las clases `Pig` y `Duck`, que extienden `Animal`, anulan el método `makeSound()` para proporcionar sus propias implementaciones. Ahora, veamos el polimorfismo en acción:```java
Animal animal1 = new Pig();
Animal animal2 = new Duck();

animal1.makeSound(); // Output: Oink
animal2.makeSound(); // Output: Quack
```Aquí, creamos dos variables de tipo `Animal`, pero les asignamos objetos de las clases `Pig` y `Duck`. Cuando llamamos al método `makeSound()` en cada variable, se llama al método anulado apropiado en la subclase respectiva. Éste es el poder del polimorfismo, la capacidad de tratar objetos de diferentes subclases como objetos de una superclase común.

Es importante comprender que anular no es lo mismo que sobrecargar. La sobrecarga se refiere a tener múltiples métodos con el mismo nombre pero diferentes listas de parámetros dentro de la misma clase. Anular, por otro lado, consiste en proporcionar una implementación diferente de un método en una subclase.

Otro error común es pensar que la anulación se aplica a todos los miembros de una clase, incluidas las variables. Sin embargo, eso no es cierto. La anulación se aplica específicamente a los métodos. Cuando declaras una variable con el mismo nombre en una subclase, en realidad estás ocultando la variable de la superclase, sin anularla.

Exploremos algunas reglas relacionadas con la anulación.

### Reglas primordiales
Hay varias reglas que debes seguir al anular métodos de una superclase:

**Regla #1: Firmas de métodos**  
La primera y más importante regla es que la firma del método debe coincidir exactamente entre la superclase y la subclase. Esto significa que el nombre, los parámetros y el tipo de retorno deben ser idénticos (con una excepción que discutiremos más adelante). No puedes cambiar los parámetros o el tipo de retorno como quieras:```java
// Superclass
class Cookie {
    // Define a method 'eat' in the superclass
    public String eat() {
        return "Eating a plain cookie";
    }
}

// Subclass
class ChocolateChipCookie extends Cookie {
    // Override the 'eat' method in the subclass
    @Override
    public String eat() {
        return "Eating a chocolate chip cookie";
    }
}
```En este ejemplo:
- La clase `Cookie` define un método llamado `eat` que devuelve una `String`.

- La clase `ChocolateChipCookie`, que extiende `Cookie`, anula el método `eat`. El método principal en `ChocolateChipCookie` tiene el mismo nombre, tipo de retorno y lista de parámetros (en este caso, ninguno) que el método en `Cookie`.

- Cuando una instancia de `ChocolateChipCookie` llama a `eat`, se ejecuta la versión anulada del método y se devuelve `"Comer una galleta con chispas de chocolate"`.

¿Por qué la firma del método tiene que permanecer igual? Bueno, piénselo como un contrato entre la superclase y la subclase. La superclase define un método específico que las subclases pueden anular si es necesario. Si cambias la firma, estás rompiendo ese contrato. El método de la subclase ya no sería una verdadera anulación del método de la superclase.

**Regla #2: Modificadores de acceso**  
Al anular un método, puede hacer que el modificador de acceso sea más indulgente, pero no más restrictivo. Por ejemplo, podría anular un método "protegido" en la superclase y hacerlo "público" en la subclase. Pero no puedes hacer lo contrario, como cambiar un método "público" a "privado":```java
// Superclass
class Cookie {
    // Define a method with 'protected' access modifier in the superclass
    protected String recipe() {
        return "Default cookie recipe";
    }
}

// Subclass
class ChocolateChipCookie extends Cookie {
    // Override the 'recipe' method in the subclass and change the access modifier to 'public'
    @Override
    public String recipe() {
        return "Chocolate chip cookie recipe";
    }
}
```En este ejemplo:
- La clase `Cookie` define un método `receta` con un modificador de acceso `protegido`. Esto significa que solo se puede acceder al método `recipe` dentro de su propia clase, subclases o dentro del mismo paquete.

- La clase `ChocolateChipCookie`, que extiende `Cookie`, anula el método `recipe`, cambiando el modificador de acceso del método principal a `public`, que es menos restrictivo que `protected`.

- Intentar acceder al método "receta" directamente desde una instancia de "Cookie" provocaría un error en tiempo de compilación, debido al control de acceso "protegido". Sin embargo, acceder al método `receta` a través de una instancia de `ChocolateChipCookie` es posible porque es `público`.

Esto muchas veces confunde a la gente. Piensan: "Dado que es mi subclase, ¿no debería poder limitar el acceso al método si quiero?" Sin embargo, esto va en contra de la idea de que una subclase siempre debería funcionar dondequiera que se utilice su superclase. Si restringes el método en la subclase, alteras esta compatibilidad.

**Regla #3: Excepciones marcadas**  
Revisaremos las excepciones con más detalle en un capítulo posterior, pero si el método de la superclase declara excepciones marcadas en su cláusula `throws`, el método anulado en la subclase solo puede declarar excepciones que sean iguales o más específicas. No puede agregar nuevas excepciones marcadas que no sean una subclase de las declaradas por el método de superclase:```java
class BakingException extends Exception {
    public BakingException(String message) {
        super(message);
    }
}

class OverBakingException extends BakingException {
    public OverBakingException(String message) {
        super(message);
    }
}

// Superclass
class Cookie {
    // Define a method that declares throwing a general BakingException
    public String bake() throws BakingException {
        return "Cookie is baked";
    }
}

// Subclass
class ChocolateChipCookie extends Cookie {
    // Override the 'bake' method, declaring a more specific exception, OverBakingException
    @Override
    public String bake() throws OverBakingException {
        return "Chocolate chip cookie is baked";
    }
}
```En este ejemplo:
- `BakingException` es una excepción marcada que representa un error de horneado general.

- `OverBakingException` es una excepción marcada más específica, que indica que la cookie se ha sobrecalentado y extiende `BakingException`.

- La clase `Cookie` tiene un método `bake` que declara que podría generar una `BakingException`.

- La clase `ChocolateChipCookie` anula el método `bake` y declara que podría generar una `OverBakingException`, que es una subclase de `BakingException`.

La gente suele pensar que pueden lanzar cualquier excepción marcada que quieran en un método anulado, especialmente si la superclase no declara ninguna. Pero ese no es el caso. Nuevamente, todo se reduce al contrato definido por el método de la superclase. La subclase no puede introducir repentinamente nuevas excepciones marcadas que la persona que llama no esperaba manejar.

**Regla n.º 4: tipos de retorno covariantes**  
Ésta es la única excepción a la regla sobre firmas de métodos. Se permite que un método anulado tenga un tipo de retorno covariante. Eso significa que el tipo de retorno puede ser una subclase del tipo de retorno declarado en el método de superclase. Sin embargo, no tiene total libertad para devolver cualquier cosa que esté vagamente relacionada.

Por ejemplo, si el método de la superclase devuelve un "Número", la subclase podría devolver un "Entero", ya que "Entero" es una subclase de "Número". Sin embargo, no puede devolver una "Cadena", a pesar de cualquier relación vaga percibida con el "Número" original. Los tipos de devolución necesitan esa relación jerárquica directa.

Aquí hay un ejemplo para ilustrar esta regla:```java
class Cookie {
    // A method in the superclass that returns an instance of Cookie
    public Cookie getCookie() {
        return new Cookie();
    }
}

class ChocolateChipCookie extends Cookie {
    // An overriding method with a covariant return type
    // It returns ChocolateChipCookie, a subclass of Cookie
    @Override
    public ChocolateChipCookie getCookie() {
        return new ChocolateChipCookie();
    }
}
```En este ejemplo:
- La clase `Cookie` tiene un método `getCookie` que devuelve una instancia de `Cookie`.

- La clase `ChocolateChipCookie` extiende `Cookie` y anula el método `getCookie`. El tipo de retorno del método anulado es "ChocolateChipCookie", que es una subclase de "Cookie". Este cambio en el tipo de retorno es un ejemplo del uso de tipos de retorno covariantes.

- Cuando se llama a `getCookie` en una instancia de `ChocolateChipCookie`, devuelve una instancia de `ChocolateChipCookie`, lo que demuestra el método anulado con un tipo de retorno covariante en acción.

Está bien.

¿Ha notado la anotación `@Override` en todos estos ejemplos?

La anotación `@Override` marca explícitamente los métodos que están destinados a anular un método de superclase. ¿Pero cuál es el punto de usarlo? ¿Es sólo por claridad o tiene un propósito real?

Si bien es cierto que `@Override` puede hacer que su código sea más legible al indicar claramente los métodos anulados, proporciona una protección contra errores accidentales. Considere este escenario:```java
class Cookie {
    public String recipe() {
        return "Default cookie recipe";
    }
}

class ChocolateChipCookie extends Cookie {
    @Override
    public String recipes() { // Oops, typo in the method name!
        return "Chocolate chip cookie recipe";
    }
}
```En este caso, la subclase tenía la intención de anular "receta", pero accidentalmente introdujo un error tipográfico y la nombró "recetas". Sin la anotación `@Override`, esto se compilaría bien. La subclase simplemente tendría dos métodos separados: la "receta" heredada y las "recetas" nuevas.

Pero con `@Override`, el compilador detectará el error y producirá un error, indicando que `recipes` no anula ningún método. La anotación obliga al compilador a verificar que el método realmente anula un método de superclase, proporcionando una capa adicional de seguridad.

Ahora bien, ¿qué sucede si redeclaras un método privado de la superclase en una subclase? ¿Se considera eso primordial? La respuesta es no. Los métodos privados no se heredan en absoluto, por lo que no hay nada que anular.

Si redeclaras un método privado en la subclase, es esencialmente un método completamente separado que casualmente tiene el mismo nombre. No interactúa con el método de la superclase de ninguna manera. Por ejemplo:```java
class Cookie {
    private String recipe() {
        return "Default cookie recipe";
    }
}

class ChocolateChipCookie extends Cookie {
    private String recipe() {
        return "Chocolate chip cookie recipe";
    }
}
```En este caso, "Cookie" y "ChocolateChipCookie" tienen cada una su propia "receta". Llamar a `recipe` en una instancia de `ChocolateChipCookie` siempre invocará la versión de subclase, nunca la de superclase.

Otra fuente de confusión es la diferencia entre ocultar y anular métodos estáticos. Cuando se redeclara un método "estático" en una subclase, se le llama ocultar, no anular. El método de la subclase oculta el método de la superclase, pero en realidad no lo anula.

La diferencia clave es que anular es un concepto de tiempo de ejecución, mientras que ocultar es un concepto de tiempo de compilación. Con la anulación, el método específico invocado está determinado por el tipo de objeto real en tiempo de ejecución. Pero con la ocultación, el método invocado está determinado por el tipo de referencia en tiempo de compilación.

Aquí hay un ejemplo para ilustrar esto:```java
class Cookie {
    public static String bake() {
        return "Cookie is baked";
    }
}

class ChocolateChipCookie extends Cookie {
    public static String bake() {
        return "Chocolate chip cookie is baked";
    }
}
```Ahora, considere el siguiente código:```java
Cookie obj1 = new Cookie();
System.out.println(obj1.bake());  // Output: "Cookie is baked"

ChocolateChipCookie obj2 = new ChocolateChipCookie();
System.out.println(obj2.bake());  // Output: "Chocolate chip cookie is baked"

Cookie obj3 = new ChocolateChipCookie();
System.out.println(obj3.bake());  // Output: "Cookie is baked"
```En el último caso, aunque `obj3` es en realidad una instancia de `ChocolateChipCookie` en tiempo de ejecución, el tipo de referencia es `Cookie`. Por lo tanto, invoca el método oculto `Cookie`, no el método anulado `ChocolateChipCookie`.

Al igual que los métodos estáticos, las variables se pueden ocultar en subclases. Si una subclase declara una variable con el mismo nombre que una variable de la superclase, oculta la variable de la superclase dentro del alcance de la subclase.

He aquí un ejemplo:```java
class Cookie {
    protected int size = 10;
}

class ChocolateChipCookie extends Cookie {
    private int size = 20;
}
```En este caso, la variable `tamaño` en `ChocolateChipCookie` oculta la variable `tamaño` de `Cookie`. Cualquier referencia al `tamaño` dentro de `ChocolateChipCookie` accederá a la variable de subclase, no a la de superclase.

Pero aquí está la parte complicada. La variable de superclase oculta no desaparece. Todavía está allí y se puede acceder a él a través de una referencia de superclase. Considere esto:```java
Cookie cookie = new ChocolateChipCookie();
System.out.println(cookie.size);  // Output: 10
```Aunque "cookie" es en realidad una instancia de "ChocolateChipCookie", la variable se declara como tipo "Cookie". Entonces accede a la variable oculta `Cookie`, no a la `ChocolateChipCookie`.

Esto puede generar mucha confusión y errores sutiles. En general, es mejor evitar ocultar variables por completo. Si necesita anular una variable de superclase, considere usar un método getter/setter, que se puede anular correctamente.

Finalmente (nunca mejor dicho), hablemos de la palabra clave "final". Cuando se aplica a un método, "final" evita que ese método sea anulado en subclases. Básicamente, bloquea el método, asegurando que su implementación permanezca constante en toda la jerarquía.

Un error común es pensar que las subclases no pueden acceder a los métodos "finales". Eso no es cierto. Las subclases aún pueden llamar y utilizar métodos "finales"; simplemente no pueden anularlos.

Por ejemplo:```java
class Cookie {
    public final void bake(int temp) {
        System.out.println("Baking at " + temp);
    }
}

class ChocolateChipCookie extends Cookie {
    // Attempting to override bake() will cause a compile error
    // @Override
    // public void bake(int temp) { ... }
    
    public void extras() {
        bake(350);  // Calling the final bake() method is allowed
    }
}
```El método `bake()` en `Cookie` es `final`, por lo que `ChocolateChipCookie` no puede anularlo. Pero aún puede llamar a `bake()` cuando sea necesario.

Entonces, ¿cuándo debería utilizar métodos "finales"? Solo cuando tenga una razón crítica para evitar la anulación. El uso excesivo de "final" puede hacer que su código sea rígido y difícil de ampliar. En la mayoría de los casos, es mejor dejar los métodos abiertos para su anulación, ya que promueve la flexibilidad y la reutilización.

### Accediendo a objetos Java
En el capítulo anterior, aprendiste que al declarar un campo o una variable, una cosa es el tipo de referencia y otra es el tipo de objeto.

Teniendo esto en cuenta, existen tres formas principales de acceder a un objeto en Java:
1. Usar una referencia del mismo tipo que el objeto.

2. Usar una referencia que sea una superclase del tipo de objeto. 

3. Usar una referencia que defina una interfaz que la clase del objeto implementa o hereda

Profundicemos en cada uno de estos con más detalle.

**Utilizando una referencia del mismo tipo que el objeto.**

La forma más sencilla de acceder a un objeto es mediante el uso de una variable de referencia que coincida exactamente con el tipo de objeto.

Considere esta clase:```java
class Dog {    
    public void bark() {
        System.out.println("Woof!");
    }
}
```Y este código:```java
Dog myDog = new Dog();
myDog.bark(); // Can access all public methods of Dog
```Aquí, `myDog` es una variable de referencia de tipo `Dog` y se refiere a un objeto `Dog`. Con esta configuración, podemos acceder a cualquier método o variable `pública` definida en la clase `Dog` directamente a través de la referencia `myDog`.

Si se pregunta si se produce polimorfismo cuando un tipo de referencia y un tipo de objeto son iguales, la respuesta es sí. Incluso con tipos coincidentes, el polimorfismo todavía está en juego bajo el capó. El tipo de referencia determina qué métodos puede llamar, pero el tipo de objeto real determina qué implementación de esos métodos se utiliza en tiempo de ejecución.

**Usando una Referencia que es una Superclase del Objeto.**

Las cosas se vuelven un poco más interesantes cuando incluimos la herencia en escena. En Java, es perfectamente válido tener una variable de referencia con un tipo que sea una superclase del tipo de objeto real.

Considere esta clase y su subclase:```java
class Animal {
    public void eat() {
        System.out.println("Animal is eating.");
    }
}

class Dog extends Animal {
    public void eat() {
        System.out.println("Dog is eating.");
    }
    
    public void bark() {
        System.out.println("Woof!");
    }
}
```Podemos tener algo como esto:```java
Animal myAnimal = new Dog();
```Aquí tenemos una referencia de tipo "Animal" que se refiere a un objeto "Perro". Dado que "Perro" extiende "Animal", esto está permitido. Pero ¿qué significa esto para acceder a la funcionalidad del objeto?

Cuando tiene una referencia de superclase a un objeto de subclase, puede acceder a cualquier método definido en la superclase, pero no a los métodos que sean exclusivos de la subclase. Entonces, en el ejemplo anterior, podríamos llamar a `myAnimal.eat()` ya que `eat()` está definido en `Animal`, pero no podríamos llamar a `myAnimal.bark()` ya que `bark()` solo está definido en `Dog`. El tipo de referencia lo restringe a los métodos que define el tipo. Sin embargo, Java nos ofrece una solución a esto: la conversión.

Si está seguro de que su referencia de superclase apunta a un objeto de subclase específico, puede convertir la referencia a ese tipo de subclase y luego llamar a los métodos de la subclase:```java
Dog myDog = (Dog) myAnimal; // Casting from Animal to Dog
myDog.bark(); // Now we can call Dog-specific methods
```El casting esencialmente dice: "Sé que esto parece ser un "animal", pero créanme, en realidad es un "perro". Por supuesto, debes tener cuidado, si intentas convertir a la subclase incorrecta, obtendrás una `ClassCastException` en tiempo de ejecución.

Continuaremos analizando la conversión en la siguiente sección, pero en resumen, las referencias de superclase le brindan flexibilidad (puede usar un "Perro" en cualquier lugar donde se espere un "Animal") pero restringen el acceso directo a la funcionalidad específica de la subclase. Este es un aspecto clave del polimorfismo en Java.

**Usando una referencia que define una interfaz que implementa el objeto.**

La tercera forma de acceder a un objeto en Java es a través de una referencia de interfaz. Si una clase implementa una interfaz, puede hacer referencia a instancias de esa clase utilizando una variable de referencia del tipo de interfaz.

Considere esta interfaz y sus implementaciones:```java
interface Pet {
    void play();
}

class Dog implements Pet {
    public void play() {
        System.out.println("Dog is playing!");
    }
    
    public void bark() {
        System.out.println("Woof!");
    }
}

class Cat implements Pet {
    public void play() {
        System.out.println("Cat is playing!");
    }
    
    public void meow() {
        System.out.println("Meow!");
    }
}
```De esta manera, podemos tener algo como esto:```java
Pet myPet = new Dog();
```En este ejemplo, `Dog` implementa la interfaz `Pet`, por lo que podemos crear una referencia `Pet` y apuntarla a un objeto `Dog`.

Ahora, podría estar pensando: ¿crear una referencia de interfaz a un objeto significa que solo puedo usar los métodos definidos en la interfaz? Y la respuesta es sí. Cuando tiene una referencia de interfaz, solo puede llamar directamente a los métodos que están definidos en esa interfaz, incluso si el objeto real tiene otros métodos disponibles.```java
myPet.play(); // Valid, play() is defined in Pet
myPet.bark(); // Not valid, bark() is not part of Pet
```Esto puede parecer limitante, pero en realidad es una característica poderosa. Al programar en una interfaz, puede escribir código más flexible y fácil de mantener. Puedes cambiar el tipo de objeto real (por ejemplo, de `Perro` a `Gato`) sin tener que cambiar ningún código que utilice la referencia de la interfaz:```java
Pet myPet = new Dog();
myPet.play(); // Output: Dog is playing!
        
myPet = new Cat();
myPet.play(); // Output: Cat is playing!
```El punto clave en este ejemplo es que a la referencia "myPet" no le importa si se trata de un "Perro" o un "Gato". Simplemente sabe que está trabajando con alguna "mascota". Podemos cambiar el tipo de objeto real de `Perro` a `Gato`, y el método `play` seguirá funcionando sin ningún cambio.

Pero, ¿qué sucede si necesita acceder a métodos que son específicos del tipo de objeto real? Al igual que con las referencias de superclase, puedes usar casting:```java
Dog myDog = (Dog) myPet; // Casting from Pet to Dog
myDog.bark(); // Now we can call Dog-specific methods
```Nuevamente, debe asegurarse de que la referencia de su interfaz realmente apunte a un objeto "Perro" antes de realizar esta conversión, o obtendrá una excepción de tiempo de ejecución.

Y recuerde, las interfaces no tienen instancias, no puede crear un objeto de un tipo de interfaz directamente. Sin embargo, se puede hacer referencia a cualquier objeto de una clase que implemente la interfaz utilizando el tipo de interfaz. En ese sentido, el objeto *es-una* forma del tipo de interfaz.

También vale la pena recordar que una sola clase puede implementar múltiples interfaces. Si una clase implementa múltiples interfaces, puede usar una referencia de cualquiera de esos tipos de interfaz para hacer referencia a instancias de la clase:```java
interface Trainable {
    void doTrick();
}

class Dog implements Pet, Trainable {
    // Implement methods from both interfaces
}

Pet myPet = new Dog();
Trainable myStudent = (Trainable) myPet;
```En este ejemplo, se puede hacer referencia a un único objeto "Perro" como "Mascota" y como "Entrenable", porque "Perro" implementa ambas interfaces.

Por tanto, las referencias de interfaz proporcionan una forma de escribir código más abstracto y flexible. Le permiten centrarse en un conjunto específico de comportamientos que un objeto puede realizar, independientemente de su tipo de clase real. Este es un principio fundamental del diseño orientado a objetos.

Una nota final: recuerde que las interfaces no forman parte de la jerarquía de herencia de un objeto. Son una construcción separada. Entonces, si bien un objeto "Perro" puede denominarse "Pet", una referencia a "Pet" no es una superclase de "Perro". Es un tipo distinto de relación.

### Tipo de fundición
Para entender el casting de tipos, puedes pensar en las variables como actores. Cada variable tiene un papel específico que desempeñar, determinado por su tipo de datos. Pero a veces, como en una película, una variable necesita asumir un nuevo rol temporalmente para adaptarse a las necesidades de una escena particular en su código. Aquí es donde entra en juego el casting tipográfico.

Para tipos primitivos, la conversión de tipos le permite asignar un valor de un tipo de datos primitivo a otro tipo. En el caso de los objetos, permite tratar un objeto de una clase como un objeto de otra clase, siempre que exista una relación de herencia entre las dos clases.

Entonces, ¿la conversión de un objeto cambia su tipo real? No exactamente. Cuando lanzas un objeto, no estás alterando su tipo subyacente, sino que simplemente lo estás tratando como un tipo diferente temporalmente para un contexto específico. Es como un actor que se disfraza para una escena. Debajo, siguen siendo la misma persona, pero desempeñan un papel diferente en ese momento. Una vez finalizada la conversión, la variable vuelve a su tipo original. Es como un actor que se quita el disfraz una vez terminada la escena. Han vuelto a ser ellos mismos.

Ahora bien, quizás te preguntes: ¿puedes convertir cualquier tipo en cualquier otro tipo? Después de todo, son sólo datos, ¿verdad? Bueno, no del todo. Java es un lenguaje fuertemente tipado, lo que significa que tiene reglas estrictas sobre compatibilidad de tipos. No se puede convertir arbitrariamente entre tipos no relacionados, como intentar convertir un "int" en una "Cadena". El compilador te dará un error si intentas hacer algo así.

Las reglas para la conversión de tipos en Java son las siguientes:

1. Convertir una referencia de un subtipo a un supertipo no requiere una conversión explícita.

2. Convertir una referencia de un supertipo a un subtipo requiere una conversión explícita.

3. En tiempo de ejecución, una conversión no válida de una referencia a un tipo incompatible da como resultado que se genere una `ClassCastException`. 

4. El compilador no permite conversiones a tipos no relacionados.

Analicémoslos uno por uno.

La primera regla dice que convertir una referencia de un subtipo a un supertipo no requiere una conversión explícita. Esto se conoce como upcasting. Si tiene una jerarquía de clases donde la clase "B" extiende la clase "A", puede asignar una referencia de tipo "B" a una variable de tipo "A" sin una conversión explícita:```java
class A {}
class B extends A {}

B b = new B();
A a = b; // upcasting, no explicit cast needed
```La conversión ascendente es segura porque una subclase siempre contiene todas las características de su superclase. Por lo tanto, tratar un objeto de subclase como un objeto de superclase nunca causará un problema.

La segunda regla dice que convertir una referencia de un supertipo a un subtipo requiere una conversión explícita. Esto se conoce como abatimiento. Si tiene una variable del supertipo y desea tratarla como subtipo, debe convertirla explícitamente:```java
A a = new B(); // upcasting
B b = (B) a; // downcasting, explicit cast needed
```El downcasting es necesario cuando se desea acceder a métodos o variables que son específicos de la subclase y no están disponibles en la superclase.

Sin embargo, abatirse conlleva un riesgo. ¿Qué pasa si el objeto al que se hace referencia no es en realidad una instancia de la subclase a la que estás intentando convertirlo? Esto nos lleva a la tercera regla.

En tiempo de ejecución, una conversión no válida de una referencia a un tipo incompatible da como resultado que se genere una `ClassCastException`:```java
A a = new A();
B b = (B) a; // Compiles but throws ClassCastException at runtime
```En este ejemplo, "a" se refiere a una instancia de la clase "A", no a la clase "B". Cuando intentamos convertirlo a `B`, se compila sin errores porque el compilador permite la posibilidad de que `a` pueda estar refiriéndose a un objeto `B`. Pero en tiempo de ejecución, cuando realmente se intenta la conversión, Java se da cuenta de que "a" no es en realidad una "B" y arroja una "ClassCastException".

Este es un punto importante: el lanzamiento no transforma mágicamente un objeto en algo que no es. Si intenta convertir un objeto a un tipo incompatible, se producirá una excepción de tiempo de ejecución. El casting explícito básicamente le dice al compilador: "Créame, sé lo que estoy haciendo". Pero si te equivocas, Java te lo hará saber en tiempo de ejecución.

Sin embargo, la cuarta regla establece que el compilador no permite conversiones a tipos no relacionados. Si intenta realizar una conversión entre clases que no están en la misma jerarquía de herencia, el compilador le dará un error:```java
class A {}
class C {}

A a = new A();
C c = (C) a; // Compilation error
```Las clases "A" y "C" no están relacionadas por herencia, por lo que el compilador sabe que es imposible que un objeto "A" sea alguna vez un objeto "C". Ni siquiera permitirá que este código se compile.

Entonces, si la conversión no funciona, ¿es un problema de tiempo de compilación o de tiempo de ejecución? Puede ser cualquiera, dependiendo de la situación. Si intenta convertir a un tipo no relacionado, se trata de un error en tiempo de compilación. Si intenta convertir a un tipo relacionado pero el objeto no es en realidad una instancia de ese tipo, es una excepción de tiempo de ejecución.

Ahora, podrías estar pensando, ¿no es peligroso todo este casting? ¿No pasa básicamente por el sistema de verificación de tipos de Java? No exactamente. El sistema de tipos de Java todavía está vigente y el compilador no le permitirá hacer nada demasiado inseguro. La conversión explícita es una forma de decirle al compilador que tiene conocimientos adicionales sobre el tipo de un objeto, pero aún así se verifica en tiempo de ejecución.

Dicho esto, generalmente es una buena idea evitar el uso excesivo de hechizos, especialmente el abatimiento. Si se siente muy abatido, podría ser una señal de que es necesario rediseñar su jerarquía de clases.

Entonces, ¿cuándo es realmente útil el casting? El upcasting es muy común y es una parte importante del polimorfismo en Java. Le permite tratar un tipo más específico como un tipo más general, lo cual es seguro y, a menudo, necesario.

Por ejemplo, digamos que tiene un método que toma un parámetro de tipo "Lista". Puede pasar una `ArrayList`, una `LinkedList` o cualquier otra subclase de `List` y funcionará bien gracias a la conversión ascendente.```java
void processNames(List<String> names) {
    // code here
}

ArrayList<String> nameList = new ArrayList<>();
processNames(nameList); // upcasting from ArrayList to List
```Downcasting es menos común y debe usarse con más moderación. Es necesario cuando tienes una referencia a una superclase pero necesitas acceder a métodos o variables que solo están disponibles en una subclase.```java
class Shape {
    void draw() { /* ... */ }
}

class Circle extends Shape {
    void drawCircle() { /* ... */ }
}

Shape shape = new Circle();
shape.draw(); // Fine, draw() is defined in Shape
((Circle)shape).drawCircle(); // Downcast to access drawCircle()
```En este caso, el abatimiento es seguro porque sabemos que "forma" en realidad se refiere a un objeto "Círculo". 

En resumen, la conversión de tipos en Java le permite tratar temporalmente un objeto como un tipo diferente, ya sea una superclase (upcasting) o una subclase (downcasting), siempre que exista una relación de herencia. Upcasting es seguro y común, mientras que downcasting requiere una conversión explícita y debe usarse con cuidado. El compilador busca conversiones no válidas a tipos no relacionados, mientras que las conversiones no válidas a tipos relacionados dan como resultado una excepción en tiempo de ejecución. Y recuerde siempre que, debajo del yeso, el objeto en sí no cambia, simplemente se ve a través de una lente diferente.

Pero para estar más seguro, puedes usar el operador `instanceof` para verificar el tipo antes de realizar la conversión. Hablemos de ello a continuación.

### El operador `instancia de`
En Java, el operador `instanceof` se utiliza para probar si un objeto es una instancia de una clase particular o si implementa una interfaz específica. Devuelve un valor "booleano": "verdadero" si el objeto es una instancia de la clase/interfaz, "falso" en caso contrario.

La sintaxis para usar `instanceof` es:```java
objectReference instanceof ClassName/InterfaceName  
```Por ejemplo:```java
Object obj = "Hello";
if(obj instanceof String) {
    System.out.println("obj is a String");
}
```Esto imprimirá `"obj es una cadena"` ya que el objeto al que hace referencia `obj` es una instancia de la clase `String`.

Es importante tener en cuenta que el uso de `instanceof` en realidad no cambia el objeto ni su tipo de ninguna manera. Simplemente compara el objeto con la clase o interfaz especificada y devuelve un resultado "booleano". `instanceof` no se puede usar con tipos primitivos como `int` o `double`, solo funciona con referencias a objetos.

Pasar la prueba `instanceof` para una clase indica que el objeto es una instancia de esa clase misma o de una de sus subclases. Todos los objetos en Java heredan de la clase `Object`, por lo que `instanceof Object` siempre devolverá `true`:```java
String str = "abc";
if(str instanceof Object) {
    System.out.println("This will always print");
}
```Una excepción a esta regla es cuando la referencia es "nula":```java
String str = null;
if(str instanceof String) {
    System.out.println("This will never be executed");
}
````instanceof` también puede verificar si un objeto implementa una interfaz particular. Si una clase implementa una interfaz directamente o mediante herencia, `instanceof` devolverá `true` para esa interfaz:```java
interface Trainable {
    void doTrick();
}

interface Pet extends Trainable {
    void play();
}

class Dog implements Pet {
    // Implement methods from both interfaces
}

Pet dog = new Dog();
if(dog instanceof Pet) {
    System.out.println("A Dog is a Pet");
}
if(dog instanceof Trainable) {
    System.out.println("A Dog is a Trainable");
}
```Ambas declaraciones de impresión se ejecutarán, ya que "Dog" implementa directamente "Pet" y "Pet" extiende "Trainable".

Un caso de uso común para `instanceof` es reducir de forma segura un objeto antes de llamar a un método específico de subclase. Recuerde, un downcast es cuando convierte una referencia de un tipo de superclase a un tipo de subclase:```java
Object obj = getSomeObject();
if(obj instanceof String) {
    String str = (String) obj;
    System.out.println(str.toUpperCase());
}
```Aquí primero verificamos si `obj` es en realidad una `String` antes de bajar y llamar al método `toUpperCase()` específico de `String`. Se requiere la conversión explícita `(String)` aunque ya hayamos confirmado el tipo con `instanceof`. 

Sin embargo, podemos utilizar la coincidencia de patrones para el operador `instanceof` para agilizar el proceso de verificación y conversión de tipos de objetos.

Entonces, en lugar de una conversión explícita, puedes combinar la verificación de tipo y la conversión en una sola operación usando la siguiente sintaxis:```java
if (objectReference instanceof ClassName variableName) {
    // Use variableName here, which is automatically cast to ClassName
}
```Esta sintaxis comprueba si `objectReference` es una instancia de `ClassName`. Si es así, `objectReference` se convierte en `ClassName` y el objeto de conversión se asigna a `variableName` dentro del alcance de la declaración `if`. Si la verificación falla, no se lanza ninguna excepción. El código dentro del bloque simplemente no se ejecuta y la variable de patrón permanece inaccesible. Esto elimina la necesidad de una conversión explícita y reduce el código repetitivo.

Aquí está el ejemplo abatido anterior reescrito para usar la coincidencia de patrones:```java
Object obj = getSomeObject();
if(obj instanceof String str) {
    System.out.println(str.toUpperCase());
}
```En este ejemplo, `str` es la variable de patrón que se convierte automáticamente en `String` si `obj` es una instancia de `String`. Las variables de patrón se inicializan implícitamente tras una coincidencia exitosa. No se requiere fundición adicional.

Las variables de patrón tienen un alcance limitado. Sólo son accesibles cuando se garantiza su coincidencia. `str` en el ejemplo anterior no está disponible fuera del bloque `if`. Esta elección de diseño garantiza que las variables de patrón solo se utilicen en contextos donde sus tipos estén garantizados, eliminando una fuente común de errores.

Sin embargo, esto no siempre significa que el alcance sea el bloque "if" donde están definidos. Cuando se utiliza la coincidencia de patrones con `instanceof`, si la condición es `true`, lo que significa que el objeto es una instancia del tipo especificado, la variable de patrón de hecho tiene alcance y es accesible dentro del bloque que sigue a la condición. Sin embargo, considere este ejemplo, donde la coincidencia de patrones se usa con una negación:```java
Object obj = getSomeObject();
if (!(obj instanceof String str)) {
    // The pattern variable str is NOT accessible here
    return "";
}
// But, because the execution only reaches this point if str IS an instance of String,
// the pattern variable str is accessible here.
return str.toUpperCase();
```En este ejemplo, la declaración `if` comprueba si `obj` no es una instancia de `String`. Si `obj` no es una `String`, el método devuelve `false` inmediatamente, y la variable de patrón `str` no es accesible dentro del bloque `if` porque la condición para su instanciación (`obj` siendo una instancia de `String`) es `false`.

Sin embargo, inmediatamente después de este bloque `if`, la ejecución del código continúa solo si `obj` es de hecho una instancia de `String`, lo que significa que `str` coincidió exitosamente y ahora es accesible y utilizable fuera de, pero directamente después, el bloque if que contiene la coincidencia de patrones. Este es un escenario específico donde el flujo del programa garantiza que se cree una instancia de la variable de patrón "str" ​​y se pueda utilizar de forma segura porque el método habría salido antes si la condición fuera "falsa".

También puedes usar una variable de patrón de esta manera:```java
Object obj = getSomeObject();
if(obj instanceof String str && str.length() > 3) {
    System.out.println(str.toUpperCase());
}
```Porque, al estar en cortocircuito el operador AND condicional (`&&`), el programa puede alcanzar la expresión `str.length() > 3` solo si la expresión `instanceof` devuelve `true`.

Sin embargo, no puede utilizar un operador OR (`||`):```java
Object obj = getSomeObject();
if(obj instanceof String str || str.length() > 3) { // Error
    System.out.println(str.toUpperCase());
}
```Esto resultará en un error porque la expresión `str.length() > 3` puede ejecutarse cuando `obj` no es una instancia de `String`, lo que lleva a un intento de acceder a `str` cuando es posible que no se haya inicializado.

Además, la coincidencia de patrones con `instanceof` está diseñada para un tipo a la vez. Simplifica el proceso para una verificación y conversión de un solo tipo, pero no se extiende a varios tipos simultáneamente:```java
Object obj = getSomeObject();

if (obj instanceof String str) {
    // obj is a String, use str here
    System.out.println("String length: " + str.length());
} else if (obj instanceof Integer intVal) {
    // obj is an Integer, use intVal here
    System.out.println("Integer value: " + intVal);
} else if (obj instanceof List<?> list) {
    // obj is a List, use list here
    System.out.println("List size: " + list.size());
}
```En este ejemplo, `obj` se compara con varios tipos: `String`, `Integer` y `List`. Dependiendo del tipo real de "obj", se ejecuta el bloque de código correspondiente. Dentro de cada bloque, el objeto `obj` se convierte automáticamente al tipo que se está verificando y puedes usar el objeto de conversión directamente sin una conversión explícita.

Este enfoque mantiene su código limpio y con seguridad de tipos, lo que permite un código más legible y fácil de mantener cuando se trata de múltiples tipos posibles para una única referencia de objeto.

En general, es una buena práctica utilizar "instancia de" con moderación y preferir el polimorfismo siempre que sea posible. Las comprobaciones frecuentes de "instancia de" pueden ser una señal de un diseño orientado a objetos deficiente. Pero tiene usos válidos para abatir de forma segura, código reflectante y algunas comparaciones de igualdad.

Finalmente, aquí hay otros dos datos clave sobre "instanceof":

- Las clases secundarias se consideran instancias de sus clases principales, pero las clases principales no se consideran instancias de sus clases secundarias.

- Puede comprobar las implementaciones de interfaces, pero no puede distinguir entre la implementación directa en una clase y la implementación heredada de una clase principal. 


## Encapsulación

### ¿Qué es la encapsulación?
La encapsulación es uno de los principios fundamentales de la programación orientada a objetos en Java. Implica agrupar datos (atributos) y métodos (comportamiento) que operan con esos datos dentro de una sola unidad (como una clase) y restringir el acceso al funcionamiento interno de la clase desde el exterior. 

La encapsulación en Java puede considerarse como una máquina expendedora. Así como interactúa con una máquina expendedora usando los botones provistos para seleccionar su refrigerio o bebida, sin necesidad de comprender o acceder a los mecanismos internos que realmente dispensan el artículo, la encapsulación le permite interactuar con un objeto a través de sus métodos públicos, mientras que el estado interno y los detalles de implementación permanecen ocultos y protegidos de interferencias externas.

El objetivo principal de la encapsulación es proteger los datos del acceso y modificación no autorizados, y separar la interfaz de una clase (cómo se puede utilizar) de la implementación (cómo funciona realmente internamente). Al encapsular el estado interno de un objeto, nos aseguramos de que el código externo no pueda ponerlo en un estado no válido o inconsistente.

Algunos programadores podrían preguntarse: "¿No puedo simplemente hacer que todo sea público para simplificar el proceso de codificación? ¿Por qué molestarse en ocultar los aspectos internos de la clase?". 

Si bien este enfoque puede parecer más simple a corto plazo, rápidamente conduce a un código inflexible, frágil y difícil de mantener. La encapsulación ayuda a gestionar la complejidad al reducir las interdependencias entre las diferentes partes de un programa. Cuando una clase está bien encapsulada, los cambios en su implementación interna no afectan el resto del código base, lo que permite un mantenimiento, refactorización y actualización más sencillos de la clase sin causar efectos dominó en todo el programa. 

Entonces, ¿cómo implementamos exactamente la encapsulación en Java? El mecanismo principal es mediante el uso de modificadores de acceso en los miembros de la clase. 

Recuerde, existen cuatro modificadores de acceso que determinan la visibilidad y accesibilidad de clases, campos y métodos:
- `privado`: Sólo accesible dentro de la misma clase.

- `default` (paquete-privado): Accesible dentro de la misma clase y desde cualquier otra clase en el mismo paquete. 

- `protected`: Accesible dentro de la misma clase, desde cualquier otra clase en el mismo paquete y desde subclases (incluso en paquetes diferentes).

- `público`: Accesible desde cualquier lugar.

Puede aplicar estos modificadores a clases, atributos y métodos de acuerdo con la siguiente tabla:

| Modificador de acceso | Clase/Interfaz | Atributo de clase | Método de clase | Atributo de interfaz | Método de interfaz |
|-----------------|-----------------|-----------------|--------------|---------------------|------------------|
| **público** | ✓ | ✓ | ✓ | ✓ | ✓ |
| **privado** |                 | ✓ | ✓|                     |                  |
| **protegido** |                 | ✓ | ✓ |                     |                  |
| **predeterminado** | ✓ | ✓ | ✓ | ✓ | ✓ |

Y aquí está el resumen de las reglas de modificadores de acceso:

| Modificador de acceso | Misma clase | Subclase (mismo paquete) | Subclase (paquete diferente) | Otra Clase (Mismo Paquete) | Otra Clase (Paquete Diferente) |
|-----------|------------|-------------------------|------------------------------|------------------------------|----------------------------------|
| **público** | ✓ | ✓ | ✓ | ✓ | ✓ |
| **privado** | ✓ |                         |                              |                              |                                   |
| **protegido** | ✓ | ✓ | ✓ | ✓ |                                   |
| **predeterminado** | ✓ | ✓ |                              | ✓ |                                   |

Para encapsular una clase, normalmente:
1. Declare los campos (variables de instancia) de la clase como "privados". Esto impide el acceso directo a los campos desde fuera de la clase.

2. Proporcione métodos getter "públicos" para recuperar los valores de los campos y métodos setter para modificarlos, si es necesario. Estos métodos proporcionan acceso controlado a los campos y permiten agregar validación, registro o cualquier otra lógica cuando se accede a los valores de los campos o se modifican.

A continuación se muestra un ejemplo de una clase `BankAccount` bien encapsulada:```java
public class BankAccount {
    private String accountNumber;
    private double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        } else if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        } else {
            balance -= amount;
        }
    }
}
```Y un diagrama para visualizarlo:```
┌─────────────────────────────────────────┐
│              BankAccount                │
├─────────────────────────────────────────┤
│ - accountNumber: String                 │
│ - balance: double                       │
├─────────────────────────────────────────┤
│ + getAccountNumber(): String            │
│ + getBalance(): double                  │
│ + deposit(amount: double): void         │
│ + withdraw(amount: double): boolean     │
└─────────────────────────────────────────┘
```En este ejemplo, los campos "número de cuenta" y "saldo" se declaran "privados", por lo que no se puede acceder a ellos ni modificarlos directamente desde fuera de la clase "CuentaBanca". Los métodos públicos `getAccountNumber()` y `getBalance()` permiten la recuperación controlada de estos valores de campo, mientras que los métodos `deposit()` y `withdraw()` permiten la modificación controlada del campo `balance` con lógica de validación adicional.

Ahora bien, quizás se pregunte: "Si uso captadores y definidores para todos mis campos, ¿eso significa automáticamente que mi clase está bien encapsulada?" 

No necesariamente. Si bien el uso de captadores y definidores es una forma común de encapsular campos, el simple hecho de tener estos métodos no garantiza una buena encapsulación. La encapsulación es algo más que ocultar datos. Se trata de garantizar que el estado interno de un objeto sea siempre válido y coherente. Los captadores y definidores son sólo una herramienta para lograrlo.

Por ejemplo, considere esta clase "Rectángulo":```java
public class Rectangle {
    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }
}
```Si bien esta clase utiliza captadores y definidores, en realidad no está bien encapsulada. El "ancho" y el "alto" se pueden establecer en cualquier valor, incluidos los números negativos, lo que no tiene sentido para un rectángulo. Un mejor enfoque sería validar la entrada en los establecedores:```java
public void setWidth(double width) {
    if (width > 0) {
        this.width = width;
    } else {
        throw new IllegalArgumentException("Width must be positive.");
    }
}

public void setHeight(double height) {
    if (height > 0) {
        this.height = height;
    } else {
        throw new IllegalArgumentException("Height must be positive.");
    }
}
```Al agregar esta lógica de validación, nos aseguramos de que el estado interno del objeto `Rectangle` sea siempre válido, logrando así una mejor encapsulación.

En resumen, la encapsulación consiste en gestionar la complejidad, proteger la integridad de los datos y separar la interfaz de una clase de su implementación. Se logra principalmente mediante el uso de modificadores de acceso, siendo los campos "privados" y los captadores y definidores "públicos" un patrón común. Sin embargo, una buena encapsulación va más allá del uso de captadores y definidores; requiere diseñar cuidadosamente la interfaz "pública" de una clase y garantizar que su estado interno sea siempre válido y consistente.

### Objetos inmutables
En la programación orientada a objetos, la inmutabilidad es la capacidad de crear objetos cuyo estado no se puede cambiar después de su creación. 

Los objetos inmutables en Java son como un libro impreso: una vez que se publica el contenido (o se crea el objeto), no se puede modificar. Así como no puedes cambiar las palabras en una página impresa sin crear un libro nuevo, no puedes modificar un objeto inmutable sin crear una nueva instancia con los cambios deseados.

Entonces, ¿qué hace que un objeto sea inmutable en Java? No es tan simple como omitir los métodos de establecimiento. Hay varios requisitos clave:

1. Marque la clase como "final" o haga que todos los constructores sean "privados". Esto evita la subclasificación, que de otro modo podría permitir que se cuele la mutabilidad. 

2. Marque todas las variables de instancia como "privadas" y "finales". Esto garantiza que el estado no se pueda modificar directamente desde fuera de la clase. ¿Pero es esto por sí solo suficiente para la inmutabilidad?

3. No defina ningún método de establecimiento. Cualquier método que modifique el estado, incluso indirectamente, rompe la inmutabilidad.

4. No permita que se modifiquen los objetos mutables a los que se hace referencia. Si su clase contiene una referencia a un objeto mutable (como una `Fecha` o una `Colección`), debe asegurarse de que esa referencia no pueda usarse para cambiar el estado del objeto.

5. Utilice un constructor para establecer todas las propiedades del objeto y haga una copia defensiva si es necesario. Una vez que se construye un objeto inmutable, su estado nunca puede cambiar. El constructor debe establecer las invariantes.

Profundicemos en cada uno de estos requisitos.

Marcar la clase como "final" evita que se subclase. Si permitiéramos la subclases, una subclase podría agregar estados mutables o anular métodos para que sean mutables, rompiendo el contrato de inmutabilidad.```java
public final class ImmutableExample {
    // class definition here
}
```Alternativamente, podemos hacer que los constructores sean "privados" y controlar la creación de instancias a través de métodos de fábrica:```java
public class ImmutableExample {
    private ImmutableExample() {
        // private constructor
    }
    
    public static ImmutableExample create() {
        return new ImmutableExample();
    }
}
```Pero hacer que una clase sea "final" no la hace automáticamente inmutable. También debemos asegurarnos de que todos sus campos sean "privados" y "finales":```java
public final class ImmutableExample {
    private final int value;
    
    public ImmutableExample(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
```Al hacer que los campos sean "privados", evitamos el acceso directo desde fuera de la clase. Y al hacerlos "finales", nos aseguramos de que solo se puedan configurar una vez, en el constructor.

Pero incluso con campos privados "finales", la inmutabilidad aún puede violarse si la clase tiene métodos que cambian de estado:```java
public final class NotActuallyImmutable {
    private final int value;
    
    public NotActuallyImmutable(int value) {
        this.value = value; 
    }
    
    public void setValue(int value) {
        this.value = value; // Mutates state - not okay!
    }
}
```Para ser verdaderamente inmutable, una clase no debe tener ningún método de establecimiento ni ningún otro método que cambie sus campos después de la construcción.

Sin embargo, la inmutabilidad va más allá del estado inmediato del objeto. El estado de un objeto inmutable incluye el estado de cualquier otro objeto al que tenga referencias.

Considere esta clase:```java
public final class NotImmutable {
    private final Date start;
    
    public NotImmutable(Date start) {
        this.start = start;
    }
    
    public Date getStart() {
        return start;
    }
}
```A primera vista, puede parecer inmutable, el campo de inicio es "privado" y "final", y no hay definidores. Pero la clase "Date" es mutable. Alguien podría hacer esto:```java
NotImmutable example = new NotImmutable(new Date());
example.getStart().setTime(0); // Mutates the internal state of example!
```Para solucionar este problema, necesitamos hacer una copia defensiva de la "Fecha" en el constructor:```java
public final class ActuallyImmutable {
    private final Date start;
    
    public ActuallyImmutable(Date start) {
        this.start = new Date(start.getTime()); // Defensive copy
    }
    
    public Date getStart() {
        return new Date(start.getTime()); // Defensive copy
    }
}
```Ahora el estado de la instancia "ActuallyImmutable" no se puede cambiar mediante la referencia que contiene.

El mismo principio se aplica a las colecciones y matrices: si una clase inmutable contiene una referencia a una colección o matriz mutable, debe copiarla de manera defensiva y no proporcionar ninguna forma de modificar la colección interna.

El uso adecuado de los constructores también es clave para la inmutabilidad. El estado de un objeto inmutable debe estar completamente definido por los argumentos pasados ​​a su constructor. Y el constructor debe establecer todas las invariantes del objeto.

Esto significa que una clase inmutable no debería tener un constructor sin argumentos, porque entonces su estado no estaría completamente definido al final de la construcción. Todas las propiedades deben establecerse mediante argumentos del constructor.

Aquí hay un ejemplo de una clase inmutable con una colección:```java
public final class ImmutableCollection {
    private final List<String> strings;
    
    public ImmutableCollection(List<String> strings) {
        this.strings = List.copyOf(strings); // Immutable copy
    }
    
    public List<String> getStrings() {
        return strings;
    }
}
```Siguiendo estas reglas, haciendo que la clase y los campos sean finales, no proporcionando métodos mutadores, copiando de manera defensiva componentes mutables y configurando todos los estados en el constructor, podemos crear objetos verdaderamente inmutables en Java.

Los objetos inmutables tienen muchas ventajas, especialmente en contextos concurrentes. Debido a que su estado nunca cambia, son inherentemente seguros para subprocesos. Se pueden compartir libremente entre subprocesos sin sincronización.

También es más sencillo razonar sobre ellos, porque sabes que su estado siempre será el mismo. Y pueden servir como bloques de construcción para estructuras más complejas seguras para subprocesos.

Sin embargo, la inmutabilidad conlleva algunos costos. Los objetos inmutables pueden ser más costosos de crear, porque a menudo requieren la realización de copias defensivas. Y si necesita realizar algún cambio, debe crear una nueva instancia, lo que puede resultar costoso para objetos grandes.


## Puntos clave

- El alcance de la variable se refiere a la visibilidad y accesibilidad de una variable en el código. Los cuatro ámbitos principales en Java son variables locales, parámetros de método, campos (variables de instancia) y variables de clase (campos estáticos).

- Las variables locales se declaran dentro de un método o bloque y solo son accesibles dentro de ese bloque. Entran en el alcance en su declaración y salen del alcance al final del bloque adjunto.

- Los parámetros del método también se consideran variables locales, con un alcance que cubre todo el cuerpo del método. Entran en el ámbito cuando se llama al método y salen del ámbito cuando se completa el método.

- Los campos, o variables de instancia, son variables declaradas a nivel de clase. Entran en el alcance cuando se crea una instancia del objeto y permanecen en el alcance mientras el objeto esté en la memoria.

- Las variables de clase, o campos estáticos, son variables estáticas declaradas a nivel de clase. Entran en el ámbito cuando se carga la clase y permanecen en el ámbito hasta que finaliza el programa.

- Los campos obtienen automáticamente valores predeterminados si no se inicializan explícitamente, mientras que las variables locales deben inicializarse explícitamente antes de su uso.

- La palabra clave `var` permite la inferencia de tipos de variables locales. El compilador infiere el tipo basándose en la expresión del inicializador.

- La herencia permite que una nueva clase se base en una clase existente, heredando sus atributos y métodos. La palabra clave "extiende" se utiliza para crear una subclase.

- No se pueden crear instancias de clases abstractas y están destinadas a ser subclasificadas. Pueden contener métodos abstractos, que no tienen implementación en la clase abstracta y deben ser implementados por subclases concretas.

- Las interfaces definen un contrato de métodos que una clase debe implementar. La palabra clave `implements` se utiliza para implementar una interfaz. Una clase puede implementar múltiples interfaces.

- Las clases selladas restringen qué otras clases pueden ampliarlas. Las subclases permitidas se especifican mediante la palabra clave "permisos". Las subclases de una clase sellada deben declararse "finales", "selladas" o "no selladas".

- La palabra clave `this` es una referencia a la instancia actual de una clase. Se utiliza para eliminar la ambigüedad entre variables locales y variables de instancia, para pasar la instancia actual como argumento de método y para llamar a otro constructor desde dentro de un constructor.

- La palabra clave `super` es una referencia a la clase principal (superclase) de la clase actual. Se utiliza para acceder a los miembros de la superclase e invocar el constructor de la superclase desde el constructor de la subclase.

- El polimorfismo permite tratar objetos de diferentes subclases como si fueran objetos de la misma superclase.

- La anulación de métodos es un concepto clave en polimorfismo, donde una subclase proporciona su propia implementación de un método definido en la superclase.

- Para anular correctamente un método, el método de la subclase debe tener el mismo nombre, tipo de retorno y lista de parámetros que el método de la superclase.

- Al anular métodos, puede hacer que el modificador de acceso sea más indulgente en la subclase, pero no más restrictivo. El método anulado también solo puede declarar excepciones que sean iguales o más específicas que las declaradas porel método de la superclase.

- Se permite que un método anulado tenga un tipo de retorno covariante, lo que significa que el tipo de retorno puede ser una subclase del tipo de retorno declarado en el método de superclase.

- La anotación `@Override` marca explícitamente los métodos que están destinados a anular un método de superclase y proporciona una protección contra errores accidentales.

- Redeclarar un método privado de la superclase en una subclase no se considera anulado. Los métodos privados no se heredan.

- Redeclarar un método estático en una subclase se llama ocultar, no anular. El método de la subclase oculta el método de la superclase, pero en realidad no lo anula.

- Las variables también se pueden ocultar en subclases si una subclase declara una variable con el mismo nombre que una variable de la superclase.

- Hay tres formas principales de acceder a un objeto en Java: usando una referencia con el mismo tipo que el objeto, usando una referencia que es una superclase del tipo del objeto y usando una referencia que define una interfaz que la clase del objeto implementa o hereda.

- La conversión de tipos le permite asignar un valor de un tipo de datos primitivo a otro tipo o tratar un objeto de una clase como un objeto de otra clase, siempre que exista una relación de herencia entre las dos clases.

- Convertir una referencia de un subtipo a un supertipo (upcasting) no requiere una conversión explícita, mientras que convertir una referencia de un supertipo a un subtipo (downcasting) requiere una conversión explícita.

- El operador `instanceof` se utiliza para probar si un objeto es una instancia de una clase particular o implementa una interfaz específica. Devuelve un valor "booleano".

- La coincidencia de patrones para el operador `instanceof` le permite combinar la verificación de tipo y la conversión en una sola operación, lo que reduce el código repetitivo.


## Preguntas de práctica

**1. ¿Cuál es el resultado de compilar y ejecutar el siguiente código?**```java
void myMethod() {
    int x = 1;
    if (x > 0) { 
        int y = 2;
        System.out.println(x + y);
    }
    System.out.println(x);
    System.out.println(y);
}
```**A)** El código se compila y genera "3" seguido de "1".  
**B)** El código compila y genera "3" seguido de "1" y un valor indefinido para "y".  
**C)** El código no se compila porque se accede a `y` fuera de su alcance.  
**D)** El código se compila pero genera una excepción de tiempo de ejecución al intentar imprimir `y`.


**2. ¿Cuáles de las siguientes declaraciones de variables son válidas? (Elija todas las que correspondan).**

**A)** `doble x, doble y;`  
**B)** `int i = 0, Cadena s = "hola";`  
**C)** `flotante f1 = 3.14f, f2 = 6.28f;`  
**D)** `char a = 'A', b, c = 'C';`  


**3. ¿Cuáles de las siguientes afirmaciones son verdaderas con respecto al uso de `var` en Java? (Elija todas las que correspondan).**

**A)** `var` se puede utilizar para declarar tanto variables locales dentro de métodos como variables de instancia dentro de clases.  
**B)** El uso de `var` está restringido a variables locales dentro de métodos, constructores o bloques inicializadores.  
**C)** `var` se puede utilizar para declarar parámetros de método.  
**D)** `var` mejora la legibilidad al inferir tipos que están claros a partir del contexto, pero no está permitido en las firmas de métodos para mantener la claridad.  
**E)** `var` se puede utilizar para declarar variables de clase (estáticas).


**4. ¿Cuál de las siguientes afirmaciones describe correctamente el uso de la herencia en Java? (Elija todas las que correspondan).**

**A)** Las subclases solo pueden acceder directamente a los miembros "protegidos" y "públicos" de su superclase.  
**B)** En Java, una clase puede extender varias clases para lograr herencia múltiple.  
**C)** La palabra clave `extends` se usa en Java para crear una subclase que hereda de una superclase.  
**D)** Una subclase en Java puede acceder directamente a miembros "privados" de su superclase.


**5. Considere el siguiente fragmento de código:**```java
abstract class Animal {
    abstract void eat();
}

class Dog extends Animal {
    void eat() {
        System.out.println("Dog eats");
    }
}

class Cat extends Animal {
    void eat() {
        System.out.println("Cat eats");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal myAnimal = new Dog();
        myAnimal.eat();
    }
}
```¿Cuál de las siguientes afirmaciones es cierta con respecto al código anterior? Elija todo lo que corresponda.

**A)** El código compilará e imprimirá `"El perro come"` cuando se ejecute.  
**B)** Se puede crear una instancia de la clase `Animal`.  
**C)** Eliminar el método `eat` de la clase `Dog` provocará un error de compilación.  
**D)** La clase `Cat` es necesaria para que el código se compile y ejecute.


**6. Considere las siguientes interfaces:**```java
interface Walkable {
    int distance = 10;
    void walk();
}

interface Runnable {
    void run();
    default void getSpeed() {
        System.out.println("Default speed");
    }
}

class Person implements Walkable, Runnable {
    public void walk() {
        System.out.println("Walking...");
    }
    public void run() {
        System.out.println("Running...");
    }
}
```¿Cuál de las siguientes afirmaciones es verdadera?

**A)** La clase `Persona` debe anular el método `getSpeed`.  
**B)** La variable `distance` en la interfaz `Walkable` es implícitamente `pública`, `estática` y `final`.  
**C)** Un objeto `Persona` puede llamar al método `getSpeed` sin ninguna implementación en la clase `Persona`.  
**D)** La interfaz `Runnable` provoca un error de compilación debido a un conflicto de nombres con `java.lang.Runnable`.


**7. Considere el siguiente fragmento de código relacionado con clases selladas:**```java
sealed abstract class Shape permits Circle, Square {
    abstract double area();
}

final class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}

non-sealed class Square extends Shape {
    private final double side;

    Square(double side) {
        this.side = side;
    }

    public double area() {
        return side * side;
    }
}

public class TestShapes {
    public static void main(String[] args) {
        Shape shape = new Circle(10);
        System.out.println("Area: " + shape.area());
    }
}
```¿Cuál de las siguientes afirmaciones es verdadera?

**A)** La clase `Shape` está definida correctamente como una clase sellada, lo que permite que solo clases específicas la extiendan.  
**B)** La clase `Cuadrado` no extiende correctamente la clase `Forma` porque no está marcada como `final`.  
**C)** La clase `Circle` puede ampliarse aún más con otras clases.  
**D)** El método `area` en la clase `Shape` debe proporcionar una implementación predeterminada.


**8. Considere la siguiente clase:**```java
public class Widget {
    private int size;

    public Widget() {
        this(10); // Line 5
    }

    public Widget(int size) {
        this.size = size;
    }

    public void resize(int size) {
        if (size > this.size) {
            this.size = size; // Line 14
            updateWidget();
        }
    }

    private void updateWidget() {
        System.out.println("Widget updated to size " + this.size);
    }

    public static void main(String[] args) {
        Widget widget = new Widget();
        widget.resize(15);
    }
}
```En la línea 114, ¿qué representa la palabra clave "this" en el contexto de la clase "Widget"?

**A)** Una referencia al contexto "estático" de la clase, que permite el acceso a métodos y campos estáticos.  
**B)** Una variable especial que almacena el valor de retorno de un método.  
**C)** Una palabra clave opcional que siempre se puede omitir sin afectar la funcionalidad del código.  
**D)** Una referencia al objeto actual, cuya variable de instancia se está llamando.


**9. Considere las siguientes clases:**```java
class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    protected void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    protected void eat() {
        super.eat();
        System.out.println(name + " (Dog) eats");
    }
}

public class TestAnimal {
    public static void main(String[] args) {
        Animal myDog = new Dog("Buddy");
        myDog.eat();
    }
}
```¿Cuáles de las siguientes afirmaciones son verdaderas con respecto al uso de "super" en el código anterior? (Elija todas las que correspondan).

**A)** La palabra clave `super` se usa en el constructor `Dog` para llamar al constructor de superclase.  
**B)** El método `eat` en la clase `Dog` usa `super` para invocar el método `eat` de la superclase.  
**C)** Eliminar la llamada `super.eat();` en el método `eat` de la clase `Dog` evitará que la clase `Dog` se compile.  
**D)** La palabra clave `super` se puede utilizar para acceder a métodos `estáticos` desde la superclase.



**10. Considere las siguientes clases:**```java
class Vehicle {
    public void drive(int speed) {
        System.out.println("Vehicle driving at speed: " + speed);
    }
}

class Car extends Vehicle {
    @Override
    public void drive(long speed) {
        System.out.println("Car driving at speed: " + speed);
    }
}

public class TestDrive {
    public static void main(String[] args) {
        Vehicle myCar = new Car();
        myCar.drive(60);
    }
}
```¿Cuál es el resultado de compilar y ejecutar el código anterior?

**A)** Compila e imprime `"Coche conduciendo a velocidad: 60"`.  
**B)** No se compila porque el método `drive` no se puede llamar usando una referencia `Vehicle`.  
**C)** No se compila porque el método `drive` de la clase `Car` no anula correctamente el método `drive` de la clase `Vehicle`.  
**D)** Compila e imprime `"Vehículo conduciendo a velocidad: 60"` porque el método `drive` en la clase `Car` es una sobrecarga, no una anulación.


**11. Considere el siguiente fragmento de código:**```java
class Fruit {
    public void flavor() {
        System.out.println("Fruit flavor");
    }
}

class Apple extends Fruit {
    @Override
    public void flavor() {
        System.out.println("Apple flavor");
    }

    public void color() {
        System.out.println("Red");
    }
}

public class TestFruit {
    public static void main(String[] args) {
        Fruit myFruit = new Apple();
        myFruit.flavor();
        // myFruit.color();
    }
}
```Si la línea comentada `// myFruit.color();` no está comentada, ¿cuál será el resultado de compilar y ejecutar el código anterior?

**A)** Compila e imprime `"Sabor Apple"` seguido de `"Rojo"`.  
**B)** Compila e imprime `"Sabor a fruta"`.  
**C)** Se compila pero arroja una excepción de tiempo de ejecución al intentar llamar a `color()`.  
**D)** No se compila porque `Apple` no es un tipo válido de `Fruit`.  
**E)** No compila porque el método `color` no está definido en la clase `Fruit`.


**12. Considere el siguiente fragmento de código:**```java
class Animal {}

class Dog extends Animal {
    public void bark() {
        System.out.println("Woof");
    }
}

class Cat extends Animal {
    public void meow() {
        System.out.println("Meow");
    }
}

public class TestCasting {
    public static void main(String[] args) {
        Animal animal = new Dog();
        ((Dog)animal).bark();

        Animal anotherAnimal = new Animal();
        // Line 1
    }
}
```¿Cuál de las siguientes líneas de código, si se inserta de forma independiente en la Línea 1, se compilará sin causar una excepción en tiempo de ejecución? (Elija todas las que correspondan).

**A)** `((Perro)otroAnimal).ladrar();`  
**B)** `si (otra instancia de Animal de Perro) ((Perro)otro Animal).bark();`  
**C)** `((Gato)animal).miau();`  
**D)** `if (otra instancia Animal de Gato) ((Gato)otro Animal).meow();`


**13. Considere el siguiente fragmento de código:**```java
public class AdvancedPatternMatching {
    public static void process(Object input) {
        if (input instanceof String s && s.contains("Java")) {
            System.out.println("String with Java: " + s);
        } else if (input instanceof Integer i && i > 10) {
            System.out.println("Integer greater than 10: " + i);
        }
    }

    public static void main(String[] args) {
        process("Hello Java!");
        process(15);
        process("Just a string");
        process(5);
    }
}
```Dado el código anterior, ¿qué declaración describe con precisión el resultado de su ejecución?

**A)** Compila e imprime `"Cadena con Java: ¡Hola Java!"` seguido de `"Entero mayor que 10: 15"`.  
**B)** Compila pero solo imprime `"Cadena con Java: ¡Hola Java!"` porque los números enteros no son compatibles con la coincidencia de patrones.  
**C)** No se compila porque la coincidencia de patrones en `instanceof` no se puede combinar con operadores lógicos como `&&`.  
**D)** Compila pero imprime las cuatro líneas debido al uso incorrecto de la coincidencia de patrones que siempre se evalúa como "verdadero".


**14. Considere las prácticas de encapsulación en la siguiente estructura de clases:**```java
package store;

public class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        setName(name);
        setPrice(price);
        setStock(stock);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public int getStock() {
        return stock;
    }

    private void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }
}
```¿Qué afirmación es cierta con respecto a la encapsulación de la clase "Producto"?

**A)** Hacer que los métodos `setName`, `setPrice` y `setStock` sean `públicos` mejoraría la encapsulación de la clase.  
**B)** La clase no está encapsulada porque los campos de la clase `Product` son `privados`.  
**C)** La encapsulación se debilita porque el constructor permite la configuración directa de campos sin validación.  
**D)** La clase `Product` debe tener captadores privados de paquetes para mejorar la encapsulación.  
**E)** La clase está encapsulada adecuadamente al proporcionar captadores "públicos" para todos los campos y configuradores "privados" con validación, lo que garantiza el control sobre el estado de sus objetos.


**15. Considere las siguientes clases definidas en el mismo paquete:**```java
class Account {
    private double balance;
    
    Account(double initialBalance) {
        if (initialBalance > 0) {
            balance = initialBalance;
        }
    }
    
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    protected double getBalance() {
        return balance;
    }
}

public class SavingsAccount extends Account {
    private double interestRate;
    
    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        this.interestRate = interestRate;
    }
    
    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }
}
```¿Qué afirmación(es) sobre los principios de encapsulación y el uso de modificadores de acceso describen con precisión el código anterior? Elija todo lo que corresponda.

**A)** La clase `CuentaDeAhorros` no puede acceder al campo `saldo` directamente debido a su modificador de acceso `privado` en la clase `Cuenta`.  
**B)** El método `getBalance` debe ser `público` para permitir que `SavingsAccount` acceda al saldo de la cuenta.  
**C)** El método `depósito` en la clase `Cuenta` debe marcarse como `final` para evitar su anulación.  
**D)** El campo `interestRate` en la clase `SavingsAccount` viola los principios de encapsulación al ser "privado".  
**E)** La clase `Cuenta` encapsula correctamente el campo `saldo`, y `SavingsAccount` se adhiere a la encapsulación accediendo a `saldo` a través de `getBalance` y `deposit`.


**16. Considere la siguiente clase:**```java
public final class Contact {
    private final String name;
    private final String email;
    private final Address address;

    public Contact(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = new Address(address.getStreet(), address.getCity());
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return new Address(address.getStreet(), address.getCity());
    }

    public static class Address {
        private final String street;
        private final String city;

        public Address(String street, String city) {
            this.street = street;
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }
    }
}
```Dada la implementación anterior, ¿qué declaración describe con precisión el objeto "Contacto"?

**A)** El objeto `Contacto` es mutable porque la clase `Dirección` no es `final`.  
**B)** El objeto `Contacto` es inmutable, pero sólo porque no proporciona configuradores.  
**C)** El objeto "Contacto" es inmutable y previene adecuadamente la fuga de un estado interno mutable mediante copia defensiva.  
**D)** El objeto `Contact` es mutable porque el objeto `Address` se puede cambiar mediante el método `getAddress`.  
**E)** El objeto `Contacto` es inmutable pero no impide el acceso a su estado interno mutable.