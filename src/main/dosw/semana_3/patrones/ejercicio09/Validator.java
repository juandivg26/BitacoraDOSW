package dosw.semana_3.patrones.ejercicio09;

/** Chain of Responsibility: cada validador decide si pasa al siguiente o niega el acceso. */
public abstract class Validator {
    private Validator next;

    public Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    public void validate(Credentials c) {
        check(c);
        if (next != null) {
            next.validate(c);
        }
    }

    protected abstract void check(Credentials c);
}
