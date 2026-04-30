/**
 * Clase que demuestra sobrecarga de constructores y metodos.
 */
public class Usuario {
    private String nombre;
    private int edad;

    public Usuario() {
        this("Sin nombre", 0);
    }

    public Usuario(String nombre) {
        this(nombre, 0);
    }

    public Usuario(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        System.out.println("Usuario creado: " + this.nombre + " (" + this.edad + " anios)");
    }

    public void actualizarPerfil(String correo) {
        System.out.println("Perfil actualizado para " + nombre + " con correo: " + correo);
    }

    public void actualizarPerfil(String correo, int telefono) {
        System.out.println("Perfil actualizado para " + nombre + " con correo: " + correo + " y telefono: " + telefono);
    }
}
