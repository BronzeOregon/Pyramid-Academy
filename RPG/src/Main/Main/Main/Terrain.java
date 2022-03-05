package Main;

import java.util.Objects;

public class Terrain {

    public int goblinLvlMod = 0;

    private int x;
    private int y;
    private String state;
    private String defaultState;

    public Terrain(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getDefaultState() {
        return defaultState;
    }

    public void setDefaultState(String defaultState) {
        this.defaultState = defaultState;
    }

    public void resetState()
    {
            this.state=defaultState;
    }

}
