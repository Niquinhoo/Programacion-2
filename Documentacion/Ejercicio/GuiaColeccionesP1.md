Guía de Ejercicios: Estructuras de Datos y Framework
de Colecciones – Parte 1
Sección 1: Matrices (Arrays)
Objetivo: Practicar la creación, inicialización, actualización y ordenamiento de arreglos
de tamaño fijo.
 Ejercicio 1.1 (Creación, actualización y recuperación): Crea un arreglo (matriz) de
enteros especificando un tamaño de 5 elementos utilizando la palabra clave new. Asigna
valores a cada posición usando el índice correspondiente. Actualiza el valor del tercer
elemento y luego recupera el último elemento utilizando el atributo length para calcular su
índice.
 Ejercicio 1.2 (Ordenamiento y Búsqueda): Crea un arreglo de tipo String con nombres
desordenados. Utiliza el método estático Arrays.sort() para ordenar los elementos del arreglo
en orden ascendente. Una vez ordenado, utiliza el método Arrays.binarySearch() para buscar
la posición de un nombre específico dentro del arreglo.
Sección 2: Listas (Lists)
Objetivo: Comprender las colecciones ordenadas que permiten duplicados y el uso de
implementaciones dinámicas.
 Ejercicio 2.1 (Operaciones CRUD en ArrayList): Crea un ArrayList de tipo String, el cual
está respaldado por un arreglo dinámico. Utiliza el método add() para agregar tres
elementos, set() para actualizar el valor del segundo elemento, get() para recuperar el primer
valor y remove() para eliminar el último.
 Ejercicio 2.2 (Listas Inmutables vs LinkedList): Utiliza el método de fábrica List.of()
para crear una lista inmutable con varios valores predefinidos. Intenta agregar un nuevo
elemento para observar cómo se lanza una UnsupportedOperationException. Luego, pasa esa
lista al constructor de un LinkedList para crear una lista mutable y realiza inserciones en el
medio de la lista, aprovechando que esta implementación de lista doblemente enlazada es
eficiente para esas operaciones.
Sección 3: Conjuntos (Sets) y Colecciones Deque
Objetivo: Manipular colecciones sin duplicados y colas de doble extremo.
 Ejercicio 3.1 (Unicidad en Sets): Instancia un HashSet de tipo Integer. Utiliza el método
add() para intentar insertar el mismo número tres veces y verifica usando el método size()
que solo se almacenó una vez. Comprueba si un número existe usando contains() y elimínalo
usando remove(). Observa al imprimir el conjunto que no se garantiza ningún orden de los
elementos.
 Ejercicio 3.2 (Colas de doble extremo con ArrayDeque): Crea una colección ArrayDeque.
Utilízala agregando elementos tanto al inicio como al final usando los métodos addFirst() y
addLast(). Luego, recupera y elimina los elementos de los extremos utilizando removeFirst()
y removeLast().
Sección 4: Mapas (Maps)
Objetivo: Trabajar con asociaciones de clave-valor utilizando diferentes
implementaciones del framework de mapas.
 Ejercicio 4.1 (Manejo de HashMap): Crea un HashMap que asocie identificadores de
usuario (tipo Integer) con sus nombres (tipo String). Utiliza el método put() para agregar
nuevas entradas. Recupera un nombre pasando su identificador al método get(), y actualiza
un nombre existente empleando el método replace(). Finalmente, utiliza remove() pasando la
clave para eliminar un registro.
 Ejercicio 4.2 (Ordenamiento con TreeMap): Repite el ejercicio anterior, pero esta vez
instancia un TreeMap. Utiliza el método keySet() para obtener todas las claves y recórrelas.
Observa cómo el TreeMap recupera los registros automáticamente ordenados de forma
ascendente según el orden natural de sus claves.
Sección 5: Ordenamiento Avanzado
Objetivo: Definir reglas de ordenamiento naturales y personalizadas para objetos
complejos en colecciones.
 Ejercicio 5.1 (Orden natural con Comparable): Crea una clase Empleado que implemente
la interfaz Comparable. Sobrescribe el método compareTo() para que los empleados se
ordenen según su edad. Agrega varios empleados a una lista y utiliza el método
Collections.sort() para ordenarlos según este orden natural.
 Ejercicio 5.2 (Orden personalizado con Comparator): Crea una clase separada que
implemente la interfaz Comparator para la clase Empleado. En el método compare(), define
una lógica para ordenar a los empleados alfabéticamente por su nombre en lugar de su edad.
Pasa tu lista y esta nueva instancia del comparador al método Collections.sort() para ver
cómo la lista adopta este nuevo ordenamiento.