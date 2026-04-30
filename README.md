# Guia Funcional y Streams - Programacion 2

Este repositorio contiene la resolucion completa de la guia de Programacion Funcional y Streams de Programacion 2, desarrollada en Java como aplicacion de consola.

## Estructura

```text
Programacion-2/
|-- FuncionalyStreams/
|   `-- src/
|       |-- App.java
|       |-- AplicacionEjercicios.java
|       |-- ImpresorConsola.java
|       |-- ConjuntosDatos.java
|       |-- Persona.java
|       |-- EjerciciosSeccionUno.java
|       |-- EjerciciosSeccionDos.java
|       |-- EjerciciosSeccionTres.java
|       `-- EjerciciosSeccionCuatro.java
`-- Documentacion/
    |-- Checklist/
    |   `-- CHECKLISTFUNSTREAMS.md
    |-- Conclusion/
    |   `-- CONCLUSIONFUNSTREAMS.md
    |-- Ejercicio/
    |   `-- FuncionalyStreams.md
    `-- Logs/
        `-- LOGSFUNSTREAMS.md
```

## Contenido resuelto

- Interfaces funcionales basicas: `Predicate`, `Function`, `Consumer` y `Supplier`.
- Variantes primitivas: `IntPredicate` y `ToIntFunction`.
- Streams: `distinct`, `skip`, `limit`, `filter`, `peek` y `count`.
- Transformacion y aplanamiento: `map`, `mapToInt` y `flatMap`.
- Ordenacion: `sorted()` y `sorted(Comparator)`.
- Operaciones finales: `anyMatch`, `allMatch`, `noneMatch`, `findFirst`, `reduce` y `collect`.
- Uso de `Optional` y construccion de `Map` con `Collectors.toMap()`.
- Documentacion separada en checklist, logs y conclusion.

## Como ejecutar

Desde la carpeta `FuncionalyStreams/src`:

```powershell
javac *.java
java -cp . App
```

La salida de la ejecucion queda documentada en [Documentacion/Logs/LOGSFUNSTREAMS.md](Documentacion/Logs/LOGSFUNSTREAMS.md).

## Documentacion

- [Documentacion/Ejercicio/FuncionalyStreams.md](Documentacion/Ejercicio/FuncionalyStreams.md): resumen general de la guia y de la estructura del proyecto.
- [Documentacion/Checklist/CHECKLISTFUNSTREAMS.md](Documentacion/Checklist/CHECKLISTFUNSTREAMS.md): validacion de requisitos y ejercicios resueltos.
- [Documentacion/Conclusion/CONCLUSIONFUNSTREAMS.md](Documentacion/Conclusion/CONCLUSIONFUNSTREAMS.md): analisis final de la implementacion.
- [Documentacion/Logs/LOGSFUNSTREAMS.md](Documentacion/Logs/LOGSFUNSTREAMS.md): salida completa de la ejecucion en consola.

## Nota

Toda la documentacion fue actualizada y validada por Codex.
