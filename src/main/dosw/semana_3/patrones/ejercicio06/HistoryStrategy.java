package dosw.semana_3.patrones.ejercicio06;

import java.util.List;

public class HistoryStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        return List.of(new Content("Recomendado por historial para " + user.getNombre()));
    }
}
