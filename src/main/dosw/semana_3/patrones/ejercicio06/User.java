package dosw.semana_3.patrones.ejercicio06;

import java.util.ArrayList;
import java.util.List;

/** Subject: notifica a sus Observers cuando cambian las preferencias. */
public class User {
    private final String nombre;
    private RecommendationAlgorithm algoritmo;
    private final List<PreferenceObserver> observers = new ArrayList<>();

    public User(String nombre, RecommendationAlgorithm algoritmoInicial) {
        this.nombre = nombre;
        this.algoritmo = algoritmoInicial;
    }

    public String getNombre() { return nombre; }

    public RecommendationAlgorithm getAlgoritmo() { return algoritmo; }

    public void addObserver(PreferenceObserver observer) {
        observers.add(observer);
    }

    public void cambiarPreferencia(RecommendationAlgorithm nuevoAlgoritmo) {
        this.algoritmo = nuevoAlgoritmo;
        for (PreferenceObserver observer : observers) {
            observer.onPreferenceChanged(this);
        }
    }
}
