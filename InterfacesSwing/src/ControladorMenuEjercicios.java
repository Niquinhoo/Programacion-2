
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ControladorMenuEjercicios implements ActionListener {

    private final PanelMenuEjercicios panelMenu;

    public ControladorMenuEjercicios(PanelMenuEjercicios panelMenu) {
        this.panelMenu = panelMenu;
    }

    public void registrarEventos() {
        panelMenu.getBotonEjercicio1().addActionListener(this);
        panelMenu.getBotonEjercicio2().addActionListener(this);
        panelMenu.getBotonEjercicio3().addActionListener(this);
        panelMenu.getBotonEjercicio4().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object origen = evento.getSource();

        if (origen == panelMenu.getBotonEjercicio1()) {
            abrirVentana(new VentanaFormularioSaludo());
            return;
        }

        if (origen == panelMenu.getBotonEjercicio2()) {
            abrirVentana(new VentanaEditorTexto());
            return;
        }

        if (origen == panelMenu.getBotonEjercicio3()) {
            abrirVentana(new VentanaPreferencias());
            return;
        }

        if (origen == panelMenu.getBotonEjercicio4()) {
            abrirVentana(new VentanaLogin());
        }
    }

    private void abrirVentana(JFrame ventana) {
        ventana.setVisible(true);
    }
}