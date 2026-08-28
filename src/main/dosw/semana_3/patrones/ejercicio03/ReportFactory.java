package dosw.semana_3.patrones.ejercicio03;

/** Factory Method: crea la instancia de reporte correcta segun el tipo solicitado. */
public class ReportFactory {
    public static ReportGenerator create(String type) {
        return switch (type) {
            case "PDF" -> new PdfReport();
            case "EXCEL" -> new ExcelReport();
            case "CSV" -> new CsvReport();
            default -> throw new IllegalArgumentException("Tipo de reporte no soportado: " + type);
        };
    }
}
