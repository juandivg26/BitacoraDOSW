package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * #01 - Pokemon Tipo Fuego.
 * Dada una lista de Pokemon con nombre y tipo, obtener unicamente
 * aquellos cuyo tipo sea Fuego.
 * Operador principal: filter()
 */
public class Ejercicio1 {

    record PokemonBasico(String nombre, String tipo) {}

    public static void main(String[] args) {
        List<PokemonBasico> pokedex = List.of(
                new PokemonBasico("Pikachu", "Electrico"),
                new PokemonBasico("Charmander", "Fuego"),
                new PokemonBasico("Squirtle", "Agua"),
                new PokemonBasico("Vulpix", "Fuego"),
                new PokemonBasico("Bulbasaur", "Planta"),
                new PokemonBasico("Flareon", "Fuego")
        );

        List<String> tipoFuego = pokedex.stream()
                .filter(p -> p.tipo().equals("Fuego"))
                .map(PokemonBasico::nombre)
                .collect(Collectors.toList());

        System.out.println("Pokemon tipo Fuego: " + tipoFuego);
    }
}
