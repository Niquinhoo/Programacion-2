import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConexionDB {
    private static final Map<String, String> env = new HashMap<>();

    static {
        // Cargar variables desde el archivo .env si existe
        try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                int eqIdx = line.indexOf('=');
                if (eqIdx > 0) {
                    String key = line.substring(0, eqIdx).trim();
                    String value = line.substring(eqIdx + 1).trim();
                    // Quitar comillas simples o dobles alrededor del valor si existen
                    if ((value.startsWith("'") && value.endsWith("'")) || 
                        (value.startsWith("\"") && value.endsWith("\""))) {
                        value = value.substring(1, value.length() - 1);
                    }
                    env.put(key, value);
                }
            }
        } catch (IOException e) {
            // Si no existe .env o falla la lectura, se usarán las variables de entorno del sistema
            System.out.println("Nota: No se pudo cargar el archivo .env local (" + e.getMessage() + "). Se intentará usar variables del sistema.");
        }
    }

    private static String getEnv(String key, String defaultValue) {
        String val = env.get(key);
        if (val == null) {
            val = System.getenv(key);
        }
        return val != null ? val : defaultValue;
    }

    public static Connection conectar() {
        String host = getEnv("DB_HOST", "localhost");
        String port = getEnv("DB_PORT", "3306");
        String database = getEnv("DB_DATABASE", "empresa_db");
        String user = getEnv("DB_USERNAME", "root");
        String password = getEnv("DB_PASSWORD", "");

        // URL de conexión JDBC. Agregamos parámetros de SSL necesarios para bases de datos en la nube (como TiDB)
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=true&trustServerCertificate=true&serverTimezone=UTC", host, port, database);

        Connection conexion = null;
        try {
            // Registrar explícitamente el driver de MySQL (opcional en versiones modernas de JDBC, pero buena práctica)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Advertencia: No se encontró la clase del Driver de MySQL. Asegúrate de tener el JAR en el classpath.");
            }

            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("¡Conexión a la base de datos establecida con éxito!");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos:");
            System.out.println("URL intentada: " + url);
            System.out.println("Usuario: " + user);
            System.out.println("Mensaje de error: " + e.getMessage());
        }
        return conexion;
    }

    // Método main para probar la conexión individualmente
    public static void main(String[] args) {
        Connection conn = conectar();
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
