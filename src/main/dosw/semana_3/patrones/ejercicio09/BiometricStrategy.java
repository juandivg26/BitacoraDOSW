package dosw.semana_3.patrones.ejercicio09;

public class BiometricStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("[Auth] Validando huella biometrica de " + c.username());
        return new AuthResult(true, c.username());
    }
}
