package dosw.semana_3.patrones.ejercicio03;

public class ExcelReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3) Aplicando formato Excel (hojas, celdas, formulas)...");
    }

    @Override
    protected void exportFile() {
        System.out.println("  4) Exportando reporte.xlsx");
    }
}
