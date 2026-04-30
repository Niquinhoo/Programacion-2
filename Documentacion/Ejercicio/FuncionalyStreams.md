# Guia de Ejercicios Practicos: Programacion Funcional y Streams

## Implementacion

La resolucion en codigo se encuentra modularizada en:

- [App.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/App.java)
- [AplicacionEjercicios.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/AplicacionEjercicios.java)
- [ImpresorConsola.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/ImpresorConsola.java)
- [ConjuntosDatos.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/ConjuntosDatos.java)
- [Persona.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/Persona.java)
- [EjerciciosSeccionUno.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/EjerciciosSeccionUno.java)
- [EjerciciosSeccionDos.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/EjerciciosSeccionDos.java)
- [EjerciciosSeccionTres.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/EjerciciosSeccionTres.java)
- [EjerciciosSeccionCuatro.java](/c:/Users/nicot/Desktop/Programacion-2/FuncionalyStreams/src/EjerciciosSeccionCuatro.java)

## Estructura modular

- `App`: punto de entrada minimo.
- `AplicacionEjercicios`: orquestador principal.
- `ImpresorConsola`: centraliza la salida por consola.
- `ConjuntosDatos`: encapsula todos los datos de prueba.
- `Persona`: modelo usado en el ejercicio del `toMap`.
- `EjerciciosSeccionUno`: expresiones lambda e interfaces funcionales.
- `EjerciciosSeccionDos`: creacion, filtrado y extraccion en streams.
- `EjerciciosSeccionTres`: transformacion, aplanamiento y ordenacion.
- `EjerciciosSeccionCuatro`: operaciones finales, `Optional`, `reduce` y `collect`.

## Desarrollo por ejercicio

### Ejercicio 1.1

Se definieron cuatro interfaces funcionales base:

- `Predicate<Integer>` para verificar si un numero es par.
- `Function<String, Integer>` para calcular la longitud de un texto.
- `Consumer<Integer>` para imprimir un numero.
- `Supplier<Double>` para obtener un valor aleatorio.

### Ejercicio 1.2

Se creo una lista de textos y se ordeno por cantidad de caracteres mediante una expresion lambda:

```java
texts.sort((first, second) -> first.length() - second.length());
```

### Ejercicio 1.3

Se usaron versiones primitivas para evitar boxing:

- `IntPredicate`
- `ToIntFunction<String>`

### Ejercicio 2.1

Sobre el flujo `Stream.of(2, 5, 3, 3, 6, 2, 4)` se aplicaron:

1. `distinct()` para quitar repetidos.
2. `skip(1)` para omitir el primer elemento.
3. `limit(3)` para quedarse con tres elementos.
4. `count()` para contar el resultado final.

### Ejercicio 2.2

Se filtraron numeros mayores a 3 y se imprimieron durante el recorrido del stream usando `peek()`, lo cual permite observar el flujo antes de la operacion terminal.

### Ejercicio 3.1

Se transformaron nombres a mayusculas usando:

```java
.map(String::toUpperCase)
```

### Ejercicio 3.2

Se uso `mapToInt(String::length)` para transformar cada nombre en su longitud y luego `sum()` para totalizar caracteres.

### Ejercicio 3.3

Se aplano una lista de sublistas con:

```java
ConjuntosDatos.nombresAnidados().stream().flatMap(List::stream)
```

Luego se filtraron los nombres con mas de 4 caracteres y se conto cuantos cumplian la condicion.

### Ejercicio 3.4

Se resolvieron dos tipos de ordenacion:

- Natural: `sorted()`
- Por longitud: `sorted(Comparator.comparingInt(String::length))`

### Ejercicio 4.1

Se probaron tres operaciones terminales booleanas:

- `anyMatch(n -> n > 5)`
- `allMatch(n -> n > 1)`
- `noneMatch(n -> n > 10)`

### Ejercicio 4.2

Se filtraron numeros pares y se tomo el primero con `findFirst()`. El valor resultante se almaceno en un `Optional<Integer>`, verificando su presencia con `isPresent()` y mostrandolo con `ifPresent()`.

### Ejercicio 4.3

Se aplico:

```java
reduce(Integer::sum)
```

para sumar todos los elementos del flujo y obtener el total.

### Ejercicio 4.4

Se resolvieron dos recolecciones:

- Lista sin duplicados con `distinct().collect(Collectors.toList())`
- Mapa `dni -> nombre` a partir de una lista de personas con `Collectors.toMap()`

## Documentacion separada

- Checklist: [CHECKLISTFUNSTREAMS.md](/c:/Users/nicot/Desktop/Programacion-2/Documentacion/Checklist/CHECKLISTFUNSTREAMS.md)
- Logs: [LOGSFUNSTREAMS.md](/c:/Users/nicot/Desktop/Programacion-2/Documentacion/Logs/LOGSFUNSTREAMS.md)
- Conclusion: [CONCLUSIONFUNSTREAMS.md](/c:/Users/nicot/Desktop/Programacion-2/Documentacion/Conclusion/CONCLUSIONFUNSTREAMS.md)
