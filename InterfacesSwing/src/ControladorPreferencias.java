
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorPreferencias implements ActionListener {

    private final PanelPreferencias panelPreferencias;
    private final DialogoPreferencias dialogoPreferencias;

    public ControladorPreferencias(PanelPreferencias panelPreferencias, DialogoPreferencias dialogoPreferencias) {
        this.panelPreferencias = panelPreferencias;
        this.dialogoPreferencias = dialogoPreferencias;
    }

    public void registrarEventos() {
        panelPreferencias.getBotonConfigurar().addActionListener(this);
        dialogoPreferencias.getBotonAceptar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object origen = evento.getSource();

        if (origen == panelPreferencias.getBotonConfigurar()) {
            dialogoPreferencias.setLocationRelativeTo(panelPreferencias);
            dialogoPreferencias.setVisible(true);
            return;
        }

        if (origen == dialogoPreferencias.getBotonAceptar()) {
            panelPreferencias.getEtiquetaOpciones().setText(construirMensajeOpciones());
            dialogoPreferencias.dispose();
        }
    }

    private String construirMensajeOpciones() {
        List<String> opcionesSeleccionadas = new ArrayList<>();

        if (dialogoPreferencias.getOpcionModoOscuro().isSelected()) {
            opcionesSeleccionadas.add("Modo Oscuro");
        }

        if (dialogoPreferencias.getOpcionNotificaciones().isSelected()) {
            opcionesSeleccionadas.add("Notificaciones");
        }

        if (dialogoPreferencias.getOpcionAutoguardado().isSelected()) {
            opcionesSeleccionadas.add("Autoguardado");
        }

        if (opcionesSeleccionadas.isEmpty()) {
            return "Opciones seleccionadas: Ninguna";
        }

        return "Opciones seleccionadas: " + String.join(", ", opcionesSeleccionadas);
    }
}