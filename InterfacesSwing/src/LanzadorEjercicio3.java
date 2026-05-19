
import javax.swing.SwingUtilities;

public final class LanzadorEjercicio3 {

    private LanzadorEjercicio3() {
    }

    public static void iniciar() {
        SwingUtilities.invokeLater(() -> {
            VentanaPreferencias ventana = new VentanaPreferencias();
            ventana.setVisible(true);
        });
    }
}