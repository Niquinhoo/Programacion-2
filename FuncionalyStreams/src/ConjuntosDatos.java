import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class ConjuntosDatos {

    private ConjuntosDatos() {
    }

    public static Stream<Integer> numerosSeccionDos() {
        return Stream.of(2, 5, 3, 3, 6, 2, 4);
    }

    public static Stream<Integer> numerosFiltrado() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7);
    }

    public static Stream<String> nombres() {
        return Stream.of("Juan", "Maria", "Ana");
    }

    public static List<List<String>> nombresAnidados() {
        return Arrays.asList(
                Arrays.asList("Juan", "Maria"),
                Arrays.asList("Ana", "Luciano"),
                Arrays.asList("Pedro", "Sol")
        );
    }

    public static List<String> palabrasOrdenables() {
        return Arrays.asList("zorro", "casa", "arbol", "luz", "computadora");
    }

    public static Stream<Integer> numerosComparacion() {
        return Stream.of(2, 5, 7, 3, 6, 2, 3);
    }

    public static Stream<Integer> numerosOpcional() {
        return Stream.of(1, 3, 5, 8, 10);
    }

    public static Stream<Integer> numerosReduccion() {
        return Stream.of(4, 6, 8, 2);
    }

    public static Stream<Integer> numerosDuplicados() {
        return Stream.of(4, 2, 4, 7, 2, 9, 7, 1);
    }

    public static List<String> textosParaOrdenarPorLongitud() {
        return Arrays.asList("sol", "programacion", "java", "lambda", "stream");
    }

    public static List<Persona> personas() {
        return Arrays.asList(
                new Persona("30111222", "Lucia"),
                new Persona("28999888", "Martin"),
                new Persona("33444555", "Carla")
        );
    }
}
