/**
 * Clase que modela un reporte simple para demostrar metodos con y sin retorno,
 * y el uso de argumentos variables.
 */
public class Reporte {
    public void generarEncabezado() {
        System.out.println("=== ENCABEZADO DEL REPORTE ===");
    }

    public String obtenerCuerpo() {
        return "Cuerpo del reporte generado correctamente.";
    }

    public void mostrarSecciones(String... secciones) {
        if (secciones.length == 0) {
            System.out.println("No se recibieron secciones para mostrar.");
            return;
        }

        System.out.println("Secciones del reporte:");
        for (String seccion : secciones) {
            System.out.println("- " + seccion);
        }
    }
}
