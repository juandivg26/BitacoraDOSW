package dosw.semana_3.patrones.ejercicio10;

/**
 * #10 - Aplicacion de Edicion de Imagenes.
 * Patrones combinados: Decorator + Command.
 * Decorator aplica filtros acumulativos envolviendo la imagen sin
 * modificar la clase base; Command encapsula cada aplicacion de
 * filtro como una operacion reversible, guardando el wrapper anterior
 * para permitir undo individual (no solo el ultimo cambio global).
 */
public class Ejercicio10 {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor(new BaseImage("foto.png"));
        System.out.println("Inicial: " + editor.getCurrent().render());

        ImageCommand grayscale = new ApplyFilterCommand(editor, GrayscaleDecorator::new);
        ImageCommand sepia = new ApplyFilterCommand(editor, SepiaDecorator::new);
        ImageCommand brightness = new ApplyFilterCommand(editor, BrightnessDecorator::new);
        ImageCommand contrast = new ApplyFilterCommand(editor, ContrastDecorator::new);
        ImageCommand noiseReduction = new ApplyFilterCommand(editor, NoiseReductionDecorator::new);

        System.out.println("--- Aplicando filtros ---");
        editor.executeCommand(grayscale);
        editor.executeCommand(sepia);
        editor.executeCommand(brightness);
        editor.executeCommand(contrast);
        editor.executeCommand(noiseReduction);

        System.out.println("Resultado final: " + editor.getCurrent().render());

        System.out.println("--- Deshaciendo el ultimo filtro (reduccion de ruido) ---");
        editor.undoLast();
        System.out.println("Resultado tras undo: " + editor.getCurrent().render());
    }
}
