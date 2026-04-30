/**
 * Servicio independiente que recibe cualquier documento exportable.
 */
public class ServicioImpresion {
    public static void enviarAImpresion(Exportable documentoExportable) {
        System.out.println("Enviando documento a impresion...");
        documentoExportable.exportar();
    }
}
