# Logs: Concurrencia y Multitarea

Fecha de verificación: 30/04/2026

## Compilación

Comando utilizado:

```bash
javac src\App.java
```

Resultado:

```text
Compilación exitosa sin errores.
```

## Ejecución completa

Comando utilizado:

```bash
java -cp src App
```

Salida registrada:

```text
==============================
1.1 - Extender Thread
==============================
[Hilo-Thread-3] paso 1 - Hilo-Thread-3
[Hilo-Thread-3] paso 2 - Hilo-Thread-3
[Hilo-Thread-3] paso 3 - Hilo-Thread-3
[Hilo-Thread-1] paso 1 - Hilo-Thread-1
[Hilo-Thread-2] paso 1 - Hilo-Thread-2
[Hilo-Thread-2] paso 2 - Hilo-Thread-2
[Hilo-Thread-2] paso 3 - Hilo-Thread-2
[Hilo-Thread-3] paso 4 - Hilo-Thread-3
[Hilo-Thread-1] paso 2 - Hilo-Thread-1
[Hilo-Thread-1] paso 3 - Hilo-Thread-1
[Hilo-Thread-2] paso 4 - Hilo-Thread-2
[Hilo-Thread-3] paso 5 - Hilo-Thread-3
[Hilo-Thread-1] paso 4 - Hilo-Thread-1
[Hilo-Thread-1] paso 5 - Hilo-Thread-1
[Hilo-Thread-2] paso 5 - Hilo-Thread-2

==============================
1.2 - Implementar Runnable
==============================
[Tarea-Runnable] paso 1 - Hilo-Runnable-1
[Tarea-Runnable] paso 2 - Hilo-Runnable-1
[Tarea-Runnable] paso 1 - Hilo-Runnable-2
[Tarea-Runnable] paso 2 - Hilo-Runnable-2
[Tarea-Runnable] paso 3 - Hilo-Runnable-2
[Tarea-Runnable] paso 4 - Hilo-Runnable-2
[Tarea-Runnable] paso 3 - Hilo-Runnable-1
[Tarea-Runnable] paso 5 - Hilo-Runnable-2
[Tarea-Runnable] paso 4 - Hilo-Runnable-1
[Tarea-Runnable] paso 5 - Hilo-Runnable-1

==============================
2.1 - Condicion de carrera
==============================
Resultado esperado: 200000
Resultado obtenido: 102274
Observacion: el valor puede ser incorrecto porque la operacion no es atomica.

==============================
3.1 - Metodo synchronized
==============================
Resultado esperado: 200000
Resultado obtenido: 200000
Observacion: el metodo synchronized protege la seccion critica.

==============================
3.2 - Bloque synchronized
==============================
Resultado esperado: 200000
Resultado obtenido: 200000
Observacion: el bloque synchronized limita el alcance de la exclusion mutua.

==============================
4.1 - Identidad y prioridades
==============================
[Prioridad-Alta] identidad=Thread[#38,Prioridad-Alta,10,main] prioridad=10 paso=1
[Prioridad-Alta] identidad=Thread[#38,Prioridad-Alta,10,main] prioridad=10 paso=2
[Prioridad-Alta] identidad=Thread[#38,Prioridad-Alta,10,main] prioridad=10 paso=3
[Prioridad-Baja] identidad=Thread[#37,Prioridad-Baja,1,main] prioridad=1 paso=1
[Prioridad-Baja] identidad=Thread[#37,Prioridad-Baja,1,main] prioridad=1 paso=2
[Prioridad-Baja] identidad=Thread[#37,Prioridad-Baja,1,main] prioridad=1 paso=3

==============================
4.2 - Join e interrupcion
==============================
Esperando con join() a que termine el hilo lento...
[Hilo-Lento-Join] ejecutando paso 1/5
[Hilo-Lento-Join] ejecutando paso 2/5
[Hilo-Lento-Join] ejecutando paso 3/5
[Hilo-Lento-Join] ejecutando paso 4/5
[Hilo-Lento-Join] ejecutando paso 5/5
join() finalizo correctamente.
[Hilo-Interrumpible] ejecutando paso 1/10
[Hilo-Interrumpible] ejecutando paso 2/10
[Hilo-Interrumpible] ejecutando paso 3/10
Enviando interrupt() al hilo interrumpible...
[Hilo-Interrumpible] interrumpido mientras estaba en espera.
[Hilo-Interrumpible] finalizando por interrupcion.

Fin de la demostracion.
```

## Observaciones

- La condición de carrera produjo un resultado menor al esperado en esta corrida.
- Las dos soluciones de sincronización devolvieron el valor correcto.
- `join()` bloqueó al hilo principal hasta completar la tarea lenta.
- `interrupt()` provocó la salida controlada del hilo interrumpible mientras dormía.
