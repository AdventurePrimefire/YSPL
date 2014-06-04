package util;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import world.grid.GridMap;
import world.grid.MapLayer;

public class GridUtil {
    public static void fill(GridMap<Actor> gridmap, Class actor, MapLayer layer) {
        if (Actor.class.isAssignableFrom(actor)) {
            Grid<Actor> grid = gridmap.getLayer(layer);
            for (int row = 0; row < grid.getNumRows(); row++) {
                for (int col = 0; col < grid.getNumCols(); col++) {
                    try {
                        ((Actor) actor.newInstance()).putSelfInGrid(grid, new Location(row, col));
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
