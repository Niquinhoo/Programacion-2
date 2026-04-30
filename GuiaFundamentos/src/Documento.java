/**
 * Clase abstracta que define el contrato base para documentos procesables.
 */
public abstract class Documento {
    protected final String numero;

    protected Documento(String numero) {
        this.numero = numero;
    }

    public abstract void procesar();
}
