# Programacion 2

Repositorio de trabajo para la materia **Programacion 2**, con foco actual en la resolucion de la guia de **Interfaces Graficas con Java Swing**.

## Modulo `InterfacesSwing`

Dentro del proyecto, `InterfacesSwing` contiene una aplicacion de escritorio en Java que resuelve los ejercicios de Swing y agrega un **menu principal grafico** para navegar entre ellos sin cambiar el codigo manualmente.

## Objetivo funcional

La aplicacion permite abrir desde una sola pantalla:

- Ejercicio 1: formulario interactivo con saludo.
- Ejercicio 2: mini editor de texto con barra de menus.
- Ejercicio 3: panel de preferencias con ventana secundaria modal.
- Ejercicio 4: pantalla de autenticacion con validacion de contraseña.

## Arquitectura

La solucion sigue una estructura modular, separando responsabilidades por tipo de clase:

- `App` y `Programacion2InterfacesSwing`: puntos de entrada.
- `Lanzador...`: inicializan cada ventana con `SwingUtilities.invokeLater(...)`.
- `Ventana...`: configuran cada `JFrame` principal.
- `Panel...`: construyen los componentes visuales.
- `Controlador...`: concentran la logica de eventos e interaccion.
- `DialogoPreferencias`: implementa la ventana secundaria del ejercicio 3 con `JDialog`.

Esta organizacion facilita mantenimiento, lectura del codigo y reutilizacion de pantallas.

## Flujo de navegacion

El flujo principal de `InterfacesSwing` es el siguiente:

1. `App` inicia `LanzadorMenuEjercicios`.
2. `LanzadorMenuEjercicios` crea `VentanaMenuEjercicios`.
3. `VentanaMenuEjercicios` muestra `PanelMenuEjercicios`.
4. `ControladorMenuEjercicios` escucha los botones del menu.
5. Cada boton abre automaticamente la ventana del ejercicio correspondiente.
6. Cada ejercicio funciona en su propia ventana y puede cerrarse sin finalizar el menu principal.

## Flujo interno por ejercicio

### Ejercicio 1

- `VentanaFormularioSaludo` muestra el formulario.
- `PanelFormularioSaludo` contiene `JLabel`, `JTextField` y `JButton`.
- Al hacer click en `Saludar`, se lee el texto ingresado y se actualiza la etiqueta de resultado.

### Ejercicio 2

- `VentanaEditorTexto` arma la ventana principal.
- `PanelEditorTexto` contiene el `JTextArea` dentro de un `JScrollPane`.
- `BarraMenuEditorTexto` define el menu `Archivo`.
- `ControladorEditorTexto` resuelve `Limpiar texto` y `Salir`.

### Ejercicio 3

- `VentanaPreferencias` muestra el estado actual de las opciones.
- `DialogoPreferencias` abre una ventana modal con tres `JCheckBox`.
- `ControladorPreferencias` procesa las opciones marcadas y actualiza la etiqueta principal.

### Ejercicio 4

- `VentanaLogin` construye la pantalla de acceso.
- `PanelLogin` contiene usuario, contraseña y boton `Acceder`.
- `ControladorLogin` obtiene la contraseña con `getPassword()`, compara contra una clave predefinida y muestra el resultado con `JOptionPane`.

## Estructura del repositorio

```text
Programacion-2/
|-- InterfacesSwing/
|   |-- bin/
|   |-- lib/
|   `-- src/
|       |-- App.java
|       |-- Programacion2InterfacesSwing.java
|       |-- LanzadorMenuEjercicios.java
|       |-- VentanaMenuEjercicios.java
|       |-- PanelMenuEjercicios.java
|       |-- ControladorMenuEjercicios.java
|       `-- ...resto de clases de los ejercicios
|-- Documentacion/
|   |-- Checklist/
|   |   `-- CHECKLIST-SWING.md
|   |-- Conclusion/
|   |   `-- CONCLUSION.md
|   |-- Ejercicio/
|   |   `-- GuiaInterfacesSwing.md
|   `-- Logs/
|       `-- LOGS_CONSOLA-SWING.md
`-- README.md
```

## Como compilar y ejecutar

Desde `C:\Users\nicot\Desktop\Programacion-2\InterfacesSwing`:

```powershell
javac -encoding UTF-8 -d bin src\*.java
java -cp bin App
```

## Documentacion complementaria

- `Documentacion/Ejercicio/GuiaInterfacesSwing.md`: consigna base de los ejercicios.
- `Documentacion/Checklist/CHECKLIST-SWING.md`: seguimiento de requisitos implementados.
- `Documentacion/Logs/LOGS_CONSOLA-SWING.md`: registro de compilaciones, arranques y validaciones.
- `Documentacion/Conclusion/CONCLUSION.md`: cierre general del trabajo.

## Estado del modulo

Actualmente el modulo `InterfacesSwing` incluye:

- implementacion de los ejercicios 1 al 4,
- menu grafico de navegacion entre ejercicios,
- codigo separado por responsabilidades,
- documentacion de apoyo para seguimiento y evidencia.
