# Resolución: Guía de Ejercicios Prácticos - Unidad 3 (Errores y Excepciones)

## Estructura modular implementada

Se evitó enfoque de un solo archivo. Solución quedó separada por capas:

- `ErroresyExcepciones/src/erroresyexcepciones/App.java`
- `ErroresyExcepciones/src/erroresyexcepciones/exercises/ExerciseRunner.java`
- `ErroresyExcepciones/src/erroresyexcepciones/exercises/ExceptionExercises.java`
- `ErroresyExcepciones/src/erroresyexcepciones/service/UserService.java`
- `ErroresyExcepciones/src/erroresyexcepciones/service/FileService.java`
- `ErroresyExcepciones/src/erroresyexcepciones/model/CuentaBancaria.java`
- `ErroresyExcepciones/src/erroresyexcepciones/model/Producto.java`
- `ErroresyExcepciones/src/erroresyexcepciones/exceptions/SaldoInsuficienteException.java`
- `ErroresyExcepciones/src/erroresyexcepciones/exceptions/ProductoInvalidoException.java`
- `ErroresyExcepciones/src/erroresyexcepciones/util/ConsoleLogger.java`
- `ErroresyExcepciones/personas.txt`

## Desarrollo por ejercicios

### Sección 1: try/catch/finally y multi-catch

- **1.1**: conversión de `"abc"` con `Integer.parseInt`, captura de `NumberFormatException`, impresión de `getMessage()` y `getClass().getName()`.
- **1.2**: división `10 / 0`, captura `ArithmeticException`, ejecución garantizada de `finally` con mensaje `"Limpieza final"`.
- **1.3**: conversión + división en una misma operación con `catch (NumberFormatException | ArithmeticException e)` y mensaje genérico de error.

### Sección 2: prevención y metodología

- **2.1**: método `registerUser(String nombre, int edad)` con enfoque fail-fast:
  - `nombre == null || nombre.isBlank()` -> `IllegalArgumentException`
  - `edad < 0` -> `IllegalArgumentException`
- **2.2**: mensaje explícito para edad inválida: `"La edad no puede ser negativa."`; captura específica de `IllegalArgumentException` en llamada.

### Sección 3: excepciones personalizadas

- **3.1 (checked)**:
  - `SaldoInsuficienteException extends Exception`
  - `CuentaBancaria.retirar(double monto) throws SaldoInsuficienteException`
  - en `main` (vía runner) se maneja con `try/catch` obligatorio.
- **3.2 (unchecked)**:
  - `ProductoInvalidoException extends RuntimeException`
  - `Producto` valida precio en constructor y lanza excepción si `precio <= 0`.

### Sección 4: automatización de recursos

- **4.1**: lectura de `personas.txt` con `try-with-resources` usando `BufferedReader` (`Files.newBufferedReader(...)`) y `catch(IOException)`.

## Punto de entrada y ejecución

`App` solo arranca flujo general:

- crea `ExerciseRunner`
- ejecuta `runAll()`

Resultado: responsabilidad separada, código mantenible, reusable y testeable por módulo.
