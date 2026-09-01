package dosw.semana_4.builder;

public class ToyFactory {
    public ToyDoll constructDoll(ToyDollBuilder builder) {
        builder.buildHead();
        builder.buildBody();
        builder.buildArms();
        builder.buildLegs();
        builder.addAccessories();
        return builder.getResult();
    }
}
