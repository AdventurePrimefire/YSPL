package player;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.event.KeyEvent;
import java.io.File;

import main.YSPL;
import display.PathedImage;

public class PlayerActor extends Actor implements PathedImage {
    private boolean running = false;
    public static final File file = new File("resource//image//actors//PlayerActor.gif");

    public PlayerActor() {
        super();
    }
    
    @Override
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
        // the KeyEvent code can later be replaced with ones loaded from a
// config
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
