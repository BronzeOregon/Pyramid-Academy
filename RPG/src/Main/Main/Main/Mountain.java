package Main;

public class Mountain extends Terrain{

    String defaultState= "⛰";

    public Mountain(int x, int y)
    {
        super(x, y);
        this.goblinLvlMod = 5;
        this.setState("⛰");
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
