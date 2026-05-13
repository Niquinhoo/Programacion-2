# Logs de Consola - Interfaces Swing

## Ejercicio 1: Formulario interactivo basico

Registro de compilacion y ejecucion realizado sobre la implementacion actual del ejercicio 1.

## Compilacion

Comando ejecutado desde `InterfacesSwing`:

```powershell
javac -d bin src\*.java
```

Resultado observado:

```text
Compilacion completada sin errores.
```

## Ejecucion

Comando de inicio:

```powershell
java -cp bin App
```

Resultado observado:

```text
La aplicacion abre una ventana Swing titulada "Ejercicio 1 - Formulario interactivo".
No se generan mensajes por consola durante la ejecucion normal.
```

## Prueba funcional manual

1. Se escribe un nombre en el campo de texto.
2. Se presiona el boton `Saludar`.
3. La etiqueta inferior muestra el saludo correspondiente.

Ejemplo verificado:

```text
Entrada: Nico
Salida visual: ¡Hola, Nico!
```
