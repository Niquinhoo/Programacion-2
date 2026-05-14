# Logs de Consola - Interfaces Swing

## Ejercicio 1: Formulario interactivo basico

Registro de compilacion y ejecucion realizado sobre la implementacion del ejercicio 1.

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

---

## Ejercicio 2: Mini editor de texto con barra de menus

Registro de compilacion y ejecucion realizado sobre la implementacion actual del ejercicio 2.

## Compilacion

Comando ejecutado desde `InterfacesSwing`:

```powershell
javac -encoding UTF-8 -d bin src\*.java
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
La aplicacion abre una ventana Swing titulada "Ejercicio 2 - Mini editor de texto".
No se generan mensajes por consola durante la ejecucion normal.
```

## Prueba funcional manual

1. Se escribe texto en el area central.
2. Desde el menu `Archivo`, se selecciona `Limpiar texto`.
3. El contenido del area de texto se borra por completo.
4. Desde el menu `Archivo`, se selecciona `Salir`.
5. La ventana se cierra correctamente.

Ejemplo verificado:

```text
Entrada en el area: Este es un texto de prueba.
Accion 1: Archivo > Limpiar texto
Resultado visual: el area queda vacia.

Accion 2: Archivo > Salir
Resultado visual: la aplicacion finaliza cerrando la ventana.
```
