
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaFormularioSaludo extends JFrame {

    public VentanaFormularioSaludo() {
        configurarVentana();
        agregarContenido();
    }

    private void configurarVentana() {
        setTitle("Ejercicio 1 - Formulario interactivo");
        setSize(420, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void agregarContenido() {
        add(new PanelFormularioSaludo(), BorderLayout.CENTER);
    }
}