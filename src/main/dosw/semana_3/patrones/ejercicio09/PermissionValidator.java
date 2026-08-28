package dosw.semana_3.patrones.ejercicio09;

public class PermissionValidator extends Validator {
    @Override
    protected void check(Credentials c) {
        System.out.println("  [PermissionValidator] Permisos OK.");
    }
}
