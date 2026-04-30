# Conclusión: Análisis de Implementación - Guía 1

**Fecha de revisión:** 2026-04-30  
**Archivo de referencia:** `Documentacion/Ejercicio/GuiaColecciones.md`  
**Código evaluado:** `Guia-Codigo/Ejercicio 1/src/`

---

## Resultado General

La implementación está completa y correcta. Los 10 ejercicios de la guía están cubiertos en el código, y el ejemplo de `Comparator` fue ajustado para que la diferencia con `Comparable` se vea claramente en la salida de consola.

---

## Detalle por Sección

### Sección 1: Matrices (Arrays)

| Ejercicio | Requisito | Estado |
|-----------|-----------|--------|
| **1.1** | Array de 5 enteros con `new`, asignación por índice, actualizar tercer elemento, recuperar último con `length` | Completo |
| **1.2** | Array de Strings desordenado, `Arrays.sort()`, `Arrays.binarySearch()` | Completo |

### Sección 2: Listas

| Ejercicio | Requisito | Estado |
|-----------|-----------|--------|
| **2.1** | `ArrayList`, `add()` x3, `set()` en segundo elemento, `get()` para primero, `remove()` para último | Completo |
| **2.2** | `List.of()` inmutable -> lanzar `UnsupportedOperationException`, pasar a `LinkedList`, insertar en el medio | Completo |

### Sección 3: Conjuntos (Sets) y Deque

| Ejercicio | Requisito | Estado |
|-----------|-----------|--------|
| **3.1** | `HashSet`, agregar mismo número 3 veces, verificar `size()`, `contains()`, `remove()`, sin orden garantizado | Completo |
| **3.2** | `ArrayDeque`, `addFirst()`, `addLast()`, `removeFirst()`, `removeLast()` | Completo |

### Sección 4: Mapas (Maps)

| Ejercicio | Requisito | Estado |
|-----------|-----------|--------|
| **4.1** | `HashMap<Integer, String>`, `put()`, `get()`, `replace()`, `remove()` por clave | Completo |
| **4.2** | `TreeMap` con mismas operaciones, iterar `keySet()` observando orden ascendente natural | Completo |

### Sección 5: Ordenamiento Avanzado

| Ejercicio | Requisito | Estado |
|-----------|-----------|--------|
| **5.1** | Clase `Empleado` con `Comparable`, `compareTo()` por edad, `Collections.sort()` | Completo |
| **5.2** | Clase separada `EmpleadoPorNombreComparator` con `Comparator`, orden alfabético por nombre, `Collections.sort()` con instancia del comparador | Completo |

---

## Observaciones de Calidad

El código cumple los requisitos de la guía y mejora la claridad del ejemplo de ordenamiento:

- `EmpleadoPorNombreComparator` ahora se aprecia de forma distinta respecto del orden natural por edad.
- La clase `App` mantiene cada ejercicio encapsulado en un método independiente.
- La salida por consola sigue una secuencia ordenada y fácil de leer.
- El proyecto conserva Javadoc en las clases principales.

---

## Archivos Evaluados

| Archivo | Rol |
|---------|-----|
| `App.java` | Clase principal con todos los ejercicios (1.1 al 5.2) |
| `Empleado.java` | Clase con orden natural via `Comparable` |
| `EmpleadoPorNombreComparator.java` | Comparador personalizado por nombre (ejercicio 5.2) |
