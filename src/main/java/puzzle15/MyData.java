package puzzle15;

public class MyData implements Comparable<MyData> {
    private int moves;
    private long time;

    public MyData(){
        this.moves = 0;
        this.time = 0;
    }

    public void incrementMoves(){
        this.moves = this.moves + 1;
    }

    public long getTime(){
        return this.time;
    }

    public int getMoves(){
        return this.moves;
    }

    public void setTime(long num){
        this.time = num;
    }

    public void setMoves(int num){
        this.moves = num;
    }

    public String toString(){
        return "Moves: " + this.moves + "\tTime:" + this.time;
    }

    public int compareTo(MyData other){
        return Integer.compare(this.moves, other.moves);
    }
}
