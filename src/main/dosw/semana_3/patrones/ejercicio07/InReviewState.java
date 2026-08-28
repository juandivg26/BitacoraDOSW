package dosw.semana_3.patrones.ejercicio07;

public class InReviewState implements DocumentState {
    @Override
    public void approve(Document doc) {
        doc.setState(new ApprovedState());
    }

    @Override
    public void reject(Document doc) {
        doc.setState(new RejectedState());
    }

    @Override
    public String name() { return "EN_REVISION"; }
}
