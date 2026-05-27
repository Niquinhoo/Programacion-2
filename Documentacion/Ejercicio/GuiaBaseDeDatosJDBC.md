## Ejercicio 5: Integracion y Conexion a Base de Datos con JDBC

**Objetivo**: Configurar un entorno de base de datos local y utilizar la API JDBC (Java Database Connectivity) para establecer una conexion y realizar las operaciones fundamentales de insercion, actualizacion y borrado (Insert, Update, Delete).

### Paso 1: Instalacion de MySQL y MySQL Workbench (Preparacion del entorno)
1. Descargar MySQL Installer desde la pagina oficial.
2. Instalar MySQL Server y MySQL Workbench.
3. Configurar el usuario administrador (`root`) y su clave.

### Paso 2: Creacion de la base de datos y la tabla
1. Crear la base y la tabla `empleados`.
2. Verificar los campos `id`, `nombre` y `departamento`.

### Paso 3: Clase para establecer la conexion
Se implemento `ConexionDB` para centralizar la lectura de credenciales y el uso de `DriverManager.getConnection()`.

### Paso 4: Clase para ejecutar el CRUD basico
Se implemento `OperacionesDB` con metodos `insertarEmpleado`, `actualizarDepartamento` y `borrarEmpleado`.

---

## Ejercicio 5 Actualizado: Arquitectura MVC y Patron DAO para MySQL

En lugar de mezclar conexion y consultas en una sola clase, se separaron responsabilidades siguiendo una estructura mas clara.

**Objetivo**: Crear una estructura orientada a objetos para manejar la tabla `empleados` utilizando el patron DAO.

### Implementacion realizada

1. **Modelo `Empleado`**  
   Se creo `BaseDeDatosConJDBC/src/Empleado.java` como POJO con:
   - `id`
   - `nombre`
   - `departamentoId`
   - `departamento`
   - `fotoRuta`

2. **Interfaz `OperacionesDAO`**  
   Se creo `BaseDeDatosConJDBC/src/OperacionesDAO.java` con los metodos:
   - `inicializarBaseDeDatos()`
   - `insertar(Empleado empleado)`
   - `actualizar(Empleado empleado)`
   - `eliminar(int id)`
   - `consultarTodos()`
   - `consultarDepartamentos()`

3. **Clase `EmpleadoDAO`**  
   Se creo `BaseDeDatosConJDBC/src/EmpleadoDAO.java` para encapsular la logica JDBC del nuevo esquema. Esta clase:
   - verifica o crea la tabla `departamentos`;
   - inserta departamentos base;
   - verifica o adapta la tabla `empleados`;
   - agrega soporte para `departamento_id` y `foto_ruta`;
   - sincroniza datos heredados del campo textual `departamento`;
   - consulta empleados con `JOIN` para devolver el nombre legible del departamento.

### Resultado de esta parte

Con esta separacion:

- `ConexionDB` queda responsable solo de la conexion;
- `Empleado` representa los datos;
- `EmpleadoDAO` concentra el acceso a base;
- la vista puede consumir datos sin escribir SQL directamente.

---

## Ejercicio 6 Actualizado: Interaccion Avanzada con JTable (Eventos de Raton y Update/Delete)

**Objetivo**: Desarrollar un formulario interactivo (la vista en MVC) que consuma los datos de `EmpleadoDAO` y permita la edicion directa.

### Implementacion realizada

Se construyo `BaseDeDatosConJDBC/src/VentanaPrincipal.java` con:

- `JTable` dentro de `JScrollPane` para visualizar empleados;
- formulario lateral izquierdo;
- `JTextField` para ID y nombre;
- `JComboBox` para departamentos;
- `JLabel` para ruta y previsualizacion de foto;
- botones `Agregar`, `Modificar`, `Eliminar`, `Limpiar` y `Buscar foto`.

### Comportamiento implementado

1. Al iniciar la ventana, se consultan empleados y departamentos desde `EmpleadoDAO`.
2. La tabla se carga mediante `DefaultTableModel`.
3. Se agrego un `MouseListener` para detectar la fila seleccionada.
4. Cuando el usuario hace clic sobre una fila:
   - se copia el ID al formulario;
   - se completa el nombre;
   - se selecciona el departamento correcto en el combo;
   - se muestra la ruta de la foto;
   - se intenta cargar la previsualizacion si el archivo existe.
5. El boton `Modificar` actualiza el registro y recarga la tabla.
6. El boton `Eliminar` borra el registro, limpia el formulario y refresca la tabla.

---

## Nuevo Ejercicio 7: CRUD Definitivo con Componentes Complejos y Claves Foraneas

**Objetivo**: Integrar listas desplegables y selectores de archivos en el ciclo de guardado de datos.

### Implementacion realizada

1. **Preparacion de base de datos**
   - Se aseguro la existencia de la tabla `departamentos`.
   - Se incorporo la columna `departamento_id` en `empleados`.
   - Se dejo preparada la clave foranea `fk_empleados_departamentos`.

2. **Uso de `JComboBox`**
   - El formulario ya no edita manualmente el departamento con texto libre.
   - `VentanaPrincipal` consulta `EmpleadoDAO.consultarDepartamentos()` y llena el combo dinamicamente.
   - Al insertar o modificar, se guarda el `id` del departamento seleccionado.

3. **Adjuntar fotografia con `JFileChooser`**
   - Se agrego un boton `Buscar foto`.
   - El selector devuelve la ruta absoluta del archivo elegido.
   - La ruta se guarda en la columna `foto_ruta`.
   - La imagen se previsualiza en un `JLabel` usando `ImageIcon`.

### Estado actual de la guia

- La implementacion completa ya compila.
- La ejecucion contra base de datos quedo pendiente en este entorno porque no hubo archivo `.env` ni variables `DB_*` activas.
- Cuando esa configuracion se restablezca, la aplicacion puede ejecutarse desde `BaseDeDatosConJDBC/src/App.java`.

### Archivos relevantes creados o completados

- `BaseDeDatosConJDBC/src/Empleado.java`
- `BaseDeDatosConJDBC/src/OperacionesDAO.java`
- `BaseDeDatosConJDBC/src/EmpleadoDAO.java`
- `BaseDeDatosConJDBC/src/VentanaPrincipal.java`
- `BaseDeDatosConJDBC/src/App.java`
