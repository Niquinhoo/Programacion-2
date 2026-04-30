

public class UserService {
    public void registerUser(String nombre, int edad) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni estar en blanco.");
        }

        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
    }
}
