Unidad 5: Procesos, Concurrencia y Redes
Guía de Ejercicios Prácticos: Concurrencia y Multitarea Básica
Sección 1: Implementación de Tareas (Creación de Hilos)
Objetivo: Comprender las dos formas clásicas de crear y poner en ejecución hilos en Java, entendiendo
que el gestor reparte el tiempo de CPU entre los procesos.
 Ejercicio 1.1 (Extendiendo la clase Thread): Crea una clase llamada TareaHilo que herede (extends) de la clase
Thread. Sobrescribe el método run() para que incluya un bucle que imprima números del 1 al 5. En tu método
main, crea dos o tres instancias de esta clase y ponlas en ejecución concurrente invocando obligatoriamente el
método start() (el cual solicitará a la JVM que las ponga en ejecución concurrente) y no el método run()
directamente.
 Ejercicio 1.2 (Implementando la interfaz Runnable): Crea una clase llamada TareaRunnable que implemente la
interfaz Runnable y proporcione el código de la tarea en el método run(). En tu programa principal, crea una
instancia de esta clase y utilízala para crear nuevos objetos de tipo Thread pasándola como argumento al
constructor (new Thread(obj)). Llama al método start() de estos nuevos hilos y observa cómo se ejecutan
simultáneamente.
Sección 2: Condiciones de Carrera y Secciones Críticas
Objetivo: Provocar e identificar los problemas que ocurren cuando múltiples hilos comparten y manipulan
un mismo recurso sin control.
 Ejercicio 2.1 (Simulando una anomalía): Crea una clase Contador con una variable entera iniciada en 0 y un
método incrementar(). Luego, crea dos hilos que compartan la misma instancia de Contador y llamen al método
incrementar() miles de veces dentro de un bucle. Observa el resultado final al terminar los hilos. Comprobarás que
se produce una situación anómala (el número final es incorrecto) debido a que ocurre una condición de carrera:
dos o más hilos acceden a un recurso compartido al mismo tiempo, interrumpiendo el proceso del otro en la
sección crítica.
Sección 3: Solución mediante Sincronización
Objetivo: Garantizar la integridad de los datos bloqueando el acceso concurrente a las secciones críticas.
 Ejercicio 3.1 (Sincronización de métodos): Para solucionar el problema del ejercicio anterior, utiliza la palabra
clave synchronized en la definición del método incrementar(). Ejecuta nuevamente el programa y verifica que el
resultado ahora es correcto, ya que el primer hilo en entrar adquiere el "monitor del objeto" y no lo suelta hasta
completar la ejecución, bloqueando a los demás.
 Ejercicio 3.2 (Bloques sincronizados): Modifica el código para quitar el modificador synchronized del método y,
en su lugar, utiliza un bloque sincronizado (synchronized(obj) { ... }) que envuelva únicamente las instrucciones
que modifican el contador (la sección crítica).
Sección 4: Control de Ciclo de Vida y Métodos Adicionales
Objetivo: Utilizar los métodos estáticos y de instancia de la clase Thread para controlar el comportamiento
y la prioridad de los hilos.
 Ejercicio 4.1 (Identidad y Prioridades): Modifica tus hilos para que, al ejecutar la tarea, impriman su propia
identidad utilizando el método estático Thread.currentThread(). Luego, antes de iniciar los hilos en el main, utiliza
el método setPriority(int priority) para asignarle a un hilo la prioridad 1 y al otro la prioridad 10. Observa si notas
alguna diferencia, recordando que, en teoría, a mayor prioridad se le dedica más tiempo de CPU a la tarea.
 Ejercicio 4.2 (Espera e Interrupción): Crea un hilo que simule una tarea que tarda mucho tiempo. En tu método
main, una vez iniciado ese hilo, utiliza el método join() sobre él. Esto detendrá el proceso actual (el hilo principal)
hasta que el hilo secundario finalice su ejecución. Como experimento adicional, en lugar de esperar a que termine,
utiliza el método interrupt() sobre el hilo secundario para instar a la JVM a detenerlo; observa cómo se produce
una excepción si el hilo se encontraba bloqueado o en espera.