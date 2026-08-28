# SEMANA No 1 — DOSW Manejo de Streams

## Datos personales:
- Nombre y Apellido: Juan Diego Valderrama Gaviria
- Curso: DOSW

---

### Ejercicio 01 — Números Pares mayores a diez

Dada una lista de números enteros, obtener una nueva lista solo con los números pares mayores a 10.

**Código implementado:**

```java
package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 01 - Numeros pares mayores a diez.
 * Dada una lista de numeros enteros, obtener una nueva lista solo
 * con los numeros pares mayores a 10.
 * Operador principal: filter()
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        List<Integer> numeros = List.of(3, 8, 10, 12, 15, 18, 20);

        List<Integer> paresMayoresADiez = numeros.stream()
                .filter(n -> n > 10 && n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Entrada: " + numeros);
        System.out.println("Pares mayores a 10: " + paresMayoresADiez);
    }
}
```
<img width="1078" height="236" alt="imagen" src="https://github.com/user-attachments/assets/6604ad01-789b-47a7-aa72-f3fac3f4e743" />


**Explicación:** Se filtra con `filter()` verificando que el número sea mayor a 10 y divisible entre 2 en una sola condición.
### Ejercicio 02 — Cantidad de Palabras con más de 4 caracteres

Filtrar palabras con más de 4 caracteres, convertirlas a mayúsculas, ordenarlas alfabéticamente y obtener la cantidad total.

**Código implementado:**

```java
package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 02 - Cantidad de palabras con mas de 4 caracteres.
 * Filtrar palabras de mas de 4 caracteres, convertirlas a mayusculas,
 * ordenarlas alfabeticamente y contar cuantas resultaron.
 * Operadores principales: filter() - map() - sorted()
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        List<String> palabras = List.of("java", "stream", "api", "functional", "code", "git");

        List<String> resultado = palabras.stream()
                .filter(p -> p.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Entrada: " + palabras);
        System.out.println("Resultado: " + resultado);
        System.out.println("Cantidad de palabras resultantes: " + resultado.size());
    }
}
```
<img width="1071" height="223" alt="imagen" src="https://github.com/user-attachments/assets/f82d36b2-5da3-4ce3-9f69-0c1db24e2b59" />


**Explicación:** Se encadenan `filter()`, `map()` con `String::toUpperCase` y `sorted()`; el tamaño de la lista resultante da la cantidad pedida.
### Ejercicio 03 — Obtener nombres de los Usuarios

Filtrar únicamente los usuarios activos, obtener sus nombres en mayúscula y ordenados alfabéticamente.

**Código implementado:**

```java
package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 03 - Obtener nombres de los Usuarios activos.
 * Filtrar solo los usuarios activos, obtener sus nombres en mayuscula
 * y ordenarlos alfabeticamente.
 * Operadores principales: filter() - map() - sorted()
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1, "Carlos", 22, true),
                new User(2, "Ana", 19, false),
                new User(3, "Beatriz", 30, true),
                new User(4, "David", 17, true)
        );

        List<String> sortedUsers = users.stream()
                .filter(User::isActive)
                .map(u -> u.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Usuarios: " + users);
        System.out.println("Nombres activos ordenados: " + sortedUsers);
    }
}
```
<img width="1064" height="220" alt="imagen" src="https://github.com/user-attachments/assets/852a2cb2-35de-4dbc-982c-53a75189d6db" />


**Explicación:** Se usa `filter(User::isActive)`, luego `map()` para pasar a mayúsculas y `sorted()` para el orden alfabético.
### Ejercicio 04 — Personas mayores de edad

Filtrar las personas mayores de edad (>=18) y obtener sus nombres.

**Código implementado:**

```java
package dosw.semana_1.streams;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Ejercicio 04 - Personas mayores de edad.
 * Filtrar las personas mayores de edad (>= 18) y obtener sus nombres.
 * Operadores principales: filter() - map()
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1, "Carlos", 22, true),
                new User(2, "Ana", 19, false),
                new User(3, "Beatriz", 30, true),
                new User(4, "David", 17, true)
        );

        List<String> mayoresDeEdad = users.stream()
                .filter(u -> u.getAge() >= 18)
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("Usuarios: " + users);
        System.out.println("Mayores de edad: " + mayoresDeEdad);
    }
}
```
<img width="1081" height="256" alt="imagen" src="https://github.com/user-attachments/assets/59050af1-31d3-4aab-b913-989079e711c7" />


**Explicación:** `filter()` valida la edad y `map(User::getName)` extrae el nombre de cada usuario resultante.
### Ejercicio 05 — Transacciones Bancarias

Usar `peek()` para ver cada transacción procesada y `anyMatch()` para verificar si existe alguna no aprobada, retornando si el lote es válido.

**Código implementado:**

```java
package dosw.semana_1.streams;

import java.util.List;

/**
 * Ejercicio 05 - Transacciones bancarias.
 * Usar peek() para ver cada transaccion procesada, verificar si existe
 * al menos una transaccion no aprobada y retornar true/false indicando
 * si el lote es valido.
 * Operadores principales: peek() - anyMatch()
 */
public class Ejercicio5 {

    static class Transaction {
        String id;
        double amount;
        boolean approved;

        Transaction(String id, double amount, boolean approved) {
            this.id = id;
            this.amount = amount;
            this.approved = approved;
        }

        @Override
        public String toString() {
            return "Transaction{id='" + id + "', amount=" + amount + ", approved=" + approved + "}";
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("T1", 150.0, true),
                new Transaction("T2", 320.5, true),
                new Transaction("T3", 80.0, false),
                new Transaction("T4", 500.0, true)
        );

        boolean tieneNoAprobadas = transactions.stream()
                .peek(t -> System.out.println("Procesando: " + t))
                .anyMatch(t -> !t.approved);

        boolean loteValido = !tieneNoAprobadas;

        System.out.println("¿Existe alguna transaccion no aprobada?: " + tieneNoAprobadas);
        System.out.println("¿El lote de transacciones es valido?: " + loteValido);
    }
}
```

<img width="1075" height="365" alt="imagen" src="https://github.com/user-attachments/assets/e35b6845-d62b-4156-ae2d-b296be8a37d0" />

**Explicación:** `peek()` imprime cada transacción a medida que el stream la procesa; `anyMatch()` corta apenas encuentra una transacción no aprobada.


---
