# Logs: Errores y Excepciones

## 2026-04-30 - Validacion de arquitectura y salida real

### Comando ejecutado
```bash
cd ErroresyExcepciones/src && java App.java
```

### Salida real
```text
=== Ejercicio 1.1 - try/catch e info de excepcion ===
[ERROR] Mensaje: For input string: "abc"
[ERROR] Tipo: java.lang.NumberFormatException

=== Ejercicio 1.2 - finally ===
[ERROR] Capturada ArithmeticException: / by zero
[INFO] Limpieza final

=== Ejercicio 1.3 - multi-catch ===
[ERROR] Error de calculo o conversion

=== Ejercicio 1.3 - multi-catch ===
[ERROR] Error de calculo o conversion

=== Ejercicio 1.3 - multi-catch ===
[INFO] Resultado: 20

=== Ejercicio 2.1 - Fail-fast con validaciones ===
[ERROR] El nombre no puede ser nulo ni estar en blanco.
[ERROR] La edad no puede ser negativa.

=== Ejercicio 2.2 - Captura especifica con mensaje claro ===
[ERROR] La edad no puede ser negativa.

=== Ejercicio 3.1 - Excepcion checked personalizada ===
[ERROR] Saldo insuficiente. Saldo actual: 500.0, monto solicitado: 700.0

=== Ejercicio 3.2 - Excepcion unchecked personalizada ===
[ERROR] Precio invalido: debe ser mayor a cero.

=== Ejercicio 4.1 - try-with-resources ===
Ana Gomez
Bruno Diaz
Carla Lopez
```

## Hallazgos de arquitectura

- Proyecto actual funciona sin paquetes Java declarados.
- Entrada real: `ErroresyExcepciones/src/App.java`.
- Orquestacion: `ExerciseRunner` + `ExceptionExercises`.
- Servicios y dominio separados por clase (`UserService`, `FileService`, `CuentaBancaria`, `Producto`).
- Excepciones custom presentes: checked (`SaldoInsuficienteException`) y unchecked (`ProductoInvalidoException`).

## Estado final (2026-04-30)

- Ejecucion funcional en este entorno con `java App.java`: **si**.
- Logs de salida alineados con consigna de errores y excepciones: **si**.
