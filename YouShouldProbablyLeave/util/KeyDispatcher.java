package util;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import main.YSPL;

public class KeyDispatcher implements KeyEventDispatcher {
public boolean[] keys = new boolean[255];

public boolean dispatchKeyEvent(KeyEvent e) {
    if (e.getID() == KeyEvent.KEY_PRESSED) {
        keys[e.getKeyCode()] = true;
        YSPL.player.newInput();
    } else if (e.getID() == KeyEvent.KEY_RELEASED) {
        keys[e.getKeyCode()] = false;
    } else if (e.getID() == KeyEvent.KEY_TYPED) {
        
    }
    return false;
}

public boolean[] getKeyArray() {
    return Arrays.copyOf(keys, 255);
}

public boolean getKey(int KeyCode) {
    return keys[KeyCode];
}
}
