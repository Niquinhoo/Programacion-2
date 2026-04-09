/**
 * La clase Reporte permite la generación de documentos estructurados
 * utilizando métodos con y sin retorno, además de argumentos variables.
 */
public class Reporte {

    /**
     * Ejercicio 2.1: Método sin retorno (void).
     * Simplemente imprime un encabezado decorativo en la consola.
     */
    public void generarEncabezado() {
        System.out.println("========================================");
        System.out.println("          REPORTE DE SISTEMA");
        System.out.println("========================================");
    }

    /**
     * Ejercicio 2.1: Método con retorno (String).
     * @return Una cadena de texto con el cuerpo predefinido del reporte.
     */
    public String obtenerCuerpo() {
        return "Este es el contenido principal del reporte generado por el sistema.";
    }

    /**
     * Ejercicio 2.3: Método con argumentos variables (var-args).
     * Permite pasar un número dinámico de secciones para el reporte.
     * @param secciones Lista variable de nombres de secciones.
     */
    public void imprimirSecciones(String... secciones) {
        if (secciones.length == 0) {
            System.out.println("[Aviso] No se han proporcionado secciones adicionales.");
        } else {
            System.out.println("Secciones del reporte:");
            // Bucle for-each para recorrer las secciones
            for (String seccion : secciones) {
                System.out.println(" - " + seccion);
            }
        }
    }

    // Demostración de los ejercicios 2.1 y 2.3
    public static void main(String[] args) {
        Reporte miReporte = new Reporte();

        // Ejercicio 2.1
        miReporte.generarEncabezado();
        String cuerpo = miReporte.obtenerCuerpo();
        System.out.println(cuerpo);

        // Ejercicio 2.3
        System.out.println("\n--- Demostración de Argumentos Variables (var-args) ---");
        System.out.println("Llamada con 3 argumentos:");
        miReporte.imprimirSecciones("Introducción", "Desarrollo", "Conclusión");
        
        System.out.println("\nLlamada sin argumentos:");
        miReporte.imprimirSecciones();
    }
}
