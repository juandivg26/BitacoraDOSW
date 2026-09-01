package dosw.semana_4.builder;

public class ActionDollBuilder implements ToyDollBuilder {
    private ToyDoll doll;

    public ActionDollBuilder() {
        doll = new ToyDoll();
    }

    @Override
    public void buildHead() { doll.setHead("Cabeza robusta de acción"); }
    @Override
    public void buildBody() { doll.setBody("Cuerpo musculoso"); }
    @Override
    public void buildArms() { doll.setArms("Brazos articulados"); }
    @Override
    public void buildLegs() { doll.setLegs("Piernas resistentes"); }
    @Override
    public void addAccessories() { doll.setAccessories(true); } //este tiene accesorios

    @Override
    public ToyDoll getResult() { return doll; }
}
