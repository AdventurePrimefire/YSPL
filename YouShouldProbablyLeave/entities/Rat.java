package entities;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.io.File;

import ai.Ai;
import ai.pathfinding.DefaultPathfinding;
import ai.pathfinding.Pathfinding;
import display.PathedImage;

public class Rat extends Ai implements PathedImage {
    public static final File file = new File("resource//image//entities//Rat.gif");
    private Pathfinding pathfind;
    
    private static final int BASE_HP = 15;
    private static final int BASE_ATT = 4;
    private static final int BASE_DEF = 2;
    private static final int BASE_AGI = 20;
    private static final int BASE_SEARCH_RANGE = 4;
    private static final int EXP_VALUE = 10;
    
    public Rat() {
        super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, EXP_VALUE);
        this.setSearchRange(BASE_SEARCH_RANGE);
        this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
        this.setColor(Color.BLACK);
    }
    
    public Rat(int level) {
        super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, EXP_VALUE);
        this.setSearchRange(BASE_SEARCH_RANGE);
        this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
        this.setColor(Color.BLACK);
    }
    
    public Rat(Actor target) {
        super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, EXP_VALUE, target, BASE_SEARCH_RANGE);
        this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
        this.setColor(Color.BLACK);
    }
    
    public Rat(int level, Actor target) {
        super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, EXP_VALUE, target, BASE_SEARCH_RANGE);
        this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
        this.setColor(Color.BLACK);
    }
    
    @Override
    public Location findPath() {
        return this.pathfind.findPath(this.getLocation(), this.getTarget().getLocation(), this.getGrid());
    }
    
}
