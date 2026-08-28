package dosw.semana_3.patrones.ejercicio09;

public class MicrosoftStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Validando cuenta de Microsoft (Azure AD) para " + c.username());
        return new AuthResult(true, c.username());
    }
}
