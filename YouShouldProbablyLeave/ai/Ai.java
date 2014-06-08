package ai;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import player.PlayerActor;
import stats.CompleatStats;

public abstract class Ai extends Actor implements CompleatStats {
    
    private int level;
    
    private final int baseHP;
    private final int baseStr;
    private final int baseDef;
    private final int baseAgi;
    
    private int maxHP;
    private int curHP;
    
    private int strength;
    private int defense;
    private int agility;
    
    private final int xpValue;
    
    private Actor target;
    private boolean hasFoundTarget;
    private int searchRange;
    
    public Ai() {// these will change depending on the character
        // we could add some math.random for variance or parameters for the
// Player Character
        super();
        setLevel(1);
        
        this.baseHP = 100;
        setCurrentHp(this.baseHP);
        this.baseStr = 20;
        this.baseDef = 10;
        this.baseAgi = 5;
        
        this.hasFoundTarget = false;
        this.searchRange = 20;
        
        this.xpValue = 10;
        
        levelUp();
    }
    
    public Ai(Actor target, int searchRange) {// these will change depending on
// the character
        // we could add some math.random for variance or parameters for the
// Player Character
        super();
        setLevel(1);
        
        this.baseHP = 100;
        setCurrentHp(this.baseHP);
        this.baseStr = 20;
        this.baseDef = 10;
        this.baseAgi = 5;
        
        this.hasFoundTarget = false;
        
        this.target = target;
        this.searchRange = searchRange;
        
        this.xpValue = 10;
        
        levelUp();
    }
    
    public Ai(int level, int baseHp, int baseStr, int baseDef, int baseAgi, int xpValue, Actor target, int searchRange) {// use
// this to change the stats of other AIs
        super();
        setLevel(level);
        
        this.baseHP = baseHp;
        setCurrentHp(this.baseHP);
        this.baseStr = baseStr;
        this.baseDef = baseDef;
        this.baseAgi = baseAgi;
        
        this.target = target;
        this.hasFoundTarget = false;
        this.searchRange = searchRange;
        
        this.xpValue = xpValue;
        
        levelUp();
    }
    
    public Ai(int level, int baseHp, int baseStr, int baseDef, int baseAgi, int xpValue) {// if
// the actor has no target it will find one the same way a critter would
        super();
        setLevel(level);
        
        this.baseHP = baseHp;
        setCurrentHp(this.baseHP);
        this.baseStr = baseStr;
        this.baseDef = baseDef;
        this.baseAgi = baseAgi;
        
        this.hasFoundTarget = false;
        this.searchRange = 2;
        
        this.xpValue = xpValue;
        
        levelUp();
    }
    
    public Actor getTarget() {
        return this.target;
    }
    
    public void setTarget(Actor target) {
        this.target = target;
    }
    
    public void resetTarget() {
        this.target = null;
    }
    
    public abstract Location findPath();
    
    public void findTarget() {
        ArrayList<Actor> actors = getGrid().getNeighbors(getLocation());
        for (Actor a : actors) {
            if (a instanceof PlayerActor) {
                this.target = a;
                break;
            }
        }
    }
    
    public boolean search() {// compares the it's location to the location of
// it's target to find the target
        // returns true if the target is near enough to be "found"
        if (this.hasFoundTarget) {
            if (getDistanceToTarget() <= this.searchRange * 1.5) {// increased search range
// when the target has been found to represent "awareness"
                return true;
            } else {
                this.hasFoundTarget = false;
                return false;
            }
        } else {
            if (getDistanceToTarget() <= this.searchRange) {
                this.hasFoundTarget = true;
                return true;
            } else {
                return false;
            }
        }
    }
    
    public int getDistanceToTarget() {
    	int distance = Math.abs(this.getLocation().getCol() - target.getLocation().getCol());
        distance += Math.abs(this.getLocation().getRow() - target.getLocation().getRow());
        return distance;
    }
    
    @Override
    public void act() {
        if (!checkLife()) {
        	if (this.target != null  && target instanceof CompleatStats) {
        		System.out.println(this.target + " has gained " + (this.xpValue * level) + " exp");
        		((CompleatStats)target).addExp(this.xpValue * level);
        	}
            removeSelfFromGrid();
            return;
        }
        if (this.target != null) {
            if (search()) {
                if (getDistanceToTarget() == 1) {
                    // attack
                	if (target instanceof CompleatStats) {
                		((CompleatStats)target).takeDamage(this.getStrength());
                	}
                } else {
                    // find path, move to it;
                	Location next = findPath();
                	System.out.println(this.toString() + " will move to " + next);
                	if ( next != null && getGrid().get(next) == null) {
                		this.moveTo(next);
                	}
                }
            }
        } else {
            findTarget();
        }
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
        if (hp >= this.maxHP) {
            this.curHP = this.maxHP;
        } else {
            this.curHP = hp;
        }
    }
    
    @Override
    public boolean checkLife() {
        return this.curHP > 0;
    }
    
    @Override
    public void takeDamage(int damage) {
        if (Math.random() > getAvoidPercent()) {
        	if (damage <= defense) {
        		System.out.println("Damage Taken: " + (damage - defense));
        		this.curHP -= damage - defense;
        	} else {
        		System.out.println("Damage Taken: 1");
        		this.curHP--;
        	}
        }
    }
    
    @Override
    public void restoreHealth(int healing) {
        if (this.curHP + healing >= this.maxHP) {
            this.curHP = this.maxHP;
        } else {
            this.curHP += healing;
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
        if (agi >= 99) {// agility will be a % chance and must be < 100
            this.agility = 99;
        } else {
            this.agility = agi;
        }
    }
    
    @Override
    public double getAvoidPercent() {
        return this.agility / 100.0;
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
        setMaxHp((int) (this.baseHP * (1 + level / 50.0)));
        setCurrentHp(getMaxHp());
        setStrength((int) (this.baseStr * (1 + level / 50.0)));
        setDefense((int) (this.baseDef * (1 + level / 50.0)));
        setAgility((int) (this.baseAgi * (1 + level / 50.0)));
    }
    
    @Override
    public int getNextExp() {
        return 0;
    }
    
    @Override
    public void setNextExp(int exp) {}
    
    @Override
    public int getExp() {
        return 0;
    }
    
    @Override
    public void setExp(int exp) {}
    
    @Override
    public void addExp(int exp) {}
    
    public void setSearchRange(int range) {
    	this.searchRange = range;
    }
    
    public int getSearchRange() {
    	return this.searchRange;
    }
    
}