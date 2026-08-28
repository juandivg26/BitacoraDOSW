package dosw.semana_3.patrones.ejercicio03;

/** Template Method: define el esqueleto fijo del algoritmo de reporte. */
public abstract class ReportGenerator {

    public final void generate() {
        fetchData();
        processData();
        applyFormat();
        exportFile();
    }

    protected void fetchData() {
        System.out.println("  1) Obteniendo datos crudos de la base de datos...");
    }

    protected void processData() {
        System.out.println("  2) Procesando y consolidando informacion...");
    }

    protected abstract void applyFormat();

    protected abstract void exportFile();
}
