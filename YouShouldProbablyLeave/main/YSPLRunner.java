package main;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import player.PlayerActor;
import util.GridUtil;
import world.grid.GridMap;
import world.grid.MapLayer;
import world.tile.floor.StoneFloor;

public class YSPLRunner {

    public static void main(String[] args) {
        GridMap<Actor> grid = new GridMap<Actor>(10, 10);
        ActorWorld world = new ActorWorld(grid);
        YSPL.world = world;
        PlayerActor player = new PlayerActor();
        YSPL.player = player;
        GridUtil.fill(grid, StoneFloor.class, MapLayer.FloorLevel);
        world.add(new Bug());
        world.add(player);
        world.show();
    }

}
