# Logs de Consola - Base de Datos con JDBC

## Compilacion y ejecucion

Registro de comandos ejecutados y resultados observados en consola.

### Conexion a Base de Datos (Clase `ConexionDB`)

Prueba historica de conexion con los parametros de entorno remotos provistos originalmente.

#### Compilacion

```powershell
javac -d bin -cp "lib/mysql-connector-j-8.3.0.jar;bin" src/ConexionDB.java
```

Resultado:

```text
Compilacion completada sin errores.
```

#### Ejecucion

```powershell
java -cp "lib/mysql-connector-j-8.3.0.jar;bin" ConexionDB
```

Resultado:

```text
Conexion a la base de datos establecida con exito.
Conexion cerrada correctamente.
```

### Operaciones CRUD basicas (Clase `OperacionesDB`)

#### Compilacion

```powershell
javac -d bin -cp "lib/mysql-connector-j-8.3.0.jar;bin" src/ConexionDB.java src/OperacionesDB.java
```

Resultado:

```text
Compilacion completada sin errores.
```

#### Ejecucion

```powershell
java -cp "lib/mysql-connector-j-8.3.0.jar;bin" OperacionesDB
```

Resultado:

```text
Conexion a la base de datos establecida con exito.
Tabla 'empleados' verificada/creada correctamente.
Conexion a la base de datos establecida con exito.
Empleado 'Ana Garcia' insertado correctamente.
Conexion a la base de datos establecida con exito.
El departamento del empleado con ID 1 fue actualizado a: Finanzas
Conexion a la base de datos establecida con exito.
El empleado con ID 1 ha sido borrado exitosamente.
```

### Implementacion MVC/DAO + Swing

#### Compilacion completa del proyecto

```powershell
javac -d bin -cp "lib/mysql-connector-j-8.3.0.jar;bin" src\App.java src\ConexionDB.java src\OperacionesDB.java src\Empleado.java src\OperacionesDAO.java src\EmpleadoDAO.java src\VentanaPrincipal.java
```

Resultado:

```text
Compilacion completada sin errores.
```

#### Ejecucion de prueba del DAO

```powershell
java -cp "lib/mysql-connector-j-8.3.0.jar;bin" EmpleadoDAO
```

Resultado:

```text
Nota: No se pudo cargar el archivo .env local (.env (El sistema no puede encontrar el archivo especificado)). Se intentara usar variables del sistema.
Error al conectar con la base de datos:
URL intentada: jdbc:mysql://localhost:3306/empresa_db?useSSL=true&trustServerCertificate=true&serverTimezone=UTC
Usuario: root
Mensaje de error: Communications link failure

The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
No fue posible inicializar el esquema porque no hay conexion disponible.
Error al conectar con la base de datos:
URL intentada: jdbc:mysql://localhost:3306/empresa_db?useSSL=true&trustServerCertificate=true&serverTimezone=UTC
Usuario: root
Mensaje de error: Communications link failure

The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
```

#### Interpretacion

- La implementacion nueva compila correctamente.
- La ejecucion conectada no pudo completarse porque no habia configuracion activa de base de datos en el entorno actual.
- Para validar el flujo completo de `App` y `VentanaPrincipal`, hace falta restaurar `.env` o definir `DB_HOST`, `DB_PORT`, `DB_DATABASE`, `DB_USERNAME` y `DB_PASSWORD`.
