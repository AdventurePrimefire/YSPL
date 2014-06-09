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

    public StoneWall() {
        super.setBlocksMovment(true);
    }

    @Override
    public void getConnectedImage(ConnectedImage image) {
        Actor testing;
        boolean[] isWall = new boolean[4];
        ArrayList<Actor> adj = super.getGrid().getNeighbors(super.getLocation());
        for (Actor i : adj) {
            if (i instanceof StoneWall) {
                int dir = super.getLocation().getDirectionToward(i.getLocation());
                switch (dir) {
                    case Location.NORTH:
                        isWall[0] = true;
                        break;
                    case Location.EAST:
                        isWall[1] = true;
                        break;
                    case Location.SOUTH:
                        isWall[2] = true;
                        break;
                    case Location.WEST:
                        isWall[3] = true;
                        break;
                }
            }
        }
// try {
// testing =
// super.getGrid().get(super.getLocation().getAdjacentLocation(Location.NORTH));
// if (testing != null) {
// if (testing instanceof StoneWall) {
// isWall[0] = true;
// }
// }
// } catch (Exception e) {
//
// }
// try {
//
// testing =
// super.getGrid().get(super.getLocation().getAdjacentLocation(Location.EAST));
// if (testing != null) {
// if (testing instanceof StoneWall) {
// isWall[1] = true;
// }
// }
// } catch (Exception e) {}
// try {
//
// testing =
// super.getGrid().get(super.getLocation().getAdjacentLocation(Location.SOUTH));
// if (testing != null) {
// if (testing instanceof StoneWall) {
// isWall[2] = true;
// }
// }
// } catch (Exception e) {}
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
                image.setImageLocation(0, 1, 1);// good
            } else {
                image.setImageLocation(0, 0, 1);// good
            }
        } else {
            if (isWall[3]) {
                image.setImageLocation(0, 1, 0);// good
            } else {
                image.setImageLocation(0, 0, 0);// good
            }
        }

        if (isWall[0]) {
            if (isWall[1]) {
                image.setImageLocation(1, 1, 1);// good
            } else {
                image.setImageLocation(1, 2, 1);// good
            }
        } else {
            if (isWall[1]) {
                image.setImageLocation(1, 1, 0);// good
            } else {
                image.setImageLocation(1, 2, 0);// good
            }
        }
        if (isWall[1]) {
            if (isWall[2]) {
                image.setImageLocation(2, 1, 1);// good
            } else {
                image.setImageLocation(2, 1, 2);// good
            }
        } else {
            if (isWall[2]) {
                image.setImageLocation(2, 2, 1);// good
            } else {
                image.setImageLocation(2, 2, 2);// good
            }
        }
        if (isWall[2]) {
            if (isWall[3]) {
                image.setImageLocation(3, 1, 1);
            } else {
                image.setImageLocation(3, 0, 1);
            }
        } else {
            if (isWall[3]) {
                image.setImageLocation(3, 1, 2);
            } else {
                image.setImageLocation(3, 0, 2);
            }
        }
    }
}
