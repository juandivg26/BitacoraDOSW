package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * #06 - Pokedex Sin Duplicados.
 * Dada una lista de Pokemon con elementos repetidos, generar una nueva
 * coleccion donde cada Pokemon aparezca una sola vez.
 * Operador principal: distinct()
 */
public class Ejercicio6 {

    public static void main(String[] args) {
        List<String> conDuplicados = List.of(
                "Pikachu", "Charmander", "Pikachu", "Squirtle", "Charmander", "Mewtwo");

        List<String> sinDuplicados = conDuplicados.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Pokedex sin duplicados: " + sinDuplicados);
    }
}
