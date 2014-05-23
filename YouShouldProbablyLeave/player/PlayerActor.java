package player;

import java.awt.event.KeyEvent;
import main.YSPL;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class PlayerActor extends Actor {
private boolean running = false;

public PlayerActor() {
    super();
}

public void act() {}

public void newInput() {
    if (!running) {
        running = true;
        if (moveInput()) {
            YSPL.world.getWorldFrame().getGUI().step();
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        running = false;
    }
}

private boolean moveInput() {
    // the KeyEvent code can later be replaced with ones loaded from a config
    // document
    if (YSPL.keys.keys[KeyEvent.VK_D] == true) {
        Location loc = new Location(super.getLocation().getRow(), super.getLocation().getCol() + 1);
        if (super.getGrid().isValid(loc)) {
            moveTo(loc);
            return true;
        }
    }
    if (YSPL.keys.keys[KeyEvent.VK_A] == true) {
        Location loc = new Location(super.getLocation().getRow(), super.getLocation().getCol() - 1);
        if (super.getGrid().isValid(loc)) {
            moveTo(loc);
            return true;
        }
    }
    if (YSPL.keys.keys[KeyEvent.VK_W] == true) {
        Location loc = new Location(super.getLocation().getRow() - 1, super.getLocation().getCol());
        if (super.getGrid().isValid(loc)) {
            moveTo(loc);
            return true;
        }
    }
    if (YSPL.keys.keys[KeyEvent.VK_S] == true) {
        Location loc = new Location(super.getLocation().getRow() + 1, super.getLocation().getCol());
        if (super.getGrid().isValid(loc)) {
            moveTo(loc);
            return true;
        }
    }
    return false;
}
}
