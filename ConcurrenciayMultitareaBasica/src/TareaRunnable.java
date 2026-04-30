public class TareaRunnable implements Runnable {

    private final String etiqueta;

    public TareaRunnable(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + etiqueta + "] paso " + i + " - " + Thread.currentThread().getName());
        }
    }
}
