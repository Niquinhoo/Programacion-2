-"None"
layout: answer

title: "Capítulo TRES"
subtitle: "Trabajar con registros y enumeraciones"
exam_objectives:
  - "Cree clases y registros, y defina y utilice campos y métodos estáticos y de instancia, constructores e inicializadores estáticos y de instancia."
  - "Cree y utilice tipos de enumeración con campos, métodos y constructores."
-"None"## Respuestas

**1. La respuesta correcta es C.**

**Explicación:**

- **A)** El registro `Empleado` define explícitamente un constructor público que inicializa sus campos. 
  - Esta opción es incorrecta porque el registro "Empleado" no define explícitamente un constructor "público". Los registros generan automáticamente un constructor "público" con los mismos parámetros que la declaración del registro.

- **B)** Los campos `nombre` y `edad` se pueden reasignar a nuevos valores después de crear un objeto `Empleado`.
  - Esta opción es incorrecta ya que los campos dentro de un registro son "finales", lo que significa que no se pueden reasignar a nuevos valores después de que se haya creado un objeto "Empleado". Esta inmutabilidad es una de las características clave de los registros.

- **C)** El registro `Empleado` crea implícitamente un constructor `público` y campos `privados` `finales` para `nombre` y `edad`.
  - Esta es la opción correcta. Los registros crean implícitamente un constructor público para los campos del registro y también hacen que estos campos sean "privados" y "finales". Esto significa que no es necesario escribir manualmente código repetitivo para constructores, captadores o para garantizar la inmutabilidad.

- **D)** Es obligatorio definir captadores para los campos `nombre` y `edad` en el registro `Empleado`.
  - Esta opción es incorrecta porque los registros generan automáticamente métodos públicos para acceder a los campos, conocidos como métodos de acceso, que esencialmente actúan como captadores. Por lo tanto, no es obligatorio (ni siquiera posible) definir captadores separados para los campos.



**2. La respuesta correcta es B.**

**Explicación:**

- **A)** El campo `saldo` se puede modificar utilizando un método de configuración pública dentro del registro `Cuenta`.
  - Esta opción es incorrecta porque los registros en Java no admiten métodos de establecimiento públicos para sus campos. Los campos de un registro son "finales" y no se pueden modificar después de la construcción del objeto, lo cual es un aspecto clave de su diseño para imponer la inmutabilidad.

- **B)** Una vez que se crea un objeto "Cuenta", su "id" y su "saldo" no se pueden cambiar.
  - Esta es la opción correcta. Los registros son inmutables por diseño, lo que significa que una vez que se crea un objeto de registro, los valores de sus campos (`id` y `balance` en este caso) no se pueden cambiar. Esta inmutabilidad se garantiza haciendo que los campos sean "privados" y "finales", y no proporcionando métodos de establecimiento.

- **C)** La inmutabilidad de los registros se puede evitar definiendo métodos de configuración personalizados para los campos `id` y `balance`. 
  - Esta opción es incorrecta. No se pueden definir métodos de establecimiento personalizados para los campos de registro porque los registros no permiten definir mutadores para sus componentes.

- **D)** Los registros permiten modificar los valores de los campos si se accede a ellos directamente, sin utilizar métodos de establecimiento.
  - Esta opción es incorrecta porque los campos de un registro son implícitamente "finales" y privados, lo que significa que no se pueden modificar directamente ni mediante métodos de configuración. El diseño de registros impone esta inmutabilidad para garantizar que las instancias de registros actúen como verdaderos portadores de datos inmutables.


**3. La respuesta correcta es D.** 

**Explicación:**

- **A)** `Producto p = nuevo Producto();`
  - Esta opción es incorrecta porque el constructor predeterminado sin parámetros no existe para los registros en Java. Los registros requieren que todos sus campos se especifiquen en el momento de la creación de instancias.

- **B)** `Producto p = Producto(101, "Café", 15,99);`
  - Esta opción es incorrecta porque la sintaxis utilizada aquí no es válida para crear una nueva instancia de un registro en Java. La sintaxis correcta para crear instancias de un registro implica el uso de la palabra clave "nueva" seguida del nombre del registro y los parámetros entre paréntesis.

- **C)** `Producto p = {101, "Café", 15,99};`
  - Esta opción es incorrecta ya que utiliza por error la sintaxis para la inicialización de la matriz. En Java, no se pueden crear instancias de objetos, incluidos los registros, utilizando llaves sin la palabra clave "nueva" y el constructor adecuado.

- **D)** `Producto p = nuevo Producto(101, "Café", 15,99);`
  - Esta es la opción correcta. Los registros en Java se crean instancias utilizando la palabra clave "nueva" seguida de la rconstructor del registro, que requiere pasar todos los campos definidos en el registro. Esta sintaxis crea correctamente un nuevo registro "Producto" con el "id", "nombre" y "precio" proporcionados.


**4. La respuesta correcta es B.**

**Explicación:**

- **A)** Los registros no pueden implementar interfaces porque son "finales" e inmutables por diseño, lo que impide cualquier forma de personalización del comportamiento.
  - Esta opción es incorrecta. Los registros en Java pueden implementar interfaces. La finalidad e inmutabilidad de los registros no les impide implementar interfaces, que pueden usarse para agregar comportamientos u obligaciones contractuales a un registro.

- **B)** Este registro implementa correctamente la interfaz `Comparable`, permitiendo que los objetos `Item` se ordenen según su `price`.
  - Esta es la opción correcta. La definición de registro proporcionada implementa correctamente la interfaz `Comparable<Item>` anulando el método `compareTo`. Esta personalización permite ordenar las instancias del registro "Artículo" según el campo "precio", lo que demuestra que los registros pueden implementar interfaces y anular sus métodos según sea necesario.

- **C)** La implementación de interfaces en registros está restringida solo a interfaces funcionales debido a su naturaleza inmutable.
  - Esta opción es incorrecta. No existe tal restricción que limite los registros a implementar únicamente interfaces funcionales. Los registros pueden implementar cualquier interfaz, incluidas aquellas con múltiples métodos abstractos, siempre que el registro proporcione implementaciones para los métodos abstractos definidos en la interfaz.

- **D)** El método `compareTo` no se puede anular en los registros porque la anulación de métodos no se admite en los tipos de registros.
  - Esta opción es incorrecta. Los registros pueden anular métodos de las interfaces que implementan, incluido el método `compareTo` de la interfaz `Comparable` en este ejemplo. La anulación de métodos es un aspecto clave de la implementación de interfaces y es totalmente compatible con los tipos de registros en Java.


**5. Las respuestas correctas son A y D.**

**Explicación:**

- **A)**```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```- Esta opción es correcta. Demuestra una declaración válida de una enumeración en Java. Las enumeraciones se utilizan para definir un conjunto de constantes con nombre y esta sintaxis es la forma estándar de declararlas. El modificador de acceso "público" hace que esta enumeración sea accesible desde cualquier otra clase.

- **B)**```java
enum Month {
    private JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
}
```- Esta opción es incorrecta. Las enumeraciones no pueden tener modificadores de acceso "privados" para sus constantes. Las constantes de enumeración son implícitamente "públicas", "estáticas" y "finales" y deben declararse sin modificadores de acceso.

- **C)**```java
protected enum Season {
    WINTER, SPRING, SUMMER, FALL
}
```- Esta opción es incorrecta porque las enumeraciones no se pueden declarar con niveles de acceso "protegido" o "privado". Las enumeraciones son implícitamente "públicas" si se definen fuera de una clase. Si se definen dentro de una clase, pueden tener cualquier nivel de acceso, pero la palabra clave "protegida" no se puede usar en el nivel de enumeración en sí.

- **D)**```java
enum Status {
    ACTIVE, INACTIVE, DELETED;

    public void printStatus() {
        System.out.println("Current status: " + this);
    }
}
```- Esta opción es correcta. Muestra una enumeración `Estado` con un método `printStatus()`. Las enumeraciones en Java pueden contener métodos, campos, constructores e implementar interfaces. Esto demuestra la capacidad de las enumeraciones para tener métodos, lo que hace que esta declaración sea válida.


**6. La respuesta correcta es A.**

**Explicación:**

- **A)** `1`
   - Esta opción es correcta. El método `ordinal()` devuelve el ordinal de esta constante de enumeración (su posición en su declaración de enumeración, donde a la constante inicial se le asigna un ordinal de cero). Dado que "VERDE" es la segunda constante de enumeración declarada en la enumeración "Color", su valor ordinal es 1.

- **B)** `2`
  - Esta opción es incorrecta. El valor ordinal de "AZUL" sería 2, no "VERDE", porque "AZUL" es la tercera constante declarada en la enumeración "Color".

- **C)** `0`
  - Esta opción es incorrecta. El valor ordinal de "RED" es 0, ya que es la primera constante declarada en la enumeración "Color".

- **D)** `Color.VERDE`
  - Esta opción es incorrecta. El método `ordinal()` devuelve un número entero que representa la posición de la constante de enumeración en la declaración, no la constante de enumeración en sí.


**7. La respuesta correcta es D.**

**Explicación:**

- **A)**```java
public enum Size {
    SMALL, MEDIUM, LARGE;
    public static void printSize() {
        System.out.println("The size is " + this.name());
    }
}
```- Esta opción es incorrecta porque el método `printSize()` está definido como `estático`, lo que significa que no puede acceder a la referencia `this`. Los métodos estáticos en enumeraciones no pueden acceder directamente a las constantes de enumeración sin especificar la constante explícitamente o pasar una referencia.

- **B)**```java
enum Flavor {
    CHOCOLATE, VANILLA, STRAWBERRY;
    void printFlavor() {
        System.out.println("Flavor: " + Flavor.name);
    }
}
```- Esta opción es incorrecta porque la propiedad "nombre" de una constante de enumeración es "privada". Sólo puedes acceder a él usando la referencia `this` y el método `name()` (`this.name()`).

- **C)**```java
protected enum Direction {
    NORTH, SOUTH, EAST, WEST;
    private printDirection() {
        System.out.println("Going " + this.toString());
    }
}
```- Esta opción es incorrecta por dos motivos. Primero, "protegido" no es un modificador de acceso válido para una enumeración de nivel superior, las enumeraciones de nivel superior solo pueden ser "públicas" o privadas de paquete (sin modificador). En segundo lugar, al método `printDirection()` le falta un tipo de retorno (por ejemplo, `void`).

- **D)**```java
public enum Season {
    WINTER, SPRING, SUMMER, FALL;
    public void printSeason() {
        System.out.println("The season is " + this.name());
    }
}
```- Esta es la opción correcta. El método `printSeason()` está definido correctamente: es `público`, no estático y utiliza la referencia `this` para acceder al nombre de la constante de enumeración actual. Este método proporciona correctamente un comportamiento personalizado para cada constante de enumeración, lo que le permite imprimir un mensaje que indica la temporada actual.