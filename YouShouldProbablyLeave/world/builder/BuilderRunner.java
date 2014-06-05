package world.builder;

import info.gridworld.actor.ActorWorld;

import java.io.*;

import player.PlayerActor;

public class BuilderRunner {

	public static void main(String[] args) throws FileNotFoundException {
		
		PlayerActor player = new PlayerActor();
		ActorWorld world; // = new ActorWorld(new GridMap<Actor>(y, x));
		
		world = Builder.buildFromFile("C:/Users/vicin_000/git/yspl/YouShouldProbablyLeave/world/builder/board2.txt", player);
		
		world.show();
	}
	
}
