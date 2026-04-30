public class ExceptionExercises {
    private final UserService userService;
    private final FileService fileService;

    public ExceptionExercises(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    public void runExercise11() {
        ConsoleLogger.section("Ejercicio 1.1 - try/catch e info de excepcion");
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            ConsoleLogger.error("Mensaje: " + e.getMessage());
            ConsoleLogger.error("Tipo: " + e.getClass().getName());
        }
    }

    public void runExercise12() {
        ConsoleLogger.section("Ejercicio 1.2 - finally");
        try {
            int result = 10 / 0;
            ConsoleLogger.info("Resultado: " + result);
        } catch (ArithmeticException e) {
            ConsoleLogger.error("Capturada ArithmeticException: " + e.getMessage());
        } finally {
            ConsoleLogger.info("Limpieza final");
        }
    }

    public void runExercise13(String input) {
        ConsoleLogger.section("Ejercicio 1.3 - multi-catch");
        try {
            int number = Integer.parseInt(input);
            int result = 100 / number;
            ConsoleLogger.info("Resultado: " + result);
        } catch (NumberFormatException | ArithmeticException e) {
            ConsoleLogger.error("Error de calculo o conversion");
        }
    }

    public void runExercise21() {
        ConsoleLogger.section("Ejercicio 2.1 - Fail-fast con validaciones");

        try {
            userService.registerUser("", 20);
        } catch (IllegalArgumentException e) {
            ConsoleLogger.error(e.getMessage());
        }

        try {
            userService.registerUser("Ana", -1);
        } catch (IllegalArgumentException e) {
            ConsoleLogger.error(e.getMessage());
        }
    }

    public void runExercise22() {
        ConsoleLogger.section("Ejercicio 2.2 - Captura especifica con mensaje claro");
        try {
            userService.registerUser("Luis", -5);
        } catch (IllegalArgumentException e) {
            ConsoleLogger.error(e.getMessage());
        }
    }

    public void runExercise31() {
        ConsoleLogger.section("Ejercicio 3.1 - Excepcion checked personalizada");
        CuentaBancaria cuenta = new CuentaBancaria(500.0);

        try {
            cuenta.retirar(700.0);
            ConsoleLogger.info("Retiro exitoso. Saldo restante: " + cuenta.getSaldo());
        } catch (SaldoInsuficienteException e) {
            ConsoleLogger.error(e.getMessage());
        }
    }

    public void runExercise32() {
        ConsoleLogger.section("Ejercicio 3.2 - Excepcion unchecked personalizada");
        try {
            Producto producto = new Producto("Notebook", -1500.0);
            ConsoleLogger.info("Producto creado: " + producto.getNombre());
        } catch (RuntimeException e) {
            ConsoleLogger.error(e.getMessage());
        }
    }

    public void runExercise41(String filePath) {
        ConsoleLogger.section("Ejercicio 4.1 - try-with-resources");
        fileService.readPeopleFile(filePath);
    }
}
