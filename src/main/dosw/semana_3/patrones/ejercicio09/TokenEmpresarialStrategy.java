package dosw.semana_3.patrones.ejercicio09;

public class TokenEmpresarialStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Validando token empresarial de " + c.username());
        return new AuthResult(true, c.username());
    }
}
