package dosw.semana_3.patrones.ejercicio03;

public class CsvReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3) Aplicando formato CSV (separador por comas)...");
    }

    @Override
    protected void exportFile() {
        System.out.println("  4) Exportando reporte.csv");
    }
}
