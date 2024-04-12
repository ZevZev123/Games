package puzzle15;

public class Moves {
    private int moves;

    public Moves(){
        this.moves = 0;
    }

    public void incrementMoves(){
        this.moves = this.moves + 1;
    }

    public int getMoves(){
        return this.moves;
    }

    public void setMoves(int num){
        this.moves = num;
    }

    public String toString(){
        return "Class 'Moves' with value moves = " + this.moves;
    }

}
