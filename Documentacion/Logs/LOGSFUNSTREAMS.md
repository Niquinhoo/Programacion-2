# Logs Funcional y Streams

## Compilacion

```powershell
javac src\*.java
```

## Ejecucion

```powershell
java -cp src App
```

## Salida de consola

```text
=== Ejercicio 1.1 - Interfaces funcionales base ===
Predicate test(8): true
Function apply("Streams"): 7
Consumer recibio: 42
Supplier get(): 0.2921970232828406

=== Ejercicio 1.2 - Ordenacion con Comparator ===
Textos ordenados por longitud: [sol, java, lambda, stream, programacion]

=== Ejercicio 1.3 - Variantes primitivas ===
IntPredicate test(11): false
ToIntFunction applyAsInt("Funcional"): 9

=== Ejercicio 2.1 - Creacion y extraccion ===
Cantidad total luego de distinct, skip y limit: 3

=== Ejercicio 2.2 - Filtrado y procesamiento intermedio ===
Cantidad de numeros mayores a 3: 4
peek -> 4
peek -> 5
peek -> 6
peek -> 7

=== Ejercicio 3.1 - Transformacion con map ===
Nombres en mayusculas: [JUAN, MARIA, ANA]

=== Ejercicio 3.2 - mapToInt ===
Suma total de letras: 12

=== Ejercicio 3.3 - Aplanamiento con flatMap ===
Cantidad de nombres con mas de 4 caracteres: 3

=== Ejercicio 3.4 - Ordenacion ===
Orden natural:
 - arbol
 - casa
 - computadora
 - luz
 - zorro
Orden por longitud:
 - luz
 - casa
 - zorro
 - arbol
 - computadora

=== Ejercicio 4.1 - Comprobaciones logicas ===
anyMatch(n > 5): true
allMatch(n > 1): true
noneMatch(n > 10): true

=== Ejercicio 4.2 - Optional y findFirst ===
Se encontro un valor?: true
Primer numero par encontrado: 8

=== Ejercicio 4.3 - Reduccion ===
Resultado de reduce: 20

=== Ejercicio 4.4 - Reduccion a coleccion ===
Lista sin duplicados: [4, 2, 7, 9, 1]
Mapa DNI -> Nombre: {30111222=Lucia, 33444555=Carla, 28999888=Martin}
```

## Nota

El valor mostrado por `Supplier get()` cambia en cada ejecucion porque depende de `Math.random()`.
