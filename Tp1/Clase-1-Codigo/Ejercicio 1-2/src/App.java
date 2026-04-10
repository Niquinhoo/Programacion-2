/**
 * Clase principal para la ejecución y demostración de los ejercicios del TP1.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   EJECUCIÓN DE EJERCICIOS - TP1");
        System.out.println("========================================\n");

        // Demostración Ejercicio 1.1 y 1.2: Sistema Gestor
        System.out.println(">>> Ejercicio 1.1 y 1.2: Sistema Gestor");
        SistemaGestor gestor = new SistemaGestor();
        // El método main de SistemaGestor también tiene lógica, pero aquí llamamos a su método específico
        gestor.imprimirLimiteConexiones();
        System.out.println();

        // Demostración Ejercicio 1.3: Cuenta Bancaria
        System.out.println(">>> Ejercicio 1.3: Cuenta Bancaria");
        CuentaBancaria miCuenta = new CuentaBancaria("ABC-789-XYZ");
        System.out.println("Número de Cuenta: " + miCuenta.getNumeroCuenta());
        
        // Operaciones
        miCuenta.depositar(2500.75);
        miCuenta.depositar(-100.0); // Debería fallar por validación
        
        System.out.println("Saldo de la cuenta: $" + miCuenta.getSaldo());
        System.out.println("\n========================================");
    }
}
