# Conclusión: Errores y Excepciones

Se completo guia con manejo de excepciones correcto y arquitectura separada por responsabilidades.

## Aprendizajes clave

- `try/catch` permite capturar fallas concretas y mostrar contexto útil del error.
- `finally` garantiza limpieza aun cuando ocurre excepción.
- `multi-catch` simplifica manejo cuando dos errores comparten tratamiento.
- Validación **fail-fast** corta flujo temprano y evita estados inconsistentes.
- Excepciones personalizadas separan errores de dominio de errores tecnicos.
- Diferencia central:
  - `Exception` (checked): compilador obliga manejo.
  - `RuntimeException` (unchecked): útil para mal uso de API/datos inválidos.
- `try-with-resources` reduce fugas y elimina cierre manual repetitivo.

## Valor de arquitectura nueva

Separar clases por capa en `src/` mejora:

- mantenibilidad,
- legibilidad,
- testeo por unidad,
- escalabilidad para próximos TPs.

Arquitectura final:
- entrada: `App`
- orquestador: `ExerciseRunner`
- casos: `ExceptionExercises`
- servicios: `UserService`, `FileService`
- dominio: `CuentaBancaria`, `Producto`
- excepciones: `SaldoInsuficienteException`, `ProductoInvalidoException`
- util: `ConsoleLogger`

## Cierre final

Objetivo tecnico cumplido: no quedo uniarchivo; quedo base clara, extensible y ejecutable con salida validada el 2026-04-30.
