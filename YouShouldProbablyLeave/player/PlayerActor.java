package player;

import java.awt.event.KeyEvent;
import main.YSPLStatics;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class PlayerActor extends Actor {

public PlayerActor() {
    super();
}

public void act() {
    // the KeyEvent code can later be replaced with ones loaded from a config
    // document
    if (YSPLStatics.keys.getKey(KeyEvent.VK_W) == true) {
        moveTo(new Location(super.getLocation().getRow(), super.getLocation().getCol() + 1));
        // move up
        return;
    }
    if (YSPLStatics.keys.getKey(KeyEvent.VK_S) == true) {
        moveTo(new Location(super.getLocation().getRow(), super.getLocation().getCol() - 1));
        // move down
        return;
    }
    if (YSPLStatics.keys.getKey(KeyEvent.VK_A) == true) {
        moveTo(new Location(super.getLocation().getRow() - 1, super.getLocation().getCol()));
        // move left
        return;
    }
    if (YSPLStatics.keys.getKey(KeyEvent.VK_D) == true) {
        moveTo(new Location(super.getLocation().getRow() + 1, super.getLocation().getCol() + 1));
        // move right
        return;
    }
    // add others for inventory and stuff
}
}
