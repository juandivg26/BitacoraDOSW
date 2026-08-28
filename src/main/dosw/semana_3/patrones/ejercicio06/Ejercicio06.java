package dosw.semana_3.patrones.ejercicio06;

/**
 * #06 - Motor de Recomendaciones.
 * Patrones combinados: Strategy + Observer.
 * Strategy permite intercambiar el algoritmo de recomendacion en
 * tiempo de ejecucion; Observer notifica automaticamente a la
 * pagina principal, notificaciones y sugeridos cuando el usuario
 * cambia sus preferencias, sin acoplar al usuario con esos componentes.
 */
public class Ejercicio06 {
    public static void main(String[] args) {
        User user = new User("Camila", new GenreStrategy());
        user.addObserver(new HomePageComponent());
        user.addObserver(new SuggestedListComponent());
        user.addObserver(new NotificationService());

        System.out.println("--- Usuario cambia a recomendacion por Historial ---");
        user.cambiarPreferencia(new HistoryStrategy());

        System.out.println("--- Usuario cambia a recomendacion por Popularidad ---");
        user.cambiarPreferencia(new PopularityStrategy());

        System.out.println("--- Usuario cambia a recomendacion por Similitud con otros usuarios ---");
        user.cambiarPreferencia(new SimilarityStrategy());
    }
}
