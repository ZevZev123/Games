package minesweeper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyDataMine implements Comparable<MyDataMine> {
    private long time;
    private int col;
    private int row;
    private int numBomb;

    private float ratio;

    @JsonCreator
    public MyDataMine(@JsonProperty("time") long time,
            @JsonProperty("col") int col,
            @JsonProperty("row") int row,
            @JsonProperty("numBomb") int bomb,
            @JsonProperty("ratio") int ratio){

        this.time = time;
        this.col = col;
        this.row = row;
        this.numBomb = bomb;

        updateRatio();
    }

    public MyDataMine(long time, int col, int row, int bomb){
        this.time = time;
        this.col = col;
        this.row = row;
        this.numBomb = bomb;

        updateRatio();
    }

    public void setTime(long newTime){
        this.time = newTime;
    }

    public void setCol(int newCol){
        this.col = newCol;
        updateRatio();
    }

    public void setRow(int newRow){
        this.row = newRow;
        updateRatio();
    }

    public void setBomb(int newNumBomb){
        this.numBomb = newNumBomb;
        updateRatio();
    }

    public void setRatio(float newRatio){
        this.ratio = newRatio;
        updateRatio();
    }

    public void updateRatio(){
        this.ratio = ((float) this.numBomb / (col * row)) * 1000;
    }

    public long getTime(){
        return this.time;
    }

    public int getCol(){
        return this.col;
    }

    public int getRow(){
        return this.row;
    }
    
    public int getNumBomb(){
        return this.numBomb;
    }
    
    public float getRatio(){
        return this.ratio;
    }
    
    public String toString(){
        return String.format("Field: %dx%d - Bombs: %d - Time: %d - Ratio: %.2f", col, row, numBomb, time, ratio);
    }

    public int compareTo(MyDataMine other){
        return Float.compare(this.ratio/this.time, other.getRatio()/other.getTime());
    }
}
