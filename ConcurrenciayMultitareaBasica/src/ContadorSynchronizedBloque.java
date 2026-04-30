public class ContadorSynchronizedBloque implements OperadorContador {

    private final Object monitor = new Object();
    private int valor = 0;

    @Override
    public void incrementar() {
        synchronized (monitor) {
            int temporal = valor;
            Thread.yield();
            valor = temporal + 1;
        }
    }

    @Override
    public int obtenerValor() {
        synchronized (monitor) {
            return valor;
        }
    }
}
