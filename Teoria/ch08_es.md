-"None"
layout: chapter

title: "Capítulo OCHO"
subtitle: "Interfaces funcionales y expresiones Lambda"
exam_objectives:
  - "Utilice objetos Java y flujos primitivos, incluidas expresiones lambda que implementan interfaces funcionales, para crear, filtrar, transformar, procesar y ordenar datos."

previous_link: "/ch07.html"
previous_title: "Manejo de errores y excepciones"
next_link: "/ch09.html"
next_title: "Corrientes"
answers_link: "/ch08a.html"
-"None"## Contenido del capítulo

- [Interfaces funcionales](#interfaces-funcionales)
    - [La anotación `@FunctionalInterface`](#la-anotación-de-interfaz-funcional)
    - [Reglas para definir una interfaz funcional](#reglas-para-definir-una-interfaz-funcional)
- [Expresiones Lambda](#expresiones-lambda)
    - [Sintaxis de una expresión Lambda](#sintaxis-de-una-expresión-lambda)
    - [Expresiones Lambda y clases anónimas](#expresiones-lambda-y-clases-anónimas)
- [Interfaces Lambda integradas en Java](#interfaces-lambda integradas en Java)
    - [`Predicado`](#predicado)
    - [`Consumidor`](#consumidor)
    - [`Función`](#función)
    - [`Proveedor`](#proveedor)
    - [`OperadorUnario`](#operadorunario)
    - [`BiPredicado`](#bipredicado)
    - [`BiConsumidor`](#biconsumidor)
    - [`BiFunción`](#bifunción)
    - [`OperadorBinario`](#operadorbinario)
    - [Interfaces funcionales específicas de primitivas](#interfaces-funcionales-específicas-primitivas)
- [Referencias de métodos](#referencias-métodos)
    - [Métodos estáticos](#métodos-estáticos)
    - [Método de instancia de un objeto de un tipo particular](#método-de-instancia-de-un-objeto-de-un-tipo-particular)
    - [Método de instancia de un objeto existente](#método-de-instancia-de-un-objeto-existente)
    - [Constructor](#constructor)
- [Puntos clave](#puntos clave)
- [Preguntas de práctica](#preguntas-de-practica)

---

## Interfaces funcionales
Java 8 nos trajo expresiones lambda, una nueva característica que tiene como objetivo simplificar el desarrollo adoptando un enfoque de programación más funcional. Pero para que esto funcione, Java también introdujo el concepto de interfaces funcionales.

Una interfaz funcional es una interfaz que contiene solo un método abstracto. Pueden contener uno o más métodos predeterminados o métodos estáticos, pero sólo puede haber un método abstracto.

A primera vista, podría pensar que usar interfaces funcionales no es muy diferente a usar clases y objetos normales. Después de todo, hemos podido definir interfaces con un único método durante mucho tiempo. Pero la diferencia clave es cómo permiten el uso de expresiones lambda.

Las expresiones Lambda le permiten tratar la funcionalidad como argumentos de método o el código como datos. En lugar de definir una clase que implemente una interfaz de método único, puede pasar directamente una expresión lambda como una instancia de una interfaz funcional, lo que permite un código más limpio y conciso.```java
public interface MyInterface {
    public void myMethod();
}

MyInterface ref = () -> System.out.println("Hello World!"); 
```En este ejemplo, la expresión lambda `() -> System.out.println("¡Hola mundo!")` se trata como una instancia de la interfaz funcional `MyInterface`. Estamos asignando un bloque de código a la variable `ref`.

### La anotación `@FunctionalInterface`
Java 8 también introdujo la anotación `@FunctionalInterface`, que se utiliza para indicar que una interfaz está destinada a ser una interfaz funcional. Es una especie de *pista* para el compilador de que desea que esta interfaz cumpla con las reglas de una interfaz funcional:```java
@FunctionalInterface
public interface MyInterface {
    void myMethod();
}
```Sin embargo, la anotación `@FunctionalInterface` no es necesaria. Si una interfaz cumple con los criterios de una interfaz funcional (tiene solo un método abstracto), es una interfaz funcional tenga o no la anotación `@FunctionalInterface`.

Entonces, ¿por qué usarlo? 

Hay un par de razones:

1. Deja clara tu intención. Al usar `@FunctionalInterface`, estás indicando a otros desarrolladores (y a ti mismo en el futuro) que esta interfaz está destinada a usarse con expresiones lambda.

2. Permite comprobaciones del compilador. Si anota una interfaz con `@FunctionalInterface` y luego intenta agregarle un segundo método abstracto, el compilador arrojará un error. Esto puede ayudar a prevenir violaciones accidentales del contrato de interfaz funcional.```java
@FunctionalInterface
public interface MyInterface {
    void myMethod();
    void myOtherMethod();  // This will cause a compiler error
}
```Sin embargo, la anotación no pasa a formar parte del código de bytes generado. Es exclusivamente para comprobaciones en tiempo de compilación y para mayor claridad del desarrollador.

Además, tenga en cuenta que si una interfaz está anotada con `@FunctionalInterface`, pero en realidad no cumple con los criterios (por ejemplo, si no tiene ningún método abstracto), el compilador generará un error:```java
@FunctionalInterface
public interface NonFunctionalInterface {
    // No abstract methods
}  // This will cause a compiler error
```### Reglas para definir una interfaz funcional

Las interfaces funcionales no limitan lo que puede hacer. Aún puedes definir tantos métodos estáticos y predeterminados en la interfaz como quieras. 

Los métodos predeterminados le permiten agregar nuevas funciones a las interfaces de sus bibliotecas y garantizar la compatibilidad binaria con el código escrito para versiones anteriores de esas interfaces. Los métodos estáticos en las interfaces se utilizan para proporcionar métodos de utilidad, como la verificación de "nulo", por ejemplo.```java
interface MyInterface {
    void abstractMethod(int x);  
    default void defaultMethod() { }        
    static void staticMethod() { }  
}
```Solo el `abstractMethod` cuenta para la prueba de método abstracto único para una interfaz funcional.

También es importante tener en cuenta que si una interfaz declara un método abstracto que anula uno de los métodos públicos de `java.lang.Object`, eso tampoco cuenta para el recuento de métodos abstractos de la interfaz, ya que cualquier implementación de la interfaz tendrá una implementación de `java.lang.Object` o de otro lugar. Por ejemplo:```java 
interface MyInterface {
    boolean equals(Object obj); 
    // Other methods
}
```En este caso, "MyInterface" sigue siendo una interfaz funcional ya que "equals" es un método público en "Object".

Usar expresiones lambda con interfaces funcionales es simplemente una nueva opción en nuestra caja de herramientas de codificación. Aún puedes usar clases internas anónimas o implementar la interfaz a la antigua usanza:```java
MyInterface ref = new MyInterface() {
    @Override
    public void myMethod() {
        System.out.println("Hello World!");
    }
};

// Implementing the interface in a separate class
class MyClass implements MyInterface {
    @Override
    public void myMethod() {
        System.out.println("Hello World!");
    }
}
MyInterface ref = new MyClass();
```Además, una clase o expresión lambda puede implementar múltiples interfaces funcionales si son compatibles. Por ejemplo, si dos interfaces tienen métodos abstractos idénticos, son efectivamente la misma interfaz funcional:```java
@FunctionalInterface
interface Interface1 {
    void method();
}

@FunctionalInterface
interface Interface2 {
    void method();
}

// Implementing multiple compatible interfaces in a class
class MyClass implements Interface1, Interface2 {
    @Override
    public void method() {
        System.out.println("Hello World!");
    }
}

// Using a lambda expression 
Interface1 ref1 = () -> System.out.println("Hello World!");
Interface2 ref2 = () -> System.out.println("Hello World!");
```Y si las interfaces funcionales integradas como "Runnable" o "Comparator" no satisfacen sus necesidades, puede definir fácilmente las suyas propias. Sólo recuerda la regla del método abstracto único.


## Expresiones Lambda

Las expresiones Lambda le permiten tratar la funcionalidad como un argumento de método o el código como datos, lo que permite un estilo de programación más funcional. Por ejemplo, te permiten escribir código como este:```java
List<Car> compactCars = findCars(cars,
     (Car c) ->
        c.getType().equals(CarTypes.COMPACT)
);
```En lugar de:```java
List<Car> compactCars = findCars(cars,
     new Searchable() {
        public boolean test(Car car) {
           return car.getType().equals(
                     CarTypes.COMPACT);
        }
});
```En esencia, una expresión lambda es una forma concisa de representar una función. El término expresión lambda proviene del cálculo lambda, escrito como λ-cálculo, donde λ es la letra griega lambda. Esta forma de cálculo se ocupa de definir y aplicar funciones.

Las interfaces funcionales son la base sobre la que se construyen las expresiones lambda. Por ejemplo, considere la siguiente interfaz funcional:```java
@FunctionalInterface
interface MyFunction {
    int apply(int a);
}
```Puede utilizar una expresión lambda siempre que se espere una instancia de esta interfaz:```java
MyFunction doubler = (int a) -> a * 2;
```La expresión lambda `a -> a * 2` se ajusta a la firma del método `apply` en `MyFunction`.

### Sintaxis de una expresión Lambda

Una expresión lambda tiene tres partes: una lista de parámetros, un token de flecha (->) y un cuerpo de función. 

Aquí está la sintaxis básica:```java
(parameters) -> expression
// or 
(parameters) -> { statements; }
```Por ejemplo, considere esta interfaz funcional:```java
@FunctionalInterface
interface MyFunction {
    int apply(int a, int b);
}
```Y esta expresión lambda que toma dos números enteros y devuelve su suma:```java
MyFunction f = (int a, int b) -> a + b
```Puede utilizar la palabra clave `var` en la lista de parámetros de una expresión lambda. Esto permite que el compilador infiera el tipo de parámetro:```java
MyFunction f = (var a, var b) -> a + b
```Puede omitir los tipos de parámetros; el compilador también puede inferirlos del contexto:```java
MyFunction f = (a, b) -> a + b
```Si la expresión lambda solo toma un parámetro, puedes incluso omitir los paréntesis:```java
@FunctionalInterface
interface MyFunction {
    int apply(int a);
}

//...

MyFunction f = a -> a * 2
```También puedes usar la palabra clave `var` para declarar una variable sin especificar su tipo solo cuando el compilador puede inferir el tipo a partir del contexto. 

Por ejemplo, tomando en cuenta la interfaz `MyFunction` del ejemplo anterior y solo la siguiente expresión:```java
var f = a -> a * 2;
```Recibirá un error en tiempo de compilación con el siguiente mensaje: "No se puede inferir el tipo: la expresión lambda requiere un tipo de destino explícito".

No puede usar `var` directamente con una expresión lambda como `var f = (var a) -> a * 2;` porque lambda necesita un tipo de destino que `var` no puede proporcionar.

Sin embargo, en este caso:```java
MyInterface f = (a) -> a * 2; // Lambda assigned to functional interface
var fVar = f; // `var` infers type MyInterface
System.out.println(fVar.apply(5)); // Outputs 10
```Puedes usar `var` porque estás asignando una lambda a una interfaz funcional previamente definida, donde el tipo se puede inferir del contexto.

Los contextos donde se puede inferir el tipo de destino (la interfaz funcional) de una expresión lambda incluyen:
- Una declaración de variables
- Una tarea
- Una declaración de devolución
- Un inicializador de matriz.
- Argumentos del método o constructor.
- Una expresión condicional ternaria
- Una expresión moldeada.

Si comprende el concepto, no es necesario que memorice esta lista.

### Expresiones Lambda y clases anónimas

Antes de Java 8, las clases anónimas eran la forma principal de representar una funcionalidad única. Con la introducción de expresiones lambda en Java 8, ahora tenemos una forma más concisa de escribir ciertos tipos de clases anónimas.

Considere esta clase anónima:```java
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Hello!");
    }
};
```Esto se puede reemplazar con una expresión lambda:```java
Runnable r2 = () -> System.out.println("Hello!");
```Sin embargo, si bien las expresiones lambda y las clases anónimas comparten algunas similitudes, también tienen diferencias significativas:

**Similitudes:**

- Las variables locales sólo se pueden utilizar si se declaran finales o son efectivamente finales.
- Puede acceder a variables estáticas o de instancia de la clase adjunta.
- No deben lanzar más excepciones marcadas que las especificadas en la cláusula throws del método de interfaz funcional.

**Diferencias:**

- En una clase anónima, "esto" se refiere a la instancia de la propia clase anónima. En una expresión lambda, "esto" se refiere a la instancia de clase adjunta.
- No se puede acceder a los métodos predeterminados de una interfaz funcional desde expresiones lambda, pero se puede acceder a ellos desde clases anónimas.
- Las expresiones lambda le permiten omitir los tipos de parámetros en la lista de parámetros, lo que no es posible con clases anónimas.
- Si haces referencia a una variable de instancia dentro de una expresión lambda, estás haciendo referencia a la variable desde la instancia adjunta. En una clase anónima, haría referencia a una copia separada de la variable.

A continuación se muestra un ejemplo sobre el uso de variables locales dentro del cuerpo de una lambda:```java
public class LambdaExample {
    public void testLambda() {
        int localVariable = 10;
        Runnable r = () -> {
            System.out.println("Lambda: " + localVariable);
        };
        r.run();
    }

    public void testAnonymous() {
        int localVariable = 10;
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Anonymous: " + localVariable);
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        LambdaExample example = new LambdaExample();
        example.testLambda();
        example.testAnonymous();
    }
}
```Esta es la salida:```
Lambda: 10
Anonymous: 10
```En este ejemplo, tanto la expresión lambda como la clase anónima pueden acceder a la `localVariable` definida en sus respectivos métodos. Sin embargo, si intentamos modificar la `localVariable` después de haberla usado en la expresión lambda o en la clase anónima, obtendremos un error de compilación:```java
public void testLambda() {
    int localVariable = 10;
    Runnable r = () -> {
        System.out.println("Lambda: " + localVariable); // Compilation error
    };
    localVariable = 20;  // Because of this
    r.run();
}
```Esto se debe a que la `localVariable` debe ser efectivamente final (su valor no cambia después de la inicialización) para poder usarse dentro de la expresión lambda o la clase anónima.

Las variables locales tienen que ser finales debido a la forma en que están implementadas en Java. Las variables de instancia se almacenan en el montón, mientras que las variables locales viven en la pila. Las variables del montón se comparten entre subprocesos, pero las variables de la pila se limitan al subproceso en el que se encuentran.

Cuando crea una instancia de una clase interna anónima o una expresión lambda, se copian los valores de las variables locales. Esto evita problemas relacionados con subprocesos y garantiza que esté trabajando con un valor coherente, ya que la variable no se puede modificar después de la inicialización.

Al requerir variables finales (o efectivamente finales), Java garantiza la seguridad y coherencia de los subprocesos, ya que el valor no se puede cambiar, lo que elimina problemas de visibilidad y posibles problemas de subprocesos.


## Interfaces Lambda integradas en Java

En la sección anterior, utilizamos interfaces funcionales como las siguientes:```java
@FunctionalInterface
interface MyFunction {
    int apply(int a, int b);
}
```Sin embargo, no es necesario escribir una interfaz como esa en cada programa que la use (o vincular una biblioteca que la contenga). Ya existe en el lenguaje una interfaz que hace lo mismo pero acepta cualquier tipo de objeto.

Java proporciona interfaces funcionales para casos de uso comunes en el paquete `java.util.function`.

Estos son los cinco principales:
- `Predicado<T>`
- `Consumidor<T>`
- `Función<T, R>`
- `Proveedor<T>`
- `OperadorUnario<T>`

Donde `T` y `R` representan tipos genéricos (`T` representa un tipo de parámetro y `R` el tipo de retorno).

También tienen especializaciones para casos en los que el parámetro de entrada es un tipo primitivo (específicamente para `int`, `long`, `double` y `boolean` para `Proveedor`), por ejemplo:
- `IntPredicado`
- `Consumidor Largo`
- `Proveedor booleano`

Donde el nombre va precedido del tipo primitivo apropiado.

Además, cuatro de ellos tienen versiones binarias, lo que significa que toman dos parámetros en lugar de uno:
- `BiPredicado<L, R>`
- `BiConsumidor<T, U>`
- `BiFunción<T, U, R>`
- `OperadorBinario<T>`

Donde `T`, `U` y `R` representan tipos genéricos (`T` y `U` representan tipos de parámetros y `R` el tipo de retorno).

Las siguientes tablas muestran la lista completa de interfaces. No es necesario que los memorices, sólo intenta comprenderlos.

| Interfaz funcional | Versiones Primitivas |
|---------------------|--------------------|
| `Predicado<T>` | `IntPredicate`<br/> `LongPredicate`<br/> `DoublePredicate` |
| `Consumidor<T>` | `IntConsumer`<br/> `LongConsumer`<br/> `DoubleConsumer` |
| `Función<T, R>` | `IntFunction<R>`<br/> `IntToDoubleFunction`<br/> `IntToLongFunction`<br/> `LongFunction<R>`<br/> `LongToDoubleFunction`<br/> `LongToIntFunction`<br/> `DoubleFunction<R>`<br/> `DoubleToIntFunction`<br/> `DoubleToLongFunction`<br/> `ToIntFunction<T>`<br/> `ToDoubleFunction<T>`<br/> `ToLongFunction<T>` |
| `Proveedor<T>` | `BooleanSupplier`<br/> `IntSupplier`<br/> `LongSupplier`<br/> `DoubleSupplier` |
| `OperadorUnario<T>` | `IntUnaryOperator`<br/> `LongUnaryOperator`<br/> `DoubleUnaryOperator` |


| Interfaz funcional | Versiones Primitivas |
|--------------------|--------------------|
| `BiPredicado<L, R>` |                    |
| `BiConsumidor<T, U>` | `ObjIntConsumer<T>`<br/> `ObjLongConsumer<T>`<br/> `ObjDoubleConsumer<T>` |
| `BiFunción<T, U, R>` | `ToIntBiFunction<T, U>`<br/> `ToLongBiFunction<T, U>`<br/> `ToDoubleBiFunction<T, U>` |
| `OperadorBinario<T>` | `IntBinaryOperator`<br/> `LongBinaryOperator`<br/> `DoubleBinaryOperator` |


### `Predicado`

Un predicado es una declaración que puede ser "verdadera" o "falsa" dependiendo de los valores de sus variables.

Esta interfaz funcional se puede utilizar en cualquier lugar donde necesite evaluar una condición "booleana".

Así se define la interfaz:```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
    // Other default and static methods
    // ...
}
```El descriptor funcional (firma del método) es:```java
Predicate<T>
```Aquí hay un ejemplo usando una clase anónima:```java
Predicate<String> startsWithA = new Predicate<String>() {
    @Override
    public boolean test(String t) {
        return t.startsWith("A");
    }
};
boolean result = startsWithA.test("Arthur");
```Y con expresión lambda:```java
Predicate<String> startsWithA = t -> t.startsWith("A");
boolean result = startsWithA.test("Arthur");
```Esta interfaz también tiene los siguientes métodos predeterminados:```java
default Predicate<T> and(Predicate<? super T> other)
default Predicate<T> or(Predicate<? super T> other)
default Predicate<T> negate()
```Estos métodos devuelven un "Predicado" compuesto que representa un **Y** y un **O** lógicos en cortocircuito de este predicado y otro y su negación lógica.

El cortocircuito significa que el otro predicado no se evaluará si el valor del primer predicado puede predecir el resultado de la operación (si el primer predicado devuelve falso en el caso de **Y** o si devuelve verdadero en el caso de **O**).

Estos métodos son útiles para combinar predicados y hacer que el código sea más legible, por ejemplo:```java
Predicate<String> startsWithA = t -> t.startsWith("A");
Predicate<String> endsWithA = t -> t.endsWith("A");
boolean result = startsWithA.and(endsWithA).test("Hi");
```Además, existe un método "estático":```java
static <T> Predicate<T> isEqual(Object targetRef)
```Eso devuelve un "Predicado" que prueba si dos argumentos son iguales según "Objects.equals (Objeto, Objeto)".

También existen versiones primitivas para "int", "long" y "double". No se extienden desde "Predicado".

Por ejemplo, aquí está la definición de "IntPredicate":```java
@FunctionalInterface
public interface IntPredicate {
    boolean test(int value);
    // And the default methods: and, or, negate
}
```Entonces en lugar de usar:```java
Predicate<Integer> even = t -> t % 2 == 0;
boolean result = even.test(5);
```Puedes usar:```java
IntPredicate even = t -> t % 2 == 0;
boolean result = even.test(5);
```¿Por qué?

Solo para evitar la conversión de `Integer` a `int` y trabajar directamente con tipos primitivos.

Observe que estas versiones primitivas no tienen un tipo genérico. Debido a la forma en que se implementan los genéricos, los parámetros de las interfaces funcionales solo pueden vincularse a tipos de objetos.

Dado que la conversión del tipo contenedor (`Integer`) al tipo primitivo (`int`) usa más memoria y tiene un costo de rendimiento, Java proporciona estas versiones para evitar operaciones de autoboxing cuando las entradas o salidas son primitivas.

Aquí está el texto corregido:

### `Consumidor`

`Consumidor` representa una operación que acepta un único argumento de entrada y no devuelve ningún resultado, solo ejecuta algunas operaciones sobre el argumento.

Así se define la interfaz:```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    // And a default method
    // ...
}
```El descriptor funcional (firma del método) es:```java
T -> void
```Aquí hay un ejemplo usando una clase anónima:```java
Consumer<String> consumeStr = new Consumer<String>() {
    @Override
    public void accept(String t) {
        System.out.println(t);
    }
};
consumeStr.accept("Hi");
```Y con expresión lambda:```java
Consumer<String> consumeStr = t -> System.out.println(t);
consumeStr.accept("Hi");
```Esta interfaz también tiene el siguiente método predeterminado:```java
default Consumer<T> andThen(Consumer<? super T> after)
```Este método devuelve un "Consumidor" compuesto que realiza, en secuencia, la operación del consumidor seguida de la operación del parámetro.

Estos métodos son útiles para combinar `Consumer`s y hacer que el código sea más legible, por ejemplo:```java
Consumer<String> first = t ->
    System.out.println("First:" + t);
Consumer<String> second = t ->
    System.out.println("Second:" + t);
first.andThen(second).accept("Hi");
```La salida es:```java
First: Hi
Second: Hi
```Mire cómo ambos "Consumidores" toman el mismo argumento y el mismo orden de ejecución.

También existen versiones primitivas para "int", "long" y "double". No se extienden desde el "Consumidor".

Por ejemplo, aquí está la definición de "IntConsumer":```java
@FunctionalInterface
public interface IntConsumer {
    void accept(int value);
    default IntConsumer andThen(IntConsumer after) {
        // ...
    }
}
```Entonces en lugar de usar:```java
int[] a = { 1,2,3,4,5,6,7,8 };
printList(a, t -> System.out.println(t));
//...
void printList(int[] a, Consumer<Integer> c) {
    for(int i : a) {
        c.accept(i);
    }
}
```Puedes usar:```java
int[] a = { 1,2,3,4,5,6,7,8 };
printList(a, (IntConsumer) t -> System.out.println(t));
//...
void printList(int[] a, IntConsumer c) {
    for(int i : a) {
        c.accept(i);
    }
}
```### `Función`

`Función` representa una operación que toma un argumento de entrada de un determinado tipo y produce un resultado de otro tipo.

Un uso común es convertir o transformar de un objeto a otro.

Así se define la interfaz:```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
    // Other default and static methods
    // ...
}
```El descriptor funcional (firma del método) es:```java
T -> R
```Suponiendo un método:```java
void round(double d, Function<Double, Long> f) {
    long result = f.apply(d);
    System.out.println(result);
}
```Aquí hay un ejemplo usando una clase anónima:```java
round(5.4, new Function<Double, Long>() {
    @Override
    public Long apply(Double d) {
        return Math.round(d);
    }
});
```Y con expresión lambda:```java
round(5.4, d -> Math.round(d));
```Esta interfaz también tiene los siguientes métodos predeterminados:```java
default <V> Function<V,R> compose(
    Function<? super V,? extends T> before)
default <V> Function<T,V> andThen(
    Function<? super R,? extends V> after)
```La diferencia entre estos métodos es que "componer" aplica primero la función representada por el parámetro y su resultado sirve como entrada para la otra función. `andThen` primero aplica la función que llama al método, y su resultado actúa como entrada de la función representada por el parámetro.

Por ejemplo:```java
Function<String, String> f1 = s -> s.toUpperCase();
Function<String, String> f2 = s -> s.toLowerCase();
System.out.println(f1.compose(f2).apply("Compose"));
System.out.println(f1.andThen(f2).apply("AndThen"));
```La salida es:```java
COMPOSE
andthen
```En el primer caso, `f2` es la primera función que se aplicará. En el segundo caso, `f2` es la última función que se aplicará.

Además, existe un método "estático":```java
static <T> Function<T, T> identity()
```Eso devuelve una función que siempre devuelve su argumento de entrada.

En el caso de las versiones primitivas, también se aplican a `int`, `long` y `double`, pero hay más combinaciones que las interfaces anteriores:

- Para indicar que la función devuelve un tipo genérico y toma un argumento primitivo, la interfaz se llama **XXXFunction**, por ejemplo, `IntFunction`:```java
    @FunctionalInterface
    public interface IntFunction<R> {
        R apply(int value);
    }
    ```- Para indicar que la función devuelve un tipo primitivo y toma un argumento genérico, la interfaz se llama **ToXXXFunction**, por ejemplo, `ToIntFunction`:```java
    @FunctionalInterface
    public interface ToIntFunction<T> {
        int applyAsInt(T value);
    }
    ```- Para indicar que la función toma un argumento primitivo y devuelve otro tipo primitivo, la interfaz se denomina **XXXToYYYFunction**, donde **XXX** es el tipo de argumento y **YYY** es el tipo de retorno, por ejemplo, `IntToDoubleFunction`:```java
    @FunctionalInterface
    public interface IntToDoubleFunction {
        double applyAsDouble(int value);
    }
    ```Recuerde que estas interfaces son por conveniencia, para trabajar directamente con primitivas, por ejemplo:

`DoubleFunction<R>` en lugar de `Function<Double, R>`  
`ToLongFunction<T>` en lugar de `Función<T, Long>`  
`IntToLongFunction` en lugar de `Función<Entero, Largo>`


### `Proveedor`

"Proveedor" es lo opuesto a "Consumidor". No requiere argumentos y solo devuelve algún valor.

Así se define la interfaz:```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```El descriptor funcional (firma del método) es:```java
() -> T
```Aquí hay un ejemplo usando una clase anónima:```java
String t = "One";
Supplier<String> supplierStr = new Supplier<String>() {
    @Override
    public String get() {
        return t.toUpperCase();
    }
};
System.out.println(supplierStr.get());
```Y con expresión lambda:```java
String t = "One";
Supplier<String> supplierStr = () -> t.toUpperCase();
System.out.println(supplierStr.get());
```Esta interfaz no define métodos predeterminados.

También existen versiones primitivas para "int", "long", "double" y "boolean", pero no se extienden desde "Proveedor".

Por ejemplo, aquí está la definición de "BooleanSupplier":```java
@FunctionalInterface
public interface BooleanSupplier {
    boolean getAsBoolean();
}
```Estas versiones primitivas se utilizan en lugar de "Proveedor" para sus respectivos tipos.


### `Operador Unario`

`UnaryOperator` es solo una especialización de la interfaz `Function` (de hecho, esta interfaz se extiende desde ella) para cuando el argumento y el resultado son del mismo tipo.

Así se define la interfaz:```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
    // Just the identity
    // method is defined
}
```El descriptor funcional (firma del método) es:```java
T -> T
```Aquí hay un ejemplo usando una clase anónima:```java
UnaryOperator<String> uOp = new UnaryOperator<String>() {
    @Override
    public String apply(String t) {
        return t.substring(0,2);
    }
};
System.out.println(uOp.apply("Hello"));
```Y con expresión lambda:```java
UnaryOperator<String> uOp = t -> t.substring(0,2);
System.out.println(uOp.apply("Hello"));
```Esta interfaz hereda los métodos predeterminados de la interfaz "Función":```java
default <V> Function<V, T> compose(
    Function<? super V, ? extends T> before)
default <V> Function<T, V> andThen(
    Function<? super T, ? extends V> after)
```Y simplemente define el método `estático` `identidad()` para esta interfaz (ya que los métodos `estáticos` no se heredan):```java
static <T> UnaryOperator<T> identity()
```Eso devuelve un "Operador Unario" que siempre devuelve su argumento de entrada.

También existen versiones primitivas para "int", "long" y "double". No se extienden desde "UnaryOperator".

Por ejemplo, aquí está la definición de "IntUnaryOperator":```java
@FunctionalInterface
public interface IntUnaryOperator {
    int applyAsInt(int operand);
    // Definitions for compose, andThen, and identity
}
```Entonces en lugar de usar:```java
int[] a = {1,2,3,4,5,6,7,8};
int sum = sumNumbers(a, t -> t * 2);
//...
int sumNumbers(int[] a, UnaryOperator<Integer> unary) {
    int sum = 0;
    for(int i : a) {
        sum += unary.apply(i);
    }
    return sum;
}
```Puedes usar:```java
int[] a = {1,2,3,4,5,6,7,8};
int sum = sumNumbers(a, t -> t * 2);
//...
int sumNumbers(int[] a, IntUnaryOperator unary) {
    int sum = 0;
    for(int i : a) {
        sum += unary.applyAsInt(i);
    }
    return sum;
}
```### `BiPredicado`

Esta interfaz representa un predicado que toma dos argumentos.

Se define de la siguiente manera:```java
@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
    // Default methods are also defined
}
```El descriptor funcional (firma del método) es:```java
(T, U) -> boolean
```Aquí hay un ejemplo usando una clase anónima:```java
BiPredicate<Integer, Integer> divisible =
    new BiPredicate<Integer, Integer>() {
        @Override
        public boolean test(Integer t, Integer u) {
            return t % u == 0;
        }
    };
boolean result = divisible.test(10, 5);
```Y con expresión lambda:```java
BiPredicate<Integer, Integer> divisible =
    (t, u) -> t % u == 0;
boolean result = divisible.test(10, 5);
```Esta interfaz define los mismos métodos predeterminados que la interfaz `Predicate`, pero con dos argumentos:```java
default BiPredicate<T, U> and(
    BiPredicate<? super T, ? super U> other) {
    return (t, u) -> test(t, u) && other.test(t, u);
}

default BiPredicate<T, U> or(
    BiPredicate<? super T, ? super U> other) {
    return (t, u) -> test(t, u) || other.test(t, u);
}

default BiPredicate<T, U> negate() {
    return (t, u) -> !test(t, u);
}
```Esta interfaz no tiene versiones primitivas.


### `BiConsumidor`

Esta interfaz representa un consumidor que toma dos argumentos (y no devuelve un resultado).

Así se define:```java
@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
    // andThen default method is defined
}
```El descriptor funcional (firma del método) es:```java
(T, U) -> void
```Aquí hay un ejemplo usando una clase anónima:```java
BiConsumer<String, String> consumeStr =
    new BiConsumer<String, String>() {
        @Override
        public void accept(String t, String u) {
            System.out.println(t + " " + u);
        }
    };
consumeStr.accept("Hi", "there");
```Y con expresión lambda:```java
BiConsumer<String, String> consumeStr =
    (t, u) -> System.out.println(t + " " + u);
consumeStr.accept("Hi", "there");
```Esta interfaz también tiene el siguiente método predeterminado:```java
default BiConsumer<T, U> andThen(
    BiConsumer<? super T, ? super U> after)
```Este método devuelve un `BiConsumer` compuesto que realiza, en secuencia, la operación del consumidor seguida de la operación del parámetro. Lanzará "NullPointerException" si el parámetro "después" es "nulo".

Como en el caso de un `Consumer`, estos métodos son útiles para combinar `BiConsumer`s y hacer que el código sea más legible, por ejemplo:```java
BiConsumer<String, String> first = (t, u) -> System.out.println(t.toUpperCase() + u.toUpperCase());
BiConsumer<String, String> second = (t, u) -> System.out.println(t.toLowerCase() + u.toLowerCase());
first.andThen(second).accept("Again", " and again");
```La salida es:```java
AGAIN AND AGAIN
again and again
```También existen versiones de especialización primitivas para "int", "long" y "double". No se extienden desde `BiConsumer` y, en lugar de tomar dos `int`, por ejemplo, toman un objeto y un valor primitivo como segundo argumento. Entonces la convención de nomenclatura cambia a **ObjXXXConsumer**, donde **XXX** es el tipo primitivo. Por ejemplo, aquí está la definición de "ObjIntConsumer":```java
@FunctionalInterface
public interface ObjIntConsumer<T> {
    void accept(T t, int value);
}
```Entonces en lugar de usar:```java
int[] a = {1,2,3,4,5,6,7,8};
printList(a, (t, i) -> System.out.println(t + i));
//...
void printList(int[] a, BiConsumer<String, Integer> c) {
    for(int i : a) {
        c.accept("Number:", i);
    }
}
```Puedes usar:```java
int[] a = {1,2,3,4,5,6,7,8};
printList(a, (t, i) -> System.out.println(t + i));
//...
void printList(int[] a, ObjIntConsumer<String> c) {
    for(int i : a) {
        c.accept("Number:", i);
    }
}
```### `BiFunción`

Esta interfaz representa una función que toma dos argumentos de diferentes tipos y produce un resultado de otro tipo.

Así se define:```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
    // Other default and static methods
    // ...
}
```El descriptor funcional (firma del método) es:```java
(T, U) -> R
```Suponiendo un método:```java
void round(double d1, double d2, BiFunction<Double, Double, Long> f) {
    long result = f.apply(d1, d2);
    System.out.println(result);
}
```Aquí hay un ejemplo usando una clase anónima:```java
round(5.4, 3.8, new BiFunction<Double, Double, Long>() {
    @Override
    public Long apply(Double d1, Double d2) {
        return Math.round(d1 + d2);
    }
});
```Y con expresión lambda:```java
round(5.4, 3.8, (d1, d2) -> Math.round(d1 + d2));
```Esta interfaz, a diferencia de "Función", tiene solo un método predeterminado:```java
default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after)
```Eso devuelve una función compuesta que primero aplica la función que llama "andThen" a su entrada y luego aplica la función representada por el argumento al resultado.

Esta interfaz también tiene menos versiones primitivas que "Función". Solo tiene las versiones que toman tipos genéricos como argumentos y devuelven tipos primitivos `int`, `long` y `double`, con la convención de nomenclatura **ToXXXBiFunction**, donde XXX es el tipo primitivo.

Por ejemplo, aquí está la definición de "ToIntBiFunction":```java
@FunctionalInterface
public interface ToIntBiFunction<T, U> {
    int applyAsInt(T t, U u);
}
```Esto reemplaza a "BiFunction".


### `Operador binario`

Esta interfaz es una especialización de la interfaz `BiFunction` (de hecho, esta interfaz la extiende) para cuando los argumentos y el resultado son del mismo tipo.

Así se define la interfaz:```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {
    // Two static methods are defined
}
```El descriptor funcional (firma del método) es:```java
(T, T) -> T
```Aquí hay un ejemplo usando una clase anónima:```java
BinaryOperator<String> binOp = new BinaryOperator<String>() {
    @Override
    public String apply(String t, String u) {
        return t.concat(u);
    }
};
System.out.println(binOp.apply("Hello", " there"));
```Y con expresión lambda:```java
BinaryOperator<String> binOp = (t, u) -> t.concat(u);
System.out.println(binOp.apply("Hello", " there"));
```Esta interfaz hereda el método predeterminado de la interfaz `BiFunction`:```java
default <V> BiFunction<T, T, V> andThen(Function<? super T, ? extends V> after)
```Y define dos nuevos métodos "estáticos":```java
static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator)
static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator)
```Eso devuelve un "BinaryOperator", que devuelve el menor o mayor de dos elementos según el "Comparador" especificado.

He aquí un ejemplo sencillo:```java
BinaryOperator<Integer> biOp = BinaryOperator.maxBy(Comparator.naturalOrder());
System.out.println(biOp.apply(28, 8));
```Como puede ver, estos métodos son solo un contenedor para ejecutar un "Comparador".

`Comparator.naturalOrder()` devuelve un `Comparador` que compara objetos `Comparables` en orden natural. Para ejecutarlo, simplemente llamamos al método `apply()` con los dos argumentos necesarios para el `BinaryOperator`. Como era de esperar, el resultado es:```java
28
```También existen versiones primitivas para "int", "long" y "double", donde los dos argumentos y el tipo de retorno son del mismo tipo primitivo. No extienden `BinaryOperator` o `BiFunction`.

Por ejemplo, aquí está la definición de "IntBinaryOperator":```java
@FunctionalInterface
public interface IntBinaryOperator {
    int applyAsInt(int left, int right);
}
```Que puedes usar en lugar de `BinaryOperator`.


### Interfaces funcionales específicas de primitivas
También hay un conjunto de interfaces funcionales que están diseñadas específicamente para trabajar con tipos primitivos. Estas interfaces pueden proporcionar un mejor rendimiento que sus contrapartes genéricas cuando se trabaja con primitivas, ya que evitan la sobrecarga de boxing y unboxing.

Hay varias categorías de interfaces funcionales específicas de primitivas:

1. `ToDoubleFunction<T>`, `ToIntFunction<T>`, `ToLongFunction<T>`: estas interfaces representan funciones que aceptan un objeto de tipo `T` y devuelven una primitiva `double`, `int` o `long`, respectivamente. Por ejemplo, así es como se define `ToIntFunction<T>`:```java
@FunctionalInterface
public interface ToIntFunction<T> {
    int applyAsInt(T value);
}
```Y aquí tienes un ejemplo de cómo usarlo:```java
ToIntFunction<String> stringToInt = Integer::parseInt;
int i = stringToInt.applyAsInt("123");  // 123
```2. `ToDoubleBiFunction<T, U>`, `ToIntBiFunction<T, U>`, `ToLongBiFunction<T, U>`: Estas interfaces representan funciones que aceptan dos objetos de tipos `T` y `U` y devuelven una primitiva `double`, `int` o `long`, respectivamente. Por ejemplo, así es como se define `ToIntBiFunction<T, U>`:```java
@FunctionalInterface
public interface ToIntBiFunction<T, U> {
    int applyAsInt(T t, U u);
}
```Y aquí tienes un ejemplo de cómo usarlo:```java
ToIntBiFunction<String, String> comparator = String::compareTo;
int result = comparator.applyAsInt("abc", "def");  // a negative value
```3. `DoubleToIntFunction`, `DoubleToLongFunction`, `IntToDoubleFunction`, `IntToLongFunction`, `LongToDoubleFunction`, `LongToIntFunction`: estas interfaces representan funciones que aceptan un tipo primitivo y devuelven otro tipo primitivo. Por ejemplo, así es como se define `DoubleToIntFunction`:```java
@FunctionalInterface
public interface DoubleToIntFunction {
    int applyAsInt(double value);
}
```Y aquí tienes un ejemplo de cómo usarlo:```java
DoubleToIntFunction roundDown = d -> (int) d;
int i = roundDown.applyAsInt(9.9);  // 9
```4. `ObjDoubleConsumer<T>`, `ObjIntConsumer<T>`, `ObjLongConsumer<T>`: Estas interfaces representan funciones que aceptan un objeto de tipo `T` y una primitiva `double`, `int` o `long`, y devuelven `void`. Por ejemplo, así es como se define `ObjIntConsumer<T>`:```java
@FunctionalInterface
public interface ObjIntConsumer<T> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first input argument
     * @param value the second input argument
     */
    void accept(T t, int value);
}
```Y aquí tienes un ejemplo de cómo usarlo:```java
ObjIntConsumer<List<Integer>> listAddInt = List::add;
List<Integer> list = new ArrayList<>();
listAddInt.accept(list, 1);  // [1]
```Estas interfaces se diferencian de `DoubleFunction<R>`, `IntFunction<R>`, `LongFunction<R>`, etc., en que estas últimas aceptan una primitiva y devuelven un objeto. Por ejemplo:```java
IntFunction<String> intToString = Integer::toString;
String s = intToString.apply(123);  // "123"
```La elección de qué interfaz utilizar depende de sus necesidades específicas. Si trabaja principalmente con primitivas y desea evitar la sobrecarga del autoboxing y unboxing, las interfaces específicas de primitivas son una buena opción. Sin embargo, si necesita trabajar con objetos, o si la sobrecarga del boxeo no es una preocupación, las interfaces genéricas como `Function<T, R>` y `BiFunction<T, U, R>` suelen ser más convenientes.

   
## Referencias de métodos

Como sabes, en Java podemos utilizar referencias a objetos, ya sea creando nuevos objetos:```java
List list = new ArrayList();
store(new ArrayList());
```O usando objetos existentes:```java
List list2 = list;
isFull(list2);
```Pero ¿qué pasa con una referencia a un *método*?

Si solo usamos el método de un objeto en otro método, aún tenemos que pasar el objeto completo como argumento. ¿No sería más práctico simplemente pasar el método como argumento? Como este por ejemplo:```java
isFull(list.size);
```Gracias a las expresiones lambda, podemos hacer algo así. Podemos utilizar métodos como si fueran objetos o valores primitivos.

Y eso se debe a que una referencia de método es la sintaxis abreviada de una expresión lambda que ejecuta solo **un** método.

Aquí está la sintaxis de una referencia de método:```java
Object :: methodName
```Puede usar expresiones lambda en lugar de usar una clase anónima, pero a veces, la expresión lambda es en realidad solo una llamada a algún método. Por ejemplo:```java
Consumer<String> c = s -> System.out.println(s);
```Para aclarar el código, puede convertir esa expresión lambda en una referencia de método:```java
Consumer<String> c = System.out::println;
```En una referencia de método, coloca el objeto (o clase) que contiene el método antes del operador `::` y el nombre del método después sin argumentos.

Pero quizás estés pensando:

- ¿Cómo es esto más claro?
- ¿Qué pasa con los argumentos?
- ¿Cómo puede ser ésta una expresión válida?
- No entiendo cómo construir una referencia de método válida.

En primer lugar, no se puede utilizar una referencia de método para ningún método. Solo se pueden utilizar para reemplazar una expresión lambda de método único.

Entonces, para utilizar una referencia de método, primero necesita una expresión lambda con un método. Y para usar una expresión lambda primero necesitas una interfaz funcional, una interfaz con un solo método abstracto.

En otras palabras:

en lugar de usar

**UNA CLASE ANÓNIMA**

puedes usar

**UNA EXPRESIÓN LAMBDA**

Y si esto solo llama a un método, puedes usar

**UNA REFERENCIA DEL MÉTODO**

Hay cuatro tipos de referencias de métodos:

- Una referencia de método a un *método estático*
- Una referencia de método a un *método de instancia de un objeto de un tipo particular*
- Una referencia de método a un *método de instancia de un objeto existente*
- Una referencia de método a un *constructor*

Comencemos explicando el caso más natural, un *método estático*.

### Métodos estáticos

En este caso, tenemos una expresión lambda como la siguiente:```java
(args) -> Class.staticMethod(args)
```Eso se puede convertir en la siguiente referencia de método:```java
Class::staticMethod
```Observe que entre un método estático y una referencia de método estático en lugar del operador `.`, usamos el operador `::` y que no pasamos argumentos a la referencia del método.

En general, no tenemos que pasar argumentos a las referencias de métodos. Sin embargo, los argumentos se tratan según el tipo de referencia del método.

En este caso, cualquier argumento (si lo hay) tomado por el método se pasa automáticamente detrás de las cortinas.

Siempre que podamos pasar una expresión lambda que simplemente llame a un método estático, podemos usar una referencia de método. Por ejemplo, asumiendo esta clase:```java
class Numbers {
    public static boolean isMoreThanFifty(int n1, int n2) {
        return (n1 + n2) > 50;
    }
    public static List<Integer> findNumbers(
        List<Integer> l, BiPredicate<Integer, Integer> p) {
        List<Integer> newList = new ArrayList<>();
        for (Integer i : l) {
            if (p.test(i, i + 10)) {
                newList.add(i);
            }
        }
        return newList;
    }
}
```Podemos llamar al método `findNumbers()`:```java
List<Integer> list = Arrays.asList(12, 5, 45, 18, 33, 24, 40);

// Using an anonymous class
findNumbers(list, new BiPredicate<Integer, Integer>() {
    public boolean test(Integer i1, Integer i2) {
        return Numbers.isMoreThanFifty(i1, i2);
    }
});

// Using a lambda expression
findNumbers(list, (i1, i2) -> Numbers.isMoreThanFifty(i1, i2));

// Using a method reference
findNumbers(list, Numbers::isMoreThanFifty);
```### Método de instancia de un objeto de un tipo particular

En este caso, tenemos una expresión lambda como la siguiente:```java
(obj, args) -> obj.instanceMethod(args)
```Donde se pasa una instancia de un objeto y uno de sus métodos se ejecuta con algunos parámetros opcionales.

Eso se puede convertir en la siguiente referencia de método:```java
ObjectType::instanceMethod
```Esta vez, la conversión no es tan sencilla. Primero, en la referencia del método, no usamos la instancia en sí. Usamos su tipo.

En segundo lugar, el otro argumento de la expresión lambda, si lo hay, no se utiliza en la referencia del método, pero se pasa detrás de las cortinas como en el caso del método estático.

Por ejemplo, asumiendo esta clase:```java
class Shipment {
    public double calculateWeight() {
        double weight = 0;
        // Calculate weight
        return weight;
    }
}
```Y este método:```java
public List<Double> calculateOnShipments(
    List<Shipment> l, Function<Shipment, Double> f) {
    List<Double> results = new ArrayList<>();
    for (Shipment s : l) {
        results.add(f.apply(s));
    }
    return results;
}
```Podemos llamar a ese método usando:```java
List<Shipment> l = new ArrayList<Shipment>();

// Using an anonymous class
calculateOnShipments(l, new Function<Shipment, Double>() {
    public Double apply(Shipment s) { // The object
        return s.calculateWeight(); // The method
    }
});

// Using a lambda expression
calculateOnShipments(l, s -> s.calculateWeight());

// Using a method reference
calculateOnShipments(l, Shipment::calculateWeight);
```En este ejemplo, no pasamos ningún argumento al método. El punto clave aquí es que una instancia del objeto es el parámetro de la expresión lambda y formamos la referencia al método de instancia con el tipo de instancia.

Aquí hay otro ejemplo en el que pasamos dos argumentos a la referencia del método.

Java tiene una interfaz `Function` que toma un parámetro, una `BiFunction` que toma dos parámetros, pero no hay una `TriFunction` que toma tres parámetros, así que hagamos uno:```java
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
```Ahora supongamos una clase con un método que toma dos parámetros y devuelve un resultado, como este:```java
class Sum {
    Integer doSum(String s1, String s2) {
        return Integer.parseInt(s1) + Integer.parseInt(s2);
    }
}
```Podemos envolver el método `doSum()` dentro de una implementación `TriFunction` usando una clase anónima:```java
TriFunction<Sum, String, String, Integer> anon =
    new TriFunction<Sum, String, String, Integer>() {
        @Override
        public Integer apply(Sum s, String arg1, String arg2) {
            return s.doSum(arg1, arg2);
        }
    };
System.out.println(anon.apply(new Sum(), "1", "4"));
```O usando una expresión lambda:```java
TriFunction<Sum, String, String, Integer> lambda =
    (Sum s, String arg1, String arg2) -> s.doSum(arg1, arg2);
System.out.println(lambda.apply(new Sum(), "1", "4"));
```O simplemente usando una referencia de método:```java
TriFunction<Sum, String, String, Integer> mRef = Sum::doSum;
System.out.println(mRef.apply(new Sum(), "1", "4"));
```Aquí:

- El primer parámetro de tipo de `TriFunction` es el tipo de objeto que contiene el método a ejecutar.
- El segundo parámetro de tipo de `TriFunction` es el tipo del primer parámetro.
- El tercer tipo de parámetro de `TriFunction` es el tipo del segundo parámetro.
- El último parámetro de tipo de `TriFunction` es el tipo de retorno del método a ejecutar. Observe cómo esto se omite (se infiere) en la expresión lambda y la referencia del método.

Puede parecer extraño ver simplemente la interfaz, la clase y cómo se usan con una referencia de método, pero esto se vuelve más evidente cuando ves la clase anónima o incluso la versión lambda.

De:```java
(Sum s, String arg1, String arg2) -> s.doSum(arg1, arg2)
```A```java
Sum::doSum
```### Método de instancia de un objeto existente

En este caso, tenemos una expresión lambda como la siguiente:```java
(args) -> obj.instanceMethod(args)
```Eso se puede convertir en la siguiente referencia de método:```java
obj::instanceMethod
```Esta vez, se utiliza una instancia definida en otro lugar y los argumentos (si los hay) se pasan detrás de escena como en el caso del método estático.

Por ejemplo, asumiendo estas clases:```java
class Car {
    private int id;
    private String color;
    // More properties
    // And getters and setters
}
class Mechanic {
    public void fix(Car c) {
        System.out.println("Fixing car " + c.getId());
    }
}
```y

 este método:```java
public static void execute(Car car, Consumer<Car> c) {
    c.accept(car);
}
```Podemos llamar al método anterior usando:```java
final Mechanic mechanic = new Mechanic();
Car car = new Car();

// Using an anonymous class
execute(car, new Consumer<Car>() {
    public void accept(Car c) {
        mechanic.fix(c);
    }
});

// Using a lambda expression
execute(car, c -> mechanic.fix(c));

// Using a method reference
execute(car, mechanic::fix);
```La clave en este caso es utilizar cualquier objeto visible mediante una expresión lambda/clase anónima y pasar algunos argumentos a un método de instancia de ese objeto.

Aquí hay otro ejemplo rápido usando otro "Consumidor":```java
Consumer<String> c = System.out::println;
c.accept("Hello");
```##Constructor

En este caso, tenemos una expresión lambda como la siguiente:```java
(args) -> new ClassName(args)
```Eso se puede convertir en la siguiente referencia de método:```java
ClassName::new
```Lo único que hace esta expresión lambda es crear un nuevo objeto, por lo que simplemente hacemos referencia a un constructor de la clase con la palabra clave "nuevo". Como en los otros casos, los argumentos (si los hay) no se pasan en la referencia del método.

La mayoría de las veces, podemos usar esta sintaxis con dos (o tres) interfaces del paquete `java.util.function`.

Si el constructor no acepta argumentos, un "Proveedor" hará el trabajo:```java
// Using an anonymous class
Supplier<List<String>> s = new Supplier<List<String>>() {
    public List<String> get() {
        return new ArrayList<String>();
    }
};
List<String> l = s.get();

// Using a lambda expression
Supplier<List<String>> s = () -> new ArrayList<String>();
List<String> l = s.get();

// Using a method reference
Supplier<List<String>> s = ArrayList::new;
List<String> l = s.get();
```Si el constructor toma un argumento, podemos usar la interfaz "Función". Por ejemplo:```java
// Using an anonymous class
Function<String, Integer> f =
    new Function<String, Integer>() {
        public Integer apply(String s) {
            return new Integer(s);
        }
    };
Integer i = f.apply("100");

// Using a lambda expression
Function<String, Integer> f = s -> new Integer(s);
Integer i = f.apply("100");

// Using a method reference
Function<String, Integer> f = Integer::new;
Integer i = f.apply("100");
```Si el constructor toma dos argumentos, usamos la interfaz `BiFunction`:```java
// Using an anonymous class
BiFunction<String, String, Locale> f = new BiFunction<String, String, Locale>() {
    public Locale apply(String lang, String country) {
        return new Locale(lang, country);
    }
};
Locale loc = f.apply("en", "UK");

// Using a lambda expression
BiFunction<String, String, Locale> f = (lang, country) -> new Locale(lang, country);
Locale loc = f.apply("en", "UK");

// Using a method reference
BiFunction<String, String, Locale> f = Locale::new;
Locale loc = f.apply("en", "UK");
```Si tiene un constructor con tres o más argumentos, deberá crear su propia interfaz funcional.

Puede ver que hacer referencia a un constructor es muy similar a hacer referencia a un método estático. La diferencia es que el *nombre del método* del constructor es "nuevo".

Muchos de los ejemplos de este capítulo son muy simples y probablemente no justifiquen el uso de expresiones lambda o referencias a métodos.

Como se mencionó al principio del capítulo, utilice referencias de métodos si aclaran su código.

Puede evitar la restricción de un método agrupando todo su código en un método estático, por ejemplo, y creando una referencia a ese método en lugar de usar una clase o una expresión lambda con muchas líneas.

Pero el verdadero poder de las expresiones lambda y las referencias a métodos surge cuando se combinan con otra característica de Java: las secuencias.

Ése será el tema del próximo capítulo.


## Puntos clave
- Una interfaz funcional es una interfaz que contiene sólo un método abstracto. Puede contener métodos predeterminados o estáticos.

- La anotación `@FunctionalInterface` se utiliza para indicar que una interfaz está destinada a ser una interfaz funcional. Permite realizar comprobaciones del compilador, pero no es necesario si la interfaz cumple con los criterios de interfaz funcional.

- Si una interfaz declara un método abstracto que anula un método público en `java.lang.Object`, no cuenta para el recuento de métodos abstractos de la interfaz.

- Las expresiones Lambda le permiten tratar la funcionalidad como un argumento de método o el código como datos.

- La sintaxis de una lambda es: `(parámetros) -> expresión` o `(parámetros) -> { declaraciones; }`.

- Puedes usar `var` en la lista de parámetros de una expresión lambda para permitir la inferencia de tipos.

- El tipo de destino de una expresión lambda se puede inferir en contextos como declaraciones de variables, asignaciones, declaraciones de retorno, inicializadores de matrices, argumentos de método/constructor, expresiones ternarias y expresiones de conversión.

- Las expresiones Lambda son similares a las clases anónimas en algunos aspectos, como el uso de variables locales, pero difieren en el tratamiento de "esto", métodos predeterminados, listas de parámetros y variables de instancia.

- Las variables locales utilizadas en una expresión lambda o clase anónima deben ser finales o efectivamente finales (no modificadas después de la inicialización) para la seguridad y coherencia del subproceso.

- Java proporciona interfaces funcionales integradas en el paquete `java.util.function` para casos de uso comunes. Los principales son `Predicado<T>`, `Consumidor<T>`, `Función<T, R>`, `Proveedor<T>` y `OperadorUnario<T>`.

- Estas interfaces también tienen especializaciones primitivas (como `IntPredicate`, `LongConsumer`, etc.) para evitar la sobrecarga de autoboxing cuando se trabaja con primitivas.

- También existen versiones binarias de algunas de estas interfaces que aceptan dos parámetros, como `BiPredicate<L, R>`, `BiConsumer<T, U>`, `BiFunction<T, U, R>` y `BinaryOperator<T>`.

- `Predicado<T>` representa una función con valor booleano que toma un objeto de tipo `T `como entrada. Tiene métodos predeterminados `and`, `or` y `negate` para combinar predicados.

- `Consumidor<T>` representa una operación que acepta un único argumento de entrada y no devuelve ningún resultado. Tiene un método predeterminado "andThen" para encadenar consumidores. 

- `Función<T, R>` representa una función que acepta un argumento y devuelve un resultado. Tiene métodos predeterminados `compose` y `andThen` para combinar funciones.

- `Proveedor<T>` representa un proveedor de resultados, no toma argumentos y devuelve un resultado.

- `UnaryOperator<T>` representa una operación en un único operando que produce un resultado del mismo tipo que su operando. Es una especialización de "Función" donde los tipos de argumento y resultado son los mismos.

- Las referencias a métodos proporcionan una forma de hacer referencia a un método sin invocarlo, utilizando el operador `::`. Se pueden utilizar cuando se espera una expresión lambda.

- Hay cuatro tipos de referencias a métodos: a un método estático, a un método de instancia de un objeto de un tipo particular, a un método de instancia de un objeto existente y a un constructor.


## PrácticaPreguntas
**1. ¿Cuáles de las siguientes afirmaciones son verdaderas sobre las interfaces funcionales en Java? (Elija todas las que correspondan).**

**A)** Una interfaz funcional puede tener múltiples métodos "abstractos".  
**B)** Una interfaz funcional puede tener métodos predeterminados y "estáticos".  
**C)** La anotación `@FunctionalInterface` es obligatoria para declarar una interfaz funcional.  
**D)** Las expresiones Lambda se pueden utilizar para crear instancias de interfaces funcionales.



**2. ¿Cuál de las siguientes expresiones lambda implementa correctamente la interfaz `Comparator<String>`?**```java
Comparator<String> comparator = /* lambda expression */;
```**A)** `(s1, s2) -> s1.compareTo(s2)`  
**B)** `(Cadena s1, s2) -> s1.compareTo(s2)`  
**C)** `s1, s2 -> s1.compareTo(s2)`  
**D)** `(s1, s2) -> devolver s1.compareTo(s2);`  
**E)** `(s1, s2) -> { s1.compareTo(s2); }`



**3. ¿Cuál de las siguientes interfaces lambda integradas en Java representa una función que acepta dos argumentos y produce un resultado?**

**A)** `java.util.función.Función`  
**B)** `java.util.function.BiFunction`  
**C)** `java.util.function.Proveedor`  
**D)** `java.util.función.Consumidor`  
**E)** `java.util.function.Predicado`



**4. ¿Cuál es el resultado del siguiente código?**```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> addThree = x -> x + 3;

        Function<Integer, Integer> combinedFunction = multiplyByTwo.andThen(addThree);

        System.out.println(combinedFunction.apply(5));
    }
}
```**A)** `13`  
**B)** `16`  
**C)** `10`  
**D)** `11`  
**E)** `8`



**5. ¿Cuál de las siguientes referencias a métodos reemplaza correctamente la expresión lambda en el código siguiente?**```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<String, Integer> func = str -> Integer.parseInt(str);
        System.out.println(func.apply("123"));
    }
}
```**A)** `Cadena::valorDe`  
**B)** `Entero::valorDe`  
**C)** `Entero::parseInt`  
**D)** `Cadena::parseInt`  
**E)** `Entero::toString`