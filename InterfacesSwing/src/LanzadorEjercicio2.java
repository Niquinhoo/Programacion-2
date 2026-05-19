
import javax.swing.SwingUtilities;

public final class LanzadorEjercicio2 {

    private LanzadorEjercicio2() {
    }

    public static void iniciar() {
        SwingUtilities.invokeLater(() -> {
            VentanaEditorTexto ventana = new VentanaEditorTexto();
            ventana.setVisible(true);
        });
    }
}