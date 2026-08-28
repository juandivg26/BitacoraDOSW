package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * #19 - Top 3 Entrenadores.
 * Ranking de los 3 mejores entrenadores: 1) mas medallas,
 * 2) mayor poder acumulado, 3) orden alfabetico como desempate.
 * Operadores principales: sorted() + limit(3)
 */
public class Ejercicio19 {

    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Gary", 10, List.of(
                        new Pokemon(1L, "Blastoise", "Agua", 58, 1200, "Kanto", false),
                        new Pokemon(2L, "Nidoking", "Veneno", 50, 1140, "Kanto", false))),
                new Entrenador(2L, "Ash", 8, List.of(
                        new Pokemon(3L, "Pikachu", "Electrico", 45, 900, "Kanto", false),
                        new Pokemon(4L, "Charizard", "Fuego", 60, 950, "Kanto", false))),
                new Entrenador(3L, "Dawn", 7, List.of(
                        new Pokemon(5L, "Piplup", "Agua", 40, 1000, "Sinnoh", false),
                        new Pokemon(6L, "Buneary", "Normal", 38, 1100, "Sinnoh", false))),
                new Entrenador(4L, "Brock", 6, List.of(
                        new Pokemon(7L, "Onix", "Roca", 42, 870, "Kanto", false),
                        new Pokemon(8L, "Geodude", "Roca", 35, 800, "Kanto", false)))
        );

        Comparator<Entrenador> ranking = Comparator
                .comparingInt(Entrenador::getMedallas).reversed()
                .thenComparing((Entrenador e) -> e.getEquipo().stream()
                        .mapToDouble(Pokemon::getPoderCombate)
                        .sum(), Comparator.reverseOrder())
                .thenComparing(Entrenador::getNombre);

        List<Entrenador> top3 = entrenadores.stream()
                .sorted(ranking)
                .limit(3)
                .collect(Collectors.toList());

        IntStream.range(0, top3.size())
                .forEach(i -> {
                    Entrenador e = top3.get(i);
                    double poder = e.getEquipo().stream()
                            .mapToDouble(Pokemon::getPoderCombate)
                            .sum();
                    System.out.println("#" + (i + 1) + " " + e.getNombre()
                            + " -- " + e.getMedallas() + " medallas, PC: " + (int) poder);
                });
    }
}
