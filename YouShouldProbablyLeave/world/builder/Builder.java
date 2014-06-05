package world.builder;

import info.gridworld.actor.*;
import info.gridworld.grid.Location;

import java.io.*;
import java.util.Scanner;

import player.PlayerActor;
import world.grid.GridMap;
import entities.*;

public class Builder {
	
	public static ActorWorld buildFromFile(String filePath) throws FileNotFoundException {
		PlayerActor player = new PlayerActor();
		ActorWorld world; // = new ActorWorld(new GridMap<Actor>(y, x));
		
		//File loadFile = new File("board1.txt");
		File loadFile = new File(filePath);
		Scanner fileReader = new Scanner(loadFile);
		
		int y = fileReader.nextInt();
		int x = fileReader.nextInt();
		
		world = new ActorWorld(new GridMap<Actor>(y, x));
		
		int posX = 0, posY = 0;
		
		while (fileReader.hasNext()) {
			String next = fileReader.next();
			int level = fileReader.nextInt();
			
			if (next.equals("Pl")) {
				world.add(new Location(posY, posX), player);
			} else if (next.equals("Wa")) {
				world.add(new Location(posY, posX), new Wall()); 
			} else if (next.equals("Go")) {
				if (level >= 0) {
					world.add(new Location(posY, posX), new Goblin(level, player));
				} else {
					System.err.println("Level: " + level + " is not aplicable for key: " + next + ". Setting level to 1.");
					world.add(new Location(posY, posX), new Goblin(player));
				}
			} else if (next.equals("Or")) {
				if (level >= 0) {
					world.add(new Location(posY, posX), new Orc(level, player));
				} else {
					System.err.println("Level: " + level + " is not aplicable for key: " + next + ". Setting level to 1.");
					world.add(new Location(posY, posX), new Orc(player));
				}
			} else if (next.equals("Ra")) {
				if (level >= 0) {
					world.add(new Location(posY, posX), new Rat(level, player));
				} else {
					System.err.println("Level: " + level + " is not aplicable for key: " + next + ". Setting level to 1.");
					world.add(new Location(posY, posX), new Rat(level, player));
				}
			} else if (next.equals("00")) {
				//leave null
			} else {
				System.err.println("Key: " + next + " is not valid. Leaving cell empty.");
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
		
		return world;
	}
	
	public static ActorWorld buildFromFile(String filePath, PlayerActor player) throws FileNotFoundException {
		ActorWorld world; // = new ActorWorld(new GridMap<Actor>(y, x));
		
		//File loadFile = new File("board1.txt");
		File loadFile = new File(filePath);
		Scanner fileReader = new Scanner(loadFile);
		
		int y = fileReader.nextInt();
		int x = fileReader.nextInt();
		
		world = new ActorWorld(new GridMap<Actor>(y, x));
		
		int posX = 0, posY = 0;
		
		while (fileReader.hasNext()) {
			String next = fileReader.next();
			int level = fileReader.nextInt();
			
			if (next.equals("Pl")) {
				world.add(new Location(posY, posX), player);
			} else if (next.equals("Wa")) {
				world.add(new Location(posY, posX), new Wall()); 
			} else if (next.equals("Go")) {
				if (level >= 0) {
					world.add(new Location(posY, posX), new Goblin(level, player));
				} else {
					System.err.println("Level: " + level + " is not aplicable for key: " + next + ". Setting level to 1.");
					world.add(new Location(posY, posX), new Goblin(player));
				}
			} else if (next.equals("Or")) {
				if (level >= 0) {
					world.add(new Location(posY, posX), new Orc(level, player));
				} else {
					System.err.println("Level: " + level + " is not aplicable for key: " + next + ". Setting level to 1.");
					world.add(new Location(posY, posX), new Orc(player));
				}
			} else if (next.equals("Ra")) {
				if (level >= 0) {
					world.add(new Location(posY, posX), new Rat(level, player));
				} else {
					System.err.println("Level: " + level + " is not aplicable for key: " + next + ". Setting level to 1.");
					world.add(new Location(posY, posX), new Rat(level, player));
				}
			} else if (next.equals("00")) {
				//leave null
			} else {
				System.err.println("Key: " + next + " is not valid. Leaving cell empty.");
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
		
		return world;
	}

}
