-"None"
layout: chapter

title: "Capítulo SEIS"
subtitle: "Matrices, genéricos y colecciones"
exam_objectives:
  - "Cree matrices, colecciones List, Set, Map y Deque, y agregue, elimine, actualice, recupere y ordene sus elementos."

previous_link: "/ch05.html"
previous_title: "Controlar el flujo del programa"
next_link: "/ch07.html"
next_title: "Manejo de errores y excepciones"
answers_link: "/ch06a.html"
-"None"## Contenido del capítulo

- [Matrices](#matrices)
    - [Creación e inicialización de matrices](#creación-e-inicialización-de-matrices)
    - [Matrices anónimas] (# matrices-anónimas)
    - [Usando una matriz](#usando-una-matriz)
    - [Matrices multidimensionales] (#matrices-multidimensionales)
    - [La clase `java.util.Arrays`](#la-clase-javautilarrays)
- [Genéricos](#genéricos)
    - [Comprensión del borrado de tipos] (# comprensión del borrado de tipos)
    - [Creando clases genéricas](#creando-clases-genericas)
    - [Convenciones de nomenclatura para genéricos](#convenciones-de-nomenclatura-para-genericos)
    - [Escritura de métodos y constructores genéricos](#escritura-de-métodos-y-constructores-genericos)
    - [Tipos genéricos que regresan] (#tipos-genericos que regresan)
    - [Sobrecarga de un método genérico](#sobrecarga-de-un-método-generico)
    - [Implementando interfaces genéricas](#implementando-interfaces-genericas)
    - [Creación de registros genéricos](#creación-registros-genericos)
    - [Tipos genéricos delimitadores](#tipos-genéricos delimitadores)
- [El marco de las colecciones](#el-marco-de-las-colecciones)
- [La interfaz `Lista`] (#la-interfaz-lista)
    - [Creando una `Lista`](#creando-una-lista)
    - [Trabajar con métodos `Lista`] (#trabajar-con-métodos-lista)
- [La interfaz `Set`] (#la-interfaz-set)
    - [Creando un `Conjunto`](#creando-un-conjunto)
    - [Trabajar con métodos `Set`] (#trabajar-con-métodos-set)
- [La interfaz `Deque`](#la-interfaz-deque)
    - [Creando un `Deque`](#creando-un-deque)
    - [Trabajar con métodos `Deque`](#trabajar-con-métodos-deque)
- [La interfaz `Mapa`] (#la-interfaz-mapa)
    - [Creando un `Mapa`](#creando-un-mapa)
    - [Trabajar con métodos `Map`](#trabajar-con-métodos-mapa)
    - [Anulando `hashCode()`](#overriding-hashcode)
- [Clasificación de datos](#clasificación-datos)
    - [La interfaz `comparable`](#la-interfaz-comparable)
    - [La interfaz `Comparador`](#la-interfaz-comparador)
    - [Comparando `Comparable` y `Comparador`](#comparando-comparable-y-comparador)
    - [`Collections.sort` y `Collections.binarySearch`](#collectionssort-and-collectionsbinarysearch)
- [Resumen de tipos de colecciones](#resumen-de-tipos-de-colecciones)
- [Puntos clave](#puntos clave)
- [Preguntas de práctica](#preguntas-de-practica)

---

## matrices
Una matriz es un objeto que contiene un número fijo de valores de un solo tipo en ubicaciones de memoria contiguas. Estos valores, o elementos, pueden ser de tipo primitivo o de tipo de referencia.

A continuación se muestra un diagrama que le ayudará a visualizar matrices de una y dos dimensiones:```
One-dimensional Array:
┌─────┬─────┬─────┬─────┬─────┐
│  0  │  1  │  2  │  3  │  4  │  int[] numbers = new int[5];
└─────┴─────┴─────┴─────┴─────┘
   ▲
   └── Index

Two-dimensional Array:
┌─────┬─────┬─────┐
│ 0,0 │ 0,1 │ 0,2 │
├─────┼─────┼─────┤
│ 1,0 │ 1,1 │ 1,2 │  int[][] matrix = new int[2][3];
└─────┴─────┴─────┘
   ▲     ▲
   │     └── Column Index
   └──────── Row Index
```Comencemos revisando cómo se crea e inicializa una matriz.

### Creación e inicialización de matrices

Para crear una matriz, debe declarar una variable del tipo de matriz deseado y luego usar la palabra clave `new` para crear el objeto de matriz y asignarlo a la variable:```java
// Creates an array of integers
int[] myArray; 
myArray = new int[5];
```También puedes combinar la declaración y la creación de la matriz en una sola declaración:```java
int[] myArray = new int[5];
```El número entre corchetes especifica la cantidad de elementos que contendrá la matriz; en otras palabras, el tamaño de la matriz. Este tamaño debe decidirse cuando se crea la matriz y no se puede cambiar más adelante.

Esta es una limitación importante a tener en cuenta: no se puede cambiar el tamaño de una matriz una vez creada. Si necesita una estructura de datos que pueda crecer o reducirse dinámicamente, debería considerar usar una de las clases de colección como `ArrayList` en su lugar.

Cuando se crea una matriz, sus elementos se inicializan automáticamente con valores predeterminados:
- `0` para tipos numéricos
- `falso` para booleano
- `nulo` para tipos de referencia. 

Sin embargo, también puedes inicializar explícitamente una matriz durante la creación:```java
int[] myArray = new int[] {10, 20, 30, 40, 50};
```Esto crea una matriz de 5 números enteros y los inicializa con los valores especificados. El tamaño de la matriz está determinado por la cantidad de valores proporcionados.

Si no necesita especificar los valores en el momento de la declaración, puede dejar algunos o todos los elementos sin inicializar:```java
int[] myArray = new int[5];
myArray[0] = 10;
myArray[1] = 20;
```Esto crea una matriz de 5 números enteros, inicializa los dos primeros y deja el resto con su valor predeterminado de "0".

Es importante tener en cuenta que todos los elementos de una matriz deben ser del mismo tipo. No se pueden mezclar diferentes tipos de datos en una sola matriz.

### Matrices anónimas

Una matriz anónima es una matriz que se declara e inicializa en una sola declaración sin asignarla a una variable:```java
new int[] {10, 20, 30, 40, 50}
```Las matrices anónimas se utilizan a menudo cuando se pasa una matriz como argumento a un método:```java
myMethod(new int[] {10, 20, 30, 40, 50});
```Proporcionan una manera conveniente de crear y pasar una matriz en línea, sin la necesidad de una declaración de variable separada.

Sin embargo, las matrices anónimas no se limitan a los argumentos del método. Se pueden usar en cualquier lugar donde se espere una matriz, como en una tarea:```java
int[] myArray = new int[] {10, 20, 30, 40, 50};
```En este caso, la matriz anónima se crea y se asigna inmediatamente a la variable `myArray`.

### Usando una matriz

Para acceder a un elemento de una matriz, utilice el nombre de la matriz seguido del índice del elemento entre corchetes:```java
int[] myArray = new int[] {10, 20, 30, 40, 50};
System.out.println(myArray[0]); // Outputs 10
System.out.println(myArray[2]); // Outputs 30
```Los índices de una matriz comienzan en 0, por lo que el primer elemento está en el índice 0, el segundo en el índice 1, y así sucesivamente.

También puedes usar una variable para el índice:```java
int index = 2;
System.out.println(myArray[index]); // Outputs 30
```Intentar acceder a un elemento fuera de los límites de la matriz dará como resultado una `ArrayIndexOutOfBoundsException`.

Para saber la cantidad de elementos en una matriz, puede usar el atributo de longitud:```java
System.out.println(myArray.length); // Outputs 5
```Tenga en cuenta que este es un atributo, no un método, por lo que no utiliza paréntesis.

Intentar cambiar el tamaño de una matriz después de haberla creado, ya sea asignando una nueva matriz a la variable o usando el atributo de longitud, generará un error en tiempo de compilación.

Si bien no puedes cambiar el tamaño de una matriz, puedes copiar el contenido de una matriz a otra:```java
int[] sourceArray = new int[] {10, 20, 30, 40, 50};
int[] destArray = new int[5];
System.arraycopy(sourceArray, 0, destArray, 0, 5);
```Esto copia los elementos de `sourceArray` a `destArray`. Los argumentos especifican la matriz de origen, la posición inicial en la matriz de origen, la matriz de destino, la posición inicial en la matriz de destino y el número de elementos a copiar.

Sin embargo, esto no es lo mismo que asignar una matriz a otra:```java
int[] sourceArray = new int[] {10, 20, 30, 40, 50};
int[] destArray = sourceArray;
```Esto no crea una copia de la matriz. En cambio, hace que `destArray` haga referencia al mismo objeto de matriz que `sourceArray`. Los cambios realizados a través de cualquiera de las variables se reflejarán en la otra, ya que ambas apuntan a la misma matriz en la memoria.

### Matrices multidimensionales

Java también admite matrices multidimensionales, que pueden considerarse como *matrices de matrices*.

El tipo más común de matriz multidimensional es la matriz bidimensional, que a menudo se utiliza para representar matrices o tablas de datos. Pero Java no impone ningún límite al número de dimensiones que puede tener una matriz.

Para declarar una matriz multidimensional, especifique cada dimensión adicional con otro conjunto de corchetes. Por ejemplo, así es como declararías una matriz bidimensional de números enteros:```java
int[][] matrix;
```Esto declara una variable `matriz` que es una matriz de matrices de números enteros.

Luego puede crear la matriz con la palabra clave "nueva":```java
matrix = new int[3][4];
```Esto crea una matriz bidimensional con 3 filas y 4 columnas. Básicamente, es una matriz que contiene 3 matrices, cada una de las cuales contiene 4 números enteros.

Al igual que con las matrices unidimensionales, puedes combinar la declaración y la creación:```java
int[][] matrix = new int[3][4];
```También puede inicializar la matriz al momento de su creación:```java
int[][] matrix = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};
```Esto crea la misma matriz de 3x4 que antes, pero también la inicializa con los valores especificados.

Acceder a elementos en una matriz multidimensional es similar a una matriz unidimensional, pero ahora necesita especificar un índice para cada dimensión:```java
int[][] matrix = new int[3][4];
matrix[0][0] = 1;
matrix[1][2] = 7;
System.out.println(matrix[1][2]); // Outputs 7
```Aquí, "matriz[0][0]" se refiere al elemento de la primera fila y la primera columna, "matriz[1][2]" se refiere al elemento de la segunda fila y la tercera columna, y así sucesivamente.

También puedes usar bucles anidados para iterar sobre una matriz multidimensional:```java
int[][] matrix = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};
        
for(int i = 0; i < matrix.length; i++) {
    for(int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
```Esta será la salida:```
1 2 3 4 
5 6 7 8 
9 10 11 12 
```El bucle exterior itera sobre las filas y el bucle interior itera sobre las columnas de cada fila.

Tenga en cuenta que en una matriz multidimensional, el atributo de longitud proporciona el número de matrices en la primera dimensión. Para obtener la longitud de las matrices en la segunda dimensión, debe especificar un índice para la primera dimensión, como `matriz[i].length`.

Tenga en cuenta también que, si bien todas las matrices de la segunda dimensión tienen la misma longitud en este ejemplo, esto no es un requisito. Puede tener una matriz *irregular* donde cada matriz en la segunda dimensión tiene una longitud diferente:```java
int[][] ragged = {
    {1, 2, 3, 4},
    {5, 6},
    {7, 8, 9}
};
```Esta flexibilidad puede ser útil en ciertas situaciones, pero es más común trabajar con matrices *rectangulares* donde todas las matrices de segunda dimensión tienen la misma longitud.

### La clase `java.util.Arrays`

La clase `java.util.Arrays` contiene varios métodos estáticos para manipular matrices. Proporciona métodos para ordenar, buscar, comparar y completar elementos de matriz. Veamos algunos de los métodos más utilizados.

#### Clasificación

El método `sort()` ordena los elementos de una matriz en orden ascendente. Tiene varias sobrecargas para diferentes tipos de arreglos:```java
int[] numbers = {4, 2, 7, 1, 3};
Arrays.sort(numbers);
System.out.println(Arrays.toString(numbers)); // [1, 2, 3, 4, 7]
```Esto ordena la matriz de "números" en su lugar, modificando la matriz original.

También puede ordenar una parte de una matriz especificando los índices inicial (inclusive) y final (exclusivo):```java
int[] numbers = {4, 2, 7, 1, 3};
Arrays.sort(numbers, 1, 4); 
System.out.println(Arrays.toString(numbers)); // [4, 1, 2, 7, 3]
```Esto ordena sólo los elementos del índice 1 al 3, dejando intactos los elementos de los índices 0 y 4.

Para matrices de objetos, los objetos deben implementar la interfaz `Comparable` para que `sort()` funcione. Alternativamente, puede proporcionar un objeto `Comparador` para definir el orden de clasificación:```java
String[] strings = {"banana", "apple", "cherry"};
Arrays.sort(strings, Comparator.comparingInt(String::length));
System.out.println(Arrays.toString(strings)); // [apple, banana, cherry]
```Esto ordena la matriz `strings` por la longitud de cada cadena, usando un `Comparator` creado por el método `comparingInt()`.

#### Buscando

El método `binarySearch()` busca un elemento específico en una matriz ordenada utilizando el algoritmo de búsqueda binaria. Si se encuentra el elemento, devuelve su índice. Si no, devuelve un valor negativo.```java
int[] numbers = {1, 2, 3, 4, 7};
System.out.println(Arrays.binarySearch(numbers, 3)); // 2
System.out.println(Arrays.binarySearch(numbers, 5)); // -5
```En la primera búsqueda, el elemento 3 se encuentra en el índice 2. En la segunda búsqueda, no se encuentra el elemento 5, por lo que el método devuelve -5. El valor negativo se calcula como `-(punto de inserción) - 1`, donde el punto de inserción es el índice en el que se insertaría el elemento para mantener el orden de clasificación.

Tenga en cuenta que para que `binarySearch()` funcione correctamente, la matriz debe estar ordenada. Si la matriz no está ordenada, los resultados no están definidos.

#### Usando `comparar()`

El método `compare()` compara dos matrices lexicográficamente (por orden del diccionario). Devuelve un valor negativo si la primera matriz es *menor que* la segunda, un valor positivo si la primera matriz es *mayor que* la segunda y cero si son iguales.```java
int[] arr1 = {1, 2, 3};
int[] arr2 = {1, 2, 3};
int[] arr3 = {1, 2, 4};
        
System.out.println(Arrays.compare(arr1, arr2)); // 0
System.out.println(Arrays.compare(arr1, arr3)); // -1
System.out.println(Arrays.compare(arr3, arr1)); // 1
```Al comparar `arr1` y `arr2`, el método devuelve 0 porque las matrices son iguales. Al comparar `arr1` y `arr3`, devuelve -1 porque `arr1` es lexicográficamente menor que `arr3` (porque 3 < 4). De manera similar, al comparar `arr3` y `arr1`, devuelve 1.

### Usando `relleno()`

El método `fill()` en la clase `Arrays` se usa para llenar una matriz o una parte de ella con un valor específico. Es una forma conveniente de establecer todos los elementos con el mismo valor.

El método `fill()` tiene varias sobrecargas:
- `fill(array, value)`: Llena toda la matriz con el valor especificado.
- `fill(array, fromIndex, toIndex, value)`: Rellena una parte de la matriz, desde `fromIndex` (inclusive) hasta `toIndex` (exclusivo), con el valor especificado.

Aquí hay un ejemplo del uso de `fill()` para llenar una matriz completa:```java
int[] numbers = new int[5];
Arrays.fill(numbers, 10);
System.out.println(Arrays.toString(numbers)); // [10, 10, 10, 10, 10]
```Esto crea una matriz de 5 números enteros y la llena por completo con el valor 10.

También puedes llenar solo una parte de una matriz:```java
int[] numbers = {1, 2, 3, 4, 5};
Arrays.fill(numbers, 1, 4, 10);
System.out.println(Arrays.toString(numbers)); // [1, 10, 10, 10, 5]
```Esto llena los elementos del índice 1 al 3 (recuerde, `toIndex` es exclusivo) con el valor 10, dejando los elementos en los índices 0 y 4 sin cambios.

El método `fill()` tiene sobrecargas para todos los tipos primitivos y para las referencias a objetos. Cuando se usa con referencias a objetos, cada elemento apuntará al mismo objeto:```java
String[] strings = new String[3];
Arrays.fill(strings, "Hello");
System.out.println(Arrays.toString(strings)); // [Hello, Hello, Hello]
```Esto llena la matriz `strings` con referencias a la misma cadena `"Hello"`.

Es importante comprender que el método `Arrays.fill()` en Java no crea nuevos objetos para cada elemento. En cambio, configura cada elemento para que haga referencia al mismo objeto. Si modifica el objeto a través de una de estas referencias, todos los elementos de la matriz reflejarán ese cambio. Sin embargo, este comportamiento también depende de si los objetos son mutables o inmutables.

Aquí hay un ejemplo con objetos inmutables (cadenas):```java
String[] strings = new String[3];
Arrays.fill(strings, new String("Hello"));
strings[0] = "Hi";
System.out.println(Arrays.toString(strings)); // [Hi, Hello, Hello]
````Arrays.fill(strings, new String("Hello"));` establece que cada elemento de la matriz haga referencia a un nuevo objeto `String` con el valor `"Hello"`. Por lo tanto, `strings[0]`, `strings[1]` y `strings[2]` hacen referencia inicialmente al mismo objeto `String`. Cuando actualiza `strings[0] = "Hi";`, cambia la referencia en `strings[0]` para que apunte a un nuevo objeto `String` con el valor `"Hi"`. Dado que los objetos `String` son inmutables en Java, esto no afecta a `strings[1]` y `strings[2]`. El resultado será `[Hola, Hola, Hola]`, ya que modificar un elemento no afecta a los demás.

Sin embargo, cuando se usa `Arrays.fill()` con objetos mutables, cada elemento hará referencia al mismo objeto. Si modifica una instancia, todos los elementos de la matriz reflejarán ese cambio:```java
class Point {
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        Point[] points = new Point[3];
        Arrays.fill(points, new Point(0, 0));

        // Modifying one element
        points[0].x = 1;
        points[0].y = 1;

        System.out.println(Arrays.toString(points)); // Output: [(1, 1), (1, 1), (1, 1)]
    }
}
```Aquí, `puntos[0]`, `puntos[1]` y `puntos[2]` hacen referencia al mismo objeto `Punto`. Cambiar los valores `x` e `y` de `points[0]` afecta a los tres porque todos hacen referencia a la misma instancia de `Point`.

Hay muchos otros métodos útiles en la clase `Arrays`, como `equals()` o `copyOf()`, etc. Vale la pena explorar [la documentación](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html) para ver qué hay disponible.



## Genéricos
Si llevas un tiempo programando en Java, probablemente te hayas topado con genéricos en algún momento. Pero, ¿qué son exactamente y por qué son útiles?

En términos simples, los genéricos le permiten escribir código que puede funcionar con diferentes tipos, sin perder los beneficios de la seguridad de tipos. Proporcionan una forma de parametrizar tipos, de modo que pueda crear clases, interfaces y métodos que puedan operar en objetos de varios tipos mientras mantienen la verificación de tipos en tiempo de compilación.

Ahora bien, podría estar pensando: "¿No son los genéricos simplemente una forma elegante de evitar el uso de `Object` en todas partes?" Es cierto que antes de que se introdujeran los genéricos en Java 5, los desarrolladores solían utilizar el tipo "Objeto" para escribir código que pudiera manejar diferentes tipos. Sin embargo, este enfoque tiene varios inconvenientes. Requiere mucha conversión explícita, lo que puede provocar errores de tiempo de ejecución si se utiliza el tipo incorrecto. Tampoco proporciona ninguna seguridad de tipo en tiempo de compilación. Los genéricos, por otro lado, le permiten especificar los tipos con los que desea trabajar, lo que proporciona una mayor seguridad de tipos y reduce la necesidad de realizar castings.

### Entendiendo el borrado de tipos
El borrado de tipos es un proceso en el que el compilador elimina toda la información de tipos genéricos en el momento de la compilación, reemplazándola con sus límites o con el tipo "Objeto" si no se especifican límites. Esto significa que en tiempo de ejecución, un tipo genérico como `List<String>` se trata esencialmente como una `List` simple, sin ninguna información de tipo específica.

Quizás se pregunte: "Si el borrado de tipos elimina la información de tipos, ¿significa que los genéricos no proporcionan ninguna seguridad de tipos?" Si bien es cierto que la información de tipos genéricos no está disponible en tiempo de ejecución debido al borrado de tipos, los genéricos aún brindan importantes beneficios de seguridad de tipos en tiempo de compilación. El compilador utiliza la información de tipo genérica para realizar comprobaciones de tipo y detectar posibles errores relacionados con el tipo desde el principio. Garantiza que no agregue accidentalmente un objeto del tipo incorrecto a una colección genérica ni devuelva el tipo incorrecto desde un método genérico.

Sin embargo, el borrado de tipos impone algunas limitaciones. Por ejemplo, no puede utilizar el operador `instanceof` directamente con tipos genéricos. Si intentas algo como:```java
if (obj instanceof List<String>) {
    // ...
}
```Obtendrá un error de compilación. Esto se debe a que la información del tipo genérico se borra en tiempo de ejecución, por lo que el operador `instanceof` solo puede verificar el tipo sin formato (`List` en este caso), no el tipo parametrizado específico.

Otra limitación es que no puedes crear matrices de tipos parametrizados. Entonces, no puedes hacer algo como:```java
List<String>[] array = new List<String>[10];
```Nuevamente, esto se debe al borrado de tipos. El compilador no tiene suficiente información en tiempo de ejecución para crear una matriz del tipo parametrizado específico.

Quizás se pregunte por qué Java utiliza el borrado de tipos en primer lugar. Una razón principal es mantener la compatibilidad con versiones anteriores de Java que no tenían genéricos. Al borrar la información del tipo genérico en el momento de la compilación, el código genérico aún se puede usar con código heredado no genérico sin causar problemas de tiempo de ejecución.

Entonces, si bien el borrado de tipos a veces puede parecer una limitación, es una elección de diseño deliberada en Java. Logra un equilibrio entre proporcionar seguridad de tipos en tiempo de compilación y mantener la compatibilidad con versiones anteriores del lenguaje. 

### Creando clases genéricas
Ahora que tiene un conocimiento sólido del borrado de tipos, exploremos cómo crear sus propias clases genéricas.

Crear una clase genérica es bastante sencillo. Simplemente define la clase con uno o más parámetros de tipo entre paréntesis angulares después del nombre de la clase. Estos parámetros de tipo actúan como marcadores de posición para los tipos reales que se utilizarán cuando se cree una instancia de la clase.

Por ejemplo, digamos que desea crear una clase genérica simple llamada "Pair", que contiene dos valores de tipos potencialmente diferentes:```java
public class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Hello", 42);

        // Demonstrate compile-time type safety
        String firstElement = pair.getFirst(); // No casting required
        Integer secondElement = pair.getSecond();

        System.out.println("First: " + firstElement);
        System.out.println("Second: " + secondElement);

        // Compiler will catch type mismatch errors
        // pair.setFirst(100); // Uncommenting this line will cause a compile-time error
    }
}
```En este ejemplo, "T" y "U" son los parámetros de tipo. Se pueden reemplazar con cualquier tipo válido al crear una instancia de la clase "Pair". Por ejemplo, podría crear un `Pair<String, Integer>` para contener un par de `String` y un `Integer`.

Usar `Object` para diseñar una clase flexible no es suficiente. Si bien usar `Object` le permitiría almacenar cualquier tipo de objeto en su clase, carece de seguridad de tipos. Con los genéricos, puede especificar los tipos exactos con los que desea trabajar y el compilador se asegurará de que solo se utilicen objetos de esos tipos con su clase. Esto detecta posibles errores relacionados con el tipo en tiempo de compilación en lugar de en tiempo de ejecución.

Además, el uso de genéricos no tiene un impacto significativo en el rendimiento. Recuerde, el compilador de Java realiza el borrado de tipos, por lo que la información de tipos genéricos se elimina en el momento de la compilación y el código de bytes generado es esencialmente el mismo que si hubiera utilizado tipos sin formato. En la mayoría de los casos, la diferencia de rendimiento es insignificante.

### Convenciones de nomenclatura para genéricos
Al crear clases o métodos genéricos, es importante seguir las convenciones de nomenclatura establecidas para los parámetros de tipo. Si bien el compilador no lo exige estrictamente, cumplir con estas convenciones hace que su código sea más legible y fácil de mantener.

Los nombres de parámetros de tipo más comunes son letras mayúsculas individuales, como por ejemplo:
- `E` para un elemento
- `K` para una clave de mapa
- `V` para un valor de mapa
- `T` para un tipo general
- `S`, `U`, `V`, etc. para tipos adicionales

Si bien técnicamente podrías usar nombres más largos para los parámetros de tipo, generalmente no se recomienda. Los nombres de una sola letra son una convención ampliamente aceptada y hacen que el código sea más conciso y fácil de leer. Es una buena idea seguir con los nombres convencionales a menos que tenga una razón de peso para hacerlo de otra manera.

Por ejemplo, en el caso de mapas, la convención es usar `K` para claves y `V` para valores, pero el compilador no aplicará esto. Sin embargo, seguir la convención hace que su código sea más consistente y más fácil de entender para otros desarrolladores.

Estas convenciones de nomenclatura proporcionan un vocabulario coherente en el que los desarrolladores pueden confiar al leer y escribir código genérico. Si sigue estas convenciones, hará que su código sea más idiomático y más fácil de mantener.


### Escribir métodos y constructores genéricos
Ahora que está familiarizado con las clases genéricas y los parámetros de tipo, exploremos otra característica poderosa de los genéricos: escribir métodos genéricos.

Los métodos genéricos le permiten escribir código reutilizable que puede funcionar con diferentes tipos, lo que proporciona flexibilidad y seguridad de tipos. Al definir parámetros de tipo a nivel de método, puede crear métodos que puedan aceptar y devolver valores de distintos tipos.

A continuación se muestra un ejemplo de un método genérico:```java
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}
```En este ejemplo, el método `printArray` se define con un parámetro de tipo `T`. El método toma una matriz de tipo "T" e imprime cada elemento de la matriz. El parámetro de tipo `T` se declara antes del tipo de retorno del método, entre corchetes angulares `<>`.

Puedes invocar este método genérico con matrices de diferentes tipos:```java
String[] strings = { "Hello", "World", "Java" } ;
printArray(strings);

Integer[] integers = { 1, 2, 3, 4, 5 };
printArray(integers);
```El método `printArray` se puede llamar con una matriz de cadenas o una matriz de números enteros, lo que demuestra su flexibilidad para trabajar con diferentes tipos.

Aquí hay otro ejemplo de un método genérico que devuelve un valor:```java
public static <T> T getFirst(T[] array) {
    if (array != null && array.length > 0) {
        return array[0];
    }
    return null;
}
```En este ejemplo, el método `getFirst` se define con un parámetro de tipo `T`. Toma una matriz de tipo `T` y devuelve el primer elemento de la matriz, también de tipo `T`. Si la matriz es "nula" o está vacía, devuelve "nula".

Puede invocar este método y asignar el resultado a una variable del tipo apropiado:```java
String[] strings = { "Hello", "World", "Java" };
String firstString = getFirst(strings);

Integer[] integers = { 1, 2, 3, 4, 5 };
Integer firstInteger = getFirst(integers);
```Al invocar un método genérico, tiene la opción de especificar explícitamente los argumentos de tipo o dejar que el compilador los infiera según el contexto. 

A continuación se muestra un ejemplo de cómo especificar explícitamente el tipo de argumento:```java
String[] strings = { "Hello", "World", "Java" };
String firstString = GenericMethodExample.<String>getFirst(strings);
```En este ejemplo, especificamos explícitamente el argumento de tipo `<String>` al invocar el método `getFirst`. Esto le dice al compilador que el parámetro de tipo "T" debe estar vinculado al tipo "String".

Y aquí hay un ejemplo de inferencia de tipos:```java
Integer[] integers = { 1, 2, 3, 4, 5 };
Integer firstInteger = GenericMethodExample.getFirst(integers);
```En este caso, omitimos el argumento de tipo explícito y dejamos que el compilador infiera el tipo basándose en el argumento del método. El compilador infiere que el parámetro de tipo "T" debe estar vinculado al tipo "Integer".

Los parámetros genéricos funcionan de manera similar con los constructores:```java
public class GenericBox<T> {
    private T content;

    public GenericBox(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
```En este ejemplo, la clase `GenericBox` tiene un constructor que acepta un argumento genérico de tipo `T`. Para crear una instancia de `GenericBox` con un tipo específico, puedes pasar el argumento genérico al llamar al constructor:```java
GenericBox<String> stringBox = new GenericBox<>("Hello");
GenericBox<Integer> integerBox = new GenericBox<>(42);
```Al especificar `<String>` o `<Integer>` al crear las instancias de `GenericBox`, se define explícitamente el tipo de `contenido` almacenado en cada cuadro.

También puedes pasar argumentos genéricos a un método de fábrica estático, por ejemplo:```java
public class GenericFactory {
    public static <T> List<T> createList(T... elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }
}
```Aquí, el método `createList` es un método de fábrica estático que crea una nueva `ArrayList` basada en los elementos proporcionados. Para pasar argumentos genéricos al llamar a este método, puede utilizar la siguiente sintaxis:```java
List<String> stringList = GenericFactory.<String>createList("Apple", "Banana", "Orange");
List<Integer> integerList = GenericFactory.<Integer>createList(1, 2, 3, 4, 5);
```Al especificar explícitamente `<String>` o `<Integer>` antes del nombre del método, indica el tipo de parámetro deseado para la lista creada.

Es importante tener en cuenta que en muchos casos, el compilador de Java puede inferir los argumentos de tipo genérico en función del contexto, como los tipos de argumentos del método o la asignación de variables. En tales casos, puedes omitir el argumento genérico explícito y dejar que el compilador lo infiera automáticamente:```java
List<String> stringList = GenericFactory.createList("Apple", "Banana", "Orange");
```Sin embargo, puede haber situaciones en las que sea necesario pasar explícitamente argumentos genéricos, como cuando el compilador no puede inferir el tipo o cuando desea imponer un tipo específico.


### Devolución de tipos genéricos
Además de crear clases y métodos genéricos que acepten parámetros de tipos genéricos, también puede devolver tipos genéricos a partir de métodos. Esto le permite escribir código más flexible y reutilizable al permitir que los métodos devuelvan valores cuyos tipos están determinados por los parámetros de tipo.

Considere este ejemplo:```java
public class GenericReturn {
    public static <T> T identity(T value) {
        return value;
    }
}
```El método "identidad" toma un valor de tipo "T" y simplemente lo devuelve. El método utiliza el parámetro de tipo `T` para especificar tanto el tipo de parámetro de entrada como el tipo de retorno. Este es un ejemplo sencillo de cómo devolver el mismo tipo que la entrada.

Sin embargo, también puedes devolver un tipo diferente según la entrada, por ejemplo:```java
public class GenericReturn {
    public static <T, R> R process(T input, Function<T, R> processor) {
        return processor.apply(input);
    }
}
```En este ejemplo, el método "proceso" toma una entrada de tipo "T" y una "Función" que convierte "T" en "R". El método aplica la función `procesador` a la entrada y devuelve el resultado de tipo `R`. Esto demuestra cómo puede devolver un tipo diferente según la entrada y una función proporcionada.

O puede devolver una colección genérica:```java
public class GenericReturn {
    public static <T> List<T> toList(T... elements) {
        return Arrays.asList(elements);
    }
}
```En este ejemplo, el método `toList` toma un parámetro varargs de tipo `T` y devuelve una `List` de tipo `T`. Este método convierte los elementos de entrada en una lista genérica, mostrando cómo se puede devolver una colección genérica.

Y aquí hay un ejemplo de devolución de un tipo genérico basado en múltiples parámetros de tipo:```java
public class GenericReturn {
    public static <K, V> Map<K, V> singletonMap(K key, V value) {
        return Collections.singletonMap(key, value);
    }
}
```El método `singletonMap` toma una clave de tipo `K` y un valor de tipo `V` y devuelve un `Map` con el par clave-valor. Este método demuestra cómo se puede devolver un tipo genérico que depende de múltiples parámetros de tipo.

Estos ejemplos muestran la flexibilidad y el poder de devolver tipos genéricos. 

Al diseñar métodos con tipos de devolución genéricos, considere lo siguiente:
- Utilice nombres de parámetros de tipo descriptivos y significativos para mejorar la legibilidad del código.
- Asegúrese de que el tipo devuelto sea compatible con el uso previsto del método.
- Considere el impacto en la complejidad y la capacidad de mantenimiento del código cuando se utilizan ampliamente tipos de retorno genéricos.

Al aprovechar los tipos de devolución genéricos, puede crear métodos que se adapten a diferentes tipos de entrada y tipos de devolución, lo que hace que su código sea más reutilizable y aplicable a diversos escenarios.


### Sobrecarga de un método genérico
La sobrecarga de métodos es una característica fundamental en Java que permite múltiples métodos con el mismo nombre pero diferentes tipos de parámetros en la misma clase. Este principio se extiende también a los métodos genéricos. Puede sobrecargar un método genérico proporcionando diferentes tipos de parámetros o utilizando diferentes tipos de parámetros.

Considere el siguiente ejemplo:```java
public class GenericMethodOverloading {
    public static <T> void print(T item) {
        System.out.println("Printing single item: " + item);
    }

    public static <T> void print(T item1, T item2) {
        System.out.println("Printing two items: " + item1 + ", " + item2);
    }

    public static <T, U> void print(T item1, U item2) {
        System.out.println("Printing two items of different types: " + item1 + ", " + item2);
    }
}
```En este ejemplo, tenemos tres versiones sobrecargadas del método genérico "imprimir":
1. El primer método toma un único parámetro genérico "T" y lo imprime.
2. El segundo método toma dos parámetros genéricos del mismo tipo "T" y los imprime.
3. El tercer método toma dos parámetros genéricos de diferentes tipos "T" y "U" y los imprime.

Al llamar a estos métodos, el compilador determinará qué versión invocar según la cantidad y los tipos de argumentos proporcionados.```java
GenericMethodOverloading.print("Hello");
GenericMethodOverloading.print(10, 20);
GenericMethodOverloading.print("Hello", 42);
```En el fragmento de código anterior:
- La primera llamada al método invocará la versión de un solo parámetro de `print`, con `T` inferida como `String`.
- La segunda llamada al método invocará la versión de dos parámetros de `print` con el mismo tipo, con `T` inferido como `Integer`.
- La tercera llamada al método invocará la versión de dos parámetros de `print` con diferentes tipos, con `T` inferida como `String` y `U` inferida como `Integer`.

La sobrecarga de métodos genéricos proporciona flexibilidad y le permite definir múltiples variaciones de un método que puede manejar diferentes tipos o combinaciones de tipos.

Sin embargo, es importante tener cuidado al sobrecargar métodos genéricos. Es posible que el mecanismo de inferencia de tipos del compilador no siempre pueda determinar la versión prevista del método a llamar, especialmente si los métodos sobrecargados tienen parámetros de tipo similares. En tales casos, es posible que necesite especificar explícitamente los argumentos de tipo para eliminar la ambigüedad de la llamada al método.```java
GenericMethodOverloading.<String>print("Hello");
```En este ejemplo, especificamos explícitamente el argumento de tipo `<String>` para garantizar que se llame a la versión de un solo parámetro de `print`.


### Implementación de interfaces genéricas
Así como puedes definir clases genéricas, también puedes definir interfaces genéricas en Java. Las interfaces genéricas proporcionan una forma de especificar un contrato que las clases pueden implementar, lo que permite una mayor flexibilidad y reutilización.

Repasemos un ejemplo para entender cómo implementar interfaces genéricas:```java
public interface Processor<T> {
    void process(T data);
}

public class StringProcessor implements Processor<String> {
    @Override
    public void process(String data) {
        System.out.println("Processing string: " + data);
    }
}

public class IntegerProcessor implements Processor<Integer> {
    @Override
    public void process(Integer data) {
        System.out.println("Processing integer: " + data);
    }
}
```En este ejemplo, tenemos una interfaz genérica `Procesador<T>`. La interfaz declara un único método "proceso" que toma un argumento de tipo "T". El objetivo de esta interfaz es definir un contrato para el procesamiento de datos de tipo "T".

Luego tenemos dos clases, `StringProcessor` e `IntegerProcessor`, que implementan la interfaz `Processor` con diferentes parámetros de tipo.

La clase `StringProcessor` implementa `Processor<String>`, lo que indica que proporcionará una implementación del método `process` que maneja los datos `String`. Dentro del método `proceso`, simplemente imprimimos un mensaje junto con la cadena de datos proporcionada.

De manera similar, la clase `IntegerProcessor` implementa `Processor<Integer>`, especificando que procesará datos `Integer`. El método `proceso` de esta clase imprime un mensaje junto con los datos enteros proporcionados.

Al implementar la interfaz genérica `Processor`, ambas clases cumplen el contrato de procesamiento de datos, pero pueden manejar diferentes tipos (`String` y `Integer` en este caso).

Así es como puedes usar las clases `StringProcessor` e `IntegerProcessor`:```java
Processor<String> stringProcessor = new StringProcessor();
stringProcessor.process("Hello, World!");

Processor<Integer> integerProcessor = new IntegerProcessor();
integerProcessor.process(42);
```En este ejemplo, creamos instancias de `StringProcessor` e `IntegerProcessor` y las asignamos a variables de tipo `Processor<String>` y `Processor<Integer>`, respectivamente. Luego invocamos el método "proceso" en cada procesador, pasando el tipo de datos apropiado.

El resultado de este fragmento de código es:```
Processing string: Hello, World!
Processing integer: 42
```La implementación de interfaces genéricas permite la reutilización de código, el polimorfismo y la capacidad de crear clases y algoritmos de propósito más general.

Sin embargo, es necesario tener en cuenta lo siguiente:
- El parámetro de tipo especificado en la declaración de la interfaz debe coincidir con el parámetro de tipo utilizado en la clase de implementación.
- La clase implementadora debe proporcionar una implementación para todos los métodos declarados en la interfaz genérica.
- El parámetro de tipo se puede utilizar dentro de la clase de implementación para definir campos, parámetros de métodos y tipos de retorno.


### Creación de registros genéricos
Los registros proporcionan una forma concisa de definir clases de datos inmutables. También pueden ser genéricos, lo que le permite crear estructuras de datos flexibles y reutilizables.

A continuación se muestra un ejemplo de creación de un registro genérico:```java
public record Pair<T, U>(T first, U second) {
    public Pair {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Both elements must be non-null");
        }
    }
}
```En este ejemplo, definimos un registro genérico llamado "Par". Tiene dos parámetros de tipo, `T` y `U`, que representan los tipos del primer y segundo elemento del par.

El registro `Pair` tiene dos componentes: `primero` de tipo `T` y `segundo` de tipo `U`. Estos componentes se traducen automáticamente en campos "finales privados" y métodos de acceso "públicos".

También incluimos un constructor compacto en la definición del registro. El constructor compacto nos permite agregar validación o lógica adicional durante la creación de una instancia "Pair". En este caso, verificamos si "primero" o "segundo" es "nulo" y, de ser así, lanzamos una "IllegalArgumentException" para exigir que ambos elementos no sean nulos.

Crear y usar instancias del registro genérico "Pair" es sencillo:```java
Pair<String, Integer> pair1 = new Pair<>("Hello", 42);
System.out.println(pair1.first() + ", " + pair1.second());

Pair<Double, Boolean> pair2 = new Pair<>(3.14, true);
System.out.println(pair2.first() + ", " + pair2.second());
```En este ejemplo, creamos dos instancias del registro "Pair" con argumentos de diferentes tipos. `pair1` es un `Pair<String, Integer>`, que representa un par de una cadena y un número entero. Lo creamos pasando los valores "Hola" y 42 al constructor.

De manera similar, `pair2` es un `Pair<Double, Boolean>`, que representa un par de doble y booleano. Lo creamos pasando los valores `3.14` y `true` al constructor.

Podemos acceder a los componentes de las instancias "Pair" utilizando los métodos de acceso generados automáticamente "primero()" y "segundo()".

El resultado de este fragmento de código sería:```
Hello, 42
3.14, true
```### Tipos genéricos delimitadores
Cuando se trabaja con genéricos, puede haber situaciones en las que desee restringir los tipos que se pueden utilizar como argumentos de tipo. Aquí es donde entran en juego los tipos genéricos delimitadores. Java proporciona tres formas de vincular tipos genéricos: comodines ilimitados, comodines con límites superiores y comodines con límites inferiores.

#### Comodines ilimitados
Los comodines ilimitados, representados por el símbolo `?`, brindan la mayor flexibilidad cuando se trabaja con tipos genéricos. Permiten utilizar cualquier tipo como argumento de tipo, lo que los hace útiles en situaciones en las que no hay restricciones de tipo específicas.

Considere este ejemplo:```java
public static void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}
```Aquí, tenemos un método genérico `printList` que acepta una `List<?>` como parámetro. El comodín ilimitado `?` significa que el método puede aceptar una lista de cualquier tipo. Dentro del método, iteramos sobre la lista e imprimimos cada elemento.

A continuación se muestra un ejemplo de cómo llamar al método `printList`:```java
List<String> stringList = Arrays.asList("Hello", "World");
printList(stringList);

List<Integer> integerList = Arrays.asList(1, 2, 3);
printList(integerList);
```En el código anterior, creamos una `List<String>` y una `List<Integer>`, y las pasamos al método `printList`. El método puede manejar listas de cualquier tipo gracias al comodín ilimitado.

Una cosa a tener en cuenta es que cuando se utiliza un comodín ilimitado, sólo se puede leer de la colección y tratar los elementos como objetos de la clase "Objeto". No puede agregar elementos a la colección porque el compilador no conoce el tipo específico de elementos.

Los comodines ilimitados son útiles cuando desea escribir código genérico que pueda funcionar con cualquier tipo, sin imponer restricciones de tipo específicas.

#### Comodines de límite superior
Comodines de límite superior, representados por `? extiende el tipo`, restringe los tipos que se pueden usar como argumentos de tipo a subtipos del tipo especificado. Proporcionan una forma de escribir código genérico más específico y al mismo tiempo permiten flexibilidad.

Considere este ejemplo:```java
public static double sumNumbers(List<? extends Number> numbers) {
    double sum = 0;
    for (Number number : numbers) {
        sum += number.doubleValue();
    }
    return sum;
}
```Aquí tenemos un método genérico `sumNumbers` que acepta una `List<? extiende Número>` como parámetro. El comodín de límite superior `? extiende Número` significa que el método puede aceptar una lista de cualquier tipo que sea un subtipo de `Número`, como `Entero`, `Doble` o `Largo`.

A continuación se muestra un ejemplo de cómo llamar al método `sumNumbers`:```java
List<Integer> integerList = Arrays.asList(1, 2, 3);
double integerSum = sumNumbers(integerList);
System.out.println("Sum of integers: " + integerSum);

List<Double> doubleList = Arrays.asList(1.5, 2.7, 3.2);
double doubleSum = sumNumbers(doubleList);
System.out.println("Sum of doubles: " + doubleSum);
```En el código anterior, creamos una `List<Integer>` y una `List<Double>`, y las pasamos al método `sumNumbers`. El método puede manejar listas de cualquier subtipo de "Número" debido al comodín de límite superior.

Al usar un comodín de límite superior, podemos invocar de forma segura métodos definidos en la clase `Number`, como `doubleValue()`, en los elementos de la lista. Esto nos permite realizar operaciones específicas sobre los elementos manteniendo la seguridad del tipo.

Sin embargo, al igual que con los comodines ilimitados, no se pueden agregar elementos a una colección con un comodín de límite superior porque el compilador no conoce el subtipo específico de los elementos.

Los comodines de límite superior son útiles cuando desea escribir código genérico que opere en una jerarquía de tipos específica, lo que permite flexibilidad dentro de esa jerarquía.

#### Comodines de límite inferior
Comodines de límite inferior, representados por `? super tipo`, restringe los tipos que se pueden usar como argumentos de tipo a supertipos del tipo especificado. Proporcionan una forma de escribir código genérico que puede funcionar con un tipo específico y sus supertipos.

Considere este ejemplo:```java
public static void addNumbers(List<? super Integer> numbers) {
    numbers.add(10);
    numbers.add(20);
    numbers.add(30);
}
```Aquí tenemos un método genérico `addNumbers` que acepta una `List<? super Integer>` como parámetro. El comodín de límite inferior `? super Integer` significa que el método puede aceptar una lista de cualquier tipo que sea un supertipo de `Integer`, como `Number` u `Object`.

A continuación se muestra un ejemplo de cómo llamar al método `addNumbers`:```java
List<Integer> integerList = new ArrayList<>();
addNumbers(integerList);
System.out.println("Integer list: " + integerList);

List<Number> numberList = new ArrayList<>();
addNumbers(numberList);
System.out.println("Number list: " + numberList);
```En el código anterior, creamos una `List<Integer>` vacía y una `List<Number>` vacía, y las pasamos al método `addNumbers`. El método puede agregar objetos "Integer" a ambas listas porque "Integer" es un subtipo de "Number" y "Object".

A diferencia de los comodines ilimitados y de límite superior, con los comodines de límite inferior, puedes agregar de forma segura elementos del tipo especificado (`Integer` en este caso) a la colección. Esto se debe a que el compilador sabe que la colección puede contener elementos del tipo especificado o sus supertipos.

Sin embargo, al leer elementos de una colección con un comodín de límite inferior, solo puede tratarlos como objetos del tipo especificado o sus supertipos. No se puede asumir ningún tipo de información más específica.

Los comodines de límite inferior son útiles cuando desea escribir código genérico que pueda aceptar un tipo específico y sus supertipos, lo que le permite agregar elementos de ese tipo a la colección.

Cada tipo de comodín tiene un propósito específico y proporciona diferentes capacidades cuando se trabaja con tipos genéricos. Recuerda:
- Utilice comodines ilimitados (`?`) cuando no tenga restricciones de tipo específicas y desee permitir cualquier tipo.
- Utilice comodines de límite superior (`? extiende el tipo`) cuando desee restringir los tipos a subtipos de un tipo específico y realizar operaciones específicas para ese tipo.
- Utilice comodines de límite inferior (`? super tipo`) cuando desee restringir los tipos a supertipos de un tipo específico y agregar elementos de ese tipo a la colección.


## El marco de colecciones
Una de las partes más útiles de la biblioteca estándar de Java es Collections Framework, que proporciona un conjunto de componentes reutilizables para gestionar grupos de objetos. El marco incluye varias interfaces principales que se extienden desde la interfaz `java.util.Collection` (que a su vez se extiende desde `java.lang.Iterable`) para definir los diferentes tipos de colecciones:

- La interfaz `List` representa una colección ordenada que permite elementos duplicados. Sus principales implementaciones son `ArrayList`, que está respaldada por una matriz de tamaño variable, y `LinkedList`, que utiliza una lista doblemente enlazada.

- La interfaz `Set` define una colección que no permite elementos duplicados. La clase `HashSet` proporciona una implementación de tabla hash, mientras que `TreeSet` usa un árbol rojo-negro para almacenar sus elementos, manteniéndolos en orden ascendente.

- La interfaz `Deque`, que significa *cola de doble extremo*, representa una colección que permite la inserción y eliminación en ambos extremos. Las implementaciones principales incluyen `ArrayDeque` y `LinkedList`, y `ArrayDeque` a menudo proporciona un mejor rendimiento para la mayoría de las operaciones.

- La interfaz `Map` asigna claves únicas a valores. La clase `HashMap` usa una tabla hash, proporcionando rendimiento en tiempo constante para operaciones básicas, mientras que `TreeMap` usa un árbol rojo-negro y ordena sus elementos según el orden natural de la clave o un `Comparador` proporcionado.

Sin embargo, vale la pena señalar que si bien la interfaz "Map" es parte del marco de colecciones de Java, no es descendiente de la interfaz "Collection".

Java 21 introdujo tres nuevas interfaces para representar colecciones con un orden de encuentro definido:

- `SequencedCollection`: una colección con un orden de encuentro bien definido, que proporciona API uniformes para acceder al primer y último elemento y procesar elementos en orden directo e inverso.

- `SequencedSet`: un conjunto con un orden de encuentro definido, que extiende tanto `Set` como `SequencedCollection`.

- `SequencedMap`: un mapa con un orden de encuentro definido para sus entradas, claves y valores.

Estas nuevas interfaces proporcionan una forma más coherente de trabajar con colecciones ordenadas en diferentes implementaciones.

Aquí hay un diagrama que muestra la jerarquía de estas colecciones, incluidas las nuevas interfaces secuenciadas:```
               ┌───────────────┐               ┌───────────┐
               │  Collection   │               │    Map    │
               └───────┬───────┘               └─────┬─────┘
                       │                             │
    ┌──────────────────┼──────────────┐              │
    │                  │              │              │
┌───┴──────┐    ┌──────┴──────┐  ┌────┴────┐   ┌─────┴─────┐
│   Set    │    │  Sequenced  │  │  Queue  │   │ Sequenced │
│          │    │ Collection  │  │         │   │    Map    │
└─┬─────┬──┘    └──────┬──────┘  └────┬────┘   └─────┬─────┘
  │     │   ┌──────────┼───────┐      │              │
  │     │   │          │       │      │              │
  │ ┌───┴───┴─┐   ┌────┴───┐   │  ┌───┴────┐    ┌────┴────┐
  │ │Sequenced│   │  List  │   └──│  Deque │    │ Sorted  │
  │ │   Set   │   └────────┘      └────────┘    │   Map   │
  │ └───┬─────┘                                 └─────────┘
  │     │                       
  │ ┌───┴────┐ 
  └─│ Sorted │ 
    │  Set   │ 
    └────────┘ 
```Al declarar una colección, podemos aprovechar el *Operador Diamante* (`<>`) para especificar el tipo:```java
List<Integer> numbers = new ArrayList<>();
Map<String, Person> people = new HashMap<>();
```El compilador inferirá los argumentos de tipo para el constructor basándose en la declaración de la variable.

Hay varias operaciones comunes que podemos realizar en una colección. Para agregar un solo elemento, usamos el método `add`:```java
List<String> words = new ArrayList<>();
words.add("hello");
words.add("world");
```Para agregar todos los elementos de otra colección, use `addAll`:```java
List<String> moreWords = Arrays.asList("goodbye", "cruel", "world");
words.addAll(moreWords);
```Eliminamos elementos con el método `remove`, especificando el objeto a eliminar o su índice para colecciones ordenadas:```java 
words.remove("hello");
words.remove(1); // removes element at index 1
```El método `size` devuelve el número de elementos actualmente en la colección:```java
int count = words.size(); 
```Para eliminar todos los elementos de una colección, llame al método "clear":```java
words.clear();
```El método `contains` comprueba si una colección contiene un elemento específico y devuelve `true` si se encuentra o `false` en caso contrario:```java
boolean found = words.contains("hello");
```El método `removeIf` permite eliminar todos los elementos que satisfacen un predicado determinado:```java
words.removeIf(word -> word.length() < 5);
```El fragmento de código anterior elimina todas las cadenas con menos de 5 caracteres de la lista de "palabras".

El método `forEach` (que en realidad proviene de la interfaz `java.lang.Iterable`) realiza una acción determinada en cada elemento de la colección:```java
words.forEach(word -> System.out.println(word));
```Esto imprime cada palabra de la lista en la consola.

El método `equals` comprueba si otro objeto es igual a la colección. Para que dos colecciones se consideren iguales, deben contener los mismos elementos en el mismo orden (para colecciones ordenadas) o los mismos elementos en cualquier orden (para colecciones desordenadas):```java
List<String> list1 = Arrays.asList("a", "b", "c");
List<String> list2 = Arrays.asList("a", "b", "c");
List<String> list3 = Arrays.asList("c", "b", "a");

System.out.println(list1.equals(list2)); // true
System.out.println(list1.equals(list3)); // false

Set<String> set1 = new HashSet<>(Arrays.asList("a", "b", "c"));
Set<String> set2 = new HashSet<>(Arrays.asList("c", "b", "a"));

System.out.println(set1.equals(set2)); // true
```Es importante tener en cuenta que para que dos colecciones sean iguales, los elementos que contienen también deben implementar correctamente el método "equals".

Con la introducción de colecciones secuenciadas en Java 21, ahora tenemos métodos consistentes para trabajar con el primer y último elemento de las colecciones con un orden de encuentro definido:```java
SequencedCollection<String> seq = new ArrayList<>(List.of("first", "second", "third"));

String first = seq.getFirst(); // "first"
String last = seq.getLast();   // "third"

seq.addFirst("new first");
seq.addLast("new last");

SequencedCollection<String> reversed = 
    seq.reversed(); // [new last, third, second, first, new first]
```Estos métodos están disponibles en `List`, `Deque`, `LinkedHashSet` y otras colecciones que implementan las nuevas interfaces secuenciadas.

En las siguientes secciones, revisaremos con más detalle cada una de estas interfaces.

## La interfaz `Lista`
Como se mencionó anteriormente, la interfaz `List` representa una colección ordenada que permite elementos duplicados. Las dos implementaciones principales de "List" son "ArrayList" y "LinkedList". Si bien ambas clases implementan la misma interfaz, tienen diferentes características de rendimiento.

Una `ArrayList` está respaldada por una matriz dinámica, que proporciona un rendimiento amortizado en tiempo constante para las operaciones básicas (agregar al final, obtener y establecer), suponiendo que se conozca el índice. Sin embargo, insertar o eliminar elementos desde el medio de una `ArrayList` puede ser lento, ya que requiere desplazar todos los elementos posteriores, lo que resulta en una complejidad `O(n)`.

Por otro lado, una `LinkedList` almacena sus elementos en una lista doblemente enlazada. Esto proporciona un rendimiento en tiempo constante para las operaciones de inserción y eliminación en ambos extremos de la lista. Sin embargo, acceder a los elementos por índice requiere recorrer la lista desde el principio o el final, lo que lleva un tiempo lineal. La inserción o eliminación en el medio de la lista también requiere tiempo lineal.

Por lo tanto, si su aplicación necesita acceder principalmente a elementos por índice, una `ArrayList` es generalmente la mejor opción. Si inserta o elimina elementos con frecuencia del medio de la lista, una "LinkedList" puede ser una mejor opción.

### Creando una `Lista`
El método más común para crear una instancia de "Lista" es utilizar un constructor:```java
List<String> fruits = new ArrayList<>();
List<String> vegetables = new LinkedList<>();
```También puedes crear una `Lista` a partir de una matriz usando el método `Arrays.asList`:```java
String[] fruitArray = {"apple", "banana", "orange"};
List<String> fruits = Arrays.asList(fruitArray);
```Tenga en cuenta que la `Lista` devuelta por `Arrays.asList` está respaldada por la matriz original, por lo que cualquier cambio realizado en la matriz se reflejará en la `Lista` y viceversa. Además, esta "Lista" tiene un tamaño fijo, por lo que no puede agregar ni eliminar elementos.

También puedes usar los métodos de fábrica `List.of` y `List.copyOf` para crear listas no modificables:```java
List<String> fruits = List.of("apple", "banana", "orange");
List<String> vegetables = List.copyOf(new ArrayList<>(Arrays.asList("carrot", "broccoli", "potato")));
```El método `List.of` toma un parámetro varargs, lo que le permite especificar los elementos individualmente, mientras que `List.copyOf` crea una nueva `List` no modificable a partir de una colección existente. Estas listas no modificables generarán "UnsupportedOperationException" si intenta modificarlas.

### Trabajar con métodos `List`
La interfaz `List` proporciona varios métodos para trabajar con sus elementos. El método `add` inserta un elemento en una posición especificada o lo agrega al final de la `Lista`:```java
List<String> fruits = new ArrayList<>();
fruits.add("apple");
fruits.add(0, "banana");
```Los métodos `get` y `set` le permiten acceder y modificar elementos según sus índices:```java
String fruit = fruits.get(0);
fruits.set(1, "orange");
```Para eliminar un elemento, utilice el método `remove`, especificando el objeto a eliminar o su índice:```java
fruits.remove("banana");
fruits.remove(0);
```El método `replaceAll` aplica una función dada a cada elemento de la `List`, reemplazando cada elemento con el resultado de la función:```java
fruits.replaceAll(String::toUpperCase);
```Para ordenar los elementos de una "Lista", utilice el método "sort":```java
fruits.sort(Comparator.naturalOrder());
```El método "ordenar" utiliza el orden natural de los elementos, o puede proporcionar un "Comparador" personalizado.

Para convertir una `Lista` en una matriz, use el método `toArray`:```java
String[] fruitArray = fruits.toArray(new String[0]);
```El método `toArray` toma un parámetro de matriz, que sirve como tipo de retorno y también se puede usar para dimensionar la matriz resultante si es lo suficientemente grande. Si la matriz proporcionada es más pequeña que la "Lista", se creará una nueva matriz del mismo tipo de tiempo de ejecución con el tamaño de la "Lista".


## La interfaz `Establecer`
La interfaz `Set` define una colección que no permite elementos duplicados. Las principales implementaciones de "Set" son "HashSet", "LinkedHashSet" y "TreeSet". Cada una de estas clases tiene diferentes características y casos de uso:

- Un `HashSet` almacena sus elementos en una tabla hash, proporcionando un rendimiento en tiempo constante para operaciones básicas (agregar, eliminar, contener y dimensionar), suponiendo que la función hash disperse los elementos adecuadamente entre los depósitos. Sin embargo, un `HashSet` no mantiene ningún orden de sus elementos.

- Un `LinkedHashSet` es una versión ordenada de `HashSet` que mantiene una lista doblemente enlazada que recorre todas sus entradas. Esto permite que `LinkedHashSet` conserve el orden de inserción de los elementos. El `LinkedHashSet` tiene un consumo de memoria ligeramente mayor y un rendimiento ligeramente más lento para operaciones básicas que un `HashSet`.

- Un `TreeSet` almacena sus elementos en un árbol rojo-negro, manteniéndolos en orden ascendente según su orden natural o un `Comparador` proporcionado. Esto proporciona un costo de tiempo log(n) garantizado para operaciones básicas, pero generalmente es más lento que un `HashSet`.

Al elegir una implementación `Set`, considere lo siguiente:
- Si necesita un rendimiento en tiempo constante y no le importa el orden de los elementos, utilice un `HashSet`.
- Si necesita mantener el orden de inserción de los elementos, utilice un `LinkedHashSet`.
- Si necesita mantener los elementos ordenados, utilice un `TreeSet`.

### Creando un `Conjunto`
Puedes crear un `Set` usando un constructor, al igual que con las listas:```java
Set<String> fruits = new HashSet<>();
Set<String> vegetables = new LinkedHashSet<>();
Set<String> nuts = new TreeSet<>();
```Alternativamente, también puedes usar los métodos de fábrica `Set.of` y `Set.copyOf` para crear conjuntos no modificables:```java
Set<String> fruits = Set.of("apple", "banana", "orange");
Set<String> vegetables = Set.copyOf(List.of("carrot", "broccoli", "potato"));
```### Trabajar con métodos `Set`
La interfaz `Set` proporciona varios métodos para trabajar con sus elementos. El método `add` inserta un elemento en el `Set` si aún no está presente:```java
Set<String> fruits = new HashSet<>();
fruits.add("apple");
fruits.add("banana");
fruits.add("apple"); // This will not be added, as "apple" is already in the Set
```Para verificar si un elemento está presente en el `Set`, use el método `contains`:```java
boolean containsApple = fruits.contains("apple"); // true
```Para eliminar un elemento del `Set`, utilice el método `remove`:```java
fruits.remove("banana");
```El método `size` devuelve el número de elementos en el `Set`:```java
int numberOfFruits = fruits.size();
```Para iterar sobre los elementos de un `Set`, puedes usar un bucle `for-each` o el método `forEach`:```java
for (String fruit : fruits) {
    System.out.println(fruit);
}

fruits.forEach(System.out::println);
```## La interfaz `Deque`
La interfaz `Deque`, que significa *cola de dos extremos*, representa una colección que permite la inserción y eliminación tanto al principio como al final del deque. Las principales implementaciones de `Deque` son `ArrayDeque` y `LinkedList`:

- Un `ArrayDeque` es una implementación de matriz redimensionable de la interfaz `Deque`. Proporciona rendimiento en tiempo constante para operaciones de inserción y eliminación en ambos extremos de la deque, lo que la hace más eficiente que una "LinkedList" para la mayoría de los casos de uso. Un `ArrayDeque` no tiene una capacidad fija y crecerá automáticamente según sea necesario.

- Una `LinkedList` es una implementación de lista doblemente enlazada que también implementa la interfaz `Deque`. Proporciona rendimiento en tiempo constante para operaciones de inserción y eliminación en ambos extremos de la lista. `LinkedList` es adecuado cuando necesita una implementación `Deque` que también pueda funcionar como una `List`.

Al elegir una implementación `Deque`, considere lo siguiente:
- Si necesita principalmente una cola de dos extremos, utilice un `ArrayDeque` para un mejor rendimiento.
- Si necesita una implementación `Deque` que también pueda funcionar como una `List`, use una `LinkedList`.

### Creando un `Deque`
Puedes crear un `Deque` usando un constructor, similar a otros tipos de colección:```java
Deque<String> fruits = new ArrayDeque<>();
Deque<String> vegetables = new LinkedList<>();
```También puede especificar una capacidad inicial para un `ArrayDeque`:```java
Deque<String> fruits = new ArrayDeque<>(20);
```Esto crea un `ArrayDeque` con una capacidad inicial de 20 elementos. Si el número de elementos excede la capacidad inicial, `ArrayDeque` crecerá automáticamente según sea necesario.

Para una `LinkedList`, puedes crear una deque vacía o inicializarla con otra colección:```java
Deque<String> fruits = new LinkedList<>();
List<String> fruitList = Arrays.asList("apple", "banana", "orange");
Deque<String> fruitDeque = new LinkedList<>(fruitList);
```### Trabajando con métodos `Deque`
La interfaz Deque proporciona varios métodos para trabajar con elementos en ambos extremos del deque. Los métodos `addFirst` y `addLast` insertan elementos al principio y al final del deque, respectivamente:```java
Deque<String> fruits = new ArrayDeque<>();
fruits.addFirst("apple");
fruits.addLast("banana");
```Los métodos `getFirst` y `getLast` recuperan, pero no eliminan, los elementos al principio y al final del deque. Si el deque está vacío, arrojan una `NoSuchElementException`:```java
String firstFruit = fruits.getFirst();
String lastFruit = fruits.getLast();
```Para eliminar y devolver los elementos al principio y al final de la deque, utilice los métodos `removeFirst` y `removeLast`. Si el deque está vacío, arrojan una `NoSuchElementException`:```java
String removedFirstFruit = fruits.removeFirst();
String removedLastFruit = fruits.removeLast();
```La interfaz `Deque` también proporciona métodos para usar deque como pila. El método `push` inserta un elemento al principio del deque, el método `pop` elimina y devuelve el elemento al principio, y el método `peek` recupera, pero no elimina, el elemento al principio:```java
Deque<String> stack = new ArrayDeque<>();
stack.push("apple");
stack.push("banana");

String topElement = stack.peek(); // banana
String poppedElement = stack.pop(); // banana
```Estos métodos son equivalentes a usar `addFirst`, `removeFirst` y `getFirst`, respectivamente, pero proporcionan una convención de nomenclatura más intuitiva cuando se usa deque como pila.

Además, la interfaz `Deque` proporciona los métodos `offerFirst`, `offerLast`, `peekFirst`, `peekLast`, `pollFirst` y `pollLast`, que son similares a sus contrapartes sin el prefijo `offer`, `peek` o `poll` pero se comportan de manera diferente cuando el deque está vacío:

- `offerFirst` y `offerLast`: insertan elementos al principio y al final del deque, respectivamente. Devuelven un valor "booleano" que indica si la inserción fue exitosa.```java
boolean addedFirst = fruits.offerFirst("apple");
boolean addedLast = fruits.offerLast("banana");
```- `peekFirst` y `peekLast`: recuperan, pero no eliminan, los elementos al principio y al final del deque. Devuelven "nulo" si la deque está vacía.```java
String firstFruit = fruits.peekFirst(); // banana
String lastFruit = fruits.peekLast(); // apple
```- `pollFirst` y `pollLast`: eliminan y devuelven los elementos al principio y al final del deque. Devuelven "nulo" si la deque está vacía.```java
String removedFirstFruit = fruits.pollFirst(); // banana
String removedLastFruit = fruits.pollLast(); // apple
```Estos métodos son útiles cuando desea evitar excepciones y manejar casos especiales con mayor elegancia.


## La interfaz `Mapa`
La interfaz `Map` representa una colección que asigna claves únicas a valores. No es un subtipo de la interfaz `Collection`, pero todavía se considera parte del marco de colecciones de Java. Las principales implementaciones de "Map" son "HashMap", "LinkedHashMap" y "TreeMap".

- Un `HashMap` es una implementación de la interfaz `Map` que almacena pares clave-valor en una tabla hash. Proporciona rendimiento en tiempo constante para operaciones básicas (poner, obtener, eliminar) suponiendo que la función hash disperse los elementos adecuadamente entre los depósitos. `HashMap` no garantiza ningún orden de los elementos.

- Un `LinkedHashMap` es una implementación de la interfaz `Map` que mantiene una lista doblemente enlazada que recorre todas sus entradas. Esto le permite preservar el orden de inserción de los pares clave-valor. `LinkedHashMap` proporciona un rendimiento casi idéntico a `HashMap` para operaciones básicas.

- Un `TreeMap` es una implementación de la interfaz `Map` que almacena sus entradas en un árbol rojo-negro, ordenado según el orden natural de sus claves o mediante un `Comparador` proporcionado. Esto proporciona un costo de tiempo `log(n)` garantizado para operaciones básicas, pero generalmente es más lento que `HashMap`.

Al elegir una implementación de "Mapa", considere lo siguiente:
- Si necesita un rendimiento en tiempo constante y no le importa el orden de los elementos, utilice un `HashMap`.
- Si necesita mantener el orden de inserción de los pares clave-valor, utilice un `LinkedHashMap`.
- Si necesita mantener las entradas ordenadas por sus claves, utilice un `TreeMap`.

### Creando un `Mapa`
Puedes crear un `Mapa` usando un constructor, similar a otros tipos de colección:```java
Map<String, Integer> fruitCounts = new HashMap<>();
Map<String, Integer> vegetableCounts = new LinkedHashMap<>();
Map<String, Integer> nutCounts = new TreeMap<>();
```También puedes crear un `Map` con una capacidad inicial y un factor de carga (para `HashMap` y `LinkedHashMap`):```java
Map<String, Integer> fruitCounts = new HashMap<>(20, 0.8f);
```Esto crea un "HashMap" con una capacidad inicial de 20 y un factor de carga de 0,8. El factor de carga determina cuándo se debe cambiar el tamaño de "HashMap" para mantener el rendimiento.

### Trabajar con métodos `Map`
La interfaz `Map` proporciona varios métodos para trabajar con sus pares clave-valor:

- `clear`: Elimina todas las entradas del mapa.```java
fruitCounts.clear();
```- `containsKey`: Devuelve `true` si el mapa contiene la clave especificada.```java
boolean containsApple = fruitCounts.containsKey("apple");
```- `containsValue`: Devuelve `true` si el mapa contiene el valor especificado.```java
boolean containsCount = fruitCounts.containsValue(5);
```- `entrySet`: Devuelve una vista `Set` de las entradas en el mapa.```java
Set<Map.Entry<String, Integer>> entries = fruitCounts.entrySet();
```- `forEach`: Realiza la acción dada para cada entrada en el mapa.```java
fruitCounts.forEach((fruit, count) -> System.out.println(fruit + ": " + count));
```- `get`: Devuelve el valor asociado con la clave especificada, o `null` si no se encuentra la clave.```java
Integer appleCount = fruitCounts.get("apple");
```- `getOrDefault`: Devuelve el valor asociado con la clave especificada, o el valor predeterminado dado si no se encuentra la clave.```java
Integer appleCount = fruitCounts.getOrDefault("apple", 0);
```- `isEmpty`: Devuelve `true` si el mapa no contiene entradas.```java
boolean empty = fruitCounts.isEmpty();
```- `keySet`: Devuelve una vista `Set` de las claves en el mapa.```java
Set<String> fruits = fruitCounts.keySet();
```- `merge`: si la clave especificada aún no está asociada con un valor o está asociada con `null`, la asocia con el valor no nulo dado. De lo contrario, reemplaza el valor asociado con los resultados de la función de reasignación dada.```java
fruitCounts.merge("apple", 1, Integer::sum);
```- `put`: Asocia el valor especificado con la clave especificada en el mapa.```java
fruitCounts.put("apple", 5);
```- `putIfAbsent`: si la clave especificada aún no está asociada con un valor (o está asignada a `null`), la asocia con el valor dado y devuelve `null`; de lo contrario, devuelve el valor actual.```java
fruitCounts.putIfAbsent("apple", 5);
```- `remove`: Elimina la entrada de la clave especificada del mapa, si está presente.```java
fruitCounts.remove("apple");
```- `reemplazar`: reemplaza la entrada de la clave especificada solo si actualmente está asignada a algún valor.```java
fruitCounts.replace("apple", 6);
```- `replaceAll`: reemplaza el valor de cada entrada con el resultado de invocar la función dada en esa entrada hasta que todas las entradas hayan sido procesadas o la función arroje una excepción.```java
fruitCounts.replaceAll((fruit, count) -> count * 2);
```- `size`: Devuelve el número de entradas en el mapa.```java
int numberOfFruits = fruitCounts.size();
```- `valores`: Devuelve una vista de `Colección` de los valores contenidos en el mapa.```java
Collection<Integer> counts = fruitCounts.values();
```### Anulando `hashCode()`
Cuando se utiliza un `HashMap` o `LinkedHashMap`, es esencial asegurarse de que el método `hashCode` de las claves se anule correctamente. El método `hashCode` debería devolver el mismo código hash para los objetos considerados iguales según el método `equals`. Esto es necesario para que el mapa funcione correcta y eficientemente.

A continuación se muestra un ejemplo de una clase personalizada con los métodos `hashCode` y `equals` anulados correctamente:```java
class Person {
    private String name;
    private int age;

    // Constructor, getters, and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
```En este ejemplo, el método `hashCode` de la clase `Persona` se implementa pasando los campos `nombre` y `edad` al método `Objects.hash`. Esto garantiza que el código hash generado se base en los valores de estos campos.

El método `Objects.hash` es un método de utilidad estático proporcionado por la clase `java.util.Objects` que genera un código hash para una secuencia de valores de entrada. Aquí está la sintaxis general del método `Objects.hash`:```java
public static int hash(Object... values)
```El método acepta cualquier número de argumentos de tipo "Objeto", lo que significa que puede pasar valores de diferentes tipos. Calcula el código hash para cada valor de entrada utilizando sus respectivos métodos "hashCode" y luego los combina para producir un único código hash. También maneja los valores "nulos" correctamente, por lo que no es necesario incluir comprobaciones "nulas" en su implementación de "hashCode".

Es importante tener en cuenta que cuando anula el método `hashCode` usando `Objects.hash`, también debe anular el método `equals` para garantizar que los objetos que se consideran iguales tengan el mismo código hash. Esto es necesario para el correcto funcionamiento de colecciones basadas en hash como `HashMap` y `HashSet`.


## Clasificación de datos
Ordenar es una operación que le permite organizar elementos en un orden específico. En Java, puede ordenar datos usando la interfaz `Comparable` o la interfaz `Comparator`. La interfaz "Comparable" define el orden natural de los elementos, mientras que la interfaz "Comparador" le permite definir un orden personalizado.

### La interfaz `comparable`
Para crear una clase que pueda ordenarse usando su orden natural, necesita implementar la interfaz "Comparable". Define un método único, `compareTo`, que compara el objeto actual con otro objeto del mismo tipo.

A continuación se muestra un ejemplo de una clase `Persona` que implementa `Comparable`:```java
class Person implements Comparable<Person> {
    private String name;
    private int age;

    // Constructor, getters, and setters

    @Override
    public int compareTo(Person other) {
        // Compare by age first, then by name if ages are equal
        int ageComparison = Integer.compare(this.age, other.age);
        if (ageComparison != 0) {
            return ageComparison;
        }
        return this.name.compareTo(other.name);
    }
}
```En este ejemplo, el método `compareTo` primero compara dos objetos `Persona` basándose primero en su edad. Si las edades son iguales, compara lexicográficamente sus nombres. El método `compareTo` devuelve un valor negativo, cero o positivo si el objeto actual es menor, igual o mayor que el otro objeto, respectivamente.

Al implementar el método `compareTo`, es importante manejar los valores `null` correctamente para evitar una `NullPointerException`. Puedes hacer esto agregando una verificación "nula" al comienzo del método:```java
@Override
public int compareTo(Person other) {
    if (other == null) {
        return 1; // Consider non-null values to be greater than null values
    }
    // Rest of the comparison logic
}
```En este ejemplo, si el objeto "otro" es "nulo", el método devuelve 1, lo que indica que el objeto actual es mayor que el valor "nulo". Puede ajustar este comportamiento según sus requisitos específicos.

Además, es importante asegurarse de que el comportamiento del método `compareTo` sea coherente con el método `equals`. Si dos objetos se consideran iguales según el método "equals", su método "compareTo" debería devolver cero.

A continuación se muestra un ejemplo de un método "equals" que es coherente con el método "compareTo":```java
class Person implements Comparable<Person> {
    private String name;
    private int age;

    // Constructor, getters, and setters

    @Override
    public int compareTo(Person other) {
        // Compare by age first, then by name if ages are equal
        int ageComparison = Integer.compare(this.age, other.age);
        if (ageComparison != 0) {
            return ageComparison;
        }
        return this.name.compareTo(other.name);
    }
                                            
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }
}
```En este ejemplo, el método "equals" considera dos objetos Persona iguales si tienen la misma edad y nombre. Esto es consistente con el método `compareTo`, que compara primero la edad y luego el nombre.

### La interfaz `Comparador`
Mientras que la interfaz "Comparable" define el orden natural de los elementos, la interfaz "Comparador" le permite definir un orden personalizado. Un "Comparador" es una clase separada que contiene la lógica de comparación.

A continuación se muestra un ejemplo de un "Comparador" que compara objetos "Persona" por la longitud de su nombre:```java
class NameLengthComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getName().length(), p2.getName().length());
    }
}
```En este ejemplo, el método `compare` de `NameLengthComparator` compara dos objetos `Persona` según la longitud de sus nombres. Devuelve un valor negativo, cero o un valor positivo si la longitud del nombre de la primera persona es menor, igual o mayor que la longitud del nombre de la segunda persona, respectivamente.

Hay varios métodos auxiliares en la interfaz "Comparador" que facilitan la creación de comparadores:

- `comparando`: Crea un comparador basado en una función que extrae una clave `Comparable` de un tipo.```java
Comparator<Person> nameComparator = Comparator.comparing(Person::getName);
```- `comparingDouble`, `comparingInt`, `comparingLong`: crea comparadores basados ​​en funciones que extraen claves `double`, `int` o `long` de un tipo.```java
Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
```- `naturalOrder`, `reverseOrder`: crea comparadores basados ​​en el orden natural o en el orden inverso del orden natural de un tipo.```java
Comparator<String> naturalStringComparator = Comparator.naturalOrder();
Comparator<String> reverseStringComparator = Comparator.reverseOrder();
```También hay métodos predeterminados en la interfaz `Comparador` que le permiten combinar y modificar comparadores:

- `reversed`: Invierte el orden de un comparador.```java
Comparator<Person> reversedNameComparator = nameComparator.reversed();
```- `thenComparing`, `thenComparingDouble`, `thenComparingInt`, `thenComparingLong`: Permite encadenar comparadores.```java
Comparator<Person> nameAndAgeComparator = nameComparator.thenComparingInt(Person::getAge);
```En este ejemplo, `nameAndAgeComparator` primero compara los objetos Persona por su nombre y, si los nombres son iguales, los compara por su edad.

### Comparando `Comparable` y `Comparador`
Tanto "Comparable" como "Comparator" se utilizan para ordenar elementos en Java, pero tienen algunas diferencias clave.

En cuanto a su finalidad:
- `Comparable` se utiliza para definir el orden natural de los elementos dentro de una clase. Es adecuado cuando la clase tiene un orden inherente que es apropiado para la mayoría de los casos de uso.
- `Comparador` se utiliza para definir un orden personalizado para los elementos de una clase. Permite múltiples formas de comparar elementos y es útil cuando el orden natural no es apropiado o cuando necesita ordenar elementos según diferentes criterios.

En cuanto a su implementación:
- `Comparable` es una interfaz implementada por la propia clase. La clase debe definir el método `compareTo`, que compara el objeto actual con otro objeto del mismo tipo y devuelve un valor negativo, cero o positivo si el objeto actual es menor, igual o mayor que el otro objeto, respectivamente.
- `Comparator` es una interfaz que se implementa como una clase separada. La clase que implementa `Comparador` debe definir el método `comparar`, que compara dos objetos de un tipo específico y devuelve un valor negativo, cero o positivo si el primer objeto es menor, igual o mayor que el segundo objeto, respectivamente.

En términos de su flexibilidad:
- `Comparable` proporciona una forma única de comparar elementos de una clase. Una vez que se define el método `compareTo`, se convierte en el orden natural de esa clase. Si necesita cambiar el orden, debe modificar la clase misma.
- `Comparador` permite múltiples formas de comparar elementos de una clase. Puede definir varias clases `Comparator`, cada una con su propio método `compare`, para proporcionar diferentes criterios de ordenación. Esto es particularmente útil cuando necesita ordenar elementos según diferentes atributos o cuando desea tener opciones de clasificación alternativas.

A continuación se muestra un ejemplo que demuestra la flexibilidad de "Comparator":```java
class Person {
    private String name;
    private int age;

    // Constructor, getters, and setters
}

class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}

// Usage
List<Person> people = new ArrayList<>();
// Add elements to the list

// Sort using NameComparator
Collections.sort(people, new NameComparator());

// Sort using AgeComparator
Collections.sort(people, new AgeComparator());
```En este ejemplo, definimos dos clases `Comparator`: `NameComparator` y `AgeComparator`. `NameComparator` compara objetos `Persona` según sus nombres, mientras que `AgeComparator` los compara según sus edades. Podemos usar estos "Comparadores" indistintamente para ordenar la lista de "personas" según diferentes criterios.

En resumen, al elegir entre "Comparable" y "Comparador", considere lo siguiente:
- Si la clase tiene un orden natural que es apropiado para la mayoría de los casos de uso y tienes control sobre la clase, implementa "Comparable".
- Si necesita varias formas de comparar elementos, desea definir un orden personalizado o necesita ordenar elementos de una clase de terceros, utilice `Comparador`.
- Puedes usar `Comparable` y `Comparator` juntos. Si se proporciona un "Comparador" a un método de clasificación, tiene prioridad sobre el orden natural definido por "Comparable".

### `Colecciones.sort` y `Colecciones.binarySearch`
La clase `Colecciones` proporciona métodos de utilidad para trabajar con colecciones, incluidos métodos para ordenar y buscar.

El método `Collections.sort` ordena una `Lista` usando su orden natural (definido por la interfaz `Comparable`) o un `Comparador` proporcionado:```java
List<Person> people = new ArrayList<>();
// Add elements to the list

// Sort using natural ordering (Comparable)
Collections.sort(people);

// Sort using a custom Comparator
Collections.sort(people, new NameLengthComparator());
```En este ejemplo, la primera llamada a `Collections.sort` ordena la lista de `personas` usando el orden natural definido por el método `compareTo` de la clase `Persona`. La segunda llamada ordena la lista usando el `NameLengthComparator` personalizado.

El método `Collections.binarySearch` busca un elemento en una `Lista` ordenada utilizando el algoritmo de búsqueda binaria. La "Lista" debe ordenarse en orden ascendente según el orden natural ("Comparable") o el "Comparador" proporcionado:```java
List<Person> people = new ArrayList<>();
// Add elements to the list and sort it

Person searchKey = new Person("John", 30);
int index = Collections.binarySearch(people, searchKey);
if (index >= 0) {
    System.out.println("Found at index: " + index);
} else {
    System.out.println("Not found");
}
```En este ejemplo, el método `Collections.binarySearch` busca el objeto `searchKey` en la lista ordenada de `personas`. Si se encuentra el elemento, devuelve su índice; de lo contrario, devuelve un valor negativo.

Si la `Lista` no está ordenada o está ordenada según un orden diferente al utilizado en la búsqueda binaria, los resultados no están definidos.

Es importante tener en cuenta que cuando se utiliza `Collections.sort` o `Collections.binarySearch` con un `Comparator` personalizado, el `Comparator` debe ser coherente con `equals` para garantizar un comportamiento adecuado. Si dos elementos son iguales según el "Comparador", también deberían ser iguales según el método "equals".



## Resumen de tipos de colección
Aquí hay algunas tablas que le ayudarán a consultar rápidamente información clave sobre Java Collections Framework:

### Tabla 1: Interfaces e implementaciones de colecciones

| Interfaz | Descripción | Principales Implementaciones | Características |
|-----------|-------------|----------------------|-----------------|
| `Lista` | Colección ordenada que permite elementos duplicados | `ArrayList`, `LinkedList` | `ArrayList` respaldada por una matriz redimensionable, `LinkedList` usa una lista doblemente enlazada |
| `Establecer` | Colección que no permite elementos duplicados | `HashSet`, `LinkedHashSet`, `TreeSet` | `HashSet` usa una tabla hash, `LinkedHashSet` mantiene el orden de inserción, `TreeSet` usa un árbol rojo-negro para ordenar |
| `Deque` | Cola de doble extremo, permite inserción y extracción en ambos extremos | `ArrayDeque`, `LinkedList` | matriz redimensionable `ArrayDeque`, lista doblemente enlazada `LinkedList` |
| `Mapa` | Asigna claves únicas a valores | `HashMap`, `LinkedHashMap`, `TreeMap` | Tabla hash `HashMap`, `LinkedHashMap` mantiene el orden de inserción, árbol rojo-negro `TreeMap` para claves ordenadas |

### Tabla 2: Funcionalidad de las interfaces de las colecciones principales

| Interfaz | Realizar pedidos | Duplicados | Valores nulos |
|-------------|------------|------------|-------------|
| `Lista` | Ordenado | Permitido | Permitido |
| `Establecer` | Sin ordenar | No permitido| Permitido |
| `Deque` | Ordenado | Permitido | No permitido |

### Tabla 2.1: Funcionalidad de la interfaz del mapa

| Interfaz | Realizar pedidos | Claves duplicadas | Claves nulas | Valores nulos |
|-------------|------------|----------------|-----------|-------------|
| `Mapa` | Sin ordenar | No permitido | Permitido | Permitido |

### Tabla 3: Métodos comunes para cobranzas

| Interfaz | Método | Descripción |
|-----------|--------|-------------|
| `Colección` | `añadir(E e)` | Agrega un elemento a la colección |
| `Colección` | `addAll(Colección<? extiende E> c)` | Agrega todos los elementos de otra colección |
| `Colección` | `eliminar (Objeto o)` | Elimina un elemento especificado |
| `Colección` | `tamaño()` | Devuelve el número de elementos |
| `Colección` | `claro()` | Elimina todos los elementos |
| `Colección` | `contiene(Objeto o)` | Comprueba si la colección contiene un elemento específico |
| `Colección` | `removeIf(Predicado<? super E> filtro)` | Elimina todos los elementos que satisfacen un predicado |
| `Colección` | `forEach(Consumidor<? super E> acción)` | Realiza una acción para cada elemento |
| `Colección` | `es igual a (Objeto o)` | Comprueba si otro objeto es igual a la colección |

### Tabla 4: Métodos específicos de lista

| Método | Descripción |
|--------|-------------|
| `agregar (índice int, elemento E)` | Inserta un elemento en una posición especificada |
| `obtener(índice int)` | Devuelve el elemento en una posición especificada |
| `set(índice int, elemento E)` | Reemplaza el elemento en una posición especificada |
| `eliminar (índice int)` | Elimina el elemento en una posición especificada |
| `reemplazarTodo(OperadorUnario<E> operador)` | Reemplaza cada elemento con el resultado de una función |
| `sort(Comparador<? super E> c)` | Ordena la lista usando un comparador |
| `toArray(T[] a)` | Convierte la lista en una matriz |

### Tabla 5: Métodos específicos del conjunto

| Método | Descripción |
|--------|-------------|
| `añadir(E e)` | Agrega un elemento al conjunto si aún no está presente |
| `contiene(Objeto o)` | Comprueba si elconjunto contiene un elemento específico |
| `eliminar (Objeto o)` | Elimina un elemento especificado |
| `tamaño()` | Devuelve el número de elementos |
| `forEach(Consumidor<? super E> acción)` | Realiza una acción para cada elemento |

### Tabla 6: Métodos específicos de Deque

| Método | Descripción |
|--------|-------------|
| `añadirPrimero(E e)` | Inserta un elemento al principio del deque |
| `addLast(E e)` | Inserta un elemento al final del deque |
| `getFirst()` | Recupera, pero no elimina, la cabeza del deque |
| `getLast()` | Recupera, pero no elimina, la cola del deque |
| `eliminarPrimero()` | Elimina y devuelve la cabeza del deque |
| `removeLast()` | Elimina y devuelve la cola del deque |
| `empujar(E e)` | Inserta un elemento al principio del deque |
| `pop()` | Elimina y devuelve el elemento al principio del deque |

### Tabla 7: Métodos específicos de mapas

| Método | Descripción |
|--------|-------------|
| `claro()` | Elimina todas las entradas del mapa |
| `contieneClave(Clave de objeto)` | Comprueba si el mapa contiene una clave especificada |
| `contieneValor(Valor del objeto)` | Comprueba si el mapa contiene un valor especificado |
| `conjuntodeentrada()` | Devuelve una vista establecida de las entradas del mapa |
| `forEach(BiConsumer<? super K,? super V> acción)` | Realiza una acción para cada entrada |
| `obtener (clave de objeto)` | Devuelve el valor asociado con una clave especificada |
| `getOrDefault(Clave de objeto, V defaultValue)` | Devuelve el valor de una clave o un valor predeterminado si no se encuentra la clave.
| `está vacío()` | Comprueba si el mapa no contiene entradas |
| `keySet()` | Devuelve una vista establecida de las claves en el mapa |
| `merge(tecla K, valor V, BiFunction<? super V,? super V,? extiende V> remappingFunction)` | Fusiona el valor con un valor existente para la clave |
| `poner (tecla K, valor V)` | Asocia un valor a una clave |
| `putIfAbsent (tecla K, valor V)` | Asocia un valor con una clave si aún no está asociada |
| `eliminar (clave de objeto)` | Elimina la entrada de una clave |
| `reemplazar (tecla K, valor V)` | Reemplaza la entrada por una clave |
| `replaceAll(BiFunction<? super K,? super V,? extiende la función V>)` | Reemplaza cada valor con el resultado de una función |
| `tamaño()` | Devuelve el número de entradas |
| `valores()` | Devuelve una vista de colección de los valores en el mapa |


## Puntos clave
- Una matriz es un objeto que contiene un número fijo de valores de un solo tipo en ubicaciones de memoria contiguas.

- Para crear una matriz, declara una variable del tipo de matriz deseado y usa la palabra clave `new` para crear el objeto de matriz.

- Los elementos de la matriz se inicializan automáticamente con valores predeterminados (0 para tipos numéricos, "falso" para booleanos y "nulo" para tipos de referencia).

- Los índices de la matriz comienzan en 0. Acceder a un elemento fuera de los límites de la matriz dará como resultado una `ArrayIndexOutOfBoundsException`.

- El atributo "longitud" proporciona el número de elementos de una matriz. Es un atributo, no un método, por lo que no se utilizan paréntesis.

- Los arreglos multidimensionales son *arreglos de arreglos*. El tipo más común es la matriz bidimensional, que se utiliza a menudo para representar matrices o tablas de datos.

- Los arreglos anónimos se declaran e inicializan en una sola declaración sin asignarlos a una variable. A menudo se utilizan cuando se pasa una matriz como argumento a un método.

- La clase `java.util.Arrays` contiene varios métodos estáticos para manipular matrices, incluidos métodos para ordenar, buscar, comparar y llenar elementos de matrices.

- El método `Arrays.sort()` ordena los elementos de una matriz en orden ascendente. Tiene sobrecargas para diferentes tipos de matrices y puede ordenar una parte de una matriz.

- El método `Arrays.binarySearch()` busca un elemento específico en una matriz ordenada utilizando el algoritmo de búsqueda binaria. La matriz debe estar ordenada para que el método funcione correctamente.

- El método `Arrays.compare()` compara dos matrices lexicográficamente (por orden del diccionario).

- El método `Arrays.fill()` llena una matriz o una parte de ella con un valor específico. Establece que cada elemento haga referencia al mismo objeto para matrices de objetos.

- GRAMOLos enéricos son un mecanismo en Java que le permite escribir código que puede funcionar con diferentes tipos manteniendo la seguridad de los tipos.

- El borrado de tipos es el proceso en el que el compilador elimina toda la información de tipos genéricos en el momento de la compilación, reemplazándola con sus límites o el tipo "Objeto".

- Las clases genéricas se definen con uno o más parámetros de tipo entre paréntesis angulares después del nombre de la clase. Estos parámetros de tipo actúan como marcadores de posición para los tipos reales utilizados cuando se crea una instancia de la clase.

- Los métodos genéricos le permiten escribir código reutilizable que puede funcionar con diferentes tipos. Los parámetros de tipo se definen antes del tipo de retorno del método.

- Al invocar un método genérico, puede especificar explícitamente los argumentos de tipo o dejar que el compilador los infiera en función del contexto.

- Los constructores genéricos y los métodos de fábrica estáticos también pueden aceptar y devolver tipos genéricos.

- Al diseñar métodos con tipos de retorno genéricos, utilice nombres de parámetros de tipo descriptivo, garantice la compatibilidad con el uso previsto y considere el impacto en la complejidad del código.

- Las interfaces genéricas proporcionan una forma de especificar un contrato que las clases pueden implementar, lo que permite una mayor flexibilidad y reutilización.

- Los registros genéricos proporcionan una forma concisa de definir clases de datos inmutables que pueden funcionar con diferentes tipos.

- Los tipos comodín (`?`, `? extends T`, `? super T`) le permiten especificar tipos desconocidos, restringir parámetros de tipo a subtipos de un tipo o restringir parámetros de tipo a supertipos de un tipo, respectivamente.

- Java Collections Framework proporciona un conjunto de componentes reutilizables para gestionar grupos de objetos, incluidas las interfaces `List`, `Set`, `Deque` y `Map`.

- La interfaz `Comparable` define el orden natural de los elementos dentro de una clase, mientras que la interfaz `Comparator` le permite definir un orden personalizado para los elementos de una clase.

- El método `Collections.sort()` ordena una `Lista` usando su orden natural o un `Comparador` proporcionado, mientras que `Collections.binarySearch()` busca un elemento en una `Lista` ordenada usando el algoritmo de búsqueda binaria.


## Preguntas de práctica

**1. ¿Cuál es el resultado del siguiente programa?**```java
public class MultiDimArray {
    public static void main(String[] args) {
        int[][] arr = new int[2][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = i + j;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
```**A)**```
0 0 0 
0 0 0 
```**B)**```
0 1 2 
0 1 2 
```**DO)**```
0 0 0 
1 1 1 
```**D)**```
0 1 2 
1 2 3 
```**2. ¿Cuál de las siguientes definiciones de métodos genéricos declara correctamente un método que devuelve el primer elemento de una matriz determinada?**

**A)**```java
public static T getFirstElement(T[] array) {
    return array[0];
}
```**B)**```java
public static <T> T getFirstElement(T[] array) {
    return array[0];
}
```**DO)**```java
public static <T> getFirstElement(T[] array) {
    return array[0];
}
```**D)**```java
public static <T> T[] getFirstElement(T[] array) {
    return array[0];
}
```**3. ¿Cuál es el resultado de compilar y ejecutar el siguiente código?**```java
import java.util.*;

public class WildcardTest {
    public static void printList(List<? extends Number> list) {
        for (Number n : list) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        List<String> strings = Arrays.asList("one", "two", "three");
        
        printList(ints);
        printList(doubles);
        printList(strings);
    }
}
```**A)** El código compila e imprime:```
   1 2 3
   1.1 2.2 3.3
   one two three
   ```**B)** El código compila e imprime:```
   1 2 3
   1.1 2.2 3.3
   ```**C)** El código no se compila debido a un error en el método `printList`.  
**D)** El código no se compila debido a un error en el método `main`.  
**E)** El código se compila pero genera una excepción de tiempo de ejecución cuando se ejecuta.




**4. ¿Cuál es el resultado del siguiente programa?**```java
import java.util.*;

public class ListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        list.add(2, "E");
        System.out.println(list);
    }
}
```**A)** `[A, B, E, C, D]`  
**B)** `[A,E,B,C,D]`  
**C)** `[A, B, C, E, D]`  
**D)** `[A, B, C, D, E]`  
**E)** `[A, C, B, E, D]`


**5. ¿Cuáles de las siguientes afirmaciones sobre la interfaz `Set` son verdaderas? (Elija todas las que correspondan).**

**A)** Un `Conjunto` permite elementos duplicados.  
**B)** Los elementos de un `Conjunto` se mantienen en el orden en que se insertaron.  
**C)** La interfaz `Set` incluye métodos para agregar, eliminar y verificar la presencia de elementos.  
**D)** La interfaz `Set` se implementa mediante clases como `HashSet`, `LinkedHashSet` y `TreeSet`.  
**E)** Un `Set` garantiza un rendimiento en tiempo constante para las operaciones básicas (agregar, eliminar, contener).


**6. ¿Cuál será el resultado del siguiente programa?**```java
import java.util.*;

public class DequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("A");
        deque.addLast("B");
        deque.addFirst("C");
        deque.addLast("D");

        System.out.println(deque);
    }
}
```**A)** `[A, B, C, D]`  
**B)** `[C, B, A, D]`  
**C)** `[C, A, B, D]`  
**D)** `[D, B, A, C]`  
**E)** `[A, C, B, D]`


**7. ¿Cuál será el resultado del siguiente programa?**```java
import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(2, "D");

        System.out.println(map);
    }
}
```**A)** `{1=A, 2=B, 3=C, 2=D}`  
**B)** `{1=A, 2=B, 3=C}`  
**C)** `{1=A, 2=D, 3=C, 2=D}`  
**D)** `{1=A, 2=D, 3=C}`  
**E)** `{1=A, 3=C, 2=B}`


**8. ¿Cuál es el resultado de ejecutar el siguiente programa?**```java
import java.util.*;

public class ComparableExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));

        Collections.sort(people);

        for (Person p : people) {
            System.out.println(p.getName() + " " + p.getAge());
        }
    }
}

class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
}
```**A)**```
Alice 30  
Bob 25  
Charlie 35
```**B)**```
Charlie 35  
Alice 30  
Bob 25
```**DO)**```
Bob 25  
Alice 30  
Charlie 35
```**D)**```
Bob 25  
Charlie 35  
Alice 30
```**MI)**```
Alice 30  
Charlie 35  
Bob 25
```**9. ¿Cuál será el resultado del siguiente programa cuando se utilice el `Comparador` proporcionado?**```java
import java.util.*;

public class ComparatorExample {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));

        Collections.sort(people, new AgeComparator());

        for (Person p : people) {
            System.out.println(p.getName() + " " + p.getAge());
        }
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}
```**A)**```
Bob 25  
Alice 30  
Charlie 35
```**B)**```
Charlie 35  
Alice 30  
Bob 25
```**DO)**```
Alice 30  
Bob 25  
Charlie 35
```**D)**```
Bob 25  
Charlie 35  
Alice 30
```**MI)**```
Alice 30  
Charlie 35  
Bob 25
```


