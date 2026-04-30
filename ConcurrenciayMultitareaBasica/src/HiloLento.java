public class HiloLento extends Thread {

    protected final int pasos;
    protected final int demoraMs;

    public HiloLento(String nombre, int pasos, int demoraMs) {
        super(nombre);
        this.pasos = pasos;
        this.demoraMs = demoraMs;
    }

    @Override
    public void run() {
        for (int i = 1; i <= pasos; i++) {
            System.out.println("[" + getName() + "] ejecutando paso " + i + "/" + pasos);
            dormir();
        }
    }

    protected void dormir() {
        try {
            Thread.sleep(demoraMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
