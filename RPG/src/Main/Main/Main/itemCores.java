package Main;

import java.util.Random;

public enum itemCores {

    GOLD, POTION, FLASK;

    public static itemCores getRandomItemCore()
    {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }

    public enum weaponCores {
        BLADE, DAGGER, BOW, SPEAR, PIKE, HATCHET, AXE, MAUL, WARHAMMER, SHORTSWORD, GREATSWORD, LONGSWORD, MACE, TRUNCHEON, CROSSBOW, HALBERD, SCYTHE;

        public static weaponCores getRandomWeaponCore()
        {
            Random rand = new Random();
            return values()[rand.nextInt(values().length)];
        }
    }

    public enum armorCores {
        GAMBESON, SPLINTMAIL, CHAINMAIL, HALFPLATE, PLATEMAIL, LEATHER, BRIGANDINE;

        public static armorCores getRandomArmorCore()
        {
            Random rand = new Random();
            return values()[rand.nextInt(values().length)];
        }
    }


}
