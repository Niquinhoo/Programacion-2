# Checklist Funcional y Streams

## Estado general

- [x] Se modularizo `App.java` en multiples clases con responsabilidades separadas.
- [x] Se centralizo la salida de consola en una clase utilitaria.
- [x] Se separaron los datos de prueba en una clase dedicada.
- [x] Se movio la clase `Persona` a su propio archivo.
- [x] Se dividio la ejecucion en secciones independientes.

## Seccion 1: Expresiones Lambda e Interfaces Funcionales

- [x] Ejercicio 1.1: `Predicate<Integer>`, `Function<String, Integer>`, `Consumer<Integer>` y `Supplier<Double>`.
- [x] Ejercicio 1.2: ordenacion de `List<String>` por longitud con lambda.
- [x] Ejercicio 1.3: uso de `IntPredicate` y `ToIntFunction<String>`.

## Seccion 2: Creacion, Filtrado y Extraccion

- [x] Ejercicio 2.1: `distinct()`, `skip(1)`, `limit(3)` y `count()`.
- [x] Ejercicio 2.2: `filter()`, `peek()` y `count()`.

## Seccion 3: Transformacion, Aplanamiento y Ordenacion

- [x] Ejercicio 3.1: `map(String::toUpperCase)`.
- [x] Ejercicio 3.2: `mapToInt(String::length)` y `sum()`.
- [x] Ejercicio 3.3: `flatMap(List::stream)` y `count()`.
- [x] Ejercicio 3.4: `sorted()` y `sorted(Comparator)`.

## Seccion 4: Operaciones Finales y Optional

- [x] Ejercicio 4.1: `anyMatch()`, `allMatch()` y `noneMatch()`.
- [x] Ejercicio 4.2: `findFirst()` con `Optional<Integer>`.
- [x] Ejercicio 4.3: `reduce(Integer::sum)`.
- [x] Ejercicio 4.4: `distinct().collect(Collectors.toList())` y `Collectors.toMap()`.

## Archivos principales

- [x] [App.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/App.java)
- [x] [AplicacionEjercicios.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/AplicacionEjercicios.java)
- [x] [ImpresorConsola.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/ImpresorConsola.java)
- [x] [ConjuntosDatos.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/ConjuntosDatos.java)
- [x] [Persona.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/Persona.java)
- [x] [EjerciciosSeccionUno.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/EjerciciosSeccionUno.java)
- [x] [EjerciciosSeccionDos.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/EjerciciosSeccionDos.java)
- [x] [EjerciciosSeccionTres.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/EjerciciosSeccionTres.java)
- [x] [EjerciciosSeccionCuatro.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/EjerciciosSeccionCuatro.java)
