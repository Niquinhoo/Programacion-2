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

