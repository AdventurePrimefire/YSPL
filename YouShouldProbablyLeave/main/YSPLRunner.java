package main;

import player.PlayerActor;
import info.gridworld.actor.ActorWorld;

public class YSPLRunner {

public static void main(String[] args) {
    ActorWorld world = new ActorWorld();
    PlayerActor player = new PlayerActor();
    world.addKeyListener(player);
    world.add(player);
    world.show();
}

}
