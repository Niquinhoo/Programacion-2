public class Incrementador implements Runnable {

    private final OperadorContador contador;
    private final int repeticiones;

    public Incrementador(OperadorContador contador, int repeticiones) {
        this.contador = contador;
        this.repeticiones = repeticiones;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeticiones; i++) {
            contador.incrementar();
        }
    }
}
