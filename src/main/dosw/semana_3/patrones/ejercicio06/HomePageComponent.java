package dosw.semana_3.patrones.ejercicio06;

public class HomePageComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        var contenido = user.getAlgoritmo().recommend(user);
        System.out.println("[HomePage] Re-renderizando con: " + contenido);
    }
}
