package dosw.semana_4.builder;

public class ClassicDollBuilder implements ToyDollBuilder {
    private ToyDoll doll;

    public ClassicDollBuilder() {
        doll = new ToyDoll();
    }

    @Override
    public void buildHead() { doll.setHead("Cabeza clásica con cabello"); }
    @Override
    public void buildBody() { doll.setBody("Cuerpo elegante"); }
    @Override
    public void buildArms() { doll.setArms("Brazos delicados"); }
    @Override
    public void buildLegs() { doll.setLegs("Piernas delgadas"); }
    @Override
    public void addAccessories() { doll.setAccessories(false); } // esta no tiene accesorios

    @Override
    public ToyDoll getResult() { return doll; }
}
