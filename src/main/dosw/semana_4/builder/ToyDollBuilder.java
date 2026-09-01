package dosw.semana_4.builder;

public interface ToyDollBuilder {
    void buildHead();
    void buildBody();
    void buildArms();
    void buildLegs();
    void addAccessories();
    ToyDoll getResult();
}
