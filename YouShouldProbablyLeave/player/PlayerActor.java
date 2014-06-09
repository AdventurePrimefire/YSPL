package player;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.event.KeyEvent;

import ai.Ai;
import stats.CompleatStats;
import java.io.File;

import main.YSPL;
import display.PathedImage;

public class PlayerActor extends Actor implements CompleatStats, PathedImage {
private boolean running = false;
    public static final File file = new File("resource//image//actors//PlayerActor.gif");
    

private int level;

private int maxHP;
private int curHP;

private int strength;
private int defense;
private int agility;

private int nextExp;
private int exp;

// the stats are set in the constructor,
//the act method checks for level ups and makes sure the player is still alive
//i've added a return statement to prevent the game crashing on death but the game will still crash if there are two entities attacking the player
//if the player presses C the player actor will skip their turn
//Pressing the WASD keys will still move but no longer through walls and if there is an enemy in the way the player will attack it 


public PlayerActor() {
    super();
    this.level = 1;
    
    this.maxHP = 100;
    this.curHP = 100;
    
    this.strength = 20;
    this.defense = 10;
    this.agility = 5;
    
    this.nextExp = 50;
    this.exp = 0;
}

public void act() {
	if (!checkLife()) {
		System.err.println("Player has died");
		removeSelfFromGrid();
		return;
	} else {
		while (exp >= nextExp) {
			System.out.println("player level up");
			exp -= nextExp;
			levelUp();
		}
	}
}

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
        Actor tar = getGrid().get(loc);
        if (tar != null) {
        	if (tar instanceof Ai) {
        		System.out.println("the player attacks...");
        		System.out.println("Target: " + tar);
        		((Ai) tar).takeDamage(this.strength);
        	}
        } else {
        	if (super.getGrid().isValid(loc)) {
        		moveTo(loc);
        	}
        }
        return true;
    }
    if (YSPL.keys.keys[KeyEvent.VK_A] == true) {
        Location loc = new Location(super.getLocation().getRow(), super.getLocation().getCol() - 1);
        Actor tar = getGrid().get(loc);
        if (tar != null) {
        	if (tar instanceof Ai) {
        		System.out.println("the player attacks...");
        		System.out.println("Target: " + tar);
        		((Ai) tar).takeDamage(this.strength);
        	}
        } else {
        	if (super.getGrid().isValid(loc)) {
        		moveTo(loc);
        	}
        }
		return true;
    }
    if (YSPL.keys.keys[KeyEvent.VK_W] == true) {
        Location loc = new Location(super.getLocation().getRow() - 1, super.getLocation().getCol());
        Actor tar = getGrid().get(loc);
        if (tar != null) {
        	if (tar instanceof Ai) {
        		System.out.println("the player attacks...");
        		System.out.println("Target: " + tar);
        		((Ai) tar).takeDamage(this.strength);
        	}
        } else {
        	if (super.getGrid().isValid(loc)) {
        		moveTo(loc);
        	}
        }
		return true;
    }
    if (YSPL.keys.keys[KeyEvent.VK_S] == true) {
        Location loc = new Location(super.getLocation().getRow() + 1, super.getLocation().getCol());
        Actor tar = getGrid().get(loc);
        if (tar != null) {
        	if (tar instanceof Ai) {
        		System.out.println("the player attacks...");
        		System.out.println("Target: " + tar);
        		((Ai) tar).takeDamage(this.strength);
        	}
        } else {
        	if (super.getGrid().isValid(loc)) {
        		moveTo(loc);
        	}
        }
		return true;
    }
    
    if (YSPL.keys.keys[KeyEvent.VK_C] == true) {
    	//do nothing
    	System.out.println("the player does nothing");
    	return true;
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
	this.nextExp += 5 * level;
	
	boolean points = false;
	
	while (!points) {
	
		if (Math.random() > .2) {
			points = true;
			this.maxHP += 5;
		}
		this.curHP = this.maxHP;
	
		if (Math.random() > .2) {
			points = true;
			this.strength++;
			if (Math.random() > .5) {
				this.strength += 2;
			}
		}
		
		if (Math.random() > .2) {
			points = true;
			this.defense++;
			if (Math.random() > .5) {
				this.defense += 2;
			}
		}
		
		if (Math.random() > .2) {
			points = true;
			this.agility++;
		}
	
	}
	
}

@Override
public int getNextExp() {
	return this.nextExp;
}

@Override
public void setNextExp(int exp) {
	this.nextExp = exp;
	
}

@Override
public int getExp() {
	return this.exp;
}

@Override
public void setExp(int exp) {
	this.exp = exp;
	
}

@Override
public void addExp(int exp) {
	this.exp += exp;
	
}

public double getAvoidPercent() {
	return getAgility() / 100.0;
}

public void takeDamage(int damage) {
if (Math.random() > getAvoidPercent()) {
	if (damage > defense) {
		this.curHP -= (damage - defense);
		System.out.println("the player takes: " + (damage - defense) + " dammage");
	} else {
		this.curHP--;
		System.out.println("the player takes: 1 dammage");
	}
} else {
	System.out.println("the player avoids attack");
}
}

public boolean checkLife() {
	if (curHP <= 0) {
		return false;
	} else {
		return true;
	}
}
}
