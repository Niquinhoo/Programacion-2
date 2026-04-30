public class HiloLentoInterrumpible extends HiloLento {

    public HiloLentoInterrumpible(String nombre, int pasos, int demoraMs) {
        super(nombre, pasos, demoraMs);
    }

    @Override
    protected void dormir() {
        try {
            Thread.sleep(demoraMs);
        } catch (InterruptedException e) {
            System.out.println("[" + getName() + "] interrumpido mientras estaba en espera.");
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        for (int i = 1; i <= pasos; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("[" + getName() + "] finalizando por interrupcion.");
                break;
            }
            System.out.println("[" + getName() + "] ejecutando paso " + i + "/" + pasos);
            dormir();
        }
    }
}
