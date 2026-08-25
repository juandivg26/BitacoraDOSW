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
