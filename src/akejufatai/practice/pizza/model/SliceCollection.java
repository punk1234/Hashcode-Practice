package akejufatai.practice.pizza.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2018-02-26.
 */
public class SliceCollection {

    private List<Slice> content;

    public SliceCollection(){

        content = new ArrayList<>();

    }

    public void add(Slice slice){
        content.add(slice);
    }

    public Slice get(int index){
        return content.get(index);
    }

    public int count(){
        return content.size();
    }

}
