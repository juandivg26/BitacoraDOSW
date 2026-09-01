package dosw.semana_4.chainOfResponsability;

public class IngresoRequest {
    public boolean passportAndVisa;
    public boolean sinAntecedentes;
    public boolean motivoValido;
    public boolean aprobacionFinal;

    public IngresoRequest(boolean passportAndVisa, boolean sinAntecedentes,
                          boolean motivoValido, boolean aprobacionFinal) {
        this.passportAndVisa = passportAndVisa;
        this.sinAntecedentes = sinAntecedentes;
        this.motivoValido = motivoValido;
        this.aprobacionFinal = aprobacionFinal;
    }
}
