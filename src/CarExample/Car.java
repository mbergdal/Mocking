package CarExample;

/**
 * Created by mbe on 19/10/15.
 */
public class Car {
    private SlowEngine engine; //Tight coupling

    public Car() {
        engine = new SlowEngine(); //Tight coupling
    }

    public void start(){
        System.out.println(engine.run());
    }
}
