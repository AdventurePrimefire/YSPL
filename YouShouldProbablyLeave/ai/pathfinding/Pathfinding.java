package ai.pathfinding;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import world.grid.AdvancedLocation;
import world.grid.GridMap;

public interface Pathfinding {
    public static final int DefaultPathfindingSteps = 16;

    /**
     * @param start
     *            Location of starting position.
     * @param end
     *            Location of the ending position.
     * @param grid
     *            that the Locations are in.
     * @return the Location that it should move to.
     */
    public AdvancedLocation findPath(Location start, Location end, GridMap<Actor> grid);
}
