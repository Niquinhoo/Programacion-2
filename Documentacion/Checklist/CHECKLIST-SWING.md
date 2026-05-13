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

## Clases involucradas

- `App`: punto de entrada de la aplicacion.
- `LanzadorEjercicio1`: inicializa la interfaz usando `SwingUtilities.invokeLater`.
- `VentanaFormularioSaludo`: configura la ventana principal.
- `PanelFormularioSaludo`: arma el formulario y maneja la interaccion del boton.

## Estado actual

- [x] Ejercicio 1 implementado.
- [ ] Ejercicio 2 implementado.
- [ ] Ejercicio 3 implementado.
- [ ] Ejercicio 4 implementado.
