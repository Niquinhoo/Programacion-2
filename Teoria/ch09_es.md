-"None"
layout: chapter

title: "Capítulo NUEVE"
subtitle: "Corrientes"
exam_objectives:
  - "Utilice objetos Java y flujos primitivos, incluidas expresiones lambda que implementan interfaces funcionales, para crear, filtrar, transformar, procesar y ordenar datos."
  - "Realizar descomposición, concatenación y reducción, así como agrupación y partición en flujos secuenciales y paralelos."

previous_link: "/ch08.html"
previous_title: "Interfaces funcionales y expresiones Lambda"
next_link: "/ch10.html"
next_title: "Concurrencia y subprocesos múltiples"
answers_link: "/ch09a.html"
-"None"## Contenido del capítulo

- [La clase opcional](#la-clase-opcional)
- [Transmisiones](#transmisiones)
    - [¿Qué son las transmisiones?](#qué-son-las-transmisiones)
    - [Creando transmisiones](#creating-streams)
    - [Operaciones intermedias](#operaciones-intermedias)
    - [Operaciones de terminal](#operaciones-de-terminal)
    - [Operaciones diferidas](#operaciones-diez)
- [Corrientes primitivas](#corrientes-primitivas)
- [Filtrado de flujos](#filtrado-flujos)
- [Mapeo de flujos](#mapping-streams)
- [Corrientes en descomposición](#corrientes-en descomposición)
- [Concatenación de transmisiones](#concatenación de transmisiones)
- [Reducción de corrientes](#reducción-de-corrientes)
- [Recopilación de resultados](#recopilación de resultados)
    - [Usando recopiladores básicos](#using-basic-collectors)
    - [Recopilar en mapas](#recopilar-en-mapas)
    - [Agrupación, partición, mapeo y salida](#agrupación-particionamiento-mapeo-y-puesta a salida)
- [Puntos clave](#puntos clave)
- [Preguntas de práctica](#preguntas-de-practica)

---

## La clase opcional

La mayoría de los lenguajes de programación tienen un tipo de datos para representar la ausencia de un valor y se le conoce con muchos nombres:

`NULL, nulo, Ninguno, Nada`

El tipo "nulo" fue introducido en ALGOL W por Tony Hoare en 1965 y se considera uno de los peores errores en informática. En palabras de Tony Hoare:

*Yo lo llamo mi error de mil millones de dólares. Fue la invención de la referencia nula en 1965. En ese momento, estaba diseñando el primer sistema de tipos completo para referencias en un lenguaje orientado a objetos (ALGOL W). Mi objetivo era garantizar que todo uso de referencias fuera absolutamente seguro, y que el compilador realizara la verificación automáticamente. Pero no pude resistir la tentación de incluir una referencia nula, simplemente porque era muy fácil de implementar. Esto ha dado lugar a innumerables errores, vulnerabilidades y caídas del sistema, que probablemente han causado miles de millones de dólares en dolores y daños en los últimos cuarenta años.*

Aún así, algunos se preguntarán cuál es el problema con "nulo".

Bueno, si estás un poco preocupado por los problemas que este código pueda causar, ya sabes la respuesta:```java
String summary = 
  book.getChapter(10)
      .getSummary().toUpperCase();
```El problema con ese código es que si alguno de esos métodos devuelve una referencia "nula" (por ejemplo, si el libro no tiene un décimo capítulo), se generará una "NullPointerException" (la excepción más común en Java) en tiempo de ejecución, deteniendo el programa.

¿Qué podemos hacer para evitar esta excepción?

Quizás la forma más sencilla sea comprobar si hay "nulo". Aquí tienes una forma de hacerlo:```java
String summary = "";
if(book != null) {
    Chapter chapter = book.getChapter(10);
    if(chapter != null) {
        if(chapter.getSummary() != null) {
            summary = chapter.getSummary()
                             .toUpperCase();
        }
    }
}
```No sabes si algún objeto en esta jerarquía puede ser "nulo", por lo que verificas cada objeto. Evidentemente, ésta no es la mejor solución; no es muy práctico y perjudica la legibilidad.

Hay otro problema. ¿Es realmente deseable comprobar si hay "nulo"? Quiero decir, ¿qué pasaría si esos objetos nunca fueran "nulos"? Al marcar "nulo", ocultamos el error y no lo solucionamos.

Por supuesto, esto también es una cuestión de diseño. Por ejemplo, si un capítulo aún no tiene resumen, ¿qué sería mejor usar como valor predeterminado? ¿Una cadena vacía o "nula"?

La clase `java.util.Optional<T>` soluciona este problema.

El trabajo de esta clase es encapsular un valor opcional, que es un objeto que puede ser "nulo".

Usando el ejemplo anterior, si sabemos que no todos los capítulos tienen resumen, en lugar de modelar la clase así:```java
class Chapter {
    private String summary;
    // Other attributes and methods
}
```Podemos usar la clase `Opcional`:```java
class Chapter {
    private Optional<String> summary;
    // Other attributes and methods
}
```Entonces, si hay un valor, la clase "Opcional" simplemente lo envuelve. De lo contrario, un valor vacío se representa mediante el método `Optional.empty()`, que devuelve una instancia única de `Optional`.

Al usar esta clase en lugar de "nulo", declaramos explícitamente que el atributo de resumen es opcional. Entonces, podemos evitar `NullPointerExceptions` mientras tenemos los métodos útiles de `Optional` a nuestra disposición, que revisaremos a continuación.

Primero, veamos cómo crear una instancia de esta clase.

Para obtener un objeto "Opcional" vacío, use:```java
Optional<String> summary = Optional.empty();
```Si está seguro de que un objeto no es "nulo", puede envolverlo en un objeto "Opcional" de esta manera:```java
Optional<String> summary = Optional.of("A summary");
```Se generará una "NullPointerException" si el objeto es "nulo". Sin embargo, puedes utilizar:```java
Optional<String> summary = Optional.ofNullable("A summary");
```Eso devuelve una instancia "Opcional" con el valor especificado si no es "nulo". De lo contrario, devuelve un "Opcional" vacío.

Si quieres saber si un "Opcional" contiene un valor, puedes hacerlo así:```java
if (summary.isPresent()) {
    // Do something
}
```O en un estilo más funcional:```java
summary.ifPresent(s -> System.out.println(s));
// Or summary.ifPresent(System.out::println);
```El método `ifPresent()` toma un `Consumer<T>` como argumento que se ejecuta solo si `Optional` contiene un valor.

Para obtener el valor de un "Opcional", use:```java
String s = summary.get();
```Sin embargo, este método arrojará una `java.util.NoSuchElementException` si `Optional` no contiene un valor, por lo que es mejor usar el método `ifPresent()`.

Alternativamente, si queremos devolver algo cuando "Opcional" no contiene un valor, hay otros tres métodos que podemos usar:```java
String summaryOrDefault = summary.orElse("Default summary");
```El método `orElse()` devuelve el argumento (que debe ser de tipo `T`, en este caso una `String`) cuando el `Optional` está vacío. De lo contrario, devuelve el valor encapsulado.```java
String summaryOrDefault = 
    summary.orElseGet(() -> "Default summary");
```El método `orElseGet()` toma un valor `Proveedor<? extiende T>` como argumento que devuelve un valor cuando `Opcional` está vacío. De lo contrario, devuelve el valor encapsulado.```java
String summaryOrException = 
    summary.orElseThrow(() -> new Exception());
```El método `orElseThrow()` toma un valor `Proveedor<? extiende X>`, donde `X` es el tipo de excepción que se lanzará cuando `Opcional` esté vacío. De lo contrario, devuelve el valor encapsulado.

Hay versiones de la clase `Optional` para trabajar con primitivas, `OptionalInt`, `OptionalLong` y `OptionalDouble`, por lo que puedes usar `OptionalInt` en lugar de `Optional<Integer>`:```java
OptionalInt optionalInt = OptionalInt.of(1);
int i = optionalInt.getAsInt();
```Sin embargo, no se recomienda el uso de estas versiones primitivas, especialmente porque carecen de tres métodos útiles de `Opcional`: `filter()`, `map()` y `flatMap()`. Y dado que "Opcional" solo contiene un valor, la sobrecarga de empaquetar/desempaquetar una primitiva no es significativa.

El método `filter()` devuelve el `Opcional` si hay un valor presente y coincide con el predicado dado. De lo contrario, se devuelve un "Opcional" vacío.```java
String summaryStr = 
    summary.filter(s -> s.length() > 10).orElse("Short summary");
```El método `map()` se usa generalmente para transformar de un tipo a otro. Si el valor está presente, aplica la `Función<? proporcionada. súper T, ? extiende U>` a él. Por ejemplo:```java
int summaryLength = summary.map(s -> s.length()).orElse(0);
```El método `flatMap()` es similar a `map()`, pero toma un argumento de tipo `Function<? super T, Opcional<U>>` y si el valor está presente devuelve el `Opcional` que resulta de aplicar la función proporcionada. De lo contrario, devuelve un "Opcional" vacío.


## Corrientes

Suponga que tiene una lista de estudiantes y los requisitos son extraer los estudiantes con una puntuación de "90,0" o superior y ordenarlos por puntuación en orden ascendente.

Una forma de hacerlo sería:```java
List<Student> studentsScore = new ArrayList<Student>();
for(Student s : students) {
   if(s.getScore() >= 90.0) {
       studentsScore.add(s);
   }
}
Collections.sort(studentsScore, new Comparator<Student>() {
   public int compare(Student s1, Student s2) {
       return Double.compare(s1.getScore(), s2.getScore());
   }
});
```Muy detallado cuando lo comparamos con la implementación que usa flujos:```java
List<Student> studentsScore = students
    .stream()
    .filter(s -> s.getScore() >= 90.0)
    .sorted(Comparator.comparing(Student::getScore))
    .collect(Collectors.toList());
```No te preocupes si no entiendes completamente el código, veremos qué significa más adelante.

### ¿Qué son las transmisiones?

En primer lugar, las transmisiones **NO** son colecciones.

Una definición simple es que las secuencias son *envoltorios* para colecciones o matrices. Envuelven una colección existente (u otra fuente de datos) para admitir operaciones expresadas con lambdas, de modo que usted especifica lo que quiere hacer, no cómo hacerlo. Ya lo viste.

Estas son las características de un arroyo:

- **Los flujos funcionan perfectamente con lambdas.** Todas las operaciones de flujos toman interfaces funcionales como argumentos, por lo que puede simplificar el código con expresiones lambda (y referencias de métodos).

- **Las transmisiones no almacenan sus elementos.** Los elementos se almacenan en una colección o se generan sobre la marcha. Sólo se transportan desde la fuente a través de una serie de operaciones.

- **Las secuencias son inmutables.** Las secuencias no mutan su fuente subyacente de elementos. En cambio, crean una nueva corriente que refleja las transformaciones aplicadas.

- **Las transmisiones no son reutilizables.** Las transmisiones solo se pueden recorrer una vez. Después de ejecutar una operación de terminal (veremos qué significa esto en un momento), debe crear otra secuencia desde la fuente para procesarla aún más.

- **Las secuencias no admiten el acceso indexado a sus elementos.** Nuevamente, las secuencias no son colecciones ni matrices. Lo máximo que puedes hacer es conseguir su primer elemento.

- **Los flujos son fácilmente paralelizables.** Con la llamada de un método (y siguiendo ciertas reglas), puedes hacer que un flujo ejecute sus operaciones simultáneamente, sin tener que escribir ningún código multiproceso.

- **Las operaciones de transmisión son diferidas cuando es posible.** Las transmisiones posponen la ejecución de sus operaciones hasta que se necesitan los resultados o hasta que se sabe cuántos datos se necesitan.

Una cosa que permite esta pereza es la forma en que están diseñadas sus operaciones. La mayoría de ellos devuelven un nuevo flujo, lo que permite encadenar operaciones y formar un canal que permite este tipo de optimizaciones.

Para configurar este canal, usted:

1. Cree la transmisión.
2. Aplicar cero o más operaciones intermedias para transformar el flujo inicial en nuevos flujos.
3. Aplicar una operación terminal para generar un resultado o un *efecto secundario*.

Aquí hay un diagrama para ayudarlo a visualizar esta canalización:```
┌─────────────┐   ┌───────────────────────────┐   ┌───────────────┐
│             │   │  Intermediate Ops         │   │               │
│   Source    │   │ ┌─────┐ ┌──────┐ ┌──────┐ │   │   Terminal    │
│ (Collection │ → │ │ map │→│filter│→│sorted│ │ → │  Operation    │
│  or Array)  │   │ └─────┘ └──────┘ └──────┘ │   │(e.g., collect)│
│             │   │                           │   │               │
└─────────────┘   └───────────────────────────┘   └───────────────┘
       ↑                       ↑                        ↑
       │                       │                        │
    Creation              Processing                 Result
```### Creando transmisiones

Una secuencia está representada por la interfaz `java.util.stream.Stream<T>`. Esto funciona sólo con objetos.

También existen especializaciones para trabajar con tipos primitivos, como `IntStream`, `LongStream` y `DoubleStream`.

Hay muchas formas de crear una transmisión. Comencemos con los tres más populares.

El primero es crear una secuencia a partir de una implementación `java.util.Collection` usando el método `stream()`:```java
List<String> words = Arrays.asList("hello", "hola", "hallo", "ciao");;
Stream<String> stream = words.stream();
```El segundo es crear una secuencia a partir de valores individuales:```java
Stream<String> stream = Stream.of("hello","hola", "hallo", "ciao");
```El tercero es crear una secuencia a partir de una matriz:```java
String[] words = {"hello", "hola", "hallo", "ciao"};
Stream<String> stream = Stream.of(words);
```Sin embargo, hay que tener cuidado con este último método cuando se trabaja con primitivas.

He aquí por qué. Suponga una matriz `int`:```java
int[] nums = {1, 2, 3, 4, 5};
```Cuando creamos una secuencia a partir de esta matriz como esta:```java
Stream.of(nums)
```No estamos creando una secuencia de `Integer`s (`Stream<Integer>`), sino una secuencia de matrices `int` (`Stream<int[]>`). Esto significa que en lugar de tener un flujo con cinco elementos tenemos un flujo de un elemento:```java
System.out.println(Stream.of(nums).count()); // It prints 1!
```El motivo son las firmas del método of:```java
// returns a stream of one element
static <T> Stream<T> of(T t)
// returns a stream whose elements are the specified values
static <T> Stream<T> of(T... values)
```Dado que un int no es un objeto, pero `int[]` sí lo es, el método elegido para crear la secuencia es el primero (`Stream.of(T t)`), no el que tiene las vargs, por lo que se crea una secuencia de `int[]`, pero como solo se pasa una matriz, el resultado es una secuencia de un elemento.

Para resolver esto, podemos forzar a Java a elegir la versión varargs creando una matriz de objetos (con `Integer`):```java
Integer[] nums = {1, 2, 3, 4, 5};
// It prints 5!
System.out.println(Stream.of(nums).count());
```O use una cuarta forma de crear una secuencia (que de hecho se usa dentro de `Stream.of(T...values)`):```java
int[] nums = {1, 2, 3, 4, 5};
// It also prints 5!
System.out.println(Arrays.stream(nums).count());
```O use la versión primitiva `IntStream`:```java
int[] nums = {1, 2, 3, 4, 5};
// It also prints 5!
System.out.println(IntStream.of(nums).count());
```Por lo tanto, no utilice `Stream<T>.of()` cuando trabaje con primitivas.

Aquí hay otras formas de crear transmisiones:```java
static <T> Stream<T> generate(Supplier<T> s)
```Este método devuelve una secuencia *infinita* donde cada elemento es generado por el "Proveedor" proporcionado y generalmente se usa con el método:```java
Stream<T> limit(long maxSize)
```Eso trunca la secuencia para que no tenga una longitud superior a "maxSize".

Por ejemplo:```java
Stream<Double> s = Stream.generate(new Supplier<Double>() {
   public Double get() {
       return Math.random();
   }
}).limit(5);
```O:```java
Stream<Double> s = Stream.generate(() -> Math.random()).limit(5);
```O simplemente:```java
Stream<Double> s = Stream.generate(Math::random).limit(5);
```Lo que genera una secuencia de cinco "dobles" aleatorios.

Luego tenemos el método `iterar`:```java
static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
```Devuelve una secuencia *infinita* producida por la aplicación iterativa de una función `f` a un elemento inicial (semilla). El primer elemento (`n = 0`) en la secuencia será la semilla proporcionada. Para `n > 0`, el elemento en la posición `n` será el resultado de aplicar la función `f` al elemento en la posición `n - 1`. Por ejemplo:```java
Stream<Integer> s = Stream.iterate(1, new UnaryOperator<Integer>() {
   @Override
   public Integer apply(Integer t) {
       return t * 2; }
}).limit(5);
```O simplemente:```java
Stream<Integer> s = Stream.iterate(1, t -> t * 2).limit(5);
```Eso genera los elementos `1`, `2`, `4`, `8`, `16`.

Hay una clase `Stream.Builder<T>` (que sigue el patrón de diseño del constructor) con métodos que agregan un elemento al flujo que se está construyendo:```java
void accept(T t)
default Stream.Builder<T> add(T t)
```Por ejemplo:```java
Stream.Builder<String> builder = Stream.<String>builder().add("h").add("e").add("l").add("l");
builder.accept("o");
Stream<String> s = builder.build();
````IntStream` y `LongStream` definen los métodos:```java
static IntStream range(int startInclusive, int endExclusive)
static IntStream rangeClosed(int startInclusive, int endInclusive)
static LongStream range(long startInclusive, long endExclusive)
static LongStream rangeClosed(long startInclusive, long endInclusive)
```Eso devuelve una secuencia secuencial para el rango de elementos "int" o "long". Por ejemplo:```java
// stream of 1, 2, 3
IntStream s = IntStream.range(1, 4);
// stream of 1, 2, 3, 4
IntStream s = IntStream.rangeClosed(1, 4);
```Además, existen métodos en la API de Java que generan secuencias. Por ejemplo:```java
IntStream s1 = new Random().ints(5, 1, 10);
```Lo que devuelve un `IntStream` de cinco `int` aleatorios del uno (inclusive) al diez (exclusivo).

### Operaciones Intermedias

Puede identificar fácilmente operaciones intermedias; siempre devuelven una nueva corriente. Esto permite conectar las operaciones.

Por ejemplo:```java
Stream<String> s = Stream.of("m", "k", "c", "t")
    .sorted()
    .limit(3)
```Una característica importante de las operaciones intermedias es que no procesan los elementos hasta que se invoca una operación de terminal, lo que significa que son vagas.

Las operaciones intermedias pueden ser *sin estado* o *con estado*.

Las operaciones sin estado no retienen el estado de elementos anteriores cuando procesan un nuevo elemento, por lo que cada una puede procesarse independientemente de las operaciones en otros elementos.

Las operaciones con estado, como "distintas" y "ordenadas", requieren procesar todo el flujo o realizar un seguimiento del estado de los elementos procesados ​​previamente para producir un resultado.

La siguiente tabla resume los métodos de la interfaz "Stream" que representan operaciones intermedias.

| Método | Tipo | Descripción |
|--------------------------------------------------------------------------------------------------|-----------|--------------------------------------------------------------------------------------------------------------------|
| `Flujo<T> distinto()` | Con estado | Devuelve una secuencia que consta de distintos elementos.                                                             |
| `Filtro de flujo<T>(Predicado<? predicado super T>)` | Apátrida | Devuelve una secuencia de elementos que coinciden con el predicado dado.                                                      |
| `<R> Stream<R> flatMap(Función<? super T,? extiende Stream<? extiende R>> mapper)` | Apátrida | Devuelve una secuencia con el contenido producido al aplicar la función de mapeo proporcionada a cada elemento. También hay versiones para `int`, `long` y `double`. |
| `Límite de flujo<T>(long maxSize)` | Con estado | Devuelve una secuencia truncada para que su longitud no supere "maxSize".                                              |
| `<R> Stream<R> map(Función<? super T,? extiende R> mapper)` | Apátrida | Devuelve una secuencia que consta de los resultados de aplicar la función dada a los elementos de esta secuencia. También hay versiones para `int`, `long` y `double`. |
| `Transmitir<T> vistazo(Consumidor<? super T> acción)` | Apátrida | Devuelve una secuencia con los elementos de esta secuencia, realizando la acción proporcionada en cada elemento.                |
| `Stream<T> skip(long n)` | Con estado | Devuelve una secuencia con los elementos restantes de esta secuencia después de descartar los primeros `n` elementos.                |
| `Flujo<T> ordenado()` | Con estado | Devuelve una secuencia ordenada según el orden natural de sus elementos.                                           |
| `Secuencia<T> ordenada(Comparador<? super T> comparador)` | Con estado | Devuelve una secuencia ordenada según el "Comparador" proporcionado.                                                   |
| `Flujo<T> paralelo()` | N/A | Devuelve una secuencia equivalente que es paralela.                                                                    |
| `Flujo<T> secuencial()` | N/A | Devuelve una secuencia equivalente que es secuencial.                                                                  |
| `Flujo<T> desordenado()` | N/A | Devuelve una secuencia equivalente que no está ordenada.                                                                   |

### Operaciones de terminales

tu puedesTambién identifica fácilmente las operaciones de la terminal, siempre devuelven algo más que una secuencia.

Después de realizar la operación del terminal, la canalización de flujo se *consume* y ya no se puede utilizar. Por ejemplo:```java
int[] digits = {0, 1, 2, 3, 4 , 5, 6, 7, 8, 9};
IntStream s = IntStream.of(digits);
long n = s.count();
System.out.println(s.findFirst()); // An exception is thrown
```Si necesita recorrer la misma secuencia nuevamente, debe regresar a la fuente de datos para obtener una nueva. Por ejemplo:```java
int[] digits = {0, 1, 2, 3, 4 , 5, 6, 7, 8, 9};
long n = IntStream.of(digits).count();
System.out.println(IntStream.of(digits).findFirst()); // OK
```La siguiente tabla resume los métodos de la interfaz "Stream" que representan operaciones de terminal.

| Método | Descripción |
|-------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|
| `boolean allMatch(Predicado<? super T> predicado)` | Devuelve si todos los elementos de esta secuencia coinciden con el predicado proporcionado. Si la secuencia está vacía, se devuelve "verdadero" y el predicado no se evalúa. |
| `boolean anyMatch(Predicado<? super T> predicado)` | Devuelve si algún elemento de esta secuencia coincide con el predicado proporcionado. Si la secuencia está vacía, se devuelve "falso" y el predicado no se evalúa. |
| `boolean noneMatch(Predicado<? super T> predicado)` | Devuelve si ningún elemento de esta secuencia coincide con el predicado proporcionado. Si la secuencia está vacía, se devuelve "verdadero" y el predicado no se evalúa. |
| `Opcional<T> findAny()` | Devuelve un "Opcional" que describe algún elemento de la secuencia.                                                   |
| `Opcional<T> findFirst()` | Devuelve un "Opcional" que describe el primer elemento de esta secuencia.                                             |
| `<R,A> R recopilar(Coleccionista<? super T,A,R> recopilador)` | Realiza una operación de reducción mutable en los elementos de esta secuencia utilizando un `Collector`.                      |
| `cuenta larga()` | Devuelve el recuento de elementos de esta secuencia.                                                                  |
| `void forEach(Consumidor<? super T> acción)` | Realiza una acción para cada elemento de esta secuencia.                                                            |
| `void forEachOrdered(Consumidor<? super T> acción)` | Realiza una acción para cada elemento de esta secuencia, en el orden de encuentro de la secuencia si la secuencia tiene un orden de encuentro definido. |
| `Opcional<T> max(Comparador<? super T> comparador)` | Devuelve el elemento máximo de esta secuencia según el `Comparador` proporcionado.                             |
| `Opcional<T> min(Comparador<? super T> comparador)` | Devuelve el elemento mínimo de esta secuencia según el "Comparador" proporcionado.                             |
| `T reducir(T identidad, BinaryOperator<T> acumulador)` | Realiza una reducción de los elementos de esta secuencia, utilizando el valor de identidad proporcionado y una función de acumulación asociativa, y devuelve el valor reducido. |
| `Objeto[] toArray()` | Devuelve una matriz que contiene los elementos de esta secuencia.                                                       |
| `<A> A[] toArray(IntFunction<A[]> generador)` | Devuelve una matriz que contiene los elementos de esta secuencia, utilizando la función generadora proporcionada para asignar la matriz devuelta. |
| `Iterador<T> iterador()` | Devuelve un iterador para los elementos de la secuencia.                                                            |
| `Divisor<T> divisorio()`| Devuelve un divisor para los elementos de la secuencia.                                                          |

### Operaciones perezosas

Las operaciones intermedias se difieren hasta que se invoca una operación terminal. La razón es que las operaciones intermedias generalmente pueden fusionarse u optimizarse mediante una operación terminal.

Tomemos, por ejemplo, esta canalización de flujo:```java
Stream.of("sun", "pool", "beach", "kid", "island", "sea", "sand")
    .map(str -> str.length())
    .filter(i -> i > 3)
    .limit(2)
    .forEach(System.out::println);
```Esto es lo que hace:

- Genera un flujo de cuerdas,
- Luego convierta la secuencia en una secuencia de `int`s (que representa la longitud de cada cadena)
- Luego filtra las longitudes mayores a tres,
- Luego toma los dos primeros elementos de la corriente y
- Finalmente, imprime esos dos elementos.

Y puede pensar que la operación de mapa se aplica a los siete elementos, luego la operación de "filtro" nuevamente a los siete, luego selecciona los dos primeros y finalmente imprime los valores.

Pero no es así como funciona. Si modificamos las expresiones lambda de `map` y `filter` para imprimir un mensaje:```java
Stream.of("sun", "pool", "beach", "kid", "island", "sea", "sand")
    .map(str -> {
        System.out.println("Mapping: " + str);
        return str.length();
    })
    .filter(i -> {
        System.out.println("Filtering: " + i);
        return i > 3;
    })
    .limit(2)
    .forEach(System.out::println);
```El orden de evaluación será revelado:```java
Mapping: sun
Filtering: 3
Mapping: pool
Filtering: 4
4
Mapping: beach
Filtering: 5
5
```En este ejemplo, podemos ver que la secuencia aplicó operaciones solo hasta que encontró suficientes elementos para devolver un resultado (debido a la operación `limit(2)`). Esto se llama *cortocircuito*.

Las operaciones de cortocircuito hacen que se procesen operaciones intermedias hasta que se pueda producir un resultado.

De esta manera, debido a operaciones diferidas y de cortocircuito, las secuencias no ejecutan todas las operaciones en todos sus elementos. En cambio, los elementos de la secuencia pasan por un proceso de operaciones hasta el punto en que se puede deducir o generar un resultado.

Puede ver el cortocircuito como una subclasificación. Sólo hay una operación intermedia de cortocircuito:```java
Stream<T> limit(long maxSize)
```Porque no necesita procesar todos los elementos de la secuencia para crear una secuencia de un tamaño determinado.

El resto son terminales:```java
boolean anyMatch(Predicate<? super T> predicate)
boolean allMatch(Predicate<? super T> predicate)
boolean noneMatch(Predicate<? super T> predicate)
Optional<T> findFirst()
Optional<T> findAny()
```Porque tan pronto como encuentre un elemento coincidente, no es necesario continuar procesando la transmisión.


## Corrientes primitivas

La mayoría de las veces, usaremos un `Stream<T>` que contiene objetos como elementos. Sin embargo, también existen flujos especializados para manejar tipos primitivos como `int`, `long` y `double` que le permiten evitar la sobrecarga de elementos de auto-boxing y auto-unboxing en sus clases contenedoras. Estos flujos primitivos son "IntStream", "LongStream" y "DoubleStream".

Cada flujo primitivo tiene métodos análogos a los de la clase `Stream` normal, como `map()` (transforma elementos), `filter()` (selecciona elementos basándose en un predicado), `reduce()` (agrega elementos), etc. Pero debido a que solo pueden tratar con sus tipos primitivos correspondientes, también existen métodos especializados para manejarlos. Repasemos los más importantes.

El método `average()` devuelve un `OptionalDouble` con la media aritmética de los elementos, o un `OptionalDouble` vacío si la secuencia primitiva está vacía:```java
IntStream stream = IntStream.range(1, 10);
OptionalDouble ave = stream.average();
System.out.println(ave.getAsDouble());
```Este código imprime el promedio de los números del 1 al 9 (sin incluir el 10):```
5.0
```Si necesita convertir una secuencia primitiva en una secuencia de objetos normal, utilice el método `boxed()`:```java
Stream<Double> boxed = DoubleStream.of(1.2, 2.4).boxed();
```Para encontrar el valor máximo en la secuencia primitiva, use `max()`:```java
IntStream stream = IntStream.of(1, 10, 2, 20);
OptionalInt max = stream.max();
System.out.println(max.getAsInt());
```Esto imprime:```
20
```Cada flujo primitivo tiene su propio método `max()` que devuelve su tipo `Optional` correspondiente (`OptionalInt`, `OptionalLong`, `OptionalDouble`). Lo mismo ocurre con `min()`.

Una peculiaridad de `IntStream` y `LongStream` es que tienen métodos especiales `range()` y `rangeClosed()` para generar una secuencia de números en un rango.

`range(int a, int b)` crea un `IntStream` de valores desde `a` (inclusive) hasta `b` (exclusivo). `rangeClosed(int a, int b)` hace lo mismo incluyendo `b`:```java
IntStream stream = IntStream.range(1, 5);
stream.forEach(System.out::println);
```Esto imprime:```
1
2
3
4
```Mientras:```java
LongStream stream = LongStream.rangeClosed(1, 5);
stream.forEach(System.out::println);
```Huellas dactilares:```
1
2
3
4
5
```Tenga en cuenta que no hay métodos `range()` o `rangeClosed()` en `DoubleStream`.

El método `sum()` devuelve la suma de todos los elementos:```java
IntStream stream = IntStream.of(1, 10, 2, 20);
int sum = stream.sum();
System.out.println(sum);
```Esto imprime:```
33
```Nuevamente, cada flujo primitivo tiene su propio método `sum()` dedicado que devuelve el resultado del tipo primitivo (`int`, `long`, `double`).

Finalmente, cada flujo primitivo tiene un método `summaryStatistics()` que devuelve un resumen de las estadísticas de los elementos. Veamos un ejemplo usando `IntStream`:```java
IntStream stream = IntStream.of(1, 10, 2, 20);
IntSummaryStatistics stats = stream.summaryStatistics();
System.out.println(stats);
```Esto imprime:```
IntSummaryStatistics{count=4, sum=33, min=1, average=8.250000, max=20}
````LongStream` y `DoubleStream` tienen clases análogas `LongSummaryStatistics` y `DoubleSummaryStatistics`.

Estos objetos de estadísticas de resumen proporcionan métodos para obtener cada estadística por separado (`getCount()`, `getSum()`, `getMin()`, `getAverage()`, `getMax()`).

Si necesita estadísticas más avanzadas, puede utilizar un `Collector` y los métodos `summarizingInt()`, `summarizingLong()` o `summarizingDouble()` como argumentos:```java
List<Integer> list = List.of(1, 10, 2, 20);
IntSummaryStatistics stats = list.stream()
        .collect(Collectors.summarizingInt(i -> i));
System.out.println(stats);  
```Esto imprime:```
IntSummaryStatistics{count=4, sum=33, min=1, average=8.250000, max=20}
```Ahora hablemos con más detalle sobre algunas de las operaciones de transmisión más comunes.


## Filtrado de secuencias
El filtrado es una de las operaciones más comunes cuando se trabaja con secuencias en Java. Permite seleccionar sólo los elementos que satisfacen un predicado determinado, descartando el resto. El método `filter()` se utiliza para este propósito:```java
Stream<T> filter(Predicate<? super T> predicate);
```El método `filter()` toma una interfaz funcional `Predicate` como argumento. Un "Predicado" es una función que toma un elemento y devuelve un "booleano". Solo los elementos para los cuales el predicado devuelve "verdadero" se incluirán en la secuencia resultante.

Repasemos un ejemplo sencillo:```java
List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenList = list.stream()
        .filter(i -> i % 2 == 0)
        .collect(Collectors.toList());
System.out.println(evenList);
```Este código filtra la lista original, manteniendo solo los números pares. Imprime:```
[2, 4, 6, 8, 10]
```Puede encadenar múltiples llamadas `filter()` para aplicar varias condiciones:```java
List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> filteredList = list.stream()
        .filter(i -> i > 3)
        .filter(i -> i < 8)
        .collect(Collectors.toList());
System.out.println(filteredList);
```Esto selecciona los números mayores que 3 y menores que 8:```
[4, 5, 6, 7]
```La interfaz `Predicate` también tiene métodos predeterminados que le permiten combinar predicados usando operaciones lógicas:
- `Predicado predeterminado<T> y(Predicado<? super T> otro)`
- `Predicado predeterminado<T> o (Predicado<? super T> otro)` 
- `Predicado predeterminado<T> negar()`

Por ejemplo, para obtener los números mayores que 3 y menores que 8 también puedes hacer:```java
List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> filteredList = list.stream()
        .filter(i -> i > 3 && i < 8)
        .collect(Collectors.toList());
```O usando métodos `Predicado`:```java
Predicate<Integer> greaterThan3 = i -> i > 3;
Predicate<Integer> lessThan8 = i -> i < 8;
List<Integer> filteredList = list.stream()
        .filter(greaterThan3.and(lessThan8))
        .collect(Collectors.toList());
```El método `filter()` no tiene estado, lo que significa que la ejecución del predicado de un elemento no afecta la ejecución de otro.

Un método muy útil relacionado con `filter()` es `distinct()`:```java
Stream<T> distinct();
```Este método devuelve un flujo de elementos únicos, descartando los duplicados:```java
List<Integer> list = List.of(1, 2, 2, 3, 4, 4, 5);
List<Integer> distinctList = list.stream()
        .distinct()
        .collect(Collectors.toList());
System.out.println(distinctList);
```Esto imprime:```
[1, 2, 3, 4, 5]
```Puede pensar en `distinct()` como una operación de filtrado especial.

Finalmente, existen otros dos métodos similares a `filter()` pero con un propósito diferente:
- `por defecto Stream<T> takeWhile(Predicado<? super T> predicado)`
- `predeterminado Stream<T> dropWhile(Predicado<? super T> predicado)`

`takeWhile()` devuelve una secuencia que contiene el prefijo más largo de elementos tomados de la secuencia original que coinciden con el predicado dado.```java
List<Integer> list = List.of(2, 4, 6, 7, 8, 10, 11);
List<Integer> prefixList = list.stream()
        .takeWhile(i -> i % 2 == 0)
        .collect(Collectors.toList());
System.out.println(prefixList);
```Esto selecciona los números pares desde el comienzo de la secuencia hasta encontrar el primer número impar (7):```
[2, 4, 6]
```Lo opuesto es `dropWhile()`, que descarta el prefijo más largo de elementos que satisfacen el predicado y devuelve una secuencia de los elementos restantes:```java
List<Integer> list = List.of(2, 4, 6, 7, 8, 10, 11);
List<Integer> postfixList = list.stream()
        .dropWhile(i -> i % 2 == 0)
        .collect(Collectors.toList());
System.out.println(postfixList);
```Esto descarta los números pares iniciales y devuelve el resto de la secuencia:```
[7, 8, 10, 11]
```Es importante tener en cuenta que los predicados utilizados en `takeWhile()` y `dropWhile()` deben ser sin estado. La ejecución de un elemento no debería afectar la ejecución de otro; de lo contrario, los resultados serán impredecibles.


## Mapeo de flujos
Cuando trabajamos con flujos, a menudo necesitamos transformar los elementos de un tipo a otro o extraer ciertos datos de ellos. Aquí es donde entran en juego las operaciones `map()` y `flatMap()`.

El método `map()` aplica una función a cada elemento del flujo, transformándolo en un nuevo elemento. Es como tener una máquina que toma materias primas (los elementos originales) y produce productos refinados (los elementos transformados).```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```El método `map()` toma una `Función` como argumento, que es una interfaz que representa una función que acepta un argumento y devuelve un resultado. En este caso, toma un elemento de tipo "T" y devuelve un elemento de tipo "R".

He aquí un ejemplo:```java
List<String> list = List.of("1", "2", "3", "4", "5");
List<Integer> intList = list.stream()
        .map(Integer::parseInt)
        .collect(Collectors.toList());
System.out.println(intList);
```Este código convierte una lista de cadenas en una lista de números enteros usando el método `parseInt` de la clase `Integer`. Imprime:```
[1, 2, 3, 4, 5]
```Puede encadenar múltiples operaciones `map()` para realizar transformaciones sucesivas:```java
List<String> list = List.of("1", "2", "3", "4", "5");
List<Integer> doubledList = list.stream()
        .map(Integer::parseInt)
        .map(i -> i * 2)
        .collect(Collectors.toList());
System.out.println(doubledList);
```Esto primero convierte las cadenas a números enteros y luego multiplica cada número por 2:```
[2, 4, 6, 8, 10]
```Ahora bien, ¿qué pasa si en lugar de transformar cada elemento, quieres extraer múltiples elementos de cada uno? Aquí es donde entra en juego `flatMap()`.

`flatMap()` es como tener una máquina que toma contenedores llenos de materias primas, desempaqueta cada contenedor, procesa los materiales y luego genera los productos refinados en una sola secuencia.```java
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
```El método `flatMap()` toma una función que devuelve una secuencia para cada elemento. Luego, aplana todas estas corrientes en una sola.

Un caso de uso común es cuando tienes un flujo de listas y deseas procesar los elementos de todas las listas como un solo flujo:```java
List<List<Integer>> listOfLists = List.of(
        List.of(1, 2, 3),
        List.of(4, 5, 6),
        List.of(7, 8, 9)
);
List<Integer> flattenedList = listOfLists.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
System.out.println(flattenedList);
```Este código aplana la lista de listas en una sola lista:```
[1, 2, 3, 4, 5, 6, 7, 8, 9]
```Cuando se trabaja con flujos primitivos, existen operaciones de mapeo especializadas para evitar costos de boxing y unboxing:
- `IntStream mapToInt(ToIntFunction<? super T> asignador)`
- `LongStream mapToLong(ToLongFunction<? super T> asignador)`
- `DoubleStream mapToDouble(ToDoubleFunction<? super T> asignador)`

Estos métodos toman `ToIntFunction`, `ToLongFunction` y `ToDoubleFunction` respectivamente, y devuelven `IntStream`, `LongStream` y `DoubleStream`.```java
List<String> list = List.of("1", "2", "3", "4", "5");
IntStream intStream = list.stream()
        .mapToInt(Integer::parseInt);
intStream.forEach(System.out::println);
```Este código convierte el flujo de cadenas en un `IntStream` e imprime cada elemento:```
1
2
3
4
5
```También puedes asignar un tipo primitivo a otro:```java
IntStream intStream = IntStream.range(1, 6);
DoubleStream doubleStream = intStream.mapToDouble(i -> i / 2.0);
doubleStream.forEach(System.out::println);
```Esto convierte un `IntStream` en un `DoubleStream`, dividiendo cada número por 2:```
0.5
1.0
1.5
2.0
2.5
```## Corrientes en descomposición
Cuando trabajamos con transmisiones, a veces necesitamos dividirlas en partes más pequeñas, analizar sus elementos o combinarlos de determinadas maneras. Esto es lo que llamamos flujos de descomposición y existen varias operaciones que nos permiten hacerlo.

Primero, hablemos de `skip()` y `limit()`. Estos métodos nos permiten cortar un flujo en partes, descartando algunos elementos y conservando otros.

`skip(long n)` devuelve una secuencia que descarta los primeros `n` elementos de la secuencia original. Es como cortar la parte superior de un tronco.

He aquí un ejemplo:```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
List<Integer> skippedList = list.stream()
        .skip(2)
        .collect(Collectors.toList());
System.out.println(skippedList);
```Esto omite los dos primeros elementos y recopila el resto en una nueva lista:```
[3, 4, 5]
```Por otro lado, `limit(long maxSize)` devuelve una secuencia que trunca la secuencia original para que no tenga más longitud que `maxSize`. Es como cortar la parte inferior de un tronco.

He aquí un ejemplo:```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
List<Integer> limitedList = list.stream()
        .limit(3)
        .collect(Collectors.toList());
System.out.println(limitedList);
```Esto mantiene sólo los primeros tres elementos y descarta el resto:```
[1, 2, 3]
```Puedes combinar `skip()` y `limit()` para extraer una subsecuencia:```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
List<Integer> subList = list.stream()
        .skip(1)
        .limit(3)
        .collect(Collectors.toList());
System.out.println(subList);
```Esto omite el primer elemento y luego toma los tres siguientes:```
[2, 3, 4]
```Ahora, hablemos de `forEach()` y `forEachOrdered()`. Estos métodos nos permiten realizar una acción en cada elemento de la secuencia.

`forEach(Acción del consumidor)` realiza la acción dada en cada elemento. No se garantiza que el orden de procesamiento sea el orden de encuentro si la transmisión es paralela.```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
list.stream()
    .forEach(System.out::println);
```Esto imprime cada elemento de la secuencia:```
1
2
3
4
5
````forEachOrdered(Acción del consumidor)` es similar, pero garantiza que la acción se realiza en los elementos en el orden de encuentro de la secuencia si es una secuencia paralela:```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
list.stream()
    .forEachOrdered(System.out::println);
```Esto también imprime cada elemento, pero asegurando el orden:```
1
2
3
4
5
```Los métodos `allMatch()`, `anyMatch()` y `noneMatch()` nos permiten verificar si ciertas condiciones se cumplen para los elementos de la secuencia.

`allMatch(Predicado predicado)` devuelve `verdadero` si todos los elementos satisfacen el predicado, `falso` en caso contrario:```java
List<Integer> list = List.of(2, 4, 6, 8, 10);
boolean allEven = list.stream()
        .allMatch(i -> i % 2 == 0);
System.out.println(allEven);
```El ejemplo anterior comprueba si todos los elementos son pares:```
true
````anyMatch(Predicado predicado)` devuelve `verdadero` si algún elemento satisface el predicado, `falso` en caso contrario:```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
boolean anyEven = list.stream()
        .anyMatch(i -> i % 2 == 0);
System.out.println(anyEven);
```Esto comprueba si algún elemento es par:```
true
````noneMatch(Predicado predicado)` devuelve `verdadero` si ningún elemento satisface el predicado, `falso` en caso contrario:```java
List<Integer> list = List.of(1, 3, 5, 7, 9);
boolean noneEven = list.stream()
        .noneMatch(i -> i % 2 == 0);
System.out.println(noneEven);
```Esto comprueba si ningún elemento es par:```
true
```Los métodos `findFirst()` y `findAny()` devuelven un elemento de la secuencia, si existe.

`findFirst()` devuelve un `Opcional` que describe el primer elemento de la secuencia, o un `Opcional` vacío si la secuencia está vacía:```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
Optional<Integer> firstElem = list.stream()
        .findFirst();
System.out.println(firstElem.get());
```Esto encuentra e imprime el primer elemento:```
1
````findAny()` devuelve un `Opcional` que describe algún elemento de la secuencia, o un `Opcional` vacío si la secuencia está vacía. En transmisiones paralelas, es útil cuando no te importa el elemento específico, solo existe ese:```java
List<Integer> list = List.of(1, 2, 3, 4, 5);
Optional<Integer> anyElem = list.parallelStream()
        .findAny();
System.out.println(anyElem.get());
```El ejemplo anterior busca e imprime cualquier elemento (el elemento específico no está garantizado debido al procesamiento paralelo):```
3
```## Concatenar secuencias
A veces, cuando trabajamos con secuencias, necesitamos combinarlas, fusionando sus elementos en una sola secuencia. Esto es lo que llamamos concatenar flujos y hay varias formas de lograrlo en Java.

La forma más sencilla de concatenar transmisiones es mediante el método `concat()`. Este método estático toma dos flujos como entrada y devuelve un nuevo flujo que es la concatenación de los dos flujos de entrada:```java
static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
```Es como unir dos tuberías, dejando que el agua (elementos) fluya de una a otra.

Veamos un ejemplo:```java
Stream<Integer> stream1 = Stream.of(1, 2, 3);
Stream<Integer> stream2 = Stream.of(4, 5, 6);
Stream<Integer> concatenated = Stream.concat(stream1, stream2);
concatenated.forEach(System.out::println);
```Esto concatena `stream1` y `stream2` e imprime los elementos del flujo resultante:```
1
2
3
4
5
6
```Es importante tener en cuenta que `concat()` es un método estático y no modifica las secuencias originales. En su lugar, crea una nueva secuencia que extrae perezosamente elementos de la primera secuencia y luego de la segunda secuencia cuando se solicita.

Además, tenga en cuenta que sólo puede concatenar transmisiones del mismo tipo. Si intenta concatenar secuencias de diferentes tipos, obtendrá un error de compilación.

Otra forma de concatenar transmisiones es mediante el uso del método `flatMap()` junto con `Stream.of()`.

`Stream.of()` crea una secuencia a partir de un número variable de argumentos. Puede pasar los flujos que desea concatenar como argumentos a `Stream.of()` y luego usar `flatMap()` para aplanar el flujo de flujos resultante en un solo flujo:```java
Stream<Integer> stream1 = Stream.of(1, 2, 3);
Stream<Integer> stream2 = Stream.of(4, 5, 6);
Stream<Integer> concatenated = Stream.of(stream1, stream2)
        .flatMap(stream -> stream);
concatenated.forEach(System.out::println);
```Este código hace lo mismo que el ejemplo anterior, pero usando `flatMap()` y `Stream.of()`.

Este enfoque es más detallado que usar `concat()` directamente, pero puede ser útil cuando tienes una colección de secuencias que deseas concatenar.

Por ejemplo, digamos que tiene una lista de transmisiones:```java
List<Stream<Integer>> listOfStreams = List.of(
        Stream.of(1, 2, 3),
        Stream.of(4, 5, 6),
        Stream.of(7, 8, 9)
);
```Puedes concatenar todas estas secuencias en una usando `flatMap()` y `Stream.of()`:```java
Stream<Integer> concatenated = listOfStreams.stream()
        .flatMap(stream -> stream);
concatenated.forEach(System.out::println);
```Esto imprime:```
1
2
3
4
5
6
7
8
9
```Aquí, primero creamos una secuencia a partir de la `Lista` de secuencias usando el método `stream()`. Luego, usamos `flatMap()` para aplanar este flujo de flujos en un solo flujo.

Es como tener un montón de tuberías y unirlas todas en una sola tubería grande.

Sin embargo, una cosa a tener en cuenta al concatenar transmisiones es el orden de encuentro. La secuencia resultante tendrá los elementos de la primera secuencia seguidos de los elementos de la segunda secuencia, y así sucesivamente, en el orden en que fueron concatenados.


## Reducir flujos
Cuando trabajamos con transmisiones, a menudo necesitamos combinar los elementos de alguna manera para producir un resultado único. Esto es lo que llamamos reducir una secuencia y es una de las operaciones más poderosas en la API de Java Streams.

La operación `reduce()` nos permite realizar una reducción de los elementos del flujo, utilizando una función de acumulación asociativa. Es como cocinar un plato:

1. Comienzas con un montón de ingredientes crudos (los elementos de la corriente).

2. Aplicas una receta (la función de acumulación) para combinarlos.

3. Se obtiene un solo plato cocido (resultado de la reducción).

El método `reduce()` tiene tres formas:```java
Optional<T> reduce(BinaryOperator<T> accumulator)

T reduce(T identity, BinaryOperator<T> accumulator)

<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
```Empecemos por el primero. Esta forma de `reduce()` toma un solo parámetro: la función de acumulación. Este es un "BinaryOperator", lo que significa que es una función que toma dos elementos de la secuencia y los combina en uno. 

Por ejemplo, digamos que tenemos un flujo de números enteros y queremos encontrar su suma:```java
Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
Optional<Integer> sum = stream.reduce((a, b) -> a + b);
System.out.println(sum.get());
```Esto imprime:```
15
```Aquí, la función de acumulación `(a, b) -> a + b` toma dos números enteros y devuelve su suma. La operación `reduce()` aplica esta función a los elementos del flujo, de dos en dos, hasta que se hayan procesado todos los elementos y se obtenga un único resultado.

Es importante tener en cuenta que esta forma de `reduce()` devuelve un `Opcional`. Esto se debe a que la secuencia podría estar vacía, en cuyo caso no habría elementos que reducir y, por lo tanto, no habría resultados que devolver. El "Opcional" nos permite manejar este caso con elegancia.

La segunda forma de `reduce()` toma dos parámetros: un valor de identidad y la función de acumulación.

El valor de identidad es el punto de partida de la reducción y también es el valor que se devolverá si la secuencia está vacía. Es como el ingrediente base de nuestra analogía culinaria.```java
Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
Integer sum = stream.reduce(0, (a, b) -> a + b);
System.out.println(sum);
```Esto también imprime:```
15
```Pero en este caso, comenzamos la reducción con 0 y, como resultado, obtenemos un "Entero" simple. Esto no es "Opcional" porque, incluso si la secuencia está vacía, aún podemos devolver el valor de identidad.

La tercera forma de `reduce()` es un poco más compleja. Se necesitan tres parámetros: un valor de identidad, una función de acumulación y una función combinadora.

El valor de identidad y la función de acumulación tienen los mismos propósitos que en la segunda forma. La función combinador se utiliza para combinar los resultados de la reducción cuando la secuencia se procesa en paralelo.

Esta forma de `reduce()` es útil para el procesamiento paralelo, asegurando que la operación de reducción se realice correctamente en varios subprocesos.

Por ejemplo, digamos que queremos concatenar un flujo de cadenas:```java
Stream<String> stream = Stream.of("a", "b", "c", "d", "e");
String concatenated = stream.reduce("", (a, b) -> a + b, (a, b) -> a + b);
System.out.println(concatenated);
```Esto imprime:```
abcde
```Aquí, el valor de identidad es una cadena vacía, la función de acumulación concatena dos cadenas y la función combinadora también concatena dos cadenas.

En este caso, la función combinadora es necesaria para garantizar la corrección en el procesamiento paralelo, aunque la concatenación de cadenas sea asociativa.

Por ejemplo, supongamos que queremos calcular la suma de las longitudes de una lista de cadenas, pero queremos dar peso adicional a las cadenas que comienzan con una vocal duplicando su longitud:```java
boolean startsWithVowel(String str) {
    return str.matches("^[AEIOUaeiou].*");
}

// ...

Stream<String> stream = Stream.of("apple", "banana", "orange", "grape", "pear");

int sumOfLengths = stream.reduce(0, 
    (sum, str) -> sum + (startsWithVowel(str) ? str.length() * 2 : str.length()), 
    Integer::sum);

System.out.println(sumOfLengths);
```Este código imprime:```
37
```Aquí, el valor de identidad es 0, la función de acumulación suma la longitud duplicada de una cadena (si comienza con una vocal) o su longitud normal a la suma acumulada, y la función combinadora suma dos resultados intermedios.

En este ejemplo, la función combinadora `Integer::sum` es importante para combinar correctamente sumas parciales cuando la secuencia se procesa en paralelo, asegurando que el resultado final sea preciso independientemente del orden de procesamiento.


## Recopilación de resultados
Después de procesar una secuencia, a menudo necesitamos recopilar los resultados en una estructura de datos para su uso posterior. Aquí es donde entran en juego la operación `collect()` y la clase `Collectors`.

### Uso de recopiladores básicos

El método `collect()` es una operación de terminal que nos permite acumular los elementos de una secuencia en una colección u otra estructura de datos. Se necesita un "Collector", que especifica cómo se deben recopilar los elementos.

La clase `Collectors` proporciona una amplia variedad de recopiladores predefinidos para casos de uso comunes. Hemos utilizado `Collectors.toList()` en algunos de los ejemplos anteriores, pero veamos algunos de estos recopiladores con más detalle.

Los recopiladores más sencillos son `toList()` y `toSet()`, que recopilan los elementos de la secuencia en una `List` o `Set`, respectivamente:```java
Stream<String> stream = Stream.of("cat", "dog", "elephant", "fox", "giraffe");
List<String> list = stream.collect(Collectors.toList());
System.out.println(list);
```El ejemplo anterior imprime:```
[cat, dog, elephant, fox, giraffe]
```Si necesita recolectar en un tipo específico de colección, puede usar `toCollection()` y proporcionar un proveedor para la colección:```java
Stream<String> stream = Stream.of("cat", "dog", "elephant", "fox", "giraffe");
LinkedList<String> linkedList = stream.collect(Collectors.toCollection(LinkedList::new));
System.out.println(linkedList);
```Esto recopila los elementos en una "LinkedList".

El recopilador `joining()` le permite concatenar los elementos de una secuencia en una sola cadena, opcionalmente con un delimitador, prefijo y sufijo:```java
Stream<String> stream = Stream.of("cat", "dog", "elephant", "fox", "giraffe");
String joined = stream.collect(Collectors.joining(", "));
System.out.println(joined);
```Esto imprime:```
cat, dog, elephant, fox, giraffe
```También hay recopiladores para calcular estadísticas simples sobre secuencias numéricas, como `counting()`, `summing()`, `averaging()` y `summarizing()`:```java
Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);
long count = stream1.collect(Collectors.counting());
System.out.println(count);

Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);
double average = stream2.collect(Collectors.averagingInt(i -> i));
System.out.println(average);

Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
int sum = stream3.collect(Collectors.summingInt(i -> i));
System.out.println(sum);

Stream<Integer> stream4 = Stream.of(1, 2, 3, 4, 5);
IntSummaryStatistics stats = stream4.collect(Collectors.summarizingInt(i -> i));
System.out.println(stats);
```Esta es la salida:```
5
3.0
15
IntSummaryStatistics{count=5, sum=15, min=1, average=3.000000, max=5}
```Estos recopiladores vienen en tres versiones para los tres tipos primitivos: "int", "long" y "double".

Los recopiladores `maxBy()` y `minBy()` le permiten encontrar los elementos máximos y mínimos según un `Comparador` dado:```java
Stream<String> stream = Stream.of("cat", "dog", "elephant", "fox", "giraffe");
Optional<String> max = stream.collect(Collectors.maxBy(Comparator.comparingInt(String::length)));
max.ifPresent(System.out::println);
```Esto imprime `"elefante"`, la cadena más larga de la secuencia.

### Recopilación en mapas

Una de las características más poderosas de la clase "Coleccionistas" es la capacidad de recopilar elementos en un "Mapa".

La forma más sencilla de hacer esto es con el recopilador `toMap()`, que requiere dos funciones: una para extraer la clave de cada elemento y otra para extraer el valor:```java
Stream<String> stream = Stream.of("elephant", "fox", "giraffe");
Map<Integer, String> map = stream.collect(Collectors.toMap(String::length, s -> s));
System.out.println(map);
```Esto recopila las cadenas en un mapa, utilizando su longitud como clave:```
{3=fox, 7=giraffe, 8=elephant}
```Si hay claves duplicadas, el recopilador `toMap()` generará una excepción. Para manejar esto, puede proporcionar una función de combinación como tercer argumento:```java
Stream<String> stream = Stream.of("cat", "elephant", "fox", "giraffe");
Map<Integer, String> map = stream.collect(Collectors.toMap(String::length, s -> s, (s1, s2) -> s1 + "," + s2));
System.out.println(map);
```Ahora, si varias cadenas tienen la misma longitud, se unirán con una coma. Este es el resultado del ejemplo anterior:```
{3=cat,fox, 7=giraffe, 8=elephant}
```### Agrupación, partición, mapeo y colocación en T

El recopilador `groupingBy()` le permite agrupar los elementos de una secuencia según una función de clasificación:```java
Stream<String> stream = Stream.of("cat", "dog", "elephant", "fox", "giraffe");
Map<Integer, List<String>> map = stream.collect(Collectors.groupingBy(String::length));
System.out.println(map);
```Esto agrupa las cadenas por su longitud:```
{3=[cat, dog, fox], 7=[giraffe], 8=[elephant]}
```También puede proporcionar un recopilador posterior para especificar cómo se deben recopilar los grupos:```java
Stream<String> stream = Stream.of("cat", "dog", "elephant", "fox", "giraffe");
Map<Integer, Set<String>> map = stream.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
```Esto recopila los grupos en "Conjuntos" en lugar de "Listas".

El recopilador `partitioningBy()` es un caso especial de `groupingBy()` que divide la secuencia en dos grupos según un predicado:```java
Stream<String> stream = Stream.of("cat", "dog", "elephant", "fox", "giraffe");
Map<Boolean, List<String>> map = stream.collect(Collectors.partitioningBy(s -> s.length() > 5));
System.out.println(map);
```Esto divide las cadenas en aquellas que tienen más de 5 caracteres y aquellas que no tienen más de 5 caracteres. Este es el resultado del ejemplo anterior:```
{false=[cat, dog, fox], true=[elephant, giraffe]}
```El recopilador `mapping()` le permite aplicar una función a cada elemento antes de recopilar los resultados:```java
Stream<String> stream = Stream.of("cat", "dog", "elephant", "fox", "giraffe");
List<Integer> list = stream.collect(Collectors.mapping(String::length, Collectors.toList()));
System.out.println(list);
```Esto recopila las longitudes de las cadenas en una lista. Este es el resultado:```
[3, 3, 8, 3, 7]
```Finalmente, un recopilador poderoso y menos conocido es el recopilador `Collectors.teeing()`. Este recopilador le permite realizar dos operaciones de recopilación independientes en una sola secuencia y luego combinar sus resultados mediante una función de fusión. Esto puede resultar particularmente útil cuando necesita realizar dos operaciones diferentes en el mismo conjunto de datos y luego combinar los resultados de manera significativa.

La forma general del método `teeing()` es la siguiente:```java
public static <T, R1, R2, R> Collector<T, ?, R> teeing(
    Collector<? super T, A1, R1> downstream1,
    Collector<? super T, A2, R2> downstream2,
    BiFunction<? super R1, ? super R2, R> merger
)
```Se necesitan tres argumentos:
1. **downstream1**: el primer recopilador que se aplica.
2. **downstream2**: el segundo recopilador que se aplicará.
3. **fusión**: una función que fusiona los resultados de los dos recopiladores.

Por ejemplo, digamos que tiene una lista de números enteros y queremos calcular tanto la suma como el recuento de los números enteros en una sola pasada por la secuencia y luego combinar estos resultados en un solo resultado.

Así es como puedes lograrlo:```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);
var result = numbers.stream().collect(Collectors.teeing(
    Collectors.summingInt(Integer::intValue),  // First collector: Sum of the integers
    Collectors.counting(),                      // Second collector: Count of the integers
    (sum, count) -> String.format("Sum: %d, Count: %d", sum, count)  // Merger function
));

System.out.println(result);
```Esta es la salida:```
Sum: 15, Count: 5
```Como puede ver, este recopilador simplifica el código para tareas de agregación complejas al eliminar la necesidad de realizar múltiples pasadas por la secuencia. Puede utilizar cualquier combinación de recopiladores y la función de fusión permite una combinación flexible de los resultados.


## Puntos clave
- La clase "Opcional" se utiliza para encapsular un valor opcional y evitar referencias "nulas". Proporciona métodos como `isPresent()`, `ifPresent()`, `get()`, `orElse()`, `orElseGet()` y `orElseThrow()` para trabajar con el valor contenido.

- Los flujos son contenedores para colecciones o matrices que permiten expresar operaciones con lambdas. No almacenan elementos, son inmutables y no reutilizables, no admiten acceso indexado, son fácilmente paralelizables y difieren la ejecución hasta que sea necesario.

- Las secuencias se pueden crear a partir de colecciones usando `stream()`, de valores individuales usando `Stream.of()`, de matrices usando `Arrays.stream()` y de otras formas como `generate()`, `iterate()` y `range()`. 

- Las operaciones de flujo intermedio siempre devuelven un nuevo flujo y son diferidas, solo procesan elementos cuando se invoca una operación de terminal. Pueden ser sin estado (como `filter()` y `map()`) o con estado (como `distinct()` y `sorted()`).

- Las operaciones de la terminal devuelven algo más que una corriente y consumen la tubería de la corriente. Incluyen `forEach()`, `count()`, `collect()`, `findFirst()`, `findAny()`, `anyMatch()`, `allMatch()` y `noneMatch()`.

- Los flujos primitivos `IntStream`, `LongStream` y `DoubleStream` evitan la sobrecarga de boxing/unboxing. Tienen métodos como `average()`, `max()`, `min()`, `sum()`, `range()` y `summaryStatistics()`.

- Las operaciones de cortocircuito como `limit()`, `findFirst()` y `anyMatch()` permiten que los flujos eviten procesar todos los elementos produciendo un resultado tan pronto como se hayan procesado suficientes elementos.

- El método `filter()` se utiliza para seleccionar sólo los elementos de una secuencia que satisfacen un predicado determinado. Devuelve una nueva secuencia que contiene solo los elementos filtrados.

- El método `distinct()` devuelve un flujo de elementos únicos, descartando duplicados. Puede considerarse como una operación de filtrado especial.

- El método `takeWhile()` devuelve una secuencia que contiene el prefijo más largo de elementos que coinciden con un predicado determinado, mientras que `dropWhile()` descarta este prefijo y devuelve los elementos restantes.

- El método `map()` transforma cada elemento de un flujo en un nuevo elemento aplicando una función. Devuelve una nueva secuencia de los elementos transformados.

- El método `flatMap()` se utiliza para aplanar un flujo de colecciones en un único flujo de elementos. Aplica una función que devuelve una secuencia a cada elemento y luego aplana todas estas secuencias en una.

- Los flujos primitivos (`IntStream`, `LongStream`, `DoubleStream`) tienen operaciones de mapeo especializadas para evitar costos de boxing y unboxing.

- El método `skip()` descarta los primeros n elementos de una secuencia, mientras que `limit()` trunca una secuencia para que no supere un tamaño especificado.

- El método `forEach()` realiza una acción sobre cada elemento de un flujo, mientras que `forEachOrdered()` hace lo mismo pero garantiza el orden de procesamiento de los flujos paralelos.

- Los métodos `allMatch()`, `anyMatch()` y `noneMatch()` verifican si ciertas condiciones se cumplen para los elementos de una secuencia.

- El método `findFirst()` devuelve el primer elemento de una secuencia, mientras que `findAny()` devuelve cualquier elemento (útil para secuencias paralelas).

- El método `concat()` concatena dos flujos en un solo flujo. Alternativamente, `flatMap()` se puede usar con `Stream.of()` para concatenar múltiples transmisiones.

- El método `reduce()` realiza una reducción de los elementos de una secuencia utilizando una función de acumulación asociativa. Puede devolver un resultado "Opcional" o aceptar un valor de identidad para devolver un resultado no opcional.

- El método `collect()` se usa para acumular los elementos de una secuencia en una colección u otra estructura de datos, usando un `Collector` para especificar cómo se deben recolectar los elementos.

- La clase `Coleccionistas` proporcionauna variedad de recopiladores predefinidos, incluidos `toList()`, `toSet()`, `toMap()`, `joining()`, `counting()`, `summing()`, `averaging()`, `maxBy()`, `minBy()`, `groupingBy()`, `partitioningBy()`, `mapping()` y `teeing()`.


## Preguntas de práctica
**1. ¿Cuál de las siguientes líneas de código demuestra el uso de la clase "Opcional" para manejar un valor potencialmente "nulo" para evitar una excepción?**```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String value = getValue();
        // Insert code here
    }
    
    public static String getValue() {
        return null; // This method may return null
    }
}
```**A)** `Opcional<Cadena> opcional = nuevo Opcional<>(valor);`  
**B)** `Opcional<Cadena> opcional = Opcional.de(valor);`  
**C)** `Opcional<Cadena> opcional = Opcional.ofNullable(valor);`  
**D)** `Opcional<Cadena> opcional = Opcional.vacío(valor);`  
**E)** `Opcional<Cadena> opcional = Opcional.nullable(valor);`



**2. ¿Cuál de las siguientes líneas de código demuestra correctamente el uso de una operación de terminal?**```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "cherry", "date");

        Stream<String> stream = list.stream()
                                    .filter(s -> s.length() > 5)
                                    .peek(System.out::println)
                                    .map(String::toUpperCase);

        // Insert terminal operation here
    }
}
```**A)** `stream.filter(s -> s.contains("A"));`  
**B)** `stream.map(String::toLowerCase);`  
**C)** `corriente.distinct();`  
**D)** `stream.limit(2);`  
**E)** `stream.collect(Collectors.toList());`


**3. ¿Cuál de las siguientes líneas de código utiliza correctamente una secuencia primitiva para calcular la suma de una matriz de números enteros?**```java
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        // Insert code here to calculate sum
    }
}
```**A)** `int suma = números.stream().sum();`  
**B)** `int suma = IntStream.range(0, números.longitud).sum();`  
**C)** `int suma = IntStream.from(números).sum();`  
**D)** `int suma = IntStream.of(números).sum();`  
**E)** `int suma = IntStream.range(números).sum();`


**4. ¿Cuál de las siguientes líneas de código filtra correctamente una secuencia para incluir solo cadenas con una longitud mayor que 3?**```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("one", "two", "three", "four");

        Stream<String> stream = list.stream();

        // Insert code here to filter the stream
    }
}
```**A)** `Stream<String> filteredStream = stream.filter(s -> s.length() > 3);`  
**B)** `Stream<String> filteredStream = stream.map(s -> s.length() > 3);`  
**C)** `Stream<String> filteredStream = stream.collect(Collectors.filtering(s -> s.length() > 3));`  
**D)** `Stream<String> filteredStream = stream.filtering(s -> s.length() > 3);`  
**E)** `Stream<String> filteredStream = stream.filterByLength(3);`


**5. ¿Cuál de las siguientes líneas de código asigna correctamente un flujo de cadenas a sus longitudes?**```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "cherry", "date");

        Stream<String> stream = list.stream();

        // Insert code here to map the stream
    }
}
```**A)** `Stream<String> lengthStream = stream.map(s -> s.length());`  
**B)** `Stream<String> lengthStream = stream.mapToInt(s -> s.length());`  
**C)** `Stream<Integer> lengthStream = stream.map(s -> s.length());`  
**D)** `IntStream lengthStream = stream.map(s -> s.length());`  
**E)** `Stream<String> lengthStream = stream.flatMap(s -> Stream.of(s.length()));`



**6. ¿Cuál de las siguientes líneas de código limita correctamente la transmisión a los primeros 3 elementos después de omitir los primeros 2 elementos?**```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("one", "two", "three", "four", "five", "six");

        Stream<String> stream = list.stream();

        // Insert code here to skip and limit the stream
    }
}
```**A)** `Stream<String> resultStream = stream.skip(2).limit(3);`  
**B)** `Stream<String> resultStream = stream.limit(3).skip(2);`  
**C)** `Stream<String> resultStream = stream.skip(3).limit(2);`  
**D)** `Stream<String> resultStream = stream.limit(2).skip(3);`  
**E)** `Stream<String> resultStream = stream.slice(2, 5);`


**7. ¿Cuál de las siguientes líneas de código concatena correctamente dos secuencias?**```java
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list1 = List.of("one", "two", "three");
        List<String> list2 = List.of("four", "five", "six");

        Stream<String> stream1 = list1.stream();
        Stream<String> stream2 = list2.stream();

        // Insert code here to concatenate the streams
    }
}
```**A)** `Stream<String> resultStream = Stream.concat(stream1, stream2.collect(Collectors.toList()));`  
**B)** `Stream<String> resultStream = Stream.concat(stream1, stream2);`  
**C)** `Stream<String> resultStream = stream1.concat(stream2);`  
**D)** `Stream<String> resultStream = stream1.merge(stream2);`  
**E)** `Stream<String> resultStream = Stream.of(stream1, stream2);`


**8. ¿Cuál de las siguientes líneas de código utiliza el método reduce para calcular correctamente el producto de todos los elementos en una secuencia de números enteros?**```java
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Stream<Integer> stream = numbers.stream();

        // Insert code here to calculate the product
    }
}
```**A)** `int producto = stream.reduce(1, (a, b) -> a + b);`  
**B)** `int producto = stream.reduce((a, b) -> a * b);`  
**C)** `int producto = stream.reduce(0, (a, b) -> a * b);`  
**D)** `Opcional<Integer> producto = stream.reduce(1, (a, b) -> a * b);`  
**E)** `int producto = stream.reduce(1, (a, b) -> a * b, (a, b) -> a * b);`



**9. ¿Cuál de las siguientes líneas de código recopila correctamente los elementos de una secuencia en un "Conjunto" y también garantiza que se mantenga el orden original de los elementos?**```java
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "cherry", "date");

        Stream<String> stream = list.stream();

        // Insert code here to collect the elements into a Set while maintaining order
    }
}
```**A)** `Establecer<Cadena> resultSet = stream.collect(Collectors.toSet());`  
**B)** `Establecer<String> resultSet = stream.collect(Collectors.toCollection(LinkedHashSet::new));`  
**C)** `Establecer<Cadena> resultSet = stream.collect(Collectors.toCollection(TreeSet::new));`  
**D)** `Establecer<Cadena> resultSet = stream.collect(Collectors.toList());`  
**E)** `Establecer<String> resultSet = stream.collect(Collectors.toMap());`