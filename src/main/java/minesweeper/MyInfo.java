package minesweeper;

public class MyInfo {
    private int row;
    private int col;
    private int bomb;

    public MyInfo(int row, int col, int bomb){
        this.row = row;
        this.col = col;
        this.bomb = bomb;
    }

    public MyInfo(){
        row = 20;
        col = 25;
        bomb = 90;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

    public void setBomb(int bomb){
        this.bomb = bomb;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public int getBomb(){
        return this.bomb;
    }
}
