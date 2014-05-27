package main;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import player.PlayerActor;
import world.grid.GridMap;

public class YSPLRunner {

public static void main(String[] args) {
    ActorWorld world = new ActorWorld(new GridMap<Actor>(10, 10));
    YSPL.world = world;
    PlayerActor player = new PlayerActor();
    YSPL.player = player;
    world.add(new Bug());
    world.add(player);
    world.show();
}

}
