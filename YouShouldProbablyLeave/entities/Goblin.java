package entities;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import ai.Ai;
import ai.pathfinding.*;

public class Goblin extends Ai {
	
	private Pathfinding pathfind;
	
	private static final int BASE_HP = 20;
	private static final int BASE_ATT = 5;
	private static final int BASE_DEF = 2;
	private static final int BASE_AGI = 5;
	private static final int BASE_SEARCH_RANGE = 4;
	
	
	public Goblin() {
		super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI);
		this.setSearchRange(BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
	}
	
	public Goblin(int level) {
		super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI);
		this.setSearchRange(BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
	}
	
	public Goblin(Actor target) {
		super(1, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, target, BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
	}
	
	public Goblin(int level, Actor target) {
		super(level, BASE_HP, BASE_ATT, BASE_DEF, BASE_AGI, target, BASE_SEARCH_RANGE);
		this.pathfind = new DefaultPathfinding(BASE_SEARCH_RANGE);
	}

	@Override
	public Location findPath() {
		return this.pathfind.findPath(this.getLocation(), this.getTarget().getLocation(), this.getGrid());
	}

}
