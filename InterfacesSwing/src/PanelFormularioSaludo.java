
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelFormularioSaludo extends JPanel implements ActionListener {

    private final JTextField campoNombre;
    private final JLabel etiquetaResultado;
    private final JButton botonSaludar;

    public PanelFormularioSaludo() {
        setLayout(new GridLayout(3, 1, 10, 10));

        campoNombre = crearCampoNombre();
        etiquetaResultado = crearEtiquetaResultado();
        botonSaludar = crearBotonSaludar();

        agregarSecciones();
        registrarEvento();
    }

    private void agregarSecciones() {
        add(crearFilaIngreso());
        add(crearFilaBoton());
        add(crearFilaResultado());
    }

    private JPanel crearFilaIngreso() {
        JPanel filaIngreso = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaIngreso.add(new JLabel("Introduce tu nombre:"));
        filaIngreso.add(campoNombre);
        return filaIngreso;
    }

    private JPanel crearFilaBoton() {
        JPanel filaBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filaBoton.add(botonSaludar);
        return filaBoton;
    }

    private JPanel crearFilaResultado() {
        JPanel filaResultado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filaResultado.add(etiquetaResultado);
        return filaResultado;
    }

    private JTextField crearCampoNombre() {
        return new JTextField(20);
    }

    private JLabel crearEtiquetaResultado() {
        return new JLabel(" ");
    }

    private JButton crearBotonSaludar() {
        return new JButton("Saludar");
    }

    private void registrarEvento() {
        botonSaludar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String nombreIngresado = campoNombre.getText().trim();
        etiquetaResultado.setText("¡Hola, " + nombreIngresado + "!");
    }
}