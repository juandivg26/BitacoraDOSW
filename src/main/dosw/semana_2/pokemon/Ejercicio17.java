package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * #17 - Equipo Mas Poderoso.
 * Calcular cual entrenador tiene la suma total de poderCombate mas alta
 * entre todos sus Pokemon.
 * Operadores principales: mapToDouble() + sum()
 */
public class Ejercicio17 {

    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of(
                        new Pokemon(1L, "Pikachu", "Electrico", 45, 900, "Kanto", false),
                        new Pokemon(2L, "Charizard", "Fuego", 60, 950, "Kanto", false))),
                new Entrenador(2L, "Gary", 10, List.of(
                        new Pokemon(3L, "Blastoise", "Agua", 58, 1200, "Kanto", false),
                        new Pokemon(4L, "Nidoking", "Veneno", 50, 1140, "Kanto", false))),
                new Entrenador(3L, "Brock", 6, List.of(
                        new Pokemon(5L, "Onix", "Roca", 42, 870, "Kanto", false),
                        new Pokemon(6L, "Geodude", "Roca", 35, 800, "Kanto", false)))
        );

        Optional<Entrenador> masPoderoso = entrenadores.stream()
                .max(Comparator.comparingDouble(e -> e.getEquipo().stream()
                        .mapToDouble(Pokemon::getPoderCombate)
                        .sum()));

        masPoderoso.ifPresent(e -> {
            double poderAcumulado = e.getEquipo().stream()
                    .mapToDouble(Pokemon::getPoderCombate)
                    .sum();
            System.out.println("Entrenador mas poderoso: " + e.getNombre());
            System.out.println("Poder acumulado del equipo: " + poderAcumulado);
        });
    }
}
