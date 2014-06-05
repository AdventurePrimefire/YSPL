package world.tile.floor;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.io.File;
import java.util.ArrayList;

import display.ConectedImageDisplay.ConnectedImage;
import display.ConectedTexture;
import display.PathedImage;

public class StoneWall extends Actor implements PathedImage, ConectedTexture {
    public static final File file = new File("resource//image//world//StoneWall.gif");

    @Override
    public void getConnectedImage(ConnectedImage image) {
        ArrayList<Location> locs = super.getGrid().getOccupiedAdjacentLocations(super.getLocation());
    }
}
