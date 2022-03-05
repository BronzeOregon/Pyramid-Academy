package Main;

import Main.itemCores.armorCores;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Loot {

    static ArrayList<Weapon> weaponTable = new ArrayList<>();
    static ArrayList<Armor> armorTable = new ArrayList<>();
    static ArrayList<Other> otherTable = new ArrayList<>();
    int value;

    public static class Armor extends Loot
    {
        String name;
        int armor;
        int value;
        int reqStr;
        int reqLvl;



        public Armor() {
        }

        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return name;
        }

        public int getArmor() {
            return armor;
        }

        public int getValue() {
            return value;
        }

        public int getReqStr() {
            return reqStr;
        }

        public int getReqLvl() {
            return reqLvl;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setArmor(int armor) {
            this.armor = armor;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setReqStr(int reqStr) {
            this.reqStr = reqStr;
        }

        public void setReqLvl(int reqLvl) {
            this.reqLvl = reqLvl;
        }
    }

    public static class Weapon extends Loot
    {
        String name;
        int damageDice;
        int damageBonus;
        int value;
        int reqStr;
        int reqDex;
        int reqLvl;

        public Weapon() {
        }

        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDamageDice() {
            return damageDice;
        }

        public void setDamageDice(int damageDice) {
            this.damageDice = damageDice;
        }

        public int getDamageBonus() {
            return damageBonus;
        }

        public void setDamageBonus(int damageBonus) {
            this.damageBonus = damageBonus;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getReqStr() {
            return reqStr;
        }

        public void setReqStr(int reqStr) {
            this.reqStr = reqStr;
        }

        public int getReqDex() {
            return reqDex;
        }

        public void setReqDex(int reqDex) {
            this.reqDex = reqDex;
        }

        public int getReqLvl() {
            return reqLvl;
        }

        public void setReqLvl(int reqLvl) {
            this.reqLvl = reqLvl;
        }
    }

    public class Other extends Loot
    {
        String name;
        int value;
        int gearDice;

        @Override
        public String toString() {
            return name;
        }

        public int healingItem()
        {

            int healValue = (3*randInt(1,gearDice))+3;
            return healValue;
        }

        public int grenadeItem()
        {
            int damageDealt = (4*randInt(1,gearDice))+4;
            return damageDealt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static int randInt(int min, int max) {

        Random rand = new Random(System.currentTimeMillis());


        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public Armor generateArmor()
    {
            int levelOfArmor = randInt(0,6);

            Armor newArmor = new Armor();
            newArmor.setName((String.valueOf(armorCores.getRandomArmorCore()))+" +"+levelOfArmor);
            newArmor.setArmor(levelOfArmor+randInt(0,4));
            newArmor.setReqStr((levelOfArmor+(newArmor.getArmor())/2));
            newArmor.setReqLvl(levelOfArmor);
            newArmor.setValue((newArmor.armor+levelOfArmor)*5);

            return newArmor;
    }

    public Armor generateBasicArmor()
    {
        int levelOfArmor = 1;

        Armor newArmor = new Armor();
        newArmor.setName((String.valueOf(armorCores.getRandomArmorCore()))+" +"+levelOfArmor);
        newArmor.setArmor(levelOfArmor+randInt(0,4));
        newArmor.setReqStr((levelOfArmor+(newArmor.getArmor())/2));
        newArmor.setReqLvl(levelOfArmor);
        newArmor.setValue((newArmor.armor+levelOfArmor)*5);

        return newArmor;
    }

    public Weapon generateWeapon()
    {
        int levelOfWeapon = randInt(0,6);

        Weapon newWeapon = new Weapon();
        newWeapon.setName(String.valueOf(itemCores.weaponCores.getRandomWeaponCore())+" +"+levelOfWeapon);
        newWeapon.setDamageDice(randInt(4,8));
        newWeapon.setReqStr((levelOfWeapon+(newWeapon.getDamageDice())/2));
        newWeapon.setReqLvl(levelOfWeapon);
        newWeapon.setDamageBonus(levelOfWeapon);
        newWeapon.setReqDex(levelOfWeapon+(randInt(0,3)));
        newWeapon.setValue((newWeapon.damageDice+levelOfWeapon)*10);

        return newWeapon;
    }

    public Weapon generateBasicWeapon()
    {
        int levelOfWeapon = 1;

        Weapon newWeapon = new Weapon();
        newWeapon.setName(String.valueOf(itemCores.weaponCores.getRandomWeaponCore())+" +"+levelOfWeapon);
        newWeapon.setDamageDice(randInt(4,8));
        newWeapon.setReqStr((levelOfWeapon+(newWeapon.getDamageDice())/2));
        newWeapon.setReqLvl(levelOfWeapon);
        newWeapon.setDamageBonus(levelOfWeapon);
        newWeapon.setReqDex(levelOfWeapon+(randInt(0,3)));
        newWeapon.setValue((newWeapon.damageDice+levelOfWeapon)*10);

        return newWeapon;
    }

    public Other generateOther()
    {
        Other newOther = new Other();

        newOther.setName(String.valueOf(itemCores.getRandomItemCore()));
        if(Objects.equals(newOther.getName(), String.valueOf(itemCores.GOLD)))
            newOther.setValue(1);
        if(Objects.equals(newOther.getName(), String.valueOf(itemCores.POTION)))
        {
            newOther.setValue(5);
            newOther.gearDice=8;
        }
        if(Objects.equals(newOther.getName(), String.valueOf(itemCores.FLASK)))
        {
            newOther.setValue(10);
            newOther.gearDice=6;
        }

        return newOther;
    }

    public void populateLoot() {
        for(int i = 0; i<100; i++)
        {
            int itemType = randInt(1,3);
            if(itemType==1)
            {
                weaponTable.add(generateWeapon());
            }
            if(itemType==2)
            {
                armorTable.add(generateArmor());
            }
            if(itemType==3)
            {
                otherTable.add(generateOther());
            }

        }
    }
}
