package BackendTests;

import Backend.Tile.Position;
import Backend.Tile.StaticTiles.Empty;
import Backend.Tile.StaticTiles.Wall;
import Backend.Tile.Unit.Enemy.Enemy;
import Backend.Tile.Unit.Enemy.Monster;
import Backend.Tile.Unit.Enemy.Trap;
import Backend.Tile.Unit.Player.*;

import java.util.List;


import static org.junit.Assert.*;

public class PlayerTest {

    Mage mage;
    Trap trap;
    Monster monster;
    Wall wall;
    Empty empty;
    List<Enemy> enemies;

    @org.junit.Before
    public void setUp() throws Exception {
        mage = new Mage("John", new Position(3, 3), 100,
                100, 100, 100, 20, 20, 2, 1);

        trap = new Trap('T', new Position(1, 1), "Trap", 1,
                10, 1, 10, 1, 1);
        wall = new Wall(new Position(0, 3));
        empty = new Empty(new Position(0, 4));
        monster = new Monster('M', new Position(2, 1), "Monster", 10000,
                1000, 1000, 100, 100);
        enemies = null;
    }

    @org.junit.Test
    public void levelUp() {
        for (int i = 0; i < 50; i++) {
            mage.setExperience();
        }
        assertEquals(mage.getExperience(), 0);
    }

    @org.junit.Test
    public void accept() {
    }

    @org.junit.Test
    public void visit() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        mage.setPosition(p1);
        wall.setPosition(p2);
        mage.visit(wall);
        assertEquals(mage.getPosition(), p1);
    }

    @org.junit.Test
    public void testVisit() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        mage.setPosition(p1);
        empty.setPosition(p2);
        mage.visit(empty);
        assertEquals(mage.getPosition(), p2);
    }

    // Tests an unsuccessful empty visit
    @org.junit.Test
    public void testBadVisit() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        mage.setPosition(p1);
        empty.setPosition(p2);
        mage.visit(empty);
        assertNotEquals(mage.getPosition(), p1);

    }

    // Tests an unsuccessful empty visit
    @org.junit.Test
    public void testBadVisit2() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        mage.setPosition(p1);
        wall.setPosition(p2);
        mage.visit(empty);
        assertNotEquals(mage.getPosition(), p2);
    }

    @org.junit.Test
    public void testVisit1() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        mage.setPosition(p1);
        trap.setPosition(p2);
        mage.visit(trap); // Todo: Set up with a callback so that the combat can be triggered
        assertEquals(mage.getPosition(), p2);
        assertEquals(mage.getExperience(), 10);
    }

    @org.junit.Test
    public void onKill() { // Player (mage) kills enemy (trap)
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        mage.setPosition(p1);
        trap.setPosition(p2);
        mage.visit(trap); // Todo: Set up with a callback so that the combat can be triggered
        assertEquals(mage.getPosition(), p2);
        assertEquals(mage.getExperience(), 10);
    }

    @org.junit.Test
    public void onDeath() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        mage.setPosition(p1);
        monster.setPosition(p2);
        monster.visit(mage); // Todo: Set up with a callback so that the combat can be triggered
        assertEquals(mage.isDead(), true);
    }
}