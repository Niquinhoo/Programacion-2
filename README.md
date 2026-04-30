# Programacion 2 - Errores y Excepciones

Proyecto Java enfocado en practica de manejo de errores y excepciones:
- `try/catch/finally`
- `multi-catch`
- validaciones fail-fast
- excepciones personalizadas checked/unchecked
- `try-with-resources`

## Arquitectura actual

Arquitectura por capas simples, sin paquetes declarados, con clases en `ErroresyExcepciones/src`.

```text
ErroresyExcepciones/
├── src/
│   ├── App.java                         # Punto de entrada
│   ├── ExerciseRunner.java              # Orquesta ejecucion de ejercicios
│   ├── ExceptionExercises.java          # Casos 1.1 a 4.1
│   ├── UserService.java                 # Validaciones de usuario
│   ├── FileService.java                 # Lectura de archivo con try-with-resources
│   ├── CuentaBancaria.java              # Dominio cuenta
│   ├── Producto.java                    # Dominio producto
│   ├── SaldoInsuficienteException.java  # Checked exception
│   ├── ProductoInvalidoException.java   # Unchecked exception
│   ├── ConsoleLogger.java               # Salida estandar de logs
│   └── personas.txt                     # Input para ejercicio 4.1
└── (sin Makefile activo)
```

## Flujo de ejecucion

1. `App` crea `ExerciseRunner`.
2. `ExerciseRunner` inyecta `UserService` y `FileService` en `ExceptionExercises`.
3. `ExceptionExercises` ejecuta todos los casos en secuencia y delega:
   - reglas de usuario a `UserService`
   - lectura de archivo a `FileService`
   - reglas de dominio a `CuentaBancaria` y `Producto`
4. `ConsoleLogger` normaliza salida `[INFO]/[ERROR]`.

## Como correr

Desde raiz repo:

```bash
cd ErroresyExcepciones/src
java App.java
```

Nota:
- En este entorno, `java` funciona.
- `javac` puede no estar disponible en PATH.

## Documentacion complementaria

- `Documentacion/Logs/LOGSERRORESYEXCEPCIONES.md`
- `Documentacion/Checklist/CHECKLISTERRORESYEXCEPCIONES.md`
- `Documentacion/Conclusion/CONCLUSIONERRORESYEXCEPCIONES.md`
