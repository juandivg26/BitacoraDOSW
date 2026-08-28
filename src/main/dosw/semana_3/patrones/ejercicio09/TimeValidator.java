package dosw.semana_3.patrones.ejercicio09;

public class TimeValidator extends Validator {
    @Override
    protected void check(Credentials c) {
        if (c.hour() < 6 || c.hour() > 20) {
            throw new AccessDeniedException("Fuera de horario laboral: " + c.hour() + "h");
        }
        System.out.println("  [TimeValidator] Horario OK (" + c.hour() + "h).");
    }
}
