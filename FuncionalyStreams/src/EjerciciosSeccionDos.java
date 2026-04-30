public class EjerciciosSeccionDos {
    private final ImpresorConsola impresor;

    public EjerciciosSeccionDos(ImpresorConsola impresor) {
        this.impresor = impresor;
    }

    public void ejecutar() {
        ejercicio21();
        ejercicio22();
    }

    private void ejercicio21() {
        impresor.imprimirTitulo("Ejercicio 2.1 - Creacion y extraccion");

        long total = ConjuntosDatos.numerosSeccionDos()
                .distinct()
                .skip(1)
                .limit(3)
                .count();

        impresor.imprimirLinea("Cantidad total luego de distinct, skip y limit: ", total);
    }

    private void ejercicio22() {
        impresor.imprimirTitulo("Ejercicio 2.2 - Filtrado y procesamiento intermedio");

        long total = ConjuntosDatos.numerosFiltrado()
                .filter(numero -> numero > 3)
                .peek(numero -> impresor.imprimirLinea("peek -> ", numero))
                .count();

        impresor.imprimirLinea("Cantidad de numeros mayores a 3: ", total);
    }
}
