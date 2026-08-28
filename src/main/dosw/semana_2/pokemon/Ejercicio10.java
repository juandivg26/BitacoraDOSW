package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * #10 - Pokedex Compacta.
 * Generar una lista que contenga unicamente los nombres de todos los
 * Pokemon del equipo.
 * Operadores principales: map() + collect()
 */
public class Ejercicio10 {

    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 88, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 55, 530, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 20, 210, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 40, 495, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 60, 610, "Kanto", false)
        );

        List<String> nombres = equipo.stream()
                .map(Pokemon::getNombre)
                .collect(Collectors.toList());

        System.out.println("Pokedex compacta: " + nombres);
    }
}
