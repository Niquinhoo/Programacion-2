import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelEditorTexto extends JPanel {

    private final JTextArea areaTexto;

    public PanelEditorTexto() {
        setLayout(new BorderLayout());
        areaTexto = crearAreaTexto();
        agregarAreaDesplazable();
    }

    private JTextArea crearAreaTexto() {
        JTextArea nuevaAreaTexto = new JTextArea();
        nuevaAreaTexto.setLineWrap(true);
        nuevaAreaTexto.setWrapStyleWord(true);
        return nuevaAreaTexto;
    }

    private void agregarAreaDesplazable() {
        JScrollPane panelDesplazamiento = new JScrollPane(areaTexto);
        add(panelDesplazamiento, BorderLayout.CENTER);
    }

    public JTextArea getAreaTexto() {
        return areaTexto;
    }
}
