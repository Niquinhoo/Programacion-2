
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaMenuEjercicios extends JFrame {

    private final PanelMenuEjercicios panelMenu;
    private final ControladorMenuEjercicios controlador;

    public VentanaMenuEjercicios() {
        panelMenu = new PanelMenuEjercicios();
        controlador = new ControladorMenuEjercicios(panelMenu);

        configurarVentana();
        agregarContenido();
        registrarEventos();
    }

    private void configurarVentana() {
        setTitle("Menu de ejercicios Swing");
        setSize(460, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void agregarContenido() {
        add(panelMenu, BorderLayout.CENTER);
    }

    private void registrarEventos() {
        controlador.registrarEventos();
    }
}