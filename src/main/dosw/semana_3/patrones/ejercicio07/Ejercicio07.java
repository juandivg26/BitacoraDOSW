package dosw.semana_3.patrones.ejercicio07;

/**
 * #07 - Flujo de Aprobacion de Documentos.
 * Patrones combinados: Chain of Responsibility + State.
 * La cadena de handlers (Autor -> Lider -> Juridico -> Financiero)
 * procesa el documento en secuencia; el handler Financiero solo se
 * activa si el documento requiere presupuesto (no todos los
 * documentos pasan por todas las etapas). Cada handler invoca
 * approve()/reject(), y el objeto State actual del documento decide
 * la transicion, eliminando switch/if de estado.
 */
public class Ejercicio07 {
    public static void main(String[] args) {
        AutorHandler autor1 = new AutorHandler();
        LiderHandler lider1 = new LiderHandler();
        JuridicoHandler juridico1 = new JuridicoHandler();
        FinancieroHandler financiero1 = new FinancieroHandler();
        autor1.setNext(lider1).setNext(juridico1).setNext(financiero1);

        System.out.println("=== Documento SIN presupuesto (salta la etapa Financiera) ===");
        Document docSimple = new Document("DOC-01", false);
        System.out.println("Estado inicial: " + docSimple.getEstado());
        autor1.handle(docSimple);
        System.out.println("Estado final: " + docSimple.getEstado());

        System.out.println();

        AutorHandler autor2 = new AutorHandler();
        LiderHandler lider2 = new LiderHandler();
        JuridicoHandler juridico2 = new JuridicoHandler();
        FinancieroHandler financiero2 = new FinancieroHandler();
        autor2.setNext(lider2).setNext(juridico2).setNext(financiero2);

        System.out.println("=== Documento CON presupuesto (pasa por las 4 etapas) ===");
        Document docPresupuesto = new Document("DOC-02", true);
        System.out.println("Estado inicial: " + docPresupuesto.getEstado());
        autor2.handle(docPresupuesto);
        System.out.println("Estado final: " + docPresupuesto.getEstado());
    }
}
