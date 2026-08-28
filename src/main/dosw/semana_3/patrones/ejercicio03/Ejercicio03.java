package dosw.semana_3.patrones.ejercicio03;

/**
 * #03 - Sistema de Reportes Empresariales.
 * Patrones combinados: Template Method + Factory Method.
 * Template Method fija los 4 pasos del algoritmo en ReportGenerator;
 * Factory Method decide, segun el tipo pedido, que subclase concreta
 * (PDF/Excel/CSV) construir para ejecutar esos pasos.
 */
public class Ejercicio03 {
    public static void main(String[] args) {
        System.out.println("--- Cliente pide reporte PDF ---");
        ReportGenerator pdf = ReportFactory.create("PDF");
        pdf.generate();

        System.out.println("--- Cliente pide reporte CSV ---");
        ReportGenerator csv = ReportFactory.create("CSV");
        csv.generate();

        System.out.println("--- Cliente pide reporte Excel ---");
        ReportGenerator excel = ReportFactory.create("EXCEL");
        excel.generate();
    }
}
