Guía de Ejercicios Prácticos: Unidad 3 - Gestión de Errores y Excepciones
A continuación, te presento una guía de ejercicios prácticos basada en las metodologías y
mecánicas de manejo de excepciones en Java.
Sección 1: Bloques try/catch/finally y multi-catch
Objetivo: Comprender cómo capturar errores en tiempo de ejecución, asegurar la ejecución de
código de limpieza y agrupar capturas de excepciones.
• Ejercicio 1.1 (Bloques try/catch y captura de información): Una excepción contiene
información útil, como el mensaje y el tipo de excepción. Crea un programa que intente
convertir la cadena "abc" a un número utilizando Integer.parseInt(). Envuelve este
código en un bloque try/catch que capture la NumberFormatException. En el bloque
catch, imprime el mensaje del error usando e.getMessage() y el tipo de clase usando
e.getClass().getName().
• Ejercicio 1.2 (El bloque finally): El bloque finally se ejecuta siempre, haya o no ocurrido
una excepción, y se utiliza generalmente para ejecutar limpieza obligatoria. Crea un método
que simule una división por cero (10 / 0) y capture la ArithmeticException resultante.
Añade un bloque finally que imprima en consola "Limpieza final" para comprobar que se
ejecuta sin importar la falla.
• Ejercicio 1.3 (Uso de multi-catch): Desde Java 7, puedes capturar varias excepciones en
un mismo bloque catch usando el operador |, lo cual sirve cuando varias excepciones se
manejan de la misma forma. Escribe un programa que reciba un String, lo convierta a
entero, y luego divida 100 por ese número. Utiliza un único bloque catch para capturar
simultáneamente NumberFormatException | ArithmeticException e imprime un
mensaje genérico de "Error de cálculo o conversión".
Sección 2: Metodologías para la gestión y prevención de errores
Objetivo: Aplicar buenas prácticas para evitar estados inconsistentes en la aplicación, utilizando
validaciones y evitando ignorar excepciones.
• Ejercicio 2.1 (Validar entradas y Fallar Rápido / Fail-fast): La metodología de "fallar
rápido" consiste en detectar errores lo antes posible validando los datos de entrada. Crea
un método registrarUsuario(String nombre, int edad). Dentro del método,
verifica si el nombre es nulo o está en blanco, y si la edad es menor a 0. Si alguna condición
falla, utiliza la palabra clave throw para lanzar manualmente una
IllegalArgumentException.
• Ejercicio 2.2 (Uso de mensajes claros y captura específica): Una buena práctica es no
abusar de catch (Exception e) ni dejar bloques catch vacíos, además de proveer
mensajes de error claros. Modifica el ejercicio anterior para que al lanzar la
IllegalArgumentException por la edad, el mensaje sea explícito, por ejemplo: "La
edad no puede ser negativa.". Luego, al llamar a la función en tu main, captura
específicamente la IllegalArgumentException en lugar de una Exception general e
imprime el mensaje del error.
Sección 3: Creación e Implementación de Excepciones Personalizadas
Objetivo: Distinguir entre excepciones verificadas (checked) y no verificadas (unchecked), y
encapsular errores de la lógica de negocio.
• Ejercicio 3.1 (Excepción Checked Personalizada): Las excepciones personalizadas se
crean cuando el error pertenece al dominio del negocio. Crea una clase
SaldoInsuficienteException que herede de Exception (haciéndola una excepción
checked que el compilador obliga a manejar) y que contenga un constructor que reciba un
mensaje. Luego, crea una clase CuentaBancaria con un método retirar(double
monto) que declare en su firma throws SaldoInsuficienteException. Si el monto a
retirar es mayor al saldo, lanza esta nueva excepción. En el método main, llama a retirar
manejando obligatoriamente el error con un try/catch.
• Ejercicio 3.2 (Excepción Unchecked Personalizada): Si la excepción hereda de
RuntimeException, no es obligatorio capturarla y se recomienda cuando el error
representa un mal uso o dato inválido. Crea una clase ProductoInvalidoException que
herede de RuntimeException. Escribe una clase Producto que, en su constructor, lance
esta excepción si el precio ingresado es menor o igual a cero. Instancia el producto con un
valor negativo y observa cómo el programa falla si no lo envuelves en un try/catch,
demostrando que el compilador no te obliga a hacerlo.
Sección 4: Automatización de Recursos
Objetivo: Gestionar correctamente los recursos de E/S del sistema sin depender exclusivamente
del bloque finally.
• Ejercicio 4.1 (Manejo de archivos con try-with-resources): La estructura try-with-
resources se usa para cerrar automáticamente recursos como archivos y streams,
siempre y cuando el recurso implemente AutoCloseable o Closeable. Crea un archivo de
texto llamado "personas.txt". Escribe un programa que utilice un BufferedReader
envuelto en los paréntesis de un try (try (BufferedReader br = ...)) para leer el
archivo línea por línea e imprimirlo en consola. Comprueba cómo este enfoque es más
limpio y seguro, ya que Java cierra automáticamente el BufferedReader al terminar el
bloque o al ocurrir un error, ahorrándote la necesidad de cerrarlo manualmente en un
bloque finally. Añade un bloque catch para manejar la IOException en caso de que el
archivo no pueda procesarse.