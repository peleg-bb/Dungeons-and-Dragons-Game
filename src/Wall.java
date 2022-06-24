public class Wall extends Tile{
    public Wall(){
        super('#');
    }
    public void accept(Unit u){
        u.visit(this);
    }
}
