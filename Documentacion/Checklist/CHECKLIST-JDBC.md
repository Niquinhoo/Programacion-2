# Checklist - Base de Datos con JDBC

## Requisitos Generales de Conexion

- [x] Descarga del conector MySQL JDBC (`mysql-connector-j`) e incorporacion en la carpeta `lib/`.
- [x] Configuracion del servidor MySQL local o remoto (TiDB Cloud). *(Nota: el proyecto ya venia con esa parte trabajada previamente.)*
- [x] Creacion de la base de datos `test` y la tabla `empleados` en la etapa inicial del ejercicio.

## Ejercicio 5: Integracion y Conexion a Base de Datos con JDBC

- [x] Creacion de la clase `ConexionDB` utilizando `DriverManager.getConnection()`.
- [x] Implementacion de `OperacionesDB` con sentencias preparadas para CRUD basico.
- [x] Metodo `insertarEmpleado(String nombre, String departamento)`.
- [x] Metodo `actualizarDepartamento(int id, String nuevoDepartamento)`.
- [x] Metodo `borrarEmpleado(int id)`.
- [x] Validacion funcional mediante el metodo `main`.

## Ejercicio 5 Actualizado: Arquitectura MVC y Patron DAO para MySQL

- [x] Creacion de la clase modelo `Empleado` (POJO).
- [x] Definicion de la interfaz `OperacionesDAO` con la firma de los metodos CRUD.
- [x] Creacion de `EmpleadoDAO` implementando la interfaz `OperacionesDAO`.
- [x] Implementacion del metodo `consultarTodos()` en `EmpleadoDAO` retornando un `ArrayList<Empleado>`.
- [x] Inicializacion automatica del esquema relacional desde `EmpleadoDAO`.

## Ejercicio 6 Actualizado: Interaccion Avanzada con JTable (Eventos de Raton y Update/Delete)

- [x] Diseno visual de la ventana principal con `JTable` a la derecha y formulario lateral a la izquierda.
- [x] Carga dinamica de la tabla al iniciar usando `EmpleadoDAO.consultarTodos()`.
- [x] Implementacion de `MouseListener` sobre la tabla para seleccionar filas.
- [x] Carga automatica de ID, nombre, departamento y foto al seleccionar una fila.
- [x] Implementacion del boton `Modificar` con actualizacion en BD y refresco de tabla.
- [x] Implementacion del boton `Eliminar` con remocion en BD, limpieza de campos y refresco de tabla.

## Nuevo Ejercicio 7: CRUD Definitivo con Componentes Complejos y Claves Foraneas

- [x] Creacion/verificacion de la tabla `departamentos`.
- [x] Establecimiento de relacion FK entre `empleados.departamento_id` y `departamentos.id_depto`.
- [x] Reemplazo del campo de texto de departamento por un `JComboBox` poblado dinamicamente.
- [x] Integracion de `JFileChooser` para adjuntar una imagen del empleado.
- [x] Almacenamiento de la ruta de la imagen en base de datos (`foto_ruta`).
- [x] Previsualizacion dinamica de la foto del empleado en un `JLabel` mediante `setIcon()`.

---

## Clases principales esperadas

- `BaseDeDatosConJDBC/src/ConexionDB.java`
- `BaseDeDatosConJDBC/src/Empleado.java`
- `BaseDeDatosConJDBC/src/OperacionesDAO.java`
- `BaseDeDatosConJDBC/src/EmpleadoDAO.java`
- `BaseDeDatosConJDBC/src/VentanaPrincipal.java`
- `BaseDeDatosConJDBC/src/App.java`

---

## Estado actual de los ejercicios

- [x] Ejercicio 5 implementado.
- [x] Ejercicio 5 Actualizado (MVC / DAO) implementado.
- [x] Ejercicio 6 Actualizado (JTable interactivo) implementado.
- [x] Ejercicio 7 (componentes complejos y FK) implementado.

## Estado de validacion

- [x] Compilacion completa del modulo realizada sin errores.
- [ ] Validacion funcional conectada a una base de datos activa en este entorno.

Motivo de la validacion pendiente:

- No se detecto archivo `.env` en el modulo.
- Tampoco hubo variables `DB_*` activas durante la ejecucion.
- La conexion intento usar `jdbc:mysql://localhost:3306/empresa_db` y el servidor no respondio.
