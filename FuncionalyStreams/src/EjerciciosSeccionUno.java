import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class EjerciciosSeccionUno {
    private final ImpresorConsola impresor;

    public EjerciciosSeccionUno(ImpresorConsola impresor) {
        this.impresor = impresor;
    }

    public void ejecutar() {
        ejercicio11();
        ejercicio12();
        ejercicio13();
    }

    private void ejercicio11() {
        Predicate<Integer> esPar = numero -> numero % 2 == 0;
        Function<String, Integer> longitudTexto = String::length;
        Consumer<Integer> consumirNumero = numero -> impresor.imprimirLinea("Consumer recibio: ", numero);
        Supplier<Double> aleatorio = Math::random;

        impresor.imprimirTitulo("Ejercicio 1.1 - Interfaces funcionales base");
        impresor.imprimirLinea("Predicate test(8): ", esPar.test(8));
        impresor.imprimirLinea("Function apply(\"Streams\"): ", longitudTexto.apply("Streams"));
        consumirNumero.accept(42);
        impresor.imprimirLinea("Supplier get(): ", aleatorio.get());
    }

    private void ejercicio12() {
        List<String> textos = ConjuntosDatos.textosParaOrdenarPorLongitud();
        textos.sort((primero, segundo) -> primero.length() - segundo.length());

        impresor.imprimirTitulo("Ejercicio 1.2 - Ordenacion con Comparator");
        impresor.imprimirLinea("Textos ordenados por longitud: ", textos);
    }

    private void ejercicio13() {
        IntPredicate esPar = numero -> numero % 2 == 0;
        ToIntFunction<String> longitudTexto = String::length;

        impresor.imprimirTitulo("Ejercicio 1.3 - Variantes primitivas");
        impresor.imprimirLinea("IntPredicate test(11): ", esPar.test(11));
        impresor.imprimirLinea("ToIntFunction applyAsInt(\"Funcional\"): ", longitudTexto.applyAsInt("Funcional"));
    }
}
