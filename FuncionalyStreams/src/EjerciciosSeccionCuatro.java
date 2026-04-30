import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EjerciciosSeccionCuatro {
    private final ImpresorConsola impresor;

    public EjerciciosSeccionCuatro(ImpresorConsola impresor) {
        this.impresor = impresor;
    }

    public void ejecutar() {
        ejercicio41();
        ejercicio42();
        ejercicio43();
        ejercicio44();
    }

    private void ejercicio41() {
        boolean algunMayorQueCinco = ConjuntosDatos.numerosComparacion()
                .anyMatch(numero -> numero > 5);

        boolean todosMayoresQueUno = ConjuntosDatos.numerosComparacion()
                .allMatch(numero -> numero > 1);

        boolean ningunoMayorQueDiez = ConjuntosDatos.numerosComparacion()
                .noneMatch(numero -> numero > 10);

        impresor.imprimirTitulo("Ejercicio 4.1 - Comprobaciones logicas");
        impresor.imprimirLinea("anyMatch(n > 5): ", algunMayorQueCinco);
        impresor.imprimirLinea("allMatch(n > 1): ", todosMayoresQueUno);
        impresor.imprimirLinea("noneMatch(n > 10): ", ningunoMayorQueDiez);
    }

    private void ejercicio42() {
        Optional<Integer> primerPar = ConjuntosDatos.numerosOpcional()
                .filter(numero -> numero % 2 == 0)
                .findFirst();

        impresor.imprimirTitulo("Ejercicio 4.2 - Optional y findFirst");
        impresor.imprimirLinea("Se encontro un valor?: ", primerPar.isPresent());
        primerPar.ifPresent(valor -> impresor.imprimirLinea("Primer numero par encontrado: ", valor));
    }

    private void ejercicio43() {
        Optional<Integer> suma = ConjuntosDatos.numerosReduccion()
                .reduce(Integer::sum);

        impresor.imprimirTitulo("Ejercicio 4.3 - Reduccion");
        impresor.imprimirLinea("Resultado de reduce: ", suma.get());
    }

    private void ejercicio44() {
        List<Integer> numerosUnicos = ConjuntosDatos.numerosDuplicados()
                .distinct()
                .collect(Collectors.toList());

        impresor.imprimirTitulo("Ejercicio 4.4 - Reduccion a coleccion");
        impresor.imprimirLinea("Lista sin duplicados: ", numerosUnicos);
        impresor.imprimirLinea(
                "Mapa DNI -> Nombre: ",
                ConjuntosDatos.personas().stream().collect(Collectors.toMap(Persona::getDni, Persona::getNombre))
        );
    }
}
