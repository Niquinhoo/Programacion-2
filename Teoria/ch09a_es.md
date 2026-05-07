-"None"
layout: answer

title: "Capítulo NUEVE"
subtitle: "Corrientes"
exam_objectives:
  - "Utilice objetos Java y flujos primitivos, incluidas expresiones lambda que implementan interfaces funcionales, para crear, filtrar, transformar, procesar y ordenar datos."
  - "Realizar descomposición, concatenación y reducción, así como agrupación y partición en flujos secuenciales y paralelos."
-"None"## Respuestas
**1. La respuesta correcta es C.**

**Explicación:**

- **A)** `Opcional<Cadena> opcional = nuevo Opcional<>(valor);`
  - Esta opción es incorrecta porque "Opcional" no tiene un constructor público. En su lugar, se deben utilizar métodos de fábrica estáticos como "of" y "ofNullable".

- **B)** `Opcional<Cadena> opcional = Opcional.de(valor);`
  - Esta opción es incorrecta porque `Optional.of(value)` genera una `NullPointerException` si `value` es `null`. En este escenario, dado que `getValue()` puede devolver `null`, esta línea podría generar una excepción.

- **C)** `Opcional<Cadena> opcional = Opcional.ofNullable(valor);`
  - Esta opción es correcta porque `Optional.ofNullable(valor)` devolverá un `Opcional` que describe el valor especificado si no es nulo, o un `Opcional` vacío si el valor es `nulo`. Ésta es la forma adecuada de manejar un valor potencialmente "nulo".

- **D)** `Opcional<Cadena> opcional = Opcional.vacío(valor);`
  - Esta opción es incorrecta porque `Optional.empty()` no acepta ningún argumento. Simplemente devuelve un "Opcional" vacío.

- **E)** `Opcional<Cadena> opcional = Opcional.nullable(valor);`
  - Esta opción es incorrecta porque no hay ningún método "nullable" en la clase "Opcional". El método correcto para este propósito es "ofNullable". 


**2. La respuesta correcta es E.**

**Explicación:**

- **A)** `stream.filter(s -> s.contains("A"));` 
  - Esta opción es incorrecta porque "filtrar" es una operación intermedia. Devuelve una nueva secuencia con elementos que coinciden con el predicado dado.

- **B)** `stream.map(String::toLowerCase);`
  - Esta opción es incorrecta porque `mapa` es una operación intermedia. Devuelve una nueva secuencia con elementos que son el resultado de aplicar la función dada.

- **C)** `corriente.distinct();`
  - Esta opción es incorrecta porque "distinta" es una operación intermedia. Devuelve una nueva secuencia con elementos distintos.

- **D)** `stream.limit(2);`
  - Esta opción es incorrecta porque "límite" es una operación intermedia. Devuelve una nueva secuencia que se trunca para que no supere el tamaño especificado.

- **E)** `stream.collect(Collectors.toList());`
  - Esta opción es correcta porque `recopilar` es una operación de terminal. Activa el procesamiento de la secuencia y recopila los elementos en una "Lista".


**3. La respuesta correcta es D.**

**Explicación:**

- **A)** `int suma = números.stream().sum();` 
  - Esta opción es incorrecta porque las matrices no tienen un método `stream` directamente sobre ellas. Debe utilizar un método de una clase de utilidad como `IntStream` para crear una secuencia.

- **B)** `int suma = IntStream.range(0, números.longitud).sum();` 
  - Esta opción es incorrecta porque `IntStream.range(0, números.length)` genera un flujo de números enteros desde 0 hasta la longitud de la matriz, no los elementos de la matriz en sí.

- **C)** `int suma = IntStream.from(números).sum();`
  - Esta opción es incorrecta porque `IntStream` no tiene un método `from`. El método correcto es "de".

- **D)** `int suma = IntStream.of(números).sum();`
  - Esta opción es correcta porque `IntStream.of(numbers).sum()` crea correctamente un `IntStream` a partir de la matriz y calcula la suma de sus elementos.

- **E)** `int suma = IntStream.range(números).sum();`
  - Esta opción es incorrecta porque `IntStream.range` requiere dos argumentos (un índice inicial y uno final) y se usa para generar una secuencia de números dentro de un rango, no para sumar una matriz.


**4. La respuesta correcta es A.**

**Explicación:**

- **A)** `Stream<String> filteredStream = stream.filter(s -> s.length() > 3);`  
  - Esta opción es correcta porque `filtro` es la operación intermedia correcta para aplicar un predicado a cada elemento de la secuencia y devolver una nueva secuencia que contenga solo elementos que coincidan con el predicado.

- **B)** `Stream<String> filteredStream = stream.map(s -> s.length() > 3);` 
  - Esta opción es incorrecta porque `mapa` se usa para transformar elementos del flujo y no los filtra. El resultado sería una secuencia de valores "booleanos" en lugar de las cadenas originales..

- **C)** `Stream<String> filteredStream = stream.collect(Collectors.filtering(s -> s.length() > 3));` 
  - Esta opción es incorrecta porque `Collectors.filtering` no es un método válido. El filtrado se realiza mediante el método "filter" en la transmisión misma, no a través de recopiladores.

- **D)** `Stream<String> filteredStream = stream.filtering(s -> s.length() > 3);` 
  - Esta opción es incorrecta porque no existe un método de "filtrado" en la transmisión. El método correcto es "filtrar".

- **E)** `Stream<String> filteredStream = stream.filterByLength(3);`
  - Esta opción es incorrecta porque no hay ningún método `filterByLength` en la transmisión. El método correcto a utilizar es "filtro".


**5. La respuesta correcta es C.**

**Explicación:**

- **A)** `Stream<String> lengthStream = stream.map(s -> s.length());`
  - Esta opción es incorrecta porque el método `map` transformará los elementos a `Integer`, no a `String`. El tipo correcto para la secuencia resultante debe ser `Stream<Integer>`.

- **B)** `Stream<String> lengthStream = stream.mapToInt(s -> s.length());`
  - Esta opción es incorrecta porque `mapToInt` produce un `IntStream`, no un `Stream<String>`. Además, el tipo de secuencia resultante no sería `Stream<String>`.

- **C)** `Stream<Integer> lengthStream = stream.map(s -> s.length());`
  - Esta opción es correcta porque `map` transforma cada cadena en la secuencia a su longitud, lo que da como resultado una `Stream<Integer>`.

- **D)** `IntStream lengthStream = stream.map(s -> s.length());` 
  - Esta opción es incorrecta porque `map` produce un `Stream<R>`, no un `IntStream`. El método correcto para producir un `IntStream` sería `mapToInt`.

- **E)** `Stream<String> lengthStream = stream.flatMap(s -> Stream.of(s.length()));`
  - Esta opción es incorrecta porque `flatMap` se usa para aplanar secuencias anidadas y no simplemente asignarlas a otro tipo. Además, el tipo de secuencia resultante no sería `Stream<String>`.


**6. La respuesta correcta es A.**

**Explicación:**

- **A)** `Stream<String> resultStream = stream.skip(2).limit(3);`
  - Esta opción es correcta porque `skip(2)` omite los primeros 2 elementos de la secuencia y `limit(3)` limita la secuencia a los siguientes 3 elementos. Por lo tanto, la secuencia resultante contendrá los elementos tercero, cuarto y quinto de la lista original.

- **B)** `Stream<String> resultStream = stream.limit(3).skip(2);`
  - Esta opción es incorrecta porque `limit(3)` primero limita la secuencia a los primeros 3 elementos y luego `skip(2)` omite 2 de esos elementos, lo que da como resultado una secuencia con solo el tercer elemento.

- **C)** `Stream<String> resultStream = stream.skip(3).limit(2);`
  - Esta opción es incorrecta porque `skip(3)` omite los primeros 3 elementos y `limit(2)` luego limita la secuencia a los siguientes 2 elementos, lo que da como resultado una secuencia con el cuarto y quinto elemento.

- **D)** `Stream<String> resultStream = stream.limit(2).skip(3);`
  - Esta opción es incorrecta porque `limit(2)` primero limita la secuencia a los primeros 2 elementos, y luego `skip(3)` intentaría omitir más elementos de los disponibles, lo que resultaría en una secuencia vacía.

- **E)** `Stream<String> resultStream = stream.slice(2, 5);`
  - Esta opción es incorrecta porque no existe un método "slice" en Stream API. Los métodos correctos para lograr el resultado deseado son "omitir" y "limitar".


**7. La respuesta correcta es B.**

**Explicación:**

- **A)** `Stream<String> resultStream = Stream.concat(stream1, stream2.collect(Collectors.toList()));`
  - Esta opción es incorrecta porque `Stream.concat` espera dos transmisiones como argumentos. `stream2.collect(Collectors.toList())` convierte `stream2` en una `Lista`, no en un `Stream`.

- **B)** `Stream<String> resultStream = Stream.concat(stream1, stream2);`
  - Esta opción es correcta porque `Stream.concat(stream1, stream2)` concatena correctamente las dos secuencias en una única secuencia que contiene todos los elementos de ambas secuencias.

- **C)** `Stream<String> resultStream = stream1.concat(stream2);`
  - Esta opción es incorrecta porque `Stream` no tiene una instanciamétodo `concat`. El método `concat` es un método estático de la clase `Stream`.

- **D)** `Stream<String> resultStream = stream1.merge(stream2);`
  - Esta opción es incorrecta porque no existe un método "merge" en la API "Stream". El método correcto para concatenar transmisiones es `Stream.concat`.

- **E)** `Stream<String> resultStream = Stream.of(stream1, stream2);`
  - Esta opción es incorrecta porque `Stream.of(stream1, stream2)` crea un flujo de flujos, lo que da como resultado `Stream<Stream<String>>` en lugar de un único `Stream<String>` concatenado.


**8. La respuesta correcta es E.**

**Explicación:**

- **A)** `int producto = stream.reduce(1, (a, b) -> a + b);`
  - Esta opción es incorrecta porque la operación de reducción utiliza suma en lugar de multiplicación. La operación correcta para calcular el producto debe ser `(a, b) -> a * b`.

- **B)** `int producto = stream.reduce((a, b) -> a * b);`
  - Esta opción es incorrecta porque no proporciona un valor de identidad, que es necesario para la operación de reducción cuando se trata de un flujo vacío. Sin un valor de identidad, el resultado es un "Opcional<Integer>" en lugar de un "int".

- **C)** `int producto = stream.reduce(0, (a, b) -> a * b);`
  - Esta opción es incorrecta porque el valor de identidad para la multiplicación debe ser "1", no "0". Usar "0" como valor de identidad daría como resultado un producto de "0" independientemente de los elementos de la secuencia.

- **D)** `Opcional<Integer> producto = stream.reduce(1, (a, b) -> a * b);`
  - Esta opción es incorrecta porque el uso correcto del método `reduce` con un valor de identidad no devuelve un `Opcional`. Debería devolver el resultado directamente como "int".

- **E)** `int producto = stream.reduce(1, (a, b) -> a * b, (a, b) -> a * b);`
  - Esta opción es correcta porque utiliza correctamente el método `reduce` con un valor de identidad de `1` y una función combinadora que multiplica los resultados. Esta forma de "reducción" también es adecuada para el procesamiento paralelo, lo que garantiza que el producto se calcule correctamente en múltiples segmentos del flujo.


**9. La respuesta correcta es B.**

**Explicación:**

- **A)** `Establecer<Cadena> resultadoSet = stream.collect(Collectors.toSet());` 
  - Esta opción es incorrecta porque `Collectors.toSet()` no garantiza el orden de los elementos. La implementación devuelta por este recopilador no conserva el orden de inserción.

- **B)** `Establecer<String> resultSet = stream.collect(Collectors.toCollection(LinkedHashSet::new));`
  - Esta opción es correcta porque `Collectors.toCollection(LinkedHashSet::new)` recopila los elementos en un `LinkedHashSet`, que mantiene el orden de inserción.

- **C)** `Establecer<Cadena> resultSet = stream.collect(Collectors.toCollection(TreeSet::new));` 
  - Esta opción es incorrecta porque `TreeSet` ordena los elementos según su orden natural (o mediante un comparador, si se proporciona). Esto no necesariamente preserva el orden original de los elementos de la secuencia.

- **D)** `Establecer<Cadena> conjunto de resultados = stream.collect(Collectors.toList());` 
  - Esta opción es incorrecta porque `Collectors.toList()` recopila los elementos en una `Lista`, no en un `Conjunto`.

- **E)** `Establecer<Cadena> conjunto de resultados = stream.collect(Collectors.toMap());`
  - Esta opción es incorrecta porque `Collectors.toMap()` se usa para recopilar los elementos en un `Map`, no en un `Set`.