package dosw.semana_4.chainOfResponsability;

public class PasaporteControl extends ControlMigratorioHandler {
    @Override
    public void procesar(IngresoRequest req) {
        if (req.passportAndVisa) {
            System.out.println("Pasaporte y visa correctos. Siguiente control...");
            if (siguiente != null) siguiente.procesar(req);
        } else {
            System.out.println("¡RECHAZADO en control de pasaporte y visa!");
        }
    }
}
