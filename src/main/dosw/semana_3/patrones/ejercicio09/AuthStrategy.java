package dosw.semana_3.patrones.ejercicio09;

/** Strategy: cada mecanismo de autenticacion decide "como" autenticar. */
public interface AuthStrategy {
    AuthResult authenticate(Credentials c);
}
