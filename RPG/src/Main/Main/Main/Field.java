package Main;

public class Field extends Terrain{

    String defaultState= "˷";

    Field(int x, int y){
        super(x, y);
        this.goblinLvlMod=1;
        this.setState("˷");
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
