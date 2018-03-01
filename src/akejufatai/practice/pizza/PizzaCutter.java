package akejufatai.practice.pizza;

import akejufatai.practice.pizza.model.*;

import java.util.Random;

/**
 * Created by AKEJU  FATAI on 2017-11-28.
 */
public class PizzaCutter {

    private static final Random random = new Random();

    private static SliceCollection sliceCollection = new SliceCollection();

    private static final int sliceRandomCutCount = 3;

    private static final int iterations = 20000000;

    public static SliceCollection execute(Pizza pizza){

        for(int iter = 0; iter < iterations; iter++){

            // pick a random point or cell in the pizza

            Cell c = getRandomCell(pizza.getHeight(),pizza.getWidth());

            if(pizza.get(c.getY(),c.getX()) != null){
                Slice s = pizza.getSlice(c,SliceSettings.maxTotalCellCount,SliceSettings.maxTotalCellCount);
                if(s.getCellCount() > SliceSettings.maxTotalCellCount){
                    s = cut(s,pizza);
                }
                else{
                    if(!pizza.hasMinimumIngredient(s)){
                        continue;
                    }
                }
                if(s != null){
                    pizza.remove(s);
                    sliceCollection.add(s);
                }
            }
        }

        return sliceCollection;

    }

    private static Cell getRandomCell(int rBound, int cBound){

        int r = random.nextInt(rBound);
        int c = random.nextInt(cBound);

        Cell cell = new Cell(c,r);

        return cell;

    }

    public static Slice cut(Slice s, Pizza pizza){

        double cost = 2;
        Slice mSlice = null;
        for(int index = 0; index < sliceRandomCutCount; index++){
            Cell c = getRandomCell(s.getHeight(),s.getWidth());
            Cell cc = new Cell(s.getStart().getX() + c.getX(),s.getStart().getY() + c.getY());

            int rRandom = 0;
            int d = s.getEnd().getY() - cc.getY();
            if(d != 0){
                rRandom = random.nextInt(d);
            }

            int rDenominator = rRandom == 0 ? 1:rRandom;

            int cRandom = random.nextInt((int)Math.ceil(SliceSettings.maxTotalCellCount / rDenominator));

            int w = s.getEnd().getX() - cc.getX();
            if(cRandom > w){
                cRandom = w;
            }

            Slice cutSlice = new Slice(cc,new Cell(cc.getX()+cRandom,cc.getY()+rRandom));
            if(pizza.hasMinimumIngredient(cutSlice)){
                double cCost = getCost(cutSlice,pizza);
                if(cCost < cost){
                    mSlice = cutSlice;
                    cost = cCost;
                }

            }
        }

        return mSlice;

    }

    private static double getCost(Slice s, Pizza pizza){

        int tomatoCount = pizza.getTomatoCount(s);
        int sliceCellCount = s.getCellCount();

        /* double cost = (tomatoCount/sliceCellCount) - (pizza.getTomatoCount()/pizza.getCellCount()); */
        double cost = ((double)tomatoCount/sliceCellCount) - ((double)pizza.getTomatoCount()/pizza.getCellCount());

        return Math.abs(cost);

    }

}
