package ai;

import info.gridworld.actor.*;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import world.grid.GridMap;

public class AIRunner {
	
	public static void main(String[] args) {
	ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(10, 10));
	Rock rock = new Rock();
	BasicAI ai = new BasicAI(rock, 4);
	world.add(new Location(3, 2), rock);
    world.add(new Location(1, 1), ai);
	System.out.println(ai.getLocation().getCol());
    world.show();
	}
}
