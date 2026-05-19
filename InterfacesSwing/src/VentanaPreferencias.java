
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaPreferencias extends JFrame {

    private final PanelPreferencias panelPreferencias;
    private final DialogoPreferencias dialogoPreferencias;
    private final ControladorPreferencias controlador;

    public VentanaPreferencias() {
        panelPreferencias = new PanelPreferencias();
        dialogoPreferencias = new DialogoPreferencias(this);
        controlador = new ControladorPreferencias(panelPreferencias, dialogoPreferencias);

        configurarVentana();
        agregarContenido();
        registrarEventos();
    }

    private void configurarVentana() {
        setTitle("Ejercicio 3 - Panel de preferencias");
        setSize(500, 180);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void agregarContenido() {
        add(panelPreferencias, BorderLayout.CENTER);
    }

    private void registrarEventos() {
        controlador.registrarEventos();
    }
}