# Checklist - Interfaces Swing

## Ejercicio 1: Formulario interactivo basico

- [x] Se creo una ventana principal mediante `JFrame`.
- [x] Se definio el tamano de la ventana usando `setSize()`.
- [x] Se agrego un `JPanel` como contenedor intermedio.
- [x] Se incorporo una `JLabel` con el texto `Introduce tu nombre:`.
- [x] Se incorporo un `JTextField` de una linea con 20 columnas.
- [x] Se agrego un `JButton` con el texto `Saludar`.
- [x] Se agrego una `JLabel` inferior para mostrar el resultado.
- [x] Se implemento `ActionListener` en el panel del formulario.
- [x] Se registro el evento del boton mediante `addActionListener()`.
- [x] Se obtuvo el texto ingresado usando `getText()`.
- [x] Se actualizo la etiqueta de resultado con el mensaje `¡Hola, [Nombre]!`.
- [x] La solucion fue modularizada en clases separadas y con nombres en espanol.

## Clases involucradas en el ejercicio 1

- `App`: punto de entrada original de la aplicacion para este ejercicio.
- `LanzadorEjercicio1`: inicializa la interfaz usando `SwingUtilities.invokeLater`.
- `VentanaFormularioSaludo`: configura la ventana principal.
- `PanelFormularioSaludo`: arma el formulario y maneja la interaccion del boton.

## Ejercicio 2: Mini editor de texto con barra de menus

- [x] Se creo una ventana principal mediante `JFrame`.
- [x] Se agrego una barra superior `JMenuBar` usando `setJMenuBar()`.
- [x] Se creo un menu `Archivo`.
- [x] Se agrego el item `Limpiar texto`.
- [x] Se agrego el item `Salir`.
- [x] Se incorporo un `JTextArea` para multiples lineas.
- [x] El `JTextArea` fue envuelto en un `JScrollPane`.
- [x] Se asociaron `ActionListener` a ambos items del menu.
- [x] La opcion `Limpiar texto` ejecuta `setText("")`.
- [x] La opcion `Salir` cierra la ventana principal.
- [x] La solucion fue modularizada en clases separadas y con nombres en espanol.

## Clases involucradas en el ejercicio 2

- `App`: punto de entrada actual de la aplicacion.
- `LanzadorEjercicio2`: inicializa la interfaz del editor usando `SwingUtilities.invokeLater`.
- `VentanaEditorTexto`: configura la ventana principal del ejercicio 2.
- `PanelEditorTexto`: contiene el area de texto y el `JScrollPane`.
- `BarraMenuEditorTexto`: construye la barra de menus y sus opciones.
- `ControladorEditorTexto`: maneja los eventos de `Limpiar texto` y `Salir`.

## Estado actual

- [x] Ejercicio 1 implementado.
- [x] Ejercicio 2 implementado.
- [ ] Ejercicio 3 implementado.
- [ ] Ejercicio 4 implementado.
