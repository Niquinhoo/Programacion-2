/**
 * Clase que encapsula una cuenta bancaria simple.
 */
public class CuentaBancaria {
    private double saldo;
    private final String numeroCuenta;

    public CuentaBancaria(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0.0;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("No se puede depositar un monto negativo o cero.");
            return;
        }

        saldo += monto;
        System.out.println("Deposito realizado por $" + monto);
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
}
