package dosw.semana_4.chainOfResponsability;

public class AntecedentesControl extends ControlMigratorioHandler {
    @Override
    public void procesar(IngresoRequest req) {
        if (req.sinAntecedentes) {
            System.out.println("No hay antecedentes. Siguiente control...");
            if (siguiente != null) siguiente.procesar(req);
        } else {
            System.out.println("¡RECHAZADO por antecedentes!");
        }
    }
}
