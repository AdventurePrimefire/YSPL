package world.builder;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import main.YSPL;
import player.PlayerActor;
import util.GridUtil;
import world.grid.AdvancedLocation;
import world.grid.GridMap;
import world.grid.MapLayer;
import world.tile.floor.StoneFloor;
import world.tile.floor.StoneWall;
import entities.Goblin;
import entities.Orc;
import entities.Rat;

public class Builder {

    // the builder reads files in the following format:
    // (an int the height of the board) (an int the width of the world)
    //
    // (the tile key) (tile level) (key) (level) (key) (level) ect...
    // the first two numbers are the size of the board
    // the following graph shows a two letter key that determines what is in
// that position
    // and then a number to denote the level of the enemy, if the key represents
// something like a wall
    // then the level will be ignored but the number is still required to be
// there
    //
    // ex:
    // Wa 0 would be a wall
    // Go 4 would be a lv 4 goblin
    // Pl 0 represents the player (the player levels up on its own so no need
// for a level)
    //
    // error management:
    //
    // if the key does not exist the tile will be left blank
    // if the level provided for an entity is not valid the level will default
// to 1
    //
    // key index:
    //
    // if you need to ad keys simply add more if/else statements in the same
// format as the others
    //
    // Wa: wall
    // Pl: player
    // Or: orc
    // Go: goblin
    // Ra: rat
    // 00: empty space (properly implemented)
    //
    // notes:
    //
    // -keys are case sensitive
    // -keys can be more than two letters but for sanity's sake I've kept them
// monospace
    // -there is no handling for having too many keys, i have not tested this
// but it will most likely throw an Out of bounds exception
    // -Currently all my links to boards are direct, i haven't gotten relative
// links to work

    public static ActorWorld buildFromFile(String filePath) throws FileNotFoundException {
        PlayerActor player = new PlayerActor();
        ActorWorld world; // = new ActorWorld(new GridMap<Actor>(y, x));

        // File loadFile = new File("board1.txt");
        File loadFile = new File(filePath);
        Scanner fileReader = new Scanner(loadFile);

        int y = fileReader.nextInt();
        int x = fileReader.nextInt();
        GridMap<Actor> grid = new GridMap<Actor>(y, x);
        world = new ActorWorld(grid);

        int posX = 0, posY = 0;

        while (fileReader.hasNext()) {
            String next = fileReader.next();
            int level = fileReader.nextInt();

            if (next.equals("Pl")) {
                world.add(new Location(posY, posX), player);
                YSPL.player = player;
            } else if (next.equals("Wa")) {
                world.add(new AdvancedLocation(new Location(posY, posX), MapLayer.FloorLevel), new StoneWall());
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
                // leave null
            } else {
                System.err.println("Key: " + next + " is not valid. Leaving cell empty.");
            }

            posY++;
            if (posY == y) {// OOB exception
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

        // File loadFile = new File("board1.txt");
        File loadFile = new File(filePath);
        Scanner fileReader = new Scanner(loadFile);

        int y = fileReader.nextInt();
        int x = fileReader.nextInt();

        world = new ActorWorld(new GridMap<Actor>(y, x));

        int posX = 0, posY = 0;
        GridUtil.fill((GridMap<Actor>) world.getGrid(), StoneFloor.class, MapLayer.FloorLevel);
        while (fileReader.hasNext()) {
            String next = fileReader.next();
            int level = fileReader.nextInt();

            if (next.equals("Pl")) {
                world.add(new Location(posY, posX), player);
            } else if (next.equals("Wa")) {
                world.add(new AdvancedLocation(new Location(posY, posX), MapLayer.FloorLevel), new StoneWall());
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
                // leave null
            } else {
                System.err.println("Key: " + next + " is not valid. Leaving cell empty.");
            }

            posY++;
            if (posY == y) {// OOB exception
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
