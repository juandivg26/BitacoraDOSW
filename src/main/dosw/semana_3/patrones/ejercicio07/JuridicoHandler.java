package dosw.semana_3.patrones.ejercicio07;

public class JuridicoHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) { return true; }

    @Override
    protected void process(Document doc) {
        System.out.println("[JuridicoHandler] Revision juridica: OK.");
    }
}
