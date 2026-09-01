package dosw.semana_4.chainOfResponsability;

public class AprobacionFinalControl extends ControlMigratorioHandler {
    @Override
    public void procesar(IngresoRequest req) {
        if (req.aprobacionFinal) {
            System.out.println("APROBADO: puede ingresar a EE. UU.");
        } else {
            System.out.println("¡RECHAZADO en aprobación final de migración!");
        }
    }
}
