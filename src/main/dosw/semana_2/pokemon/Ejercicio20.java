package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * #20 - Pokedex Analitica.
 * Construir una estructura que muestre: cantidad de Pokemon por tipo,
 * por region, cantidad de legendarios, promedio de nivel y el Pokemon
 * mas fuerte. Todo usando unicamente Streams.
 * Operadores principales: groupingBy() + counting()
 */
public class Ejercicio20 {

    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 88, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", true),
                new Pokemon(4L, "Charizard", "Fuego", 60, 610, "Kanto", false),
                new Pokemon(5L, "Vulpix", "Fuego", 18, 220, "Kanto", false),
                new Pokemon(6L, "Torchic", "Fuego", 22, 260, "Hoenn", false),
                new Pokemon(7L, "Piplup", "Agua", 21, 250, "Sinnoh", false)
        );

        Map<String, Long> porTipo = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getTipo, Collectors.counting()));

        Map<String, Long> porRegion = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getRegion, Collectors.counting()));

        long legendarios = pokedex.stream()
                .filter(Pokemon::isLegendario)
                .count();

        double promedioNivel = pokedex.stream()
                .mapToInt(Pokemon::getNivel)
                .average()
                .orElse(0);

        Pokemon masFuerte = pokedex.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate))
                .orElseThrow();

        System.out.println("Por tipo: " + porTipo);
        System.out.println("Por region: " + porRegion);
        System.out.println("Legendarios: " + legendarios);
        System.out.printf("Promedio niv: %.1f%n", promedioNivel);
        System.out.println("Mas fuerte: " + masFuerte.getNombre() + " (PC: " + (int) masFuerte.getPoderCombate() + ")");
    }
}
