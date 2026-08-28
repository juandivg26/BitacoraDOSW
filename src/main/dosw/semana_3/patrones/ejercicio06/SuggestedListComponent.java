package dosw.semana_3.patrones.ejercicio06;

public class SuggestedListComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        var contenido = user.getAlgoritmo().recommend(user);
        System.out.println("[Sugeridos] Actualizando lista con: " + contenido);
    }
}
