package world.builder;

import java.io.*;
import java.util.*;

import player.PlayerActor;
import entities.*;
import world.grid.GridMap;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;


public class BuilderRunner {

	public static void main(String[] args) throws FileNotFoundException {
		
		PlayerActor player = new PlayerActor();
		ActorWorld world; // = new ActorWorld(new GridMap<Actor>(y, x));
		
		//File loadFile = new File("board1.txt");
		File loadFile = new File("C:/Users/vicin_000/git/yspl/YouShouldProbablyLeave/world/builder/board2.txt");
		Scanner fileReader = new Scanner(loadFile);
		
		int y = fileReader.nextInt();
		int x = fileReader.nextInt();
		
		world = new ActorWorld(new GridMap<Actor>(y, x));
		
		int posX = 0, posY = 0;
		
		while (fileReader.hasNext()) {
			String next = fileReader.next();
			
			if (next.equals("Pl")) {
				world.add(new Location(posY, posX), player);
			} else if (next.equals("Wa")) {
				world.add(new Location(posY, posX), new Wall()); 
			} else if (next.equals("Go")) {
				world.add(new Location(posY, posX), new Goblin(player));
			} else if (next.equals("Or")) {
				world.add(new Location(posY, posX), new Orc(player));
			} else if (next.equals("Ra")) {
				world.add(new Location(posY, posX), new Rat(player));
			} else if (next.equals("00")) {
				//leave null
			} else {
				//throw exception?
			}
			
			posY++;
			if (posY == y) {//OOB exception
				posY = 0;
				posX++;
				if (posX == x) {
					break;
				}
			}
		}
		
		fileReader.close();
		
		world.show();
	}
	
}
