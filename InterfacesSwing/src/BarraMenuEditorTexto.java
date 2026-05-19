
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BarraMenuEditorTexto extends JMenuBar {

    private final JMenuItem itemLimpiarTexto;
    private final JMenuItem itemSalir;

    public BarraMenuEditorTexto() {
        JMenu menuArchivo = new JMenu("Archivo");

        itemLimpiarTexto = new JMenuItem("Limpiar texto");
        itemSalir = new JMenuItem("Salir");

        menuArchivo.add(itemLimpiarTexto);
        menuArchivo.add(itemSalir);
        add(menuArchivo);
    }

    public JMenuItem getItemLimpiarTexto() {
        return itemLimpiarTexto;
    }

    public JMenuItem getItemSalir() {
        return itemSalir;
    }
}