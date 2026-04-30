/**
 * Clase que representa un sistema gestor basico para demostrar
 * comentarios, constantes de clase y uso de miembros static final.
 */
public class SistemaGestor {
    /*
     * Bloque de configuracion que define el limite maximo de conexiones
     * aceptadas por el sistema.
     */
    public static final int MAX_CONEXIONES = 10;

    public SistemaGestor() {
        // Constructor sin logica adicional: la clase solo expone comportamiento utilitario.
    }

    public void imprimirLimiteConexiones() {
        // La constante pertenece a la clase porque el limite es compartido por todas las instancias.
        // No puede reasignarse porque el valor debe permanecer estable durante la ejecucion.
        System.out.println("Limite maximo de conexiones: " + MAX_CONEXIONES);
    }
}
