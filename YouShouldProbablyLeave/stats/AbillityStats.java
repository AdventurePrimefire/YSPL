package stats;

public interface AbillityStats extends Stats {
    /**
     * @return the character str value.
     */
    public int getStrength();
    
    /**
     * @param str
     *            new str value.
     */
    public void setStrength(int str);
    
    /**
     * @return the characters defense value.
     */
    public int getDefense();
    
    /**
     * @param def
     *            new def value
     */
    public void setDefense(int def);
    
    /**
     * @return characters aafility value.
     */
    public int getAgility();
    
    /**
     * @param agi
     *            new agility value.
     */
    public void setAgility(int agi);
    
    /**
     * @return the % dodge of the character
     */
    public default double getAvoidPercent() {
        return getAgility() / 100.0;
    }
}
