# Guia Colecciones 1 - Programacion 2

Este repositorio contiene la resolucion completa de la **Guia de Colecciones 1** de **Programacion 2**, desarrollada en Java como aplicacion de consola.

## Estructura

```text
GuiaColecciones1/
|-- Guia-Codigo/
|   `-- Ejercicio 1/
|       `-- src/
|           |-- App.java
|           |-- Empleado.java
|           `-- EmpleadoPorNombreComparator.java
`-- Documentacion/
    |-- Checklist/
    |   `-- CHECKLIST-GUIA1.md
    |-- Conclusion/
    |   `-- CONCLUSION.md
    |-- Ejercicio/
    |   |-- GuiaColecciones.md
    |   `-- Guía de Ejercicios colecciones 1eraParte.pdf
    `-- Logs/
        `-- LOGS_CONSOLA-GUIA1.md
```

## Contenido resuelto

- Arrays: creacion, actualizacion, ordenamiento y busqueda.
- Lists: `ArrayList`, `List.of()` y `LinkedList`.
- Sets y Deque: `HashSet` y `ArrayDeque`.
- Maps: `HashMap` y `TreeMap`.
- Ordenamiento avanzado: `Comparable` y `Comparator`.
- Documentacion tecnica con Javadoc en todas las clases y metodos del codigo fuente.

## Como ejecutar

Desde la carpeta `Guia-Codigo/Ejercicio 1/src`:

```powershell
javac App.java Empleado.java EmpleadoPorNombreComparator.java
java -cp . App
```

La salida de la ejecucion queda documentada en `Documentacion/Logs/LOGS_CONSOLA-GUIA1.md`.

## Documentacion Javadoc

El proyecto cuenta con documentacion Javadoc completa en:

- `App.java`: descripcion general de la aplicacion y de cada ejercicio resuelto.
- `Empleado.java`: documentacion de la entidad, su constructor, getters y orden natural.
- `EmpleadoPorNombreComparator.java`: documentacion del comparador utilizado para ordenar por nombre.

Para generar la documentacion HTML desde `Guia-Codigo/Ejercicio 1/src`:

```powershell
javadoc -d docs App.java Empleado.java EmpleadoPorNombreComparator.java
```

Ese comando crea la carpeta `docs/` con la navegacion HTML de la documentacion generada.

## Documentacion adicional

- `Documentacion/Ejercicio/GuiaColecciones.md`: resumen de la guia de ejercicios.
- `Documentacion/Checklist/CHECKLIST-GUIA1.md`: validacion de requisitos y pruebas realizadas.
- `Documentacion/Conclusion/CONCLUSION.md`: analisis final de la implementacion.
- `Documentacion/Logs/LOGS_CONSOLA-GUIA1.md`: salida completa de la ejecucion en consola.

Toda la documentacion fue actualizada y testeada por Codex.
