package main;

import player.PlayerActor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;

public class YSPLRunner { 

public static void main(String[] args) {
    ActorWorld world = new ActorWorld();
    YSPL.world = world;
    PlayerActor player = new PlayerActor();
    YSPL.player = player;
    world.add(new Bug());
    world.add(player);
    world.show();
}

}
