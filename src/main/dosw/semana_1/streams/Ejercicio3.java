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
