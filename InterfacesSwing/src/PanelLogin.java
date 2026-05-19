
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelLogin extends JPanel {

    private final JTextField campoUsuario;
    private final JPasswordField campoContrasena;
    private final JButton botonAcceder;

    public PanelLogin() {
        setLayout(new GridLayout(3, 1, 10, 10));

        campoUsuario = new JTextField(20);
        campoContrasena = new JPasswordField("", 20);
        campoContrasena.setEchoChar('*');
        botonAcceder = new JButton("Acceder");

        agregarSecciones();
    }

    private void agregarSecciones() {
        add(crearFilaUsuario());
        add(crearFilaContrasena());
        add(crearFilaBoton());
    }

    private JPanel crearFilaUsuario() {
        JPanel filaUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaUsuario.add(new JLabel("Usuario:"));
        filaUsuario.add(campoUsuario);
        return filaUsuario;
    }

    private JPanel crearFilaContrasena() {
        JPanel filaContrasena = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaContrasena.add(new JLabel("ContraseÃ±a:"));
        filaContrasena.add(campoContrasena);
        return filaContrasena;
    }

    private JPanel crearFilaBoton() {
        JPanel filaBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filaBoton.add(botonAcceder);
        return filaBoton;
    }

    public JTextField getCampoUsuario() {
        return campoUsuario;
    }

    public JPasswordField getCampoContrasena() {
        return campoContrasena;
    }

    public JButton getBotonAcceder() {
        return botonAcceder;
    }
}