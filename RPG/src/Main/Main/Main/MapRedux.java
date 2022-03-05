package Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapRedux {

    public class Row{
        public ArrayList<Terrain> displayRow = new ArrayList<>();
        public Row(int rowSize, int yAxis){
            IntStream.range(0,rowSize).forEach(i -> displayRow.add(addRandTerrain(randInt(1,20), i, yAxis)));
        }

        @Override
        public String toString() {
            return String.join(" ", displayRow.stream().map(String::valueOf).collect(Collectors.toList()));
        }
    }

    ArrayList<Row> masterMap = new ArrayList<>();
    static ArrayList<Row> displayMap = new ArrayList<>();
    ArrayList<Asset> actors = new ArrayList<>();
    ArrayList<Terrain> alteredTerrain = new ArrayList<>();

    String loot = "⚱";


    public MapRedux(int dim){

        IntStream.range(0,dim).forEach(i -> displayMap.add(new Row(dim,i)));
        masterMap = displayMap;
    }

    public Terrain getTerrain(int x, int y)
    {
        return displayMap.get(y).displayRow.get(x);
    }

    public String getDefaultTerrain(int x, int y)
    {
        return displayMap.get(y).displayRow.get(x).getDefaultState();
    }

    @Override
    public String toString() {
        return String.join("\n", displayMap.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    public Terrain addMountain(int xAxis, int yAxis)
    {
        return new Mountain(xAxis,yAxis);
    }

    public Terrain addForest(int xAxis, int yAxis)
    {
        return new Forest(xAxis,yAxis);
    }

    public Terrain addField(int xAxis, int yAxis)
    {
        return new Field(xAxis,yAxis);
    }

    public Terrain addTown(int xAxis, int yAxis)
    {
        return new Town(xAxis,yAxis);
    }

    public Terrain addRandTerrain(int rand, int xAxis, int yAxis)
    {
        if(rand==1)
            return addTown(xAxis,yAxis);
        if(rand<=7)
            return addForest(xAxis,yAxis);
        if(rand<=12)
            return addMountain(xAxis,yAxis);
        else
            return addField(xAxis,yAxis);
    }

    public MapRedux addActor(Asset actor)
    {
        actors.add(actor);
        return this;
    }


    public MapRedux updateState(){

        alteredTerrain.forEach(Terrain::resetState);
        alteredTerrain.clear();
        for(Asset actor : actors) {
                var t = getTerrain(actor.x, actor.y);
                alteredTerrain.add(t);
                t.setState(actor.toString());
            }
        return this;
    }

    Goblin enemy;

    public MapRedux checkCombat(){

        if(actors.contains(Runtime.newPlayer))
        {
            var p = actors.get(actors.indexOf(Runtime.newPlayer));
            actors.forEach(asset -> {
                if (asset.getClass() != p.getClass())
                {
                    if (asset.x == p.x && asset.y == p.y)
                    {
                        enemy = (Goblin) asset;
                        System.out.println("You have encountered a goblin!");
                        Runtime.gameState = Runtime.GameState.COMBAT;
                    }
                }
            });
        }

        return this;
    }

    public MapRedux goblinMove(){
        if(actors.contains(Runtime.newPlayer))
        {
            var p = actors.get(actors.indexOf(Runtime.newPlayer));
            actors.forEach(asset -> {
                if (asset.getClass() != p.getClass())
                {
                    if (asset.x != p.x)
                    {
                        int xDif = asset.x-p.x;
                        if(xDif<0)
                            asset.moveEast();
                        if(xDif>0)
                            asset.moveWest();
                    } else if(asset.y != p.y)
                    {
                        int yDif = asset.y-p.y;
                        if(yDif<0)
                            asset.moveSouth();
                        if(yDif>0)
                            asset.moveNorth();
                    }

                }
            });
        }
        return this;
    }

    public void mapManager()
    {
        System.out.println(toString());
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();


        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void resetTerrain(int x, int y)
    {
        displayMap.get(x).displayRow.get(y).resetState();
    }
}


