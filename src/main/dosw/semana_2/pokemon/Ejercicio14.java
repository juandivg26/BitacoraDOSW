package dosw.semana_2.pokemon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * #14 - Organizar por Region.
 * Agrupar los Pokemon segun su region de origen.
 * Operador principal: groupingBy()
 */
public class Ejercicio14 {

    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Chikorita", "Planta", 20, 240, "Johto", false),
                new Pokemon(3L, "Torchic", "Fuego", 22, 260, "Hoenn", false),
                new Pokemon(4L, "Piplup", "Agua", 21, 250, "Sinnoh", false),
                new Pokemon(5L, "Charmander", "Fuego", 15, 200, "Kanto", false),
                new Pokemon(6L, "Totodile", "Agua", 19, 230, "Johto", false)
        );

        Map<String, List<String>> porRegion = pokedex.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())));

        System.out.println("Agrupados por region: " + porRegion);
    }
}
