package minesweeper;

public class MyDataMine implements Comparable<MyDataMine> {
    private long time;

    public MyDataMine(long time){
        this.time = time;
    }

    public void setTime(long newTime){
        this.time = newTime;
    }

    public long getTime(){
        return this.time;
    }

    public String toString(){
        return "Time: " + this.time;
    }

    public int compareTo(MyDataMine other){
        return Long.compare(this.time, other.getTime());
    }
}
