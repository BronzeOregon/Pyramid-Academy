package Main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapReduxTest {

    @Test
    void testToString() {
    }

    @Test
    void addMountain() {
    }

    @Test
    void addForest() {
    }

    @Test
    void addField() {
    }

    @Test
    void addTown() {
    }

    @Test
    void addRandTerrain() {
    }

    @Test
    void mapManager() {
        MapRedux map = new MapRedux(10);
        map.mapManager();
    }

    @Test
    void randInt() {
    }

    @Test
    void getTerrain() {
        MapRedux map = new MapRedux(10);
        map.mapManager();
        System.out.println(map.getTerrain(3,6));
        System.out.println(map.getTerrain(6,3));
    }
}