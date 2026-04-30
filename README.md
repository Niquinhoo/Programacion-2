# Concurrencia y Multitarea Basica - Programacion 2

Este repositorio contiene la resolucion completa de la unidad de Concurrencia y Multitarea de Programacion 2, desarrollada en Java como aplicacion de consola.

## Estructura

```text
ConcurrenciayMultitareaBasica/
`-- src/
    |-- App.java
    |-- TareaHilo.java
    |-- TareaRunnable.java
    |-- Contador.java
    |-- OperadorContador.java
    |-- ContadorSynchronizedMetodo.java
    |-- ContadorSynchronizedBloque.java
    |-- TareaHiloConIdentidad.java
    |-- HiloLento.java
    |-- HiloLentoInterrumpible.java
    `-- Incrementador.java
`-- Documentacion/
    |-- Checklist/
    |   `-- CHECKLISTCONCURRENCIA.md
    |-- Conclusion/
    |   `-- CONCLUSIONCONCURRENCIA.md
    |-- Ejercicio/
    |   `-- ConcurrenciayMultitarea.md
    `-- Logs/
        `-- LOGSCONCURRENCIA.md
```

## Contenido resuelto

- Creacion de hilos con `Thread`.
- Ejecucion concurrente con `Runnable`.
- Condicion de carrera sobre recurso compartido (`Contador`).
- Sincronizacion por metodo (`synchronized`).
- Sincronizacion por bloque (`synchronized` en secciones criticas).
- Prioridades e identidad de hilos.
- Coordinacion con `join()` e interrupcion con `interrupt()`.
- Documentacion tecnica con Javadoc en clases y metodos principales.

## Como ejecutar

Desde la carpeta `ConcurrenciayMultitareaBasica/src`:

```bash
javac *.java
java -cp . App
```

La salida de la ejecucion queda documentada en `Documentacion/Logs/LOGSCONCURRENCIA.md`.

## Documentacion Javadoc

El proyecto cuenta con documentacion Javadoc completa en:

- App.java: descripcion general y orquestacion de ejercicios de concurrencia.
- Contador y OperadorContador: entidad compartida y operaciones concurrentes.
- ContadorSynchronizedMetodo y ContadorSynchronizedBloque: estrategias de sincronizacion.
- HiloLento y HiloLentoInterrumpible: demostracion de `join()` e `interrupt()`.

Para generar documentacion HTML desde `ConcurrenciayMultitareaBasica/src`:

```bash
javadoc -d docs *.java
```

Ese comando crea la carpeta `docs/` con la navegacion HTML de la documentacion generada.

## Documentacion adicional

- `Documentacion/Ejercicio/ConcurrenciayMultitarea.md`: resumen de ejercicios implementados.
- `Documentacion/Checklist/CHECKLISTCONCURRENCIA.md`: validacion de requisitos y pruebas realizadas.
- `Documentacion/Conclusion/CONCLUSIONCONCURRENCIA.md`: analisis final de implementacion.
- `Documentacion/Logs/LOGSCONCURRENCIA.md`: salida completa de ejecucion en consola.

Toda la documentacion fue actualizada y testeada por Codex.
