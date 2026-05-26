# Logs de Consola - Base de Datos con JDBC

## Compilación y Ejecución

Registro de comandos ejecutados y resultados observados en consola.

### Conexión a Base de Datos (Clase ConexionDB)

Prueba de conexión con los parámetros de entorno remotos provistos (TiDB Cloud / MySQL).

#### Compilación

```powershell
javac -d bin -cp "lib/mysql-connector-j-8.3.0.jar;bin" src/ConexionDB.java
```

Resultado:
```text
Compilación completada sin errores.
```

#### Ejecución

```powershell
java -cp "lib/mysql-connector-j-8.3.0.jar;bin" ConexionDB
```

Resultado:
```text
¡Conexión a la base de datos establecida con éxito!
Conexión cerrada correctamente.
```

### Operaciones CRUD Básicas (Clase OperacionesDB)

#### Compilación

```powershell
javac -d bin -cp "lib/mysql-connector-j-8.3.0.jar;bin" src/ConexionDB.java src/OperacionesDB.java
```

Resultado:
```text
Compilación completada sin errores.
```

#### Ejecución

```powershell
java -cp "lib/mysql-connector-j-8.3.0.jar;bin" OperacionesDB
```

Resultado:
```text
¡Conexión a la base de datos establecida con éxito!
Tabla 'empleados' verificada/creada correctamente.
¡Conexión a la base de datos establecida con éxito!
Empleado 'Ana García' insertado correctamente.
¡Conexión a la base de datos establecida con éxito!
El departamento del empleado con ID 1 fue actualizado a: Finanzas
¡Conexión a la base de datos establecida con éxito!
El empleado con ID 1 ha sido borrado exitosamente.
```
