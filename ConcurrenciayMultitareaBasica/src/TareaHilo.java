public class TareaHilo extends Thread {

    public TareaHilo(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + getName() + "] paso " + i + " - " + Thread.currentThread().getName());
        }
    }
}
