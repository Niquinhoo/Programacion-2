# Logs de consola - Verificacion del TP1

Documento ajustado segun la ejecucion real de los archivos fuente del TP1.

## Seccion 1: Creacion de clases, encapsulacion y documentacion

### Ejercicios 1.1 y 1.2 - `SistemaGestor`

**Comando ejecutado**
```bash
java "Tp1 /Clase-1-Codigo/Ejercicio 1-2/src/SistemaGestor.java"
```

**Salida en consola**
```text
Iniciando Sistema Gestor...
Sistema activo. Intentos realizados: 0
Límite máximo de conexiones: 10
```

**Verificacion**
- La clase ejecuta correctamente y muestra el bloque de configuracion inicial.
- Se imprime el valor de `MAX_CONEXIONES`, validando el uso de `static final`.

### Ejercicio 1.3 - `CuentaBancaria`

**Comando ejecutado**
```bash
java "Tp1 /Clase-1-Codigo/Ejercicio 1-2/src/CuentaBancaria.java"
```

**Salida en consola**
```text
Número de Cuenta: 987654321
Depósito exitoso de: $1500.0
Error: No se pueden depositar montos negativos (-200.0).
Saldo actual: $1500.0
```

**Verificacion**
- La cuenta informa su numero correctamente.
- El deposito positivo actualiza el saldo.
- El deposito negativo es rechazado y no altera el estado final de la cuenta.

## Seccion 2: Diseno de metodos y constructores

### Ejercicios 2.1 y 2.3 - `Reporte`

**Comando ejecutado**
```bash
java "Tp1 /Clase-1-Codigo/Ejercicio 1-2/src/Reporte.java"
```

**Salida en consola**
```text
========================================
          REPORTE DE SISTEMA
========================================
Este es el contenido principal del reporte generado por el sistema.

--- Demostración de Argumentos Variables (var-args) ---
Llamada con 3 argumentos:
Secciones del reporte:
 - Introducción
 - Desarrollo
 - Conclusión

Llamada sin argumentos:
[Aviso] No se han proporcionado secciones adicionales.
```

**Verificacion**
- `generarEncabezado()` imprime texto en consola y cumple el caso `void`.
- `obtenerCuerpo()` devuelve un `String` que luego se imprime.
- `imprimirSecciones(String... secciones)` funciona tanto con tres argumentos como sin argumentos.

### Ejercicio 2.2 - `Usuario`

**Comando ejecutado**
```bash
java "Tp1 /Clase-1-Codigo/Ejercicio 1-2/src/Usuario.java"
```

**Salida en consola**
```text
--- Demostración de Sobrecarga de Constructores ---
Usuario: [Nombre=Invitado, Edad=0]
Usuario: [Nombre=Nicolas, Edad=18]
Usuario: [Nombre=Andres, Edad=22]

--- Demostración de Sobrecarga de Métodos ---
Perfil actualizado: Correo fijado en nico@example.com
Perfil actualizado: Correo andres.prof@gmail.com y Teléfono 11223344
```

**Verificacion**
- Se prueban correctamente los tres constructores pedidos.
- Se prueban correctamente las dos versiones de `actualizarPerfil`.

## Observacion adicional

El archivo `App.java` no forma parte directa de los puntos pedidos en la consigna. Al ejecutarlo como archivo aislado con:

```bash
java "Tp1 /Clase-1-Codigo/Ejercicio 1-2/src/App.java"
```

aparece error de compilacion por dependencias no resueltas (`SistemaGestor` y `CuentaBancaria`) en modo source-file. Si queres usar `App.java` como lanzador general, conviene compilar todas las clases juntas desde `src`.
