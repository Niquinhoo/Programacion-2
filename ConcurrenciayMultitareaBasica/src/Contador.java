public class Contador implements OperadorContador {

    private int valor = 0;

    @Override
    public void incrementar() {
        int temporal = valor;
        Thread.yield();
        valor = temporal + 1;
    }

    @Override
    public int obtenerValor() {
        return valor;
    }
}
