/**
 * La clase CuentaBancaria demuestra el concepto de encapsulación,
 * protegiendo el acceso directo a los datos sensibles como el saldo.
 */
public class CuentaBancaria {
    
    // Campos privados para encapsulación (Ejercicio 1.3)
    private double saldo;
    private String numeroCuenta;

    /**
     * Constructor para inicializar una cuenta con un número específico.
     * @param numeroCuenta El identificador único de la cuenta.
     */
    public CuentaBancaria(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0.0; // Inicialmente el saldo es cero
    }

    /**
     * Permite depositar dinero en la cuenta.
     * Implementa validación para proteger la integridad del saldo.
     * @param monto El valor a depositar. Debe ser positivo.
     */
    public void depositar(double monto) {
        // Validación para asegurar que no se depositen montos negativos
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Deposito exitoso de: $" + monto);
        } else {
            System.out.println("Error: No se pueden depositar montos negativos (" + monto + ").");
        }
    }

    /**
     * Método getter para obtener el saldo actual sin permitir modificación directa.
     * @return El saldo disponible en la cuenta.
     */
    public double getSaldo() {
        return this.saldo;
    }

    /**
     * Método getter para obtener el número de cuenta.
     * @return El identificador de la cuenta.
     */
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }

    // Método main para demostración de la clase
    public static void main(String[] args) {
        CuentaBancaria miCuenta = new CuentaBancaria("987654321");
        
        System.out.println("Numero de Cuenta: " + miCuenta.getNumeroCuenta());
        
        // Caso de éxito
        miCuenta.depositar(1500.0);
        
        // Caso de error (Validación de encapsulación)
        miCuenta.depositar(-200.0);
        
        System.out.println("Saldo actual: $" + miCuenta.getSaldo());
    }
}
