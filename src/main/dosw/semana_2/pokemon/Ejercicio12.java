package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * #12 - Campeon Regional.
 * Obtener el Pokemon con mayor poderCombate de toda la lista.
 * Operador principal: max(Comparator)
 */
public class Ejercicio12 {

    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 88, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Charizard", "Fuego", 60, 610, "Kanto", false)
        );

        Optional<Pokemon> campeon = pokedex.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate));

        campeon.ifPresent(p -> System.out.println(
                "Campeon: " + p.getNombre() + " con PC: " + p.getPoderCombate()));
    }
}
