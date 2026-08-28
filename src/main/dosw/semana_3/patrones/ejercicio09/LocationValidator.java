package dosw.semana_3.patrones.ejercicio09;

public class LocationValidator extends Validator {
    @Override
    protected void check(Credentials c) {
        if (!"oficina".equals(c.location())) {
            throw new AccessDeniedException("Ubicacion no autorizada: " + c.location());
        }
        System.out.println("  [LocationValidator] Ubicacion OK (" + c.location() + ").");
    }
}
