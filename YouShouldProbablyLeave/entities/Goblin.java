package entities;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.io.File;

import ai.Ai;
import ai.pathfinding.DefaultPathfinding;
import ai.pathfinding.Pathfinding;
import display.PathedImage;

public class Goblin extends Ai implements PathedImage {
    public static final File file = new File("resource//image//entities//Goblin.gif");
    private Pathfinding pathfind;
    
    // to make new entities you can make a script almost the same as this file
    // simply change the name of the constructors and these base values to your
// specifications
    // the constructors will work if you have a target to add or not but the
// world builder does defaults to adding the player as a target;
    // the stats scale with levels and the constructors default to level 1 if a
// level is not provided
    
    private static final int BASE_HP = 20;
    private static final int BASE_ATT = 10;
    private static final int BASE_DEF = 2;
    private static final int BASE_AGI = 5;
    private static final int BASE_SEARCH_RANGE = 4;
    private static final int BASE_EXP_VALUE = 20;
    
    public Goblin() {
        super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, BASE_EXP_VALUE);
        this.setSearchRange(BASE_SEARCH_RANGE);
        this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
        this.setColor(Color.GREEN);
    }
    
    public Goblin(int level) {
        super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, BASE_EXP_VALUE);
        this.setSearchRange(BASE_SEARCH_RANGE);
        this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
        this.setColor(Color.GREEN);
    }
    
    public Goblin(Actor target) {
        super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, BASE_EXP_VALUE, target, BASE_SEARCH_RANGE);
        this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
        this.setColor(Color.GREEN);
    }
    
    public Goblin(int level, Actor target) {
        super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, BASE_EXP_VALUE, target, BASE_SEARCH_RANGE);
        this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
        this.setColor(Color.GREEN);
    }
    
    @Override
    public Location findPath() {
        return this.pathfind.findPath(this.getLocation(), this.getTarget().getLocation(), this.getGrid());
    }
    
}
