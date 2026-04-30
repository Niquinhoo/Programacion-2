/**
 * Representa un recibo que extiende Documento.
 */
public class Recibo extends Documento {
    private final String concepto;

    public Recibo(String numero, String concepto) {
        super(numero);
        this.concepto = concepto;
    }

    @Override
    public void procesar() {
        System.out.println("Procesando recibo " + numero + " asociado a: " + concepto);
    }
}
