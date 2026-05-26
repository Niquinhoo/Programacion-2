# Checklist - Base de Datos con JDBC

## Requisitos Generales de Conexión

- [x] Descarga del conector MySQL JDBC (`mysql-connector-j`) e incorporación en la carpeta `lib/`.
- [x] Configuración del servidor MySQL local o remoto (TiDB Cloud). *(Nota: El usuario ya tenía instalado MySQL local por su cuenta hace mucho tiempo; se configuró la conexión remota a la instancia de TiDB Cloud)*
- [x] Creación de la base de datos `test` y la tabla `empleados` (verificada y creada automáticamente).

## Ejercicio 5: Integración y Conexión a Base de Datos con JDBC

- [x] Creación de la clase `ConexionDB` utilizando `DriverManager.getConnection()`.
- [x] Implementación de `OperacionesDB` con sentencias preparadas para CRUD básico.
- [x] Método `insertarEmpleado(String nombre, String departamento)`.
- [x] Método `actualizarDepartamento(int id, String nuevoDepartamento)`.
- [x] Método `borrarEmpleado(int id)`.
- [x] Validación funcional mediante el método `main`.


## Ejercicio 5 Actualizado: Arquitectura MVC y Patrón DAO para MySQL

- [ ] Creación de la clase modelo `Empleado` (POJO: id, nombre, departamento).
- [ ] Definición de la interfaz `OperacionesDAO` con la firma de los métodos CRUD.
- [ ] Creación de `EmpleadoDAO` implementando la interfaz `OperacionesDAO`.
- [ ] Implementación del método `consultarTodos()` en `EmpleadoDAO` retornando un `ArrayList<Empleado>`.

## Ejercicio 6 Actualizado: Interacción Avanzada con JTable (Eventos de Ratón y Update/Delete)

- [ ] Diseño visual de la ventana principal con `JTable` (derecha) y formulario lateral de edición (izquierda).
- [ ] Carga dinámica de la tabla al iniciar usando los datos de `EmpleadoDAO.consultarTodos()`.
- [ ] Implementación de `MouseListener` sobre la tabla para seleccionar filas (`getSelectedRow()`).
- [ ] Carga automática de los campos de texto (`JTextField` para ID, Nombre y Departamento) al seleccionar una fila.
- [ ] Implementación del botón "Modificar" (UPDATE) con actualización en BD y refresco de tabla.
- [ ] Implementación del botón "Eliminar" (DELETE) con remoción en BD, limpieza de campos y refresco de tabla.

## Nuevo Ejercicio 7: CRUD Definitivo con Componentes Complejos y Claves Foráneas

- [ ] Creación de la tabla `departamentos` en la base de datos y establecimiento de relación FK en `empleados`.
- [ ] Reemplazo del campo de texto de departamento por un `JComboBox` poblado dinámicamente desde la tabla `departamentos`.
- [ ] Integración de `JFileChooser` para adjuntar una imagen del empleado.
- [ ] Almacenamiento de la ruta/bytes de la imagen en base de datos.
- [ ] Previsualización dinámica de la foto del empleado en un `JLabel` mediante `setIcon()`.

---

## Clases principales esperadas

- `BaseDeDatosConJDBC/src/ConexionDB.java`
- `BaseDeDatosConJDBC/src/Empleado.java`
- `BaseDeDatosConJDBC/src/OperacionesDAO.java`
- `BaseDeDatosConJDBC/src/EmpleadoDAO.java`
- `BaseDeDatosConJDBC/src/VentanaPrincipal.java`

---

## Estado actual de los Ejercicios

- [x] Ejercicio 5 implementado.
- [ ] Ejercicio 5 Actualizado (MVC / DAO) implementado.
- [ ] Ejercicio 6 Actualizado (JTable interactivo) implementado.
- [ ] Ejercicio 7 (Componentes complejos y FK) implementado.


