package dosw.semana_3.patrones.ejercicio09;

public class GoogleStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Validando token de Google para " + c.username());
        return new AuthResult(true, c.username());
    }
}
