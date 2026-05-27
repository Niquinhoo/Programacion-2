import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {
    private final EmpleadoDAO empleadoDAO;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaEmpleados;
    private final JTextField txtId;
    private final JTextField txtNombre;
    private final JComboBox<DepartamentoItem> comboDepartamentos;
    private final JLabel lblFoto;
    private final JLabel lblRutaFoto;

    public VentanaPrincipal() {
        this.empleadoDAO = new EmpleadoDAO();

        setTitle("Gestion de Empleados JDBC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        modeloTabla = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Departamento", "Foto"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.getTableHeader().setReorderingAllowed(false);
        tablaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cargarEmpleadoSeleccionado();
            }
        });

        txtId = new JTextField(12);
        txtId.setEditable(false);
        txtNombre = new JTextField(18);
        comboDepartamentos = new JComboBox<>();
        lblFoto = new JLabel("Sin foto", SwingConstants.CENTER);
        lblFoto.setPreferredSize(new Dimension(180, 180));
        lblFoto.setBorder(BorderFactory.createEtchedBorder());
        lblRutaFoto = new JLabel("Sin archivo seleccionado");

        add(crearPanelFormulario(), BorderLayout.WEST);
        add(new JScrollPane(tablaEmpleados), BorderLayout.CENTER);

        cargarDepartamentos();
        cargarTabla();
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("ID"), gbc);
        gbc.gridy++;
        panel.add(txtId, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Nombre"), gbc);
        gbc.gridy++;
        panel.add(txtNombre, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Departamento"), gbc);
        gbc.gridy++;
        panel.add(comboDepartamentos, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Foto"), gbc);
        gbc.gridy++;
        panel.add(lblFoto, gbc);

        gbc.gridy++;
        panel.add(lblRutaFoto, gbc);

        gbc.gridy++;
        JButton btnBuscarFoto = new JButton("Buscar foto");
        btnBuscarFoto.addActionListener(e -> seleccionarFoto());
        panel.add(btnBuscarFoto, gbc);

        gbc.gridy++;
        panel.add(crearPanelBotones(), gbc);

        gbc.gridy++;
        gbc.weighty = 1.0;
        panel.add(new JLabel(), gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(e -> insertarEmpleado());

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarEmpleado());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarEmpleado());

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        return panelBotones;
    }

    private void cargarDepartamentos() {
        comboDepartamentos.removeAllItems();
        for (Map.Entry<Integer, String> entry : empleadoDAO.consultarDepartamentos().entrySet()) {
            comboDepartamentos.addItem(new DepartamentoItem(entry.getKey(), entry.getValue()));
        }
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        ArrayList<Empleado> empleados = empleadoDAO.consultarTodos();
        for (Empleado empleado : empleados) {
            modeloTabla.addRow(new Object[]{
                empleado.getId(),
                empleado.getNombre(),
                empleado.getDepartamento(),
                empleado.getFotoRuta() == null ? "" : empleado.getFotoRuta()
            });
        }
    }

    private void cargarEmpleadoSeleccionado() {
        int fila = tablaEmpleados.getSelectedRow();
        if (fila < 0) {
            return;
        }

        txtId.setText(String.valueOf(tablaEmpleados.getValueAt(fila, 0)));
        txtNombre.setText(String.valueOf(tablaEmpleados.getValueAt(fila, 1)));
        seleccionarDepartamentoPorNombre(String.valueOf(tablaEmpleados.getValueAt(fila, 2)));

        Object fotoValor = tablaEmpleados.getValueAt(fila, 3);
        String rutaFoto = fotoValor == null ? "" : String.valueOf(fotoValor);
        lblRutaFoto.setText(rutaFoto.isBlank() ? "Sin archivo seleccionado" : rutaFoto);
        mostrarPrevisualizacion(rutaFoto);
    }

    private void insertarEmpleado() {
        Empleado empleado = construirEmpleadoDesdeFormulario(false);
        if (empleado == null) {
            return;
        }

        if (empleadoDAO.insertar(empleado)) {
            cargarTabla();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Empleado agregado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar el empleado.");
        }
    }

    private void modificarEmpleado() {
        Empleado empleado = construirEmpleadoDesdeFormulario(true);
        if (empleado == null) {
            return;
        }

        if (empleadoDAO.actualizar(empleado)) {
            cargarTabla();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Empleado modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar el empleado.");
        }
    }

    private void eliminarEmpleado() {
        if (txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Selecciona un empleado antes de eliminar.");
            return;
        }

        int id = Integer.parseInt(txtId.getText());
        if (empleadoDAO.eliminar(id)) {
            cargarTabla();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el empleado.");
        }
    }

    private Empleado construirEmpleadoDesdeFormulario(boolean requiereId) {
        String nombre = txtNombre.getText().trim();
        DepartamentoItem departamentoItem = (DepartamentoItem) comboDepartamentos.getSelectedItem();

        if (nombre.isBlank()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede quedar vacio.");
            return null;
        }

        if (departamentoItem == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionarse un departamento.");
            return null;
        }

        if (requiereId && txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Selecciona un empleado de la tabla.");
            return null;
        }

        Empleado empleado = new Empleado();
        if (requiereId) {
            empleado.setId(Integer.parseInt(txtId.getText()));
        }
        empleado.setNombre(nombre);
        empleado.setDepartamentoId(departamentoItem.getId());
        empleado.setDepartamento(departamentoItem.getNombre());

        String rutaFoto = "Sin archivo seleccionado".equals(lblRutaFoto.getText()) ? "" : lblRutaFoto.getText();
        empleado.setFotoRuta(rutaFoto);
        return empleado;
    }

    private void seleccionarFoto() {
        JFileChooser chooser = new JFileChooser();
        int resultado = chooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            lblRutaFoto.setText(archivo.getAbsolutePath());
            mostrarPrevisualizacion(archivo.getAbsolutePath());
        }
    }

    private void mostrarPrevisualizacion(String rutaFoto) {
        if (rutaFoto == null || rutaFoto.isBlank()) {
            lblFoto.setText("Sin foto");
            lblFoto.setIcon(null);
            return;
        }

        File archivo = new File(rutaFoto);
        if (!archivo.exists()) {
            lblFoto.setText("Archivo no encontrado");
            lblFoto.setIcon(null);
            return;
        }

        ImageIcon icono = new ImageIcon(rutaFoto);
        Image imagenEscalada = icono.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        lblFoto.setText("");
        lblFoto.setIcon(new ImageIcon(imagenEscalada));
    }

    private void seleccionarDepartamentoPorNombre(String nombre) {
        for (int i = 0; i < comboDepartamentos.getItemCount(); i++) {
            DepartamentoItem item = comboDepartamentos.getItemAt(i);
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                comboDepartamentos.setSelectedIndex(i);
                return;
            }
        }
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        if (comboDepartamentos.getItemCount() > 0) {
            comboDepartamentos.setSelectedIndex(0);
        }
        lblRutaFoto.setText("Sin archivo seleccionado");
        mostrarPrevisualizacion("");
        tablaEmpleados.clearSelection();
    }

    private static class DepartamentoItem {
        private final int id;
        private final String nombre;

        private DepartamentoItem(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
}
