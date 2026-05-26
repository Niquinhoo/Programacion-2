# Base de Datos con JDBC - Programacion 2

Este repositorio contiene la resolucion de la unidad de **Base de Datos con JDBC** para la materia **Programacion 2**.

## Modulo `BaseDeDatosConJDBC`

Dentro de este proyecto, `BaseDeDatosConJDBC` contiene la solucion a los ejercicios planteados utilizando la API de JDBC para interactuar con bases de datos en Java.

## Arquitectura

La solucion sigue una estructura modular, separando responsabilidades por tipo de clase (ej. utilidades de conexion, DAOs, controladores, y modelos).

## Estructura del repositorio

```text
Programacion-2/
|-- BaseDeDatosConJDBC/
|   |-- bin/
|   |-- lib/
|   `-- src/
|-- Documentacion/
|   |-- Checklist/
|   |   `-- CHECKLIST-JDBC.md
|   |-- Conclusion/
|   |   `-- CONCLUSION-JDBC.md
|   |-- Ejercicio/
|   |   `-- GuiaBaseDeDatosJDBC.md
|   `-- Logs/
|       `-- LOGS_CONSOLA-JDBC.md
`-- README.md
```

## Como compilar y ejecutar

- ```powershell
  PS> javac -d bin -cp "lib/*;bin" src/*.java
  PS> java -cp "lib/*;bin" App
  ```

## Documentacion complementaria

- `Documentacion/Ejercicio/GuiaBaseDeDatosJDBC.md`: consigna base de los ejercicios.
- `Documentacion/Checklist/CHECKLIST-JDBC.md`: seguimiento de requisitos implementados.
- `Documentacion/Logs/LOGS_CONSOLA-JDBC.md`: registro de compilaciones, arranques y validaciones.
- `Documentacion/Conclusion/CONCLUSION-JDBC.md`: cierre general del trabajo.

## Estado del modulo

Actualmente el modulo se encuentra en su fase inicial (estructura vacia de plantilla lista para implementar).
