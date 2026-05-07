---
layout: chapter

title: "Capítulo CUATRO"
subtitle: "Trabajar con datos"
exam_objectives:
  - "Utilice primitivas y clases contenedoras. Evalúe expresiones aritméticas y booleanas utilizando la API de matemáticas y aplicando reglas de precedencia, conversiones de tipos y conversión."
  - "Manipule texto, incluidos bloques de texto, utilizando las clases String y StringBuilder."

previous_link: "/ch03.html"
previous_title: "Trabajar con registros y enumeraciones"
next_link: "/ch05.html"
next_title: "Controlar el flujo del programa"
answers_link: "/ch04a.html"
-"None"## Contenido del capítulo

- [Comprensión de los tipos de datos](#comprensión-de-los-tipos-de-datos)
    - [Tipos primitivos](#tipos-primitivos)
    - [Tipos de referencia](#tipos-de-referencia)
- [Operadores](#operadores)
    - [Presentación de operadores](#presentación-operadores)
    - [Precedencia del operador](#precedencia del operador)
    - [Operadores unarios](#operadores-unarios)
    - [Operadores binarios](#operadores-binarios)
    - [Operadores bit a bit y de desplazamiento] (#operadores bit a bit y de desplazamiento)
    - [Operadores de asignación](#operadores-de-asignación)
    - [Operadores de igualdad](#operadores-de-igualdad)
    - [Operadores relacionales](#operadores-relacionales)
    - [Operadores lógicos](#operadores-lógicos)
- [`String` y `StringBuilder`](#string-and-stringbuilder)
    - [Creando cadenas](#creando-cadenas)
    - [Concatenación de cadenas](#concatenación de cadenas)
    - [Métodos de cadenas importantes](#métodos-de-cadenas-importantes)
    - [Anulando `toString()`](#overriding-tostring)
    - [Cadenas de formato] (#cadenas de formato)
    - [Usando la clase `StringBuilder`](#usando-la-clase-stringbuilder)
    - [Métodos `StringBuilder` importantes] (#métodos-stringbuilder-importantes)
- [Bloques de texto](#bloques de texto)
    - [Características de los bloques de texto](#características-de-bloques-de-texto)
- [La API de matemáticas](#the-math-api)
    - [Encontrar el mínimo y el máximo](#encontrar-el-mínimo-y-el-máximo)
    - [Redondear números](#redondear-números)
    - [Generando números aleatorios](#generando-números-aleatorios)
- [Puntos clave](#puntos clave)
- [Preguntas de práctica](#preguntas-de-practica)

---

## Comprender los tipos de datos

Hay dos tipos de datos principales, primitivos y de referencia:

```

┌───────────────────────────────────────────────────┐
│                  Java Data Types                  │
│                         │                         │
│          ┌──────────────┴──────────────┐          │
│          │                             │          │
│    Primitive Types               Reference Types  │
│          │                             │          │
│  ┌───────┴───────┐             ┌───────┴───────┐  │
│  │ byte          │             │ Classes       │  │
│  │ short         │             │ Interfaces    │  │
│  │ int           │             │ Arrays        │  │
│  │ long          │             │ Enums         │  │
│  │ float         │             └───────────────┘  │
│  │ double        │                                │
│  │ boolean       │                                │
│  │ char          │                                │
│  └───────────────┘                                │
└───────────────────────────────────────────────────┘

```

Repasemos cada uno con más detalle.

### Tipos primitivos
Java es un lenguaje de tipo estático, lo que significa que todas las variables deben declararse primero antes de poder usarse. El tipo de variable determina los valores que puede contener y qué operaciones se pueden realizar con ella.

En Java, los tipos primitivos son los tipos de datos más básicos disponibles. No son objetos y no pertenecen a ninguna clase. Más bien, están definidos por el propio lenguaje. Los tipos primitivos se utilizan para almacenar valores simples como números enteros, números de punto flotante, booleanos y caracteres.

Los tipos primitivos se almacenan directamente en la memoria y se accede a ellos por sus valores. Esto contrasta con los tipos de referencia (objetos), a los que se accede mediante su referencia. Debido a este almacenamiento directo, las primitivas son más rápidas y requieren menos memoria que los objetos.

Hay ocho tipos de datos primitivos en Java:

| Tipo |  Tamaño (bits) | Valor mínimo | Valor máximo | Predeterminado |
|-----------|---------------|----------------------|---------------------|------------------|
| `byte` | 8 | -128 | 127 | 0 |  
| `corto` | 16 | -32.768 | 32.767 | 0 |
| `int` | 32 | -2.147.483.648 | 2.147.483.647 | 0 |  
| `largo` | 64 | -9.223.372.036.854.775.808 | 9.223.372.036.854.775.807 | 0L |
| `flotar` | 32 | 1.4E-45 | 3.4028235E38 | 0.0f |
| `double` | 64 | 4.9E-324 | 1.7976931348623157E308 | 0,0d | 
| `boolean` | n/a | n/a | n/a | falso |
| `char` | 16 | '\u0000' (0) | '\uffff' (65,535) | '\u0000'|

Analicemos esto. 

Los tipos de números enteros (`byte`, `short`, `int`, `long`) son para valores de números enteros. Se diferencian por el rango de valores que pueden contener. Un "byte" tiene "8" bits y puede contener valores desde "-128" hasta "127". Un "corto" tiene "16" bits y puede contener valores desde "-32,768" hasta "32,767". Un "int" tiene "32" bits y puede contener valores desde "-2,147,483,648" hasta "2,147,483,647". Y un "largo" tiene 64 bits y contiene valores desde "-9,223,372,036,854,775,808" hasta "9,223,372,036,854,775,807".

Quizás se pregunte: ¿no se tratan todos los números en Java de la misma manera? ¿Por qué necesitamos diferentes tipos como "int", "long", etc.? La razón es la eficiencia y el uso de memoria. Si sabe que sus valores siempre estarán dentro de un rango determinado, puede utilizar un tipo más pequeño para ahorrar memoria. Por ejemplo, un "int" ocupa la mitad de la memoria que un "largo". En aplicaciones a gran escala con muchos datos, esto puede marcar una gran diferencia.

Los tipos de punto flotante ("flotante" y "doble") son para números con puntos decimales. Un "flotante" es de 32 bits y un "doble" es de 64 bits. Esto le da a "doble" mucha más precisión que a "flotante". Muchos desarrolladores optan por utilizar "doble" para todos los valores decimales para evitar problemas de precisión, pero hay escenarios en los que se puede utilizar "flotante" para conservar memoria si no se necesita una alta precisión.

En Java, el tamaño de un booleano no está definido explícitamente en la Especificación del lenguaje Java. El tamaño de memoria real de una variable booleana depende de la implementación y varía según la máquina virtual Java que se utilice. Sin embargo, para el examen, sólo necesitas saber que el tipo "booleano" tiene sólo dos valores posibles: "verdadero" y "falso", y que se utiliza para la lógica condicional.

El tipo `char` es para caracteres individuales. Utiliza 16 bits porque utiliza codificación Unicode, lo que le permite representar una amplia variedad de caracteres de diferentes idiomas.

Algo importante que hay que recordar es que los valores predeterminados sólo se aplican a los campos. Las variables locales, por otro lado, deben inicializarse explícitamente antes de su uso; de lo contrario, su código no se compilará.

Puede asignar valores a variables usando literales. Además de la notación decimal estándar, Java le permite asignar números enteros.literales que utilizan notación hexadecimal (prefijo `0x` o `0X`), octal (prefijo `0`) y binaria (prefijo `0b` o `0B`).

He aquí un ejemplo:

```java
// Decimal notation
int decimalNum = 42;

// Hexadecimal notation
int hexNum = 0x2A; // Equivalent to decimal 42
        
// Octal notation
int octalNum = 052; // Equivalent to decimal 42
        
// Binary notation
int binaryNum = 0b101010; // Equivalent to decimal 42

```

Al asignar literales a variables, es importante tener en cuenta que el tipo de literal debe coincidir con el tipo de variable. Si no coinciden, es posible que necesites utilizar un sufijo para especificar el tipo de literal.

Para literales enteros:
- Los literales `long` usan el sufijo `L` o `l`: `long longNum = 1000L;`
- Los literales `int` no requieren un sufijo, ya que `int` es el valor predeterminado para los números enteros: `int intNum = 1000;`

Para literales de punto flotante:
- Los literales `float` usan el sufijo `F` o `f`: `float floatNum = 3.14f;`
- Los literales `dobles` usan el sufijo `D` o `d`, aunque este sufijo es opcional ya que doble es el tipo predeterminado para los literales decimales: `double doubleNum = 3.14D;`

A continuación se muestran algunos ejemplos:

```java
long longNum = 1000L; // Suffix L is required
float floatNum = 3.14f; // Suffix f is required
double doubleNum1 = 3.14; // Suffix d is optional
double doubleNum2 = 3.14d; // Suffix d is optional

```

Si no utiliza el sufijo correcto, es posible que encuentre un error de compilación. Por ejemplo:

```java
byte longNum = 1000; // Compilation error: integer literal is too large
float floatNum = 3.14; // Compilation error: incompatible types

```

En estos casos, Java supone que el literal es de tipo `int` o `double` respectivamente, que no se puede asignar directamente a una variable `byte` o `float` sin una conversión explícita.

También puede utilizar guiones bajos en literales numéricos para mejorar la legibilidad, como `1_000_000`. A continuación se muestran algunos ejemplos que ilustran el uso de guiones bajos en literales numéricos:

```java
// Valid use of underscores
int million = 1_000_000;
long creditCardNumber = 1234_5678_9012_3456L;
float pi = 3.14_15F;
double avogadro = 6.022_140_857e23;

```

Sin embargo, existen algunas restricciones. No puedes colocar un guión bajo:
- Al principio o al final de un número.
- Adyacente a un punto decimal en un literal de punto flotante
- Antes de un sufijo "F" o "L"
- En posiciones donde se espera una cadena de dígitos

A continuación se muestran algunos ejemplos adicionales de colocaciones de guiones bajos no válidas:

```java
// Invalid use of underscores
int x1 = _1000; // Compilation error: illegal underscore
int x2 = 1000_; // Compilation error: illegal underscore
float y1 = 3_.14F; // Compilation error: illegal underscore
float y2 = 3._14F; // Compilation error: illegal underscore

float y3 = 3.14__F; // Compilation error: consecutive underscores
long z1 = 1000_L; // Compilation error: underscore before L suffix

int x3 = 0_x42; // Compilation error: underscore in position where digits are expected
int x4 = 0b_101010; // Compilation error: underscore in position where digits are expected

```

Estas reglas existen para evitar ambigüedades y garantizar que el uso de guiones bajos no entre en conflicto con otras partes de la sintaxis del lenguaje.

### Tipos de referencia
En la sección anterior, exploramos el concepto de tipos primitivos en Java. Sin embargo, como sabemos, Java es un lenguaje orientado a objetos y casi todo se trata como un objeto. Mientras que las primitivas proporcionan los componentes básicos, los tipos de referencia nos permiten trabajar con objetos y aprovechar al máximo las características orientadas a objetos de Java.

Entonces, ¿qué son exactamente los tipos de referencia? A diferencia de las primitivas, que mantienen sus valores directamente, los tipos de referencia almacenan la dirección de memoria donde reside el objeto real. En otras palabras, una variable de referencia *se refiere* a la ubicación del objeto en lugar de contener el objeto en sí.

```java
String myString = "Hello"; // Reference type

```

En este ejemplo, "myString" es una variable de referencia de tipo "String". No contiene el valor real de la cadena, sino más bien una referencia a la ubicación de la memoria donde está almacenado el objeto "Hola".

Esto contrasta con cómo funcionan los tipos primitivos:

```java
int myNumber = 42; // Primitive type

```

Aquí, "myNumber" contiene directamente el valor entero "42" en lugar de hacer referencia a un objeto.

Pero ¿qué pasa si queremos tratar a los primitivos como objetos? Aquí es donde entran en juego las clases contenedoras. Java proporciona un conjunto de clases contenedoras que corresponden a cada tipo primitivo, lo que permite su uso en escenarios que requieren objetos:

|  Tipo primitivo |  Clase de envoltura | Hereda del número |
|------------------|-----------------|----------------------|
| `boolean` | `Booleano` | No |
| `byte` | `Byte` | Sí |
| `corto` | `Corto` | Sí |
| `int` | `Entero` | Sí |
| `largo` | `Largo` | Sí |
| `flotar` | `Flotador` | Sí |
| `double` | `Doble` | Sí |
| `char` | `Carácter` | No |

Cada tipo primitivo tiene una clase contenedora correspondiente, la mayoría de las cuales heredan de la clase "Número". Las clases "Boolean" y "Character" son excepciones, ya que no representan valores numéricos.

Las clases contenedoras proporcionan métodos para crear instancias a partir de muchas representaciones y para convertir entre diferentes tipos de datos. A continuación se muestran algunos ejemplos:

1. Métodos de análisis:
   - `Integer.parseInt(String s)`: analiza un argumento de cadena como un entero decimal con signo.
   - `Double.parseDouble(String s)`: analiza un argumento de cadena como un número de punto flotante de doble precisión.
   - `Boolean.parseBoolean(String s)`: analiza un argumento de cadena como un valor booleano.

   Por ejemplo:

```java
   int num = Integer.parseInt("42");
   double value = Double.parseDouble("3.14");
   boolean flag = Boolean.parseBoolean("true");

```

2. Métodos de conversión:
   - `Integer.valueOf(String s)`: Devuelve un objeto `Integer` que contiene el valor de la representación de cadena especificada.
   - `Long.valueOf(long l)`: Devuelve un objeto `Long` que contiene el valor largo primitivo especificado.
   - `Double.valueOf(double d)`: Devuelve un objeto `Double` que contiene el valor doble primitivo especificado.

   Por ejemplo:

```java
   Integer myInt = Integer.valueOf("100");
   Long myLong = Long.valueOf(1234567890L);
   Double myDouble = Double.valueOf(2.71828);

```

3. Conversión entre tipos numéricos:
   - `Integer.byteValue()`: Devuelve el valor de un `Entero` como un byte.
   - `Long.intValue()`: Devuelve el valor de un `Long` como un int.
   - `Float.doubleValue()`: Devuelve el valor de un `Float` como doble.

   Por ejemplo:

```java
   byte myByte = myInt.byteValue();
   long myLong = myInt.longValue();

```

4. Métodos de carácter:
   - `Character.isDigit(char ch)`: Determina si el carácter especificado es un dígito.
   - `Character.isLetter(char ch)`: Determina si el carácter especificado es una letra.
   - `Character.toUpperCase(char ch)`: Convierte el argumento del carácter a mayúsculas.

   Por ejemplo:

```java
   char myChar = '7';
   boolean isDigit = Character.isDigit(myChar);
   boolean isLetter = Character.isLetter(myChar);
   char upperCase = Character.toUpperCase(myChar);

```

Tenga en cuenta que estos métodos son "estáticos", lo que le permite usarlos sin crear una instancia de la clase contenedora.

Estos son sólo algunos ejemplos de los métodos proporcionados por las clases contenedoras para crear instancias y convertir entre diferentes representaciones. Cada clase contenedora ofrece una variedad de métodos específicos para su tipo primitivo correspondiente, lo que brinda flexibilidad y conveniencia al trabajar con diferentes formatos de datos y conversiones.

Entonces, ¿las clases contenedoras crean objetos primitivos? No exactamente. Las clases contenedoras están separadas de las primitivas, pero proporcionan una forma de envolver las primitivas en forma de objeto. Esto permite usar primitivas en contextos que esperan objetos, como colecciones o cuando se usan genéricos.

Para cerrar la brecha entre las primitivas y sus clases contenedoras, Java introdujo el autoboxing y unboxing. Como se mencionó anteriormente, el autoboxing es la conversión automática de un tipo primitivo a su clase contenedora correspondiente, mientras que el unboxing es el proceso inverso:

```java
int num = 42;
Integer objNum = num; // Autoboxing
int num2 = objNum;    // Unboxing

```

En este ejemplo, `num` se encuadra automáticamente en un objeto `Integer` cuando se asigna a `objNum`. De manera similar, `objNum` se descomprime nuevamente a `int` cuando se asigna a `num2`. Esto sucede implícitamente, lo que hace conveniente cambiar entre primitivas y sus clases contenedoras.

El autoboxing y unboxing funcionan con todos los tipos primitivos y sus correspondientes clases contenedoras, no solo con `int` e `Integer`. Java maneja estas conversiones automáticamente según el contexto en el que se utilizan.

Es importante tener en cuenta que, si bien el autoboxing y unboxing simplifican la legibilidad del código, podrían tener algunas implicaciones en el rendimiento. Cada conversión entre una primitiva y su clase contenedora implica crear o descartar un objeto, lo que añade una pequeña sobrecarga. En la mayoría de los casos, esta sobrecarga es insignificante, pero puede acumularse en escenarios sensibles al rendimiento con operaciones frecuentes de autoboxing/unboxing.

Una ventaja de las clases contenedoras es su capacidad para representar la ausencia de un valor utilizando "nulo". Si bien las primitivas no pueden ser "nulas", los objetos contenedores sí pueden.

```java
Integer num = null;
int value = num; // NullPointerException

```

Aquí, asignar "null" a "num" es válido porque es un objeto "Entero". Sin embargo, intentar desempaquetar `num` a un `int` genera una `NullPointerException`. Este comportamiento permite un manejo más explícito de valores "nulos" y puede resultar útil en escenarios en los que es posible que una variable no tenga un valor asignado.

Además, vale la pena señalar que las clases contenedoras son inmutables, lo que significa que sus valores no se pueden cambiar una vez asignados. Cuando realiza operaciones en un objeto contenedor, se crea un nuevo objeto con el valor actualizado en lugar de modificar el objeto existente.

```java
Integer num = 42;
num++;

```

En este ejemplo, la operación `++` en `num` crea un nuevo objeto `Integer` con el valor 43 en lugar de modificar el objeto original. Este comportamiento garantiza la seguridad de los subprocesos y evita efectos secundarios inesperados al compartir objetos contenedores en varias partes del programa.

## Operadores

### Presentación de operadores
Un operador es un símbolo que le indica al compilador que realice operaciones matemáticas o lógicas específicas. 

Java proporciona un rico conjunto de operadores para realizar operaciones con variables y valores:
- Operadores unarios: operadores que actúan sobre un único operando, como `++` para incrementar un valor o `!` para negar un valor booleano.
- Operadores binarios: Operadores que actúan sobre dos operandos, como operadores aritméticos (`+`, `-`, `*`, `/`, `%`) y operadores de comparación (`>`, `<`, `>=`, `<=`, `==`, `!=`). 
- Operador ternario: el operador condicional que toma tres operandos (`condición? valor_si_verdadero: valor_si_falso`).
- Operadores de asignación: Operadores utilizados para asignar valores a variables (`=`, `+=`, `-=`, `*=`, `/=`, `%=`, `&=`, `^=`, `|=`, `<<=`, `>>=`, `>>>=`).
- Operadores lógicos: Operadores utilizados para determinar la lógica entre variables o valores (`&&`, `||`).

Muchos desarrolladores nuevos en Java creen erróneamente que los operadores son sólo para operaciones matemáticas. Pero los operadores también desempeñan un papel importante en el control del flujo del programa, la realización de operaciones lógicas, la manipulación de bits y más. Por ejemplo:

```java
int a = 10;
int b = 5;
        
// Arithmetic operator
System.out.println(a + b);  // 15 

// Comparison operator  
System.out.println(a > b);  // true

// Logical operator
System.out.println((a > b) && (a != b)); // true

```

Como puede ver, los operadores en Java van mucho más allá de la aritmética básica.

### Precedencia del operador

Un concepto importante que los operadores deben comprender es el de precedencia. Al igual que en matemáticas, algunos operadores en Java tienen mayor prioridad que otros, lo que significa que se evalúan primero en una expresión.

Por ejemplo, considere este código:

```java
int result = 10 + 5 * 2;
System.out.println(result);

```

Es posible que espere que el "resultado" sea "30" ("10 + 5" es "15", luego "15 * 2"). Pero en realidad, imprime "20". Esto se debe a que el operador `*` tiene mayor prioridad que `+`. Entonces, primero se evalúa "5 * 2", lo que da "10", y luego se agrega "10" al "10" original.

Aquí hay una tabla que muestra la precedencia de los operadores en Java, de mayor a menor:

| Categoría | Operator | Asociatividad |
|------------------|-------------------------------------------------|------------------------|
| Sufijo | `expr++` `expr--` | De izquierda a derecha |
| Unario | `++expr` `--expr` `+expr` `-expr` `~` `!` | De derecha a izquierda |
| Multiplicativo | `*` `/` `%` | De izquierda a derecha |
| Aditivo | `+` `-` | De izquierda a derecha |
| Cambio | `<<` `>>` `>>>` | De izquierda a derecha |
| Relacional | `<` `>` `<=` `>=` `instancia de` | De izquierda a derecha |
| Igualdad | `==` `!=` | De izquierda a derecha |
| Bit a bit Y | `&` | De izquierda a derecha |
| XOR bit a bit | `^` | De izquierda a derecha |
| Bit a bit O | `|` | De izquierda a derecha | 
| Y lógico | `&&` | De izquierda a derecha |
| O lógico | `||` | De izquierda a derecha |
| Ternario | `? :` | De derecha a izquierda |
| Tarea | `=` `+=` `-=` `*=` `/=` `%=` `&=` `^=` `|=` `<<=` `>>=` `>>>=` | De derecha a izquierda |

Como muestra la tabla, la mayoría de los operadores evalúan de izquierda a derecha. Entonces, en una expresión como `a + b - c`, `a + b` ocurre primero, luego `- c`.

Pero los operadores de asignación y los operadores unarios en realidad evalúan de derecha a izquierda. Considere este código:

```java
int a = 10;
int b = 20;
int c = (a = 3) + (b = 5);
System.out.println(a + ", " + b + ", " + c); // 3, 5, 8

```

Aquí, a "a" se le asigna "3", a "b" se le asigna "5" y, debido a que la asignación se evalúa de derecha a izquierda, las asignaciones se realizan antes de la suma. Entonces `c` termina como `8` (`3 + 5`), mientras que `a` es `3` y `b` es `5`.

Esta evaluación de derecha a izquierda permite asignaciones encadenadas, como `a = b = c = 5`. El "5" se asigna a "c", luego ese resultado se asigna a "b" y finalmente a "a", de derecha a izquierda.

Hay mucho que recordar y no es necesario memorizar toda la tabla de precedencia. Los puntos clave son:
1. Las operaciones de sufijo como `x++` ocurren antes que las de prefijo como `++x`. 
2. Las operaciones multiplicativas (`*`, `/`, `%`) ocurren antes que las aditivas (`+`, `-`).
3. Las operaciones bit a bit (`&`, `|`, `^`) ocurren después de las comparaciones (`>`, `==`, etc.) pero antes de las lógicas (`&&`, `||`).
4. Las tareas se evalúan al final y de derecha a izquierda.

En caso de duda, siempre se pueden utilizar paréntesis para hacer explícito el orden. Las expresiones `(a + b) * c` y `a + (b * c)` son inequívocamente diferentes.

En general, se deben utilizar paréntesis siempre que la precedencia no esté clara o para mejorar la legibilidad. Pero no se deben abusar de ellos hasta el punto de generar desorden. Con un conocimiento sólido de la precedencia de los operadores, muchos paréntesis se vuelven innecesarios, lo que genera un código más limpio y legible.

Veamos algunos ejemplos más para que pueda comprender mejor los operadores y la precedencia en Java:

```java
int x = 10;
int y = 20;
int z = 30;

System.out.println(x + y - z);  // 10 + 20 - 30 = 0
System.out.println(x - y + z);  // 10 - 20 + 30 = 20
System.out.println(x * y / z);  // 10 * 20 / 30 = 6
System.out.println(x / y * z);  // 10 / 20 * 30 = 0

```

En las dos primeras declaraciones, los operadores tienen la misma precedencia (`+` y `-`), por lo que se evalúan de izquierda a derecha. En las declaraciones tercera y cuarta, `*` y `/` tienen mayor prioridad y se evalúan primero, de izquierda a derecha, antes de agregar los resultados. En el cuarto ejemplo, "10/20" es cero (porque estamos usando números enteros).

Ahora mezclemos algunas asignaciones y operadores unarios:

```java
int a = 5;
int b = 10;
int c = ++a * b--;
System.out.println(a + ", " + b + ", " + c);  // 6, 9, 60

```

Aquí, `++a` incrementa `a` a 6 antes de la multiplicación. Entonces "6 * 10" da 60, que se asigna a "c". Finalmente, `b--` disminuye `b` a 9, pero después de la multiplicación. Entonces terminamos con "a" como 6, "b" como 9 y "c" como 60.

Es fundamental comprender la naturaleza de derecha a izquierda de las asignaciones:

```java
int x = 2;
int y = 3;
int z = 1;
x += y -= z;
System.out.println(x + ", " + y + ", " + z);  // 4, 2, 1

```

Primero, se resta `z` (1) de `y` (3), lo que da 2, que luego se asigna nuevamente a `y`. Luego, este valor (2) se suma a `x` (2), dando 4, que se asigna nuevamente a `x`. Entonces "x" termina siendo 4, "y" como 2 y "z" permanece 1.

Los operadores lógicos y bit a bit pueden agregar mayor complejidad:

```java
int a = 10;  // 1010 in binary
int b = 6;   // 0110 in binary

System.out.println(a & b);  // 1010 & 0110 = 0010 (2 in decimal)
System.out.println(a | b);  // 1010 | 0110 = 1110 (14 in decimal)
System.out.println(a ^ b);  // 1010 ^ 0110 = 1100 (12 in decimal)

System.out.println(a > 5 && b < 10); // true && true = true
System.out.println(a > 5 || b < 5);  // true || false = true

```

Los operadores bit a bit `&`, `|` y `^` realizan operaciones AND, OR y XOR en cada bit de los números. Los operadores lógicos `&&` y `||` realizan AND y OR en condiciones booleanas, teniendo `&&` mayor prioridad.

Por último, no olvidemos el operador ternario, que es como una declaración compacta `if`-`else`:

```java
int x = 10;
int y = 20;
int max = (x > y) ? x : y;
System.out.println(max);  // 20

```

Aquí, `(x > y)` es falso, por lo que el valor después de los dos puntos (`y`, que es 20) se asigna a `max`.

En las siguientes secciones, revisaremos cada tipo de operadores con más detalle.


### Operadores unarios
Los operadores unarios son operadores que trabajan con un solo operando. Ya has visto algunos operadores unarios en acción, como el operador de complemento lógico (`!`) usado con valores booleanos. Sin embargo, en esta sección cubriremos los operadores unarios que se usan principalmente con tipos numéricos.

#### Operadores de complemento y negación
El operador de complemento unario (`~`), también conocido como operador de complemento bit a bit, invierte todos los bits de un número, cambiando efectivamente cada 0 a 1 y cada 1 a 0. En Java, los números enteros se representan usando 32 bits en formato de complemento a dos.

Para números positivos, el complemento bit a bit invertirá todos los bits y el número resultante es la negación del número original menos uno. Esto se debe a que invertir todos los bits y luego interpretar el resultado en complemento a dos produce "-(n + 1)".

Así es como funciona:

Para un número positivo como 5, la representación binaria es:

```

0000 0000 0000 0000 0000 0000 0000 0101

```

Cuando aplica el operador de complemento bit a bit, invierte todos los bits:

```

1111 1111 1111 1111 1111 1111 1111 1010

```

En complemento a dos, esta es la representación de "-6". Por lo tanto, "~5" en Java es igual a "-6".

Para números negativos, el operador de complemento bit a bit también invierte todos los bits. El resultado es la versión positiva del número original menos uno, porque al invertir todos los bits de un número negativo e interpretarlo en complemento a dos se obtiene la contraparte positiva disminuida en 1.

Por ejemplo, tomemos -5. En complemento a dos, se representa como:

```

1111 1111 1111 1111 1111 1111 1111 1011

```

Aplicando el operador complemento bit a bit:

```

0000 0000 0000 0000 0000 0000 0000 0100

```

Este número binario representa 4 en decimal. Entonces, `~(-5)` es igual a 4.

En resumen:
- `~n` para un número positivo `n` da como resultado `-(n + 1)`.
- `~(-n)` da como resultado `n - 1`.

Por otro lado, el operador de negación unario (`-`) es más sencillo de entender. Este operador se utiliza para negar un valor numérico, cambiando efectivamente su signo. Por ejemplo, si "x" es 5, entonces "-x" sería -5.

Un error común es creer que el operador de negación es lo mismo que restar un número a cero. Si bien el resultado final puede ser el mismo, el operador de negación funciona de manera diferente bajo el capó. Cambia directamente el bit de signo del número, en lugar de realizar una operación de resta.

#### Operadores de incremento y decremento
Java también proporciona operadores de incremento (`++`) y decremento (`--`), que se utilizan para incrementar o disminuir el valor de una variable en 1. Estos operadores se pueden utilizar en forma de prefijo o postfijo.

Aquí hay una tabla que resume los diferentes operadores de incremento y decremento:

| Operator | Name | Descripción | Example |
|----------|---------------------|--------------------------------------------------------------|----------|
| `++x` | Operador de incremento de prefijo | Incrementa `x` en 1, luego devuelve el nuevo valor de `x` | `++x` |
| `x++` | Operador de incremento de sufijo| Devuelve el valor actual de `x`, luego incrementa `x` en 1 | `x++` | 
| `--x` | Operador de decremento de prefijo | Disminuye `x` en 1, luego devuelve el nuevo valor de `x` | `--x` |
| `x--` | Operador de decremento de sufijo| Devuelve el valor actual de `x`, luego disminuye `x` en 1 | `x--` |

Una pregunta es si se pueden utilizar operadores de incremento y decremento con valores booleanos. La respuesta es no. Estos operadores solo son aplicables a tipos numéricos como "int", "long", "float", "double", etc.

Otro punto de confusión es si `x++` incrementa `x` antes o después de la expresión en la que se usa. El operador de incremento de sufijo (`x++`) devuelve el valor original de `x` y luego incrementa `x` después de que se devuelve ese valor. Entonces, si tiene una expresión como `y = x++;`, a y se le asignará el valor original de `x` y luego se incrementará `x`.

Por otro lado, si usa `--x`, disminuye el valor de `x` antes de que se evalúe la expresión. Entonces `y = --x;` primero disminuiría `x` y luego asignaría el nuevo valor de `x` a `y`.

Además, quizás te preguntes si hay una diferencia entre `++x` y `x++` si son las únicas operaciones en una declaración. En este caso no hay diferencia. Ambos incrementarán `x` en 1. La diferencia solo entra en juego cuando la operación de incremento es parte de una expresión más grande.

#### Resumen de operadores unarios
Aquí hay una tabla completa de los operadores unarios en Java:

| Operator | Name | Descripción | Example |
|----------|---------------------|--------------------------------------------------------------|------------------------|
| `+` | Unario más | Indica un valor positivo (rara vez se usa) | `+x` |
| `-` | Menos unario | Niega un valor | `-x` |
| `++` | Incremento | Incrementa un valor en 1 | `++x (prefijo) x++ (posfijo)`|
| `--` | Decremento | Disminuye un valor en 1 | `--x (prefijo) x-- (posfijo)`|
| `~` | Complemento bit a bit | Invierte todos los bits | `~x` |

Tenga en cuenta que el operador complemento (`~`) solo funciona en tipos enteros, no en `float` o `double`.

Algunos puntos más matizados a considerar:

- Cuando se utiliza `++x` y `x++`, el orden de las operaciones y los efectos secundarios entran en juego. Considere tsu ejemplo:

```java 
  int x = 5;
  int y = ++x + x++; // y = 12, x = 7

```

Esto es lo que está pasando:
  1. Primero se aplica el operador de incremento de prefijo (`++x`). Esto incrementa `x` a 6, y el valor de la expresión `++x` es 6. 
  2. Luego, se aplica el operador de incremento de sufijo (`x++`). Esto devuelve el valor actual de "x", que es 6, y luego incrementa "x" a 7. 
  3. El valor de `y` es la suma de la expresión de incremento de prefijo (6) y la expresión de incremento de postfijo (6), que es 12. 
  4. Después de ejecutar la declaración, "x" es 7 (debido al incremento del sufijo) e "y" es 12.

- Es posible utilizar operadores de incremento y decremento dentro de una expresión compleja sin afectar el resultado, pero puede hacer que el código sea mucho más difícil de leer y comprender. Por ejemplo:

```java
  int x = 5;
  int y = 3 * x++ + 2; // y = 17, x = 6

```

Esto funciona, pero es más claro realizar la operación de incremento en una línea separada antes de la expresión.

- Los operadores de incremento y decremento no se pueden utilizar con valores booleanos porque los valores booleanos sólo pueden ser "verdaderos" o "falso". No tienen un valor "siguiente" o "anterior" como los números.

- Si utiliza múltiples operadores de incremento o decremento en la misma variable en una sola declaración, el orden de las operaciones es de izquierda a derecha:

```java
  int x = 5;
  System.out.println(++x + x++ + x--); // Output: 19

```

Esto es lo que está pasando:
  1. `++x` incrementa `x` a 6 y devuelve 6
  2. `x++` devuelve 6 (el valor actual de `x`), luego incrementa `x` a 7
  3. `x--` devuelve 7 (el valor actual de `x`), luego disminuye `x` a 6
  4. La suma es 6 + 6 + 7 = 19

- El operador de complemento (`~`) es útil para tareas de manipulación de bits de bajo nivel, utilizado a menudo en programación de sistemas, sistemas integrados, protocolos de red, criptografía y más.

- El compilador de Java reconoce los operadores de incremento y decremento y genera el código de bytes apropiado en función de si el operador se utiliza como prefijo o postfijo. No es algo de lo que debas preocuparte como programador, pero se maneja a nivel de código de bytes.

- Los operadores de incremento y decremento se pueden utilizar en variables de tipo float y double. Se aplican las mismas reglas de prefijo/posfijo que con los tipos de números enteros.

- Si usa `++x` versus `x++` dentro de un bucle, la diferencia en el valor final de `x` después del bucle depende de cuándo ocurre el incremento. Considerar:

```java
  int x = 0;
  for(int i = 0; i < 5; i++) {
      System.out.println(++x);
  }
  // Output: 1 2 3 4 5
  // x is 5 after the loop

  x = 0;
  for(int i = 0; i < 5; i++) {
      System.out.println(x++);
  }
  // Output: 0 1 2 3 4
  // x is 5 after the loop

```

En ambos casos, `x` termina siendo 5, pero cuando se imprimen los valores es diferente. Con `++x`, x se incrementa antes de imprimir su valor, mientras que con `x++`, el valor original de `x` se imprime antes de incrementarse.


### Operadores binarios
Los operadores binarios son operadores que trabajan con dos operandos. Java proporciona un conjunto de operadores aritméticos binarios para realizar operaciones matemáticas básicas con operandos numéricos. Estos operadores incluyen suma (`+`), resta (`-`), multiplicación (`*`), división (`/`) y módulo (`%`).

#### Operadores aritméticos
Los operadores de suma (`+`), resta (`-`) y multiplicación (`*`) funcionan como es de esperar:

```java
int a = 10;
int b = 20;
int sum = a + b; // 30
int difference = b - a; // 10
int product = a * b; // 200

```

El operador de división (`/`) realiza la división entre dos operandos numéricos. Es importante tener en cuenta que cuando se usa con operandos enteros, el operador de división realiza una división entera, lo que significa que devuelve el cociente y descarta cualquier resto.

```java
int a = 10;
int b = 3;
int quotient = a / b; // 3

```

Si desea realizar una división de punto flotante y obtener un resultado fraccionario, al menos uno de los operandos debe ser del tipo punto flotante ("flotante" o "doble").

```java
int a = 10;
double b = 3.0;
double quotient = a / b; // 3.3333333333333335

```

El operador de módulo (`%`) devuelve el resto después de realizar la división de enteros.

```java
int a = 10;
int b = 3;
int remainder = a % b; // 1

```

#### Promoción numérica
Al realizar operaciones aritméticas con operandos de diferentes tipos, Java promueve automáticamente los operandos de acuerdo con un conjunto de reglas conocidas como promoción numérica.

La promoción numérica es la conversión automática de un tipo numérico más pequeño a un tipo numérico más grande para evitar la pérdida de precisión durante las operaciones aritméticas. Esto le permite realizar operaciones aritméticas en tipos mixtos sin tener que convertir explícitamente los operandos.

Java sigue estas reglas para la promoción numérica:

1. Si alguno de los operandos es de tipo "doble", el otro se promociona a "doble".
2. De lo contrario, si alguno de los operandos es de tipo "flotante", el otro se promociona a "flotante".
3. De lo contrario, si alguno de los operandos es de tipo "largo", el otro se promociona a "largo".
4. De lo contrario, ambos operandos se promocionan a "int".

A continuación se muestran algunos ejemplos:

```java
int a = 10;
double b = 20.0;
double result1 = a + b; // a is promoted to double

float c = 10.0f;
long d = 20L;
float result2 = c + d; // d is promoted to float

short e = 10;
short f = 20;
int result3 = e + f; // e and f are promoted to int

```

#### Agregar paréntesis para cambiar el orden de operación
Puede utilizar paréntesis para cambiar el orden predeterminado de las operaciones en una expresión aritmética. Las expresiones entre paréntesis se evalúan primero.

```java
int a = 10;
int b = 20;
int c = 30;
int result = a + b * c; // 610 (multiplication happens first)
int result2 = (a + b) * c; // 900 (addition happens first)

```

Sin embargo, al utilizar paréntesis, es importante asegurarse de que estén correctamente equilibrados. Cada paréntesis de apertura debe tener un paréntesis de cierre correspondiente. Los paréntesis que no coinciden resultarán en un error de compilación.

```java
int result = (a + b) * c; // correct
int result2 = (a + b * c; // compilation error (mismatched parentheses)

```

#### Resumen de operadores binarios
Aquí hay una tabla que resume los operadores binarios en Java:

| Operator | Name | Descripción | Example |
|------------|----------------|--------------------------------------------------|--------------|
| `+` | Adición | Agrega dos operandos | `a+b` |
| `-` | Resta | Resta el segundo operando del primero | `a-b` | 
| `*` | Multiplicación | Multiplica dos operandos | `a * b` |
| `/` | División | Divide el primer operando por el segundo | `a/b` |
| `%` | Módulo | Devuelve el resto de la división | `a %b` |

Algunos puntos matizados a considerar son:

- Cuando se trabaja con números de coma flotante ("flotante" y "doble"), es importante recordar que tienen una precisión limitada. Esto puede provocar pequeñas imprecisiones en los cálculos.

```java
   double a = 0.1;
   double b = 0.2;
   double sum = a + b; // 0.30000000000000004 (not exactly 0.3)

```

Esto se debe a cómo se representan los números de punto flotante en binario. Para cálculos decimales precisos, debes utilizar la clase `BigDecimal`.

- Cuando el resultado de una operación aritmética excede el valor máximo o mínimo que puede ser representado por el tipo de objetivo, se puede producir un desbordamiento o un desbordamiento insuficiente. En Java, el desbordamiento y el subdesbordamiento de enteros no generan una excepción; el valor simplemente cambia.

```java
   int a = Integer.MAX_VALUE;
   int b = 1;
   int sum = a + b; // -2147483648 (minimum int value)

```

Para los tipos de punto flotante, el desbordamiento resulta en Infinity y el desbordamiento insuficiente resulta en 0.

- Intentar dividir un número entero entre cero arrojará una `ArithmeticException`.

```java
   int a = 10;
   int b = 0;
   int result = a / b; // throws ArithmeticException

```

Sin embargo, dividir un número de punto flotante por cero no genera una excepción. Resulta en "Infinito" o "NaN" (No es un número).

```java
   double a = 10.0;
   double b = 0.0;
   double result = a / b; // Infinity or NaN (Not-a-Number)

```

### Operadores bit a bit y de desplazamiento
Además de los operadores aritméticos, Java proporciona un conjunto de operadores bit a bit y de desplazamiento que le permiten manipular los bits individuales de valores enteros. Estos operadores son particularmente útiles cuando se trabaja con banderas, máscaras y operaciones del sistema de bajo nivel.

#### Operadores bit a bit
Java tiene cuatro operadores bit a bit: AND (`&`), OR (`|`), XOR (`^`) y complemento (`~`).

El operador AND bit a bit (`&`) devuelve un 1 en cada posición de bit para la cual los bits correspondientes de ambos operandos son 1.

```java
int a = 0b1010; // 10
int b = 0b1100; // 12
int result = a & b; // 0b1000 = 8

```

El operador OR bit a bit (`|`) devuelve un 1 en cada posición de bit para la cual los bits correspondientes de uno o ambos operandos son 1.

```java
int a = 0b1010; // 10
int b = 0b1100; // 12
int result = a | b; // 0b1110 = 14

```

El operador bit a bit XOR (OR exclusivo) (`^`) devuelve un 1 en cada posición de bit para la cual los bits correspondientes de cualquiera de los operandos, pero no de ambos, son 1.

```java
int a = 0b1010; // 10
int b = 0b1100; // 12
int result = a ^ b; // 0b0110 = 6

```

El operador complemento bit a bit (`~`) es un operador unario que invierte todos los bits de su operando.

```java
int a = 0b1010; // 10
int result = ~a; // 0b11111111111111111111111111110101 = -11

```

#### Operadores de turno
Java proporciona tres operadores de desplazamiento: desplazamiento a la izquierda (`<<`), desplazamiento a la derecha con signo (`>>`) y desplazamiento a la derecha sin signo (`>>>`).

El operador de desplazamiento a la izquierda (`<<`) desplaza los bits del primer operando a la izquierda el número de posiciones especificadas por el segundo operando. Los nuevos bits más a la derecha están llenos de ceros.

```java
int a = 0b1010; // 10
int result = a << 1; // 0b10100 = 20

```

Cada desplazamiento a la izquierda efectivamente duplica el número.

El operador de desplazamiento a la derecha con signo (`>>`) desplaza los bits del primer operando a la derecha el número de posiciones especificadas por el segundo operando. Los nuevos bits situados más a la izquierda se rellenan con el bit de signo (0 para números positivos, 1 para números negativos), conservando el signo del número.

```java
int a = 0b1010; // 10
int result = a >> 1; // 0b0101 = 5

```

Cada desplazamiento a la derecha con signo efectivamente reduce a la mitad el número, redondeando hacia abajo.

El operador de desplazamiento a la derecha sin signo (`>>>`) es similar al operador de desplazamiento a la derecha con signo, pero los nuevos bits más a la izquierda siempre se rellenan con ceros, independientemente del signo.

```java
int a = 0b11111111111111111111111111110110; // -10
int result = a >>> 1; // 0b01111111111111111111111111111011 = 2147483643

```

#### Resumen de operadores bit a bit y de desplazamiento
Aquí hay un resumen de los operadores bit a bit y de desplazamiento en Java:

| Operator | Name | Descripción | Example |
|------------|---------------------|----------------------------------------------------------------------|------------|
| `&` | Bit a bit Y | Devuelve 1 si ambos bits son 1 | `a y b` |
| `|` | Bit a bit O | Devuelve 1 si al menos un bit es 1 | `un | b` |
| `^` | XOR bit a bit | Devuelve 1 si exactamente un bit es 1 | `a^b` |
| `~` | Complemento bit a bit | Invierte todos los bits | `~a` |
| `<<` | Desplazamiento a la izquierda | Desplaza bits hacia la izquierda, llenándolos con ceros | `a <<b` |
| `>>` | Firmado desplazamiento a la derecha | Desplaza los bits hacia la derecha, llenándolos con el bit de signo | `a >> b` |
| `>>>` | Desplazamiento a la derecha sin firmar | Desplaza los bits a la derecha, llenándolos con ceros | `a >>> b` |

Algunos puntos más matizados a considerar:

- El operando izquierdo de un operador de turno determina el tipo de resultado. El operando derecho (la distancia de desplazamiento) siempre se promueve a int.

```java
   byte a = 10;
   byte b = a << 1; // Compilation error: the result is int
   int c = a << 1; // OK

```

- No existe un *operador de desplazamiento a la izquierda sin firmar* independiente en Java. El operador de desplazamiento a la izquierda (`<<`) inherentemente desplaza los bits hacia la izquierda y llena los bits más a la derecha con ceros, dejándolos efectivamente sin signo. La noción de signo o sin signo no se aplica al desplazamiento a la izquierda de la misma manera que lo hace para el desplazamiento a la derecha porque el desplazamiento a la izquierda no implica el bit de signo.

- Los operadores bit a bit y de desplazamiento tienen menor prioridad que los operadores aritméticos, pero mayor que los operadores lógicos y de comparación. Utilice paréntesis para que la precedencia sea explícita y el código sea más legible.



### Operadores de asignación
Los operadores de asignación se utilizan para asignar valores a variables. Además del operador de asignación simple (=), Java proporciona operadores de asignación compuestos que combinan una operación aritmética o bit a bit con la asignación.

Los operadores de asignación compuestos combinan una operación aritmética o bit a bit con la operación de asignación. Proporcionan una forma concisa de modificar el valor de una variable en función de su valor actual.

La sintaxis general de los operadores de asignación compuesta es:

```

variable op= expression;

```

Donde `op` es uno de los operadores aritméticos o bit a bit (`+`, `-`, `*`, `/`, `%`, `&`, `|`, `^`, `<<`, `>>`, `>>>`).

Esto equivale a:

```

variable = variable op expression;

```

Los operadores de asignación compuesta son:
- `+=` (asignación de suma)
- `-=` (asignación de resta)
- `*=` (asignación de multiplicación)
- `/=` (asignación de división)
- `%=` (asignación de módulo)
- `&=` (asignación AND bit a bit)
- `|=` (asignación OR bit a bit)
- `^=` (asignación XOR bit a bit)
- `<<=` (asignación de desplazamiento a la izquierda)
- `>>=` (asignación de desplazamiento a la derecha firmada)
- `>>>=` (asignación de desplazamiento a la derecha sin firmar)

A continuación se muestran ejemplos de cada operador de asignación compuesta:

```java
int a = 10;

a += 5;  // equivalent to a = a + 5; a is now 15
a -= 3;  // equivalent to a = a - 3; a is now 12
a *= 2;  // equivalent to a = a * 2; a is now 24
a /= 4;  // equivalent to a = a / 4; a is now 6
a %= 5;  // equivalent to a = a % 5; a is now 1

int b = 0b1010; // binary representation of 10

b &= 0b1100;  // equivalent to b = b & 0b1100; b is now 0b1000 (8 in decimal)
b |= 0b0101;  // equivalent to b = b | 0b0101; b is now 0b1101 (13 in decimal)
b ^= 0b1001;  // equivalent to b = b ^ 0b1001; b is now 0b0100 (4 in decimal)
b <<= 2;      // equivalent to b = b << 2; b is now 0b10000 (16 in decimal)
b >>= 1;      // equivalent to b = b >> 1; b is now 0b01000 (8 in decimal)
b >>>= 2;     // equivalent to b = b >>> 2; b is now 0b00010 (2 in decimal)

```

Los operadores de asignación compuestos no sólo son más concisos sino que también pueden ser más eficientes que sus equivalentes ampliados. Esto se debe a que la variable solo se evalúa una vez en la forma compuesta, mientras que se evalúa dos veces en la forma expandida.

Por ejemplo, considere el siguiente código:

```java
int[] array = {1, 2, 3, 4, 5};
int index = 2;

array[index++] += 10; // More efficient
array[index++] = array[index++] + 10; // Less efficient

```

En la primera línea, "índice" solo se incrementa una vez después de que su valor se haya utilizado para acceder al elemento de la matriz. En la segunda línea, el "índice" se incrementa dos veces, lo que genera un comportamiento inesperado y un código menos eficiente.

#### Cambiando el tipo primitivo con sufijos
Al asignar un valor a una variable de un tipo primitivo diferente, puede usar los sufijos `f`, `l` y `d` para especificar el tipo de valor literal.

- `f` se usa para literales flotantes
- `l` o `L` se usan para literales largos
- `d` o `D` se usan para literales dobles

```java
float a = 3.14f;
long b = 100L;
double c = 3.14d; // d is optional here, as double is the default for decimal literals

```

#### Valores de fundición
Al asignar un valor de un tipo a una variable de otro tipo, es posible que necesite utilizar la conversión para convertir explícitamente el valor al tipo de destino.

```java
int a = 10;
byte b = (byte) a;

```

En este ejemplo, el valor "int" se convierte en un byte antes de la asignación.

Al asignar un valor demasiado grande o demasiado pequeño para el tipo de destino, se puede producir un desbordamiento o un desbordamiento insuficiente.

```java
byte a = 127;
a++; // a is now -128 (underflow)

byte b = -128;
b--; // b is now 127 (overflow)

```

En estos ejemplos, incrementar el valor máximo de un byte provoca un desbordamiento insuficiente y disminuir el valor mínimo provoca un desbordamiento.

Para evitar un comportamiento inesperado debido a un desbordamiento o un desbordamiento insuficiente, es importante revisar sus asignaciones y asegurarse de que los valores sean apropiados para los tipos de destino.

```java
int a = 1000;
byte b = (byte) a; // b is now -24 (overflow)

```

Aquí, convertir el int value 1000 en un byte produce un desbordamiento, ya que 1000 está fuera del rango de un byte (-128 a 127).

#### Resumen de operadores de asignación
Aquí hay una tabla resumen de los operadores de asignación en Java:

| Operator | Name | Example |
|----------|---------------------------|---------|
| `=` | Asignación sencilla | `un = 10` |
| `+=` | Asignación de suma | `a += 5` |
| `-=` | Asignación de resta | `a-= 5` |
| `*=` | Asignación de multiplicación | `un *= 5` |
| `/=` | Asignación de división | `un/= 5` |
| `%=` | Asignación de módulo | `un %= 5` |
| `&=` | Asignación AND bit a bit | `un &= 5` |
| `|=` | Asignación OR bit a bit | `un |= 5` |
| `^=` | Asignación XOR bit a bit | `un^= 5` |
| `<<=` | Asignación de turno a la izquierda | `un <<= 2` |
| `>>=` | Asignación de desplazamiento a la derecha firmada | `a >>= 2` |
| `>>>=` | Asignación de desplazamiento a la derecha sin firmar | `a >>>= 2` |

Hay algunos puntos matizados a considerar:
- Al convertir un valor de punto flotante a un tipo entero, la parte fraccionaria se trunca (no se redondea).

```java
   double a = 3.9999;
   int b = (int) a; // b is now 3

```

- Los operadores de asignación compuestos tienen menor prioridad que los operadores aritméticos pero mayor que el operador de asignación simple.

```java
   int a = 10;
   a *= 5 + 2; // a is now 70 ((10 * 5) + 2)
   a = 10;
   a = a * 5 + 2; // a is now 52 ((10 * 5) + 2)

```

- El operador de asignación simple (`=`) se puede encadenar para asignar el mismo valor a múltiples variables.

```java
   int a, b, c;
   a = b = c = 10; // a, b, and c are all 10

```

- Cuando se asigna una variable a sí misma mediante un operador de asignación compuesto, la operación se realiza utilizando el valor original de la variable.

```java
   int a = 5;
   a += a++; // a is now 10 (5 + 5, then a is incremented)

```

- Cuando utilice operadores de asignación compuestos con expresiones, tenga en cuenta la precedencia de los operadores para evitar resultados inesperados.

```java
    int a = 10;
    a *= 2 + 5; // a is now 70, not 25! (a = a * (2 + 5))

```

### Operadores de Igualdad
Los operadores de igualdad en Java se utilizan para comparar dos valores de igualdad o desigualdad. Devuelven un resultado "booleano" ("verdadero" o "falso") basado en la comparación.

Java proporciona dos operadores de igualdad:
- `==` (igual a)
- `!=` (no es igual a)

A continuación se muestran ejemplos del uso de estos operadores:

```java
int a = 10;
int b = 20;

boolean result1 = (a == b); // false
boolean result2 = (a != b); // true

```

#### Entendiendo la igualdad
Cuando se utilizan operadores de igualdad, es importante comprender cómo Java compara los valores de igualdad.

Para tipos primitivos, el operador `==` compara los valores reales:

```java
int a = 10;
int b = 10;
boolean result = (a == b); // true

```

Sin embargo, para los objetos, el operador `==` compara las referencias de los objetos, no el contenido de los objetos:

```java
String s1 = new String("Hello");
String s2 = new String("Hello");
boolean result = (s1 == s2); // false

```

En este caso, `s1` y `s2` son dos objetos diferentes en la memoria, aunque contengan el mismo String value.

Para comparar el contenido de los objetos, debes usar el método `equals()`:

```java
String s1 = new String("Hello");
String s2 = new String("Hello");
boolean result = s1.equals(s2); // true

```

Sin embargo, al comparar objetos usando el método `equals()`, es importante asegurarse de que la clase de los objetos haya anulado el método `equals(Object)` para proporcionar una comparación significativa.

La implementación predeterminada de `equals(Object)` en la clase `Object` simplemente compara las referencias de objetos, tal como lo hace el operador `==`. Para comparar el contenido de los objetos, necesitas anular `equals(Object)` en tu clase:

```java
class Person {
    private String name;
    private int age;

    // Constructor, getters, setters...

    @Override
    public boolean equals(Object obj) {
        // Implementation...
    }
}

```

Para anular el método `equals` en Java, debe seguir ciertas reglas para garantizar que el método funcione correctamente y cumpla con el contrato definido por la clase `Object`:

1. **Simetría**: Si `a.equals(b)` es `true`, entonces `b.equals(a)` también debe ser `true`.

2. **Reflexividad**: Un objeto debe ser igual a sí mismo; es decir, "a.equals(a)" debe ser "verdadero".

3. **Transitividad**: Si "a.equals(b)" es "verdadero" y "b.equals(c)" es "verdadero", entonces "a.equals(c)" debe ser "verdadero".

4. **Consistencia**: Si `a.equals(b)` devuelve `true` una vez, debe continuar devolviendo `true` siempre que no se modifique ningún objeto. De manera similar, si devuelve "falso", debe devolver "falso" consistentemente.

5. **No nulidad**: `a.equals(null)` siempre debe devolver `false`.

A continuación se muestra un ejemplo de cómo anular el método "equals" en la clase "Persona":

```java
public class Person {
    private String name;
    private int age;

    // Constructor, getters, and setters

    @Override // 1.
    public boolean equals(Object obj) {
        // 2. Check if obj is the same as this object
        if (this == obj) {
            return true;
        }
        // 3. Check if obj is null or not an instance of Person
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // 4. Cast obj to Person and compare significant fields
        Person person = (Person) obj;
                                       
        // 5. Compare significant fields
        return age == person.age && (name != null ? name.equals(person.name) : person.name == null);
    }

    @Override
    public int hashCode() {
        // Ensure consistency with the equals method
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}

```

El ejemplo anterior muestra cómo anular correctamente el método "equals":

1. **Utilice la anotación `@Override`**: esto garantiza que esté anulando correctamente el método y ayuda con la legibilidad.

2. **Compruebe si hay "nulo"**: la primera comprobación debe ser para ver si el objeto que se está comparando es "nulo".

3. **Compruebe el tipo**: utilice el operador `instanceof` para asegurarse de que los objetos que se comparan sean del mismo tipo.

4. **Transmitir el objeto**: Transmitir el objeto al tipo correcto después de verificarlo.

5. **Compare campos significativos**: compare los campos que determinan la igualdad usando el operador `==` para campos primitivos y el método `equals` para campos de objeto.

Además, anule siempre "hashCode" cuando anule "equals" para mantener el contrato general de que los objetos iguales deben tener códigos hash iguales.

#### Resumen de operadores de igualdad y desigualdad
Aquí hay una tabla resumen de los operadores de igualdad y desigualdad en Java:

| Operator | Name | Example |
|----------|--------------|------------|
| `==` | Igual a | `a == b` |
| `!=` | No igual a | `a != b` |

Hay algunos puntos matizados a considerar:
- Al comparar valores de punto flotante (flotante y doble) usando `==` o `!=`, tenga en cuenta que los resultados pueden no ser los esperados debido a la naturaleza imprecisa de la representación de punto flotante.

```java
   double a = 0.1 + 0.2;
   double b = 0.3;
   boolean result = (a == b); // false

```

- Los operadores `==` y `!=` tienen mayor precedencia que los operadores lógicos (`&&`, `||`) pero menor precedencia que los operadores relacionales (`<`, `>`, `<=`, `>=`).

- El operador `!=` es la negación del operador `==`, por lo que `a != b` es equivalente a `!(a == b)`.



### Operadores relacionales
Los operadores relacionales en Java se utilizan para comparar dos valores y determinar su relación. Devuelven un resultado booleano ("verdadero" o "falso") basado en la comparación.

Java proporciona cuatro operadores relacionales:
- `<` (menos que)
- `>` (mayor que)
- `<=` (menor o igual a)
- `>=` (mayor o igual que)

Estos operadores se pueden utilizar con tipos primitivos numéricos y "char".

A continuación se muestran ejemplos del uso de operadores relacionales con valores enteros:

```java
int a = 10;
int b = 20;

boolean result1 = (a < b);  // true
boolean result2 = (a > b);  // false
boolean result3 = (a <= b); // true
boolean result4 = (a >= b); // false

```

Y aquí hay otro con valores char, que compara los valores Unicode de los caracteres:

```java
char c1 = 'a';
char c2 = 'b';
boolean result = (c1 < c2); // true

```

#### Resumen de operadores relacionales
Aquí hay una tabla resumen de los operadores relacionales en Java:

| Operator | Name | Example |
|----------|--------------------------|---------|
| `<` | Menos de | `a <b` |
| `>` | Mayor que | `a > b` |
| `<=` | Menor o igual a | `a <= b` |
| `>=` | Mayor o igual a | `a >= b` |

Además, hay dos puntos matizados a considerar:
- Al comparar valores de punto flotante ("flotante" y "doble") utilizando operadores relacionales, tenga en cuenta que los resultados pueden no ser los esperados debido a la naturaleza imprecisa de la representación de punto flotante.

```java
   double a = 0.1 + 0.2;
   double b = 0.3;
   boolean result = (a <= b); // false

```

- Los operadores relacionales tienen mayor precedencia que los operadores de igualdad (`==`, `!=`) y los operadores lógicos (`&&`, `||`), pero menor precedencia que los operadores aritméticos (`+`, `-`, `*`, `/`, `%`).



### Operadores lógicos
Los operadores lógicos en Java se utilizan para realizar operaciones lógicas en expresiones booleanas. Devuelven un resultado booleano ("verdadero" o "falso") basado en los operandos y el operador específico utilizado.

Java proporciona seis operadores lógicos:
- `&` (Y lógico)
- `|` (O lógico)
- `^` (XOR lógico)
- `&&` (Y lógico de cortocircuito)
- `||` (OR lógico de cortocircuito)
- `!` (NO lógico)

Estos operadores se pueden utilizar con valores "booleanos" o expresiones que se evalúan como valores "booleanos".

A continuación se muestran ejemplos del uso de operadores lógicos:

```java
boolean a = true;
boolean b = false;

boolean result1 = a & b;  // false
boolean result2 = a | b;  // true
boolean result3 = a ^ b;  // true
boolean result4 = a && b; // false
boolean result5 = a || b; // true
boolean result6 = !a;     // false

```

Y estas son las tablas de verdad para los operadores lógico AND (`&`), lógico OR (`|`) y lógico XOR (`^`):

Y lógico (`&`):

| un | segundo | a y b |
|---------|---------|---------|
| `false` | `false` | `false` |
| `false` | `true` | `false` |
| `true` | `false` | `false` |
| `true` | `true` | `true` |

O lógico (`|`):

| un | segundo | un \| segundo |
|---------|---------|---------|
| `false` | `false` | `false` |
| `false` | `true` | `true` |
| `true` | `false` | `true` |
| `true` | `true` | `true` |

XOR lógico (`^`):

| un | segundo | a^b |
|---------|---------|---------|
| `false` | `false` | `false` |
| `false` | `true` | `true` |
| `true` | `false` | `true` |
| `true` | `true` | `false` |


En resumen:
- `&` (Y) es `true` solo si ambos operandos son `verdaderos`.
- `|` (O) es `true` si al menos un operando es `true`.
- `^` (XOR) es `true` si exactamente un operando es `true`.

#### Cortocircuito
Los operadores `&&` y `||` son operadores de cortocircuito. Evalúan el segundo operando sólo si es necesario, basándose en el resultado del primer operando.

Para "&&", si el primer operando es "falso", toda la expresión será "falso", independientemente del segundo operando. Por tanto, el segundo operando no se evalúa.

Para `||`, si el primer operando es `true`, toda la expresión será `verdadera`, independientemente del segundo operando. Por tanto, el segundo operando no se evalúa.

El cortocircuito puede ser útil para evitar una `NullPointerException` cuando se busca `null` antes de acceder a los métodos o campos de un objeto:

```java
String str = null;
if (str != null && str.length() > 0) {
    // This code will not throw a NullPointerException
}

```

Sin embargo, tenga cuidado al utilizar operadores de cortocircuito con expresiones que tienen efectos secundarios (por ejemplo, llamadas a métodos que modifican datos o tienen otras consecuencias):

```java
int a = 10;
if (a > 5 || ++a > 10) {
    // a will be 11 if a > 5, but will remain 10 if a <= 5
}

```

#### Resumen de operadores lógicos
Aquí hay una tabla resumen de los operadores lógicos en Java:

| Operator | Name | Example |
|----------|----------------------------|---------|
| `&` | Y lógico | `a y b` |
| `|` | O lógico | `un \| b` |
| `^` | XOR lógico (OR exclusivo) | `a^b` |
| `&&` | Cortocircuito lógico Y | `a && b` |
| `||` | Cortocircuito lógico O | `a || b` |
| `!` | NO lógico | `!a` |


Aquí hay algunos puntos matizados a considerar:
- Los operadores `&`, `|` y `^` también se pueden usar como operadores bit a bit cuando se aplican a tipos de números enteros (`byte`, `short`, `int`, `long`). En este contexto, realizan operaciones AND, OR y XOR bit a bit en los bits individuales de los operandos.

- El operador `!` tiene mayor prioridad que los operadores `&`, `|`, `^`, `&&` y `||`.

- Los operadores `&`, `|` y `^` tienen menor prioridad que los operadores `&&` y `||`.

- Los operadores `&`, `|` y `^` siempre evalúan ambos operandos, incluso si el resultado se puede determinar a partir del primer operando. Esto puede ser menos eficiente que usar los operadores de cortocircuito `&&` y `||` cuando el segundo operando es costoso de evaluar o tiene efectos secundarios.

- El operador `^` devuelve `true` si y sólo si exactamente uno de sus operandos es `true`. Esto es diferente del comportamiento del operador `!=`, que devuelve verdadero si los operandos no son iguales.

- Los operadores lógicos se pueden combinar para formar expresiones booleanas complejas. Es importante utilizar paréntesis para especificar claramente el orden de evaluación previsto cuando se utilizan varios operadores.

```java
   boolean result = (a && b) || (c && d);

```

## `String` y `StringBuilder`

Una cadena es simplemente una secuencia de caracteres. Sin embargo, en el fondo, las cadenas tienen algunas propiedades y optimizaciones únicas que es importante comprender.

```java
String greeting = "Hello World!";

```

La clase `String` en Java es inmutable, lo que significa que una vez que se crea un objeto de cadena, su valor no se puede cambiar. Esto puede parecer contradictorio al principio; después de todo, a menudo modificamos cadenas en nuestros programas. Pero lo que realmente sucede es que cada vez se crea un nuevo objeto de cadena, mientras que el original permanece sin cambios. 

Esta inmutabilidad trae algunas ventajas. Las cadenas se pueden compartir de forma segura entre varias partes de un programa sin preocuparse de que una parte modifique accidentalmente la cadena para todas las demás. La JVM también puede optimizar la memoria reutilizando cadenas comunes.

Sin embargo, la inmutabilidad también significa que las operaciones que modifican una cadena (como la concatenación) son menos eficientes, porque se debe crear una nueva cadena cada vez.

### Creando cadenas

Hay algunas formas de crear una cadena en Java:

```java
String literalString = "I am a literal string";
String objectString = new String("I am a String object");

```

Ambos logran el mismo resultado final, una cadena con el valor especificado. Sin embargo, hay una ligera diferencia en cómo la JVM los maneja.

Cuando crea un literal de cadena, la JVM primero verifica el **grupo de cadenas**, un área especial de memoria reservada solo para cadenas. Si ya existe una cadena equivalente en el grupo, la JVM simplemente devuelve una referencia a esa cadena existente, en lugar de asignar nueva memoria.

```java
String s1 = "Hello";
String s2 = "Hello";
System.out.println(s1 == s2);  // Prints 'true'

```

Aquí, `s1` y `s2` en realidad se refieren al mismo objeto de cadena en la memoria, porque `"Hello"` ya estaba en el grupo de cadenas.

Por el contrario, el uso de la palabra clave `new` siempre crea un nuevo objeto, incluso si ya existe una cadena equivalente en el grupo.

```java
String s3 = new String("Hello");
System.out.println(s1 == s3);  // Prints 'false'

```

Aquí, a pesar de que `s1` y `s3` tienen el mismo contenido, se refieren a objetos diferentes en la memoria.

Si tiene un objeto de cadena y desea asegurarse de que esté usando la cadena optimizada para memoria del grupo, puede usar el método `intern()`.

```java
String s4 = s3.intern();
System.out.println(s1 == s4);  // Prints 'true'

```

Después de internar `s3`, `s4` ahora se refiere a la misma cadena agrupada que `s1`.

Sin embargo, es importante utilizar `intern()` con prudencia. En realidad, el uso excesivo puede provocar problemas de rendimiento, ya que el grupo de cadenas es un recurso finito. Se utiliza mejor para cadenas que espera que se reutilicen con frecuencia en todo el programa.

### Concatenación de cadenas

Concatenar cadenas es una operación común y Java proporciona dos formas principales de hacerlo.

```java
String s1 = "Hello";
String s2 = "World";
String s3 = s1 + " " + s2;  // Using the + operator
String s4 = s1.concat(" ").concat(s2);  // Using the concat() method

```

Ambos enfoques producen el mismo resultado. Sin embargo, hay algunas diferencias a considerar.

El operador `+` suele ser más legible y el compilador de Java lo optimiza en una operación `StringBuilder` (que cubriremos en breve). Cuando usas `+`, el compilador en realidad lo transforma en algo como esto:

```java
String s3 = new StringBuilder(s1).append(" ").append(s2).toString();

```

Entonces, aunque parezca que estás creando una nueva cadena con cada `+`, el compilador es lo suficientemente inteligente como para usar un `StringBuilder` bajo el capó para optimizarlo.

Por otro lado, el método `concat` es un método directo de la clase `String`. Concatena la cadena especificada al final de la cadena actual y devuelve una nueva cadena. Esta es la firma del método:

```java
String concat(String str)

```

Y aquí hay otro ejemplo:

```java
String s4 = s1.concat(" ");  // s4 is "Hello "
s4 = s4.concat(s2);  // s4 is now "Hello World"

```

Una ventaja de usar `concat` es que es más explícito acerca de lo que está sucediendo: estás llamando a un método para concatenar cadenas, en lugar de usar un operador. Esto puede hacer que el código sea más legible, especialmente para los desarrolladores que son nuevos en Java y pueden no estar familiarizados con cómo se optimiza el operador `+`.

Sin embargo, `concat` solo puede concatenar una cadena a la vez, por lo que para concatenar varias cadenas, necesitaría varias llamadas a `concat`, lo que puede resultar engorroso. El operador `+` permite concatenar varias cadenas en una sola expresión, lo que suele ser más conveniente.

En última instancia, la elección entre "+" y "concat" a menudo se reduce a preferencias personales y estilo de codificación. Muchos desarrolladores prefieren "+" por su concisión y legibilidad, mientras que otros prefieren lo explícito de "concat".

Sin embargo, es importante tener cuidado al utilizar cualquiera de los métodos en bucles, ya que puede provocar problemas de rendimiento debido a la creación de muchos objetos de cadena intermedios. En tales casos, se debe utilizar "StringBuilder" directamente.

```java
String result = "";
for (int i = 0; i < 100; i++) {
    result = result.concat(Integer.toString(i));  // Inefficient!
}

```

Este código creará una nueva cadena en cada iteración del bucle. Para un gran número de iteraciones, esto puede resultar muy ineficiente en términos de tiempo y memoria. En tales casos se debe utilizar un `StringBuilder` (que discutiremos más adelante).


### Métodos de cadena importantes

La clase `String` proporciona un amplio conjunto de métodos para examinar y manipular el contenido de las cadenas. Estos son algunos de los más utilizados:

- `int length()`: Devuelve el número de caracteres de la cadena.

- `char charAt(int index)`: Devuelve el carácter en el índice especificado.

- `int indexOf(String str)`: Devuelve el índice dentro de la cadena de la primera aparición de la subcadena especificada.

- `String substring(int beginIndex, int endIndex)`: Devuelve una nueva cadena que es una subcadena de esta cadena.

- `String toLowerCase()`: Convierte todos los caracteres de esta cadena a minúsculas.

- `String toUpperCase()`: Convierte todos los caracteres de esta cadena a mayúsculas.

- `boolean equals(Object obj)`: compara esta cadena con el objeto especificado.

- `boolean equalsIgnoreCase(String anotherString)`: compara esta cadena con otra cadena, ignorando las consideraciones entre mayúsculas y minúsculas.

- `boolean startsWith(String prefix)`: prueba si esta cadena comienza con el prefijo especificado.

- `boolean endsWith(String suffix)`: prueba si esta cadena termina con el sufijo especificado.

- `boolean contains(CharSequence s)`: devuelve `true` si y solo si esta cadena contiene la secuencia especificada de valores `char`.

- `String replace(char oldChar, char newChar)`: devuelve una nueva cadena resultante de reemplazar todas las apariciones de `oldChar` en esta cadena con `newChar`.

- `String strip()`: Devuelve una cadena cuyo valor es esta cadena, sin todos los espacios en blanco iniciales y finales.

- `String trim()`: Devuelve una cadena cuyo valor es esta cadena, con todos los espacios iniciales y finales eliminados, donde el espacio se define como cualquier carácter cuyo punto de código sea menor o igual a `'U+0020'` (el carácter de espacio).

- `String indent(int n)`: Ajusta la sangría de cada línea de esta cadena en función del valor de `n`.

- `String stripIndent()`: Devuelve una cadena cuyo valor es esta cadena, sin espacios en blanco incidentales al principio y al final de cada línea.

- `boolean isEmpty()`: Devuelve `true` si, y sólo si, `length()` es 0.

- `boolean isBlank()`: Devuelve `true` si la cadena está vacía o contiene solo puntos de código de espacios en blanco; de lo contrario, `false`.

Cada uno de estos métodos proporciona una utilidad específica y juntos forman un poderoso conjunto de herramientas para trabajar con cadenas. Sin embargo, recuerde que debido a la inmutabilidad de las cadenas, los métodos que tienen `String` como tipo de retorno devuelven una nueva cadena en lugar de modificar la original. Por ejemplo:

```java
String s1 = "  Hello World   ";
String s2 = s1.strip();
System.out.println(s1);  // Still prints "  Hello World   "
System.out.println(s2);  // Prints "Hello World"

```

Esto también permite una técnica conocida como encadenamiento de métodos, donde se invocan múltiples métodos en una sola expresión.

```java
String result = "  Hello World  ".trim().toUpperCase().replace('O', '0');
System.out.println(result);  // Prints "HELL0 W0RLD"

```

Aquí, se eliminan los espacios en blanco de la cadena original, luego se convierte a mayúsculas y, finalmente, todos los caracteres `'O'` se reemplazan por `'0'`. Cada método devuelve una nueva cadena que se convierte en la base para el siguiente método de la cadena.

El encadenamiento puede hacer que su código sea más conciso y legible, pero es importante no exagerar. Las cadenas excesivamente largas pueden resultar difíciles de entender y depurar.

Además, algunos de estos métodos funcionan con índices. Un índice varía de 0 a `length()` - 1. El primer valor de carácter de la secuencia está en el índice 0, el siguiente en el índice 1, y así sucesivamente, tal como funciona con matrices.

### Anulando `toString()`

Un método especial a tener en cuenta es `toString()`. Este método se define en la clase `Object`, de la que heredan todas las clases en Java. Devuelve una representación de cadena del objeto.

De forma predeterminada, esta cadena no es muy informativa (incluye el nombre de clase del objeto y el código hash). Sin embargo, podemos anular `toString()` en nuestras propias clases para proporcionar una representación más útil.

```java
public class Person {
    private String name;
    private int age;

    // Constructor and other methods...

    @Override
    public String toString() {
        return "Person[name=" + name + ",age=" + age + "]";
    }
}

```

Ahora, cuando imprimamos un objeto `Persona`, obtendremos una cadena con un formato agradable:

```java
Person alice = new Person("Alice", 25);
System.out.println(alice);  // Prints "Person[name=Alice,age=25]"

```

Esto es especialmente útil para fines de registro y depuración.

### Formato de cadenas

Además de manipular cadenas, Java proporciona potentes herramientas para formatearlas. La clase `String` incluye los métodos `format()` y `formatted()` que le permiten crear una cadena formateada usando una cadena de formato y argumentos.

```java
String name = "Alice";
int age = 25;
String city = "Florida";
String formatted = String.format("My name is %s, I'm %d years old, and I live in %s.", name, age, city);
System.out.println(formatted);
// Prints "My name is Alice, I'm 25 years old, and I live in Florida."

```

La cadena de formato incluye marcadores de posición (`%s` para cadenas, `%d` para números enteros, etc.) que se reemplazan por los argumentos correspondientes.

Estos son algunos de los marcadores de posición de formato más comunes:

| Especificador de formato | Descripción |
|--------------------|-------------|
| `%s` | Cadena |
| `%c` | Personaje |
| `%d` | Entero decimal |
| `%f` | Número de coma flotante |
| `%t` | Fecha/hora |
| `%n` | Nueva línea |

Estos son solo algunos ejemplos, la [lista completa de opciones de formato](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Formatter.html#syntax) es bastante extensa, lo que permite un control preciso sobre el formato de salida.

### Usando la clase `StringBuilder`

Si bien la clase `String` es poderosa, su inmutabilidad puede provocar problemas de rendimiento cuando es necesario realizar muchas modificaciones en una cadena. Aquí es donde entra en juego "StringBuilder".

`StringBuilder` es una secuencia mutable de caracteres. Proporciona métodos similares a `String` para agregar, insertar y eliminar caracteres. Sin embargo, estos métodos modifican el propio `StringBuilder` en lugar de crear un nuevo objeto.

Esta mutabilidad permite un código más eficiente cuando necesita realizar múltiples modificaciones en una cadena. Con `String`, cada modificación crea un nuevo objeto de cadena, lo que puede resultar costoso en términos de tiempo y memoria si se realiza con frecuencia, como en un bucle. `StringBuilder` evita esto modificando directamente su secuencia de caracteres interna.

Además, los métodos `StringBuilder` se pueden encadenar entre sí, de forma similar a los métodos `String`. Sin embargo, debido a que `StringBuilder` es mutable, cada método en la cadena modifica la misma instancia de `StringBuilder` y devuelve una referencia a ella, lo que permite un encadenamiento adicional:

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World").insert(0, "Hey, ").delete(4, 9);
System.out.println(sb);  // Prints "Hey, World"

```

En este ejemplo, comenzamos con un `StringBuilder` que contiene `"Hello"`. Luego le agregamos `" World"`, insertamos `"Hey, "` al principio y eliminamos los caracteres del índice 4 al 8 (inclusive). Cada una de estas operaciones modifica la misma instancia de `StringBuilder`.

Puedes crear un `StringBuilder` de varias maneras:

```java
StringBuilder sb1 = new StringBuilder();  // Creates an empty StringBuilder
StringBuilder sb2 = new StringBuilder(10);  // Creates a StringBuilder with initial capacity of 10
StringBuilder sb3 = new StringBuilder("Hello");  // Creates a StringBuilder initialized with the string "Hello"

```

Cuando crea un `StringBuilder` sin especificar una cadena inicial, comienza con una capacidad predeterminada de 16 caracteres. Si sabe que creará una cadena más grande, puede especificar una capacidad inicial más alta para evitar el cambio de tamaño automático más adelante, lo que puede resultar costoso.

### Métodos importantes de `StringBuilder`

`StringBuilder` proporciona muchos de los mismos métodos que `String` para examinar y modificar la secuencia de caracteres; sin embargo, vale la pena mencionar dos cosas:
- Estos métodos modifican la instancia `StringBuilder` en sí en lugar de crear una nueva.
- `StringBuilder` no se extiende desde `String`; sin embargo, ambas clases se extienden desde la interfaz [CharSequence](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/CharSequence.html).

**Métodos comunes con `String`:**
- `int length()`: Devuelve el número de caracteres en `StringBuilder`.
- `char charAt(int index)`: Devuelve el carácter en la posición especificada.
- `int indexOf(String str)`: Devuelve el índice dentro de esta cadena de la primera aparición de la subcadena especificada.
- `String substring(int start)` y `substring(int start, int end)`: Devuelve una nueva cadena que es una subcadena de esta secuencia.

**Añadiendo valores:**
- `StringBuilder append(...)`: Agrega la representación de cadena del argumento a la secuencia. Hay sobrecargas para todos los tipos primitivos, matrices `char`, `CharSequence` y `Object`.

**Insertando datos:**
- `StringBuilder insert(int offset, ...)`: Inserta la representación de cadena del segundo argumento en la secuencia en la posición especificada por el primer argumento. Hay sobrecargas para todos los tipos primitivos, matrices `char`, `CharSequence` y `Object`.

**Eliminando contenidos:**
- `StringBuilder delete(int start, int end)`: Elimina los caracteres en una subcadena de esta secuencia.
- `StringBuilder deleteCharAt(int index)`: elimina el carácter en la posición especificada.

**Reemplazar porciones:**
- `StringBuilder replace(int start, int end, String str)`: Reemplaza los caracteres en una subcadena de esta secuencia con caracteres en la cadena especificada.
- `setCharAt(int index, char ch)`: establece el carácter en el índice especificado en `ch`.

**Reversa:**
- `void StringBuilder reverse()`: Provoca que esta secuencia de caracteres sea reemplazada por el reverso de la secuencia.

**Conversión a `String`:**
- `String toString()`: Devuelve una cadena que representa los datos en esta secuencia.

A continuación se muestra un ejemplo que demuestra algunos de estos métodos:

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" there");  // Now contains "Hello there"
sb.insert(5, ",");  // Now contains "Hello, there"
sb.replace(7, 12, "world");  // Now contains "Hello, world"
sb.delete(5, 7);  // Now contains "Helloworld"
sb.reverse();  // Now contains "dlrowolleH"

String finalString = sb.toString();
System.out.println(finalString);  // Prints "dlrowolleH"

```

En este ejemplo, comenzamos con un `"StringBuilder` que contiene `"Hello"`, luego le agregamos `" there"`, insertamos una coma después de `"Hello"`, reemplazamos `"there"` por `"world"`, eliminamos la coma y el espacio, invertimos toda la cadena y finalmente la convertimos en una `String`.

Es importante tener en cuenta que, si bien "StringBuilder" es mutable, no está sincronizado. Si varios subprocesos acceden a la misma instancia de `StringBuilder` simultáneamente y al menos uno de los subprocesos la está modificando, debe garantizar una sincronización adecuada en su código para evitar la corrupción de datos. Si necesita una versión segura para subprocesos, puede usar `StringBuffer`, que es como `StringBuilder` pero está sincronizado (a costa de cierta sobrecarga de rendimiento).

Finalmente, aquí hay un diagrama que resume las diferencias entre `String` y `StringBuilder`:

```

┌───────────────────────────────────────────────────┐
│           String vs StringBuilder                 │
│                                                   │
│  ┌─────────────────────┐ ┌─────────────────────┐  │
│  │       String        │ │    StringBuilder    │  │
│  ├─────────────────────┤ ├─────────────────────┤  │
│  │ - Immutable         │ │ - Mutable           │  │
│  │ - Thread-safe       │ │ - Not thread-safe   │  │
│  │ - Slower for        │ │ - Faster for        │  │
│  │   concatenation     │ │   concatenation     │  │
│  │ - Less memory       │ │ - More memory       │  │
│  │   efficient for     │ │   efficient for     │  │
│  │   many modifications│ │   many modifications│  │
│  └─────────────────────┘ └─────────────────────┘  │
│                                                   │
│  Use for:               Use for:                  │
│  - Constant strings     - Building strings        │
│  - Simple concatenation - Many modifications      │
│  - Thread safety needed - Performance critical    │
│                           string operations       │
└───────────────────────────────────────────────────┘

```

## Bloques de texto
Un bloque de texto proporciona una sintaxis más concisa e intuitiva para representar cadenas que conserva las nuevas líneas y la sangría sin la necesidad de secuencias de escape explícitas o concatenación:

```java
String traditional = "{\n" +
                     "  \"name\": \"John Doe\",\n" +
                     "  \"age\": 30\n" +
                     "}";

String textBlock = """
                   {
                     "name": "John Doe",  
                     "age": 30
                   }
                   """;

```

Como puedes ver, la versión en bloque de texto es mucho más limpia y fácil de leer. Elimina todo el ruido de los caracteres de nueva línea (`\n`) y las comillas de escape (`\"`) que abarrotan el literal de cadena tradicional. Con los bloques de texto, lo que ves es lo que obtienes, la cadena aparece en tu código exactamente como se generará.

Para definir un bloque de texto, utilice tres comillas dobles (""""`) como delimitadores de apertura y cierre. El contenido del bloque de texto aparece entre estos delimitadores y puede abarcar varias líneas:

```java
String textBlock = """
                   This is a Text Block.
                   It can contain multiple lines,
                     indentation,
                   and "special" characters.
                   """;

```

Tenga en cuenta que el delimitador de cierre (""""`) debe aparecer solo en una línea e ir seguido de un punto y coma. Se ignorará cualquier espacio en blanco después del delimitador de cierre en esa línea.

Un error común es creer que se pueden utilizar comillas simples para cerrar un bloque de texto que se abrió con comillas triples. Sin embargo, este no es el caso. Los delimitadores de apertura y cierre de un bloque de texto siempre deben ser tres comillas dobles (""""`).

El compilador trata todo el contenido entre los delimitadores como parte de la cadena literal, incluidas las nuevas líneas, la sangría y cualquier otro espacio en blanco. Sin embargo, existen algunas reglas sobre la sangría y el escape que analizaremos en breve.

### Características de los bloques de texto
Una de las características clave de los bloques de texto es su capacidad para representar cadenas de varias líneas de forma natural, sin recurrir a caracteres de nueva línea explícitos ni a la concatenación de cadenas.

```java
String multiLine = """
                   First line
                   Second line
                   Third line
                   """;

```

Este bloque de texto conservará las nuevas líneas y la sangría exactamente como están escritas, lo que dará como resultado una cadena con tres líneas de texto. No necesita agregar manualmente caracteres `\n` ni preocuparse por alinear cadenas concatenadas.

Los bloques de texto también proporcionan manejo automático de sangría según la posición del delimitador de cierre. El compilador determinará un prefijo de espacio en blanco común a partir de las líneas entre los delimitadores y eliminará automáticamente ese prefijo de cada línea.

```java
String indented = """
                    Line 1
                      Line 2
                    Line 3
                    """;

// Equivalent to:
// "Line 1\n  Line 2\nLine 3\n"

```

En este ejemplo, el delimitador de cierre está alineado con la línea con menor sangría (`Línea 1`). Por lo tanto, el prefijo de espacios en blanco común es de cuatro espacios, que se eliminan de cada línea. La cadena resultante tendrá la "Línea 2" con una sangría de dos espacios en relación con las otras líneas.

Es importante tener en cuenta que los bloques de texto no recortan automáticamente todos los espacios en blanco iniciales y finales. El compilador solo elimina el prefijo de espacios en blanco común según la posición del delimitador de cierre. Cualquier espacio en blanco inicial o final adicional se conservará en la cadena final.

Considere este ejemplo:

```java
String traditional = "  \n  ";  // This evaluates to two spaces, a newline, and two more spaces.
String textBlock = """
                     \n  
                   """;        // Evaluates to two spaces, a newline, two spaces, 
                               // and an additional final newline added by the text block syntax.

```

Aquí, la cadena "tradicional" incluirá espacios antes y después de la nueva línea, ya que son explícitamente parte de la cadena. En el `textBlock`, todos los espacios y la nueva línea se conservan tal como aparecen, y se agrega una nueva línea al final debido a cómo los bloques de texto manejan el delimitador de cierre.

Otro aspecto importante de los bloques de texto son los caracteres especiales de escape. Las reglas para escapar en bloques de texto son prácticamente las mismas que en las cadenas literales tradicionales, con algunas advertencias:
- No es necesario utilizar caracteres de escape para una comilla doble simple o un par de comillas dobles dentro de un bloque de texto.
- Las comillas dobles triples dentro del bloque de texto deben tener caracteres de escape para evitar que finalicen el bloque prematuramente.
- Para incluir un carácter de barra invertida (`\`) en el bloque de texto, debe evitarlo con otra barra invertida (`\\`).

He aquí un ejemplo:

```java
String escaped = """
                 This is a "quoted" text with \\ and \u0040.
                 """;

```

Un error común es que las barras invertidas se ignoran en los bloques de texto, ya que son literales de varias líneas. Sin embargo, este no es el caso. Las barras invertidas todavía tienen un significado especial en los bloques de texto y es necesario utilizar caracteres de escape si desea incluir una barra invertida literal en la cadena.

Y, como puede ver en el ejemplo anterior, los bloques de texto también admiten el uso de escapes Unicode (`\uXXXX`) para representar caracteres mediante sus puntos de código Unicode.

Por último, los bloques de texto se pueden combinar con cadenas literales tradicionales e incluso con otros bloques de texto usando el operador `+`, al igual que las cadenas normales:

```java
String name = "John";
String greeting = """
                  Hello, """ + name + """
                  . How are you?
                  """;

```

Aquí, concatenamos un bloque de texto con una cadena literal tradicional (`nombre`) para crear un saludo personalizado. El operador `+` aparece en la misma línea que los delimitadores de apertura y cierre de los bloques de texto. Colocarlo en una línea separada puede generar espacios en blanco no deseados en la cadena resultante.



## La API de matemáticas
La API "Math" proporciona un amplio conjunto de métodos estáticos para realizar operaciones matemáticas. Incluye métodos para encontrar el mínimo y el máximo de dos valores, redondear números, determinar el techo y el suelo de un valor y generar números aleatorios. Repasemos cada uno de estos.

### Encontrar el mínimo y el máximo

La clase "Math" proporciona métodos "min" y "max" para encontrar el mínimo y el máximo de dos valores respectivamente. Estos métodos están sobrecargados para aceptar argumentos "int", "long", "float" y "double":

```java
static double max(double a, double b)
static float max(float a, float b)
static int max(int a, int b)
static long max(long a, long b)

static double min(double a, double b)
static float min(float a, float b)
static int min(int a, int b)
static long min(long a, long b)

```

A continuación se muestran algunos ejemplos:

```java
int min = Math.min(5, 10);  // min is 5
int max = Math.max(5, 10);  // max is 10

double min2 = Math.min(5.7, 10.2);  // min2 is 5.7
double max2 = Math.max(5.7, 10.2);  // max2 is 10.2

```

Estos métodos son útiles cuando necesita aplicar un rango a un valor.

### Redondear números

La clase `Math` proporciona varios métodos para redondear números:

- `int round(float)` y `long round(double)`: estos métodos devuelven el `int` o `long` más cercano al argumento. Los valores intermedios (como 0,5) se redondean hacia arriba, siguiendo la convención de redondear a la mitad hacia arriba.
- `double rint(double)`: Devuelve el valor `double` que tiene el valor más cercano al argumento y es igual a un entero matemático. Si dos valores "dobles" que son números enteros matemáticos están igualmente cerca, se elige el par.
- `double floor(double)`: Devuelve el valor `double` más grande (más cercano al infinito positivo) que es menor o igual que el argumento y es igual a un entero matemático.
- `double ceil(double)`: Devuelve el valor `double` más pequeño (más cercano al infinito negativo) que es mayor o igual que el argumento y es igual a un entero matemático.

A continuación se muestran algunos ejemplos:

```java
long roundedLong = Math.round(5.7);  // roundedLong is 6
int roundedInt = Math.round(5.4f);  // roundedInt is 5

double rintValue = Math.rint(5.5);  // rintValue is 6.0 (ties round to even)
double rintValue2 = Math.rint(6.5);  // rintValue2 is 6.0

double floorValue = Math.floor(5.7);  // floorValue is 5.0
double ceilingValue = Math.ceil(5.2);  // ceilingValue is 6.0

```

Como puede ver, el piso es el entero más grande menor o igual al valor, mientras que el techo es el entero más pequeño mayor o igual al valor.

### Generando números aleatorios

La clase `Math` incluye un método `random()` que devuelve un valor `double` con signo positivo, mayor o igual a 0.0 y menor que 1.0:

```java
static double random()

```

Este método es útil para generar números aleatorios.

```java
double randomValue = Math.random();  // randomValue is a random double between 0.0 and 1.0

```

Puede utilizar `Math.random()` en combinación con otros métodos `Math` para generar números aleatorios en un rango específico. Por ejemplo, para generar un número entero aleatorio entre 1 y 10 (inclusive), puedes hacer esto:

```java
int randomInt = (int)(Math.random() * 10) + 1;

```

Así es como funciona esto:
1. `Math.random()` genera un doble aleatorio entre 0.0 y 1.0, llamémoslo `r`.
2. `r * 10` es entonces un doble aleatorio entre 0,0 y 10,0.
3. `(int)(r * 10)` convierte este doble en un int, redondeándolo efectivamente hacia abajo. Ahora tenemos un número entero aleatorio entre 0 y 9.
4. Finalmente, sumamos 1 para cambiar el rango entre 1 y 10.

Puede ajustar esta fórmula para generar números aleatorios en cualquier rango de enteros. Por ejemplo, para generar un número aleatorio entre "min" y "max" (inclusive), puedes usar:

```java
int randomNum = (int)(Math.random() * (max - min + 1)) + min;

```

## Puntos clave
- Java tiene 8 tipos de datos primitivos: `byte`, `short`, `int`, `long`, `float`, `double`, `boolean` y `char`.

- Los tipos primitivos son los tipos de datos más básicos y no son objetos. Almacenan valores simples directamente en la memoria.

- Los literales enteros se pueden asignar usando notación decimal, hexadecimal (prefijo `0x` o `0X`), octal (prefijo `0`) o binaria (prefijo `0b` o `0B`).

- Los guiones bajos se pueden utilizar en literales numéricos para mejorar la legibilidad, pero tienen restricciones en su ubicación.

- Los tipos de referencia almacenan la dirección de memoria donde reside un objeto, en lugar del objeto en sí.

- Las clases contenedoras (`Boolean`, `Byte`, `Short`, `Integer`, `Long`, `Float`, `Double`, `Character`) permiten utilizar primitivas como objetos.

- Autoboxing convierte automáticamente una primitiva a su clase contenedora, mientras que unboxing convierte un objeto contenedor a su tipo primitivo.

- Las clases contenedoras proporcionan métodos para analizar, convertir entre tipos y más.

- Los objetos envolventes pueden ser "nulo", mientras que los primitivos no. Al desempaquetar un objeto contenedor "nulo", se genera una "NullPointerException".

- Java proporciona un amplio conjunto de operadores para operaciones matemáticas, lógicas y bit a bit.

- La precedencia del operador determina el orden de evaluación de las expresiones. Los paréntesis pueden cambiar la prioridad predeterminada.

- Los operadores unarios (`++`, `--`, `+`, `-`, `~`, `!`) operan en un solo operando. Los operadores de incremento y decremento (`++` y `--`) se pueden usar en forma de prefijo o postfijo.

- Los operadores binarios (`+`, `-`, `*`, `/`, `%`) operan con dos operandos. La promoción numérica convierte automáticamente los operandos a un tipo mayor para evitar la pérdida de precisión.

- Los operadores bit a bit (`&`, `|`, `^`, `~`) y operadores de desplazamiento (`<<`, `>>`, `>>>`) manipulan bits individuales de valores enteros.

- Los operadores de asignación (`=`, `+=`, `-=`, `*=`, `/=`, `%=`, `&=`, `^=`, `|=`, `<<=`, `>>=`, `>>>=`) asignan valores a las variables. Los operadores de asignación compuestos combinan una operación con una asignación.

- Los operadores de igualdad (`==` y `!=`) comparan valores para determinar la igualdad. Para objetos, `==` compara referencias, mientras que equals() compara contenidos.

- Los operadores relacionales (`<`, `>`, `<=`, `>=`) comparan valores y determinan su relación.

- Los operadores lógicos (`&`, `|`, `^`, `&&`, `||`, `!`) realizan operaciones lógicas en expresiones booleanas. Los operadores de cortocircuito (`&&` y `||`) pueden omitir la evaluación del segundo operando según el valor del primer operando.

- Las cadenas en Java son inmutables, lo que significa que su valor no se puede cambiar una vez creadas. Cualquier operación que parezca modificar una cadena en realidad crea una nueva cadena.

- Los literales de cadena se almacenan en el grupo de cadenas, un área especial de la memoria. Si ya existe una cadena equivalente en el grupo, se devuelve una referencia a esa cadena en lugar de crear un nuevo objeto.

- Las cadenas se pueden concatenar usando el operador `+` o el método `concat()`. El compilador optimiza el operador `+` en una operación `StringBuilder`.

- La clase `String` proporciona muchos métodos para examinar y manipular el contenido de la cadena, como `length()`, `charAt()`, `substring()`, `toLowerCase()`, `equals()`, `startsWith()`, `endsWith()`, `replace()`, `trim()` y más.

- El método `toString()`, heredado de la clase `Object`, devuelve una representación de cadena de un objeto. Se puede anular en clases personalizadas para proporcionar una representación más informativa.

- La clase `String` proporciona métodos `format()` y `formatted()` para crear cadenas formateadas usando marcadores de posición.

- `StringBuilder` es una secuencia mutable de caracteres. Proporciona métodos similares a `String` para agregar, insertar y eliminar caracteres, pero estos métodos modifican el `StringBuilder` en sí en lugar de crear un nuevo objeto.

- `StringBuilder` es más eficiente que `String` cuando es necesario realizar muchas modificaciones, ya que evita crear un nuevo objeto para cada modificación.

- Los métodos importantes de `StringBuilder` incluyen `append()`, `insert()`, `delete()`, `replace()`, `reverse()` y `toString()`.

- Texto blocks proporciona una sintaxis más concisa e intuitiva para representar cadenas de varias líneas. Se definen utilizando comillas dobles triples (""""`) como delimitadores.

- Los bloques de texto manejan automáticamente nuevas líneas, sangrías y prefijos de espacios en blanco comunes, lo que los hace más fáciles de leer y escribir que los literales de cadena tradicionales.

- La clase `Math` proporciona muchos métodos estáticos para realizar operaciones matemáticas, incluidos `min()`, `max()`, `round()`, `floor()`, `ceil()` y `random()`.

- El método `random()` se puede utilizar en combinación con otros métodos `Math` para generar números aleatorios en un rango específico.



## Preguntas de práctica

**1. ¿Cuál de las siguientes afirmaciones sobre los tipos de datos de referencia y primitivos de Java es verdadera?**

**A)** Un "doble" se puede asignar directamente a un "flotante" sin realizar un casting.  
**B)** Un `boolean` se puede convertir en un `int`.  
**C)** Se puede asignar una `String` a una variable de referencia `Object`.  
**D)** Un `char` es un tipo de datos de referencia.  
**E)** Un `int` puede almacenar un valor `long` sin ninguna conversión explícita.


**2. ¿Cuál es el resultado del siguiente fragmento de código?**

```java
public class OperatorTest {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        int c = 15;
        int result = a + b * c / a - b;
        System.out.println(result);
    }
}

```

**A)** `25`  
**B)** `35`  
**C)** `20`  
**D)** `15`


**3. ¿Cuál de las siguientes afirmaciones sobre `String` y `StringBuilder` es verdadera?**

**A)** Los objetos `StringBuilder` son inmutables.  
**B)** Los objetos `String` se pueden modificar después de su creación.  
**C)** `StringBuilder` está sincronizado y es seguro para subprocesos.  
**D)** `StringBuilder` proporciona métodos para secuencias mutables de caracteres.  
**E)** `String` y `StringBuilder` tienen las mismas características de rendimiento para la manipulación de cadenas.


**4. ¿Cuáles de las siguientes afirmaciones sobre bloques de texto son verdaderas? (Elija todas las que correspondan).**

**A)** Los bloques de texto pueden abarcar varias líneas sin necesidad de secuencias de escape para líneas nuevas.  
**B)** Los bloques de texto conservan el formato exacto, incluidos los espacios en blanco, del código tal como está escrito.  
**C)** Los bloques de texto solo se pueden usar dentro de los métodos.  
**D)** Los bloques de texto recortan automáticamente los espacios en blanco iniciales y finales de cada línea.  
**E)** Los bloques de texto requieren un nivel de sangría mínimo de un espacio.



**5. ¿Cuál de las siguientes afirmaciones sobre la clase "Matemáticas" es cierta?**

**A)** El método `Math.round()` devuelve un `double`.  
**B)** El método `Math.random()` devuelve un número entero aleatorio.  
**C)** El método `Math.max()` solo se puede utilizar con números enteros.  
**D)** El método `Math.pow()` devuelve el resultado de elevar el primer argumento a la potencia del segundo argumento.  
**E)** El método `Math.abs()` solo se puede utilizar con números positivos.