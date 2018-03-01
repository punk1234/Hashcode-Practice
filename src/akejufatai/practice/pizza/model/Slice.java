package akejufatai.practice.pizza.model;

/**
 * Created by AKEJU  FATAI on 2018-02-26.
 */
public class Slice {

    private Cell start;
    private Cell end;

    public Slice(Cell start, Cell end){
        this.start = start;
        this.end = end;
    }

    public Cell getStart(){
        return start;
    }

    public Cell getEnd(){
        return end;
    }

    public int getWidth(){
        return (end.getX() - start.getX() + 1);
    }

    public int getHeight(){
        return (end.getY() - start.getY() + 1);
    }

    public int getCellCount(){
        return (getWidth() * getHeight());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(start.getX());
        sb.append(" ");
        sb.append(start.getY());
        sb.append(" ");
        sb.append(end.getX());
        sb.append(" ");
        sb.append(end.getY());

        return sb.toString();
    }
}