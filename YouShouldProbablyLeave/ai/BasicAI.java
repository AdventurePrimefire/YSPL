package ai;

import world.grid.GridMap;
import ai.pathfinding.DefaultPathfinding;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class BasicAI extends Ai {
	
	public final DefaultPathfinding pathfinding;

	public BasicAI() {
		super();
		this.pathfinding = new DefaultPathfinding();
	}
	
	public BasicAI(Actor target, int searchRange) {
		super(target, searchRange);
		this.pathfinding = new DefaultPathfinding(searchRange);
	}

	@Override
	public Location findPath() {
		return this.pathfinding.findPath(this.getLocation(), this.getTarget().getLocation(), this.getGrid());
	}
}
