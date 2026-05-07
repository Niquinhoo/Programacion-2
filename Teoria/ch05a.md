---
layout: answer

title: "Chapter FIVE"
subtitle: "Controlling Program Flow"
exam_objectives:
  - "Create program flow control constructs including if/else, switch statements and expressions, loops, and break and continue statements."
  - "Implement inheritance, including abstract and sealed types as well as record classes. Override methods, including that of the Object class. Implement polymorphism and differentiate between object type and reference type. Perform reference type casting, identify object types using the instanceof operator, and pattern matching with the instanceof operator and the switch construct."
---

## Answers
**1. The correct answer is A.**

**Explanation:**

- **A)** `x is between 5 and 20`
  - This option is correct. The value of `x` is 10, which satisfies both conditions in the nested `if` statements (`x > 5` and `x < 20`). Therefore, the program prints `"x is between 5 and 20"`.

- **B)** `x is 5 or less` 
  - This option is incorrect. The value of `x` is 10, which does not satisfy the condition `x <= 5` in the `else` block. Therefore, this message will not be printed.

- **C)** `x is greater than 20`
  - This option is incorrect. The value of `x` is 10, which does not satisfy the condition `x > 20`. Therefore, this message will not be printed.

- **D)** The program does not compile 
  - This option is incorrect. The program compiles successfully without any errors.

- **E)** The program compiles but does not produce any output
  - This option is incorrect. The program produces output because the value of `x` satisfies the conditions within the nested `if` statements, leading to the output `"x is between 5 and 20"`.
  

**2. The correct answer is D.**

**Explanation:**

- **A)** 
```java
if (emp instanceof Employee) {
    var (id, Person(name, age)) = emp;
    System.out.println(name + " is " + age + " years old.");
}
```
  - This option is incorrect. While it attempts to use destructuring, this syntax is not valid in Java. Java doesn't support destructuring assignment in this way.

- **B)** 
```java
if (emp instanceof Employee(_, Person(var name, var age))) {
    System.out.println(name + " is " + age + " years old.");
}
```
  - This option is incorrect. It uses the underscore (`_`) to ignore the `id` field, which is not a valid technique in Java 21.

- **C)**
```java
if (emp instanceof Employee e) {
    System.out.println(e.person().name() + " is " + e.person().age() + " years old.");
}
```
  - This option is incorrect. It uses traditional `instanceof` without pattern matching, relying on accessor methods to extract the data.

- **D)**
```java
if (emp instanceof Employee(var id, Person(var name, var age))) {
    System.out.println(name + " is " + age + " years old.");
}
```
  - This option is correct. It uses nested record pattern matching to extract both the `Employee` and `Person` data in a single step. It uses `var` for type inference and correctly names the variables `name` and `age` as required.

- **E)** 
```java
if (emp instanceof Employee(var id, var person)) {
    System.out.println(person.name() + " is " + person.age() + " years old.");
}
```
  - This option is incorrect. While it uses pattern matching for the `Employee` record, it doesn't nest the pattern matching for the `Person` record, so it still requires calling accessor methods on `person`.



**3. The correct answer is C.**



**Explanation:**

- **A)** Code snippet 1
  - This option is incorrect. The variable `y` is declared inside the first `if` statement and is not accessible outside its block. Therefore, trying to print `y` outside its scope results in a compilation error.

- **B)** Code snippet 2
  - This option is incorrect. The variable `z` is declared inside the second `if` statement and is not accessible outside its block. Therefore, attempting to use `z` outside its scope results in a compilation error.

- **C)** Code snippet 3
  - This option is correct. The variable `a` is declared outside the `if` statement, so it is accessible both inside and outside the `if` block. Reassigning a inside the `if` block is allowed.

- **D)** None of the above
  - This option is incorrect. While it's true that code snippets 1, 2 and 4 will not compile, code snippet 3 does compile without any errors. Therefore, the answer cannot be "none of the above".


**4. The correct answer is C.**

**Explanation:**

- **A)** `Weekend` 
  - This option is incorrect. The value of `dayOfWeek` is 3, which does not match cases 1 or 7, so it does not print `"Weekend"`.

- **B)** `Invalid day`
  - This option is incorrect. The default case is not executed because the value of `dayOfWeek` matches one of the specific cases (2, 3, 4, 5, or 6).

- **C)** `Weekday`
  - This option is correct. The value of `dayOfWeek` is 3, which matches case 3. Therefore, the variable `dayType` is set to `"Weekday"`, and this value is printed.

- **D)** The program does not compile
  - This option is incorrect. The program compiles without errors.

- **E)** The program compiles but does not produce any output
  - This option is incorrect. The program compiles and produces output, which is `"Weekday"` based on the given `dayOfWeek` value.


**5. The correct answer is B.**

**Explanation:**

- **A)** `A`
  - This option is incorrect. The value of `score` is 85, which does not match the cases for 90 or 100. Therefore, it does not print `"A"`.

- **B)** `F`
  - This option is correct. The default case is executed because the value of `score` doesn't match any of the other `case` statements.

- **C)** The program does not compile 
  - This option is incorrect. The program uses a `switch` expression correctly, compiling without errors.

- **D)** `B`
  - This option is incorrect. The value of `score` is 85, which doesn't match the case for 80 or 89.

- **E)** The program compiles but does not produce any output
  - This option is incorrect. The program compiles and produces output, which is `"F"` based on the given `score` value.


**6. The correct answer is A.**

**Explanation:**

- **A)** 
```java
case CarType.SEDAN, CarType.HATCHBACK -> System.out.println("Compact vehicle");
case CarType.SUV -> System.out.println("Large vehicle");
case CarType.CONVERTIBLE -> System.out.println("Open-top vehicle");
```
  - This option is correct. In Java 21, you can use fully qualified names of enum constants in switch statements, even when the selector expression is of a type that's assignment-compatible with the enum type (in this case, `Vehicle` is assignment-compatible with `CarType`).

- **B)** 
```java
case SEDAN, HATCHBACK -> System.out.println("Compact vehicle");
case SUV -> System.out.println("Large vehicle");
case CONVERTIBLE -> System.out.println("Open-top vehicle");
```
  - This option is incorrect. When using an interface type (`Vehicle`) as the selector expression, you must use fully qualified names for the enum constants. Using unqualified names (`SEDAN`, `HATCHBACK`, etc.) will result in a compilation error.

- **C)** 
```java
case CarType.SEDAN || CarType.HATCHBACK -> System.out.println("Compact vehicle");
case CarType.SUV -> System.out.println("Large vehicle");
case CarType.CONVERTIBLE -> System.out.println("Open-top vehicle");
```
  - This option is incorrect. It attempts to use the logical OR operator (`||`) in the case label, which is not valid syntax for switch statements. Multiple case labels should be separated by commas, not logical operators.

- **D)** 
```java
case Vehicle.SEDAN, Vehicle.HATCHBACK -> System.out.println("Compact vehicle");
case Vehicle.SUV -> System.out.println("Large vehicle");
case Vehicle.CONVERTIBLE -> System.out.println("Open-top vehicle");
```
  - This option is incorrect. Although it uses fully qualified names, it incorrectly prefixes the enum constants with `Vehicle` instead of `CarType`. The enum constants belong to the `CarType` enum, not the `Vehicle` interface, so this will result in a compilation error.


**7. The correct answer is D.**

**Explanation:**

- **A)** 
```java
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case null -> 0;
```
  - This option is incorrect. It doesn't compile because the `switch` expression is not exhaustive, it does not cover all possible `Shape` values.

- **B)** 
```java
default -> 0;
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case Triangle t -> 0.5 * t.base() * t.height();
```
   - This option is incorrect. It doesn't compile because the (unnecessary) `default` case comes before the rest of the `case` statements.

- **C)** 
```java
case Shape s when s instanceof Circle ->
        Math.PI * ((Circle)s).radius() * ((Circle)s).radius();
case Shape s when s instanceof Square ->
        ((Square)s).side() * ((Square)s).side();
case Shape s when s instanceof Triangle ->
        0.5 * ((Triangle)s).base() * ((Triangle)s).height();
```
  - This option is incorrect. It doesn't compile because it's not exhaustive. Since it uses verbose `instanceof` checks instead of leveraging pattern matching, it's missing a `default` branch.

- **D)** 
```java
case Circle c -> Math.PI * c.radius() * c.radius();
case Square s -> s.side() * s.side();
case Triangle t -> 0.5 * t.base() * t.height();
``` 
- This option is correct. It covers all possible subtypes of the sealed `Shape` interface without an unnecessary `default` case.


**8. The correct answer is B.**

**Explanation:**

- **A)** `2`
  - This option is incorrect. The value of `count` is incremented until it reaches 3. The labeled `break` statement breaks out of the outer loop when `count` equals 3.

- **B)** `3`
  - This option is correct. The value of `count` is incremented inside the inner `while` loop. When `count` reaches 3, the labeled `break` statement (`break outerLoop`) is executed, causing the control to exit the outer loop. Therefore, `count` is 3 when printed.

- **C)** `4`
  - This option is incorrect. The loop does not continue incrementing `count` to 4 because the labeled `break` statement exits the loop when `count` is 3.

- **D)** `5`
  - This option is incorrect. The loop does not continue incrementing `count` to 5 because the labeled `break` statement exits the loop when `count` is 3.

- **E)** The program does not compile
  - This option is incorrect. The program compiles successfully and runs without errors.


**9. The correct answer is C.**

**Explanation:**

- **A)** `5`
  - This option is incorrect. The value 5 is just the upper limit of the loop and not the sum of the integers from 1 to 5.

- **B)** `10`
  - This option is incorrect. The value 10 is less than the sum of the integers from 1 to 5.

- **C)** `15`
  - This option is correct. The loop iterates from 1 to 5, adding each value of `i` to `sum`. The calculations are as follows: 1 + 2 + 3 + 4 + 5 = 15.

- **D)** `20`
  - This option is incorrect. The value 20 is more than the sum of the integers from 1 to 5.

- **E)** The program does not compile
  - This option is incorrect. The program compiles successfully and runs without errors.


**10. The correct answer is A.**

**Explanation:**

- **A)** `9`
  - This option is correct. The `continue` statement skips the current iteration when the number is even (`num % 2 == 0`). The odd numbers in the array are 1, 3, and 5. Their sum is 1 + 3 + 5 = 9.

- **B)** `10`
  - This option is incorrect. The sum of the odd numbers (1, 3, and 5) is 9, not 10.

- **C)** `12`
  - This option is incorrect. The sum of the odd numbers (1, 3, and 5) is 9, not 12.

- **D)** `15`
  - This option is incorrect. The sum of all the numbers in the array (1 + 2 + 3 + 4 + 5) is 15, but the `continue` statement causes the loop to skip adding the even numbers.

- **E)** The program does not compile
  - This option is incorrect. The program compiles successfully and runs without errors.

