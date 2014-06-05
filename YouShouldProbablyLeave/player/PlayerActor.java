package player;

import java.awt.event.KeyEvent;

import stats.CompleatStats;
import main.YSPL;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class PlayerActor extends Actor implements CompleatStats {
private boolean running = false;

private int level;

private int maxHP;
private int curHP;

private int strength;
private int defense;
private int agility;

private int nextExp;
private int exp;

public PlayerActor() {
    super();
    this.level = 1;
    
    this.maxHP = 100;
    this.curHP = 100;
    
    this.strength = 10;
    this.defense = 5;
    this.agility = 5;
    
    this.nextExp = 50;
    this.exp = 0;
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

@Override
public int getMaxHp() {
	return this.maxHP;
}

@Override
public void setMaxHp(int hp) {
	this.maxHP = hp;
	
}

@Override
public int getCurrentHp() {
	return this.curHP;
}

@Override
public void setCurrentHp(int hp) {
	if (hp > this.maxHP) {
		this.curHP = this.maxHP;
	} else {
		this.curHP = hp;
	}
	
}

@Override
public int getStrength() {
	return this.strength;
}

@Override
public void setStrength(int str) {
	this.strength = str;
	
}

@Override
public int getDefense() {
	return this.defense;
}

@Override
public void setDefense(int def) {
	this.defense = def;
	
}

@Override
public int getAgility() {
	return this.agility;
}

@Override
public void setAgility(int agi) {
	this.agility = agi;
	
}

@Override
public int getLevel() {
	return this.level;
}

@Override
public void setLevel(int lev) {
	this.level = lev;
	
}

@Override
public void levelUp() {
	this.level++;
	
	if (Math.random() > .2) {
		this.maxHP += 5;
	}
	this.curHP = this.maxHP;
	
}

@Override
public int getNextExp() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public void setNextExp(int exp) {
	// TODO Auto-generated method stub
	
}

@Override
public int getExp() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public void setExp(int exp) {
	// TODO Auto-generated method stub
	
}

@Override
public void addExp(int exp) {
	// TODO Auto-generated method stub
	
}
}
