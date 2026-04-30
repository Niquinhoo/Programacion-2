# Logs de Consola - Guia 1

Salida obtenida al compilar con `javac` y ejecutar `java -cp . App` desde `Guia-Codigo/Ejercicio 1/src`:

```text
============================================================
Guia 1 - Framework de Colecciones
============================================================

------------------------------------------------------------
Ejercicio 1.1 - Creacion, actualizacion y recuperacion en arrays
------------------------------------------------------------
Array inicial: [10, 20, 30, 40, 50]
Tercer elemento actualizado: [10, 20, 35, 40, 50]
Ultimo elemento usando length: indice 4, valor 50

------------------------------------------------------------
Ejercicio 1.2 - Ordenamiento y busqueda en arrays
------------------------------------------------------------
Array desordenado: [Zoe, Ana, Carlos, Bruno, Elena]
Array ordenado ascendente: [Ana, Bruno, Carlos, Elena, Zoe]
Resultado de binarySearch para Carlos: posicion 2

------------------------------------------------------------
Ejercicio 2.1 - Operaciones CRUD en ArrayList
------------------------------------------------------------
Lista luego de add(): [Rojo, Verde, Azul]
Lista luego de set() en la segunda posicion: [Rojo, Amarillo, Azul]
Valor recuperado con get(0): Rojo
Elemento eliminado con remove(): Azul
Lista final: [Rojo, Amarillo]

------------------------------------------------------------
Ejercicio 2.2 - Listas inmutables vs LinkedList
------------------------------------------------------------
Lista inmutable creada con List.of(): [Otono, Invierno, Primavera]
Excepcion esperada al intentar agregar en lista inmutable: UnsupportedOperationException
LinkedList mutable con inserciones en el medio: [Otono, Verano, Invierno, Pretemporada, Primavera]

------------------------------------------------------------
Ejercicio 3.1 - Unicidad en HashSet
------------------------------------------------------------
Contenido del set: [3, 7, 12]
Tamano del set tras agregar 7 tres veces: 3
El set contiene 12: true
Se elimino 12: true
Set luego de remove(): [3, 7]

------------------------------------------------------------
Ejercicio 3.2 - ArrayDeque como cola de doble extremo
------------------------------------------------------------
Deque luego de addFirst() y addLast(): [Revisar pendientes, Preparar informe, Enviar correo, Cerrar sesion]
Elemento removido del inicio: Revisar pendientes
Elemento removido del final: Cerrar sesion
Deque restante: [Preparar informe, Enviar correo]

------------------------------------------------------------
Ejercicio 4.1 - Manejo de HashMap
------------------------------------------------------------
HashMap inicial: {101=Lucia, 102=Mateo, 103=Sofia}
Valor recuperado con get(102): Mateo
HashMap luego de replace(103): {101=Lucia, 102=Mateo, 103=Sofia B}
Registro eliminado con clave 101: Lucia
HashMap final: {102=Mateo, 103=Sofia B}

------------------------------------------------------------
Ejercicio 4.2 - Ordenamiento automatico con TreeMap
------------------------------------------------------------
TreeMap completo: {100=Carla, 150=Andres, 200=Bianca, 300=Julian}
Recorrido por keySet() en orden ascendente:
Clave 100 -> Carla
Clave 150 -> Andres
Clave 200 -> Bianca
Clave 300 -> Julian

------------------------------------------------------------
Ejercicio 5.1 - Orden natural con Comparable
------------------------------------------------------------
Lista original: [Empleado{nombre='Marina', edad=34}, Empleado{nombre='Alberto', edad=41}, Empleado{nombre='Zoe', edad=29}, Empleado{nombre='Bruno', edad=29}]
Lista ordenada por edad (compareTo): [Empleado{nombre='Zoe', edad=29}, Empleado{nombre='Bruno', edad=29}, Empleado{nombre='Marina', edad=34}, Empleado{nombre='Alberto', edad=41}]

------------------------------------------------------------
Ejercicio 5.2 - Orden personalizado con Comparator
------------------------------------------------------------
Lista original: [Empleado{nombre='Marina', edad=34}, Empleado{nombre='Alberto', edad=41}, Empleado{nombre='Zoe', edad=29}, Empleado{nombre='Bruno', edad=29}]
Lista ordenada por nombre (Comparator): [Empleado{nombre='Alberto', edad=41}, Empleado{nombre='Bruno', edad=29}, Empleado{nombre='Marina', edad=34}, Empleado{nombre='Zoe', edad=29}]
```

Nota: el orden de impresion de `HashSet` puede variar entre ejecuciones porque no garantiza orden.

Esta documentacion fue actualizada y testeada por Codex
