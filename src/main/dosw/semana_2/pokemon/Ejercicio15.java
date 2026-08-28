package dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * #15 - Maestro de Gimnasios.
 * Dado un listado de entrenadores con sus medallas, encontrar el
 * entrenador con mas medallas.
 * Operador principal: max(Comparator)
 */
public class Ejercicio15 {

    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 5, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of())
        );

        Optional<Entrenador> campeon = entrenadores.stream()
                .max(Comparator.comparingInt(Entrenador::getMedallas));

        campeon.ifPresent(e -> {
            System.out.println("Campeon de gimnasios: " + e.getNombre());
            System.out.println("Medallas obtenidas: " + e.getMedallas());
        });
    }
}
