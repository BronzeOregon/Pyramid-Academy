package Main;

public class Asset {
    int x;
    int y;

    public Asset(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Asset setPosition(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

//    public Asset moveNorth(){
//        this.y -=1;
//        resetTerrain(this.x, this.y+1);
//        return this;
//    }

    public Asset moveNorth(){
        this.y -=1;
        return this;
    }

    public Asset moveSouth(){
        this.y +=1;
        return this;
    }

    public Asset moveEast(){
        this.x +=1;
        return this;
    }

    public Asset moveWest(){
        this.x -=1;
        return this;
    }

    @Override
    public String toString() {
        return "☺";
    }

}
