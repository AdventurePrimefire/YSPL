package world.tile.floor;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.io.File;

import display.ConectedImageDisplay.ConnectedImage;
import display.ConectedTexture;
import display.PathedImage;

public class StoneWall extends Actor implements PathedImage, ConectedTexture {
    public static final File file = new File("resource//image//world//StoneWall.gif");

    public StoneWall() {
        super.setBlocksMovment(false);
    }

    @Override
    public void getConnectedImage(ConnectedImage image) {
        Actor testing;
        boolean[] isWall = new boolean[4];
        try {
            testing = super.getGrid().get(super.getLocation().getAdjacentLocation(Location.NORTH));
            if (testing != null) {
                if (testing instanceof StoneWall) {
                    isWall[0] = true;
                }
            }
        } catch (Exception e) {

        }
        try {

            testing = super.getGrid().get(super.getLocation().getAdjacentLocation(Location.EAST));
            if (testing != null) {
                if (testing instanceof StoneWall) {
                    isWall[1] = true;
                }
            }
        } catch (Exception e) {}
        try {

            testing = super.getGrid().get(super.getLocation().getAdjacentLocation(Location.SOUTH));
            if (testing != null) {
                if (testing instanceof StoneWall) {
                    isWall[2] = true;
                }
            }
        } catch (Exception e) {}
        try {
            testing = super.getGrid().get(super.getLocation().getAdjacentLocation(Location.WEST));
            if (testing != null) {
                if (testing instanceof StoneWall) {
                    isWall[3] = true;
                }
            }
        } catch (Exception e) {}
        // Seting images
        if (isWall[0]) {
            if (isWall[3]) {
                image.setImageLocation(0, 1, 1);
            } else {
                image.setImageLocation(0, 0, 0);
            }
        } else {
            if (isWall[3]) {
                image.setImageLocation(0, 0, 1);
            } else {
                image.setImageLocation(0, 0, 0);
            }
        }

        if (isWall[0]) {
            if (isWall[1]) {
                image.setImageLocation(1, 1, 1);
            } else {
                image.setImageLocation(1, 1, 2);
            }
        } else {
            if (isWall[1]) {
                image.setImageLocation(1, 0, 1);
            } else {
                image.setImageLocation(1, 0, 2);
            }
        }
        if (isWall[2]) {
            if (isWall[1]) {
                image.setImageLocation(2, 1, 1);
            } else {
                image.setImageLocation(2, 1, 2);
            }
        } else {
            if (isWall[1]) {
                image.setImageLocation(2, 2, 1);
            } else {
                image.setImageLocation(2, 2, 2);
            }
        }
        if (isWall[2]) {
            if (isWall[3]) {
                image.setImageLocation(3, 1, 1);
            } else {
                image.setImageLocation(3, 1, 0);
            }
        } else {
            if (isWall[3]) {
                image.setImageLocation(3, 2, 1);
            } else {
                image.setImageLocation(3, 2, 0);
            }
        }
    }
}
