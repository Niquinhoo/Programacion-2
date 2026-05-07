---
layout: chapter

title: "Capítulo UNO"
subtitle: "Utilizando el enfoque orientado a objetos de Java - Parte 1"
exam_objectives:
  - "Declare y cree instancias de objetos Java, incluidos objetos de clases anidados, y explique el ciclo de vida del objeto, incluida la creación, la reasignación de referencias y la recolección de basura."
  - "Cree clases y registros, y defina y utilice campos y métodos estáticos y de instancia, constructores e inicializadores estáticos y de instancia."
  - "Implemente la sobrecarga, incluidos los métodos var-arg."

previous_link: "/intro.html"
previous_title: "Introducción"
next_link: "/ch02.html"
next_title: "Utilizando el enfoque orientado a objetos de Java - Parte 2"
answers_link: "/ch01a.html"
---
## Contenido del capítulo

- [Introducción a la Programación Orientada a Objetos](#introducción-a-la-programación-orientada-a-objetos)
    - [Objetos y clases](#objetos-y-clases)
    - [Principios de programación orientada a objetos de nivel superior](#principios-de-programación-orientada-a-objetos-de-nivel-superior)
- [Ciclo de vida del objeto en Java](#ciclo-de-vida-del-objeto-en-java)
    - [Reasignación de referencia](#reasignación-de-referencia)
    - [Recolección de basura](#recolección-de-basura)
- [Palabras clave](#palabras-clave)
- [Comentarios](#comentarios)
- [Organizar clases en paquetes](#organizar-clases-en-paquetes)
    - [Creando un paquete](#creando-un-paquete)
    - [Uso de declaraciones de importación](#uso-de-declaraciones-de-importación)
    - [Casos especiales y mejores prácticas](#casos-especiales-y-mejores-prácticas)
    - [Importaciones redundantes](#importaciones-redundantes)
    - [Control de acceso](#control-de-acceso)
- [Modificadores de acceso](#modificadores-de-acceso)
- [Declaración de clases](#declaración-de-clases)
- [Miembros estáticos y de instancia](#miembros-estáticos-y-de-instancia)
- [Declaración de campos](#declaración-de-campos)
    - [Acceder y modificar campos](#acceder-y-modificar-campos)
- [Declaración de métodos](#declaración-de-métodos)
    - [Firmas de métodos](#firmas-de-métodos)
    - [Llamar a un método](#llamar-a-un-método)
    - [Uso de modificadores de acceso con métodos](#uso-de-modificadores-de-acceso-con-métodos)
    - [Pasar argumentos entre métodos](#pasar-argumentos-entre-métodos)
    - [Sobrecarga de métodos](#sobrecarga-de-métodos)
    - [Varargs](#varargs)
    - [El método `main`](#el-método-main)
- [Constructores e inicializadores](#constructores-e-inicializadores)
    - [Constructores](#constructores)
    - [Inicializadores de instancia](#inicializadores-de-instancia)
    - [Inicializadores estáticos](#inicializadores-estáticos)
    - [Orden de inicialización](#orden-de-inicialización)
- [Extendiéndose desde `java.lang.Object`](#extendiéndose-desde-javalangobject)
- [Clases anidadas](#clases-anidadas)
    - [Clases anidadas estáticas](#clases-anidadas-estáticas)
    - [Clases anidadas no estáticas](#clases-anidadas-no-estáticas)
    - [Clases locales](#clases-locales)
    - [Clases anónimas](#clases-anónimas)
- [Clases y archivos fuente](#clases-y-archivos-fuente)
- [Puntos clave](#puntos-clave)
- [Preguntas de práctica](#preguntas-de-práctica)

---
## Introducción a la programación orientada a objetos

Como su nombre lo indica, la programación orientada a objetos (POO) es un paradigma de programación centrado en el concepto de objetos. En lugar de estructurar programas en torno a procedimientos y funciones (como la programación de procedimientos), la programación orientada a objetos organiza el código en objetos, que representan entidades del mundo real que contienen datos (atributos) y comportamientos (métodos). Este enfoque ofrece varias ventajas:

- Mejora de la organización y modularidad.
- Reutilización de código mediante herencia.
- Modelado del mundo real

Java es un lenguaje OOP, por lo que sus componentes básicos son objetos y clases.

### Objetos y clases

Los objetos son instancias distintas en el código que contienen datos y comportamientos. Las clases, por otro lado, son planos o plantillas que definen los datos y comportamientos comunes a todos los objetos de esa clase.

Para comprender mejor estos conceptos, piense en las galletas hechas con un cortador de galletas. El cortador de cookies define la forma y el tamaño de las cookies, del mismo modo que las clases definen qué atributos y métodos tendrán las instancias de objetos. Cada galleta puede ser única, con diferentes ubicaciones de chispas de chocolate, del mismo modo que los objetos contienen valores de datos distintos.

Por ejemplo, podemos definir una clase `Cookie` que especifique los atributos de las cookies, como sabor, forma, cobertura, etc. También puede definir métodos, que son funciones que operan con los datos. Los métodos permiten que los objetos realicen acciones. Nuestros objetos `Cookie` podrían tener un método `eat()`:

```java
public class Cookie {
    // Attributes
    String flavor; 
    int size;
                     
    // Behavior (Method)
    public void eat() {
        System.out.println("That was yummy!");
    }
}

```

- `Cookie de clase pública` define una nueva clase `Cookie`.
- `public` hace que esta clase sea accesible desde otras clases.
- `Sabor de cadena;` declara un nuevo atributo de cadena llamado `sabor`.
- `int size;` declara un atributo `size` entero.
- `public void eat()` define un método público `eat` que no devuelve un valor (`void`).
- Los cuerpos de la clase y del método están entre corchetes `{ }`.
- `System.out.println();` imprime texto en la salida estándar (generalmente la consola o ventana de terminal).

Y podemos crear instancias de objetos cookie desde la clase `Cookie`:

```java 
Cookie chocoChip = new Cookie();
chocoChip.flavor = "Chocolate Chip";
chocoChip.size = 2;

Cookie oatmealRaisin = new Cookie(); 
oatmealRaisin.flavor = "Oatmeal Raisin";
oatmealRaisin.size = 1;

```

- `Cookie chocoChip = new Cookie();` crea una instancia de un nuevo objeto `Cookie` llamado `chocoChip`.
- Usamos el nombre de clase `Cookie` y el constructor predeterminado `new Cookie()`.
- `chocoChip.flavor = "Chocolate Chip";` establece el atributo de sabor de `chocoChip`.
- `chocoChip.size = 2;` establece el atributo de tamaño en `2`.
- Repetimos el proceso para `oatmealRaisin`, creando otro objeto cookie único.

Los objetos `chocoChip` y `oatmealRaisin` son cookies con los mismos métodos definidos por la clase `Cookie`. Sin embargo, contienen diferentes valores de datos para atributos como sabor y tamaño.

Un error común es pensar que los objetos y las clases son iguales. Sin embargo, si bien los objetos y las clases están relacionados, tienen propósitos distintos:

- Las clases definen la estructura del objeto.
- Los objetos representan instancias únicas.

La clase actúa como molde, mientras que los objetos son las galletas que se producen.

### Principios de programación orientada a objetos de nivel superior

Una vez que comprenda los objetos y las clases, será más fácil comprender los principios de nivel superior de la programación orientada a objetos, como la herencia, la encapsulación y el polimorfismo:

- **Herencia** permite la reutilización de código y la creación de jerarquías de clases. Es como tener una receta básica de galletas que sirve como modelo para muchos tipos de galletas. Esta receta básica (la clase principal) incluye ingredientes y métodos comunes (atributos y comportamientos) que comparten todas las cookies. Las recetas especializadas (subclases) para diferentes tipos de galletas, como las con chispas de chocolate o las de avena con pasas, heredan elementos comunes pero también introducen ingredientes o pasos únicos.

- **Encapsulación** implica agrupar atributos y comportamientos de datos en definiciones de clases. Es como envolver la masa para galletas y las instrucciones de la receta en un paquete ordenado. Cada tipo de galleta, como la de chispas de chocolate o la de avena con pasas, tiene su propia caja que contiene todo lo necesario para elaborarla: ingredientes (datos) y pasos (métodos). Este paquete garantiza que todos los secretos para hornear la galleta perfecta se mantengan unidos y sean accesibles solo a través de una abertura específica en la caja.

- **Polimorfismo** permite personalizar comportamientos principales heredados en subclases, como anular el método principal `eat()` dentro de `ChocolateChip` para imprimir `"Mmm, chispas de chocolate!"`.

Para cerrar este círculo, podemos modelar jerarquías de cookies del mundo real a través de:

- **Herencia**: aprovechar las características de las cookies principales y ampliarlas.
- **Encapsulación** - Combinación de ingredientes y recetas de galletas.
- **Polimorfismo** - Personalización de comportamientos como `eat()` por subclase.

Juntos, estos conceptos básicos de programación orientada a objetos permiten un diseño de clases de cookies modular y flexible. Revisaremos estos conceptos con más detalle en el próximo capítulo. Primero, hablemos del ciclo de vida de un objeto.


## Ciclo de vida del objeto en Java

Comprender las diferentes etapas del ciclo de vida de un objeto es esencial en la programación orientada a objetos de Java. Esto incluye la creación de objetos, cómo acceden a ellos las variables de referencia y cómo el recolector de basura de Java gestiona los objetos no utilizados.

A continuación se muestra un diagrama que ilustra el ciclo de vida típico de un objeto Java, desde la creación hasta la recolección de basura:

```

┌────────────────────┐
│   Object Creation  │
│    (new keyword)   │
└────────┬───────────┘
         │
         ▼
┌────────────────────┐
│   Initialization   │
│   (Constructor)    │
└────────┬───────────┘
         │
         ▼
┌───────────────────┐
│     Object Use    │
│ (Active Lifetime) │
└────────┬──────────┘
         │
         ▼
┌────────────────────┐
│     Unreachable    │
│(No more references)│
└────────┬───────────┘
         │
         ▼
┌────────────────────┐
│   Garbage Collect  │
│     (finalize)     │
└────────────────────┘

```

Pero para ilustrar las etapas de vida de un objeto Java, usemos la analogía de un libro de biblioteca. Cuando llega un libro nuevo a la biblioteca, es similar a construir un objeto nuevo usando la palabra clave "nuevo". Por ejemplo:

```java
Book javaBook = new Book("The Java Book");

```

Analicemos paso a paso lo que sucede en esa única línea:

1. **Declaración de la variable de referencia:**

```java
    Book javaBook;

```

Esto declara una variable llamada `javaBook` de tipo `Book`. En este punto, todavía no existe ningún objeto "Libro"; Acabamos de crear una variable de referencia que puede apuntar a un objeto "Libro".

2. **Creación de instancias del objeto:**

```java
    = new Book("The Java Book");

```

La palabra clave `new` crea una instancia o construye un nuevo objeto `Book`. Esto asigna memoria en el montón para el objeto, pasa el argumento de cadena al constructor `Book` para inicializar su estado y devuelve una referencia al objeto recién creado.

3. **Asignación de la referencia:**
    El operador `=` asigna la referencia del nuevo objeto `Book` a la variable `javaBook`. 

Entonces, `javaBook` ahora contiene una referencia que apunta a la nueva instancia de `Book` en la memoria:

```

javaBook --> [New Book object]

```

Aquí, `javaBook` es la variable de referencia que apunta a la instancia `Book` recién creada en el montón de Java.

### Reasignación de referencia

Al igual que los libros de la biblioteca que diferentes personas sacan en préstamo, las referencias a objetos en Java se pueden reasignar. Por ejemplo:

```java
Book refBook = javaBook; // Assign second reference
javaBook = null; // Remove original reference

```

Repasemos esto paso a paso:

1. **Crear una segunda referencia:**

```java
    Book refBook = javaBook;

```

Esto crea una nueva variable de referencia `refBook` y le asigna el valor de `javaBook`. Tanto `javaBook` como `refBook` ahora apuntan al mismo objeto `Book`.

```

javaBook --> [Book object]
    refBook --> [Book object]

```

2. **Anulación de la referencia original:**

```java
    javaBook = null;

```

Esto establece "javaBook" en "nulo", lo que significa que ya no hace referencia a ningún objeto.

```

javaBook --> null
    refBook --> [Book object]

```

Sólo "refBook" ahora apunta al objeto "Libro". El objeto no califica para la recolección de basura porque `refBook` todavía hace referencia a él.

### Recolección de basura

Los libros que ya no se toman prestados eventualmente se eliminan del catálogo de una biblioteca. De manera similar, en Java, el recolector de basura limpia los objetos sin referencias:

```java
refBook = null; // Unreferenced object eligible for garbage collection

```

Cuando desaparecen todas las referencias a un objeto, éste pasa a ser elegible para la recolección de basura. 

El proceso de recolección de basura se puede resumir de la siguiente manera:

1. **Identificación de objetos no utilizados:**
    El recolector de basura (GC) escanea periódicamente el montón para encontrar objetos a los que ya no hace referencia ninguna parte de la aplicación.

2. **Recuperación de memoria:**
    Los objetos sin referencia, a los que ya no se puede acceder, se consideran *basura*. El GC libera la memoria ocupada por estos objetos y la devuelve al grupo de memoria disponible en el montón.

3. **Gestión automática:**
    La recolección de basura ocurre automáticamente en segundo plano, sin que se active un programa explícito, lo que garantiza que la administración de la memoria se maneje de manera eficiente.

En lenguajes como C, la memoria debe administrarse manualmente asignando y liberando memoria. Java automatiza este proceso con recolección de basura, lo que aumenta la productividad del programador y reduce el riesgo de pérdidas de memoria y otros problemas relacionados.

Ahora, analicemos algunos conceptos que usaremos para declarar una clase y otros elementos.



## Palabras clave
En Java, una palabra clave es una palabra reservada que tiene un significado predefinido en el lenguaje. Las palabras clave definen la estructura y la sintaxis de los programas Java. No se pueden utilizar como identificadores (nombres de variables, métodos, clases, etc.) porque están reservados para propósitos específicos.

Java incluye un conjunto de palabras clave fundamentales para el lenguaje. Algunas palabras clave comúnmente utilizadas incluyen:

- `class`: Se utiliza para declarar una clase.
- `public`, `private`, `protected`: modificadores de acceso que determinan la visibilidad y accesibilidad de clases, métodos y variables.
- `static`: Indica que un miembro pertenece a la clase misma en lugar de a instancias de la clase.
- `void`: Especifica que un método no devuelve un valor.
- `if`, `else`, `switch`, `case`: Se utiliza para declaraciones condicionales.
- `for`, ` while`, `do`: se utiliza para bucles e iteraciones.
- `return`: Se utiliza para devolver un valor de un método.
- `new`: Se utiliza para crear nuevas instancias de una clase.
- `try`, `catch`, `finally`: Se utiliza para el manejo de excepciones.
- `import`: Se utiliza para importar clases o paquetes.

Tenga siempre en cuenta que cada palabra clave tiene un propósito específico y se utiliza para definir la estructura y el comportamiento de los programas Java.

Además, es importante tener en cuenta que las palabras clave distinguen entre mayúsculas y minúsculas en Java. Por ejemplo, "clase" es una palabra clave, pero "Clase" no lo es. Además, no puede utilizar palabras clave como identificadores, como nombres de variables o métodos, porque están reservados por el idioma.

A continuación se muestra un ejemplo que demuestra el uso de algunas palabras clave:

```java
public class MyClass {
    private static int myVariable;
    
    public static void myMethod() {
        if (myVariable > 0) {
            System.out.println("Positive");
        } else {
            System.out.println("Negative");
        }
    }
}

```

En este ejemplo, "público", "clase", "privado", "estático", "int", "void", "if" y "else" son palabras clave utilizadas para definir la estructura y el comportamiento de la clase "MyClass".

Revisaremos estas y otras palabras clave en las próximas secciones y capítulos.

## Comentarios
Los comentarios son anotaciones en el código que el compilador ignora. Se pueden utilizar para:
- Describir o explicar qué hace el código.
- Documentar el propósito de bloques de código específicos.
- Explicar la lógica detrás de algoritmos complejos.
- Marcar secciones del código.

Java admite tres tipos de comentarios:

1. Comentarios de una sola línea
2. Comentarios de varias líneas
3. Comentarios de documentación (javadoc)

Los comentarios de una sola línea comienzan con dos barras diagonales (`//`). El compilador de Java ignora todo lo que sigue a `//` en la misma línea:

```java
// This is a single-line comment
int variable = 1; // This is another single-line comment

```

Los comentarios de varias líneas, también conocidos como comentarios de bloque, comienzan con `/*` y terminan con `*/`. Todo lo que esté entre `/*` y `*/` se considera un comentario, independientemente de cuántas líneas abarque:

```java
/* This is a multi-line comment
   and it can span multiple lines. */
int variable = 1;

```

Los comentarios de documentación, o comentarios javadoc, están diseñados para documentar el código Java. Comienzan con `/**` y terminan con `*/`. Estos comentarios se pueden extraer a un documento HTML utilizando la herramienta Javadoc. Los comentarios de la documentación se utilizan principalmente antes de las definiciones de clases, interfaces, métodos y campos:

```java
/**
 * This is a documentation comment.
 * It can be used to describe classes, interfaces, methods, and fields.
 */
public class MyClass {
    /**
     * This method adds up two int values.
     *
     * @param a First value
     * @param b Second value
     * @return The sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }
}

```

## Organizar clases en paquetes
Un paquete organiza clases, interfaces y subpaquetes relacionados en una sola unidad.

Por ejemplo, imagina que eres dueño de una tienda de comestibles que vende muchos tipos de productos. Para mantener todo organizado y fácil de encontrar, decide agrupar productos similares en diferentes secciones o pasillos de la tienda.

En esta analogía:
- La tienda de comestibles representa tu proyecto Java.
- Las secciones o pasillos de la tienda representan paquetes en Java.
- Los productos en los estantes representan clases e interfaces en Java.

Al igual que agrupa productos relacionados en la misma sección de la tienda, agrupa clases e interfaces relacionadas en el mismo paquete en Java. 

Por ejemplo, en tu supermercado podrías tener:
- Una sección de *Frutas* donde colocas todos los diferentes tipos de frutas como manzanas, plátanos y naranjas.
- Una sección de *Lácteos* para leche, queso, yogur y otros productos lácteos.
- Una sección de *Bebidas* para diversas bebidas como agua, jugo y refrescos.

De manera similar, en su proyecto Java, puede tener:
- Un paquete `com.example.products` para clases relacionadas con la gestión de productos, como `Producto`, `Inventario` y `Categoría`.
- Un paquete `com.example.orders` para clases relacionadas con el procesamiento de pedidos, como `Order`, `ShoppingCart` y `Payment`.
- Un paquete `com.example.auth` para clases relacionadas con la autenticación de usuarios, como `Usuario`, `Inicio de sesión` y `Permiso`.

Aquí hay una representación visual de estos paquetes y clases:

```

┌─────────────────────────────────────────────────────────────┐
│                      com.example                            │
│  ┌─────────────────────────┐  ┌─────────────────────────┐   │
│  │       products          │  │         orders          │   │
│  │  ┌─────────────────┐    │  │  ┌─────────────────┐    │   │
│  │  │  Product.java   │    │  │  │  Order.java     │    │   │
│  │  └─────────────────┘    │  │  └─────────────────┘    │   │
│  │  ┌─────────────────┐    │  │  ┌─────────────────┐    │   │
│  │  │ Inventory.java  │    │  │  │ShoppingCart.java│    │   │
│  │  └─────────────────┘    │  │  └─────────────────┘    │   │
│  │  ┌─────────────────┐    │  │  ┌─────────────────┐    │   │
│  │  │ Category.java   │    │  │  │  Payment.java   │    │   │
│  │  └─────────────────┘    │  │  └─────────────────┘    │   │
│  └─────────────────────────┘  └─────────────────────────┘   │
│                                                             │
│  ┌─────────────────────────┐                                │
│  │         auth            │                                │
│  │  ┌─────────────────┐    │                                │
│  │  │   User.java     │    │                                │
│  │  └─────────────────┘    │                                │
│  │  ┌─────────────────┐    │                                │
│  │  │   Login.java    │    │                                │
│  │  └─────────────────┘    │                                │
│  │  ┌─────────────────┐    │                                │
│  │  │ Permission.java │    │                                │
│  │  └─────────────────┘    │                                │
│  └─────────────────────────┘                                │
└─────────────────────────────────────────────────────────────┘

```

Al organizar sus clases en paquetes, crea una estructura lógica que facilita la localización y administración de elementos de código relacionados, de la misma manera que la organización de productos en secciones facilita que los clientes encuentren lo que necesitan en el supermercado.

### Creando un paquete

Para crear un paquete, use la palabra clave `paquete` seguida del nombre del paquete en la parte superior de su archivo fuente de Java. Por ejemplo:

```java
package com.example.mypackage;

```

El nombre del paquete debe estar en minúsculas y seguir la convención de nombre de dominio inversa para garantizar la unicidad.

El nombre del paquete no debe tener ninguna de las palabras reservadas de Java (como `int`, `if`, `for`, etc.)

La declaración del paquete debe ser la primera declaración en el archivo fuente, antes de cualquier declaración de importación o declaración de clase. Lo siguiente no se compilará:

```java
import java.util.ArrayList; // Import statement before the package declaration

package mypackage; // Package declaration not at the beginning

public class MyClass {
    public static void main(String[] args) {
        System.out.println("This will not compile.");
    }
}

```

### Uso de declaraciones de importación

Las declaraciones `import` se utilizan para traer clases o interfaces de otros paquetes al espacio de nombres actual. En lugar de usar el nombre completo cada vez que hace referencia a una clase de otro paquete, puede usar una declaración `import` para hacer referencia a la clase por su nombre. Por ejemplo:

```java
import java.util.ArrayList;
// ...
ArrayList list = new ArrayList();

```

Si elige no usar una declaración `import` para una clase de otro paquete, deberá usar el nombre completo de la clase cada vez que haga referencia a ella en su código. Recuerde, el nombre completo incluye tanto el nombre del paquete como el nombre de la clase.

Por ejemplo, si no importa la clase `ArrayList` del paquete `java.util`, tendrá que usar `java.util.ArrayList` cada vez que quiera crear o usar un objeto `ArrayList` en su código:

```java
// No import statement for java.util.ArrayList
// ...
java.util.ArrayList list = new java.util.ArrayList();

```

### Casos especiales y mejores prácticas

Hay un par de excepciones o casos especiales a la regla con respecto al uso de nombres completos y declaraciones de importación:

1. **Clases en el paquete `java.lang`**: No es necesario importar explícitamente las clases e interfaces en el paquete `java.lang`, ya que están disponibles automáticamente. Por ejemplo, no necesita importar clases como `String`, `Math`, `System` o clases contenedoras como `Integer`, `Double`, etc.

2. **Mismo paquete**: las clases e interfaces que están en el mismo paquete que la clase que estás escribiendo no requieren una declaración de importación. Java busca automáticamente en el paquete actual otras clases e interfaces si no encuentra la clase o interfaz a la que se hace referencia en los paquetes importados.

3. **Colisión de nombres totalmente calificados**: cuando dos clases tienen el mismo nombre pero están en paquetes diferentes y necesita usar ambas en el mismo archivo, no puede importar ambas directamente debido a la colisión de nombres. En tales casos, se debe hacer referencia a al menos uno (y posiblemente a ambos) por sus nombres completos para evitar ambigüedades.

Aquí hay un ejemplo para ilustrar este último punto:

```java
import java.sql.Date;

public class Example {
    public static void main(String[] args) {
        Date sqlDate = new Date(System.currentTimeMillis());
        java.util.Date utilDate = new java.util.Date();
    }
}

```

En este ejemplo, se importa "Fecha" de "java.sql", por lo que se puede hacer referencia a ella por su nombre simple. Sin embargo, dado que también queremos utilizar `Date` de `java.util`, debemos referirnos a él por su nombre completo para distinguirlo de `java.sql.Date`.

También puede utilizar un comodín (`*``) para importar todas las clases de un paquete. Por ejemplo:

```java
import java.util.*;

```

Sin embargo, generalmente se recomienda importar clases específicas en lugar de usar comodines porque pueden hacer que el código sea menos legible, generar conflictos de nombres si varios paquetes tienen clases con el mismo nombre y agregar redundancia, como incluir una clase dos veces.

### Importaciones redundantes

Aunque el compilador permite importaciones redundantes, pueden saturar el código y reducir la legibilidad.

Por ejemplo, suponiendo que tenemos dos clases, `MyClass` y `HelperClass`, en el mismo paquete, `mypackage`:

```java
// File: HelperClass.java
package mypackage;

public class HelperClass {
    public static void doSomething() {
        System.out.println("Doing something...");
    }
}

```

La siguiente clase ilustra importaciones redundantes:

```java
package mypackage;

import mypackage.HelperClass; // Redundant import because HelperClass is in the same package
import java.util.List; // Redundant import because it's not used in the class

public class MyClass {
    public static void main(String[] args) {
        HelperClass.doSomething();
    }
}

```

En este ejemplo:
- La declaración de importación `import mypackage.HelperClass;` es redundante porque `HelperClass` ya está en el mismo paquete que `MyClass`. Recuerde, las clases del mismo paquete están automáticamente disponibles entre sí sin necesidad de declaraciones de importación.
- La declaración de importación `import java.util.List;` también es redundante porque la interfaz `List` no se usa en ninguna parte de `MyClass`.

Eliminar estas importaciones redundantes haría que el código fuera más limpio sin afectar su funcionalidad.

### Control de acceso

Los paquetes proporcionan un nivel de control de acceso, similar a cómo ciertas secciones de la tienda podrían estar restringidas únicamente al personal autorizado. Puede utilizar modificadores de acceso (`public`, `protected`, predeterminado, `private`) para controlar la visibilidad y accesibilidad de clases y miembros dentro y entre paquetes.

Por ejemplo, digamos que tienes un paquete llamado `com.example.internals` que contiene clases y métodos destinados para uso interno solo dentro de ese paquete:

```java
package com.example.internals;
class InternalClass {
    void internalMethod() {
        // Internal implementation
    }
}

```

Ahora, considere otro paquete `com.example.api`:

```java
package com.example.api;
import com.example.internals.InternalClass;
public class APIClass {
    public void someMethod() {
        InternalClass obj = new InternalClass(); // Not accessible
        obj.internalMethod(); // Not accessible
    }
}

```

En este ejemplo, `InternalClass` y sus métodos tienen acceso predeterminado (paquete privado). Se puede acceder a ellos dentro del paquete `com.example.internals` pero no desde otros paquetes. La `APIClass` en el paquete `com.example.api` no puede acceder a la `InternalClass` ni a sus métodos directamente.

Repasemos con más detalle los modificadores de acceso disponibles.


## Modificadores de acceso
Los modificadores de acceso son palabras clave utilizadas en clases, métodos o declaraciones de variables para controlar la visibilidad de ese miembro desde otras partes del programa. Hay cuatro tipos principales de modificadores de acceso en Java:

1. **`public`**: El modificador de acceso `public` especifica que se puede acceder al miembro desde cualquier otra clase en la aplicación Java, independientemente del paquete al que pertenezca. El uso del modificador "público" significa que no hay restricciones para acceder al miembro.

2. **`protected`**: El modificador de acceso `protected` permite acceder al miembro dentro de su propio paquete y también mediante subclases de su clase en otros paquetes. Esto es menos restrictivo que el paquete privado pero más restrictivo que el "público".

3. **`default`** (también conocido como **paquete privado**): si no se especifica ningún modificador de acceso, el miembro tiene acceso privado al paquete de forma predeterminada. Esto significa que solo se puede acceder al miembro dentro de su propio paquete y no es visible para clases fuera del paquete. Es importante tener en cuenta que no existe una palabra clave "predeterminada" explícita en Java; simplemente omite el modificador de acceso.

4. **`private`**: El modificador de acceso `private` especifica que se puede acceder al miembro solo dentro de la clase en la que está declarado. Es el nivel de acceso más restrictivo y se utiliza para garantizar que no se pueda acceder al miembro desde fuera de su propia clase, ni siquiera mediante subclases.

Cada uno de estos modificadores de acceso tiene un propósito específico en el contexto del diseño y encapsulación orientado a objetos. Le permiten estructurar su código de una manera que proteja los datos confidenciales y los detalles de implementación al tiempo que expone la funcionalidad necesaria a otras partes de su aplicación.

Aquí hay un diagrama para comprender el alcance de cada modificador de acceso más fácilmente:

```

┌─────────────────────────────────────────────────────────────┐
│                         public                              │
│  ┌─────────────────────────────────────────────────┐        │
│  │               protected                         │        │
│  │  ┌─────────────────────────────────────┐        │        │
│  │  │    default (package-private)        │        │        │
│  │  │  ┌─────────────────────────┐        │        │        │
│  │  │  │      private            │        │        │        │
│  │  │  └─────────────────────────┘        │        │        │
│  │  └─────────────────────────────────────┘        │        │
│  └─────────────────────────────────────────────────┘        │
└─────────────────────────────────────────────────────────────┘

Access Levels (from most restrictive to least restrictive):
private   : Same class only
default   : Same package
protected : Same package + subclasses in other packages
public    : Accessible from anywhere

```

En las siguientes secciones, explicaremos los modificadores de acceso en el contexto de clases, campos y métodos. Pero primero, revisemos cómo declarar una clase correctamente.

## Declarando clases

Una clase en Java actúa como modelo para objetos, encapsulando tanto datos como comportamiento.

La sintaxis para declarar una clase sigue este formato:

```java
[accessModifier] class ClassName [extends Superclass] [implements Interface1, Interface2, ...] {
    // class body
}

```

Por ejemplo, una declaración de clase podría verse así:

```java
public class MyClass extends MySuperClass implements MyInterface {
    private int myField;

    public MyClass() {
        // Constructor body
    }

    public void myMethod() {
        // Method body
    }
}

```

En primer lugar, opcionalmente puede especificar un modificador de acceso para determinar la visibilidad y accesibilidad de la clase a otras partes de una aplicación Java:

- **`public`**: Se puede acceder a la clase desde cualquier otra clase a través de diferentes paquetes.
- **Predeterminado (Paquete-Privado)**: Si no se especifica ningún modificador de acceso, solo otras clases dentro del mismo paquete pueden acceder a la clase. Esto es útil para agrupar clases relacionadas sin exponerlas a toda la aplicación.

Después del modificador de acceso opcional, debes usar la palabra clave `class`, seguida del nombre de la clase.

Un nombre de clase o un identificador de clase debe seguir las siguientes reglas:

1. **Caracteres Unicode**: Java permite el uso de caracteres Unicode en identificadores, lo que significa que también puedes usar letras de alfabetos no latinos. Sin embargo, esto no se usa comúnmente y puede generar código difícil de leer y mantener.

2. **Caracteres alfabéticos, dígitos, guiones bajos (_) y signos de dólar ($)**: estos son los caracteres más comunes utilizados en los identificadores. Se permite cualquier combinación de estos caracteres, pero los nombres de clase **no deben** comenzar con un dígito.

3. **Sin caracteres especiales**: aparte de guiones bajos y signos de dólar, caracteres especiales como `@`, `%`, `!`, `?`, `#`, `&`, `*`, `^`, `~`, `_`, `-`, `+`, `=`, `{`, `}`, `[`, `]`, `|`, `,`, `;`, `<`, `>`, `/`, `\` o `'` no están permitidos en los identificadores de clase.

4. **Los nombres de las clases no deben contener espacios**. Esto invalidaría el código y provocaría errores de compilación.

5. **No puede ser una palabra reservada de Java**: los identificadores no pueden utilizar ninguna de las palabras reservadas de Java (como `int`, `if`, `for`, etc.). Las palabras reservadas tienen significados específicos en Java y no se pueden utilizar para nombres de clases, nombres de variables ni ningún otro identificador.

6. **Sensibilidad entre mayúsculas y minúsculas**: Java distingue entre mayúsculas y minúsculas, lo que significa que identificadores como `MyClass`, `myclass` y `MYCLASS` se considerarán diferentes.

7. **Longitud**: No hay límite de longitud para los nombres de clases en Java.

Estas reglas garantizan que los nombres de las clases sean sintácticamente correctos y eviten conflictos con las funciones integradas del lenguaje Java. También es una buena práctica seguir las convenciones de nomenclatura de Java además de estas reglas, como comenzar los nombres de las clases con una letra mayúscula y usar mayúsculas y minúsculas para nombres de varias palabras (como usar `MyClass` en lugar de `myclass` o `MY_CLASS`). Pero repito, esto es sólo una convención, no una regla.

Después del nombre de la clase, opcionalmente puedes extender una superclase usando la palabra clave `extends`, seguida del nombre de la superclase. Java admite herencia única, lo que significa que una clase sólo puede extender una superclase.

Sin embargo, puede implementar una o más interfaces usando la palabra clave `implements`, seguida de una lista de nombres de interfaces separados por comas:

```java
public class MyClass implements MyInterface1, MyInterface2, MyInterface3 {
    // ...
}

```

Finalmente, define el cuerpo de la clase dentro de un par de llaves `{}`. El cuerpo de la clase contiene los miembros de la clase, incluidos campos, métodos, constructores y clases anidadas.

De esta manera, en el siguiente ejemplo:

```java
public class MyClass extends MySuperClass implements MyInterface {
    /* Class body begins */
    // Fields
    private int myField;

    // Constructor
    public MyClass() {
        // Constructor body
    }

    // Methods
    public void myMethod() {
        // Method body
    }
    /* Class body ends */
}

```

- `public` es el modificador de acceso, que indica que se puede acceder a la clase desde cualquier lugar.
- `class` es la palabra clave utilizada para declarar una clase.
- `MyClass` es el nombre de la clase.
- `extiende MySuperClass` especifica que `MyClass` hereda de la superclase `MySuperClass`.
- `implementa MyInterface` indica que `MyClass` implementa la interfaz `MyInterface`.
- El cuerpo de la clase contiene un campo "privado" "myField", un constructor "público" "MyClass()" y un método "público" "myMethod()".

Ahora, antes de revisar con más detalle cómo declarar campos y métodos, hablemos de miembros estáticos y de instancia.

## Miembros estáticos y de instancia
Las clases pueden tener dos tipos de miembros: miembros estáticos y miembros de instancia. Usemos la analogía de un modelo de televisión para comprender mejor a este tipo de miembros.

Imagínese diferentes televisores del mismo modelo en diferentes hogares. Cada televisor representa una instancia (objeto) de la clase "Televisión". El modelo de TV en sí representa la clase.

Los miembros de instancia, como las variables de instancia y los métodos de instancia, pertenecen a cada televisor (objeto) individual:
- Cada televisor tiene su propio conjunto de variables de instancia, como su canal actual, volumen y si está encendido o apagado.
- Los métodos de instancia, como `changeChannel()` o `adjustVolume()`, son acciones que cada televisor puede realizar de forma independiente.
- Se accede a los miembros de la instancia utilizando la instancia (objeto) de la clase.

Los miembros estáticos, como las variables estáticas y los métodos estáticos, pertenecen al propio modelo (clase) de TV:
- El modelo de TV tiene variables estáticas que se comparten entre todos los televisores, como el logo del fabricante o el número de modelo.
- Los métodos estáticos, como `getManufacturerInfo()` o `getModelNumber()`, son acciones que pertenecen al modelo de TV y se puede acceder a ellas sin crear una instancia de la clase `Television`.
- Se accede a los miembros estáticos utilizando el nombre de la clase en sí, sin necesidad de crear una instancia.

Aquí está la clase "Televisión":

```java
public class Television {
    // Instance fields
    private int currentChannel;
    private int volume;
    private boolean isOn;
    
    // Static field
    private static String manufacturerLogo = "MyBrand";
    
    // Instance method
    public void changeChannel(int channel) {
        this.currentChannel = channel;
        System.out.println("Channel changed to: " + channel);
    }
    
    // Static method
    public static void getManufacturerInfo() {
        System.out.println("All TVs by: " + manufacturerLogo);
    }
}

```

En este ejemplo:
- Los campos `currentChannel`, `volume` e `isOn` son variables de instancia. Cada televisor (objeto) tiene su propio conjunto de estas variables.
- El campo `manufacturerLogo` es una variable `estática`. Pertenece a la propia clase y se comparte entre todos los televisores.
- El método `changeChannel()` es un método de instancia. Cada televisor puede invocar este método de forma independiente.
- El método `getManufacturerInfo()` es un método estático. Pertenece a la clase y se puede invocar sin crear una instancia de la clase "Televisión".

Para acceder a los miembros de la instancia, necesita crear una instancia de la clase:

```java
Television tv1 = new Television();
tv1.changeChannel(5); // Changes channel of tv1

```

Pero para acceder a miembros estáticos, puedes usar el nombre de la clase directamente:

```java
Television.getManufacturerInfo();

```

Los miembros estáticos son útiles para representar datos y comportamientos a nivel de clase que se comparten entre todas las instancias de la clase. Se puede acceder a ellos sin crear una instancia de la clase, lo que los hace eficientes en memoria. Sin embargo, los miembros estáticos no pueden acceder a los miembros de la instancia directamente, ya que no están asociados con ninguna instancia específica.

Es importante tener en cuenta que Java permite acceder a miembros estáticos (campos y métodos) a través de instancias de una clase. Por ejemplo, el método estático `getManufacturerInfo()` también se puede utilizar de esta manera:

```java
tv1.getManufacturerInfo();

```

Sin embargo, esta no es una práctica recomendada, ya que no transmite claramente que el miembro es estático y pertenece a la clase y no a la instancia.

Los miembros de instancia, por otro lado, están asociados con cada instancia individual de la clase. Contienen datos específicos de cada objeto y pueden acceder tanto a miembros estáticos como a miembros de instancia.

Ahora quizás estés pensando: ¿Por qué se puede acceder a miembros estáticos sin crear una instancia de la clase? ¿No va esto en contra de la idea de la programación orientada a objetos?

Bueno, esto no necesariamente va en contra de los principios de la programación orientada a objetos (POO), sino que los complementa al proporcionar un mecanismo para definir el comportamiento y el estado a nivel de clase.

Los métodos estáticos se pueden utilizar para implementar funciones de utilidad o auxiliares que no dependen del estado de una instancia de objeto. Esto es común en clases de utilidad, como la clase "Math", donde todos los métodos son estáticos porque no requieren acceso a datos a nivel de instancia.

Además, los miembros estáticos permiten el acceso global. Por supuesto, existe cierta controversia sobre esto debido al potencial de un mayor acoplamiento y un código más difícil de probar; sin embargo, puede ser apropiado para constantes globales a las que se debe acceder desde varios puntos de una aplicación.

Este diagrama ilustra varios puntos clave sobre los miembros estáticos y de instancia en Java:

```

┌─────────────────────────────────────────────────────────────┐
│                         Class                               │
│  ┌─────────────────────────┐ ┌─────────────────────────┐    │
│  │    Static Members       │ │    Instance Members     │    │
│  │ ┌─────────────────────┐ │ │ ┌─────────────────────┐ │    │
│  │ │   Static Fields     │ │ │ │  Instance Fields    │ │    │
│  │ └─────────────────────┘ │ │ └─────────────────────┘ │    │
│  │ ┌─────────────────────┐ │ │ ┌─────────────────────┐ │    │
│  │ │   Static Methods    │ │ │ │  Instance Methods   │ │    │
│  │ └─────────────────────┘ │ │ └─────────────────────┘ │    │
│  └─────────────────────────┘ └─────────────────────────┘    │
│                                                             │
│  ┌─────────────────────────┐ ┌─────────────────────────┐    │
│  │      Object 1           │ │      Object 2           │    │
│  │ ┌─────────────────────┐ │ │ ┌─────────────────────┐ │    │
│  │ │  Instance Fields    │ │ │ │  Instance Fields    │ │    │
│  │ └─────────────────────┘ │ │ └─────────────────────┘ │    │
│  └─────────────────────────┘ └─────────────────────────┘    │
└─────────────────────────────────────────────────────────────┘

- The class contains both static and instance members.
- Static members (fields and methods) are associated with the class itself.
- Instance members (fields and methods) are associated with objects of the class.
- Multiple objects of the class each have their own instance members.
- All objects share the same static members.

```

Ahora, revisemos con más detalle cómo declarar campos.

## Declaración de campos
Un campo es una variable que se declara a nivel de clase. Los campos, también denominados atributos o variables de instancia, se utilizan para mantener el estado de un objeto.

Para declarar un campo, utiliza la siguiente sintaxis:

```

[accessModifier] [specifiers] type fieldName [= initialValue];

```

A continuación se muestran algunos ejemplos:

```java
public class MyClass {
    public static final int MAX_VALUE = 100;
    private String name;
    protected double salary;
    boolean active = true;
    
    // ...
}

```

El modificador de acceso es opcional y puede ser acceso "público", "privado", "protegido" o predeterminado (paquete-privado) si no se especifica ninguno. Tenga en cuenta que, a diferencia de las clases, los campos pueden utilizar los cuatro tipos de modificadores de acceso. Dependiendo de los modificadores de acceso utilizados, se puede acceder a los campos desde dentro de la clase, subclases, clases en el mismo paquete o cualquier otra clase. Más sobre esto más adelante.

La parte de especificadores también es opcional y puede incluir palabras clave como "estática", "final", "transitoria" y "volatil". Puede especificar cero o más especificadores (como en la declaración del primer campo), pero la palabra clave "final" solo se puede aplicar una vez:

- Un campo declarado como "estático" pertenece a la clase misma, no a una instancia en particular. Solo habrá una copia de un campo "estático" compartida por todas las instancias de la clase. Un uso común de los campos "estáticos" es definir constantes.
- Un campo declarado como `final` no se puede reasignar para hacer referencia a un objeto o valor diferente. Si es un tipo primitivo, el valor no se puede modificar. Si es un tipo de referencia, la referencia no se puede cambiar para que apunte a otro objeto, pero el estado interno del objeto se puede alterar si es mutable. Los campos finales se pueden utilizar para constantes o para hacer que los campos sean de solo lectura después de la inicialización. Sin embargo, aunque el campo en sí se vuelve de solo lectura, los objetos a los que hacen referencia los campos finales aún pueden cambiar su estado interno si son mutables.
- Las palabras clave "transient" y "volatile" son más avanzadas y se relacionan con la serialización y el subproceso múltiple. Los cubriremos en capítulos posteriores.

El tipo de campo sigue los especificadores. Puede ser un tipo primitivo como `int`, `boolean`, etc. o un tipo de referencia como `String`, `LocalDate`, `ArrayList`, etc.  

El nombre del campo sigue las reglas estándar de nomenclatura de identificadores de Java. Estas son las reglas principales que debe recordar para los identificadores de campo:

1. **Caracteres Unicode**: Java permite caracteres Unicode en los identificadores, lo que significa que puede utilizar caracteres de conjuntos de caracteres no latinos. Sin embargo, esto no se usa comúnmente para nombres de campos, ya que puede dificultar la lectura y el mantenimiento del código.

2. **Caracteres permitidos**: Los identificadores de campo solo pueden incluir caracteres alfanuméricos (`A-Z`, `a-z`, `0-9`), guión bajo (`_`) y signo de dólar ($`). El identificador debe comenzar con una letra (`A-Z` o `a-z`), un guión bajo (`_`) o un signo de dólar ($`). No puede comenzar con un dígito.

3. **Sin palabras reservadas**: los identificadores no pueden ser palabras reservadas de Java. Las palabras reservadas incluyen palabras clave como "int", "if", "clase", etc. Estos son parte de la sintaxis del lenguaje Java y tienen significados específicos para el compilador.

4. **Sensibilidad entre mayúsculas y minúsculas**: Java distingue entre mayúsculas y minúsculas, lo que significa que identificadores como `myField`, `MyField` y `MYFIELD` se considerarían distintos.

5. **Longitud ilimitada**: Técnicamente, no hay límite para la longitud de un identificador, pero es esencial mantenerlo razonable para facilitar la lectura y el mantenimiento.

Es importante diferenciar entre reglas y convenciones. Se deben seguir reglas para compilar el código Java, mientras que las convenciones, como comenzar los nombres de los campos con una letra minúscula o usar "camelCase" para varias palabras, son mejores prácticas diseñadas para hacer que el código sea más legible y fácil de mantener, pero el compilador no las aplica.

Finalmente, proporcionar un valor inicial es opcional. Si no se proporciona ninguno, los campos se inicializarán con sus valores predeterminados (`0`, `false` o `null` según el tipo). Sin embargo, el valor inicial debe ser una constante en tiempo de compilación para los campos finales estáticos.

Una vez declarado un campo, puedes acceder a él para leer su valor o modificarlo asignándole un nuevo valor. La forma de acceder a un campo depende de si es un campo de instancia o un campo estático y qué modificador de acceso utiliza.

### Acceso y modificación de campos
Para acceder a un campo de instancia, primero necesita una instancia de la clase. Luego puede leer el valor del campo usando el operador de punto (`.`) de esta manera:

```java
instanceVariable.fieldName

```

Por ejemplo:

```java
String name = person.firstName;
int age = employee.age;

```

Para modificar un campo de instancia, utiliza el operador de asignación (`=`) de esta manera:

```java
person.firstName = "John";
employee.age = 45;

```

Acceder a campos "estáticos" es un poco diferente. Como pertenecen a la clase misma, no necesitas una instancia. Puede acceder a un campo estático utilizando el nombre de la clase y el operador de punto:

```java
ClassName.fieldName

```

Por ejemplo:

```java
double pi = Math.PI;
int max = Integer.MAX_VALUE;

```

Dentro de la misma clase que declara un campo, puedes acceder a él directamente por su nombre, sin ningún prefijo, independientemente del modificador de acceso utilizado. La única excepción es acceder a un campo estático; se recomienda utilizar el nombre de la clase incluso dentro de la misma clase para facilitar la lectura.

Los modificadores de acceso "público", "privado", "protegido" y predeterminado (paquete) controlan la visibilidad de un campo y determinan si se puede acceder a él directamente desde fuera de la clase.

Veamos algunos ejemplos para ilustrar los diferentes niveles de acceso.

```java
public class Person {
    public String name;
    private int age;
    protected String email;
    double height;
}

```

El campo `nombre` es `public`, por lo que se puede acceder a él desde cualquier otra clase:

```java
Person p = new Person();
p.name = "Alice";

```

El campo "edad" es "privado". Solo se puede acceder a él dentro de la clase "Persona". Intentar acceder a él directamente desde fuera de la clase dará como resultado un error de compilación:

```java
// This will not compile
p.age = 30;

```

El campo "correo electrónico" está "protegido". Se puede acceder a él dentro de la misma clase, cualquier subclase y otras clases en el mismo paquete:

```java
// This is okay
String email = p.email;

// This is also valid in a subclass, even in a different package
class Employee extends Person {
    public void setEmail(String e) {
         email = e;
    }
}

```

El campo `altura` tiene acceso predeterminado (paquete) ya que no se especifica ningún modificador. Pueden acceder a él otras clases dentro del mismo paquete:

```java
// This is okay if Person and Student are in same package 
class Student {
    public void printHeight(Person p) {
        System.out.println(p.height);
    }
}

```

Es común declarar campos como "privados" y acceder a ellos mediante métodos getter y setter. Los campos "público" y "protegido" se utilizan con menos frecuencia. El acceso predeterminado (paquete privado) es útil para clases relacionadas dentro del mismo paquete.


## Métodos de declaración
Un método es un bloque de código que realiza una tarea específica y, opcionalmente, devuelve un valor. Los métodos se utilizan para definir el comportamiento de un objeto. Proporcionan una manera de encapsular lógica compleja, dividir un programa en partes manejables y permitir la reutilización de código.

Para declarar un método, utilice la siguiente sintaxis:

```java
[accessModifier] [specifiers] returnType methodName([parameters]) [throws ExceptionType1, ExceptionType2, ...] {
    // method body
}

```

Por ejemplo:

```java
public static String addParenthesis(String s) {
    return "(" + s + ")";
}

private int sum(int a, int b) {
    return a + b;
}

protected void setName(String name) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
        throw new IllegalArgumentException("Name cannot be null or empty");
    }
    this.name = name;
}

```

El modificador de acceso es opcional y controla la visibilidad del método. Puede ser acceso "público", "privado", "protegido" o predeterminado (paquete) si no se especifica ninguno. Se aplican las mismas reglas que para los campos, que comentamos anteriormente.

Los especificadores también son opcionales y pueden incluir palabras clave como "estático", "final", "abstracto" y "sincronizado". Estas palabras clave modifican el comportamiento del método:

- Los métodos `estáticos` pertenecen a la clase misma y se pueden llamar sin una instancia de la clase.
- Los métodos `final` no pueden ser anulados por subclases.
- Los métodos `abstractos` no tienen implementación en la clase actual y deben ser anulados por subclases no abstractas.
- Los métodos `sincronizados` solo pueden ser ejecutados por un hilo a la vez.

El tipo de retorno especifica el tipo de valor que devuelve el método. Puede ser un tipo primitivo, un tipo de referencia o nulo si el método no devuelve nada. Cada declaración de método debe tener un tipo de retorno.

El nombre del método sigue las mismas convenciones de nomenclatura que las clases y los campos, normalmente utilizando "camelCase". Elija nombres significativos que describan el propósito del método.

Los parámetros se especifican entre paréntesis después del nombre del método. Puede haber cero o más parámetros. Varios parámetros están separados por comas. Los parámetros son variables que reciben los valores pasados ​​al método cuando se llama. Cada parámetro consta de dos partes, opcionalmente tres:

```java
[parameterModifier] parameterType parameterName

```

El modificador de parámetros es opcional y puede ser "final". Si un parámetro se declara como "final", significa que el valor del parámetro no se puede cambiar dentro del cuerpo del método. He aquí un ejemplo:

```java
public void printMessage(final String message) {
    // message = "Hello"; // This would cause a compile error
    System.out.println(message);
}

```

El tipo de parámetro es obligatorio y especifica el tipo de datos del parámetro. Puede ser un tipo primitivo (como `int`, `double`, `boolean`) o un tipo de referencia (como `String`, `ArrayList` o clases personalizadas).

El nombre del parámetro también es obligatorio y sigue las mismas convenciones de nomenclatura que los identificadores de clases, campos y métodos, normalmente utilizando "camelCase". El nombre del parámetro se utiliza para hacer referencia al valor pasado dentro del cuerpo del método.

A continuación se muestran algunos ejemplos de definiciones de parámetros:

```java
// A single parameter of type int
public void printNumber(int number) {
    System.out.println("The number is: " + number);
}

// Multiple parameters of different types
public void printPersonDetails(String name, int age, boolean isStudent) {
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Is a student? " + isStudent);
}

// A parameter with a modifier
public void calculateDiscount(final double price, double discountPercentage) {
    double discountAmount = price * (discountPercentage / 100);
    double finalPrice = price - discountAmount;
    System.out.println("Discounted price: " + finalPrice);
}

```

Volviendo a las partes de la declaración de un método, la cláusula `throws` es opcional y especifica cualquier excepción marcada que el método pueda generar. Varias excepciones están separadas por comas.

El cuerpo del método está entre llaves `{}` y contiene el código que implementa la funcionalidad del método. Puede incluir declaraciones de variables, bucles, condicionales, llamadas a métodos y otras declaraciones.

Si el método tiene un tipo de retorno distinto de nulo, debe incluir una declaración "return" que especifique el valor que se devolverá. El valor de retorno debe ser compatible con el tipo de retorno declarado:

```java
// A simple method that returns a string
public String getName() {
    return "Mark";
}

```

### Firmas de métodos
Una firma de método identifica de forma única un método dentro de una clase. Consiste en el nombre del método y la lista ordenada de tipos de parámetros. Los modificadores de acceso (como "público" o "privado"), los tipos de retorno (como "void" o "int") y los nombres de los parámetros no forman parte de la firma del método:

```java
methodName(parameterType1, parameterType2, ...)

```

Por ejemplo, considere las siguientes declaraciones de métodos:

```java
public void printMessage(String message) {
    System.out.println(message);
}

public int calculateSum(int a, int b) {
    return a + b;
}

private void updateUser(String username, int age, boolean isActive) {
    // method body
}

```

Las firmas de métodos para estos métodos son:

- `imprimirMensaje(Cadena)`
- `calcularSuma(int, int)`
- `actualizarUsuario(Cadena, int, booleano)`

### Llamar a un método
Al llamar a un método, se pasan argumentos que coinciden con los tipos y el orden de los parámetros declarados en la firma del método. Los argumentos son los valores reales que se pasan al método.

De esta manera, para llamar a un método, debe utilizar el nombre del método seguido de paréntesis y proporcionar los argumentos necesarios. La sintaxis es:

```java
[ObjectReference.]methodName([arguments]);

```

Si el método es un método de instancia (no estático), necesita tener un objeto de la clase que contiene el método. Luego puede llamar al método usando la referencia del objeto seguida del operador de punto y el nombre del método.

Si el método es un método "estático", puede llamarlo directamente usando el nombre de la clase seguido del operador de punto y el nombre del método. No necesita una instancia de objeto para llamar a un método estático.

A continuación se muestran algunos ejemplos de métodos de llamada:

```java
// Calling an instance method
Person person = new Person();
person.setName("John");
String name = person.getName();

// Calling a static method
int max = Math.max(10, 20);
double random = Math.random();

// Calling a method with arguments
Calculator calculator = new Calculator();
int sum = calculator.add(5, 3);
double result = calculator.multiply(2.5, 4.0);

```

Asegúrese de proporcionar el número y tipo correcto de argumentos según lo definido en la firma del método. Si hay una discrepancia, el compilador arrojará un error.

### Uso de modificadores de acceso con métodos
Al igual que con los campos, los modificadores de acceso controlan la visibilidad y accesibilidad de los métodos. Se pueden utilizar los mismos cuatro modificadores de acceso: "público", "privado", "protegido" y predeterminado (paquete-privado).

Considere esta clase:

```java
package com.my.package;

public class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }

    private static int subtract(int a, int b) {
        return a - b;
    }

    protected static int multiply(int a, int b) {
        return a * b;
    }

    static int divide(int a, int b) {
        return a / b;
    }
}

```

El método `add` está declarado como `public`, por lo que se puede llamar desde cualquier otra clase:

```java
int sum = MathUtils.add(1, 2);

```

El método "restar" se declara como "privado". Sólo se puede llamar desde la propia clase `MathUtils`. Intentar llamarlo desde otra clase resultará en un error de compilación:

```java
// This will not compile
int difference = MathUtils.subtract(10, 7);

```

El método "multiplicar" se declara como "protegido". Se puede llamar desde dentro de la misma clase, cualquier subclase (incluso en un paquete diferente) y otras clases en el mismo paquete:

```java
package com.my.other.package;

// Calling from a subclass in a different package
public class AdvancedMathUtils extends MathUtils {
    public static int square(int a) {
        return multiply(a, a);
    }
}

```

El método `divide` tiene acceso predeterminado (paquete privado) ya que no se especifica ningún modificador explícito. Recuerde, esto significa que solo las clases dentro del mismo paquete pueden acceder al método:

```java
package com.my.package;

// Calling from another class in the same package
public class ArithmeticOperations {
    public static int performDivision(int a, int b) {
        return MathUtils.divide(a, b);
    }
}

```

### Pasar argumentos entre métodos
En Java, cuando pasas argumentos a un método, siempre se pasan por valor. Esto significa que se pasa una copia del valor al método, en lugar de una referencia a la variable original. Sin embargo, el comportamiento del paso por valor difiere dependiendo de si estás pasando un tipo primitivo (como un `int`) o un tipo de referencia (como un objeto como `String`).

Cuando pasa un tipo primitivo a un método, el método recibe una copia del valor. Cualquier cambio realizado en el parámetro dentro del método no afecta la variable original fuera del método.

He aquí un ejemplo:

```java
public void testPrimitive() {
    int num = 10;
    modifyPrimitive(num);
    System.out.println(num); // Output: 10
}

public void modifyPrimitive(int value) {
    value = 20;
}

```

En este ejemplo, el método `modifyPrimitive` recibe una copia del valor de `num`. Modificar el parámetro `value` dentro del método no cambia la variable `num` original en el método `testPrimitive`.

Cuando pasa un tipo de referencia a un método, el método recibe una copia de la referencia al objeto. Si bien la referencia en sí se pasa por valor, el método aún puede modificar el estado del objeto al que apunta la referencia.

He aquí un ejemplo:

```java
public void test() {
    Person person = new Person("John", 25);
    modifyPerson(person);
    System.out.println(person.getName()); // Output: Alice
    System.out.println(person.getAge()); // Output: 25
}

public void modifyPerson(Person p) {
    p.setName("Alice"); // Sets a new name
    p = new Person("Bob", 30); // Reassigns p to a new person
}

```

En este ejemplo, el método `modifyPerson` recibe una copia de la referencia al objeto `Persona`. Dentro del método, se llama al método `setName()` en el objeto al que hace referencia `p`, lo que modifica el nombre del objeto original. Sin embargo, cuando "p" se reasigna a un nuevo objeto "Persona", no afecta la referencia de persona original en el método "principal".

Exploremos algunos ejemplos más para aclarar la diferencia entre reasignar una referencia y modificar el objeto en sí. 

Primero, considere esto sobre reasignar una referencia:

```java
public void test() {
    StringBuilder sb = new StringBuilder("Hello");
    modifyStringBuilder(sb);
    System.out.println(sb.toString()); // Output: Hello
}

public void modifyStringBuilder(StringBuilder builder) {
    builder = new StringBuilder("World");
}

```

En este ejemplo, el método `modifyStringBuilder` recibe una copia de la referencia al objeto `StringBuilder`. Dentro del método, la referencia del constructor se reasigna a un nuevo objeto `StringBuilder`, pero esto no afecta la referencia `sb` original en el método `main`.

Compare el ejemplo anterior con el siguiente, que demuestra en qué se diferencia modificar el estado de un objeto de simplemente reasignar una referencia:

```java
public void test() {
    StringBuilder sb = new StringBuilder("Hello");
    appendToStringBuilder(sb);
    System.out.println(sb.toString()); // Output: Hello, World!
}

public void appendToStringBuilder(StringBuilder builder) {
    builder.append(", World!");
}

```

En este ejemplo, el método `appendToStringBuilder` recibe una copia de la referencia al objeto `StringBuilder`. Dentro del método, se llama al método `append()` en el objeto al que hace referencia el constructor, lo que modifica el estado del objeto original. Los cambios realizados en el objeto son visibles fuera del método.

Comprender el comportamiento del paso por valor y la diferencia entre reasignar una referencia y modificar el objeto en sí es importante para escribir código correcto y predecible. Considere siempre si tiene la intención de modificar el objeto o simplemente reasignar la referencia al pasar tipos de referencia a métodos.



### Sobrecarga de métodos
En Java, es posible definir dos o más métodos dentro de la misma clase que comparten el mismo nombre, siempre que sus declaraciones de parámetros sean diferentes. Esto se llama sobrecarga de métodos. Considere los métodos de la siguiente clase:

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
}

```

Cuando se llama al método `add`, el compilador de Java determina qué versión del método sobrecargado llamar en función del tipo de argumentos que se le pasan. 

Esto es similar a pedir café en una cafetería. El barista puede preparar diferentes variaciones de café según sus especificaciones, café negro, café con leche o café con leche y azúcar. Cada variación se ordena usando la misma palabra (café), pero los ingredientes que especifiques determinan el tipo exacto de café que recibirás. De la misma manera, cuando llamas a un método sobrecargado en Java, los argumentos que pasas determinan qué versión del método se ejecutará.

Por ejemplo, si llamamos al método `add` con diferentes argumentos:

```java
Calculator calc = new Calculator();

int result1 = calc.add(5, 10);
System.out.println(result1);  // Output: 15

double result2 = calc.add(5.5, 10.2);
System.out.println(result2);  // Output: 15.7

double result3 = calc.add(5, 10.2);
System.out.println(result3);  // Output: 15.2

```

Esto es lo que sucede: 

1. Cuando se llama a `calc.add(5, 10)`, ambos argumentos son de tipo `int`. El compilador de Java hace coincidir esta llamada con el método `add` que toma dos parámetros `int` y el resultado es un valor `int` de 15.

2. Cuando se llama a `calc.add(5.5, 10.2)`, ambos argumentos son de tipo `double`. El compilador de Java compara esta llamada con el método `add` que toma dos parámetros `double` y el resultado es un valor `double` de 15,7.

3. Cuando se llama a `calc.add(5, 10.2)`, un argumento es un `int` y el otro es un `double`. En este caso, el compilador de Java realiza una conversión del argumento "int" a un "doble" para que coincida con el método "add" que toma dos parámetros "dobles". El resultado es un valor "doble" de 15,2.

Este ejemplo demuestra cómo el compilador de Java utiliza el tipo de argumentos para determinar qué método sobrecargado llamar. Hace coincidir los argumentos con la firma del método más específico disponible.

Java sólo puede elegir un método sobrecargado si puede encontrar una coincidencia exacta para los argumentos o si puede encontrar una versión que sea más específica mediante conversiones ampliadas.

Las conversiones de ampliación se producen cuando se pasa de un tipo de datos más pequeño a un tipo de datos más grande, por ejemplo, de "int" a "long", o, como en el ejemplo anterior, de "int" a "double".

Sin embargo, Java no puede aplicar conversiones restringidas (pasar de un tipo de datos más grande a uno más pequeño) automáticamente. Si no encuentra una coincidencia exacta o una coincidencia mediante la ampliación, generará un error de compilación.

Es importante tener en cuenta que la sobrecarga de métodos no es lo mismo que la anulación de métodos. Hablaremos más sobre la anulación en el próximo capítulo, pero al anular, se proporciona una implementación diferente para un método heredado. El método anulado debe tener el mismo nombre, tipo de retorno y parámetros que el método heredado. Por otro lado, los métodos sobrecargados deben tener el mismo nombre pero diferentes parámetros.

Así que tenga siempre esto en cuenta: cambiar solo el tipo de retorno no es suficiente para sobrecargar el método. La lista de parámetros debe ser diferente.

Además, un error común es pensar que Java siempre elige el método sobrecargado con la mayor cantidad de parámetros. No es así. Java selecciona el método basándose en la coincidencia más específica con los tipos de argumentos, no necesariamente el método con la mayor cantidad de parámetros.

Considere esta clase que tiene múltiples métodos sobrecargados llamados "display":

```java
public class DisplayOverload {
    
    // Method with a single String argument
    public void display(String str) {
        System.out.println("Displaying a String: " + str);
    }
    
    // Overloaded method with a single int argument
    public void display(int num) {
        System.out.println("Displaying an integer: " + num);
    }
    
    // Overloaded method with two int arguments
    public void display(int num1, int num2) {
        System.out.println("Displaying two integers: " + num1 + " and " + num2);
    }
}

// ...

DisplayOverload obj = new DisplayOverload();
        
obj.display("Hello, World!"); // Calls the method with a String argument
obj.display(5); // Calls the method with a single int argument
obj.display(10, 20); // Calls the method with two int arguments

```

En este ejemplo:
- Cuando se llama a `display("Hello, World!");`, Java selecciona el método `display(String str)` porque el argumento es una cadena, que coincide con el tipo de parámetro de este método específico.
- Cuando se llama a `display(5);`, Java selecciona el método `display(int num)` porque el argumento es un número entero, lo que lo convierte en la coincidencia más específica entre los métodos sobrecargados.
- Cuando se llama a `display(10, 20);`, aunque existen otros métodos de `display` que teóricamente podrían aceptar números enteros, Java elige `display(int num1, int num2)` porque coincide más específicamente con los dos argumentos enteros proporcionados.

Una última cosa a tener en cuenta es que no se pueden sobrecargar métodos que difieren sólo por un parámetro varargs. Por ejemplo, esto no compilará:

```java
public void sum(int[] numbers) { }
public void sum(int... numbers) { } // Compile-time error

```

La razón es que tanto `int[] números` como `int... números` son esencialmente iguales desde la perspectiva de Java porque `int...` es simplemente azúcar sintáctico para una matriz de números enteros (`int[]`). Cuando intentas sobrecargar un método con estos dos tipos de parámetros, Java los ve como firmas idénticas. Pero hablemos más de varargs.



### Varargs
Varargs, abreviatura de argumentos de longitud variable, es una característica que permite a los métodos aceptar una cantidad no especificada de argumentos de un tipo específico. Piense en los varargs como un buffet libre. En un buffet, no estás limitado a una cantidad fija de platos; puedes elegir tantos platos diferentes como quieras, e incluso puedes volver por más. De manera similar, con varargs, se puede llamar a un método con un número variable de argumentos; no estás fijado a un número específico. Esto hace que sus métodos sean más flexibles y fáciles de usar cuando el número exacto de entradas puede variar.

Para definir un método con varargs, se utilizan puntos suspensivos (`...`) después del tipo de datos del último parámetro. Así es como funciona:

```java
public void display(String... words) {
    for (String word : words) {
        System.out.println(word);
    }
}

```

En este ejemplo, se puede llamar a `display` con cualquier número de argumentos `String`, incluido ninguno. Es como si le estuvieras diciendo al método: "Esto es lo que tengo, tómalo todo". Esta flexibilidad hace que varargs sea extremadamente útil para crear métodos que necesitan manejar una cantidad desconocida de objetos, como una lista de nombres, números o incluso objetos complejos.

Ahora bien, existen reglas específicas que debes seguir para usar varargs de manera efectiva y correcta.

Primero, un parámetro varargs debe ser el último parámetro en la lista de parámetros de un método. Esta regla garantiza que el método pueda aceptar un número variable de argumentos sin ambigüedad con respecto a qué argumentos pertenecen al parámetro varargs y cuáles no. Por ejemplo, considere el siguiente método:

```java
void printStrings(String title, String... strings) {
    System.out.println(title + ":");
    for (String str : strings) {
        System.out.println(str);
    }
}

```

En este ejemplo, `String... strings` es un parámetro varargs que puede aceptar cualquier número de argumentos `String`. Ser el último parámetro le permite llamar a `printStrings` con cualquier número de cadenas, o incluso sin ninguna cadena.

En segundo lugar, sólo se permite un parámetro varargs en la lista de parámetros de un método. Esta restricción evita confusión sobre qué argumentos pertenecen a qué parámetro varargs si se permitiera más de uno. Por ejemplo, si desea crear un método que sume números, puede hacer lo siguiente:

```java
double multiplyAndSum(double multiplier, int... numbers) {
    double sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    return sum * multiplier;
}

```

Este método incluye correctamente solo un parámetro varargs (`int... números`), lo que garantiza claridad sobre cómo debe llamarse y cómo opera con los argumentos pasados.

En tercer lugar, un método con un parámetro varargs puede sobrecargarse, pero hay que asegurarse de evitar la ambigüedad. Esto requiere garantizar que la firma de cada método sea lo suficientemente distinta para evitar errores en tiempo de compilación. Por ejemplo, podría tener:

```java
void display(String s, int... numbers) {
    System.out.println(s);
    for (int num : numbers) {
        System.out.print(num + " ");
    }
    System.out.println();
}

void display(String first, String second) {
    System.out.println(first + ", " + second);
}

```

Aquí, `display` está sobrecargado con una versión que acepta una cadena y un parámetro entero varargs, y otra que acepta dos cadenas. Esta sobrecarga es válida porque las firmas de los métodos son distintas, lo que garantiza que el compilador pueda determinar qué método llamar en función de los argumentos proporcionados.

Dentro del método, se puede acceder a los elementos de un parámetro varargs de varias maneras, cada una adecuada para diferentes escenarios.

La forma más sencilla de acceder a elementos en un parámetro varargs es tratándolo como una matriz y accediendo a sus elementos directamente mediante un índice. Este método es útil cuando conoce el número exacto de argumentos o necesita acceder a elementos específicos. Por ejemplo, considere un método que imprime el primer, segundo y último elemento de un parámetro varargs:

```java
void printSelectedNumbers(int... numbers) {
    if (numbers.length >= 3) {
        System.out.println("First: " + numbers[0]);
        System.out.println("Second: " + numbers[1]);
        System.out.println("Last: " + numbers[numbers.length - 1]);
    } else {
        System.out.println("Insufficient arguments.");
    }
}

```

Este método accede directamente a los elementos por sus índices, similar al acceso a una matriz, lo que facilita la recuperación de valores específicos.

Para iterar sobre cada elemento en un parámetro varargs, el bucle for mejorado proporciona una forma limpia y concisa de procesar cada argumento. Este enfoque es más beneficioso cuando necesita realizar operaciones en cada elemento o cuando el número de argumentos es variable. Aquí hay un ejemplo que suma todos los números pasados ​​al método:

```java
int sumAll(int... numbers) {
    int sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    return sum;
}

```

El bucle "for" mejorado itera automáticamente sobre cada elemento en "números", lo que permite una fácil agregación o procesamiento.

Aunque es similar al uso de un bucle "for" mejorado, es posible que a veces necesites iterar manualmente sobre un parámetro varargs usando su propiedad "length" para una lógica más compleja, como cuando necesitas acceder al índice actual. Así es como puedes imprimir cada elemento con su índice:

```java
void printWithIndices(String... strings) {
    for (int i = 0; i < strings.length; i++) {
        System.out.println("Element " + i + ": " + strings[i]);
    }
}

```

Este método aprovecha la propiedad "longitud" del parámetro varargs para controlar manualmente la iteración, ofreciendo flexibilidad para operaciones basadas en índices.

Para operaciones más complejas, incluido el filtrado, el mapeo o la agregación de elementos, la API Stream de Java puede funcionar directamente con varargs. Este método es particularmente poderoso para procesar elementos en un estilo de programación funcional. Cubriremos las transmisiones en un capítulo posterior, pero, por ejemplo, puedes filtrar y sumar solo los números pares de la siguiente manera:

```java
int sumEvenNumbers(int... numbers) {
    return Arrays.stream(numbers) // Convert varargs to a stream
                 .filter(n -> n % 2 == 0) // Filter even numbers
                 .sum(); // Sum them
}

```

Una vez que haya definido un método que toma un parámetro vararg, puede llamarlo pasando argumentos individuales, pasando una matriz o llamándolo sin ningún argumento.

La forma más sencilla de llamar a un método con varargs es pasándole argumentos individuales. Este enfoque es idéntico a llamar a un método con un número fijo de parámetros, pero con la flexibilidad adicional de especificar cualquier número de argumentos. A continuación se muestra un ejemplo que utiliza un método que imprime cada argumento:

```java
void printArgs(String... args) {
    for (String arg : args) {
        System.out.println(arg);
    }
}

// Calling the method
printArgs("Hello", "World", "Varargs", "are", "flexible");

```

En este ejemplo, el método `printArgs` se llama con cinco argumentos de cadena, lo que demuestra la facilidad con la que se puede pasar cualquier número de argumentos.

Alternativamente, puedes llamar a un método varargs pasando una matriz del tipo especificado. Este enfoque es útil cuando los argumentos ya están almacenados en una matriz o cuando desea construir dinámicamente la lista de argumentos. Considere un método que suma un número arbitrario de números enteros:

```java
int sumNumbers(int... numbers) {
    return Arrays.stream(numbers).sum();
}

// Calling the method with an array
int[] numberArray = {1, 2, 3, 4, 5};
int sum = sumNumbers(numberArray);
System.out.println("Sum is: " + sum);

```

Aquí, se llama a `sumNumbers` con una matriz de números enteros, lo que muestra cómo una matriz coincide con la firma varargs, lo que proporciona una forma compacta de pasar múltiples argumentos.

Finalmente, también se puede llamar a un método varargs sin pasar ningún argumento. Esta característica es particularmente útil cuando una operación es opcional o cuando existe un comportamiento predeterminado válido en ausencia de entradas. Aquí hay un método que concatena cualquier número de cadenas, con una demostración de cómo llamarlo sin argumentos:

```java
String concatenateStrings(String... strings) {
    return Stream.of(strings).collect(Collectors.joining(", "));
}

// Calling the method without arguments
String result = concatenateStrings();
System.out.println("Result: " + result);

```

El ejemplo anterior ilustra que llamar a `concatenateStrings` sin ningún argumento es perfectamente válido y que varargs proporciona una firma de método flexible que se adapta a una amplia gama de casos de uso.


### El método `principal`
El método `main` es un método especial en Java que sirve como punto de entrada de una aplicación Java. Cuando ejecuta un programa Java, la JVM busca este método y comienza a ejecutar el código que contiene. Cada aplicación Java debe tener un método "principal" en al menos una de sus clases.

Aquí está la sintaxis para declarar un método "principal":

```java
public static void main(String[] args) {
    // ...
}

```

Analicemos cada parte:

- `public`: El método `main` debe declararse como `public` para permitir que la JVM lo llame desde fuera de la clase.
- `static`: El método `main` debe declararse como `static` para que pueda llamarse sin crear una instancia de la clase.
- `void`: El método `main` no devuelve ningún valor, por lo que su tipo de retorno es `void`.
- `main`: El nombre del método debe ser "main" (todo en minúsculas) para que la JVM lo reconozca como el punto de entrada.
- `String[] args`: El método `main` acepta un único parámetro, de tipo `String` array, convencionalmente llamado `args`. Este parámetro le permite pasar argumentos de línea de comandos al programa.

A continuación se muestra un ejemplo de un método "principal" simple:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

```

En este ejemplo, el método "main" simplemente imprime "¡Hola, mundo!" en la consola.

Los argumentos del programa se pasan como una matriz `String`, donde cada elemento representa un argumento separado:

```java
public class CommandLineArguments {
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println("Arguments:");
            for (String arg : args) {
                System.out.println(arg);
            }
        } else {
            System.out.println("No arguments provided.");
        }
    }
}

```

En este ejemplo, el método `main` comprueba si se pasó algún argumento utilizando `args.length`. Si hay argumentos, itera sobre la matriz `args` e imprime cada argumento. Si no se proporcionaron argumentos, imprime un mensaje indicándolo.

Puede ejecutar este programa desde la línea de comando y pasar argumentos como este:

```java CommandLineArguments arg1 arg2 arg3

```

Esta será la salida:

```

Arguments:
arg1
arg2
arg3

```

Si ejecuta el programa sin ningún argumento:

```java CommandLineArguments

```

Esta será la salida:

```

No arguments provided.

```

Es posible tener varios métodos llamados "principal" en una clase, siempre que tengan diferentes listas de parámetros. Sin embargo, solo el método definido como `public static void main(String[] args)` será reconocido como punto de entrada de la aplicación:

```java
public class MainOverloading {
    public static void main(String[] args) {
        System.out.println("Main method with String[] args");
        main(42);
    }

    public static void main(int num) {
        System.out.println("Main method with int parameter: " + num);
    }
}

```

En este ejemplo, la clase tiene dos métodos `main`: uno con la firma estándar y otro con un parámetro int; sin embargo, el método `main(String[] args)` es el punto de entrada y llama al método `main(int num)`.

Esta es la salida:

```

Main method with String[] args
Main method with int parameter: 42

```

## Constructores e inicializadores

### Constructores
En Java, un constructor es un método especial que se utiliza para inicializar objetos. Se llama cuando se crea una instancia de una clase.

Imagine un constructor como una receta para hornear un pastel específico. Así como la receta contiene las instrucciones y los ingredientes para hacer el pastel, un constructor tiene el código para configurar el estado inicial de un objeto.

La sintaxis para definir un constructor es sencilla. Tiene el mismo nombre que la clase y no tiene tipo de retorno, ni siquiera nulo.

He aquí un ejemplo:

```java
class Cake {
    String flavor;
    double price;
    Cake() {
        flavor = "Vanilla";
        price = 9.99;
    }
}

```

Para crear un objeto, usamos la palabra clave `new` seguida de una llamada al constructor:

```java
Cake myCake = new Cake();

```

La línea anterior creará un nuevo objeto "Cake" con el sabor "Vainilla" predeterminado y un precio de "9,99".

Pero, ¿qué pasa si quieres el sabor predeterminado pero con un precio diferente? ¿O personalizar ambos en determinados casos?

Bueno, así como puedes hornear diferentes variedades de pasteles modificando la receta, puedes crear objetos con diferentes estados iniciales proporcionando múltiples constructores.

Por ejemplo, agreguemos otro constructor a nuestra clase `Cake`:

```java
Cake(String flavor, double price) {
    this.flavor = flavor; 
    this.price = price;
}

```

Con este constructor podremos crear una tarta del sabor y precio que queramos:

```java
Cake specialCake = new Cake("Chocolate", 12.99);

```

Tener varios constructores brinda flexibilidad en la creación de objetos. Podemos proporcionar diferentes formas de inicializar un objeto según los datos disponibles en el momento de la creación.

El constructor sin parámetros se denomina constructor predeterminado. Si no define ningún constructor en su clase, el compilador proporcionará automáticamente un constructor predeterminado con un cuerpo vacío.

Sin embargo, si define cualquier constructor (como el nuestro parametrizado), el compilador no proporcionará un constructor predeterminado. En este caso, si aún desea tener la opción de crear un objeto sin especificar parámetros, debe definir explícitamente el constructor predeterminado.

Entonces, en nuestra clase `Cake`, podríamos tener ambos constructores:

```java
class Cake {
    String flavor;
    double price;

    Cake() {
        flavor = "Vanilla";
        price = 9.99;
    }

    Cake(String flavor, double price) {
        this.flavor = flavor;
        this.price = price;
    }
}

```

Ahora podemos crear un pastel de vainilla predeterminado con `new Cake()` o un pastel personalizado con `new Cake("Chocolate", 10.99)`.

### Inicializadores de instancia
Los inicializadores de instancias son bloques de código que se ejecutan cuando se crea un objeto, al igual que los constructores. Sin embargo, mientras que los constructores son métodos con un nombre específico y potencialmente parámetros, los inicializadores de instancia son solo bloques de código dentro de una clase.

Usemos una analogía para comprender los inicializadores de instancias.

Imagínese mudarse a una casa nueva. Todos tenemos nuestros rituales únicos para hacer que una casa se sienta como un hogar. Algunos colgarán fotos familiares, otros pintarán las paredes de su color favorito. Estos rituales son específicos de cada persona, al igual que los inicializadores de instancias son específicos de cada objeto.

Aquí está la sintaxis de un inicializador de instancia:

```java
class House {
    String color;
    // instance initializer
    {
        color = "White";
        System.out.println("Performing move-in ritual");
    }
}

```

Siempre que se crea un nuevo objeto `House`, se ejecutará el código dentro del bloque inicializador de instancia. Establecerá el color en `Blanco` e imprimirá `"Realizando ritual de mudanza"`.

Entonces, ¿cómo se comparan los inicializadores de instancia con los constructores y cuándo se pueden utilizar?

Bueno, imagina que tienes una clase con múltiples constructores. Cada constructor necesita realizar algunas tareas de inicialización comunes. En lugar de duplicar el código en cada constructor, puedes ponerlo en un inicializador de instancia. El código inicializador se ejecutará independientemente del constructor que se utilice.

```java
class House {
    String color;
    int numberOfRooms;

    // instance initializer    
    {
        color = "White";
        System.out.println("Performing move-in ritual");
    }

    House(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    House(String color, int numberOfRooms) {
        this.color = color;
        this.numberOfRooms = numberOfRooms;
    }
}

```

En este caso, independientemente del constructor que se utilice para crear un objeto "Casa", el inicializador de instancia se ejecutará, estableciendo el color predeterminado en "Blanco" e imprimiendo el mensaje de entrada.

Sin embargo, es importante tener en cuenta que, en la mayoría de los casos, puede lograr el mismo resultado simplemente moviendo el código de inicialización común a un método separado y llamando a ese método desde cada constructor.

De hecho, algunos argumentan que los inicializadores de instancia son redundantes ya que cualquier cosa que pueda hacer con un inicializador de instancia, también puede hacerlo con un constructor. La principal diferencia es que los constructores pueden tomar parámetros, mientras que los inicializadores de instancias no.

Dicho esto, existen algunos escenarios en los que los inicializadores de instancias pueden resultar útiles. Por ejemplo, si usa clases anónimas (que cubriremos más adelante), no puede definir un constructor, por lo que un inicializador de instancia es su única opción para el código de inicialización.

### Inicializadores estáticos
Los inicializadores estáticos son bloques de código que se ejecutan cuando una clase se carga en la memoria, antes de que se creen instancias de la clase. Se utilizan para inicializar variables estáticas o realizar acciones que son comunes a todas las instancias de la clase.

Imagine una reunión en el ayuntamiento que se lleva a cabo una vez que se establece una ciudad. En esta reunión, los líderes del pueblo establecen reglas y pautas que se aplican a todos en el pueblo. Esta configuración única es similar a lo que hace un inicializador "estático" para una clase.

Aquí está la sintaxis de un inicializador "estático":

```java
class TownHall {
    static String townName;
    static int population;

    // static initializer
    static {
        townName = "JavaVille";
        population = 1000;
        System.out.println("Town established: " + townName);
    }
}

```

La palabra clave "estática" antes de la llave de apertura indica que se trata de un bloque inicializador estático. Se ejecutará una vez cuando se cargue la clase `TownHall`, estableciendo el `townName` en `JavaVille`, la `población` inicial en `1000` e imprimiendo `Ciudad establecida: JavaVille`.

Ahora, podrías pensar que los inicializadores estáticos son simplemente otra forma de inicializar variables estáticas, y podrías lograr el mismo resultado inicializando directamente las variables en su declaración, así:

```java
static String townName = "JavaVille";
static int population = 1000;

```

Y estarías parcialmente en lo cierto. Para inicializaciones simples, la asignación directa suele ser más clara y concisa.

Sin embargo, los inicializadores estáticos proporcionan más flexibilidad. Le permiten escribir una lógica de inicialización más compleja, como por ejemplo:
- Métodos de llamada
- Uso de estructuras de control como bucles y condicionales.
- Manejo de excepciones

Aquí hay un ejemplo que demuestra esto:

```java
static List<String> residents = new ArrayList<>();

static {
    Path path = Paths.get("residents.txt");
    try (Stream<String> lines = Files.lines(path)) {
        lines.forEach(residents::add);
    } catch (IOException e) {
        System.out.println("Residents file not found.");
    }
}

```

En este caso, estamos usando el inicializador estático para leer una lista de residentes de un archivo y completar la lista de "residentes". Este tipo de inicialización compleja no sería posible con una simple asignación directa.

Otra diferencia clave es que una clase puede tener múltiples inicializadores estáticos y se ejecutarán en el orden en que aparecen en la clase. Esto puede resultar útil para organizar una lógica de inicialización compleja en fragmentos legibles.

Es importante tener en cuenta que los inicializadores estáticos se ejecutan antes de que se cree cualquier instancia de la clase, e incluso antes de que se llame al método "main". Son parte del proceso de carga de clases.

Por el contrario, los inicializadores y constructores de instancias se ejecutan cada vez que se crea una nueva instancia de la clase. Son parte del proceso de creación de objetos.

### Orden de inicialización
Hemos revisado constructores, inicializadores de instancias e inicializadores estáticos. Sin embargo, si una clase incluye las tres, ¿cuál se ejecuta primero? ¿Cuál es el orden de inicialización?

Cuando se carga una clase, lo primero que se inicializa son las variables estáticas y los inicializadores estáticos, en el orden en que aparecen en la clase. Esto sucede una vez por carga de clase, antes de que se creen instancias.

Después de eso, cada vez que se crea una nueva instancia de la clase, las variables de instancia se inicializan y se ejecutan los inicializadores y constructores de instancia. 

El orden es el siguiente:

1. Las variables de instancia se inicializan a sus valores predeterminados (`0`, `false` o `null`). Este paso garantiza que todas las variables de instancia tengan un estado inicial predecible antes de que se ejecute cualquier código de inicialización adicional.
2. Los inicializadores de instancias se ejecutan en el orden en que aparecen en la clase.
3. Se ejecuta el constructor.

Aquí hay un ejemplo que demuestra este orden:

```java
class InitializationOrder {
    static int staticVar = 1;
    int instanceVar = 1;

    static {
        System.out.println("Static Initializer: staticVar = " + staticVar);
        staticVar = 2;
    }

    {
        System.out.println("Instance Initializer: instanceVar = " + instanceVar);
        instanceVar = 2;
    }

    InitializationOrder() {
        System.out.println("Constructor: instanceVar = " + instanceVar);
        instanceVar = 3;
    }

    public static void main(String[] args) {
        System.out.println("Creating new instance");
        InitializationOrder obj = new InitializationOrder();
        System.out.println("Created instance: instanceVar = " + obj.instanceVar);
    }
}

```

Si ejecuta este código, el resultado será:

```

Static Initializer: staticVar = 1
Creating new instance
Instance Initializer: instanceVar = 1
Constructor: instanceVar = 2
Created instance: instanceVar = 3

```

Analicemos esto:
1. Cuando se carga la clase `InitializationOrder`, la variable estática `staticVar` se inicializa en `1`, y luego se ejecuta el inicializador estático, que imprime el valor actual de `staticVar` (`1`) y luego lo establece en `2`.
2. En el método `main`, imprimimos `Creando nueva instancia` para marcar el inicio de la creación de la instancia.
3. Se crea un nuevo objeto `InitializationOrder`. Primero, la variable de instancia `instanceVar` se inicializa a su valor predeterminado de `1`.
4. Se ejecuta el inicializador de instancia, que imprime el valor actual de `instanceVar` (`1`) y luego lo establece en `2`.
5. Se ejecuta el constructor, que imprime el valor actual de `instanceVar` (`2`) y luego lo establece en `3`.
6. Finalmente, de vuelta en el método `main`, imprimimos el valor final de `instanceVar` (`3`).

Es importante tener en cuenta este orden, especialmente si los inicializadores y constructores dependen unos de otros. Las suposiciones incorrectas sobre el orden de inicialización pueden provocar errores sutiles.

Además, tenga en cuenta que si una clase tiene varios inicializadores estáticos, se ejecutarán en el orden en que aparecen en la clase. Lo mismo ocurre, por ejemplo, con los inicializadores.

Ampliemos nuestro ejemplo anterior para demostrar esto:

```java
class MultipleInitializers {
    static int staticVar1;
    static int staticVar2;
    int instanceVar1;
    int instanceVar2;

    static {
        System.out.println(
          "Static Initializer 1: staticVar1 = " + staticVar1
        );
        staticVar1 = 1;
    }

    static {
        System.out.println(
          "Static Initializer 2: staticVar2 = " + staticVar2
        );
        staticVar2 = 2;
    }

    {
        System.out.println(
          "Instance Initializer 1: instanceVar1 = " + instanceVar1
        );
        instanceVar1 = 1;
    }

    {
        System.out.println(
          "Instance Initializer 2: instanceVar2 = " + instanceVar2
        );
        instanceVar2 = 2;
    }

    MultipleInitializers() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        System.out.println("Creating new instance");
        MultipleInitializers obj = new MultipleInitializers();
        System.out.println(
          "Created instance: instanceVar1 = " 
              + obj.instanceVar1 
              + ", instanceVar2 = " 
              + obj.instanceVar2
        );
    }
}

```

Cuando ejecutamos este código, el resultado será:

```

Static Initializer 1: staticVar1 = 0
Static Initializer 2: staticVar2 = 0
Creating new instance
Instance Initializer 1: instanceVar1 = 0
Instance Initializer 2: instanceVar2 = 0
Constructor
Created instance: instanceVar1 = 1, instanceVar2 = 2

```

Esto es lo que está pasando:
1. Cuando se carga la clase `MultipleInitializers`, las variables estáticas `staticVar1` y `staticVar2` se inicializan a su valor predeterminado de `0`.

2. Se ejecuta el primer inicializador estático, que imprime el valor actual de `staticVar1` (`0`) y luego lo establece en `1`.

3. Se ejecuta el segundo inicializador estático, que imprime el valor actual de `staticVar2` (`0`) y luego lo establece en `2`.

4. En el método `main`, imprimimos `Creando nueva instancia` para marcar el inicio de la creación de la instancia.

5. Se crea un nuevo objeto `MultipleInitializers`. Primero, las variables de instancia `instanceVar1` y `instanceVar2` se inicializan a su valor predeterminado de `0`.

6. Se ejecuta el inicializador de primera instancia, que imprime el valor actual de `instanceVar1` (`0`) y luego lo establece en `1`.

7. Se ejecuta el inicializador de la segunda instancia, que imprime el valor actual de `instanceVar2` (0) y luego lo establece en `2`.

8. Se ejecuta el constructor, que simplemente imprime `Constructor`.

9. Finalmente, de vuelta en el método `main`, imprimimos los valores finales de `instanceVar1` (`1`) y `instanceVar2` (`2`).

Recuerde, todos los inicializadores estáticos se ejecutarán antes que cualquier inicializador de instancia y todos los inicializadores se ejecutarán antes que el constructor. Pero dentro de cada categoría (estática o instancia), los inicializadores se ejecutarán en el orden en que están definidos en la clase.


## Extendiendo desde `java.lang.Object`
En Java, cada clase es implícitamente una subclase de la clase `java.lang.Object`, que es la raíz de la jerarquía de clases. Incluso si no extiende explícitamente ninguna clase, su clase heredará automáticamente de "Objeto".

La clase `Object` proporciona un conjunto de métodos fundamentales que son comunes a todos los objetos. Cuando creas una nueva clase, heredas automáticamente estos métodos. Algunos de los métodos comúnmente utilizados heredados de "Object" incluyen:

1. `toString()`: Devuelve una representación de cadena del objeto. De forma predeterminada, devuelve una cadena que consta del nombre de la clase del objeto, un símbolo `@` y el código hash del objeto en formato hexadecimal. Puede anular este método para proporcionar una representación de cadena personalizada de su objeto.

2. `equals(Object obj)`: Compara el objeto con otro objeto para determinar la igualdad. De forma predeterminada, compara las referencias de objetos utilizando el operador `==`. Puede anular este método para definir una lógica de igualdad personalizada basada en el estado del objeto.

3. `hashCode()`: Devuelve un valor de código hash para el objeto. El código hash se utiliza en estructuras de datos basadas en hash como "HashSet" y "HashMap". De forma predeterminada, devuelve un valor entero único para cada objeto. Si anula el método `equals()`, también debe anular el método `hashCode()` para garantizar que los objetos iguales tengan el mismo código hash.

4. `getClass()`: Devuelve la clase de tiempo de ejecución del objeto. Es un método "final", lo que significa que no se puede anular.

5. `clone()`: Crea y devuelve una copia del objeto. De forma predeterminada, realiza una copia superficial del objeto. Para utilizar este método, su clase debe implementar la interfaz `Cloneable`.

A continuación se muestra un ejemplo que demuestra algunos de los métodos heredados de `Object`:

```java
class MyClass {
    private int value;
    
    public MyClass(int value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "MyClass[value=" + value + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MyClass other = (MyClass) obj;
        return value == other.value;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

```

En este ejemplo, `MyClass` anula los métodos `toString()`, `equals()` y `hashCode()` heredados de `Object`. El método `toString()` proporciona una representación de cadena personalizada del objeto, el método `equals()` define la igualdad en función del campo de valor y el método `hashCode()` genera un código hash basado en el campo de valor.

Al aprovechar estos métodos, puede proporcionar representaciones de cadenas significativas, definir comparaciones de igualdad y garantizar un comportamiento adecuado en estructuras de datos basadas en hash.


## Clases anidadas
En Java, es posible definir una clase dentro de otra clase. Estas clases se denominan clases anidadas. De manera similar a cómo un cuadro puede contener varios cuadros más pequeños en su interior, una clase (la clase externa o envolvente) puede tener otras clases (clases anidadas) definidas dentro de ella.

Hay cuatro tipos de clases anidadas en Java:

1. Clase anidada estática

2. Clase interna (también conocida como clase anidada no estática)

3. clase local

4. Clase anónima

Cada tipo de clase anidada tiene sus propias características y casos de uso:

- Una clase anidada estática es como una caja más pequeña que no depende de la caja más grande para su existencia. Puede acceder directamente a los miembros estáticos de la clase adjunta. Sin embargo, para acceder a miembros no estáticos, se necesita una instancia de la clase adjunta, como lo haría cualquier otra clase externa.

- Una clase interna, por otro lado, es como una caja más pequeña que está estrechamente ligada a la caja más grande. Puede acceder directamente a miembros estáticos y no estáticos de la clase adjunta. Sin embargo, una instancia de una clase interna no puede existir sin una instancia de la clase adjunta.

- Una clase local es como un cuadro temporal creado dentro de un método o un bloque de la clase adjunta. El alcance de una clase local se limita al bloque en el que está definida. Si bien las clases locales no utilizan modificadores de acceso tradicionales como "público" o "privado", su accesibilidad está inherentemente limitada al bloque circundante.

- Una clase anónima es como un cuadro único y sin nombre creado para un propósito específico. Se define y se crea una instancia en una sola declaración, generalmente como un argumento para una llamada a un método o como un inicializador. La accesibilidad de una clase anónima está determinada por el contexto en el que se utiliza, como dentro de un método o como inicializador de campo, y no utiliza modificadores de acceso tradicionales.

Al decidir entre una clase anidada estática y una clase interna, considere la relación entre la clase anidada y la clase adjunta. Si la clase anidada no necesita acceso a los miembros no estáticos de la clase adjunta, utilice una clase anidada estática. Esto hace que la clase sea más independiente y reutilizable. Si la clase anidada requiere acceso a los miembros no estáticos de la clase adjunta o necesita estar vinculada a una instancia de la clase adjunta, use una clase interna.

Es importante tener en cuenta que, si bien las clases anidadas pueden ayudar a organizar mejor el código, sí afectan el funcionamiento del código. Cada tipo de clase anidada tiene su propio comportamiento y casos de uso específicos. Por ejemplo, una clase interna tiene una referencia implícita a una instancia de la clase adjunta, lo que puede tener implicaciones para el uso de memoria y la serialización.

Un error común es pensar que las clases anidadas estáticas y las clases internas son esencialmente iguales ya que ambas están definidas dentro de otra clase. Sin embargo, esto no es cierto. Las clases anidadas estáticas son semánticamente similares a cualquier otra clase de nivel superior y no tienen una referencia implícita a una instancia de la clase adjunta. Las clases internas, por otra parte, están íntimamente ligadas a una instancia de la clase circundante y no pueden existir de forma independiente.

En términos de modificadores de acceso, las clases anidadas se pueden declarar como "públicas", paquete privado (predeterminado), "protegidas" o "privadas", a diferencia de las clases de nivel superior que no se pueden declarar como "protegidas" o "privadas".

La accesibilidad de clases anidadas estáticas y no estáticas depende de su modificador de acceso y de la accesibilidad de la clase adjunta. Por ejemplo, si la clase adjunta es "pública" y el nesLa clase ted es "privada", solo se puede acceder a la clase anidada dentro de la clase adjunta. Si la clase anidada es pública, se puede acceder a ella desde cualquier lugar, siempre que también se pueda acceder a la clase adjunta.

Aquí hay un poco más de detalle sobre cada tipo:

- **Clase anidada pública:** Se puede acceder a una clase anidada "pública" desde cualquier otra clase, pero la accesibilidad de la clase anidada aún depende de la accesibilidad de su clase externa. Si no se puede acceder a la clase externa en algún contexto, entonces tampoco se podrá acceder a su clase pública anidada allí.

- **Clase anidada protegida:** Se puede acceder a una clase anidada `protegida` dentro de su propio paquete y mediante subclases de su clase externa, independientemente del paquete en el que se encuentre la subclase. Esto permite una visibilidad más controlada en comparación con una clase anidada pública, particularmente útil cuando desea exponer cierta funcionalidad solo a ciertas subclases.

- **Clase anidada privada:** Solo se puede acceder a una clase anidada "privada" dentro de su clase externa. Esto es útil para ocultar completamente la clase del mundo exterior, haciéndola accesible solo para la clase externa. Esto se usa a menudo para clases auxiliares que no tienen ningún interés fuera de la clase externa.

- **Clase anidada privada de paquete (predeterminada):** Una clase anidada sin modificador de acceso es privada de paquete, lo que significa que solo se puede acceder a ella dentro de su propio paquete. Este es el nivel de acceso predeterminado si no se especifica ningún modificador de acceso. Es un término medio en términos de accesibilidad, más restrictivo que lo "público" pero menos restrictivo que lo "privado".

Las clases locales se definen en un bloque, normalmente dentro del cuerpo de un método. La visibilidad de una clase local está restringida al bloque en el que está definida. Por lo tanto, si bien no puede aplicar modificadores de acceso tradicionales ("público", "protegido", "privado") a la clase misma porque no es visible fuera del bloque, puede controlar el acceso a instancias de esta clase desde dentro del bloque. 

Y dado que las clases anónimas se utilizan dentro de una expresión, no permiten modificadores de acceso para la clase misma. El contexto en el que se declaran dicta su accesibilidad. Sin embargo, los métodos y campos dentro de una clase anónima pueden tener modificadores de acceso, sujetos a reglas de alcance normales.


Aquí hay una tabla resumen de los modificadores de acceso permitidos para cada tipo de clase anidada:

| Tipo de clase anidada | `public` | `protected` | `predeterminado` | `private` |
|-------------------|----------|-------------|-----------|-----------|
| Clase anidada estática | Sí | Sí | Sí | Sí |
| Clase interna | Sí | Sí | Sí | Sí |
| Clase local | No | No | Sí* | No |
| Clase anónima | No | No | Sí* | No |

- `Sí` indica que el modificador de acceso está permitido.
- `No` indica que el modificador de acceso no es aplicable.
- `Sí*` indica que para las clases locales y anónimas el concepto de modificadores de acceso tradicionales no se aplica a estas clases porque su visibilidad está inherentemente limitada al bloque en el que se declaran. Por tanto, no tienen modificadores de acceso en el sentido tradicional.

Ahora repasemos cada tipo con más detalle.

### Clases anidadas estáticas
Una clase anidada estática es una clase definida dentro de otra clase y marcada con la palabra clave "estática":

```java
class OuterClass {
    static class StaticNestedClass {
        // members of the static nested class
    }
}

```

Las clases anidadas estáticas se pueden declarar con cualquiera de los cuatro modificadores de acceso: "público", "protegido", paquete privado (predeterminado) o "privado". La accesibilidad de la clase anidada estática depende del modificador de acceso utilizado y de la accesibilidad de la clase adjunta. He aquí un ejemplo:

```java
public class OuterClass {
    private static class PrivateNestedClass {
        // ...
    }

    protected static class ProtectedNestedClass {
        // ...
    }

    static class PackagePrivateNestedClass {
        // ...
    }

    public static class PublicNestedClass {
        // ...
    }
}

```

En este ejemplo, solo se puede acceder a `PrivateNestedClass` dentro de `OuterClass`, a `ProtectedNestedClass` se puede acceder dentro de `OuterClass` y sus subclases, a `PackagePrivateNestedClass` se puede acceder dentro del mismo paquete que `OuterClass` y a `PublicNestedClass` se puede acceder desde cualquier lugar.

Las clases anidadas estáticas pueden extender otra clase e implementar interfaces, como cualquier otra clase de nivel superior:

```java
class BaseClass {
    // ...
}

interface MyInterface {
    // ...
}

class OuterClass {
    static class NestedClass extends BaseClass implements MyInterface {
        // ...
    }
}

```

Aquí, `NestedClass` extiende `BaseClass` e implementa `MyInterface`, lo que demuestra que una clase anidada estática puede extender otra clase e implementar interfaces.

Pueden acceder a los miembros estáticos de la clase adjunta directamente, utilizando el nombre de la clase adjunta seguido de la notación de puntos. Sin embargo, para acceder a miembros no estáticos de la clase adjunta, una clase anidada estática requiere una instancia de la clase adjunta. Esto se debe a que las clases anidadas estáticas no tienen inherentemente acceso a las variables de instancia de la clase adjunta.

A continuación se muestra un ejemplo de una clase estática anidada:

```java
class OuterClass {
    private static int staticField = 10;
    private int instanceField = 20;

    static class NestedClass {
        void accessOuterMembers() {
            System.out.println(staticField); // Accessible directly
            System.out.println(instanceField); // Compilation error: cannot access non-static field
            System.out.println(new OuterClass().instanceField); // Accessible via an instance of OuterClass
        }
    }
}

```

En este ejemplo, `NestedClass` puede acceder directamente al `staticField` de `OuterClass`, pero no puede acceder directamente al `instanceField`. Para acceder a "instanceField", se necesita una instancia de "OuterClass".

Para crear una instancia de una clase anidada estática, no necesita una instancia de la clase adjunta. Puede crear una instancia utilizando el nombre de la clase adjunta seguido de la notación de puntos y el nombre de la clase anidada estática.

```java
OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();

```

Al hacer referencia a miembros estáticos de una clase anidada estática desde fuera de la clase adjunta, use el nombre de la clase adjunta, seguido de un punto, el nombre de la clase anidada estática, otro punto y luego el nombre del miembro. Esta sintaxis resalta la estructura anidada al tiempo que proporciona rutas claras para acceder a los miembros estáticos:

```java
OuterClass.StaticNestedClass.staticField;
OuterClass.StaticNestedClass.staticMethod();
OuterClass.StaticNestedClass.StaticNestedNestedClass nestedNestedObject 
                  = new OuterClass.StaticNestedClass.StaticNestedNestedClass();

```

Desde dentro de la clase adjunta, puede acceder directamente a los miembros de la clase anidada estática sin el nombre de la clase adjunta.

```java
class OuterClass {
    static class StaticNestedClass {
        static void staticMethod() {
            // ...
        }
    }

    void outerMethod() {
        StaticNestedClass.staticMethod();
    }
}

```

Como puede ver, las clases anidadas estáticas son similares a las clases normales de nivel superior en muchos aspectos:

1. Pueden tener todo tipo de modificadores de acceso (`public`, `private`, `protected` y paquete).

2. Pueden ampliar otras clases e implementar interfaces.

3. Pueden tener miembros estáticos y no estáticos.

4. Se pueden crear instancias de forma independiente (sin una instancia de la clase adjunta).

Sin embargo, existen algunas diferencias clave:

1. Las clases anidadas estáticas se definen dentro de otra clase, mientras que las clases de nivel superior se definen de forma independiente.

2. Las clases anidadas estáticas tienen acceso directamente a los miembros estáticos de la clase adjunta, mientras que las clases de nivel superior necesitan usar el nombre de la clase adjunta para acceder a sus miembros estáticos.

3. Las clases anidadas estáticas pueden ser "privadas", lo que permite una mejor encapsulación, mientras que las clases de nivel superior solo pueden ser "públicas" o privadas de paquete.

En resumen, las clases anidadas estáticas son esencialmente como clases normales de nivel superior que se han anidado dentro de otra clase con fines organizativos. No tienen una referencia implícita a una instancia de la clase adjunta y se pueden crear instancias de forma independiente. Esto los hace útiles para agrupar clases relacionadas y proporcionar un nivel de encapsulación.

### Clases anidadas no estáticas
Las clases anidadas no estáticas, también conocidas como clases internas, son clases que se definen dentro de otra clase sin la palabra clave "estática":

```java
class OuterClass {
    class InnerClass {
        // members of the inner class
    }
}

```

Se puede declarar una clase interna con cualquiera de los cuatro modificadores de acceso: "público", "protegido", "privado" o el nivel de acceso predeterminado. La accesibilidad de la clase interna depende del modificador de acceso utilizado y de la accesibilidad de la clase adjunta. Si la clase externa es "pública" y la clase interna es "privada", solo se puede acceder a la clase interna desde la clase externa. He aquí un ejemplo:

```java
public class OuterClass {
    private class PrivateInnerClass {
        // ...
    }

    protected class ProtectedInnerClass {
        // ...
    }

    class PackagePrivateInnerClass {
        // ...
    }

    public class PublicInnerClass {
        // ...
    }
}

```

En este ejemplo, solo se puede acceder a `PrivateInnerClass` dentro de `OuterClass`, a `ProtectedInnerClass` se puede acceder dentro de `OuterClass` y sus subclases, a `PackagePrivateInnerClass` se puede acceder dentro del mismo paquete que `OuterClass` y a `PublicInnerClass` se puede acceder desde cualquier lugar, siempre que se pueda acceder a `OuterClass`.

Una clase interna puede extender otra clase e implementar interfaces, como cualquier otra clase. Esto permite que las clases internas hereden el comportamiento y se ajusten a los contratos definidos por otras clases e interfaces:

```java
class BaseClass {
    // ...
}

interface MyInterface {
    // ...
}

class OuterClass {
    class InnerClass extends BaseClass implements MyInterface {
        // ...
    }
}

```

Aquí, `InnerClass` extiende `BaseClass` e implementa `MyInterface`, lo que demuestra que una clase interna puede heredar de otra clase y ajustarse a una interfaz.

Una clase interna tiene acceso a todos los miembros (campos, métodos y clases anidadas) de la clase adjunta, incluidos los miembros "privados". Esto se debe a que una clase interna está asociada con una instancia de la clase externa y comparte una relación especial con ella. La clase interna puede acceder y manipular directamente el estado de la instancia de la clase externa:

```java
class OuterClass {
    private int privateField = 10;
    protected int protectedField = 20;
    int packagePrivateField = 30;
    public int publicField = 40;

    class InnerClass {
        void accessOuterMembers() {
            System.out.println(privateField);
            System.out.println(protectedField);
            System.out.println(packagePrivateField);
            System.out.println(publicField);
        }
    }
}

```

En este ejemplo, "InnerClass" tiene acceso directo a todos los miembros de "OuterClass", incluido el campo privado "privateField". La clase interna puede acceder y manipular libremente el estado de la instancia de la clase externa.

Para crear una instancia de una clase interna, normalmente necesita una instancia de la clase externa. La forma más común de crear una instancia de una clase interna es desde un método no estático de la clase externa:

```java
class OuterClass {
    class InnerClass {
        // ...
    }

    void outerMethod() {
        InnerClass innerObject = new InnerClass();
    }
}

```

Desde fuera de la clase externa, puede crear una instancia de una clase interna usando la siguiente sintaxis:

```java
OuterClass outerObject = new OuterClass();
OuterClass.InnerClass innerObject = outerObject.new InnerClass();

```

Para hacer referencia a miembros (campos, métodos, clases anidadas) de una clase interna desde fuera de la clase externa, primero necesita una instancia de la clase externa, luego use la notación de puntos para acceder a la clase interna, seguida de otro punto y el nombre del miembro:

```java
OuterClass outerObject = new OuterClass();
OuterClass.InnerClass innerObject = outerObject.new InnerClass();
innerObject.innerField;
innerObject.innerMethod();

```

Desde dentro de la clase externa, puede acceder directamente a los miembros de la clase interna usando una instancia de la clase interna:

```java
class OuterClass {
    class InnerClass {
        void innerMethod() {
            // ...
        }
    }

    void outerMethod() {
        InnerClass innerObject = new InnerClass();
        innerObject.innerMethod();
    }
}

```

Las clases internas se diferencian de las clases regulares de alto nivel en varios aspectos:

1. Las clases internas se definen dentro de otra clase, mientras que las clases de nivel superior se definen fuera de otras clases.

2. Las clases internas tienen acceso a todos los miembros de la clase adjunta, incluidos los miembros "privados", mientras que las clases de nivel superior solo pueden acceder a los miembros "públicos", "protegidos" y "predeterminados" de otras clases.

3. Las clases internas están asociadas con una instancia de la clase externa y no pueden existir de forma independiente, mientras que las clases de nivel superior pueden crear instancias de forma independiente.

4. Las clases internas pueden ser "privadas", lo que permite una mejor encapsulación, mientras que las clases de nivel superior solo pueden ser "públicas" o privadas de paquete.

Las clases internas son útiles cuando una clase está estrechamente vinculada a otra clase y necesita acceso a sus componentes internos. Proporcionan una forma de organizar clases relacionadas y mantener un estrecho vínculo entre ellas. Las clases internas se usan comúnmente para implementar detectores de eventos, iteradores u otras funciones específicas de la clase adjunta.

### Clases locales
Las clases locales se definen dentro de un bloque de código, normalmente dentro de un método o constructor. Tienen alcance limitado y sólo son accesibles dentro del bloque donde están definidos:

```java
void someMethod() {
    class LocalClass {
        // members of the local class
    }
}

```

Una clase local no puede tener modificadores de acceso. No se puede acceder a ellos desde fuera del bloque o método en el que están definidos. Esto se debe a que las clases locales no son miembros de la clase adjunta, sino que están definidas dentro de un método o bloque.

Sin embargo, pueden ampliar otra clase e implementar interfaces, como cualquier otra clase.

Ejemplo:

```java
void someMethod() {
    class LocalClass extends BaseClass implements MyInterface {
        // ...
    }
}

```

Además, una clase local tiene acceso a todos los miembros (campos, métodos y clases anidadas) de la clase adjunta, incluidos los miembros "privados". Además, una clase local puede acceder a variables y parámetros locales "finales" o efectivamente finales del método adjunto:

```java
class OuterClass {
    private int privateField = 10;

    void someMethod(final int parameter) {
        final int localVariable = 20;

        class LocalClass {
            void accessOuterMembers() {
                System.out.println(privateField);
                System.out.println(parameter);
                System.out.println(localVariable);
            }
        }

        LocalClass localObject = new LocalClass();
        localObject.accessOuterMembers();
    }
}

```

En este ejemplo, `LocalClass` tiene acceso al campo `private` `privateField` de `OuterClass`, así como al parámetro `final` `parameter` y a la variable local final `localVariable` de `someMethod()`.

Para crear una instancia de una clase local, puede crear una instancia dentro del método o bloque donde está definida, usando la palabra clave `new`:

```java
void someMethod() {
    class LocalClass {
        // ...
    }

    LocalClass localObject = new LocalClass();
}

```

Para hacer referencia a miembros (campos, métodos, clases anidadas) de una clase local, puedes acceder a ellos directamente usando una instancia de la clase local dentro del método o bloque donde está definida:

```java
void someMethod() {
    class LocalClass {
        int localField = 10;

        void localMethod() {
            System.out.println("Local method");
        }
    }

    LocalClass localObject = new LocalClass();
    System.out.println(localObject.localField);
    localObject.localMethod();
}

```

Las clases locales se diferencian de las clases regulares de alto nivel en varios aspectos:

1. Las clases locales se definen dentro de un método o bloque, mientras que las clases de nivel superior se definen de forma independiente.

2. Las clases locales tienen un alcance limitado y solo son accesibles dentro del bloque donde están definidas, mientras que las clases de nivel superior tienen un alcance más amplio.

3. Las clases locales no pueden tener modificadores de acceso, mientras que las clases de nivel superior pueden ser "públicas" o privadas de paquete.

4. Las clases locales pueden acceder a variables y parámetros locales "finales" o efectivamente finales del método adjunto, mientras que las clases de nivel superior no pueden acceder directamente a variables o parámetros locales.

Las clases locales son útiles cuando necesita definir una clase que solo se usa dentro de un método o bloque específico y no es necesario acceder a ella desde otras partes del código. Proporcionan una forma de encapsular el comportamiento y el estado dentro de un alcance limitado.

### Clases anónimas
Las clases anónimas son una forma de definir y crear instancias de una clase al mismo tiempo, sin darle un nombre. Se utilizan para crear implementaciones únicas de interfaces o clases abstractas.

Para declarar una clase anónima, se utiliza la palabra clave `new` seguida del nombre de una interfaz o una clase abstracta, y luego se proporciona el cuerpo de la clase entre llaves.

```java
interface MyInterface {
    void myMethod();
}

MyInterface myObject = new MyInterface() {
    @Override
    public void myMethod() {
        // Implementation of myMethod()
    }
};

```

Dado que las clases anónimas no reciben un nombre explícito y se definen en el momento de su uso, no pueden tener ningún modificador de acceso explícito. Su accesibilidad está determinada por el contexto en el que se utilizan. Específicamente, el ámbito en el que se define una clase anónima determina su accesibilidad. Por ejemplo, si una clase anónima se define dentro de un método, solo se puede acceder a ella dentro de ese método. Si se define dentro de una clase, sigue las reglas de accesibilidad de esa clase.

Una clase anónima puede extender una clase o implementar una interfaz, sin embargo, no puede hacer ambas cosas al mismo tiempo:

```java
class BaseClass {
    void baseMethod() {
        System.out.println("Base method");
    }
}

interface MyInterface {
    void myMethod();
}

BaseClass anonymousObject1 = new BaseClass() {
    @Override
    void baseMethod() {
        System.out.println("New implementation of base method");
    }
};

MyInterface anonymousObject2 = new MyInterface() {
    @Override
    public void myMethod() {
        System.out.println("Implementation of myMethod()");
    }
};

```

Una clase anónima tiene acceso a todos los miembros (campos, métodos y clases anidadas) de la clase adjunta, incluidos los miembros "privados". Además, una clase anónima puede acceder a variables y parámetros locales "finales" o efectivamente "finales" del método adjunto:

```java
class OuterClass {
    private int privateField = 10;

    void someMethod(final int parameter) {
        final int localVariable = 20;

        MyInterface anonymousObject = new MyInterface() {
            @Override
            public void myMethod() {
                System.out.println(privateField);
                System.out.println(parameter);
                System.out.println(localVariable);
            }
        };

        anonymousObject.myMethod();
    }
}

```

Una clase anónima no tiene nombre, por lo que no se puede hacer referencia directamente a sus miembros desde fuera del cuerpo de la clase. Sin embargo, puede hacer referencia a los miembros de la interfaz o clase abstracta que la clase anónima implementa o extiende:

```java
interface MyInterface {
    void myMethod();
    int myField = 10;
}

MyInterface anonymousObject = new MyInterface() {
    @Override
    public void myMethod() {
        System.out.println("Implementation of myMethod()");
    }
};

anonymousObject.myMethod();
System.out.println(MyInterface.myField);

```

Las clases anónimas se diferencian de las clases regulares de alto nivel en varios aspectos:

1. Las clases anónimas se definen y se crean instancias al mismo tiempo, sin un nombre explícito, mientras que las clases de nivel superior se definen por separado y se crean instancias utilizando la palabra clave "nueva".

2. Las clases anónimas se definen en el momento de su uso, normalmente como argumento de un método o como inicializador, mientras que las clases de nivel superior se definen de forma independiente.

3. Las clases anónimas no pueden tener constructores o modificadores de acceso explícitos, mientras que las clases de nivel superior pueden tenerlos todos.

4. Las clases anónimas se utilizan para crear implementaciones o instancias únicas, mientras que las clases de nivel superior se utilizan para crear clases reutilizables y con nombre.

En resumen, las clases anónimas son útiles cuando necesita crear una implementación única de una interfaz o clase abstracta sin la necesidad de una clase con nombre. Proporcionan una forma concisa de definir y crear instancias de una clase en una sola expresión.

Finalmente, para concluir esta sección, aquí hay una tabla que resume muchas propiedades de cada tipo de clase anidada:

| Propiedad | Clase anidada estática | Clase interna | Clase local | Clase anónima |
|-----------------------------------------|---------------------|---------------------|---------------------|---------------------|
| Asociación con clase externa | Asociado libremente (puede existir sin una instancia de la clase externa) | Estrechamente acoplado (no puede existir sin una instancia de la clase externa) | Estrechamente acoplado (asociado con una instancia del bloque envolvente) | Estrechamente acoplado (instanciado dentro de una expresión y asociado con una instancia del bloque adjunto) |
| Puede declarar miembros estáticos | Sí (incluidos métodos y campos estáticos) | No (excepto campos estáticos finales) | No (no se pueden declarar miembros estáticos, solo variables finales) | No (no se pueden declarar miembros estáticos, solo variables finales) |
| Acceso a miembros de la clase externa | Sólo miembros estáticos | Miembros estáticos y de instancia | Miembros estáticos y de instancia | Miembros estáticos y de instancia |
| Requiere referencia a una instancia de clase externa | No | Sí | Sí (variables implícitamente finales o efectivamente finales del ámbito adjunto) | Sí (variables implícitamente finales o efectivamente finales del ámbito adjunto) |
| Casos de uso típicos | Agrupar clases que se utilizan en un solo lugar, mejorando la encapsulación | Manejo de eventos, acceso a miembros privados de la clase externa, proporcionando código más legible y mantenible | Encapsular código complejo dentro de un método sin hacerlo visible afuera | Simplificar la creación de instancias de objetos que deben usarse una vez o donde la definición de clase es innecesaria |



## Clases y archivos fuente
Es importante tener en cuenta que puede tener una o más definiciones de clase en un archivo fuente de Java. Sin embargo, debes seguir estas reglas:

#### Regla de clase pública
Si una clase Java se declara como "pública", el nombre del archivo debe coincidir exactamente con el nombre de la clase pública, incluida la distinción entre mayúsculas y minúsculas, con la adición de la extensión ".java". Por ejemplo, si tiene una clase "pública" llamada "MiClase", entonces el archivo fuente debe llamarse "MiClase.java":

```java
// File name: MyClass.java
public class MyClass {
    // class body
}

```

#### Clase pública única por archivo
Un archivo fuente Java puede contener varias clases, pero solo puede tener una clase "pública". Si hay varias clases en un archivo y una de ellas se declara "pública", el nombre del archivo debe coincidir con el nombre de la clase "pública". Por ejemplo, si `PublicClass` es la clase `pública`, el archivo debe llamarse `PublicClass.java` y también puede contener `AnotherClass` que no es `pública`:

```java
// File name: PublicClass.java
public class PublicClass {
    // class body
}

class AnotherClass {
    // class body
}

```

#### Sin clase pública
Si no hay una clase "pública" en el archivo, se puede utilizar cualquier nombre. Por ejemplo, el siguiente archivo, `ManyClasses.java` contiene varias clases, ninguna de las cuales es `pública`:

```java
// File name: ManyClasses.java
class FirstClass {
    // class body
}

class SecondClass {
    // class body
}

```

#### Clases no públicas
Si existen varias clases no públicas en un solo archivo, no es necesario que el nombre del archivo coincida con el nombre de ninguna de las clases. Por ejemplo, puedes tener un archivo llamado `UtilityClasses.java` que contenga varias clases no públicas que no coincidan con este nombre:

```java
// File name: UtilityClasses.java
class HelperClass {
    // class body
}

class AnotherHelperClass {
    // class body
}

```

#### Sensibilidad entre mayúsculas y minúsculas
Java distingue entre mayúsculas y minúsculas. Si su clase se llama `CaseSensitiveClass`, el nombre del archivo debe coincidir exactamente (`CaseSensitiveClass.java`):

```java
// File name: CaseSensitiveClass.java
public class CaseSensitiveClass {
    // class body
}

```

Entonces, la principal restricción en Java es que un archivo fuente no puede contener más de una clase "pública". Esto ayuda a organizar el código y hacerlo más fácil de administrar. Cada clase pública debe estar en su propio archivo fuente y el nombre del archivo debe coincidir con el nombre de la clase (incluida la distinción entre mayúsculas y minúsculas) con la extensión `.java`.

Sin embargo, un único archivo fuente Java puede contener cualquier número de clases no públicas. Por defecto, estas clases son privadas de paquete y el archivo también puede contener clases anidadas "protegidas" o "privadas" dentro de clases "públicas" o de paquete privado. Esta flexibilidad permite que las clases lógicamente relacionadas se agrupen dentro del mismo archivo si no están destinadas al uso "público", lo que ayuda en la encapsulación y el diseño modular.

## Puntos clave
- La programación orientada a objetos (POO) organiza el código en objetos, que representan entidades del mundo real que contienen datos (atributos) y comportamientos (métodos).

- Las clases son planos o plantillas que definen los datos y comportamientos comunes a todos los objetos de ese tipo, mientras que los objetos son instancias distintas de una clase que contiene valores de datos únicos.

- Las principales etapas del ciclo de vida de un objeto en Java son la creación usando la palabra clave "nueva", el acceso a través de variables de referencia y la limpieza mediante el recolector de basura de Java cuando ya no se hace referencia a él.

- Las palabras clave son palabras reservadas en Java que definen la estructura y sintaxis de los programas Java. No se pueden utilizar como identificadores.

- Los comentarios son anotaciones en el código ignoradas por el compilador, utilizadas para describir o explicar el código. Java admite comentarios de una sola línea (`//`), de varias líneas (`/* */`) y de documentación (`/** */`).

- Los paquetes organizan clases, interfaces y subpaquetes relacionados en una sola unidad, proporcionando un nivel de control de acceso. La palabra clave `paquete` se utiliza para crear un paquete.

- Los modificadores de acceso (`public`, `protected`, `default`, `private`) controlan la visibilidad y accesibilidad de clases, métodos y variables de otras partes de una aplicación Java.

- Una clase se declara utilizando la palabra clave `class` seguida del nombre de la clase. Opcionalmente, puede extender una superclase usando "extiende" e implementar interfaces usando "implementos".

- Los campos son variables declaradas a nivel de clase para mantener el estado de un objeto. Pueden tener modificadores de acceso, especificadores (`static`, `final`), un tipo y un valor inicial opcional.

- Los métodos son bloques de código que realizan tareas específicas y, opcionalmente, devuelven valores. Se declaran con un modificador de acceso opcional, especificadores, tipo de retorno, nombre, parámetros y un cuerpo de método.

- La sobrecarga de métodos es la práctica de definir múltiples métodos con el mismo nombre pero diferentes listas de parámetros dentro de la misma clase.

- El compilador de Java determina qué método sobrecargado llamar en función del número, los tipos y el orden de los argumentos pasados ​​durante la invocación del método.

- Java sólo puede elegir un método sobrecargado si puede encontrar una coincidencia exacta para los argumentos o si puede encontrar una versión más específica mediante conversiones ampliadas (`int` a `long`, `int` `to double`, etc.).

- Varargs (argumentos de longitud variable) permiten que los métodos acepten un número no especificado de argumentos de un tipo específico. Para definir un método con varargs, use puntos suspensivos (...) después del tipo de datos del último parámetro en la firma del método.

- Un parámetro varargs debe ser el último parámetro en la lista de parámetros de un método y solo se permite un parámetro varargs por método.

- Los métodos con varargs pueden sobrecargarse, pero debes evitar la ambigüedad asegurándote de que las firmas de los métodos sean diferentes.

- Los constructores son métodos especiales utilizados para inicializar objetos, llamados cuando se crea una instancia de una clase usando la palabra clave "nueva". Tienen el mismo nombre que la clase y no tienen tipo de retorno.

- Los inicializadores de instancias son bloques de código que se ejecutan cuando se crea un objeto, similares a los constructores pero sin parámetros. Están encerrados en `{}` dentro del cuerpo de la clase.

- Los inicializadores estáticos son bloques de código ejecutados cuando una clase se carga en la memoria, antes de unaSe crean y instancias. Se definen utilizando la palabra clave "estática" seguida de "{}".

- Cada clase extiende implícitamente la clase `java.lang.Object`, heredando métodos fundamentales como `toString()`, `equals()` y `hashCode()`.

- Las clases anidadas son clases definidas dentro de otra clase. Pueden ser clases anidadas estáticas, clases internas (clases anidadas no estáticas), clases locales o clases anónimas.

- Las clases anidadas estáticas están asociadas con la propia clase externa y pueden acceder a sus miembros estáticos directamente. Se pueden crear instancias de forma independiente, sin una instancia de la clase externa.

- Las clases internas (clases anidadas no estáticas) están asociadas con una instancia de la clase externa y tienen acceso a miembros estáticos y no estáticos de la clase externa. Requieren que se cree una instancia de la clase externa.

- Las clases locales se definen dentro de un bloque, normalmente un método, y tienen acceso a variables finales o efectivamente finales desde el ámbito adjunto. No pueden tener modificadores de acceso y solo son visibles dentro del bloque que los define.

- Las clases anónimas se definen dentro de una expresión y se utilizan para crear implementaciones únicas de interfaces o clases abstractas. No tienen nombre y se crean instancias en el momento de la declaración.

- Si una clase se declara "pública", el nombre del archivo fuente de Java debe coincidir exactamente con el nombre de la clase "pública", incluida la distinción entre mayúsculas y minúsculas, con la extensión ".java".

- Un archivo fuente Java puede contener múltiples definiciones de clases, pero solo una de ellas puede declararse "pública". Si no hay una clase "pública", el nombre del archivo puede ser diferente de los nombres de las clases.


## Preguntas de práctica

**1. Considere el siguiente fragmento de código:**

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("Java");
        StringBuilder sb2 = new StringBuilder("Python");
        sb1 = sb2;
        // More code here
    }
}

```

Después de la ejecución del código anterior, ¿cuál de las siguientes afirmaciones es verdadera con respecto a la recolección de basura?

**A)** Tanto `sb1` como `sb2` son elegibles para la recolección de basura.  
**B)** Sólo el objeto `StringBuilder` al que inicialmente hace referencia `sb1` es elegible para la recolección de basura.  
**C)** Sólo el objeto `StringBuilder` al que inicialmente hace referencia `sb2` es elegible para la recolección de basura.  
**D)** Ninguno de los objetos `StringBuilder` es elegible para la recolección de basura.



**2. ¿Cuáles de las siguientes son palabras clave reservadas en Java? (Elija todas las que correspondan).**

**A)** `implementar`  
**B)** `matriz`  
**C)** `volátil`  
**D)** `extends`



**3. Considere el siguiente fragmento de código:**

```java
1. // calculates the sum of numbers
2. public class Calculator {
3.     /* Adds two numbers
4.      * @param a the first number
5.      * @param b the second number
6.      * @return the sum of a and b
7.      */
8.     public int add(int a, int b) {
9.         // return the sum
10.        return a + b;
11.    }
12.    //TODO: Implement subtract method
13.}

```

¿Cuáles de las siguientes afirmaciones son verdaderas sobre los comentarios en el código anterior? (Elija todas las que correspondan).

**A)** La línea 1 es un ejemplo de un comentario de una sola línea.  
**B)** Las líneas 3-7 demuestran el uso de un comentario javadoc.  
**C)** La línea 9 utiliza un comentario javadoc para explicar el método `add`.  
**D)** La línea 12 utiliza un comentario especial `TODO`, diferente de un comentario de una sola línea.  
**E)** Las líneas 3-7 son un comentario de bloque que se utiliza como si fuera un comentario javadoc.  


**4. Considere que tiene los siguientes dos archivos Java ubicados en el mismo directorio:**

```java
// File 1: Calculator.java
package math;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

// File 2: Application.java
package app;

import math.Calculator;

public class Application {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(5, 3));
    }
}

```

¿Cuál de las siguientes afirmaciones es verdadera con respecto a las declaraciones "paquete" e "importación" en Java?

**A)** La declaración `import` en `Application.java` es innecesaria porque ambas clases están en el mismo directorio.  
**B)** La declaración `import` en `Application.java` es necesaria para usar la clase `Calculator` porque pertenecen a paquetes diferentes.  
**C)** No se podrá acceder a la clase `Calculadora` en `Application.java` debido a que está en un directorio diferente.  
**D)** Eliminar la declaración `paquete` de ambos archivos permitirá que `Application.java` use `Calculator` sin una declaración `import`, independientemente de la estructura del directorio.



**5. Considere los niveles de acceso predeterminados proporcionados por los cuatro modificadores de acceso de Java: "público", "protegido", "predeterminado" (sin modificador) y "privado". ¿Cuál de las siguientes afirmaciones describe correctamente los niveles de acceso otorgados por estos modificadores? (Elija todas las que correspondan).**

**A)** Cualquier otra clase en el mismo paquete o en cualquier otro paquete puede acceder a una clase o miembro "público".  
**B)** Cualquier clase en su propio paquete puede acceder a un miembro "protegido", pero desde fuera del paquete, solo las clases que extienden la clase que contiene el miembro "protegido".  
**C)** Cualquier clase en el mismo paquete puede acceder a un miembro con acceso `predeterminado` (sin modificador), pero no desde una clase en un paquete diferente.  
**D)** Solo se puede acceder a un miembro "privado" mediante métodos que sean miembros de la misma clase o dentro del mismo archivo.  
**E)** Cualquier clase del programa Java puede acceder a un miembro "protegido", independientemente del paquete.



**6. ¿Cuál de las siguientes declaraciones de clase demuestra correctamente el uso de modificadores de acceso, la palabra clave "clase" y las convenciones de nomenclatura de clases en Java?**

**A)** `clase vehículo público { }`  
**B)** `vehículo de clase pública { }`  
**C)** `Vehículo de clase pública { }`  
**D)** `Vehículo de clase pública { }`  
**E)** `claseVehículo público { }`



**7. Considere el siguiente fragmento de código:**

```java
public class Counter {
    public static int COUNT = 0;
    
    public Counter() {
        COUNT++;
    }
    
    public static void resetCount() {
        COUNT = 0;
    }
    
    public int getCount() {
        return COUNT;
    }
}

```

¿Cuáles de las siguientes afirmaciones son verdaderas sobre los miembros "estáticos" y de instancia dentro de la clase "Contador"? (Elija todas las que correspondan).

**A)** Se puede acceder a la variable `COUNT` directamente usando el nombre de la clase sin crear una instancia de `Counter`.  
**B)** El método `getCount()` es un ejemplo de método estático porque devuelve el valor de una variable estática.  
**C)** Cada vez que se crea una nueva instancia de `Counter`, se incrementa la variable `COUNT`.  
**D)** El método `resetCount()` restablece la variable `COUNT` a 0 para todas las instancias de `Counter`. 



**8. ¿Cuáles de los siguientes son identificadores de nombres de campos válidos en Java? (Elija todas las que correspondan).**

**A)** `int _edad;`  
**B)** `doble 2doValor;`  
**C)** `booleano es_válido;`  
**D)** `Cadena $nombre;`  
**E)** `char #char;`



**9. Considere la sintaxis utilizada para declarar métodos en una clase. ¿Cuál de las siguientes declaraciones de métodos es correcta según las reglas de sintaxis de Java?**

**A)** `int public static final computeSum(int num1, int num2) { return num1 + num2 }`  
**B)** `private void updateRecord(int id) throws IOException {}`  
**C)** `checkStatus booleano sincronizado [estado int] { estado de retorno == 1; }`  
**D)** `flotar calcularArea() {}`



**10. Dadas las declaraciones de métodos siguientes, ¿cuáles de ellos tienen la misma firma de método?**

**A)** `public void update (int id, String value)`  
**B)** `private void update (int identifier, String data)`  
**C)** `public boolean update (String value, int id)`  
**D)** `void update (String value, int id)`  
**E)** `la void update protegida (int id, int valor) arroja IOException`



**11. Dada esta clase:**

```java
public class AccountManager {
    private void resetAccountPassword(String accountId) {
        // Implementation code here
    }
    
    void auditTrail(String accountId) {
        // Implementation code here
    }
    
    protected void notifyAccountChanges(String accountId) {
        // Implementation code here
    }
    
    public void updateAccountInformation(String accountId) {
        // Implementation code here
    }
}

```

¿Cuál de las siguientes afirmaciones describe correctamente la accesibilidad de los métodos dentro de la clase `AccountManager` desde una clase en el mismo paquete y desde una clase en un paquete diferente?

**A)** Se puede acceder al método `resetAccountPassword` desde cualquier clase dentro del mismo paquete, pero no desde una clase en un paquete diferente.  
**B)** Se puede acceder al método `auditTrail` desde cualquier clase dentro del mismo paquete y desde subclases en diferentes paquetes.  
**C)** Se puede acceder al método `notifyAccountChanges` desde cualquier clase dentro del mismo paquete y desde subclases en diferentes paquetes.  
**D)** Se puede acceder al método `updateAccountInformation` desde cualquier clase, independientemente de su paquete.



**12. ¿Cuál será el resultado de este programa?**

```java
public class TestPassByValue {
    public static void main(String[] args) {
        int originalValue = 10;
        TestPassByValue test = new TestPassByValue();
        System.out.println("Before calling changeValue: " + originalValue);
        test.changeValue(originalValue);
        System.out.println("After calling changeValue: " + originalValue);
    }

    public void changeValue(int value) {
        value = 20;
    }
}

```

**A)**

```

Before calling changeValue: 10  
After calling changeValue: 20

```

**B)**

```

Before calling changeValue: 10  
After calling changeValue: 10

```

**DO)**

```

Before calling changeValue: 20  
After calling changeValue: 20

```

**D)**

```

Before calling changeValue: 20  
After calling changeValue: 10

```

**13. ¿Cuál será el resultado del siguiente programa?**

```java
public class Test {
    public static void main(String[] args) {
        print(null);
    }

    public static void print(Object o) {
        System.out.println("Object");
    }

    public static void print(String s) {
        System.out.println("String");
    }
}

```

**A)** `Object`  
**B)** `String`  
**C)** La compilación falla  
**D)** Se lanza una excepción de tiempo de ejecución  



**14. ¿Cuál de las siguientes declaraciones de métodos utiliza correctamente varargs? Elija todo lo que corresponda.**

**A)** `public void print (cadena... mensajes, int count)`  
**B)** `public void print (int count, String... mensajes)`  
**C)** `public void print (String... messages...)`  
**D)** `public void print (Cadena[]... mensajes)`  
**E)** `public void print (cadena... mensajes, cadena lastMessage)`  




**15. Dada la clase `Vehículo`:**

```java
public class Vehicle {
    private String type;
    private int maxSpeed;

    public Vehicle(String type) {
        this.type = type;
    }

    public Vehicle(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    // Additional methods here
}

```

¿Cuál de las siguientes afirmaciones es verdadera con respecto a sus constructores?

**A)** La clase `Vehicle` demuestra la sobrecarga del constructor al tener múltiples constructores con diferentes listas de parámetros.  
**B)** La clase `Vehicle` se compilará con un error porque no proporciona un constructor predeterminado.  
**C)** Es posible crear una instancia de `Vehicle` con `type` y `maxSpeed` establecidos en valores específicos mediante una única llamada al constructor. 
**D)** Llamar a cualquiera de los constructores inicializará los campos `type` y `maxSpeed` de la clase `Vehicle`.



**16. Considere la siguiente clase con un bloque inicializador de instancia:**

```java
public class Library {
    private int bookCount;
    private List<String> books;

    {
        books = new ArrayList<>();
        books.add("Book 1");
        books.add("Book 2");
        // Instance initializer block
    }

    public Library(int bookCount) {
        this.bookCount = bookCount + books.size();
    }

    public int getBookCount() {
        return bookCount;
    }

    // Additional methods here
}

```

Dada la clase `Biblioteca` anterior, ¿cuál de las siguientes afirmaciones describe con precisión la función y el efecto del bloque inicializador de instancia?

**A)** El bloque inicializador de instancia se ejecuta antes que el constructor, inicializando la lista de `libros` y agregándole dos libros.  
**B)** El bloque inicializador de instancia reemplaza la necesidad de un constructor en la clase `Biblioteca`.  
**C)** Los bloques inicializadores de instancia no pueden inicializar variables de instancia como "libros".  
**D)** Si se crean varias instancias de `Biblioteca`, el bloque inicializador de instancia se ejecutará cada vez antes que el constructor, asegurando que la lista de `libros` esté inicializada y completada para cada objeto.



**17. Considere la siguiente clase Java con un bloque inicializador "estático":**

```java
public class Configuration {
    private static Map<String, String> settings;
    
    static {
        settings = new HashMap<>();
        settings.put("url", "https://eherrera.net");
        settings.put("timeout", "30");
        // Static initializer block
    }

    public static String getSetting(String key) {
        return settings.get(key);
    }

    // Additional methods here
}

```

Dada la clase "Configuración" anterior, ¿cuál de las siguientes afirmaciones describe con precisión la función y el efecto del bloque inicializador "estático"?

**A)** El bloque inicializador `static` se ejecuta solo una vez cuando la clase se carga por primera vez en la memoria, inicializando el mapa de `configuración` con los valores predeterminados.  
**B)** El bloque inicializador `static` permite que los métodos de instancia modifiquen el mapa de `configuración` sin crear una instancia de la clase `Configuración`.  
**C)** Los bloques inicializadores `estáticos` se ejecutan cada vez que se crea una nueva instancia de la clase `Configuración`.  
**D)** El bloque inicializador `static` se ejecuta antes que cualquier bloque o constructor inicializador de instancia, cuando se crea una instancia de la clase.



**18. Considere la siguiente definición de clase:**

```java
public class InitializationOrder {
    static {
        System.out.println("1. Static initializer");
    }

    private static int staticValue = initializeStaticValue();

    private int instanceValue = initializeInstanceValue();

    {
        System.out.println("3. Instance initializer");
    }

    public InitializationOrder() {
        System.out.println("4. Constructor");
    }

    private static int initializeStaticValue() {
        System.out.println("2. Static value initializer");
        return 0;
    }

    private int initializeInstanceValue() {
        System.out.println("3. Instance value initializer");
        return 0;
    }

    public static void main(String[] args) {
        new InitializationOrder();
    }
}

```

Cuando se ejecuta el método `main` de la clase `InitializationOrder`, ¿cuál es el orden correcto de ejecución para los bloques de inicialización, llamadas a métodos y constructor?

**A)**

```

1. Static initializer
   2. Static value initializer
   3. Instance initializer
   3. Instance value initializer
   4. Constructor

```

**B)**

```

1. Static initializer
   2. Static value initializer
   3. Instance value initializer
   3. Instance initializer
   4. Constructor

```

**DO)**

```

1. Static initializer
   3. Instance initializer
   2. Static value initializer
   3. Instance value initializer
   4. Constructor

```

**D)**

```

2. Static value initializer
   1. Static initializer
   3. Instance value initializer
   3. Instance initializer
   4. Constructor

```

**19. Considere una clase `CustomObject` que no anula explícitamente ningún método de `java.lang.Object`:**

```java
public class CustomObject {
    // Class implementation goes here
}

```

¿Cuál de las siguientes afirmaciones refleja correctamente los resultados cuando se utilizan métodos de `java.lang.Object` con instancias de `CustomObject`? (Elija todas las que correspondan).

**A)** Al invocar `toString()` en una instancia de `CustomObject` se devolverá una `String` que incluye el nombre de la clase seguido del símbolo `@` y el código hash del objeto.  
**B)** Llamar a `equals(Object obj)` en dos instancias diferentes de `CustomObject` que tienen contenido idéntico devolverá `true` porque son instancias de la misma clase.  
**C)** El uso de `hashCode()` en cualquier instancia de `CustomObject` generará un número entero único que permanece consistente en múltiples invocaciones dentro de la misma ejecución de un programa.  
**D)** El método `clone()` se puede utilizar para crear una copia superficial de una instancia de `CustomObject` sin la necesidad de que `CustomObject` implemente la interfaz `Cloneable`.  



**20. Considere el siguiente fragmento de código que demuestra el uso de una clase anidada estática:**

```java
public class OuterClass {
    private static String message = "Hello, World!";

    static class NestedClass {
        void printMessage() {
            // Note: A static nested class can access the static members of its outer class.
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        OuterClass.NestedClass nested = new OuterClass.NestedClass();
        nested.printMessage();
    }
}

```

¿Cuál de las siguientes afirmaciones es cierta con respecto a las clases anidadas estáticas en Java?

**A)** Una clase anidada estática puede acceder directamente a miembros estáticos y no estáticos de su clase adjunta.  
**B)** Pueden existir instancias de una clase anidada estática sin una instancia de su clase adjunta.  
**C)** Una clase anidada estática solo se puede crear una instancia dentro del método estático de su clase adjunta.  
**D)** Las clases anidadas estáticas no se consideran miembros de su clase adjunta y no pueden acceder a ningún miembro de la clase adjunta.



**21. Considere el siguiente fragmento de código que demuestra el uso de una clase anidada (interna) no estática:**

```java
public class OuterClass {
    private String message = "Hello, World!";

    class InnerClass {
        void printMessage() {
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.printMessage();
    }
}

```

¿Cuál de las siguientes afirmaciones es cierta con respecto a las clases anidadas (internas) no estáticas en Java?

**A)** Una clase anidada no estática puede acceder directamente a miembros estáticos y no estáticos de su clase adjunta.  
**B)** Las instancias de una clase anidada no estática pueden existir independientemente de una instancia de su clase adjunta.  
**C)** Una clase anidada no estática no puede acceder directamente a los miembros no estáticos de su clase adjunta.  
**D)** Las clases anidadas no estáticas deben declararse estáticas para acceder a los miembros estáticos de su clase adjunta.



**22. Considere el siguiente fragmento de código que demuestra el uso de una clase local dentro de un método:**

```java
public class LocalClassExample {
    public void printEvenNumbers(int[] numbers, int max) {
        class EvenNumberPrinter {
            public void print() {
                for (int number : numbers) {
                    if (number % 2 == 0 && number <= max) {
                        System.out.println(number);
                    }
                }
            }
        }
        EvenNumberPrinter printer = new EvenNumberPrinter();
        printer.print();
    }

    public static void main(String[] args) {
        LocalClassExample example = new LocalClassExample();
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        example.printEvenNumbers(numbers, 6);
    }
}

```

¿Cuál de las siguientes afirmaciones describe correctamente las clases locales en Java, según el ejemplo proporcionado?

**A)** Las clases locales se pueden declarar dentro de cualquier bloque que preceda a una declaración.  
**B)** Las instancias de una clase local se pueden crear y utilizar fuera del bloque donde se define la clase local.  
**C)** Las clases locales son un tipo de clase anidada estática y pueden acceder directamente a miembros estáticos y no estáticos de la clase adjunta.  
**D)** Las clases locales pueden acceder a variables locales y parámetros del bloque adjunto solo si se declaran "finales" o efectivamente finales. 



**23. Considere el siguiente fragmento de código Java que demuestra el uso de una clase anónima:**

```java
public class HelloWorld {
    interface HelloWorldInterface {
        void greet();
    }

    public void sayHello() {
        HelloWorldInterface myGreeting = new HelloWorldInterface() {
            @Override
            public void greet() {
                System.out.println("Hello, world!");
            }
        };
        myGreeting.greet();
    }

    public static void main(String[] args) {
        new HelloWorld().sayHello();
    }
}

```

¿Cuál de las siguientes afirmaciones es cierta sobre las clases anónimas en Java?

**A)** Las clases anónimas pueden implementar interfaces y ampliar clases sin la necesidad de declarar una clase con nombre.  
**B)** Una clase anónima debe anular todos los métodos de la superclase o interfaz que declara que está implementando o ampliando.  
**C)** Las clases anónimas pueden tener constructores como los tienen las clases con nombre.  
**D)** Las instancias de clases anónimas no se pueden pasar como argumentos a los métodos.


**24. ¿Cuál de las siguientes afirmaciones refleja con precisión una regla válida sobre cómo se organizan las clases y los archivos fuente?**

**A)** Un archivo fuente puede contener varias clases públicas.  
**B)** Las clases privadas se pueden declarar en el nivel superior en un archivo fuente.  
**C)** Se debe declarar una clase `pública` en un archivo fuente que tenga el mismo nombre que la clase.  
**D)** Si un archivo fuente contiene más de una clase, ninguna de las clases puede ser "pública".