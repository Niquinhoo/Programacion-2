
import javax.swing.SwingUtilities;

public final class LanzadorEjercicio4 {

    private LanzadorEjercicio4() {
    }

    public static void iniciar() {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin ventana = new VentanaLogin();
            ventana.setVisible(true);
        });
    }
}