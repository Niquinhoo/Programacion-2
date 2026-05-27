import java.util.ArrayList;
import java.util.Map;

public interface OperacionesDAO {
    void inicializarBaseDeDatos();
    boolean insertar(Empleado empleado);
    boolean actualizar(Empleado empleado);
    boolean eliminar(int id);
    ArrayList<Empleado> consultarTodos();
    Map<Integer, String> consultarDepartamentos();
}
