package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * #18 - Top 5 Pokemon Mas Fuertes.
 * Generar un ranking de los cinco Pokemon con mayor poderCombate
 * de toda la Pokedex.
 * Operadores principales: sorted() + limit(5)
 */
public class Ejercicio18 {

    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 88, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Gengar", "Fantasma", 40, 495, "Kanto", false),
                new Pokemon(5L, "Charizard", "Fuego", 60, 610, "Kanto", false),
                new Pokemon(6L, "Squirtle", "Agua", 20, 210, "Kanto", false)
        );

        List<Pokemon> top5 = pokedex.stream()
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .limit(5)
                .collect(Collectors.toList());

        IntStream.range(0, top5.size())
                .forEach(i -> System.out.println(
                        "#" + (i + 1) + " " + top5.get(i).getNombre()
                                + " -- PC: " + (int) top5.get(i).getPoderCombate()));
    }
}
