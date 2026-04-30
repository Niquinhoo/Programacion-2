import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EjerciciosSeccionTres {
    private final ImpresorConsola impresor;

    public EjerciciosSeccionTres(ImpresorConsola impresor) {
        this.impresor = impresor;
    }

    public void ejecutar() {
        ejercicio31();
        ejercicio32();
        ejercicio33();
        ejercicio34();
    }

    private void ejercicio31() {
        List<String> nombresMayuscula = ConjuntosDatos.nombres()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        impresor.imprimirTitulo("Ejercicio 3.1 - Transformacion con map");
        impresor.imprimirLinea("Nombres en mayusculas: ", nombresMayuscula);
    }

    private void ejercicio32() {
        int sumaLetras = ConjuntosDatos.nombres()
                .mapToInt(String::length)
                .sum();

        impresor.imprimirTitulo("Ejercicio 3.2 - mapToInt");
        impresor.imprimirLinea("Suma total de letras: ", sumaLetras);
    }

    private void ejercicio33() {
        long total = ConjuntosDatos.nombresAnidados().stream()
                .flatMap(List::stream)
                .filter(nombre -> nombre.length() > 4)
                .count();

        impresor.imprimirTitulo("Ejercicio 3.3 - Aplanamiento con flatMap");
        impresor.imprimirLinea("Cantidad de nombres con mas de 4 caracteres: ", total);
    }

    private void ejercicio34() {
        List<String> ordenNatural = ConjuntosDatos.palabrasOrdenables().stream()
                .sorted()
                .collect(Collectors.toList());

        List<String> ordenPorLongitud = ConjuntosDatos.palabrasOrdenables().stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        impresor.imprimirTitulo("Ejercicio 3.4 - Ordenacion");
        impresor.imprimirLista("Orden natural:", ordenNatural);
        impresor.imprimirLista("Orden por longitud:", ordenPorLongitud);
    }
}
