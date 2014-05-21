package stats;

public interface Stats {
	//HP Methods
	//these are how you not die
	public int getMaxHp();//get/set Maximum HP
	public void setMaxHp();
	
	public int getCurentHp();//get/set Current HP
	public void setCurentHp();
	
	public boolean checkLife();//return true if the actor is alive
	public void takeDamage(int damage);//reduce CurrentHp by an amount
	public void restoreHealth(int healing);//increase CurrentHp by an amount
	
	//Other Stats
	public int getStrength();//your damage output or a modifier for your weapon
	public void setStrength();
	
	public int getDefense();//your damage resistance, improved upon by armor 
	public void setDefense();
	
	public int getAgility();// a % chance to avoid attacks
	public void setAgility();
	public double getAvoidPercent();//returns a double < 0 that is proportional to agility
}

