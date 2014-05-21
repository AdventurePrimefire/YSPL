/**
 * 
 */
package stats;

import info.gridworld.actor.Actor;

public class StandardStatisImplementationTest extends Actor implements Stats {
	
	private int maxHP;
	private int curHP;
	
	private int strength;
	private int defense;
	private int agility;

	public StandardStatisImplementationTest() {//these will change depending on the character
		//we could add some math.random for variance or parameters for the Player Character
		super();
		setMaxHp(100);
		setCurrentHp(getMaxHp());
		setStrength(20);
		setDefense(10);
		setAgility(5);
	}
	
	@Override
	public void act() {
		if (!checkLife()) {
			removeSelfFromGrid();
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
		return (this.curHP > 0);
	}

	@Override
	public void takeDamage(int damage) {
		if (Math.random() > getAvoidPercent()) {
			this.curHP -= (damage - defense);
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
		if (agi >= 99) {//agility will be a % chance and must be < 100
			this.agility = 99;
		} else {
			this.agility = agi;
		}
	}

	@Override
	public double getAvoidPercent() {
		return (double)this.agility / 100.0;
	}

}
