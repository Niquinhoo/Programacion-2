
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaEditorTexto extends JFrame {

    private final PanelEditorTexto panelEditor;
    private final BarraMenuEditorTexto barraMenu;
    private final ControladorEditorTexto controlador;

    public VentanaEditorTexto() {
        panelEditor = new PanelEditorTexto();
        barraMenu = new BarraMenuEditorTexto();
        controlador = new ControladorEditorTexto(panelEditor.getAreaTexto(), this, barraMenu);

        configurarVentana();
        agregarContenido();
        registrarEventos();
    }

    private void configurarVentana() {
        setTitle("Ejercicio 2 - Mini editor de texto");
        setSize(640, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void agregarContenido() {
        setJMenuBar(barraMenu);
        add(panelEditor, BorderLayout.CENTER);
    }

    private void registrarEventos() {
        controlador.registrarEventos();
    }
}