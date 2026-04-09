# Checklist del TP1 - Programacion 2

Checklist verificada contra la consigna de [TP1.txt](/home/nicolas/Documentos/Programacion%202/Tp1%20/Documentacion/Ejercicio/TP1/TP1.txt) y el codigo fuente de `src`.

## Seccion 1: Creacion de clases, encapsulacion y documentacion

- [x] **Ejercicio 1.1 - `SistemaGestor` creada**
- [x] La clase tiene comentario Javadoc antes de su declaracion.
- [x] Se usan comentarios de una linea (`//`) para explicar variables locales.
- [x] Se usa comentario multilnea (`/* */`) para describir el bloque de configuracion inicial.

- [x] **Ejercicio 1.2 - Uso de `static` y `final`**
- [x] Existe la constante `public static final int MAX_CONEXIONES = 10;`.
- [x] Existe un metodo que imprime el valor de la constante: `imprimirLimiteConexiones()`.
- [x] Hay comentarios que explican por que el campo pertenece a la clase y por que no puede reasignarse.

- [x] **Ejercicio 1.3 - Encapsulacion en `CuentaBancaria`**
- [x] Los atributos `saldo` y `numeroCuenta` estan declarados como `private`.
- [x] Se exponen metodos `public` para operar de forma segura (`depositar`, `getSaldo`, `getNumeroCuenta`).
- [x] `depositar(double monto)` valida y rechaza montos negativos.

## Seccion 2: Diseno de metodos y constructores

- [x] **Ejercicio 2.1 - Clase `Reporte`**
- [x] Existe un metodo sin retorno: `generarEncabezado()`.
- [x] Existe un metodo con retorno: `obtenerCuerpo()`.

- [x] **Ejercicio 2.2 - Sobrecarga en `Usuario`**
- [x] Hay constructor por defecto.
- [x] Hay constructor que recibe solo `nombre`.
- [x] Hay constructor que recibe `nombre` y `edad`.
- [x] Existe sobrecarga del metodo `actualizarPerfil`.

- [x] **Ejercicio 2.3 - Argumentos variables en `Reporte`**
- [x] Existe un metodo con var-args: `imprimirSecciones(String... secciones)`.
- [x] El metodo recorre internamente las secciones con `for-each`.
- [x] Hay demostracion con tres argumentos.
- [x] Hay demostracion sin argumentos.

## Verificacion general

- [x] Los ejercicios pedidos por la consigna estan implementados.
- [x] Las salidas de consola de `SistemaGestor`, `CuentaBancaria`, `Reporte` y `Usuario` coinciden con la documentacion.
- [ ] `App.java` no ejecuta correctamente como archivo aislado con `java App.java` porque depende de otras clases del mismo directorio; no forma parte de un punto requerido de la consigna, pero conviene compilarlo junto al resto si se quiere usar como lanzador general.

**Estado final:** TP1 correcto segun la consigna.
