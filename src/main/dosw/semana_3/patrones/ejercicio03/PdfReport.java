package dosw.semana_3.patrones.ejercicio03;

public class PdfReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("  3) Aplicando formato PDF (paginas, encabezados)...");
    }

    @Override
    protected void exportFile() {
        System.out.println("  4) Exportando reporte.pdf");
    }
}
