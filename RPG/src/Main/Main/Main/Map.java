//package Main;
//
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class Map {
//
//    public ArrayList<Terrain> displayRow = new ArrayList<>();
//    public int rowNum = 1;
//
//    public class Row extends Terrain {
//
//        public Row(int rowSize, int num){
//
//            if(rowNum==1)
//            {IntStream.rangeClosed(0,9).forEach(i -> displayRow.add(i, addMountain()));
//                    rowNum+=1;}
//            if(rowNum==2)
//            {IntStream.rangeClosed(0,2).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(3,5).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(6,6).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(7,9).forEach(i -> displayRow.add(addForest()));
//                    rowNum+=1;}
//            if(rowNum==3)
//            {IntStream.rangeClosed(0,0).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(1,3).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(4,6).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(7,7).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(8,9).forEach(i -> displayRow.add(addForest()));
//                    rowNum+=1;}
//            if(rowNum==4)
//            {IntStream.rangeClosed(0,2).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(3,3).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(4,8).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(9,9).forEach(i -> displayRow.add(addMountain()));
//                    rowNum+=1;}
//            if(rowNum==5)
//            {IntStream.rangeClosed(0,1).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(2,6).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(7,7).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(8,9).forEach(i -> displayRow.add(addMountain()));
//                    rowNum+=1;}
//            if(rowNum==6)
//            {IntStream.rangeClosed(0,1).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(2,2).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(3,3).forEach(i -> displayRow.add(addTown()));
//                    IntStream.rangeClosed(4,5).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(6,6).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(7,9).forEach(i -> displayRow.add(addMountain()));
//                    rowNum+=1;}
//            if(rowNum==7)
//            {IntStream.rangeClosed(0,0).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(1,4).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(5,5).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(6,6).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(7,9).forEach(i -> displayRow.add(addForest()));
//                    rowNum+=1;}
//            if(rowNum==8)
//            {IntStream.rangeClosed(0,1).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(2,3).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(4,4).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(5,6).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(7,7).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(8,8).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(9,9).forEach(i -> displayRow.add(addForest()));
//                    rowNum+=1;}
//            if(rowNum==9)
//            {IntStream.rangeClosed(0,2).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(3,5).forEach(i -> displayRow.add(addField()));
//                    IntStream.rangeClosed(6,6).forEach(i -> displayRow.add(addTown()));
//                    IntStream.rangeClosed(7,8).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(9,9).forEach(i -> displayRow.add(addMountain()));
//                    rowNum+=1;}
//            if(rowNum==10)
//            {IntStream.rangeClosed(0,4).forEach(i -> displayRow.add(addMountain()));
//                    IntStream.rangeClosed(5,6).forEach(i -> displayRow.add(addForest()));
//                    IntStream.rangeClosed(7,9).forEach(i -> displayRow.add(addMountain()));
//                    rowNum+=1;}
//
//
//
//        }
//
//        @Override
//        public String toString() {
//            return String.join("", displayRow.stream().map(String::valueOf).collect(Collectors.toList()));
//        }
//    }
//
//    static ArrayList<Terrain> displayMap = new ArrayList<>();
//    static ArrayList<Terrain> finMap = new ArrayList<>();
//
//    String tree = "🌲";
//    String mountain = "⛰";
//    String town = "⌂";
//    String castle = "⛫";
//    String loot = "⚱";
//    String goblin = "\uD83D\uDC7A";
//
//    public Map(int dim){
////        addMountains(13);
////        addForests(3);
////        addFields(1);
////        addForests(4);
////        addMountains(3);
////        addForests(3);
////        addFields(1);
////        addForests(5);
////        addMountains(1);
////        addFields(5);
////        addMountains(1);
////        addForests(2);
////        addFields(5);
////        addForests(1);
////        addMountain(2);
////        addForests(2);
////        addFields(1);
////        addTowns(1);
////        addFields(2);
////        addForests(1);
////        addMountain(4);
////        addFields(4);
////        addForests(1);
////        addMountain(1);
////        addForests(3);
////        addMountain(2);
////        addFields(2);
////        addForests(2);
////        addMountain(1);
////        addForests(1);
////        addFields(1);
////        addForests(1);
////        addMountain(3);
////        addFields(3);
////        addTowns(1);
////        addForests(2);
////        addMountain();
////        addForests(2);
////        addMountain();
//
//
//        IntStream.rangeClosed(1,dim).forEach(i -> finMap.add(new Row(dim, rowNum)));
//
//
//    }
//
//    @Override
//    public String toString() {
//        return String.join("\n", finMap.stream().map(String::valueOf).collect(Collectors.toList()));
//    }
//
//    public Terrain addMountain()
//    {
//        return new Mountain();
//    }
//
//    public Terrain addForest()
//    {
//        return new Forest();
//    }
//
//    public Terrain addField()
//    {
//        return new Field();
//    }
//
//    public Terrain addTown()
//    {
//        return new Town();
//    }
//
//    public static void mapManager()
//    {
//        System.out.println(new Map(10));
//    }
//}
