package akejufatai.practice.pizza.model;

import akejufatai.practice.pizza.PizzaFileReader;

/**
 * Created by AKEJU  FATAI on 2017-11-28.
 */
public class Pizza {

    private int width;
    private int height;
    private Boolean[][] content;
    private int tomatoCount;
    private int mushroomCount;

    public Pizza(int width, int height){

        this.width = width;
        this.height = height;
        tomatoCount = 0;
        mushroomCount = 0;
        content = new Boolean[height][width];

    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void set(int rowIndex, int columnIndex, Boolean value){
        content[rowIndex][columnIndex] = value;
    }

    public Boolean get(int rowIndex, int columnIndex){
        return content[rowIndex][columnIndex];
    }

    public int getTomatoCount(){
        return tomatoCount;
    }

    public int getMushroomCount(){
        return mushroomCount;
    }

    public void setTomatoCount(int tomatoCount){
        this.tomatoCount = tomatoCount;
    }

    public void setMushroomCount(int mushroomCount){
        this.mushroomCount = mushroomCount;
    }

    public int getCellCount(){
        return (width * height);
    }

    public Slice getSlice(Cell cell, int maxWidth, int maxHeight){

        int x = cell.getX();
        int y = cell.getY();

        if(get(y,x) != null){
            int riBound = y+maxHeight;
            int ciBound = x+maxWidth;

            if(riBound > getHeight()){
                riBound = getHeight();
            }

            if(ciBound > getWidth()){
                ciBound = getWidth();
            }

            for(int ri = y; ri < riBound; ri++){
                for(int ci = x; ci < ciBound; ci++){
                    if(get(ri,ci) == null){
                        if(ci == x){
                            return new Slice(cell,new Cell(ciBound-1,ri-1));
                        }
                        else{
                            ciBound = ci;
                        }
                    }
                }
            }
            return new Slice(cell,new Cell(ciBound-1,riBound-1));
        }
        return new Slice(cell,cell);

    }

    public int getTomatoCount(Slice s){

        int tomatoCount = 0;
        for(int y = s.getStart().getY(); y <= s.getEnd().getY(); y++){
            for(int x = s.getStart().getX(); x <= s.getEnd().getX(); x++){
                if(get(y,x) == true){
                    tomatoCount++;
                }
            }
        }
        return tomatoCount;

    }

    public boolean hasMinimumIngredient(Slice s){

        int tomatoCount = getTomatoCount(s);
        int mushroomCount = s.getCellCount() - tomatoCount;
        if(tomatoCount >= SliceSettings.minCellCountPerIngredient && mushroomCount >= SliceSettings.minCellCountPerIngredient){
            return true;
        }
        return false;
    }

    public void remove(Slice s){
        for(int x = s.getStart().getX(); x <= s.getEnd().getX(); x++){
            for(int y = s.getStart().getY(); y <= s.getEnd().getY(); y++){
                set(y,x,null);
            }
        }
    }

}
