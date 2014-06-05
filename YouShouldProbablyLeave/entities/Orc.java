package entities;

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import ai.Ai;
import ai.pathfinding.*;

public class Orc extends Ai {
	
	private Pathfinding pathfind;
	
	private static final int BASE_HP = 30;
	private static final int BASE_ATT = 8;
	private static final int BASE_DEF = 5;
	private static final int BASE_AGI = 3;
	private static final int BASE_SEARCH_RANGE = 4;
	private static final int EXP_VALUE = 40;
	
	public Orc() {
		super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, EXP_VALUE);
		this.setSearchRange(BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
		this.setColor(Color.GRAY);
	}
	
	public Orc(int level) {
		super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, EXP_VALUE);
		this.setSearchRange(BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
		this.setColor(Color.GRAY);
	}
	
	public Orc(Actor target) {
		super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, EXP_VALUE, target, BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
		this.setColor(Color.GRAY);
	}
	
	public Orc(int level, Actor target) {
		super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, EXP_VALUE, target, BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
		this.setColor(Color.GRAY);
	}

	@Override
	public Location findPath() {
		return this.pathfind.findPath(this.getLocation(), this.getTarget().getLocation(), this.getGrid());
	}

}
