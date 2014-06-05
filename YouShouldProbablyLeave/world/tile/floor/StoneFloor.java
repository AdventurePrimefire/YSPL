package world.tile.floor;

import info.gridworld.actor.Actor;

import java.io.File;

import display.PathedImage;

public class StoneFloor extends Actor implements PathedImage {
    public static final File file = new File("resource//image//world//StoneFloor.gif");
    
    public StoneFloor() {
        super();
    }
}
