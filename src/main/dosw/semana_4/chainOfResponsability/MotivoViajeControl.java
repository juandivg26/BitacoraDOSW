package dosw.semana_4.chainOfResponsability;

public class MotivoViajeControl extends ControlMigratorioHandler {
    @Override
    public void procesar(IngresoRequest req) {
        if (req.motivoValido) {
            System.out.println("Motivo de viaje válido. Siguiente control...");
            if (siguiente != null) siguiente.procesar(req);
        } else {
            System.out.println("¡RECHAZADO por motivo de viaje no válido!");
        }
    }
}
