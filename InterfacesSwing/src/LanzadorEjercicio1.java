import javax.swing.SwingUtilities;

public final class LanzadorEjercicio1 {

    private LanzadorEjercicio1() {
    }

    public static void iniciar() {
        SwingUtilities.invokeLater(() -> {
            VentanaFormularioSaludo ventana = new VentanaFormularioSaludo();
            ventana.setVisible(true);
        });
    }
}
