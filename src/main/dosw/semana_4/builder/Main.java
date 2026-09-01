package dosw.semana_4.builder;

public class Main {
    public static void main(String[] args) {
        ToyFactory toyFactory = new ToyFactory();

        ActionDollBuilder actionDollBuilder = new ActionDollBuilder();
        toyFactory.constructDoll(actionDollBuilder);
        ToyDoll actionDoll = actionDollBuilder.getResult();

        ClassicDollBuilder classicDollBuilder = new ClassicDollBuilder();
        toyFactory.constructDoll(classicDollBuilder);
        ToyDoll classicDoll = classicDollBuilder.getResult();

        actionDoll.showInfo();
        classicDoll.showInfo();
    }
}
