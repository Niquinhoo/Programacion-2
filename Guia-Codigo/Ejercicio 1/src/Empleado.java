/**
 * Representa un empleado con nombre y edad.
 *
 * <p>La clase define su orden natural a traves de la interfaz
 * {@link Comparable}, priorizando la edad del empleado.
 */
public class Empleado implements Comparable<Empleado> {
    private final String nombre;
    private final int edad;

    /**
     * Crea un empleado con los datos recibidos.
     *
     * @param nombre nombre del empleado.
     * @param edad edad del empleado.
     */
    public Empleado(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    /**
     * Devuelve el nombre del empleado.
     *
     * @return nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la edad del empleado.
     *
     * @return edad del empleado.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Compara este empleado con otro segun la edad para definir el orden
     * natural.
     *
     * @param otro empleado contra el cual se realiza la comparacion.
     * @return un valor negativo, cero o positivo si este empleado es menor,
     *         igual o mayor que el otro en edad.
     */
    @Override
    public int compareTo(Empleado otro) {
        return Integer.compare(this.edad, otro.edad);
    }

    /**
     * Devuelve una representacion textual del empleado.
     *
     * @return cadena con el nombre y la edad del empleado.
     */
    @Override
    public String toString() {
        return "Empleado{nombre='" + nombre + "', edad=" + edad + "}";
    }
}
