/**
 * La clase Usuario demuestra la sobrecarga de constructores y métodos.
 */
public class Usuario {
    private String nombre;
    private int edad;
    private String correo;
    private int telefono;

    /**
     * Ejercicio 2.2: Sobrecarga de Constructores - Opción 1 (Por defecto)
     */
    public Usuario() {
        this.nombre = "Invitado";
        this.edad = 0;
    }

    /**
     * Ejercicio 2.2: Sobrecarga de Constructores - Opción 2 (Solo nombre)
     * @param nombre Nombre del usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.edad = 18; // Edad predeterminada
    }

    /**
     * Ejercicio 2.2: Sobrecarga de Constructores - Opción 3 (Nombre y edad)
     * @param nombre Nombre del usuario.
     * @param edad Edad del usuario.
     */
    public Usuario(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    /**
     * Ejercicio 2.2: Sobrecarga de Métodos - Versión 1 (Actualizar correo)
     * @param correo Nuevo correo electrónico.
     */
    public void actualizarPerfil(String correo) {
        this.correo = correo;
        System.out.println("Perfil actualizado: Correo fijado en " + correo);
    }

    /**
     * Ejercicio 2.2: Sobrecarga de Métodos - Versión 2 (Actualizar correo y teléfono)
     * @param correo Nuevo correo electrónico.
     * @param telefono Nuevo número de teléfono.
     */
    public void actualizarPerfil(String correo, int telefono) {
        this.correo = correo;
        this.telefono = telefono;
        System.out.println("Perfil actualizado: Correo " + correo + " y Teléfono " + telefono);
    }

    /**
     * Método auxiliar para mostrar el estado del objeto.
     */
    public void mostrarInfo() {
        System.out.println("Usuario: [Nombre=" + nombre + ", Edad=" + edad + "]");
    }

    // Demostración del ejercicio 2.2
    public static void main(String[] args) {
        System.out.println("--- Demostración de Sobrecarga de Constructores ---");
        Usuario user1 = new Usuario();
        Usuario user2 = new Usuario("Nicolas");
        Usuario user3 = new Usuario("Andres", 22);

        user1.mostrarInfo();
        user2.mostrarInfo();
        user3.mostrarInfo();

        System.out.println("\n--- Demostración de Sobrecarga de Métodos ---");
        user2.actualizarPerfil("nico@example.com");
        user3.actualizarPerfil("andres.prof@gmail.com", 11223344);
    }
}
