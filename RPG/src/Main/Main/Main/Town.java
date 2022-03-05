package Main;

public class Town extends Terrain {

    String defaultState= "⌂";

    Town(int x, int y) {
        super(x, y);
        this.setState("⌂");
    }

    @Override
    public String getDefaultState() {
        return defaultState;
    }

    @Override
    public void resetState() {
        this.setDefaultState(defaultState);
        this.setState(defaultState);
    }
}
