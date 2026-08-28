package dosw.semana_3.patrones.ejercicio09;

/**
 * #09 - Sistema de Autenticacion Empresarial.
 * Patrones combinados: Strategy + Chain of Responsibility.
 * Strategy decide "como autentico" (password, Google, biometria);
 * una vez autenticado, la Chain of Responsibility valida en secuencia
 * credenciales, permisos, ubicacion y horario laboral, lanzando
 * AccessDeniedException si algun eslabon falla.
 */
public class Ejercicio09 {
    public static void main(String[] args) {
        AuthService service = new AuthService();

        CredentialValidator cred = new CredentialValidator();
        PermissionValidator perm = new PermissionValidator();
        LocationValidator loc = new LocationValidator();
        TimeValidator time = new TimeValidator();
        cred.setNext(perm).setNext(loc).setNext(time);

        System.out.println("--- Login con Password, dentro de horario y ubicacion validas ---");
        service.login(new PasswordStrategy(), cred, new Credentials("jvalderrama", "oficina", 10));

        System.out.println("--- Login con Google, fuera de ubicacion autorizada ---");
        service.login(new GoogleStrategy(), cred, new Credentials("jvalderrama", "casa", 10));

        System.out.println("--- Login con Microsoft (Azure AD), todo en regla ---");
        service.login(new MicrosoftStrategy(), cred, new Credentials("jvalderrama", "oficina", 9));

        System.out.println("--- Login con Token Empresarial, todo en regla ---");
        service.login(new TokenEmpresarialStrategy(), cred, new Credentials("jvalderrama", "oficina", 15));

        System.out.println("--- Login biometrico, fuera de horario laboral ---");
        service.login(new BiometricStrategy(), cred, new Credentials("jvalderrama", "oficina", 23));
    }
}
