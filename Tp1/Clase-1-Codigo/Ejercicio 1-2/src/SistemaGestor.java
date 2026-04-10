/**
 * La clase SistemaGestor es responsable de coordinar las operaciones básicas
 * del sistema de gestión, permitiendo la configuración inicial y el seguimiento
 * de procesos internos.
 */
public class SistemaGestor {

    // Ejercicio 1.2: Constante de clase
    public static final int MAX_CONEXIONES = 10;

    /*
     * Explicación de modificadores (Ejercicio 1.2):
     * - 'static': El campo pertenece a la clase SistemaGestor y no a una instancia específica (objeto). 
     *             Se comparte entre todos los objetos de la clase y se puede acceder sin instanciarla.
     * - 'final': Indica que el valor es inmutable. Una vez asignado, no puede ser cambiado o reasignado,
     *            convirtiéndolo en una constante técnica.
     */

    /**
     * Imprime el límite máximo de conexiones permitido por el sistema.
     */
    public void imprimirLimiteConexiones() {
        System.out.println("Límite máximo de conexiones: " + MAX_CONEXIONES);
    }

    public static void main(String[] args) {
        /*
         * Bloque de configuración inicial:
         * En este segmento se definen los parámetros de arranque del sistema,
         * asegurando que todos los servicios necesarios estén listos antes de la ejecución.
         */
        System.out.println("Iniciando Sistema Gestor...");

        int contadorIntentos = 0; // Almacena la cantidad de veces que el sistema ha intentado conectarse
        boolean estaActivo = true; // Indica si el sistema se encuentra en estado operativo

        if (estaActivo) {
            System.out.println("Sistema activo. Intentos realizados: " + contadorIntentos);
        }

        // Demostración Ejercicio 1.2
        SistemaGestor gestor = new SistemaGestor();
        gestor.imprimirLimiteConexiones();
    }
}
