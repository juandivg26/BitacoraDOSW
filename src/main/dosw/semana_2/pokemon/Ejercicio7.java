package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * #07 - Orden del Profesor Oak.
 * Ordenar alfabeticamente los nombres de los Pokemon.
 * Operador principal: sorted()
 */
public class Ejercicio7 {

    public static void main(String[] args) {
        List<String> pokedex = List.of("Squirtle", "Pikachu", "Mewtwo", "Bulbasaur", "Charmander", "Abra");

        List<String> ordenados = pokedex.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Pokedex ordenada: " + ordenados);
    }
}
