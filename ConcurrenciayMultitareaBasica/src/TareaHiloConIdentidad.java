public class TareaHiloConIdentidad extends Thread {

    public TareaHiloConIdentidad(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        Thread actual = Thread.currentThread();
        for (int i = 1; i <= 3; i++) {
            System.out.println("[" + actual.getName() + "] identidad=" + actual + " prioridad=" + actual.getPriority() + " paso=" + i);
        }
    }
}
