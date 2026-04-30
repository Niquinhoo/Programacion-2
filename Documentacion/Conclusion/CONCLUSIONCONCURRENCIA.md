# Conclusión: Concurrencia y Multitarea

La guía permitió ver en práctica la diferencia entre crear hilos extendiendo `Thread` y reutilizando una tarea con `Runnable`. También quedó claro que, cuando varios hilos comparten un recurso mutable sin control, el resultado puede ser inconsistente aunque el código parezca simple.

La condición de carrera del contador mostró por qué una operación aparentemente elemental como `valor++` no es segura en concurrencia. A partir de eso, la sincronización con `synchronized` en un método y luego con un bloque sincronizado resolvió el problema al proteger la sección crítica.

Además, el uso de `Thread.currentThread()`, las prioridades, `join()` e `interrupt()` ayudó a entender el ciclo de vida del hilo y cómo coordinar tareas concurrentes sin perder el control del flujo principal.

En conjunto, la actividad dejó la idea central de que concurrencia no es solo ejecutar cosas "al mismo tiempo", sino hacerlo con reglas claras para evitar interferencias, resultados erróneos y bloqueos innecesarios.
