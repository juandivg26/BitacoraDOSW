package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * #08 - Evoluciones Preparadas.
 * Dada una lista de Pokemon que incluye si pueden evolucionar,
 * obtener unicamente los que estan listos para evolucionar.
 * Operador principal: filter()
 */
public class Ejercicio8 {

    record PokemonEvo(String nombre, boolean puedeEvolucionar) {}

    public static void main(String[] args) {
        List<PokemonEvo> equipo = List.of(
                new PokemonEvo("Pikachu", true),
                new PokemonEvo("Raichu", false),
                new PokemonEvo("Charmander", true),
                new PokemonEvo("Charizard", false),
                new PokemonEvo("Squirtle", true),
                new PokemonEvo("Blastoise", false)
        );

        List<String> listosParaEvolucionar = equipo.stream()
                .filter(PokemonEvo::puedeEvolucionar)
                .map(PokemonEvo::nombre)
                .collect(Collectors.toList());

        System.out.println("Listos para evolucionar: " + listosParaEvolucionar);
    }
}
