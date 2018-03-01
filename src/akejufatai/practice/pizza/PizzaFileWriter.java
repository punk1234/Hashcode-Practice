package akejufatai.practice.pizza;

import akejufatai.practice.pizza.model.SliceCollection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by AKEJU  FATAI on 2018-02-26.
 */
public class PizzaFileWriter {

    private static final String outputFileName = "output.txt";

    public static void execute(SliceCollection sliceCollection){

        File file = new File(outputFileName);

        FileWriter fw = null;
        BufferedWriter bw = null;

        try{
            if(!file.exists()){
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsolutePath(),false);
            bw = new BufferedWriter(fw);

            bw.write(sliceCollection.count()+"\n");
            for(int sliceIndex = 0; sliceIndex < sliceCollection.count(); sliceIndex++){
                bw.write(sliceCollection.get(sliceIndex).toString()+ "\n");
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(bw != null) bw.close();
                if(fw != null) fw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

}
