/**
 * Representa una factura concreta que extiende Documento e implementa
 * las interfaces Exportable y Auditable.
 */
public class Factura extends Documento implements Exportable, Auditable {
    private final double importe;

    public Factura(String numero, double importe) {
        super(numero);
        this.importe = importe;
    }

    @Override
    public void procesar() {
        System.out.println("Procesando factura " + numero + " por $" + importe);
    }

    public void procesar(boolean esUrgente) {
        if (esUrgente) {
            System.out.println("Procesamiento urgente de la factura " + numero);
        } else {
            procesar();
        }
    }

    @Override
    public void exportar() {
        System.out.println("Exportando factura " + numero + " a formato PDF.");
    }

    @Override
    public void registrarAuditoria() {
        System.out.println("Registrando auditoria de la factura " + numero);
    }
}
