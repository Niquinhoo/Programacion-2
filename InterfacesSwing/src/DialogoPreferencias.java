
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class DialogoPreferencias extends JDialog {

    private final JCheckBox opcionModoOscuro;
    private final JCheckBox opcionNotificaciones;
    private final JCheckBox opcionAutoguardado;
    private final JButton botonAceptar;

    public DialogoPreferencias(Frame propietario) {
        super(propietario, "Preferencias", true);

        opcionModoOscuro = new JCheckBox("Modo Oscuro");
        opcionNotificaciones = new JCheckBox("Notificaciones");
        opcionAutoguardado = new JCheckBox("Autoguardado");
        botonAceptar = new JButton("Aceptar");

        configurarDialogo();
        agregarContenido();
    }

    private void configurarDialogo() {
        setSize(320, 220);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void agregarContenido() {
        JPanel panelOpciones = new JPanel(new GridLayout(3, 1, 0, 10));
        panelOpciones.add(opcionModoOscuro);
        panelOpciones.add(opcionNotificaciones);
        panelOpciones.add(opcionAutoguardado);

        JPanel panelAcciones = new JPanel();
        panelAcciones.add(botonAceptar);

        add(panelOpciones, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.SOUTH);
    }

    public JCheckBox getOpcionModoOscuro() {
        return opcionModoOscuro;
    }

    public JCheckBox getOpcionNotificaciones() {
        return opcionNotificaciones;
    }

    public JCheckBox getOpcionAutoguardado() {
        return opcionAutoguardado;
    }

    public JButton getBotonAceptar() {
        return botonAceptar;
    }
}