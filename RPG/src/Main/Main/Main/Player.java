package Main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player extends Asset{

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "\uD83E\uDDCD";
    }

    ArrayList<Loot> inventory = new ArrayList<>();
    Loot.Weapon equippedWeapon;
    Loot.Armor equippedArmor;
    int playerGold = 0;
    String playerName;

    //Stats
    int playerLvl;
    int playerStr;
    int playerDex;
    int playerCon;
    int playerCurrentXP;
    int playerMaxHealth;
    int playerCurrentHealth;

    //Inventory for shops (Holder)
    ArrayList<Loot> shopWares = new ArrayList<>();


    public void initializePlayer() {
        //player starts with no items equipped

        //Slot 0 is for weapon
        Loot.Weapon equippedWeapon = new Loot.Weapon();
        //Other slot is for armor
        Loot.Armor equippedArmor = new Loot.Armor();


        System.out.println(("Equipment: "+equippedWeapon+" "+equippedArmor));

        System.out.println("Greetings! What is your name, adventurer?");
        playerName = getInput();

        playerLvl = 1;
        System.out.println("Level: " +playerLvl);
        playerStr = (3*randInt(3,6));
        System.out.println("Strength: "+playerStr);
        playerDex = (3*randInt(3,6));
        System.out.println("Dexterity: "+playerDex);
        playerCon = (3*randInt(3,6));
        System.out.println("Constitution: "+playerCon);
        playerMaxHealth = 20+(2*playerCon)+(playerLvl);
        System.out.println("Max Health: "+playerMaxHealth);
        playerCurrentHealth=playerMaxHealth;
        System.out.println("Current Health: "+playerCurrentHealth);

        //Give Player a stipend of Gold
        playerGold = 5;
        System.out.println(playerGold);
    }

    public void playerHealthUpdate(int healthChange)
    {
        //This defaults to removing health, set healthChange var to negative to heal.
        playerCurrentHealth -= healthChange;
    }

    public void displayEquipment(){
        if(equippedArmor!=null && equippedWeapon!=null)
        {
            System.out.println(("Equipment: "+this.equippedWeapon.toString()+" "+this.equippedArmor.toString()));
        } else if(equippedArmor!=null)
        {
            System.out.println("Equipment: "+this.equippedArmor.toString());
        } else if(equippedWeapon!=null)
        {
            System.out.println("Equipment: "+this.equippedWeapon.toString());
        } else {
            System.out.println("You have no items equipped.");
        }
    }

    public void takeAction(String input)
    {
        switch (input)
        {
            case "Inventory":
                displayEquipment();
                displayInventory();
            break;
            case "Equip": equipItem();
            break;
            case "Move":
                System.out.println("Which direction would you like to move in? Type 'North' to move North, 'South' for South, etc.");
                movePlayer();
            break;
            case "Attack":
                Runtime.map.enemy.goblinHPCurrent -= this.attack();

            break;
            case "Item":
                this.useItem();

                break;
            case "Shop":

                if(Runtime.map.getTerrain(this.x, this.y).getClass()==Town.class)
                {
                    shopWares = populateWares();
                    Runtime.gameState= Runtime.GameState.SHOP;
                }

            break;

            case "Rest":
                this.playerCurrentHealth += (this.playerCon*2);

                System.out.println("You rest and heal for "+(this.playerCon*2)+".");

                break;

        }
    }

    public Player tradeLoot() {

        String input = getInput();

        displayInventory();

        boolean loop = true;

        do {
            if (input.equals("Buy"))
            {
                System.out.println("Please enter the number of the item you would like to purchase.");
                input=getInput();
                this.inventory.add(this.shopWares.get(Integer.parseInt(input)-1));
                if(this.playerGold<this.shopWares.get(Integer.parseInt(input)-1).value)
                {
                    System.out.println("You don't have enough gold to afford that!");
                }
                this.playerGold-=this.shopWares.get(Integer.parseInt(input)-1).value;
                this.shopWares.remove(Integer.parseInt(input)-1);

            } else if (input.equals("Sell"))
            {
                System.out.println("Please enter the number of the item in your inventory you want to sell.");
                input = getInput();
                this.playerGold+=this.inventory.get(Integer.parseInt(input)-1).value;
                this.shopWares.add(this.inventory.get(Integer.parseInt(input)-1));
                this.inventory.remove(Integer.parseInt(input)-1);

            } else if (input.equals("Leave"))
            {
                System.out.println("The shopkeeper waves as you go.");
                loop = false;
            } else{
                System.out.println("I'm sorry, I didn't catch that. Please type 'Buy', 'Sell', or 'Leave'.");
            }

        } while (loop);

        Runtime.gameState = Runtime.GameState.MAP;

        return this;
    }

    public ArrayList<Loot> populateWares() {
        ArrayList<Loot> wares = new ArrayList<>();

        for(int i =0; i<5; i++)
        {
            int roll = randInt(1,3);
            if(roll==1)
                wares.add(Loot.weaponTable.get(randInt(0,Loot.weaponTable.size()-1)));
            if(roll==2)
                wares.add(Loot.armorTable.get(randInt(0,Loot.armorTable.size()-1)));
            if(roll==3)
                wares.add(Loot.otherTable.get(randInt(0,Loot.otherTable.size()-1)));
        }

        return wares;
    }

    public Player buyAndSell(){

        String input = getInput();

        if(input.equals("Buy"))
        {
            System.out.println("Which item would you like to purchase? Please enter the number of the item you wish to purchase.");
            input = getInput();
        }

        return this;
    }

    public int attack() {

        int damage;

        if(this.equippedWeapon==null)
        {
            damage = (randInt(1, this.playerStr)+this.playerStr);
            System.out.println("You swing your fist at the goblin!");
        } else
        {
            damage = (randInt(1, this.equippedWeapon.getDamageDice()+this.playerStr)+this.playerStr+this.equippedWeapon.getDamageBonus());
            System.out.println("You swing your "+this.equippedWeapon.toString()+" at the goblin!");
        }

        System.out.println("It takes "+damage+" damage!");

        return damage;
    }

    public Player useItem() {

        ArrayList<Object> flasks = new ArrayList<>();
        ArrayList<Object> potions = new ArrayList<>();

        inventory.forEach(e -> {
            if(e.getClass()==Loot.Other.class)
            {
                if(((Loot.Other) e).name.equals("FLASK"))
                {
                    flasks.add(e);
                } else if(((Loot.Other) e).name.equals("POTION"))
                {
                    potions.add(e);
                }
            }
        });
        if(!flasks.isEmpty())
        {
            System.out.println("You have "+(flasks.size())+" flasks");
        } else {
            System.out.println("You have no flasks!");
        }
        if(!potions.isEmpty())
        {
            System.out.println("You have "+(potions.size())+" potions");
        } else {
            System.out.println("You have no potions!");
        }
        if(flasks.isEmpty() && potions.isEmpty())
            return this;
        System.out.println("Please enter the type of item you want to use. Type 'Potion' to use a potion to heal, otherwise type");

        String input = getInput();

        if(input.equals("Flask"))
        {
            int damage = (4*randInt(1,6))+4;
            Runtime.map.enemy.goblinHPCurrent-= damage;
            System.out.println("Your flask explodes as it impacts, causing "+damage+" to the goblin!");
            Runtime.newPlayer.inventory.remove(flasks.get(0));
        } if(input.equals("Potion")) {
            int healValue =(3*randInt(1,8))+this.playerCon;
            this.playerCurrentHealth+= healValue;
            System.out.println("Your potion heals you for "+healValue+" health!");
            Runtime.newPlayer.inventory.remove(potions.get(0));
        }

        return this;
    }

    public boolean isPlayerDead(){

        boolean dead = false;

        if(this.playerCurrentHealth<=0) {
            dead=true;
        }

        return dead;
    }

    public void displayInventory()
    {
        addGold();
        System.out.println("You have "+playerGold+" gold pieces.");
        int i = 0;

        for (Object o : inventory) {

            System.out.println("Item "+ ++i +": ");

            if (o instanceof Loot.Weapon) {
                System.out.println(((Loot.Weapon) o).name);
                System.out.println("Maximum Damage: " + ((Loot.Weapon) o).damageDice);
                System.out.println("Requires level: " + ((Loot.Weapon) o).reqLvl);
                System.out.println("Required Strength: " + ((Loot.Weapon) o).reqStr);
                System.out.println("Required Dexterity: " + ((Loot.Weapon) o).reqDex);
                System.out.println("Value: " + ((Loot.Weapon) o).value);
                System.out.println();
            }
            if (o instanceof Loot.Armor) {
                System.out.println(((Loot.Armor) o).name);
                System.out.println("Armor Value: " + ((Loot.Armor) o).armor);
                System.out.println("Requires level: " + ((Loot.Armor) o).reqLvl);
                System.out.println("Requires Strength: " + ((Loot.Armor) o).reqStr);
                System.out.println("Value: " + ((Loot.Armor) o).value);
                System.out.println();
            }
            if (o instanceof Loot.Other) {
                System.out.println(((Loot.Other) o).name);
                System.out.println();
            }

        }

    }

    public void updateLevel(int xpGained)
    {
        //Adds xp, then checks to see if player has earned enough to level up based upon the square of their level * 1000. If so, the player gains a level,
        //and calculates new stat totals. Also restores all missing HP.
        playerCurrentXP += xpGained;
        if(playerCurrentXP>=(playerLvl*(1000*playerLvl)))
        {
            playerLvl++;
            System.out.println("Congratulations! You have leveled up! You are now level: " +playerLvl+"!");
            playerStr = playerStr+(randInt(3,6));
            playerDex = playerDex+(randInt(3,6));
            playerCon = playerCon+(randInt(3,6));
            playerMaxHealth = 20+(2*playerCon)+(playerLvl);
            System.out.println("Your health is restored!");
            playerCurrentHealth = playerMaxHealth;
        }
    }

    public void equipItem() {
        System.out.println("What do you want to equip? A weapon, or armor?\nPlease type either 'Weapon' or 'Armor', or 'Return' to enter a new command.");

        String input = getInput();


        if (String.valueOf(input).equals("Weapon")) {
            System.out.println("Which weapon do you want to equip? Please type the number that corresponds to it's position in your inventory.");
            input = getInput();
            try{
                if (inventory.contains(inventory.get((Integer.parseInt(input))-1))) {
                    System.out.println("Item " + input + " Has been equipped!");
                    equippedWeapon = (Loot.Weapon) inventory.get((Integer.parseInt(input))-1);
                } else {
                    System.out.println("I'm sorry, I couldn't find that in your inventory. Please check your bags and try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("That's not a weapon! Please try again!");
            }
        }
        if (String.valueOf(input).equals("Armor")) {
            System.out.println("Which armor do you want to equip? Please type the number that corresponds to it's position in your inventory.");
            input = getInput();
            try{
                if (inventory.contains(inventory.get((Integer.parseInt(input))-1))) {
                    System.out.println("Item " + input + " Has been equipped!");
                    equippedArmor = (Loot.Armor) inventory.get((Integer.parseInt(input))-1);
                } else {
                    System.out.println("I'm sorry, I couldn't find that in your inventory. Please check your bags and try again!");
                }
            }catch (NumberFormatException e) {
                System.out.println("That's not armor! Please try again!");
            }
        }

            if (String.valueOf(input).equals("Return")) {
                System.out.println();
            }

        }


    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void movePlayer()
    {
        String input = getInput();
        if(input.equals("North"))
        {
            this.moveNorth();
        }
        else if(input.equals("South"))
        {
            this.moveSouth();
        }
        else if(input.equals("East"))
        {
            this.moveEast();
        }
        else if(input.equals("West"))
        {
            this.moveWest();
        }
        else
            System.out.println("I'm sorry, I didn't get that.");
    }

    public String getInput()
    {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void addGold() {
        for (int i =0; i<inventory.size();i++) {
            if(inventory.get(i) instanceof Loot.Other)
            {
                if((((Loot.Other) inventory.get(i)).name).equals(String.valueOf(itemCores.GOLD))){
                            playerGold++;
                            inventory.remove(i);
                }
            }
        }
    }
}
