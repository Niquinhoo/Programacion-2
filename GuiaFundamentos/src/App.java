/**
 * Clase principal para la ejecucion y demostracion de los ejercicios del TP1.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   EJECUCION DE EJERCICIOS - TP1");
        System.out.println("========================================\n");

        System.out.println(">>> Seccion 1: Fundamentos, encapsulacion y documentacion");
        SistemaGestor gestor = new SistemaGestor();
        gestor.imprimirLimiteConexiones();

        CuentaBancaria miCuenta = new CuentaBancaria("ABC-789-XYZ");
        System.out.println("Numero de cuenta: " + miCuenta.getNumeroCuenta());
        miCuenta.depositar(2500.75);
        miCuenta.depositar(-100.0);
        System.out.println("Saldo de la cuenta: $" + miCuenta.getSaldo());

        System.out.println("\n>>> Seccion 2: Metodos y constructores");
        Reporte reporte = new Reporte();
        reporte.generarEncabezado();
        System.out.println(reporte.obtenerCuerpo());
        reporte.mostrarSecciones("Introduccion", "Desarrollo", "Cierre");
        reporte.mostrarSecciones();

        Usuario usuarioDefault = new Usuario();
        Usuario usuarioNombre = new Usuario("Ana");
        Usuario usuarioCompleto = new Usuario("Lucia", 28);
        usuarioDefault.actualizarPerfil("sin-datos@ejemplo.com");
        usuarioNombre.actualizarPerfil("ana@ejemplo.com", 1122334455);
        usuarioCompleto.actualizarPerfil("lucia@ejemplo.com");

        System.out.println("\n>>> Seccion 3: Herencia, polimorfismo y abstraccion");
        Documento doc1 = new Factura("FAC-1001", 15200.50);
        Documento doc2 = new Recibo("REC-2002", "Pago de matricula");
        doc1.procesar();
        doc2.procesar();

        Factura factura = new Factura("FAC-1003", 8900.00);
        factura.procesar(true);

        System.out.println("\n>>> Seccion 4: Interfaces y polimorfismo de interfaz");
        factura.exportar();
        factura.registrarAuditoria();
        ServicioImpresion.enviarAImpresion(factura);

        System.out.println("\n========================================");
    }
}
