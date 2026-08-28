package dosw.semana_3.patrones.ejercicio06;

import java.util.List;

/** Strategy: cada algoritmo de recomendacion es intercambiable. */
public interface RecommendationAlgorithm {
    List<Content> recommend(User user);
}
