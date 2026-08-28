package dosw.semana_3.patrones.ejercicio07;

/** Chain of Responsibility: cada handler decide si procesa o pasa al siguiente. */
public abstract class DocumentHandler {
    private DocumentHandler next;

    public DocumentHandler setNext(DocumentHandler next) {
        this.next = next;
        return next;
    }

    public void handle(Document doc) {
        if (canHandle(doc)) {
            process(doc);
        }
        if (next != null) {
            next.handle(doc);
        } else {
            doc.approve();
        }
    }

    protected abstract boolean canHandle(Document doc);

    protected abstract void process(Document doc);
}
