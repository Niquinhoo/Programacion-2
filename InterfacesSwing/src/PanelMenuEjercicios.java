
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMenuEjercicios extends JPanel {

    private final JButton botonEjercicio1;
    private final JButton botonEjercicio2;
    private final JButton botonEjercicio3;
    private final JButton botonEjercicio4;

    public PanelMenuEjercicios() {
        setLayout(new GridLayout(6, 1, 10, 10));

        botonEjercicio1 = new JButton("Abrir Ejercicio 1");
        botonEjercicio2 = new JButton("Abrir Ejercicio 2");
        botonEjercicio3 = new JButton("Abrir Ejercicio 3");
        botonEjercicio4 = new JButton("Abrir Ejercicio 4");

        agregarSecciones();
    }

    private void agregarSecciones() {
        add(crearFilaTitulo());
        add(crearFilaDescripcion());
        add(botonEjercicio1);
        add(botonEjercicio2);
        add(botonEjercicio3);
        add(botonEjercicio4);
    }

    private JPanel crearFilaTitulo() {
        JPanel filaTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filaTitulo.add(new JLabel("Menu principal de ejercicios Swing"));
        return filaTitulo;
    }

    private JPanel crearFilaDescripcion() {
        JPanel filaDescripcion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filaDescripcion.add(new JLabel("Selecciona una pantalla para abrirla automaticamente."));
        return filaDescripcion;
    }

    public JButton getBotonEjercicio1() {
        return botonEjercicio1;
    }

    public JButton getBotonEjercicio2() {
        return botonEjercicio2;
    }

    public JButton getBotonEjercicio3() {
        return botonEjercicio3;
    }

    public JButton getBotonEjercicio4() {
        return botonEjercicio4;
    }
}