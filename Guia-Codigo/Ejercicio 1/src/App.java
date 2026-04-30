import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Punto de entrada de la aplicacion de consola para la Guia 1 del framework de
 * colecciones.
 *
 * <p>La clase ejecuta una secuencia de ejercicios practicos organizados por
 * tematicas: arreglos, listas, conjuntos, mapas y estrategias de
 * ordenamiento. Cada metodo privado representa un ejercicio independiente y
 * muestra por consola el resultado de las operaciones solicitadas en la guia.
 */
public class App {
    /**
     * Ejecuta todos los ejercicios de la guia en el orden definido.
     *
     * @param args argumentos de linea de comandos. No se utilizan en esta
     *             aplicacion.
     */
    public static void main(String[] args) {
        imprimirTitulo("Guia 1 - Framework de Colecciones");

        ejercicio11();
        ejercicio12();
        ejercicio21();
        ejercicio22();
        ejercicio31();
        ejercicio32();
        ejercicio41();
        ejercicio42();
        ejercicio51();
        ejercicio52();
    }

    /**
     * Resuelve el ejercicio 1.1 mostrando la creacion, carga, actualizacion y
     * recuperacion de elementos dentro de un arreglo de enteros.
     */
    private static void ejercicio11() {
        imprimirSeccion("Ejercicio 1.1 - Creacion, actualizacion y recuperacion en arrays");

        int[] numeros = new int[5];
        numeros[0] = 10;
        numeros[1] = 20;
        numeros[2] = 30;
        numeros[3] = 40;
        numeros[4] = 50;

        System.out.println("Array inicial: " + Arrays.toString(numeros));

        numeros[2] = 35;
        System.out.println("Tercer elemento actualizado: " + Arrays.toString(numeros));

        int ultimoIndice = numeros.length - 1;
        int ultimoElemento = numeros[ultimoIndice];
        System.out.println("Ultimo elemento usando length: indice " + ultimoIndice + ", valor " + ultimoElemento);
    }

    /**
     * Resuelve el ejercicio 1.2 ordenando un arreglo de cadenas y buscando un
     * valor especifico mediante busqueda binaria.
     */
    private static void ejercicio12() {
        imprimirSeccion("Ejercicio 1.2 - Ordenamiento y busqueda en arrays");

        String[] nombres = {"Zoe", "Ana", "Carlos", "Bruno", "Elena"};
        System.out.println("Array desordenado: " + Arrays.toString(nombres));

        Arrays.sort(nombres);
        System.out.println("Array ordenado ascendente: " + Arrays.toString(nombres));

        String nombreBuscado = "Carlos";
        int posicion = Arrays.binarySearch(nombres, nombreBuscado);
        System.out.println("Resultado de binarySearch para " + nombreBuscado + ": posicion " + posicion);
    }

    /**
     * Resuelve el ejercicio 2.1 utilizando un {@link ArrayList} para demostrar
     * operaciones CRUD basicas.
     */
    private static void ejercicio21() {
        imprimirSeccion("Ejercicio 2.1 - Operaciones CRUD en ArrayList");

        ArrayList<String> colores = new ArrayList<>();
        colores.add("Rojo");
        colores.add("Verde");
        colores.add("Azul");
        System.out.println("Lista luego de add(): " + colores);

        colores.set(1, "Amarillo");
        System.out.println("Lista luego de set() en la segunda posicion: " + colores);

        String primerValor = colores.get(0);
        System.out.println("Valor recuperado con get(0): " + primerValor);

        String eliminado = colores.remove(colores.size() - 1);
        System.out.println("Elemento eliminado con remove(): " + eliminado);
        System.out.println("Lista final: " + colores);
    }

    /**
     * Resuelve el ejercicio 2.2 comparando una lista inmutable creada con
     * {@link List#of(Object...)} y una {@link LinkedList} mutable.
     */
    private static void ejercicio22() {
        imprimirSeccion("Ejercicio 2.2 - Listas inmutables vs LinkedList");

        List<String> estaciones = List.of("Otono", "Invierno", "Primavera");
        System.out.println("Lista inmutable creada con List.of(): " + estaciones);

        try {
            estaciones.add("Verano");
        } catch (UnsupportedOperationException e) {
            System.out.println("Excepcion esperada al intentar agregar en lista inmutable: "
                    + e.getClass().getSimpleName());
        }

        LinkedList<String> estacionesMutables = new LinkedList<>(estaciones);
        estacionesMutables.add(1, "Verano");
        estacionesMutables.add(3, "Pretemporada");
        System.out.println("LinkedList mutable con inserciones en el medio: " + estacionesMutables);
    }

    /**
     * Resuelve el ejercicio 3.1 demostrando la unicidad de elementos en un
     * {@link HashSet} y sus operaciones principales.
     */
    private static void ejercicio31() {
        imprimirSeccion("Ejercicio 3.1 - Unicidad en HashSet");

        Set<Integer> numerosUnicos = new HashSet<>();
        numerosUnicos.add(7);
        numerosUnicos.add(7);
        numerosUnicos.add(7);
        numerosUnicos.add(12);
        numerosUnicos.add(3);

        System.out.println("Contenido del set: " + numerosUnicos);
        System.out.println("Tamano del set tras agregar 7 tres veces: " + numerosUnicos.size());

        int numeroBuscado = 12;
        System.out.println("El set contiene " + numeroBuscado + ": " + numerosUnicos.contains(numeroBuscado));

        boolean eliminado = numerosUnicos.remove(numeroBuscado);
        System.out.println("Se elimino " + numeroBuscado + ": " + eliminado);
        System.out.println("Set luego de remove(): " + numerosUnicos);
    }

    /**
     * Resuelve el ejercicio 3.2 utilizando un {@link ArrayDeque} como cola de
     * doble extremo para insertar y remover elementos de ambos lados.
     */
    private static void ejercicio32() {
        imprimirSeccion("Ejercicio 3.2 - ArrayDeque como cola de doble extremo");

        ArrayDeque<String> tareas = new ArrayDeque<>();
        tareas.addFirst("Preparar informe");
        tareas.addLast("Enviar correo");
        tareas.addFirst("Revisar pendientes");
        tareas.addLast("Cerrar sesion");

        System.out.println("Deque luego de addFirst() y addLast(): " + tareas);

        String primero = tareas.removeFirst();
        String ultimo = tareas.removeLast();
        System.out.println("Elemento removido del inicio: " + primero);
        System.out.println("Elemento removido del final: " + ultimo);
        System.out.println("Deque restante: " + tareas);
    }

    /**
     * Resuelve el ejercicio 4.1 mostrando operaciones basicas de alta,
     * recuperacion, actualizacion y baja sobre un {@link HashMap}.
     */
    private static void ejercicio41() {
        imprimirSeccion("Ejercicio 4.1 - Manejo de HashMap");

        Map<Integer, String> usuarios = new HashMap<>();
        usuarios.put(101, "Lucia");
        usuarios.put(102, "Mateo");
        usuarios.put(103, "Sofia");
        System.out.println("HashMap inicial: " + usuarios);

        String nombreUsuario = usuarios.get(102);
        System.out.println("Valor recuperado con get(102): " + nombreUsuario);

        usuarios.replace(103, "Sofia B");
        System.out.println("HashMap luego de replace(103): " + usuarios);

        String registroEliminado = usuarios.remove(101);
        System.out.println("Registro eliminado con clave 101: " + registroEliminado);
        System.out.println("HashMap final: " + usuarios);
    }

    /**
     * Resuelve el ejercicio 4.2 demostrando como {@link TreeMap} mantiene las
     * claves ordenadas segun su orden natural.
     */
    private static void ejercicio42() {
        imprimirSeccion("Ejercicio 4.2 - Ordenamiento automatico con TreeMap");

        TreeMap<Integer, String> usuariosOrdenados = new TreeMap<>();
        usuariosOrdenados.put(300, "Julian");
        usuariosOrdenados.put(100, "Carla");
        usuariosOrdenados.put(200, "Bianca");
        usuariosOrdenados.put(150, "Andres");

        System.out.println("TreeMap completo: " + usuariosOrdenados);
        System.out.println("Recorrido por keySet() en orden ascendente:");
        for (Integer clave : usuariosOrdenados.keySet()) {
            System.out.println("Clave " + clave + " -> " + usuariosOrdenados.get(clave));
        }
    }

    /**
     * Resuelve el ejercicio 5.1 ordenando empleados mediante el orden natural
     * definido en {@link Empleado#compareTo(Empleado)}.
     */
    private static void ejercicio51() {
        imprimirSeccion("Ejercicio 5.1 - Orden natural con Comparable");

        List<Empleado> empleados = crearEmpleados();
        System.out.println("Lista original: " + empleados);

        Collections.sort(empleados);
        System.out.println("Lista ordenada por edad (compareTo): " + empleados);
    }

    /**
     * Resuelve el ejercicio 5.2 ordenando empleados con una estrategia
     * personalizada basada en {@link EmpleadoPorNombreComparator}.
     */
    private static void ejercicio52() {
        imprimirSeccion("Ejercicio 5.2 - Orden personalizado con Comparator");

        List<Empleado> empleados = crearEmpleados();
        System.out.println("Lista original: " + empleados);

        Collections.sort(empleados, new EmpleadoPorNombreComparator());
        System.out.println("Lista ordenada por nombre (Comparator): " + empleados);
    }

    /**
     * Crea la lista base de empleados utilizada en los ejercicios de
     * ordenamiento.
     *
     * @return una lista mutable con empleados de ejemplo.
     */
    private static List<Empleado> crearEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("Marina", 34));
        empleados.add(new Empleado("Alberto", 41));
        empleados.add(new Empleado("Zoe", 29));
        empleados.add(new Empleado("Bruno", 29));
        return empleados;
    }

    /**
     * Imprime en consola un titulo principal delimitado por separadores para
     * identificar el inicio de la ejecucion.
     *
     * @param titulo texto del encabezado principal.
     */
    private static void imprimirTitulo(String titulo) {
        System.out.println("============================================================");
        System.out.println(titulo);
        System.out.println("============================================================");
    }

    /**
     * Imprime en consola el encabezado de una seccion o ejercicio.
     *
     * @param titulo texto descriptivo de la seccion actual.
     */
    private static void imprimirSeccion(String titulo) {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println(titulo);
        System.out.println("------------------------------------------------------------");
    }
}
