package stats;

public interface Stats {
	//HP Methods
	//these are how you not die
	public int getMaxHp();//get/set Maximum HP
	public void setMaxHp(int hp);
	
	public int getCurrentHp();//get/set Current HP
	public void setCurrentHp(int hp);
	
	public boolean checkLife();//return true if the actor is alive
	public void takeDamage(int damage);//reduce CurrentHp by an amount
	public void restoreHealth(int healing);//increase CurrentHp by an amount
	
	//Other Stats
	public int getStrength();//your damage output or a modifier for your weapon
	public void setStrength(int str);
	
	public int getDefense();//your damage resistance, improved upon by armor 
	public void setDefense(int def);
	
	public int getAgility();// a % chance to avoid attacks
	public void setAgility(int agi);
	public double getAvoidPercent();//returns a double < 0 that is proportional to agility
	
	//Leveling 
	public int getLevel();//the actual level
	public void setLevel(int lev);
	public void levelUp();//increases stats (or the basestat's increment)
	
	public int getNextExp();//the exp needed to increase level
	public void setNextExp(int exp);
	
	public int getExp();//the exp the character has (this should be reset to 0 on level up)
	public void setExp(int exp);
	public void addExp(int exp);
}

