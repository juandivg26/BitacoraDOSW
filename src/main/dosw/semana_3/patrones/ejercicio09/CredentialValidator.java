package dosw.semana_3.patrones.ejercicio09;

public class CredentialValidator extends Validator {
    @Override
    protected void check(Credentials c) {
        System.out.println("  [CredentialValidator] Credenciales OK.");
    }
}
