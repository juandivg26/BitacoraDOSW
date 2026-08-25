package dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * #02 - Pokedex Gritona.
 * Transformar todos los nombres de Pokemon a mayusculas.
 * Operador principal: map()
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        List<String> nombres = List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur");

        List<String> nombresMayuscula = nombres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(nombresMayuscula);
    }
}
