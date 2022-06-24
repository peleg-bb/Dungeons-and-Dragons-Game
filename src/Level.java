import java.util.List;

public class Level {
    private Player p;
    private List<Enemy> enemy;
    private List<Tile> tile;
    private boolean gameOver;
    private boolean done;

    public Level(Player p, List<Enemy> t ){
        this.p = p;
        this.enemy = t;
        gameOver = false;
        done = false;
    }
    public void GameTick(){
        if(p.isDead()){//add is dead
            gameOver = true;
        }
        else{
            p.onGameTick();
            for (Enemy e : enemy) {
                e.onEnemyTurn(p);
            }
        }
        if(enemy.isEmpty()){
            done = true;
        }
    }
    public void move(char move){
        int x =p.getPosition().getX();
        int y = p.getPosition().getY();
        Position point = new Position(x,y);
        if(move == 'w'){
            p.position.setY(y+1);
        }
        if(move == 's'){
            p.position.setY(y-1);
        }
        else if(move == 'd'){
            p.position.setX(x+1);
        }
        else if(move == 'w'){
            p.position.setX(x-1);
        }
        else if(move == 'c'){
            p.onAbilityCast(enemy);
        }
        if(checkPoint()){
            p.position.setPosition(point);
        }
        else{
            FightEnemy();
        }
    }

    private void FightEnemy() {
        for(Enemy e : enemy){
            if(e.position.equals(p.position)){
                p.interact(e);
                if(e.isDead()){
                    //remove from board:)
                }

            }
        }
    }

    public boolean checkPoint(){
        for(Tile t : tile){
            if(p.interact(t)){ //I want to know if its a wall or not
                if(t.position.equals(p.position)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }
}
