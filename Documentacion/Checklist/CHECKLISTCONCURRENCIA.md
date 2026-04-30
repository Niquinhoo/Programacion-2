# Checklist: Concurrencia y Multitarea

- [x] Ejercicio 1.1: Extendiendo la clase `Thread`
  Se creó `TareaHilo extends Thread`, se sobrescribió `run()` y se inició la ejecución con `start()` para lanzar varios hilos en paralelo.

- [x] Ejercicio 1.2: Implementando la interfaz `Runnable`
  Se implementó `TareaRunnable` y se reutilizó una misma tarea en distintos objetos `Thread`, confirmando la separación entre tarea y ejecutor.

- [x] Ejercicio 2.1: Simulando una anomalía
  Se compartió una instancia de `Contador` entre dos hilos sin sincronización y se observó un valor final incorrecto por condición de carrera.

- [x] Ejercicio 3.1: Sincronización de métodos
  Se protegió `incrementar()` con `synchronized` para garantizar exclusión mutua y obtener el resultado correcto esperado.

- [x] Ejercicio 3.2: Bloques sincronizados
  Se reemplazó la sincronización del método por un bloque `synchronized`, limitando la sección crítica solo a la escritura del contador.

- [x] Ejercicio 4.1: Identidad y prioridades
  Se usó `Thread.currentThread()` para identificar cada hilo y `setPriority()` para asignar prioridad baja y alta antes del arranque.

- [x] Ejercicio 4.2: Espera e interrupción
  Se aplicó `join()` para bloquear al hilo principal hasta terminar la tarea lenta y luego `interrupt()` para cortar un hilo en espera.
