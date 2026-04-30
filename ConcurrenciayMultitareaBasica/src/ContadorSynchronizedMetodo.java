public class ContadorSynchronizedMetodo implements OperadorContador {

    private int valor = 0;

    @Override
    public synchronized void incrementar() {
        int temporal = valor;
        Thread.yield();
        valor = temporal + 1;
    }

    @Override
    public int obtenerValor() {
        return valor;
    }
}
