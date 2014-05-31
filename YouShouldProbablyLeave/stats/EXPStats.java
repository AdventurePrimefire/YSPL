package stats;

public interface EXPStats extends Stats {
    public int getLevel();
    
    public void setLevel(int lev);
    
    public void levelUp();
    
    public int getNextExp();
    
    public void setNextExp(int exp);
    
    public int getExp();
    
    public void setExp(int exp);
    
    public void addExp(int exp);
}
