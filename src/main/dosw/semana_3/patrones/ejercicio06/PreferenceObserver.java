package dosw.semana_3.patrones.ejercicio06;

/** Observer: reacciona cuando el usuario cambia su algoritmo de recomendacion. */
public interface PreferenceObserver {
    void onPreferenceChanged(User user);
}
