
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ControladorEditorTexto implements ActionListener {

    private final JTextArea areaTexto;
    private final JFrame ventanaPrincipal;
    private final BarraMenuEditorTexto barraMenu;

    public ControladorEditorTexto(JTextArea areaTexto, JFrame ventanaPrincipal, BarraMenuEditorTexto barraMenu) {
        this.areaTexto = areaTexto;
        this.ventanaPrincipal = ventanaPrincipal;
        this.barraMenu = barraMenu;
    }

    public void registrarEventos() {
        barraMenu.getItemLimpiarTexto().addActionListener(this);
        barraMenu.getItemSalir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object origen = evento.getSource();

        if (origen == barraMenu.getItemLimpiarTexto()) {
            areaTexto.setText("");
            return;
        }

        if (origen == barraMenu.getItemSalir()) {
            ventanaPrincipal.dispose();
        }
    }
}