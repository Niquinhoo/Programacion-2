
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {

    private final PanelLogin panelLogin;
    private final VentanaLogin ventanaLogin;
    private final char[] contrasenaSecreta;

    public ControladorLogin(PanelLogin panelLogin, VentanaLogin ventanaLogin, char[] contrasenaSecreta) {
        this.panelLogin = panelLogin;
        this.ventanaLogin = ventanaLogin;
        this.contrasenaSecreta = Arrays.copyOf(contrasenaSecreta, contrasenaSecreta.length);
    }

    public void registrarEventos() {
        panelLogin.getBotonAcceder().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        char[] contrasenaIngresada = panelLogin.getCampoContrasena().getPassword();
        String usuarioIngresado = panelLogin.getCampoUsuario().getText().trim();
        boolean accesoCorrecto = Arrays.equals(contrasenaSecreta, contrasenaIngresada);

        if (accesoCorrecto) {
            JOptionPane.showMessageDialog(
                    ventanaLogin,
                    "Acceso correcto. Bienvenido, " + usuarioIngresado + ".",
                    "Login correcto",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    ventanaLogin,
                    "La contraseÃ±a ingresada es incorrecta.",
                    "Error de autenticaciÃ³n",
                    JOptionPane.ERROR_MESSAGE);
        }

        Arrays.fill(contrasenaIngresada, '\0');
        panelLogin.getCampoContrasena().setText("");
    }
}