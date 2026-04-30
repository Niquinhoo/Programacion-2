public class Producto {
    private final String nombre;
    private final double precio;

    public Producto(String nombre, double precio) {
        if (precio <= 0) {
            throw new ProductoInvalidoException("Precio invalido: debe ser mayor a cero.");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
