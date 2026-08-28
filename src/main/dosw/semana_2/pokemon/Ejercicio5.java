package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * #05 - Pokemon Legendarios.
 * Muestra el equipo completo, identifica los Pokemon con nivel superior a 80
 * y presenta el resultado usando Streams y lambdas.
 */
public class Ejercicio5 {

    record PokemonNivel(String nombre, int nivel) {}

    public static void main(String[] args) {
        List<PokemonNivel> equipo = List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Mewtwo", 88),
                new PokemonNivel("Dragonite", 82),
                new PokemonNivel("Squirtle", 38),
                new PokemonNivel("Mew", 85),
                new PokemonNivel("Charmander", 62)
        );

      
        List<PokemonNivel> superiores = equipo.stream()
                .filter(p -> p.nivel() > 80)
                .toList();

        String nombresSuperiores = superiores.stream()
                .map(PokemonNivel::nombre)
                .collect(Collectors.joining(", "));


        System.out.println("Pokémon con nivel > 80: " + superiores.size());
        System.out.println("(" + nombresSuperiores + ")");
    }
}
