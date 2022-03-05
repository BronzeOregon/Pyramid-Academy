package Main;

public class Forest extends Terrain {

    String defaultState= "🌲";

    public Forest(int x, int y)
    {
        super(x, y);
        this.goblinLvlMod = 3;
        this.setState("🌲");
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
