
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPreferencias extends JPanel {

    private final JLabel etiquetaOpciones;
    private final JButton botonConfigurar;

    public PanelPreferencias() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

        etiquetaOpciones = new JLabel("Opciones seleccionadas: Ninguna");
        botonConfigurar = new JButton("Configurar Preferencias");

        add(etiquetaOpciones);
        add(botonConfigurar);
    }

    public JLabel getEtiquetaOpciones() {
        return etiquetaOpciones;
    }

    public JButton getBotonConfigurar() {
        return botonConfigurar;
    }
}