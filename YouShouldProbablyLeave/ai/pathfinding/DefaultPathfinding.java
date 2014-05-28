package ai.pathfinding;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import world.grid.AdvancedLocation;
import world.grid.GridMap;

public class DefaultPathfinding implements Pathfinding {
    public final int MaxPathLength;

    /**
     * Default constructor for the Pathfinding. Uses
     * Pathfinding.DefaultPathfindingSteps for MaxPathLength.
     */
    public DefaultPathfinding() {
        MaxPathLength = Pathfinding.DefaultPathfindingSteps;
    }

    /**
     * @param steps
     *            in the MaxPathLength.
     */
    public DefaultPathfinding(int steps) {
        MaxPathLength = steps;
    }

    @Override
    public AdvancedLocation findPath(Location start, Location end, GridMap<Actor> grid) {
        ArrayList<Space> queue = new ArrayList<Space>();
        queue.add(new Space(new AdvancedLocation(end), 0));
        for (int i = 0; i < queue.size(); i++) {
            try {
                consolidate(queue, findNextSpace(queue.get(i), grid));
            } catch (Exception e) {}
        }
        Space best = null;
        for (Space i : queue) {
            if (i.loc.isAdjacent(start)) {
                if (i.counter < best.counter) {
                    best = i;
                }
            }
        }
        return best.loc;
    }

    void consolidate(ArrayList<Space> queue, ArrayList<Space> addingLocations) {
        for (Space i : addingLocations) {
            for (int j = 0; j < queue.size(); j++) {
                Space test = queue.get(j);
                if (test.loc.getRow() == i.loc.getRow() && test.loc.getCol() == i.loc.getCol()) {
                    if (test.counter > i.counter) {
                        break;
                    } else {
                        queue.remove(j);
                        queue.add(i);
                    }
                }
            }
        }
    }

    ArrayList<Space> findNextSpace(Space current, GridMap<Actor> grid) throws Exception {
        if (current.counter <= this.MaxPathLength) {
            ArrayList<Space> out = new ArrayList<Space>();
            // Finding all possible locations to travel to.
            if (grid.isValid(current.loc.getAdjacentLocation(Location.NORTH))) {
                AdvancedLocation aloc = new AdvancedLocation(current.loc.getAdjacentLocation(Location.NORTH));
                if (grid.isValid(aloc)) {
                    out.add(new Space(aloc, current.counter++));
                }
            }
            if (grid.isValid(current.loc.getAdjacentLocation(Location.EAST))) {
                AdvancedLocation aloc = new AdvancedLocation(current.loc.getAdjacentLocation(Location.EAST));
                if (grid.isValid(aloc)) {
                    out.add(new Space(aloc, current.counter++));
                }
            }
            if (grid.isValid(current.loc.getAdjacentLocation(Location.SOUTH))) {
                AdvancedLocation aloc = new AdvancedLocation(current.loc.getAdjacentLocation(Location.SOUTH));
                if (grid.isValid(aloc)) {
                    out.add(new Space(aloc, current.counter++));
                }
            }
            if (grid.isValid(current.loc.getAdjacentLocation(Location.WEST))) {
                AdvancedLocation aloc = new AdvancedLocation(current.loc.getAdjacentLocation(Location.WEST));
                if (grid.isValid(aloc)) {
                    out.add(new Space(aloc, current.counter++));
                }
            }
            return out;
        } else {
            throw new Exception();
        }
    }

    class Space {
        public AdvancedLocation loc;
        public int counter;

        public Space(AdvancedLocation loc, int counter) {
            this.loc = loc;
            this.counter = counter;
        }
    }
}
