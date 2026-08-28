package dosw.semana_3.patrones.ejercicio07;

public class AutorHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) { return true; }

    @Override
    protected void process(Document doc) {
        System.out.println("[AutorHandler] Revision del autor: OK.");
        doc.approve();
    }
}
