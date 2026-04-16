import java.util.Comparator;

/**
 * Comparador que ordena empleados alfabeticamente por nombre, ignorando
 * diferencias entre mayusculas y minusculas.
 */
public class EmpleadoPorNombreComparator implements Comparator<Empleado> {
    /**
     * Compara dos empleados segun sus nombres.
     *
     * @param primero primer empleado a comparar.
     * @param segundo segundo empleado a comparar.
     * @return un valor negativo, cero o positivo si el primer nombre es menor,
     *         igual o mayor que el segundo.
     */
    @Override
    public int compare(Empleado primero, Empleado segundo) {
        return primero.getNombre().compareToIgnoreCase(segundo.getNombre());
    }
}
