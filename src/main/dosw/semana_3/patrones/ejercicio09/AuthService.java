package dosw.semana_3.patrones.ejercicio09;

public class AuthService {
    public void login(AuthStrategy strategy, Validator chain, Credentials c) {
        AuthResult result = strategy.authenticate(c);
        if (!result.success()) {
            System.out.println("Autenticacion fallida para " + c.username());
            return;
        }
        try {
            chain.validate(c);
            System.out.println("Acceso concedido a " + c.username());
        } catch (AccessDeniedException e) {
            System.out.println("Acceso denegado a " + c.username() + ": " + e.getMessage());
        }
    }
}
