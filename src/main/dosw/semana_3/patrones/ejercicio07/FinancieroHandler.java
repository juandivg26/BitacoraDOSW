package dosw.semana_3.patrones.ejercicio07;

/** Solo revisa documentos que involucran presupuesto (no todos los documentos pasan por aqui). */
public class FinancieroHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) {
        return doc.isRequierePresupuesto();
    }

    @Override
    protected void process(Document doc) {
        System.out.println("[FinancieroHandler] Revision financiera: OK.");
    }
}
