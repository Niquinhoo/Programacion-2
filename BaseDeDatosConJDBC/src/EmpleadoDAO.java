import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmpleadoDAO implements OperacionesDAO {
    public EmpleadoDAO() {
        inicializarBaseDeDatos();
    }

    @Override
    public void inicializarBaseDeDatos() {
        try (Connection conn = ConexionDB.conectar()) {
            if (conn == null) {
                System.out.println("No fue posible inicializar el esquema porque no hay conexion disponible.");
                return;
            }

            crearTablaDepartamentos(conn);
            insertarDepartamentosBase(conn);
            crearOMigrarTablaEmpleados(conn);
            System.out.println("Esquema JDBC verificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    @Override
    public boolean insertar(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre, departamento, departamento_id, foto_ruta) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar()) {
            if (conn == null) {
                return false;
            }

            int departamentoId = resolverDepartamento(conn, empleado);

            try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, empleado.getNombre());
                pstmt.setString(2, empleado.getDepartamento());
                pstmt.setInt(3, departamentoId);
                if (empleado.getFotoRuta() == null || empleado.getFotoRuta().isBlank()) {
                    pstmt.setNull(4, Types.VARCHAR);
                } else {
                    pstmt.setString(4, empleado.getFotoRuta());
                }

                int filas = pstmt.executeUpdate();
                if (filas > 0) {
                    try (ResultSet keys = pstmt.getGeneratedKeys()) {
                        if (keys.next()) {
                            empleado.setId(keys.getInt(1));
                        }
                    }
                    empleado.setDepartamentoId(departamentoId);
                    System.out.println("Empleado insertado con ID " + empleado.getId() + ".");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean actualizar(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, departamento = ?, departamento_id = ?, foto_ruta = ? WHERE id = ?";

        try (Connection conn = ConexionDB.conectar()) {
            if (conn == null) {
                return false;
            }

            int departamentoId = resolverDepartamento(conn, empleado);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, empleado.getNombre());
                pstmt.setString(2, empleado.getDepartamento());
                pstmt.setInt(3, departamentoId);
                if (empleado.getFotoRuta() == null || empleado.getFotoRuta().isBlank()) {
                    pstmt.setNull(4, Types.VARCHAR);
                } else {
                    pstmt.setString(4, empleado.getFotoRuta());
                }
                pstmt.setInt(5, empleado.getId());

                int filas = pstmt.executeUpdate();
                if (filas > 0) {
                    empleado.setDepartamentoId(departamentoId);
                    System.out.println("Empleado con ID " + empleado.getId() + " actualizado correctamente.");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";

        try (Connection conn = ConexionDB.conectar()) {
            if (conn == null) {
                return false;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int filas = pstmt.executeUpdate();
                if (filas > 0) {
                    System.out.println("Empleado con ID " + id + " eliminado correctamente.");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
        }

        System.out.println("No se encontro el empleado con ID " + id + " para eliminar.");
        return false;
    }

    @Override
    public ArrayList<Empleado> consultarTodos() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        String sql =
            "SELECT e.id, e.nombre, e.departamento_id, " +
            "COALESCE(d.nombre_depto, e.departamento, 'Sin asignar') AS departamento, e.foto_ruta " +
            "FROM empleados e " +
            "LEFT JOIN departamentos d ON e.departamento_id = d.id_depto " +
            "ORDER BY e.id";

        try (Connection conn = ConexionDB.conectar()) {
            if (conn == null) {
                return empleados;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Empleado empleado = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("departamento_id"),
                        rs.getString("departamento"),
                        rs.getString("foto_ruta")
                    );
                    empleados.add(empleado);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar empleados: " + e.getMessage());
        }

        return empleados;
    }

    @Override
    public Map<Integer, String> consultarDepartamentos() {
        Map<Integer, String> departamentos = new LinkedHashMap<>();
        String sql = "SELECT id_depto, nombre_depto FROM departamentos ORDER BY nombre_depto";

        try (Connection conn = ConexionDB.conectar()) {
            if (conn == null) {
                return departamentos;
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    departamentos.put(rs.getInt("id_depto"), rs.getString("nombre_depto"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar departamentos: " + e.getMessage());
        }

        return departamentos;
    }

    private void crearTablaDepartamentos(Connection conn) throws SQLException {
        String sql =
            "CREATE TABLE IF NOT EXISTS departamentos (" +
            "id_depto INT AUTO_INCREMENT PRIMARY KEY, " +
            "nombre_depto VARCHAR(100) NOT NULL UNIQUE" +
            ")";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }

    private void insertarDepartamentosBase(Connection conn) throws SQLException {
        String[] nombres = {"Finanzas", "Operaciones", "Recursos Humanos", "Sistemas", "Ventas"};
        String sql = "INSERT INTO departamentos (nombre_depto) VALUES (?)";

        for (String nombre : nombres) {
            if (buscarDepartamentoIdPorNombre(conn, nombre) == 0) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nombre);
                    pstmt.executeUpdate();
                }
            }
        }
    }

    private void crearOMigrarTablaEmpleados(Connection conn) throws SQLException {
        if (!existeTabla(conn, "empleados")) {
            String sql =
                "CREATE TABLE empleados (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "departamento VARCHAR(100), " +
                "departamento_id INT NOT NULL, " +
                "foto_ruta VARCHAR(255), " +
                "CONSTRAINT fk_empleados_departamentos FOREIGN KEY (departamento_id) " +
                "REFERENCES departamentos(id_depto)" +
                ")";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.executeUpdate();
            }
            return;
        }

        asegurarColumna(conn, "empleados", "departamento", "VARCHAR(100)");
        asegurarColumna(conn, "empleados", "departamento_id", "INT");
        asegurarColumna(conn, "empleados", "foto_ruta", "VARCHAR(255)");
        migrarDepartamentosExistentes(conn);
        asegurarForeignKey(conn);
    }

    private void migrarDepartamentosExistentes(Connection conn) throws SQLException {
        int sinAsignarId = buscarOCrearDepartamento(conn, "Sin asignar");
        String selectSql = "SELECT id, departamento, departamento_id FROM empleados";

        try (PreparedStatement pstmt = conn.prepareStatement(selectSql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String departamentoTexto = rs.getString("departamento");
                int departamentoId = rs.getInt("departamento_id");

                if (departamentoTexto != null && !departamentoTexto.isBlank()) {
                    departamentoId = buscarOCrearDepartamento(conn, departamentoTexto.trim());
                } else if (departamentoId <= 0) {
                    departamentoId = sinAsignarId;
                    departamentoTexto = "Sin asignar";
                } else if (departamentoTexto == null || departamentoTexto.isBlank()) {
                    departamentoTexto = buscarNombreDepartamento(conn, departamentoId);
                }

                actualizarRelacionDepartamento(conn, id, departamentoId, departamentoTexto);
            }
        }
    }

    private void actualizarRelacionDepartamento(Connection conn, int id, int departamentoId, String departamentoTexto)
        throws SQLException {
        String sql = "UPDATE empleados SET departamento = ?, departamento_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, departamentoTexto);
            pstmt.setInt(2, departamentoId);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        }
    }

    private void asegurarForeignKey(Connection conn) throws SQLException {
        if (tieneForeignKey(conn, "empleados", "fk_empleados_departamentos")) {
            return;
        }

        try (PreparedStatement pstmt = conn.prepareStatement(
            "ALTER TABLE empleados ADD CONSTRAINT fk_empleados_departamentos " +
            "FOREIGN KEY (departamento_id) REFERENCES departamentos(id_depto)")) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Aviso: no se pudo crear la FK de empleados.departamento_id: " + e.getMessage());
        }
    }

    private int resolverDepartamento(Connection conn, Empleado empleado) throws SQLException {
        if (empleado.getDepartamentoId() > 0) {
            String nombre = buscarNombreDepartamento(conn, empleado.getDepartamentoId());
            if (nombre != null) {
                empleado.setDepartamento(nombre);
                return empleado.getDepartamentoId();
            }
        }

        if (empleado.getDepartamento() == null || empleado.getDepartamento().isBlank()) {
            empleado.setDepartamento("Sin asignar");
        }

        return buscarOCrearDepartamento(conn, empleado.getDepartamento().trim());
    }

    private int buscarOCrearDepartamento(Connection conn, String nombre) throws SQLException {
        int departamentoId = buscarDepartamentoIdPorNombre(conn, nombre);
        if (departamentoId > 0) {
            return departamentoId;
        }

        String sql = "INSERT INTO departamentos (nombre_depto) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }

        return buscarDepartamentoIdPorNombre(conn, nombre);
    }

    private int buscarDepartamentoIdPorNombre(Connection conn, String nombre) throws SQLException {
        String sql = "SELECT id_depto FROM departamentos WHERE nombre_depto = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_depto");
                }
            }
        }
        return 0;
    }

    private String buscarNombreDepartamento(Connection conn, int departamentoId) throws SQLException {
        String sql = "SELECT nombre_depto FROM departamentos WHERE id_depto = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departamentoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre_depto");
                }
            }
        }
        return null;
    }

    private boolean existeTabla(Connection conn, String tabla) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet rs = metaData.getTables(conn.getCatalog(), null, tabla, null)) {
            return rs.next();
        }
    }

    private void asegurarColumna(Connection conn, String tabla, String columna, String definicion) throws SQLException {
        if (existeColumna(conn, tabla, columna)) {
            return;
        }

        try (PreparedStatement pstmt = conn.prepareStatement(
            "ALTER TABLE " + tabla + " ADD COLUMN " + columna + " " + definicion)) {
            pstmt.executeUpdate();
        }
    }

    private boolean existeColumna(Connection conn, String tabla, String columna) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet rs = metaData.getColumns(conn.getCatalog(), null, tabla, columna)) {
            return rs.next();
        }
    }

    private boolean tieneForeignKey(Connection conn, String tabla, String nombreFk) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet rs = metaData.getImportedKeys(conn.getCatalog(), null, tabla)) {
            while (rs.next()) {
                if (nombreFk.equalsIgnoreCase(rs.getString("FK_NAME"))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        EmpleadoDAO dao = new EmpleadoDAO();
        Empleado empleado = new Empleado("Empleado Demo JDBC", "Sistemas");
        empleado.setFotoRuta("C:\\demo\\foto-empleado.png");

        if (dao.insertar(empleado)) {
            empleado.setNombre("Empleado Demo JDBC Actualizado");
            empleado.setDepartamento("Finanzas");
            dao.actualizar(empleado);
            System.out.println("Cantidad de empleados consultados: " + dao.consultarTodos().size());
            dao.eliminar(empleado.getId());
        }
    }
}
