package Main;

import java.util.Random;

public class Goblin extends Asset{

    int goblinHPMax;
    int goblinHPCurrent;
    int goblinStr;
    int goblinDex;
    int goblinCon;
    int goblinLevelMod = 1;

    public Goblin(int x, int y) {
        super(x, y);
    }

    public Goblin resetStats(){
        this.goblinHPMax=0;
        this.goblinLevelMod =1;
        this.goblinStr=0;
        this.goblinDex=0;
        this.goblinCon=0;

        return this;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC7A";
    }

    public int getGoblinHPMax() {
        return goblinHPMax;
    }

    public void setGoblinHPMax(int goblinHPMax) {
        this.goblinHPMax = goblinHPMax;
    }

    public int getGoblinHPCurrent() {
        return goblinHPCurrent;
    }

    public void setGoblinHPCurrent(int goblinHPCurrent) {
        this.goblinHPCurrent = goblinHPCurrent;
    }

    public int getGoblinStr() {
        return goblinStr;
    }

    public void setGoblinStr(int goblinStr) {
        this.goblinStr = goblinStr;
    }

    public int getGoblinDex() {
        return goblinDex;
    }

    public void setGoblinDex(int goblinDex) {
        this.goblinDex = goblinDex;
    }

    public int getGoblinCon() {
        return goblinCon;
    }

    public void setGoblinCon(int goblinCon) {
        this.goblinCon = goblinCon;
    }

    public int getGoblinLevelMod() {
        return goblinLevelMod;
    }

    public void setGoblinLevelMod(int goblinLevelMod) {
        this.goblinLevelMod = goblinLevelMod;
    }

    public Goblin updateGoblinStats()
    {
        this.resetStats();
        goblinLevelMod+=Runtime.map.getTerrain(this.x,this.y).goblinLvlMod;
        goblinStr=(goblinLevelMod*3)+(randInt(1,4));
        goblinDex=(goblinLevelMod*3)+(randInt(1,4));
        goblinCon=(goblinLevelMod*3)+(randInt(1,4));
        goblinHPMax=(5*goblinLevelMod)+(goblinCon*2);
        goblinHPCurrent=goblinHPMax;

        return this;
    }

    public int combatLogic(){
//        if(this.goblinLevelMod>Runtime.newPlayer.playerLvl){
//            System.out.println("This goblin looks pretty strong!");
//        }
        System.out.println("The goblin attacks!");

        int damage = this.goblinStr+this.goblinLevelMod-Runtime.newPlayer.equippedArmor.getArmor();

        System.out.println("You take "+damage+" damage!");

        return damage;
    }

    public boolean isGoblinDead(){

        boolean dead = false;

        if(this.goblinHPCurrent<=0) {
            dead=true;
        }

        return dead;
    }

    public Goblin spawnNewGoblin(){
        this.setPosition(randInt(0,9),randInt(0,9));
        this.goblinHPCurrent=this.goblinHPMax;
        return this;
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
