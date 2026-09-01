package dosw.semana_4.chainOfResponsability;

public interface ControlMigratorio {
    void setSiguiente(ControlMigratorio siguiente);
    void procesar(IngresoRequest req);
}
