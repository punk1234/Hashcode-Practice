package akejufatai.practice.pizza;

import akejufatai.practice.pizza.model.Pizza;
import akejufatai.practice.pizza.model.SliceSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by AKEJU  FATAI on 2018-02-26.
 */
public class PizzaFileReader {

    private static final String fileName = "./input/big.in";
    private static final String space = " ";
    public static final char tomato = 'T';
    private static final char mushroom = 'M';

    public static Pizza execute(){

        File pizzaFile = new File(fileName);
        if(pizzaFile.exists()){

            try{
                Scanner input = new Scanner(pizzaFile);

                String firstLine = input.nextLine();
                String[] firstLineSplit = firstLine.split(space);

                int r = toInt(firstLineSplit[0]);
                int c = toInt(firstLineSplit[1]);
                int l = toInt(firstLineSplit[2]);
                int h = toInt(firstLineSplit[3]);

                SliceSettings.minCellCountPerIngredient = l;
                SliceSettings.maxTotalCellCount = h;

                Pizza pizza = new Pizza(c,r);

                int tomatoCount = 0;
                for(int rowIndex = 0; rowIndex < r; rowIndex++){
                    String line = input.nextLine();
                    for(int columnIndex = 0; columnIndex < c; columnIndex++){
                        if(line.charAt(columnIndex) == tomato){
                            pizza.set(rowIndex,columnIndex,true);
                            tomatoCount++;
                        }
                        else{
                            pizza.set(rowIndex,columnIndex,false);
                        }
                    }
                }
                pizza.setTomatoCount(tomatoCount);
                pizza.setMushroomCount(pizza.getCellCount() - tomatoCount);

                return pizza;

            }catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch(StringIndexOutOfBoundsException e){
                e.printStackTrace();
            }

        }

        return null;

    }

    private static int toInt(String value){

        return Integer.parseInt(value);

    }

}