package entities;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import ai.Ai;
import ai.pathfinding.*;

public class Rat extends Ai {
	
	private Pathfinding pathfind;
	
	private static final int BASE_HP = 15;
	private static final int BASE_ATT = 4;
	private static final int BASE_DEF = 2;
	private static final int BASE_AGI = 20;
	private static final int BASE_SEARCH_RANGE = 4;
	
	public Rat() {
		super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI);
		this.setSearchRange(BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
	}
	
	public Rat(int level) {
		super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI);
		this.setSearchRange(BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
	}
	
	public Rat(Actor target) {
		super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, target, BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
	}
	
	public Rat(int level, Actor target) {
		super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, target, BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
	}

	@Override
	public Location findPath() {
		return this.pathfind.findPath(this.getLocation(), this.getTarget().getLocation(), this.getGrid());
	}

}
