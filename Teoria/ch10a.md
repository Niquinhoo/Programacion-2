---
layout: answer

title: "Chapter TEN"
subtitle: "Concurrency and Multithreading"
exam_objectives:
  - "Create both platform and virtual threads. Use both Runnable and Callable objects, manage the thread lifecycle, and use different Executor services and concurrent API to run tasks."
  - "Develop thread-safe code, using locking mechanisms and concurrent API."
  - "Process Java collections concurrently and utilize parallel streams."
---

## Answers
**1. The correct answer is C.**

**Explanation:**

- **A)** `Thread thread = Thread.ofVirtual(); thread.start(task);`
  - This option is incorrect because `Thread.ofVirtual()` returns a `Thread.Builder`, not a `Thread`. The `start()` method on `Thread.Builder` takes a `Runnable`, but this syntax is incorrect.

- **B)** `Thread thread = Thread.ofVirtual().unstarted(task).run();`
  - This option is incorrect because calling `run()` directly does not start a new thread. It executes the task in the current thread.

- **C)** `Thread thread = Thread.ofVirtual().start(task);`
  - This option is correct. It uses the new thread builder API in Java 21 to create and start a virtual thread in one step.

- **D)** `Thread thread = Thread.ofVirtual(); task.run();`
  - This option is incorrect because it doesn't actually start a new thread. It creates a thread builder but doesn't use it, and then runs the task in the current thread.

- **E)** `Thread thread = Thread.start(task);`
  - This option is incorrect because `Thread.start(task)` is not a valid static method in Java 21.


**2. The correct answer is B.**

**Explanation:**
- **A)** `synchronized (this) { counter++; }`
  - This option is incorrect because `this` cannot be used in a static context. In the `main` method, `this` is not available. For a static field like `counter`, you need to synchronize on a static object or class.

- **B)** `synchronized (Main.class) { counter++; }`
  - This option is correct because synchronizing on `Main.class` ensures that only one thread can enter the synchronized block at a time for all instances of `Main`, which is appropriate for protecting static fields like `counter`.

- **C)** `synchronized (task) { counter++; }`
  - This option is incorrect because `task` is a `Runnable` object, and synchronizing on it does not effectively control access to the shared static field `counter`.

- **D)** `synchronized (counter) { counter++; }`
  - This option is incorrect because `counter` is a primitive type (`int`), and you cannot synchronize on a primitive type. Synchronization requires an object.

- **E)** `synchronized (System.out) { counter++; }`
  - This option is incorrect because synchronizing on `System.out` is not related to controlling access to `counter`. It would also interfere with other potential uses of `System.out`.


**3. The correct answers are B and C.**   

**Explanation:**

- **A)** `AtomicInteger` is part of the `java.util.concurrent.atomic` package, but it does not provide atomic operations for increment and decrement.
  - This statement is incorrect. `AtomicInteger` provides atomic operations for increment and decrement, such as `incrementAndGet()` and `decrementAndGet()`.

- **B)** `AtomicReference` can only be used with reference types, not primitive types.
  - This statement is correct. `AtomicReference` is designed to work with reference types and cannot be used with primitive types directly.

- **C)** `AtomicLong` supports atomic operations on `long` values, including `getAndIncrement()` and `compareAndSet()` methods. 
  - This statement is correct. `AtomicLong` provides atomic operations on `long` values, including `getAndIncrement()` and `compareAndSet()` methods.

- **D)** `AtomicBoolean` can be used to perform atomic arithmetic operations on `boolean` values.
  - This statement is incorrect. `AtomicBoolean` is used for atomic updates to `boolean` values, but it does not support atomic arithmetic operations.



**4. The correct answer is A.**

**Explanation:**

- **A)** 
```java
lock.lock();
try {
    count++;
} finally {
    lock.unlock();
}
```
  - This option correctly acquires the lock before modifying the shared resource and ensures the lock is released in the `finally` block, which is the proper use of the `Lock` interface.

- **B)** 
```java
lock.lock();
count++;
lock.unlock();
```
  - This option is incorrect because if an exception occurs between `lock.lock()` and `lock.unlock()`, the lock will not be released, potentially causing a deadlock.

- **C)** 
```java
try {
    lock.lock(() -> {
        count++;
    });
} finally {
    lock.unlock();
}
```
  - This option is incorrect because that's not a valid `lock.lock()` call.

- **D)** 
```java
synchronized(lock) {
    count++;
}
```
  - This option is incorrect because the `synchronized` block is used with the `lock` object itself, which is not the correct usage of the `Lock` interface and does not provide the intended functionality.



**5. The correct answer is A.**

**Explanation:**

- **A)** 
```java
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> System.out.println("Task executed"));
}
```
  - This option is correct. It uses the `try-with-resources` block with the new `Executors.newVirtualThreadPerTaskExecutor()` method introduced in Java 21. The `ExecutorService` will be automatically closed when the `try` block exits, eliminating the need for explicit shutdown calls.

- **B)** 
```java
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> System.out.println("Task executed"));
} finally {
    executor.shutdown();
}
```
  - This option is incorrect because it unnecessarily calls `shutdown()` in the finally block. With `try-with-resources`, the `ExecutorService` is automatically closed, making the explicit `shutdown()` call redundant and potentially harmful.

- **C)** 
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
try {
    executor.submit(() -> System.out.println("Task executed"));
} finally {
    executor.close();
}
```
  - This option is incorrect because it doesn't use `the try-with-resources` syntax. While it does correctly close the `ExecutorService`, it doesn't take advantage of the automatic resource management provided by `try-with-resources`.

- **D)** 
```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> System.out.println("Task executed"));
    executor.awaitTermination(1, TimeUnit.SECONDS);
}
```
  - This option is incorrect because it unnecessarily calls `awaitTermination()`. In a `try-with-resources` block, the `ExecutorService` is automatically closed when the block exits, making the explicit wait for termination unnecessary and potentially causing the thread to block for 1 second.



**6. The correct answer is D.**

**Explanation:**

- **A)** 
```java
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    Future<Integer> future = executor.submit(task);
    System.out.println(future.get());
}
```
  - This option is incorrect because it doesn't handle the potential `InterruptedException` and `ExecutionException` that `future.get()` can throw.

- **B)**
```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    Future<Integer> future = executor.submit(task);
    Integer result = future.get(1, TimeUnit.SECONDS);
    System.out.println(result);
}
```
  - This option is incorrect because it doesn't handle the potential exceptions (`InterruptedException`, `ExecutionException`, and `TimeoutException`) that `future.get(long, TimeUnit)` can throw.

- **C)**
```java
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    Future<Integer> future = executor.submit(task);
    executor.shutdown();
    System.out.println(future.get());
}
```
  - This option is incorrect because it unnecessarily calls `executor.shutdown()`. In a `try-with-resources` block, the `ExecutorService` is automatically closed when the block exits. Also, it doesn't handle the potential exceptions from `future.get()`.

- **D)**
```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    Future<Integer> future = executor.submit(task);
    try {
        Integer result = future.get();
        System.out.println(result);
    } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
    }
}
```
  - This option is correct. It uses `try-with-resources` to automatically close the `ExecutorService`, properly submits the `Callable` task, retrieves the result using `Future.get()`, and handles the potential `InterruptedException` and `ExecutionException` that might be thrown.



**7. The correct answer is A.**

**Explanation:**

- **A)** `ConcurrentHashMap` allows concurrent read and write operations, and retrieval operations do not block even when updates are being made.
  - This statement is correct. `ConcurrentHashMap` is designed to handle concurrent access, allowing multiple threads to read and write simultaneously without blocking read operations during updates.

- **B)** `CopyOnWriteArrayList` is optimized for scenarios with a high number of write operations compared to read operations. 
  - This statement is incorrect. `CopyOnWriteArrayList` is optimized for scenarios where read operations are far more frequent than write operations because it creates a new copy of the array on each write, which can be costly if writes are frequent.

- **C)** `ConcurrentSkipListSet` does not kept elements sorted.
  - This statement is incorrect. `ConcurrentSkipListSet` keep elements according to their natural ordering, or by a `Comparator` provided at set creation time.

- **D)** `BlockingQueue` implementations like `LinkedBlockingQueue` allow elements to be added and removed concurrently without any internal locking mechanisms.
  - This statement is incorrect. `BlockingQueue` implementations like `LinkedBlockingQueue` do use internal locking mechanisms to handle concurrent access safely.



**8. The correct answer is B.**

**Explanation:**

- **A)** Parallel streams always improve the performance of a program by utilizing multiple threads.
  - This statement is incorrect because parallel streams do not always improve performance. The overhead of managing multiple threads can sometimes outweigh the benefits, especially for small datasets or simple operations.

- **B)** Parallel streams can lead to incorrect results if the operations performed are not thread-safe.
  - This statement is correct. When using parallel streams, care must be taken to ensure that the operations performed on the elements are thread-safe. Failure to do so can lead to race conditions and incorrect results.

- **C)** The order of elements in a parallel stream is always preserved compared to the original stream.
  - This statement is incorrect. The order of elements in a parallel stream is not guaranteed to be the same as in the original stream unless special care is taken to preserve the order, such as using ordered stream operations.

- **D)** Using parallel streams guarantees that the operations on elements will execute in a fixed order.
  - This statement is incorrect because parallel streams do not guarantee the order of execution of operations on elements. The operations may execute in a non-deterministic order due to the concurrent nature of parallel processing.



**9. The correct answer is B.**

**Explanation:**

- **A)** 
```java
int sum = numbers.parallelStream().reduce(1, Integer::sum);
System.out.println(sum);
```
  - This option is incorrect because it uses `1` as the identity value. The identity value for sum should be `0`, as it is the neutral element for addition. Starting the reduction with `1` will result in an incorrect sum that is incremented by `1`.

- **B)** 
```java
int sum = numbers.parallelStream().reduce(0, Integer::sum).collect();
System.out.println(sum);
```
  - This option is correct. It correctly uses `parallelStream()` to create a parallel stream and the `reduce` method with the identity value `0` and the method reference `Integer::sum` to sum the elements.

- **C)** 
```java
int sum = numbers.stream().reduce(0, Integer::sum);
System.out.println(sum);
```
  - This option is incorrect because it uses a sequential stream (`stream()`) instead of a parallel stream. While it correctly sums the elements, it does not demonstrate the use of a parallel stream as specified in the question.

- **D)** 
```java
int sum = numbers.parallelStream().collect(reduce(0, Integer::sum));
System.out.println(sum);
```
  - This option is incorrect because it attempts to use the `collect()` method in combination with `reduce()`, which is not the correct syntax. The `collect()` method is used for mutable reduction and is typically used with collectors, not with the `reduce()` operation directly.