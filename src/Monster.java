public class Monster extends Enemy{
    private int visionRange;

    public Monster(char tile, String name, int healthA, int attackPoints, int defensePoint, int expirience,int visionRange){
        super(tile,name,healthA, attackPoints, defensePoint, expirience);
        this.visionRange = visionRange;
    }
    public void onGameTick(Player p){
        if(this.position.range(p.position)<visionRange){
            int dx = this.position.getX()-p.position.getX();
            int dy = this.position.getY()-p.position.getY();
            if(Math.abs(dx)>Math.abs(dy)){
                if(dx>0){
                    position.setX(position.getX()-1);
                }
                else{
                    position.setX(position.getX()+1);
                }
            }

            else{
                if(dy>0){
                    position.setY(position.getY()+1);
                }
                else{
                    position.setY(position.getY()-1);
                }
            }
        }
        else{
            int move = (int) (Math.random()*(4-0)) + 0;
            switch(move){
                case 0:break;
                case 1: position.setX(position.getX()-1); break;
                case 2 : position.setX(position.getX()+1); break;
                case 3 : position.setY(position.getY()+1); break;
                case 4 : position.setY(position.getY()-1); break;
            }
        }
    }

}
