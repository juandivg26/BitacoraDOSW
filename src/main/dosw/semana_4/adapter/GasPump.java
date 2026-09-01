package dosw.semana_4.adapter;

public class GasPump implements FuelService {
    @Override
    public void supply(int liters) {
        System.out.println("Abasteciendo " + liters + " litros de combustible.");
    }
}
