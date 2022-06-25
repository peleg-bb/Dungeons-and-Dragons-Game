package Backend.Tile;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position getPosition(){
        return this;
    }

    public double range(Position other){
        int m = other.x-x;
        int n = other.y-y;
        double ans = Math.sqrt((double)((m*m) + (n*n)));
        return ans;
    }
    public int compareTo(Position position) {
        if(y<position.y){
            return -1;
        }
        if(y>position.y){
            return 1;
        }
        else{
            if(x<position.x){
                return -1;
            }
            if(x>position.x){
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    public void setPosition(Position point) {
        this.x = point.x;
        this.y = point.y;
    }
}
