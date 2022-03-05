package Main;

import java.util.Random;

public class Runtime {

    static GameState gameState;
    static Main.Player newPlayer = new Main.Player(5,5);
    static Loot newLoot = new Loot();
    static MapRedux map = new MapRedux(10);

    static int livingGoblins =0;
    static Goblin gob1 = new Goblin(randInt(0,9),randInt(0,9));
    static Goblin gob2 = new Goblin(randInt(0,9),randInt(0,9));
    static Goblin gob3 = new Goblin(randInt(0,9),randInt(0,9));
    static Goblin gob4 = new Goblin(randInt(0,9),randInt(0,9));

    public static void main(String[] Args)
    {

        gameState = GameState.INTRO;

        newLoot.populateLoot();
        newLoot.otherTable.add(newLoot.otherTable.size(),newLoot.generateOther());
        newLoot.weaponTable.add(newLoot.weaponTable.size(),newLoot.generateBasicWeapon());
        newLoot.armorTable.add(newLoot.armorTable.size(),newLoot.generateBasicArmor());
        newPlayer.initializePlayer();

        for(int i = 0; i<3; i++)
        {
            if(i==0)
                newPlayer.inventory.add(newLoot.weaponTable.get(newLoot.weaponTable.size()-1));
            if(i<2&&i!=0)
                newPlayer.inventory.add(newLoot.armorTable.get(newLoot.armorTable.size()-1));
            if(i==2)
                newPlayer.inventory.add(newLoot.otherTable.get(newLoot.otherTable.size()-1));
        }

        do
        {
            prompts();
        }while (true);



    }

    public static String prompts()
    {
        switch (gameState)
        {
            case INTRO:
                System.out.println("You wake with a start, the howl of animals far in the distance.");
                System.out.println("You remember why you came to these cursed lands, to purge all the greenskins who dared to defile it.");
                System.out.println("You pack up your meager belongings, and your adventure begins...");
                map.addActor(gob1.setPosition(randInt(0,3),randInt(0,3)));
                map.addActor(gob2.setPosition(randInt(7,9),randInt(0,3)));
                map.addActor(gob3.setPosition(randInt(0,3),randInt(7,9)));
                map.addActor(gob4.setPosition(randInt(7,9),randInt(7,9)));
                map.addActor(newPlayer.setPosition(5,5));
                gameState=GameState.MAP;
            case MAP:
                map.updateState();
                map.mapManager();
                System.out.println("You are in a " + map.getDefaultTerrain(newPlayer.x, newPlayer.y));
                System.out.println("What would you like to do, "+newPlayer.playerName+"?");
                System.out.println("Type 'Inventory' to display your inventory, 'Equip' to equip a piece of gear, 'Move' to move to another tile, or 'Shop' if you're on a town tile.");
                System.out.println("You can also type 'Rest' to stay where you are and heal.");
                newPlayer.takeAction(newPlayer.getInput());
                map.actors.forEach(asset -> {
                    if(asset.getClass()==Goblin.class)
                    {
                        ((Goblin) asset).updateGoblinStats();
                    }
                });
                map.goblinMove();
                if(gameState!=GameState.SHOP)
                    map.checkCombat();
                break;

            case COMBAT:
                System.out.println("Goblin HP: " + map.enemy.goblinHPCurrent+"/"+map.enemy.goblinHPMax);
                System.out.println("Your HP: " + newPlayer.playerCurrentHealth+"/"+newPlayer.playerMaxHealth);
                if(map.enemy.goblinDex > newPlayer.playerDex){
                    newPlayer.playerCurrentHealth -= map.enemy.combatLogic();
                    if(newPlayer.isPlayerDead())
                    {
                        System.out.println("You have been slain!");
                        System.out.println("GAME OVER!");
                        System.exit(0);
                    }
                    System.out.println("You are in combat! What do you do? Type 'Attack' to strike your foe, or 'Item' to use an item!");
                    newPlayer.takeAction(newPlayer.getInput());
                } else {
                    System.out.println("You are in combat! What do you do? Type 'Attack' to strike your foe, or 'Item' to use an item!");
                    newPlayer.takeAction(newPlayer.getInput());
                    if(!map.enemy.isGoblinDead())
                        newPlayer.playerCurrentHealth -= map.enemy.combatLogic();
                }
                if(map.enemy.isGoblinDead())
                {
                    System.out.println("You have slain the goblin!");
                    newPlayer.updateLevel((map.enemy.goblinLevelMod*100));
                    System.out.println("You have gained "+(map.enemy.goblinLevelMod*100)+" experience!");
                    int randomLOOT = randInt(1,3);
                    if(randomLOOT==1)
                    {
                        Loot.Weapon lootedWeapon = Loot.weaponTable.get(randInt(0,Loot.weaponTable.size()-1));
                        System.out.println("The goblin was carrying a "+lootedWeapon+"!");
                        newPlayer.inventory.add(lootedWeapon);
                    }
                    if(randomLOOT==2)
                    {
                        Loot.Armor lootedArmor = Loot.armorTable.get(randInt(0,Loot.armorTable.size()-1));
                        System.out.println("The goblin was carrying a "+lootedArmor+"!");
                        newPlayer.inventory.add(lootedArmor);
                    }
                    if(randomLOOT==3)
                    {
                        Loot.Other lootedOther = Loot.otherTable.get(randInt(0,Loot.otherTable.size()-1));
                        System.out.println("The goblin was carrying a "+lootedOther+"!");
                        newPlayer.inventory.add(lootedOther);
                    }
                    map.enemy.spawnNewGoblin();
                    gameState=GameState.MAP;
                }
                if(newPlayer.isPlayerDead())
                {
                    System.out.println("You have been slain!");
                    System.out.println("GAME OVER!");
                    System.exit(0);
                }

            case SHOP:

                System.out.println("You walk into a shop in the town. The owner greets you and shows you their wares.");
                newPlayer.shopWares.forEach(w -> System.out.println("Item "+(newPlayer.shopWares.indexOf(w)+1)+": "+w+". Price: "+newPlayer.shopWares.get(newPlayer.shopWares.indexOf(w)).value));
                System.out.println("Would you like to buy or sell? Please type 'Buy', 'Sell', or 'Leave'.");
                newPlayer.tradeLoot();


        }
        return null;
    }

    public enum GameState {
        INTRO, MAP, SHOP, COMBAT
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();


        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
