
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaLogin extends JFrame {

    private final char[] contrasenaSecreta = {'1', '2', '3', '4'};
    private final PanelLogin panelLogin;
    private final ControladorLogin controlador;

    public VentanaLogin() {
        panelLogin = new PanelLogin();
        controlador = new ControladorLogin(panelLogin, this, contrasenaSecreta);

        configurarVentana();
        agregarContenido();
        registrarEventos();
    }

    private void configurarVentana() {
        setTitle("Ejercicio 4 - Pantalla de autenticación");
        setSize(420, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void agregarContenido() {
        add(panelLogin, BorderLayout.CENTER);
    }

    private void registrarEventos() {
        controlador.registrarEventos();
    }
}