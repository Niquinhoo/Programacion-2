public class App {

    private static final int ITERACIONES_CONTADOR = 100_000;

    public static void main(String[] args) throws Exception {
        mostrarSeccion("1.1 - Extender Thread");
        ejecutarEjercicioThread();

        mostrarSeccion("1.2 - Implementar Runnable");
        ejecutarEjercicioRunnable();

        mostrarSeccion("2.1 - Condicion de carrera");
        ejecutarCondicionDeCarrera();

        mostrarSeccion("3.1 - Metodo synchronized");
        ejecutarSincronizacionPorMetodo();

        mostrarSeccion("3.2 - Bloque synchronized");
        ejecutarSincronizacionPorBloque();

        mostrarSeccion("4.1 - Identidad y prioridades");
        ejecutarIdentidadYPrioridades();

        mostrarSeccion("4.2 - Join e interrupcion");
        ejecutarJoinEInterrupcion();

        System.out.println("\nFin de la demostracion.");
    }

    private static void ejecutarEjercicioThread() throws InterruptedException {
        TareaHilo hilo1 = new TareaHilo("Hilo-Thread-1");
        TareaHilo hilo2 = new TareaHilo("Hilo-Thread-2");
        TareaHilo hilo3 = new TareaHilo("Hilo-Thread-3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();
    }

    private static void ejecutarEjercicioRunnable() throws InterruptedException {
        TareaRunnable tareaCompartida = new TareaRunnable("Tarea-Runnable");
        Thread hilo1 = new Thread(tareaCompartida, "Hilo-Runnable-1");
        Thread hilo2 = new Thread(tareaCompartida, "Hilo-Runnable-2");

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();
    }

    private static void ejecutarCondicionDeCarrera() throws InterruptedException {
        Contador contador = new Contador();
        Thread hilo1 = new Thread(new Incrementador(contador, ITERACIONES_CONTADOR), "Carrera-1");
        Thread hilo2 = new Thread(new Incrementador(contador, ITERACIONES_CONTADOR), "Carrera-2");

        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();

        int esperado = ITERACIONES_CONTADOR * 2;
        System.out.println("Resultado esperado: " + esperado);
        System.out.println("Resultado obtenido: " + contador.obtenerValor());
        System.out.println("Observacion: el valor puede ser incorrecto porque la operacion no es atomica.");
    }

    private static void ejecutarSincronizacionPorMetodo() throws InterruptedException {
        ContadorSynchronizedMetodo contador = new ContadorSynchronizedMetodo();
        Thread hilo1 = new Thread(new Incrementador(contador, ITERACIONES_CONTADOR), "SyncMetodo-1");
        Thread hilo2 = new Thread(new Incrementador(contador, ITERACIONES_CONTADOR), "SyncMetodo-2");

        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();

        int esperado = ITERACIONES_CONTADOR * 2;
        System.out.println("Resultado esperado: " + esperado);
        System.out.println("Resultado obtenido: " + contador.obtenerValor());
        System.out.println("Observacion: el metodo synchronized protege la seccion critica.");
    }

    private static void ejecutarSincronizacionPorBloque() throws InterruptedException {
        ContadorSynchronizedBloque contador = new ContadorSynchronizedBloque();
        Thread hilo1 = new Thread(new Incrementador(contador, ITERACIONES_CONTADOR), "SyncBloque-1");
        Thread hilo2 = new Thread(new Incrementador(contador, ITERACIONES_CONTADOR), "SyncBloque-2");

        hilo1.start();
        hilo2.start();
        hilo1.join();
        hilo2.join();

        int esperado = ITERACIONES_CONTADOR * 2;
        System.out.println("Resultado esperado: " + esperado);
        System.out.println("Resultado obtenido: " + contador.obtenerValor());
        System.out.println("Observacion: el bloque synchronized limita el alcance de la exclusion mutua.");
    }

    private static void ejecutarIdentidadYPrioridades() throws InterruptedException {
        TareaHiloConIdentidad hiloBajaPrioridad = new TareaHiloConIdentidad("Prioridad-Baja");
        TareaHiloConIdentidad hiloAltaPrioridad = new TareaHiloConIdentidad("Prioridad-Alta");

        hiloBajaPrioridad.setPriority(Thread.MIN_PRIORITY);
        hiloAltaPrioridad.setPriority(Thread.MAX_PRIORITY);

        hiloBajaPrioridad.start();
        hiloAltaPrioridad.start();

        hiloBajaPrioridad.join();
        hiloAltaPrioridad.join();
    }

    private static void ejecutarJoinEInterrupcion() throws InterruptedException {
        HiloLento hiloLento = new HiloLento("Hilo-Lento-Join", 5, 250);
        hiloLento.start();
        System.out.println("Esperando con join() a que termine el hilo lento...");
        hiloLento.join();
        System.out.println("join() finalizo correctamente.");

        HiloLentoInterrumpible hiloInterrumpible = new HiloLentoInterrumpible("Hilo-Interrumpible", 10, 250);
        hiloInterrumpible.start();
        Thread.sleep(600);
        System.out.println("Enviando interrupt() al hilo interrumpible...");
        hiloInterrumpible.interrupt();
        hiloInterrumpible.join();
    }

    private static void mostrarSeccion(String titulo) {
        System.out.println("\n==============================");
        System.out.println(titulo);
        System.out.println("==============================");
    }
}
