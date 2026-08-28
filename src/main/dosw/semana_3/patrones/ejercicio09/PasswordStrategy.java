package dosw.semana_3.patrones.ejercicio09;

public class PasswordStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Validando usuario/contrasena de " + c.username());
        return new AuthResult(true, c.username());
    }
}
