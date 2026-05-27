# Base de Datos con JDBC - Programacion 2

Repositorio de resolucion para la unidad de **Base de Datos con JDBC** de la materia **Programacion 2**.

El proyecto muestra una evolucion progresiva desde una conexion JDBC basica hasta una estructura mas cercana a una aplicacion real de escritorio, incorporando:

- conexion a base de datos con `DriverManager`;
- operaciones CRUD con `PreparedStatement`;
- separacion por capas con modelo + DAO;
- interfaz grafica Swing con `JTable`;
- seleccion y edicion de registros;
- manejo de departamentos mediante clave foranea;
- seleccion y previsualizacion de foto de empleado.

## Objetivo del trabajo

Implementar y documentar una aplicacion Java que permita conectarse a una base de datos MySQL/TiDB, administrar empleados y reflejar la evolucion de la guia practica desde JDBC inicial hasta una solucion con interfaz grafica y organizacion tipo MVC/DAO.

## Estructura del repositorio

```text
Programacion-2/
|-- BaseDeDatosConJDBC/
|   |-- bin/
|   |-- lib/
|   |   `-- mysql-connector-j-8.3.0.jar
|   `-- src/
|       |-- App.java
|       |-- ConexionDB.java
|       |-- OperacionesDB.java
|       |-- Empleado.java
|       |-- OperacionesDAO.java
|       |-- EmpleadoDAO.java
|       `-- VentanaPrincipal.java
|-- Documentacion/
|   |-- Checklist/
|   |   `-- CHECKLIST-JDBC.md
|   |-- Conclusion/
|   |   `-- CONCLUSION-JDBC.md
|   |-- Ejercicio/
|   |   `-- GuiaBaseDeDatosJDBC.md
|   `-- Logs/
|       `-- LOGS_CONSOLA-JDBC.md
`-- README.md
```

## Componentes principales

### `ConexionDB`

Centraliza la apertura de conexiones JDBC. Lee configuracion desde un archivo `.env` local o desde variables de entorno del sistema.

Variables esperadas:

- `DB_HOST`
- `DB_PORT`
- `DB_DATABASE`
- `DB_USERNAME`
- `DB_PASSWORD`

### `OperacionesDB`

Implementa el ejercicio inicial de JDBC con CRUD basico:

- insertar empleado
- actualizar departamento
- borrar empleado

### `Empleado`

Modelo de datos del empleado. Representa la informacion que usa la aplicacion en memoria.

### `OperacionesDAO`

Interfaz que define las operaciones del acceso a datos para la version actualizada del ejercicio.

### `EmpleadoDAO`

Encapsula la logica JDBC de la capa DAO. Se encarga de:

- inicializar/verificar el esquema;
- crear la tabla `departamentos` si hace falta;
- asegurar la relacion entre `empleados` y `departamentos`;
- insertar, actualizar, eliminar y consultar empleados;
- devolver departamentos para poblar la interfaz.

### `VentanaPrincipal`

Vista Swing del proyecto. Incluye:

- tabla de empleados;
- formulario lateral;
- seleccion por mouse;
- botones de alta, modificacion y borrado;
- `JComboBox` para departamentos;
- `JFileChooser` para seleccionar foto;
- previsualizacion de imagen.

## Base de datos

La implementacion actual trabaja sobre una estructura que contempla:

### Tabla `departamentos`

- `id_depto`
- `nombre_depto`

### Tabla `empleados`

- `id`
- `nombre`
- `departamento`
- `departamento_id`
- `foto_ruta`

Nota: se mantuvo el campo textual `departamento` para conservar compatibilidad con la evolucion del ejercicio y facilitar la migracion desde la version mas simple.

## Como compilar

Desde la carpeta `BaseDeDatosConJDBC`:

```powershell
javac -d bin -cp "lib/mysql-connector-j-8.3.0.jar;bin" src\App.java src\ConexionDB.java src\OperacionesDB.java src\Empleado.java src\OperacionesDAO.java src\EmpleadoDAO.java src\VentanaPrincipal.java
```

## Como ejecutar

### Prueba de conexion

```powershell
java -cp "lib/mysql-connector-j-8.3.0.jar;bin" ConexionDB
```

### CRUD basico por consola

```powershell
java -cp "lib/mysql-connector-j-8.3.0.jar;bin" OperacionesDB
```

### Aplicacion de escritorio

```powershell
java -cp "lib/mysql-connector-j-8.3.0.jar;bin" App
```

## Estado actual

- JDBC basico implementado.
- Arquitectura DAO implementada.
- Interfaz Swing implementada.
- Documentacion complementaria completada.
- Compilacion verificada.
- Validacion final dependiente de una base de datos accesible desde el entorno de ejecucion.

## Documentacion incluida

- [Guia del ejercicio](C:/Users/nicot/Desktop/Programacion-2/Documentacion/Ejercicio/GuiaBaseDeDatosJDBC.md)
- [Checklist de avance](C:/Users/nicot/Desktop/Programacion-2/Documentacion/Checklist/CHECKLIST-JDBC.md)
- [Logs de consola](C:/Users/nicot/Desktop/Programacion-2/Documentacion/Logs/LOGS_CONSOLA-JDBC.md)
- [Conclusion final](C:/Users/nicot/Desktop/Programacion-2/Documentacion/Conclusion/CONCLUSION-JDBC.md)

## Observacion importante

En la ultima validacion realizada dentro de este entorno, la aplicacion compilo correctamente pero no pudo conectarse a la base porque no estaba disponible el archivo `.env` ni variables `DB_*` activas. Apenas se reponga esa configuracion, el proyecto queda listo para probar el flujo completo.
