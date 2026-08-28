package dosw.semana_3.patrones.ejercicio07;

public class DraftState implements DocumentState {
    @Override
    public void approve(Document doc) {
        doc.setState(new InReviewState());
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] Un borrador no puede rechazarse, sigue en Draft.");
    }

    @Override
    public String name() { return "BORRADOR"; }
}
