package ai;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class AIRunner {

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(10, 10));
        Rock rock = new Rock();
        BasicAI ai = new BasicAI(rock, 4);
        world.add(new Location(3, 3), rock);
        world.add(new Location(1, 1), ai);
        ai.setTarget(rock);
        System.out.println(ai.getLocation().getCol());
        world.show();
    }
}
