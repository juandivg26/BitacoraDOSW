package dosw.semana_2.pokemon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * #13 - Organizar por Tipo.
 * Agrupar todos los Pokemon por su tipo y mostrar el listado por grupo.
 * Operador principal: groupingBy()
 */
public class Ejercicio13 {

    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(2L, "Psyduck", "Agua", 25, 250, "Kanto", false),
                new Pokemon(3L, "Charmander", "Fuego", 15, 200, "Kanto", false),
                new Pokemon(4L, "Vulpix", "Fuego", 18, 220, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 16, 230, "Kanto", false)
        );

        Map<String, List<String>> porTipo = pokedex.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getTipo,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())));

        System.out.println("Agrupados por tipo: " + porTipo);
    }
}
