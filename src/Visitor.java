public interface Visitor {
    public void visit(Enemy enemy);
    public void visit(Player player);
    public boolean visit(Empty e);
    public boolean visit(Wall w);

}
