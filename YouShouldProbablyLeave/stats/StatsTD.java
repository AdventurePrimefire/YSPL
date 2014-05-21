package stats;

import info.gridworld.actor.ActorWorld;

public class StatsTD {

	public static void main(String args[]) {
		ActorWorld world = new ActorWorld();
		StandardStatisImplementationTest actor = new StandardStatisImplementationTest();
		world.add(actor);
		world.show();
	}

}
