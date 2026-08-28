package dosw.semana_3.patrones.ejercicio06;

public class NotificationService implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        System.out.println("[Notificaciones] Avisando a " + user.getNombre() + " que sus preferencias cambiaron.");
    }
}
