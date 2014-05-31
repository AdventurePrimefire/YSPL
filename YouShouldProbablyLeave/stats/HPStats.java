package stats;

public interface HPStats extends Stats {
    /**
     * @return the max hp value.
     */
    public int getMaxHp();

    /**
     * @param hp
     *            new max hp.
     */
    public void setMaxHp(int hp);

    /**
     * @return current hp (can be more then max).
     */
    public int getCurrentHp();

    /**
     * @param new current hp.
     */
    public void setCurrentHp(int hp);

    /**
     * @return true if they are alive.
     */
    public default boolean checkLife() {
        if (this.getCurrentHp() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param damage
     *            value of the damage to take.
     */
    public default void takeDamage(int damage) {
        setCurrentHp(getCurrentHp() - damage);
    }

    /**
     * @param healing
     *            amount of health regained.
     */
    public default void restoreHealth(int healing) {
        setCurrentHp(getCurrentHp() + healing);
    }

}
