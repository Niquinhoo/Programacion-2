# Checklist: Errores y Excepciones

## Sección 1 - try/catch/finally/multi-catch

- [x] 1.1 Se captura `NumberFormatException` al parsear `"abc"`.
- [x] 1.1 Se imprime mensaje (`getMessage`) y tipo (`getClass().getName`).
- [x] 1.2 Se provoca `ArithmeticException` con división por cero.
- [x] 1.2 Existe bloque `finally` y muestra `"Limpieza final"`.
- [x] 1.3 Se usa `multi-catch` con `NumberFormatException | ArithmeticException`.
- [x] 1.3 Se muestra mensaje genérico de error de cálculo/conversión.

## Sección 2 - prevención y captura específica

- [x] 2.1 Método `registerUser(String nombre, int edad)` implementado.
- [x] 2.1 Validación fail-fast de nombre nulo/blanco.
- [x] 2.1 Validación fail-fast de edad negativa.
- [x] 2.1 Uso de `throw new IllegalArgumentException(...)`.
- [x] 2.2 Mensaje explícito: `"La edad no puede ser negativa."`.
- [x] 2.2 Captura específica de `IllegalArgumentException` en uso.

## Sección 3 - excepciones personalizadas

- [x] 3.1 Clase `SaldoInsuficienteException` creada y hereda de `Exception` (checked).
- [x] 3.1 `CuentaBancaria.retirar(double)` declara `throws SaldoInsuficienteException`.
- [x] 3.1 Se lanza excepción cuando monto > saldo.
- [x] 3.1 Manejo con `try/catch` en flujo principal.
- [x] 3.2 Clase `ProductoInvalidoException` creada y hereda de `RuntimeException` (unchecked).
- [x] 3.2 `Producto` valida precio y lanza excepción cuando `precio <= 0`.

## Sección 4 - try-with-resources

- [x] 4.1 Archivo `ErroresyExcepciones/src/personas.txt` creado.
- [x] 4.1 Lectura con `BufferedReader` dentro de `try (...)`.
- [x] 4.1 Manejo de `IOException` con `catch`.

## Arquitectura actual (actualizada)

- [x] `App` minimo, solo orquesta ejecucion.
- [x] `ExerciseRunner` centraliza secuencia de ejercicios.
- [x] `ExceptionExercises` concentra casos 1.1 a 4.1.
- [x] Servicios separados: `UserService`, `FileService`.
- [x] Dominio separado: `CuentaBancaria`, `Producto`.
- [x] Excepciones custom separadas: `SaldoInsuficienteException`, `ProductoInvalidoException`.
- [x] Utilidad de logging separada: `ConsoleLogger`.
- [x] Proyecto no uniarchivo.
- [x] Proyecto actual sin paquetes Java declarados (todas las clases en `src/`).
