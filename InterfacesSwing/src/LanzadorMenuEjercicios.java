
import javax.swing.SwingUtilities;

public final class LanzadorMenuEjercicios {

    private LanzadorMenuEjercicios() {
    }

    public static void iniciar() {
        SwingUtilities.invokeLater(() -> {
            VentanaMenuEjercicios ventana = new VentanaMenuEjercicios();
            ventana.setVisible(true);
        });
    }
}