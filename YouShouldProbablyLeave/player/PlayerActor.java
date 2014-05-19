package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class PlayerActor extends Actor implements KeyListener {
private boolean[] keys = new boolean[255];

public PlayerActor() {
    super();
}

public void act() {
    // the KeyEvent code can later be replaced with ones loaded from a config
    // document
    if (this.keys[KeyEvent.VK_W] == true) {
        moveTo(new Location(super.getLocation().getRow(), super.getLocation().getCol()+1));
        // move up
        return;
    }
    if (this.keys[KeyEvent.VK_S] == true) {
        moveTo(new Location(super.getLocation().getRow(), super.getLocation().getCol()-1));
        // move down
        return;
    }
    if (this.keys[KeyEvent.VK_A] == true) {
        moveTo(new Location(super.getLocation().getRow()-1, super.getLocation().getCol()));
        // move left
        return;
    }
    if (this.keys[KeyEvent.VK_D] == true) {
        moveTo(new Location(super.getLocation().getRow()+1, super.getLocation().getCol()+1));
        // move right
        return;
    }
    // add others for inventory and stuff
}

@Override
public void keyPressed(KeyEvent key) {
    if (key.getKeyCode() < 255) {
        this.keys[key.getKeyCode()] = true;
    }
}

@Override
public void keyReleased(KeyEvent key) {
    if (key.getKeyCode() < 255) {
        this.keys[key.getKeyCode()] = false;
    }
}

@Override
public void keyTyped(KeyEvent arg0) {}
}
