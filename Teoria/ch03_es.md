-"None"
layout: chapter

title: "Capítulo TRES"
subtitle: "Trabajar con registros y enumeraciones"
exam_objectives:
  - "Cree clases y registros, y defina y utilice campos y métodos estáticos y de instancia, constructores e inicializadores estáticos y de instancia."
  - "Cree y utilice tipos de enumeración con campos, métodos y constructores."


previous_link: "/ch02.html"
previous_title: "Utilizando el enfoque orientado a objetos de Java - Parte 2"
next_link: "/ch04.html"
next_title: "Trabajar con datos"
answers_link: "/ch03a.html"
-"None"## Contenido del capítulo

- [Registros](#registros)
    - [Presentación de registros](#introducción-registros)
    - [Registro de inmutabilidad] (#record-inmutability)
    - [Inicializando registros](#inicializando-registros)
    - [Personalizar registros](#personalizar-registros)
- [Enumeraciones](#enumeraciones)
    - [Presentación de enumeraciones] (#introducción de enumeraciones)
    - [Declarar una enumeración](#declarar-una-enum)
    - [Métodos especiales de una enumeración](#métodos-especiales-de-una-enum)
    - [Personalizar enumeraciones](#customizing-enums)
- [Puntos clave](#puntos clave)
- [Preguntas de práctica](#preguntas-de-practica)

---

## Registros

### Introducción de registros

Los registros proporcionan una forma más concisa de declarar clases que están pensadas principalmente como simples soportes de datos. Puede pensar en los registros como un tipo especial de clase que está específicamente diseñada para almacenar datos inmutables, algo así como una caja fuerte resistente y a prueba de manipulaciones para su información.

Pero ¿qué son exactamente los registros? Bueno, esencialmente, un registro es una clase final que genera automáticamente un constructor, campos `privados` `final` para los parámetros que usted define e implementaciones de los métodos `equals()`, `hashCode()` y `toString()` basados ​​en esos campos. Esto significa que los registros le brindan una forma abreviada de crear una clase que encapsule datos, sin tener que escribir mucho código repetitivo.

A continuación se muestra un diagrama que muestra la estructura básica y los componentes de una declaración de registro:```
┌─────────────────────────────────────────────────┐
│ public record Person(String name, int age) {    │
│                                                 │
│  ┌─────────────────────────────────────────┐    │
│  │ Implicit Components                     │    │
│  │ ● Private final fields                  │    │
│  │ ● Public constructor                    │    │
│  │ ● Public accessor methods               │    │
│  │ ● equals(), hashCode(), toString()      │    │
│  └─────────────────────────────────────────┘    │
│                                                 │
│  ┌─────────────────────────────────────────┐    │
│  │ Customizable Components                 │    │
│  │ ● Compact constructor                   │    │
│  │ ● Additional methods                    │    │
│  │ ● Static fields and methods             │    │
│  └─────────────────────────────────────────┘    │
│                                                 │
└─────────────────────────────────────────────────┘
```Y aquí hay un ejemplo de una definición de registro:```java
record Person(String name, int age) {}
```Con solo esta línea, hemos definido un registro de "Persona" que tiene dos campos: "nombre" y "edad". El registro genera automáticamente un constructor que toma esos campos como parámetros, por lo que podemos crear instancias del registro como esta:```java
Person john = new Person("John Doe", 30);
```Una cosa importante que hay que entender acerca de los registros es que no son sólo una abreviatura de las clases de escritura. Si bien proporcionan una sintaxis más concisa, los registros tienen algunas características únicas que los diferencian de las clases regulares. Uno de los más significativos es que los registros son implícitamente definitivos, lo que significa que no pueden ser ampliados por otras clases. Esto refuerza su función como soportes de datos simples e inmutables.

Además, los registros son implícitamente estáticos cuando se declaran como tipos anidados. Esto significa que no tienen una referencia a la instancia de la clase adjunta:```java
public class OuterClass {

    // Nested record
    public record NestedRecord(int value) {
    }

    // ...
}
```En el ejemplo, "NestedRecord" es un registro anidado dentro de "OuterClass". Es implícitamente estático, lo que significa que se puede crear una instancia sin una instancia de "OuterClass":```java
OuterClass.NestedRecord nestedRecord = new OuterClass.NestedRecord(8);
```Entonces, ¿cuándo deberías usar un registro en lugar de una clase? Los registros son ideales para situaciones en las que es necesario representar una estructura de datos simple e inmutable, como un punto con coordenadas xey, o una persona con nombre y edad. En estos casos, usar un registro puede ahorrarle mucho tiempo y reducir la verbosidad de su código:```java
record Point(int x, int y) {}
```Por otro lado, si necesita una estructura de datos más compleja que requiera un comportamiento adicional o un estado mutable, una clase normal sigue siendo el camino a seguir. Los registros no están destinados a reemplazar las clases por completo, sino a complementarlas proporcionando una solución optimizada para un caso de uso específico.

### Registro de inmutabilidad

Una de las características definitorias de los registros es su inmutabilidad. Cuando decimos que los registros son inmutables, significa que una vez que se crea una instancia de un registro, su estado no se puede cambiar. Esto se debe al hecho de que todos los campos de un registro son implícitamente finales, lo que significa que deben inicializarse cuando se crea una instancia del registro y no pueden modificarse posteriormente.```java
record Person(String name, int age) {
    void birthday() {
        age++; // Compile-time error: Cannot assign a value to final variable age
    }
}
```Dado que los registros están diseñados para ser inmutables, no hay forma de hacer que los campos individuales sean mutables. Si necesita modificar los valores de los campos después de la creación de instancias, es una buena indicación de que un registro podría no ser la opción correcta para su caso de uso y una clase normal sería más apropiada.

En cuanto a la inmutabilidad, existen algunas razones por las que a menudo se prefieren los registros a los objetos mutables:

1. Los registros son inherentemente seguros para subprocesos porque su estado no se puede modificar después de su creación, lo que elimina el riesgo de problemas de acceso simultáneo.
2. Es más sencillo razonar sobre los registros y menos propensos a errores porque su estado permanece constante durante toda su vida.
3. Los registros se pueden compartir y reutilizar de forma segura sin necesidad de realizar copias defensivas.

Sin embargo, es importante tener en cuenta que la inmutabilidad en los registros solo se aplica al registro en sí y a sus campos. Si un registro contiene una referencia a un objeto mutable, como una lista o una matriz, ese objeto aún se puede modificar aunque el registro en sí sea inmutable:```java
record Numbers(List<Integer> values) {}

Numbers numbers = new Numbers(new ArrayList<>(List.of(1, 2, 3)));
numbers.values().add(4); // The list can still be modified
```En este ejemplo, aunque el registro "Números" es inmutable, la "Lista" almacenada en su campo "valores" aún se puede modificar porque es un objeto mutable.

Por eso, al diseñar sus registros, es importante considerar la inmutabilidad de los objetos que contienen. Si desea garantizar una inmutabilidad total, debe utilizar objetos inmutables o técnicas de copia defensiva al almacenar objetos mutables dentro de sus registros.

### Inicializando registros
Anteriormente, vio cómo los registros generan automáticamente un constructor basado en los componentes del registro. Este constructor predeterminado es suficiente para muchos casos de uso, pero hay ocasiones en las que es posible que necesite más control sobre el proceso de inicialización. Afortunadamente, los registros proporcionan varias formas de personalizar el constructor y agregar su propia lógica de inicialización.

El constructor largo, también conocido como constructor canónico, es el constructor predeterminado generado por el registro. Toma todos los componentes del registro como parámetros en el orden en que se declaran.```java
record Person(String name, int age) {}

Person john = new Person("John Doe", 30);
```En este ejemplo, el registro "Persona" tiene un constructor predeterminado que toma una "Cadena" para el "nombre" y un "int" para la "edad".

Si necesita validar o preprocesar alguno de los campos antes de asignarlos, puede utilizar un constructor compacto. Este constructor no especifica parámetros explícitamente. En su lugar, escribe el constructor sin parámetros y el compilador entiende que debe usar los parámetros del registro. Dentro del constructor compacto, puede agregar lógica de validación o transformación. Sin embargo, a diferencia del constructor canónico, no asigna valores a los campos directamente, esto se maneja automáticamente.

A continuación se muestra un ejemplo de un constructor compacto para el registro "Persona":```java
record Person(String name, int age) {
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}
```El cuerpo del constructor contiene una verificación de validación para garantizar que la "edad" no sea negativa. Si se proporciona una edad no válida, se genera una "IllegalArgumentException".

Los registros también admiten la sobrecarga de constructores, lo que significa que puede definir varios constructores con diferentes listas de parámetros. Esto puede resultar útil cuando desee proporcionar formas alternativas de inicializar un registro.

Sin embargo, cada uno de estos constructores debe delegar en el constructor canónico (ya sea directa o indirectamente a través de otro constructor personalizado) para garantizar que todos los campos estén inicializados. Esto generalmente se hace con la llamada `this()`, pasando los parámetros necesarios.

A continuación se muestra un ejemplo de un constructor personalizado/sobrecargado:```java
record Person(String name, int age) {
    public Person(String name) {
        this(name, 0);
    }
}

Person john = new Person("John Doe", 30);
Person jane = new Person("Jane Smith");
```En este ejemplo, agregamos un constructor sobrecargado que solo toma el parámetro "nombre". Dentro del constructor, llamamos al constructor canónico usando `this()`, pasando el `nombre` proporcionado y una `edad` predeterminada de `0`.

Este enfoque le permite:
- Defina constructores personalizados para inicializar el registro de diferentes maneras, lo que le brinda flexibilidad en la forma de crear instancias del registro.
- Agregue su propia lógica de inicialización y comprobaciones de validación utilizando constructores compactos o constructores sobrecargados.


### Personalización de registros
Si bien los registros son fáciles de usar desde el primer momento, Java proporciona algunas formas de personalizarlos para que se ajusten a sus necesidades.

#### Métodos de instancia

Aunque los registros están diseñados principalmente para transportar datos, esto no significa que no puedan tener comportamiento. Al igual que las clases normales, puedes agregar métodos de instancia a los registros para encapsular la lógica que opera en los componentes del registro. He aquí un ejemplo:```java
public record Point(int x, int y) {
    public double distance(Point other) {
        int dx = x - other.x;
        int dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
```En este caso, el registro "Punto" tiene un método de instancia "distancia()" que calcula la distancia euclidiana entre él y otro "Punto". El método puede acceder a los componentes del registro "x" e "y" directamente.

También puede anular métodos heredados de la clase `Object`, como `equals()`, `hashCode()` y `toString()`. De forma predeterminada, los registros proporcionan implementaciones sensatas de estos métodos según los componentes del registro, pero puede personalizarlos si es necesario:```java
public record Person(String name, int age) {
    @Override
    public String toString() {
        return name + " (" + age + " years old)";
    }
}
```En este ejemplo, `toString()` se anula para ofrecer una representación más legible por humanos de un registro `Persona`.

Sin embargo, al anular `equals()` y `hashCode()`, tenga cuidado de mantener la coherencia con las implementaciones generadas automáticamente. Los componentes del registro deben incluirse en la comparación de igualdad y en el cálculo del código hash para garantizar que dos registros con los mismos valores de componentes se consideren iguales y tengan el mismo código hash.

#### Tipos anidados

Los registros pueden contener clases anidadas, interfaces, anotaciones, enumeraciones e incluso otros registros. Esto le permite agrupar tipos relacionados dentro del registro, mejorando la encapsulación y la legibilidad. Por ejemplo:```java
public record Employee(String name, Department department) {
    public class Department { 
        // Implementation of the class
    }
    
    public static record Manager(String name) {
        // Additional fields and methods for managers
    }
}
```En este ejemplo, el registro "Empleado" tiene una clase "Departamento" anidada que representa, por ejemplo, los diferentes departamentos a los que puede pertenecer un empleado. También tiene un registro estático anidado "Administrador", que puede tener campos y métodos adicionales específicos para los administradores.

Los tipos anidados declarados dentro de un registro son implícitamente estáticos, por lo que se puede acceder a ellos usando el nombre del registro seguido del nombre del tipo, como "Empleado.Departamento" o "Empleado.Manager".

#### Genéricos y parámetros de tipo

Los registros pueden ser genéricos y aceptar parámetros de tipo, al igual que las clases y las interfaces. Esto le permite crear registros que pueden funcionar con diferentes tipos de datos y al mismo tiempo mantener la seguridad de los tipos. A continuación se muestra un ejemplo de un registro "Par" genérico:```java
public record Pair<T, U>(T first, U second) { }
```Luego puedes crear instancias del registro "Pair" con tipos específicos:```java
Pair<String, Integer> nameAge = new Pair<>("Alice", 30);
```Los registros genéricos funcionan perfectamente con el sistema de tipos de Java, incluidos comodines, parámetros de tipo acotados e inferencia de tipos. Hablaremos más sobre los genéricos en otro capítulo.

#### Registros locales

Además de declararse a nivel de clase, los registros también se pueden declarar localmente dentro de los métodos. Esto puede resultar útil cuando necesita una estructura de datos temporal con un alcance limitado. He aquí un ejemplo:```java
public void processCoordinates() {
    record Coordinate(int x, int y) { }
    
    Coordinate point1 = new Coordinate(10, 20);
    Coordinate point2 = new Coordinate(30, 40);
    
    // Process the coordinates...
}
```El registro `Coordinate` se declara dentro del método `processCoordinates()` y solo es accesible dentro de ese método.

#### Implementación de interfaces

Aunque los registros están diseñados principalmente para la encapsulación de datos, aún pueden implementar interfaces. Esto permite que los registros cumplan contratos y se utilicen en contextos donde se requiere una interfaz específica. He aquí un ejemplo:```java
public interface Drawable {
    void draw();
}

public record ColoredPoint(int x, int y, String color) implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " point at (" + x + ", " + y + ")");
    }
}
```En este caso, el registro `ColoredPoint` implementa la interfaz `Drawable` y proporciona una implementación para el método `draw()`.

#### Restricciones

En primer lugar, los registros no pueden ampliar clases ni ser ampliados por otras clases. Esta restricción refuerza la idea de que los registros son soportes de datos independientes y no forman parte de una jerarquía de herencia. Sin embargo, los registros pueden implementar interfaces, como se mostró anteriormente.

Otra restricción importante es que los registros no permiten campos de instancia adicionales fuera de los definidos en la declaración del registro. Los componentes del registro son los únicos campos de instancia permitidos. Por ejemplo:```java
public record Point(int x, int y) {
    private int z; // Compilation error: field declaration must be static
}
```Agregar campos de instancia adicionales como `z` en este ejemplo dará como resultado un error de compilación. El propósito de esta restricción es mantener la inmutabilidad del registro y mantener su estado vinculado únicamente a sus componentes.

La necesidad de campos de instancia adicionales indica que una clase normal puede ser más adecuada que un registro. Los registros deben ser soportes de datos livianos, no objetos complejos con estado mutable.

Sin embargo, es importante tener en cuenta que el mensaje de error menciona específicamente que la declaración del campo debe ser estática. Entonces, si modificamos el ejemplo para hacer de `z` un campo `estático`:```java
public record Point(int x, int y) {
    private static int z; // Compiles successfully
}
```Esta versión del registro `Point` se compilará sin problemas. Sin embargo, tenga en cuenta que los campos estáticos se comparten en todas las instancias del registro, por lo que no contribuyen al estado individual del registro.

Otra cosa a tener en cuenta es que los registros no admiten inicializadores de instancias. Si intenta agregar un bloque inicializador de instancia a un registro, así:```java
public record Point(int x, int y) {
    // Instance initializer block
    { 
        System.out.println("Initializing Point...");
    } // Compiler error: instance initializers not allowed in records
}
```El compilador de Java arrojará un error. La razón detrás de esta restricción es que los registros están diseñados para ser simples e inmutables, y los inicializadores de instancias pueden introducir una lógica de inicialización compleja que puede violar estos principios.

Si necesita realizar una lógica de inicialización adicional, puede utilizar un constructor compacto en su lugar:```java
public record Point(int x, int y) {
    public Point {
        System.out.println("Initializing Point...");
    }
}
```Un constructor compacto le permite ejecutar código en el momento de la creación de instancias del registro y al mismo tiempo garantizar que los componentes del registro se inicialicen correctamente.

Sin embargo, se permiten inicializadores estáticos. Lo siguiente se compilará sin errores:```java
public record Point(int x, int y) {
    // Static initializer block
    static { 
        System.out.println("Initializing Point...");
    }
}
```¿Por qué?

Los inicializadores estáticos se permiten en los registros por las mismas razones que se permiten en otras clases: para inicializar campos estáticos o para realizar bloques de inicialización estática que se ejecutan cuando se carga la clase. 

Entonces, si bien puede agregar métodos de instancia, campos estáticos y bloques inicializadores estáticos, no puede agregar campos de instancia o bloques inicializadores de instancia, porque podrían romper la inmutabilidad.

Recuerde, los registros no reemplazan las clases regulares, sino más bien una característica complementaria para casos de uso específicos donde se necesitan soportes de datos inmutables.


## Enumeraciones

### Presentamos enumeraciones
En Java, una enumeración (o enumeración) es un tipo especial de clase que se utiliza para definir un conjunto de constantes predefinidas. Es una forma de dar nombres a valores numéricos, haciendo que su código sea más legible y fácil de mantener.

Piense en una enumeración como una lista VIP para un evento exclusivo. La lista (enum) define quién puede entrar (las constantes predefinidas), pero cada persona en la lista también puede tener sus propios atributos (campos) y acciones únicos que puede realizar (métodos). El proceso de agregar a alguien a la lista con sus atributos específicos es similar a usar un constructor en una enumeración.

Digamos que estás creando una aplicación para administrar una tienda de mascotas. Es posible que tengas una variable para representar el tipo de animal:```java
String animalType;
//...
if(animalType.equals("DOG")) { 
    // process dog
} else if(animalType.equals("CAT")) {
    // process cat
} else if(animalType.equals("BIRD")) {
    // process bird
}
```Pero este enfoque tiene algunos problemas. Primero, es propenso a errores. ¿Qué pasa si escribes mal "PERRO" como "DIG" en alguna parte? El compilador no captará eso. En segundo lugar, no es muy legible. Es posible que alguien que lea este código no sepa inmediatamente qué significa "BIRD" en el contexto de su aplicación.

Aquí es donde entran las enumeraciones:```java
enum AnimalType {
    DOG, CAT, BIRD
}
```Ahora puedes usar la enumeración de esta manera:```java
AnimalType animalType;
//...  
if(animalType == AnimalType.DOG) {
    // process dog
} else if(animalType == AnimalType.CAT) {
    // process cat  
} else if(animalType == AnimalType.BIRD) {
    // process bird
}
```Si escribe mal "DOG", el compilador lo detectará. Y es mucho más legible.

Entonces, en esencia, las enumeraciones proporcionan una forma de definir un conjunto de constantes con nombre, lo que puede hacer que su código sea más legible, mantenible y menos propenso a errores.

Aquí hay un diagrama que muestra la estructura básica y los componentes de una declaración de enumeración:```
┌─────────────────────────────────────────────────┐
│ public enum DayOfWeek {                         │
│     MONDAY, TUESDAY, WEDNESDAY, THURSDAY,       │
│     FRIDAY, SATURDAY, SUNDAY;                   │
│                                                 │
│  ┌─────────────────────────────────────────┐    │
│  │ Implicit Components                     │    │
│  │ ● ordinal() : int                       │    │
│  │ ● name() : String                       │    │
│  │ ● values() : DayOfWeek[]                │    │
│  │ ● valueOf(String) : DayOfWeek           │    │
│  └─────────────────────────────────────────┘    │
│                                                 │
│  ┌─────────────────────────────────────────┐    │
│  │ Customizable Components                 │    │
│  │ ● Fields                                │    │
│  │ ● Constructors                          │    │
│  │ ● Methods                               │    │
│  └─────────────────────────────────────────┘    │
│                                                 │
└─────────────────────────────────────────────────┘
```### Declarar una enumeración

Declarar una enumeración es similar a declarar una clase, pero se utiliza la palabra clave `enum` en lugar de `class`:```java
public enum AnimalType {
    DOG, CAT, BIRD
}
```Cada constante (`DOG`, `CAT`, `BIRD`) es implícitamente "pública", "estática" y "final". La convención es utilizar todo en mayúsculas para sus nombres.

Es importante tener en cuenta que las enumeraciones solo pueden tener acceso "público" o predeterminado (paquete-privado) cuando se declaran fuera de una clase; no pueden declararse con acceso "protegido" o "privado". Si una enumeración se define dentro de una clase, puede tener cualquier nivel de acceso que pueda tener una clase interna normal.

He aquí un ejemplo:```java
public class PetStore {
    // This is okay
    private enum EmployeeLevel {
        TRAINEE, MANAGER, DIRECTOR
    }
    
    // This is okay
    protected enum AnimalBreed {
        LABRADOR, SIAMESE, PARROT
    }
}

// This is okay  
enum AnimalType {
    DOG, CAT, BIRD
}

// This will not compile
private enum FoodType {
    KIBBLE, CANNED, SEEDS
}
```Como puede ver, las enumeraciones declaradas dentro de una clase (`EmployeeLevel`) pueden tener cualquier modificador de acceso que pueda tener una clase interna normal. Y cuando una enumeración se declara fuera de una clase, debe ser pública o tener acceso predeterminado, no puede ser privada ("FoodType").

Además, si declara una enumeración en su propio archivo, el nombre de la enumeración debe coincidir con el nombre del archivo.

Pero las enumeraciones no son sólo una lista de constantes. Pueden tener constructores, métodos y campos, como una clase normal. Sin embargo, el constructor de una enumeración siempre es privado, ya sea explícita o implícitamente. De forma predeterminada, si no se especifica ningún modificador de acceso, el constructor es implícitamente privado. Los constructores de enumeraciones no pueden ser "públicos" ni "protegidos". Esto se debe a que no crea instancias de una enumeración usando "nuevo". En cambio, las instancias están predefinidas.```java
public enum AnimalType {
    DOG("Dog"), CAT("Cat"), BIRD("Bird");

    private String displayName;

    AnimalType(String displayName) {
        this.displayName = displayName;  
    }

    public String getDisplayName() {
        return displayName;
    }
}
```En este ejemplo, cada constante se crea con un nombre para mostrar, que se pasa al constructor. El constructor es privado, que es el predeterminado para las enumeraciones. Cada constante es esencialmente una instancia de la clase enum.

Esto responde a algunas preguntas comunes sobre enumeraciones:
- Las enumeraciones pueden tener métodos, constructores y campos además de las constantes predefinidas.  
- Los constructores en una enumeración son siempre privados (o paquetes privados), incluso si no se declaran así explícitamente. Es por eso que no puedes crear una instancia de una enumeración usando "nuevo". Si marca el constructor como "público" o "protegido", el compilador generará un error.
- Las enumeraciones no son sólo una lista de constantes enteras. Cada constante de enumeración es en realidad una instancia de la clase de enumeración, que puede tener su propio estado (campos) y comportamiento (métodos).

Otra cosa importante a tener en cuenta es que todas las enumeraciones extienden implícitamente `java.lang.Enum`. Esta es una clase especial en Java que proporciona algunos métodos integrados para enumeraciones.  

Debido a esta extensión implícita, una enumeración no puede extender ninguna otra clase. Sin embargo, puede implementar interfaces.

### Métodos especiales de una enumeración
Una clase enum declara implícitamente algunos métodos `públicos estáticos` que son bastante útiles y que no son obvios a primera vista, como los métodos `values()` y `valueOf()`.

Por ejemplo, suponiendo que tenemos esta enumeración:```java
enum Season {
    WINTER, SPRING, SUMMER, FALL;
}
```El método `public static T[] value()` devuelve una matriz que contiene todas las constantes de la clase enum, en el mismo orden en que se declaran. Este método se usa comúnmente para iterar sobre todas las constantes. Por ejemplo:```java
for(Season s : Season.values()) {
    System.out.println(s);
}
```Salidas:```
WINTER
SPRING
SUMMER
FALL
```Quizás se pregunte de dónde viene este método, ya que no se menciona en [javadoc para la clase enum](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Enum.html). La respuesta es que el compilador de Java lo agrega automáticamente a la clase de enumeración durante la compilación. En cierto modo, es como azúcar sintáctico proporcionado por el lenguaje.

El método `public static T valueOf(String)` devuelve la constante de enumeración con el nombre especificado. El nombre debe coincidir exactamente con un identificador utilizado para declarar la constante en la clase de enumeración. Por ejemplo:```java
Season s = Season.valueOf("SUMMER");
```Aparte de eso, cada constante de enumeración también tiene un método `name()` para obtener el nombre de la constante tal como se declara en la enumeración, y un método `ordinal()` para obtener su posición en el orden de declaración (comenzando desde `0`). Por ejemplo:```java
Season.WINTER.name();    // "WINTER"
Season.SPRING.ordinal(); // 1
```El método `compareTo(E o)` es otro método importante disponible para todos los tipos de enumeración. Este método compara la constante de enumeración con otra constante de enumeración del mismo tipo de enumeración en función de sus valores ordinales. Devuelve un entero negativo, cero o un entero positivo si esta constante de enumeración se considera menor, igual o mayor que la constante de enumeración especificada, respectivamente. Este método permite utilizar constantes de enumeración en colecciones ordenadas o para cualquier operación basada en comparación. Por ejemplo:```java
Season.WINTER.compareTo(Season.SUMMER); // Returns a negative number
Season.FALL.compareTo(Season.SPRING);   // Returns a positive number
Season.SPRING.compareTo(Season.SPRING); // Returns 0
```Vale la pena señalar que el orden natural proporcionado por `compareTo()` para las constantes de enumeración se basa en su orden de declaración, que puede no siempre ser el orden más significativo para su caso de uso específico. En tales situaciones, es posible que necesite implementar un "Comparador" personalizado para su tipo de enumeración.

Aquí hay una tabla que no solo resume todos estos métodos, sino que también proporciona un poco más de profundidad sobre cómo se pueden usar y qué tener en cuenta al usarlos:

| Método | Descripción | Tipo de devolución | Observaciones |
|-------------|-------------------------------------------------------|-------------|-------------------------------------------------|
| `valores()` | Devuelve una matriz que contiene todas las constantes de enumeración en el orden en que se declaran. | `TipoEnum[]` | Útil para iterar sobre todas las constantes en una enumeración. |
| `valorDe(Nombre de cadena)` | Devuelve la constante de enumeración del nombre especificado.    | `Tipo de enumeración` | Lanza `IllegalArgumentException` si el nombre especificado no coincide con ninguna de las constantes de enumeración. |
| `nombre()` | Devuelve el nombre de esta constante de enumeración, exactamente como se declara en su declaración de enumeración. | `Cadena` | Idéntico a llamar a `toString()`, pero `name()` es final y no se puede anular. |
| `ordinal()` | Devuelve el ordinal de esta constante de enumeración (su posición en la declaración de enumeración, donde a la constante inicial se le asigna un ordinal de cero). | `int` | Se puede utilizar para asociar índices de matrices o listas directamente con constantes de enumeración. Si tiene una matriz donde cada posición corresponde a una constante de enumeración específica, `ordinal()` ayuda a acceder directamente a estos elementos de la matriz según el orden de las constantes de enumeración. |
| `comparar con (E o)` | Compara esta enumeración con el objeto especificado para el pedido. | `int` | Devuelve un entero negativo, cero o un entero positivo ya que este objeto es menor, igual o mayor que el objeto especificado. El orden natural se basa en los valores ordinales de las constantes de enumeración. |


### Personalización de enumeraciones
Como se mencionó anteriormente, puedes agregar tus propios constructores a una clase de enumeración. El único requisito es que los constructores sean "privados" o privados del paquete. Sin embargo, también puedes agregar campos y métodos para personalizar la clase de enumeración.

Digamos que queremos asociar una temperatura media mínima y máxima a cada estación:```java
public enum Season {
    WINTER(-5, 10), 
    SPRING(11, 20), 
    SUMMER(21, 35), 
    FALL(5, 20);

    private int minTemp;
    private int maxTemp;
    
    Season(int minTemp, int maxTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
    
    public int getMinTemp() { return minTemp; }
    public int getMaxTemp() { return maxTemp; }
}
```El ejemplo agrega un constructor que recibe las temperaturas. Es un paquete privado, según sea necesario. Además, declara los campos para almacenar los valores y los captadores públicos para ellos.

Con esto podemos consultar las temperaturas asociadas a una estación:```java
Season.WINTER.getMaxTemp(); // 10
```Podemos agregar cualquier otro campo y método que queramos para que nuestra enumeración sea más interesante. 

Lo único que debemos recordar es declarar las constantes de enumeración primero en la clase. Podemos declarar campos y constructores en el medio, pero no otras constantes debajo de ellos, o obtendremos un error de compilación.

El siguiente ejemplo intenta declarar campos en medio de constantes de enumeración. Esto dará lugar a un error de compilación:```java
public enum Season {
    WINTER(-5, 10), 
    SPRING(11, 20),
    
    private int minTemp; // Compile error: enum constant expected here
    private int maxTemp;

    SUMMER(21, 35), 
    FALL(5, 20);
    
    Season(int minTemp, int maxTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
    
    public int getMinTemp() { return minTemp; }
    public int getMaxTemp() { return maxTemp; }
}
```Así que tenga cuidado, declarar constantes de enumeración después de cualquier campo o constructor es un error común al definir enumeraciones con constructores y campos personalizados.

## Puntos clave
- Los registros proporcionan una manera concisa de declarar clases que están pensadas principalmente como soportes de datos simples e inmutables.

- Un registro genera automáticamente un constructor, campos finales privados para los parámetros e implementaciones de `equals()`, `hashCode()` y `toString()` basadas en esos campos.

- Los registros son implícitamente definitivos y no pueden ser ampliados por otras clases.

- Todos los campos de un registro son implícitamente finales, lo que impone la inmutabilidad. El estado de un registro no se puede cambiar después de la creación de instancias.

- Los registros proporcionan un constructor canónico predeterminado que toma todos los componentes del registro como parámetros.

- Los constructores compactos permiten agregar lógica de validación o preprocesamiento sin especificar parámetros explícitamente.

- Los registros admiten la sobrecarga del constructor, pero cada constructor debe delegar en el constructor canónico para garantizar la inicialización del campo.

- Se pueden agregar métodos de instancia a los registros para encapsular el comportamiento que opera en los componentes del registro.

- Los registros pueden contener clases anidadas, interfaces, anotaciones, enumeraciones y otros registros.

- Los registros pueden ser genéricos y aceptar parámetros de tipo, lo que les permite trabajar con diferentes tipos de datos manteniendo la seguridad de los tipos.

- Los registros locales se pueden declarar dentro de métodos para estructuras de datos temporales con alcance limitado.

- Los registros pueden implementar interfaces para cumplir contratos y usarse cuando se requiere una interfaz específica.

- Los registros no pueden extender clases ni ampliarse, no pueden tener campos de instancia adicionales más allá de los componentes del registro y no admiten inicializadores de instancia.

- Se permiten campos estáticos e inicializadores estáticos en los registros.

- Una enumeración es un tipo especial de clase que se utiliza para definir un conjunto de constantes predefinidas, lo que hace que el código sea más legible y fácil de mantener.

- Cada constante de enumeración es implícitamente "pública", "estática" y "final" y, por convención, sus nombres están en mayúsculas.

- Los constructores de enumeración son siempre "privados" (o paquetes privados), por lo que las instancias de enumeración no se pueden crear usando "nuevo".

- Todas las enumeraciones extienden implícitamente `java.lang.Enum`, que proporciona métodos integrados como `valueOf()`.

- El método `values()` devuelve una matriz de todas las constantes de enumeración en el orden en que se declaran.

- El método `valueOf()` devuelve la constante de enumeración con el nombre especificado.

- Cada constante de enumeración también tiene un método `name()` para obtener su nombre declarado y un método `ordinal()` para obtener su posición.

- Las enumeraciones se pueden personalizar con campos, constructores y métodos para asociar datos y comportamiento adicionales con cada constante.

- Al definir una enumeración con constructores y campos personalizados, todas las constantes de enumeración deben declararse antes de cualquier campo o constructor.

## Preguntas de práctica

**1. Considere la siguiente definición de registro:**```java
public record Employee(String name, int age) {}
```¿Cuál de las siguientes afirmaciones es cierta sobre el registro de "Empleado"?

**A)** El registro `Empleado` define explícitamente un constructor público que inicializa sus campos.  
**B)** Los campos `nombre` y `edad` se pueden reasignar a nuevos valores después de crear un objeto `Empleado`.  
**C)** El registro "Empleado" crea implícitamente un constructor público y campos finales privados para "nombre" y "edad".  
**D)** Es obligatorio definir captadores para los campos "nombre" y "edad" en el registro "Empleado".


**2. Dada la definición de registro a continuación:**```java
public record Account(String id, double balance) {}
```¿Qué afirmación describe con precisión la inmutabilidad de los registros?

**A)** El campo "saldo" se puede modificar utilizando un método de establecimiento público dentro del registro "Cuenta".  
**B)** Una vez que se crea un objeto "Cuenta", su "id" y su "saldo" no se pueden cambiar.  
**C)** La inmutabilidad de los registros se puede evitar definiendo métodos de configuración personalizados para los campos `id` y `balance`.  
**D)** Los registros permiten modificar los valores de los campos si se accede a ellos directamente, sin utilizar métodos de establecimiento.


**3. Considere la siguiente declaración récord:**```java
public record Product(int id, String name, double price) {}
```¿Cómo se puede inicializar correctamente una instancia del registro "Producto"?

**A)** `Producto p = nuevo Producto();`  
**B)** `Producto p = Producto(101, "Café", 15,99);`  
**C)** `Producto p = {101, "Café", 15.99};`  
**D)** `Producto p = nuevo Producto(101, "Café", 15,99);`


**4. Considere un registro que necesita implementar la interfaz "Comparable" para permitir la clasificación según uno de sus campos. Dada la siguiente definición de registro:**```java
public record Item(int id, String name, double price) implements Comparable<Item> {
    public int compareTo(Item other) {
        return Double.compare(this.price, other.price);
    }
}
```¿Qué afirmación describe correctamente cómo se pueden personalizar los registros mediante la implementación de interfaces?

**A)** Los registros no pueden implementar interfaces porque son "finales" e inmutables por diseño, lo que impide cualquier forma de personalización del comportamiento.  
**B)** Este registro implementa correctamente la interfaz `Comparable`, permitiendo que los objetos `Item` se clasifiquen según su `price`.  
**C)** La implementación de interfaces en registros está restringida solo a interfaces funcionales debido a su naturaleza inmutable.  
**D)** El método `compareTo` no se puede anular en los registros porque la anulación de métodos no se admite en los tipos de registros.


**5. Considere las formas de declarar enumeraciones en Java. ¿Cuáles de las siguientes declaraciones son válidas? (Elija todas las que correspondan).**

**A)**```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```**B)**```java
enum Month {
    private JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
}
```**DO)**```java
protected enum Season {
    WINTER, SPRING, SUMMER, FALL
}
```**D)**```java
enum Status {
    ACTIVE, INACTIVE, DELETED;

    public void printStatus() {
        System.out.println("Current status: " + this);
    }
}
```**6. Considere la siguiente declaración de enumeración:**```java
public enum Color {
    RED, GREEN, BLUE;
}
```¿Cuál es el resultado de llamar a `Color.GREEN.ordinal()`?

**A)** `1`  
**B)** `2`  
**C)** `0`  
**D)** `Color.VERDE`


**7. Considere una enumeración que necesita proporcionar un método personalizado para mostrar un mensaje basado en la constante de enumeración. ¿Cuál de las siguientes implementaciones define correctamente dicha enumeración?**

**A)**```java
public enum Size {
    SMALL, MEDIUM, LARGE;
    public static void printSize() {
        System.out.println("The size is " + this.name());
    }
}
```**B)**```java
enum Flavor {
    CHOCOLATE, VANILLA, STRAWBERRY;
    void printFlavor() {
        System.out.println("Flavor: " + Flavor.name);
    }
}
```**DO)**```java
protected enum Direction {
    NORTH, SOUTH, EAST, WEST;
    private printDirection() {
        System.out.println("Going " + this.toString());
    }
}
```**D)**```java
public enum Season {
    WINTER, SPRING, SUMMER, FALL;
    public void printSeason() {
        System.out.println("The season is " + this.name());
    }
}
```
