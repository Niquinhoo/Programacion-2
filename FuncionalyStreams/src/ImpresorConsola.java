import java.util.List;

public class ImpresorConsola {

    public void imprimirTitulo(String titulo) {
        System.out.println();
        System.out.println("=== " + titulo + " ===");
    }

    public void imprimirLinea(String etiqueta, Object valor) {
        System.out.println(etiqueta + valor);
    }

    public void imprimirLista(String encabezado, List<String> valores) {
        System.out.println(encabezado);
        valores.forEach(valor -> System.out.println(" - " + valor));
    }
}
