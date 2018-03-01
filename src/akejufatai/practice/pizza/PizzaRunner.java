package akejufatai.practice.pizza;

import akejufatai.practice.pizza.model.Pizza;
import akejufatai.practice.pizza.model.SliceCollection;

/**
 * Created by AKEJU  FATAI on 2018-02-26.
 */
public class PizzaRunner {

    public static void run(){

        Pizza pizza = PizzaFileReader.execute();

        SliceCollection sliceCollection = PizzaCutter.execute(pizza);

        PizzaFileWriter.execute(sliceCollection);

    }

}
