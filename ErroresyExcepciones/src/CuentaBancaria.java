public class CuentaBancaria {
    private double saldo;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto > saldo) {
            throw new SaldoInsuficienteException(
                "Saldo insuficiente. Saldo actual: " + saldo + ", monto solicitado: " + monto
            );
        }
        saldo -= monto;
    }

    public double getSaldo() {
        return saldo;
    }
}
